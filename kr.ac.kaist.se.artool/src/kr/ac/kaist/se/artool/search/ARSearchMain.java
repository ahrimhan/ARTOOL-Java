package kr.ac.kaist.se.artool.search;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Set;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.engine.StatusLogger;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.candidate.CandidateSelection;
import kr.ac.kaist.se.artool.search.candidate.DeltaMatrixEngine;
import kr.ac.kaist.se.artool.search.candidate.RandomCandidateSelection;
import kr.ac.kaist.se.artool.search.fitness.EPMEngine;
import kr.ac.kaist.se.artool.search.fitness.FitnessFunction;
import kr.ac.kaist.se.artool.search.fitness.QMoodEngine;
import kr.ac.kaist.se.artool.search.strategy.BestFitnessSelectionStrategy;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ARSearchMain {
	
	private static ARSearchMain instance;
	public static Logger candidateLogger = LogManager.getLogger("Candidate");
	public static Logger selectionLogger = LogManager.getLogger("Selection");
	public static Logger logger = LogManager.getLogger("SimpleLogger");

	public enum FitnessType
	{
		FLEXIBILITY, REUSABILITY, UNDERSTANDABILITY, EPM
	}
	
	public enum SearchTechType
	{
		SELECT_FIRST, SELECT_BEST, SELECT_FIRST_RESTART, SIMULATED_ANNEALING
	}
	
	private ARSearchMain()
	{

	}

	public static ARSearchMain getInstance()
	{
		if( instance == null )
		{
			instance = new ARSearchMain();
		}

		return instance;
	}
	

	
	public void run(AbstractObjectModel aom, FitnessType fitnessType, SearchTechType searchType, boolean useDeltaTable, int max_iteration, int max_candidate_selection) throws IOException
	{	
		long mem_usage = 0;
		
		
		
		// Initialize
		SystemEntitySet ses = new SystemEntitySet(aom);

		StatusLogger.getInstance().clear();
		StatusLogger.getInstance().openOriginalPhase();
		Comparator<MoveMethodCommand> comparator;

		MoveMethodRefactoring mmr = new MoveMethodRefactoring(aom);

		CandidateSelection candidateSelection;
		
		DeltaMatrixEngine dmEngine = null;
		
		if(useDeltaTable)
		{
			dmEngine = new DeltaMatrixEngine(ses, max_candidate_selection);
			mmr.addListener(dmEngine);
			candidateSelection = dmEngine;
		}
		else
		{
			candidateSelection = new RandomCandidateSelection(aom, max_candidate_selection);
		}
		
		FitnessFunction fitnessFunction;
		
		comparator = new Comparator<MoveMethodCommand>(){
			
			@Override
			public int compare(MoveMethodCommand o1, MoveMethodCommand o2) {
				if( o1.fitness > o2.fitness ) return 1;
				else if( o1.fitness < o2.fitness ) return -1;
				else return 0;
			}
			
		};
		
		switch( fitnessType )
		{
		case EPM:
			EPMEngine epmEngine = new EPMEngine(ses);
			fitnessFunction = epmEngine;
			mmr.addListener(epmEngine);
			
			comparator = new Comparator<MoveMethodCommand>(){
				
				@Override
				public int compare(MoveMethodCommand o1, MoveMethodCommand o2) {
					if( o1.fitness < o2.fitness ) return 1;
					else if( o1.fitness > o2.fitness ) return -1;
					else return 0;
				}
				
			};
			break;
		case FLEXIBILITY:
			fitnessFunction = new QMoodEngine(aom, QMoodEngine.TYPE.FLEXIBILITY);
			break;
		case REUSABILITY:
			fitnessFunction = new QMoodEngine(aom, QMoodEngine.TYPE.REUSABILITY);
			break;
		case UNDERSTANDABILITY:
			fitnessFunction = new QMoodEngine(aom, QMoodEngine.TYPE.UNDERSTANDABILITY);
			break;
		default:
			fitnessFunction = new QMoodEngine(aom, QMoodEngine.TYPE.FLEXIBILITY);
			break;
		
		}
		
		
		float fitness = fitnessFunction.calculate();
		float prevFitness = fitness;
		
		BestFitnessSelectionStrategy strategy = new BestFitnessSelectionStrategy(prevFitness, comparator);

		SpearmansCorrelation correlation = new SpearmansCorrelation();

		//
		//
		//
		
		int tuningCandidate = max_candidate_selection / 10;
		
		double[] epmArray = new double[tuningCandidate];
		double[] deltaArray = new double[tuningCandidate];
		double maxCorr = 0;
		double maxCorrIdx = 0;
		
		if( dmEngine != null )
		{
			dmEngine.setAdjust(false);
		}
		
		for(int warmUpIteration = 0; dmEngine != null && warmUpIteration <= 20; warmUpIteration++ )
		{
			
			Set<MoveMethodCommand> candidates = candidateSelection.getCandidates();
			
			
			dmEngine.setCohesiveFactorRate(warmUpIteration, 20 - warmUpIteration);
			
			for( int i = 0 ; i < tuningCandidate; i++ )
			{
				epmArray[i] = 0;
				deltaArray[i] = 0;
			}
			
			int idx = 0;
			
			for( MoveMethodCommand mmc : candidates )
			{
				if( idx >= tuningCandidate ) break;

				if( mmr.doAction(mmc) )
				{					
					fitness = fitnessFunction.calculate();	
					mmr.undoAction();
				}
				deltaArray[idx] = mmc.getDeltaValue();
				epmArray[idx] = fitness; 
				idx++;
			}	
			
			double[] realEPMArray = new double[idx];
			double[] realDeltaArray = new double[idx];
			System.arraycopy(epmArray, 0, realEPMArray, 0, idx);
			System.arraycopy(deltaArray, 0, realDeltaArray, 0, idx);
			
			logger.debug("corr calculate begin...");
			double corr = correlation.correlation(realEPMArray, realDeltaArray);
			logger.debug("corr calculate stopped...");
			logger.debug("corr:" + corr);

			if( maxCorr < corr )
			{
				maxCorr = corr;
				maxCorrIdx = warmUpIteration;
			}
		}
		//
		//
		//
		
		logger.debug("Selected Corr:" + maxCorr);
		
		if( dmEngine != null )
		{
			dmEngine.setAdjust(true);
			dmEngine.setCohesiveFactorRate(maxCorrIdx, 20 - maxCorrIdx);
		}
		
		
		
		for(int iteration = 0; iteration < max_iteration; iteration++ )
		{
			prevFitness = fitness;
			//System.out.print("select candidate...");
			Set<MoveMethodCommand> candidates = candidateSelection.getCandidates();
			//System.out.println("done");

			
			/*
			System.err.println();
			
			
			for( MoveMethodCommand command: candidates )
			{
				System.err.println(command);
			}
			*/
			
			
			
			//StatusLogger.getInstance().openTrialPhase();
			int idx = 0;
			for( MoveMethodCommand mmc : candidates )
			{
				if( idx >= max_candidate_selection ) break;

				//StatusLogger.getInstance().openTrialSuite(mmc);
				if( mmr.doAction(mmc) )
				{
					
					fitness = fitnessFunction.calculate();
					
					candidateLogger.debug("{}, {}, {}, {}, {}, {}", iteration, idx, mmc.toString(), fitnessType.toString(), fitness, mmc.getDeltaValue());
					
					if (strategy.next(mmc, fitness)) {
						mmr.undoAction();
					} else {
						mmr.undoAction();
						break;
					}
				}
				
				idx++;
			}	
			
			MoveMethodCommand selectedCommand = strategy.done();
			fitness = selectedCommand.fitness;
			
			//[IN:{}][SN:{}][SD:{}][FT:{}][FV:{}][DT:{}]
			selectionLogger.info("{}, {}, {}, {}, {}, {}", iteration, idx, selectedCommand.toString(), fitnessType.toString(), fitness, selectedCommand.getDeltaValue());
			//dLog("[ITERATION:" + iteration + "][DT:" + useDeltaTable + "]["+ fitnessType.toString() + ":" + fitness + "] selected!!!");
			
			//StatusLogger.getInstance().selectSuite(selectedCommand);
			mmr.doAction(selectedCommand);
		}
	}
	
	public static void dLog(String s)
	{
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.println(time.toString() + s);
	}
}
