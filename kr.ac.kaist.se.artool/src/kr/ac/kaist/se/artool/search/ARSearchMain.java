package kr.ac.kaist.se.artool.search;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.engine.StatusLogger;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.candidate.CandidateIterator;
import kr.ac.kaist.se.artool.search.candidate.CandidateSelection;
import kr.ac.kaist.se.artool.search.candidate.DeltaMatrixEngine;
import kr.ac.kaist.se.artool.search.candidate.RandomCandidateSelection;
import kr.ac.kaist.se.artool.search.fitness.ConnectivityEngine;
import kr.ac.kaist.se.artool.search.fitness.EPMEngine;
import kr.ac.kaist.se.artool.search.fitness.FitnessFunction;
import kr.ac.kaist.se.artool.search.fitness.MPCEngine;
import kr.ac.kaist.se.artool.search.fitness.MSCEngine;
import kr.ac.kaist.se.artool.search.fitness.QMoodEngine;
import kr.ac.kaist.se.artool.search.strategy.AbstractRefactoringSelectionStrategy;
import kr.ac.kaist.se.artool.search.strategy.BestFitnessSelectionStrategy;
import kr.ac.kaist.se.artool.search.strategy.FirstPositiveFitnessSelectionStrategy;
import kr.ac.kaist.se.artool.search.strategy.SimulatedAnnealingStrategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ARSearchMain {
	
	private static ARSearchMain instance;
	//public static Logger candidateLogger = LogManager.getLogger("Candidate");
	//public static Logger selectionLogger = LogManager.getLogger("Selection");
	public static Logger logger = LogManager.getLogger("SimpleLogger");

	public enum FitnessType
	{
		FLEXIBILITY, REUSABILITY, UNDERSTANDABILITY, EPM, MPC, MSC, CONNECTIVITY
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
	
	public void run(int caseIdx, String project, String timestamp, AbstractObjectModel originalAOM, FitnessType fitnessType, SearchTechType searchType, CandidateSelectionType candidateSelectionType, int max_iteration, int max_candidate_selection, int saMaxPermissibleIdleIteration, IProgressMonitor monitor) throws IOException
	{	
		long mem_usage = 0;
		AbstractObjectModel aom = EcoreUtil.copy(originalAOM);
		
//		EcoreUtil.Copier copier = new EcoreUtil.Copier(true, true);
//		
//		AbstractObjectModel aom = (AbstractObjectModel)copier.copy(originalAOM);
//		copier.copyReferences();
		
		
		
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
			searchTypeStr = "sa";
			break;
		}
		
		String fitnessTypeStr = fitnessType.name().toLowerCase();
		
		System.setProperty("candidate_filename", baseLogPath + timestamp + "/" + caseIdx + "-" + project + "-" + candidateMode + "-" + searchTypeStr + "-" + fitnessTypeStr + "/candidate.log");
		System.setProperty("selection_filename", baseLogPath + timestamp + "/" + caseIdx + "-" + project + "-" + candidateMode + "-" + searchTypeStr + "-" + fitnessTypeStr + "/selection.log");
		
		monitor.setTaskName(project + " " + candidateMode + " " + searchTypeStr + " " + fitnessTypeStr);
		
		
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
			dmEngine = new DeltaMatrixEngine(ses);
			mmr.addListener(dmEngine);
			candidateSelection = dmEngine;
			break;
		case RANDOM:
			candidateSelection = new RandomCandidateSelection(aom);
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
		case MSC:
			fitnessFunction = new MSCEngine(aom);
			break;
		case MPC:
			fitnessFunction = new MPCEngine(aom);
			break;
		case CONNECTIVITY:
			fitnessFunction = new ConnectivityEngine(aom);
			break;
		default:
			throw new RuntimeException("Strange Fitness Type");
		
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
			strategy = new SimulatedAnnealingStrategy(prevFitness, comparator, saMaxPermissibleIdleIteration);
		}
		

		
		Runtime runtime = Runtime.getRuntime();
		
		if( dmEngine != null )
		{
			dmEngine.setAdjust(true);
			dmEngine.setCohesiveFactorRate(fitnessFunction.getCouplingFactor(), fitnessFunction.getCohesiveFactor());
		}
		
		int iteration = 0;
		boolean shouldBreak;

		for(iteration = 0; iteration < max_iteration; iteration++ )
		{
			monitor.subTask(iteration + "/" + max_iteration);
			
			prevFitness = fitness;
			
			CandidateIterator candidateIterator = candidateSelection.getCandidateIterator(strategy.restrictCandidateCount() ? max_candidate_selection : -1);
			int idx = 0;
			shouldBreak = false;
			MoveMethodCommand mmc = null;
			
			for( idx = 0; candidateIterator.hasNext() && !shouldBreak; idx++  )
			{
				mmc = candidateIterator.getNextCandidate();
				
				if( mmr.doAction(mmc) )
				{
					fitness = fitnessFunction.calculate();
					
					candidateLogger.debug("{}, {}, {}, {}, {}, {}", iteration, idx, mmc.toString(), fitnessType.toString(), fitness, mmc.getDeltaValue());
					shouldBreak = !strategy.next(mmc, fitness);
					mmr.undoAction();
				
				}
			}	
			
			MoveMethodCommand selectedCommand = strategy.done();
			
			if( selectedCommand == null )	
			{
				System.err.println("There is no improvements on Main Iteration Loop");
				break;
			}
			
			monitor.worked(1);
			fitness = selectedCommand.fitness;
			
			//[IN:{}][SN:{}][SD:{}][FT:{}][FV:{}][DT:{}]
			
			//Print used memory
	        double used_memory = ((double)(runtime.totalMemory() - runtime.freeMemory())) / (1024 * 1024);
			selectionLogger.info("{}, {}, {}, {}, {}, {}, {}", iteration, idx, selectedCommand.toString(), fitnessType.toString(), fitness, selectedCommand.getDeltaValue(), used_memory);
			
			mmr.doAction(selectedCommand);
			
		}
		
		monitor.worked(max_iteration - iteration);
	}
	
	public static void dLog(String s)
	{
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.println(time.toString() + s);
	}
}
