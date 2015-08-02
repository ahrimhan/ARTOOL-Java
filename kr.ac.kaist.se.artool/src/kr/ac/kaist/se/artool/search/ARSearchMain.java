package kr.ac.kaist.se.artool.search;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
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
import kr.ac.kaist.se.artool.search.strategy.AbstractRefactoringSelectionStrategy;
import kr.ac.kaist.se.artool.search.strategy.BestFitnessSelectionStrategy;
import kr.ac.kaist.se.artool.search.strategy.FirstPositiveFitnessSelectionStrategy;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ARSearchMain {
	
	private static ARSearchMain instance;
	//public static Logger candidateLogger = LogManager.getLogger("Candidate");
	//public static Logger selectionLogger = LogManager.getLogger("Selection");
	public static Logger logger = LogManager.getLogger("SimpleLogger");

	public enum FitnessType
	{
		FLEXIBILITY, REUSABILITY, UNDERSTANDABILITY, EPM, MPC, MSC
	}
	
	public enum SearchTechType
	{
		SELECT_FIRST, SELECT_BEST, SELECT_FIRST_RESTART, SIMULATED_ANNEALING
	}
	
	public enum CandidateSelectionType
	{
		DELTA, RANDOM
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
	
	public static String baseLogPath = "/Users/igsong/log/";

	/*
	public void run(String project, AbstractObjectModel aom, FitnessType fitnessType, int max_iteration, int max_candidate_selection) throws IOException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
			
		String timestamp = format.format(new Date());
		_run(project, timestamp, aom, fitnessType, SearchTechType.SELECT_BEST, false, max_iteration, max_candidate_selection);
		_run(project, timestamp, aom, fitnessType, SearchTechType.SELECT_BEST, true, max_iteration, max_candidate_selection);
		_run(project, timestamp, aom, fitnessType, SearchTechType.SELECT_FIRST, false, max_iteration, max_candidate_selection);
		_run(project, timestamp, aom, fitnessType, SearchTechType.SELECT_FIRST, true, max_iteration, max_candidate_selection);
	}
	*/
	
	public void run(String project, String timestamp, AbstractObjectModel originalAOM, FitnessType fitnessType, SearchTechType searchType, CandidateSelectionType candidateSelectionType, int max_iteration, int max_candidate_selection) throws IOException
	{	
		long mem_usage = 0;
		AbstractObjectModel aom = EcoreUtil.copy(originalAOM);
		
		String candidateMode = candidateSelectionType.name().toLowerCase();
		
		String searchTypeStr = "nosearch";
		switch( searchType )
		{
		case SELECT_BEST:
			searchTypeStr = "best";
			break;
		case SELECT_FIRST:
			searchTypeStr = "first";
			break;
		case SELECT_FIRST_RESTART:
			throw new RuntimeException("Not Yet Implemented");
		case SIMULATED_ANNEALING:
			throw new RuntimeException("Not Yet Implemented");
		}
		
		String fitnessTypeStr = fitnessType.name().toLowerCase();
		
		System.setProperty("candidate_filename", baseLogPath + timestamp + "/" + project + "-" + candidateMode + "-" + searchTypeStr + "-" + fitnessTypeStr + "/candidate.log");
		System.setProperty("selection_filename", baseLogPath + timestamp + "/" + project + "-" + candidateMode + "-" + searchTypeStr + "-" + fitnessTypeStr + "/selection.log");
		
		org.apache.logging.log4j.core.LoggerContext ctx =
			    (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		ctx.reconfigure();
		
		Logger candidateLogger = LogManager.getLogger("Candidate");
		Logger selectionLogger = LogManager.getLogger("Selection");	
		
		// Initialize
		SystemEntitySet ses = new SystemEntitySet(aom);

		StatusLogger.getInstance().clear();
		StatusLogger.getInstance().openOriginalPhase();
		Comparator<MoveMethodCommand> comparator;

		MoveMethodRefactoring mmr = new MoveMethodRefactoring(aom);

		CandidateSelection candidateSelection;
		
		DeltaMatrixEngine dmEngine = null;
		
		
		switch( candidateSelectionType )
		{
		case DELTA:
			dmEngine = new DeltaMatrixEngine(ses, max_candidate_selection);
			mmr.addListener(dmEngine);
			candidateSelection = dmEngine;
			break;
		case RANDOM:
			candidateSelection = new RandomCandidateSelection(aom, max_candidate_selection);
			break;
		default:
			throw new RuntimeException("Waaaagh!!!!");
		}
		
		FitnessFunction fitnessFunction;
		
		
		switch( fitnessType )
		{
		case EPM:
			EPMEngine epmEngine = new EPMEngine(ses);
			fitnessFunction = epmEngine;
			mmr.addListener(epmEngine);
			

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
		
		if( fitnessFunction.isBiggerValueMeantBetterFitness() )
		{
			comparator = new Comparator<MoveMethodCommand>(){
				
				@Override
				public int compare(MoveMethodCommand o1, MoveMethodCommand o2) {
					if( o1.fitness > o2.fitness ) return 1;
					else if( o1.fitness < o2.fitness ) return -1;
					else return 0;
				}
			};
		}
		else
		{
			comparator = new Comparator<MoveMethodCommand>(){
				
				@Override
				public int compare(MoveMethodCommand o1, MoveMethodCommand o2) {
					if( o1.fitness < o2.fitness ) return 1;
					else if( o1.fitness > o2.fitness ) return -1;
					else return 0;
				}
			};	
		}
		
		
		float fitness = fitnessFunction.calculate();
		float prevFitness = fitness;
		
		AbstractRefactoringSelectionStrategy strategy = null;
		
		switch( searchType )
		{
		case SELECT_BEST:
			strategy = new BestFitnessSelectionStrategy(prevFitness, comparator);
			break;
		case SELECT_FIRST:
			strategy = new FirstPositiveFitnessSelectionStrategy(prevFitness, comparator);
			break;
		case SELECT_FIRST_RESTART:
			throw new RuntimeException("Not Yet Implemented");
		case SIMULATED_ANNEALING:
			throw new RuntimeException("Not Yet Implemented");
		}
		

		SpearmansCorrelation correlation = new SpearmansCorrelation();
		
		int tuningCandidate = max_candidate_selection / 10;
		
		double[] epmArray = new double[tuningCandidate];
		double[] deltaArray = new double[tuningCandidate];
		double maxCorr = 0;
		double maxCorrIdx = -1.0;
		Runtime runtime = Runtime.getRuntime();
		
		
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
				System.err.println("Warming Up Fitness: " + fitness);
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

			if( ((!fitnessFunction.isBiggerValueMeantBetterFitness()) && maxCorr < corr) 
					|| (fitnessFunction.isBiggerValueMeantBetterFitness() && maxCorr > corr)
					|| maxCorrIdx == -1.0)
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
			
			if( selectedCommand == null )
			{
				System.err.println("There is no improvements on Main Iteration Loop");
				return;
			}
			fitness = selectedCommand.fitness;
			
			//[IN:{}][SN:{}][SD:{}][FT:{}][FV:{}][DT:{}]
			
			//Print used memory
	        double used_memory = ((double)(runtime.totalMemory() - runtime.freeMemory())) / (1024 * 1024);
			selectionLogger.info("{}, {}, {}, {}, {}, {}, {}", iteration, idx, selectedCommand.toString(), fitnessType.toString(), fitness, selectedCommand.getDeltaValue(), used_memory);
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
