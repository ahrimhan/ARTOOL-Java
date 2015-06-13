package kr.ac.kaist.se.artool.search;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.engine.StatusLogger;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

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

		MoveMethodRefactoring mmr = new MoveMethodRefactoring(aom);

		CandidateSelection candidateSelection;
		
		if(useDeltaTable)
		{
			DeltaMatrixEngine dmEngine = new DeltaMatrixEngine(ses);
			mmr.addListener(dmEngine);
			candidateSelection = dmEngine;
		}
		else
		{
			candidateSelection = new RandomCandidateSelection(aom, max_candidate_selection);
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
		
		
		float fitness = fitnessFunction.calculate();
		float prevFitness;

		
		for(int iteration = 0; iteration < max_iteration; iteration++ )
		{
			prevFitness = fitness;
			System.out.print("select candidate...");
			Vector<MoveMethodCommand> candidates = candidateSelection.getCandidates();
			System.out.println("done");
			
			StatusLogger.getInstance().openTrialPhase();
			BestFitnessSelectionStrategy strategy = new BestFitnessSelectionStrategy(prevFitness);

			for( int idx = 0 ; idx < max_candidate_selection && idx < candidates.size() ; idx++ )
			{
				MoveMethodCommand mmc = candidates.elementAt(idx);
				StatusLogger.getInstance().openTrialSuite(mmc);
				mmr.doAction(mmc);
				System.out.print("calculate fitness...");
				fitness =  fitnessFunction.calculate();
				System.out.println("done");
				if( strategy.next(mmc, fitness) )
				{
					mmr.undoAction();	
				}
				else
				{
					mmr.undoAction();	
					break;
				}
			}	
			
			MoveMethodCommand selectedCommand = strategy.done();
			fitness = selectedCommand.fitness;
			
			StatusLogger.getInstance().selectSuite(selectedCommand);
			mmr.doAction(selectedCommand);
		}
	}
}
