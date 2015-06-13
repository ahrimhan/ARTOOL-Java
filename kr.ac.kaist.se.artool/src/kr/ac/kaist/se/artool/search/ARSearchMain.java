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

import org.eclipse.swt.widgets.Shell;

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
	 
	private void trialExecute(Object rule)
	{
		//System.err.print("Trial["+rule.getName()+"]\t");
		//ps.print("Trial["+rule.getName()+"]\t");

	}
	

	
	public void run(AbstractObjectModel aom, FitnessType fitnessType, SearchTechType searchType, boolean useDeltaTable) throws IOException
	{	
		
		// Initialize
		SystemEntitySet ses = new SystemEntitySet(aom);
		DeltaMatrixEngine dme = new DeltaMatrixEngine(ses);
		StatusLogger.getInstance().clear();
		StatusLogger.getInstance().openOriginalPhase();
		QMoodFitnessFunction qmood = new QMoodFitnessFunction(aom);
		
		float fitness = qmood.calculate(aom)[QMoodFitnessFunction.TYPE.FLEXIBILITY.ordinal()];
		float prevFitness;
		long mem_usage = 0;
		
		int max_iteration = 100;
		int max_search_one_step = 100;
		MoveMethodRefactoring mmr = new MoveMethodRefactoring(aom, dme);
		
		
		for(int iteration = 0; iteration < max_iteration; iteration++ )
		{
			prevFitness = fitness;
			Vector<MoveMethodCommand> positiveRefactorings = dme.getPositiveRefactorings();
			StatusLogger.getInstance().openTrialPhase();
			BestFitnessSelectionStrategy strategy = new BestFitnessSelectionStrategy(prevFitness);

			for( int idx = 0 ; idx < max_search_one_step && idx < positiveRefactorings.size() ; idx++ )
			{
				MoveMethodCommand mmc = positiveRefactorings.elementAt(idx);
				StatusLogger.getInstance().openTrialSuite(mmc);
				mmr.doAction(mmc);
				fitness =  qmood.calculate(aom)[QMoodFitnessFunction.TYPE.FLEXIBILITY.ordinal()];
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
