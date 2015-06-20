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

public class ARSearchMain {
	
	private PrintStream ps;
	private static ARSearchMain instance;
	
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
		try {

			ps = new PrintStream(new FileOutputStream("/Users/wjsong/git/ARTOOL/resultARTool/metric_result1.txt"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.err.println ("Error in writing to file");
		}

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
		
		if(useDeltaTable)
		{
			DeltaMatrixEngine dmEngine = new DeltaMatrixEngine(ses, max_candidate_selection);
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
					dLog("[Candidate:" + mmc.toString() + "]["+fitnessType.toString() + ":" + fitness + "][DT:" + mmc.getDeltaValue() +"]");										
					
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
			
			dLog("[ITERATION:" + iteration + "][DT:" + useDeltaTable + "]["+ fitnessType.toString() + ":" + fitness + "] selected!!!");
			
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
