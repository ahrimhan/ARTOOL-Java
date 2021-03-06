package kr.ac.kaist.se.artool.search;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.engine.StatusLogger;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.ARSearchMain.CandidateSelectionType;
import kr.ac.kaist.se.artool.search.ARSearchMain.FitnessType;
import kr.ac.kaist.se.artool.search.ARSearchMain.SearchTechType;
import kr.ac.kaist.se.artool.search.candidate.CandidateIterator;
import kr.ac.kaist.se.artool.search.candidate.CandidateSelection;
import kr.ac.kaist.se.artool.search.candidate.DeltaMatrixEngine;
import kr.ac.kaist.se.artool.search.candidate.ExhaustiveCandidateSelection;
import kr.ac.kaist.se.artool.search.candidate.NativeDeltaMatrixEngineAdaptor;
import kr.ac.kaist.se.artool.search.candidate.RandomCandidateSelection;
import kr.ac.kaist.se.artool.search.fitness.AtomicFitnessFunction;
import kr.ac.kaist.se.artool.search.fitness.ConnectivityEngine;
import kr.ac.kaist.se.artool.search.fitness.FitnessFunction;
import kr.ac.kaist.se.artool.search.fitness.MPCEngine;
import kr.ac.kaist.se.artool.search.fitness.MSCEngine;
import kr.ac.kaist.se.artool.search.fitness.NativeEPMEngineAdapter;
import kr.ac.kaist.se.artool.search.fitness.ParetoEngine;
import kr.ac.kaist.se.artool.search.fitness.QMoodEngine;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;
import kr.ac.kaist.se.artool.search.strategy.AbstractRefactoringSelectionStrategy;
import kr.ac.kaist.se.artool.search.strategy.BestFitnessSelectionStrategy;
import kr.ac.kaist.se.artool.search.strategy.FirstPositiveFitnessSelectionStrategy;
import kr.ac.kaist.se.artool.search.strategy.SimulatedAnnealingStrategy;

public class ARSearchWorker {
	private AbstractObjectModel aom;
	private FitnessType fitnessType;
	private SearchTechType searchType;
	private CandidateSelectionType candidateSelectionType;
	private int max_iteration;
	private int max_candidate_selection;
	private int saMaxPermissibleIdleIteration;
	private long timeLimitForIterationInMillis;
	private List<FitnessType> multiFitnessTypeList;


	private Logger candidateLogger;
	private Logger selectionLogger;
	
	private SystemEntitySet ses;
	private MoveMethodRefactoring mmr;
	
	private FitnessValue prevFitnessValue;
	private int searchedCandidateCountOnPrevIteration;
	private String projectSimplename;
	private Logger iterationTimeLogger;
	
	public ARSearchWorker(
			AbstractObjectModel originalAOM, 
			String projectSimplename, FitnessType fitnessType, 
			List<FitnessType> multiFitnessTypeList,
			SearchTechType searchType, 
			CandidateSelectionType candidateSelectionType, 
			int max_iteration, 
			int max_candidate_selection, 
			int saMaxPermissibleIdleIteration,
			int timeLimitForIteration,
			Logger candidateLogger,
			Logger selectionLogger, Logger iterationTimeLogger
			)
	{
		this.aom = EcoreUtil.copy(originalAOM);
		this.fitnessType = fitnessType;
		this.projectSimplename = projectSimplename;
		this.multiFitnessTypeList = multiFitnessTypeList;
		this.searchType = searchType;
		this.candidateSelectionType = candidateSelectionType;
		this.max_iteration = max_iteration;
		this.max_candidate_selection = max_candidate_selection;
		this.saMaxPermissibleIdleIteration = saMaxPermissibleIdleIteration;
		this.timeLimitForIterationInMillis = timeLimitForIteration * 1000;
		this.candidateLogger = candidateLogger;
		this.selectionLogger = selectionLogger;
		this.iterationTimeLogger = iterationTimeLogger;
		searchedCandidateCountOnPrevIteration = 0;
		ses = new SystemEntitySet(aom);
		mmr = new MoveMethodRefactoring(aom);

		StatusLogger.getInstance().clear();

	}
	
	private CandidateSelection setupCandidateSelection(FitnessFunction fitnessFunction)
	{
		DeltaMatrixEngine dmEngine = null;
//		dmEngine = new JavaDeltaMatrixEngine(ses);
		dmEngine = new NativeDeltaMatrixEngineAdaptor(ses);
		mmr.addListener(dmEngine);
		if( dmEngine != null )
		{
			dmEngine.setAdjust(true);
			dmEngine.setCohesiveFactorRate(fitnessFunction.getCouplingFactor(), fitnessFunction.getCohesiveFactor());
		}
		
		switch( candidateSelectionType )
		{
		case DELTA:
			return dmEngine;
		case RANDOM:
			return new RandomCandidateSelection(aom);
		case EXHAUSTIVE:
			return new ExhaustiveCandidateSelection(aom);
		default:
			throw new RuntimeException("Waaaagh!!!!");
		}
	}
	
	private AtomicFitnessFunction setupAtomicFitnessFunction(FitnessType fitnessType)
	{
		AtomicFitnessFunction fitnessFunction;
		
		switch( fitnessType )
		{
		case EPM:
			NativeEPMEngineAdapter epmEngine = new NativeEPMEngineAdapter(ses);
//			EPMEngine epmEngine = new EPMEngine(ses);
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
		
		return fitnessFunction;
	}

	
	private FitnessFunction setupFitnessFunction()
	{
		FitnessFunction fitnessFunction;
		
		switch( fitnessType )
		{
		case PARETO_COMPOSITE:
			ParetoEngine paretoEngine = new ParetoEngine();
			for( FitnessType multiFitnessType : multiFitnessTypeList )
			{
				paretoEngine.addFitnessFunction(setupAtomicFitnessFunction(multiFitnessType));
			}
			fitnessFunction = paretoEngine;
			break;
		default:
			fitnessFunction = setupAtomicFitnessFunction(fitnessType);
		
		}
		
		return fitnessFunction;
	}
	
	
	private AbstractRefactoringSelectionStrategy setupStrategy(FitnessValue initialFitnessValue)
	{
		AbstractRefactoringSelectionStrategy strategy = null;
		
		switch( searchType )
		{
		case SELECT_BEST:
			strategy = new BestFitnessSelectionStrategy(initialFitnessValue);
			break;
		case SELECT_FIRST:
			strategy = new FirstPositiveFitnessSelectionStrategy(initialFitnessValue);
			break;
		case SELECT_FIRST_RESTART:
			throw new RuntimeException("Not Yet Implemented");
		case SIMULATED_ANNEALING:
			strategy = new SimulatedAnnealingStrategy(initialFitnessValue, saMaxPermissibleIdleIteration);
		}
		
		return strategy;
	}
	
	private MoveMethodCommand selectMoveMethodCommand(int iteration, 
			CandidateSelection candidateSelection, 
			FitnessFunction fitnessFunction, 
			AbstractRefactoringSelectionStrategy strategy, CandidateIterator candidateIterator)
	{
		int idx = 0;
		boolean shouldBreak = false;
		MoveMethodCommand mmc = null;


		long startTimeMillis = System.currentTimeMillis();

		for( idx = 0; candidateIterator.hasNext() && !shouldBreak; idx++  )
		{
			mmc = candidateIterator.getNextCandidate();
			
			if( mmr.doAction(mmc, false) )
			{
				FitnessValue fitnessValue = fitnessFunction.calculate();
				mmc.fitnessValue = fitnessValue;
				
				candidateIterator.feedback(mmc, prevFitnessValue, fitnessValue);
				
				fitnessValue.ownedCommand = mmc;
				candidateLogger.debug("{}, {}, {}, {}, {}, {}", iteration, idx, mmc.toString(), fitnessType.toString(), fitnessValue, mmc.getDeltaValue());
				shouldBreak = !strategy.next(fitnessValue);
				mmr.undoAction();
			}
			
			if( timeLimitForIterationInMillis > 0 && (System.currentTimeMillis() - startTimeMillis) > timeLimitForIterationInMillis )
			{
				break;
			}
		}	
		
		searchedCandidateCountOnPrevIteration = idx;
		
		candidateIterator.dispose();
		
		FitnessValue selectedValue = strategy.done();
		
		if( selectedValue != null )
		{
			prevFitnessValue = selectedValue;
			System.err.println("Fitness:" + selectedValue.toString());
		}
		
		MoveMethodCommand selectedCommand = selectedValue == null ? null : selectedValue.ownedCommand;
		
		if( selectedValue != null )
		{
			selectedValue.ownedCommand = null;
		}
		
		return selectedCommand;
	}
	
	public void run(IProgressMonitor monitor)
	{		
		FitnessFunction fitnessFunction = setupFitnessFunction();
		FitnessValue initialFitnessValue = fitnessFunction.calculate();
		AbstractRefactoringSelectionStrategy strategy = setupStrategy(initialFitnessValue);
		CandidateSelection candidateSelection = setupCandidateSelection(fitnessFunction);
		
		
		prevFitnessValue = initialFitnessValue;
		
		StatusLogger.getInstance().openOriginalPhase();
		int iteration = 0;
		MoveMethodCommand selectedCommand = null;
		

		if( iteration == 0 )
		{
			selectionLogger.info("{}, {}, {}, {}, {}, {}", 0, 0, "Initial State", fitnessType.toString(), 
					initialFitnessValue, 0);	
		}
		
		for(iteration = 0; iteration < max_iteration; iteration++ )
		{
			long timestamp1 = System.currentTimeMillis(); 

			monitor.subTask(iteration + "/" + max_iteration); 
			
			CandidateIterator candidateIterator = 
					candidateSelection.getCandidateIterator(strategy.restrictCandidateCount() ? max_candidate_selection : -1,
							timeLimitForIterationInMillis > 0);
			
			long timestamp2 = System.currentTimeMillis(); 

			selectedCommand = selectMoveMethodCommand(iteration + 1, candidateSelection, fitnessFunction, strategy, candidateIterator);
			if( selectedCommand == null ) break;
			
			long timestamp3 = System.currentTimeMillis();
			
			selectionLogger.info("{}, {}, {}, {}, {}, {}", 
					iteration + 1, searchedCandidateCountOnPrevIteration, 
					selectedCommand.toString(), fitnessType.toString(), 
					selectedCommand.fitnessValue, selectedCommand.getDeltaValue());	
			mmr.doAction(selectedCommand, true);
			monitor.worked(1);
			
			long timestamp4 = System.currentTimeMillis();
			
			Runtime.getRuntime().gc();
			
			long timestamp5 = System.currentTimeMillis();
			
			
			
			iterationTimeLogger.info("{}, {}, {}, {}, {}, {}, {}, {}, {}, {}", 
					projectSimplename, fitnessType.name().toLowerCase(), max_candidate_selection, candidateSelectionType.name().toLowerCase(), iteration + 1,
					timestamp2 - timestamp1, timestamp3 - timestamp2, timestamp4 - timestamp3, timestamp5 - timestamp4, timestamp5 - timestamp1);
			
		}
		
		monitor.worked(max_iteration - iteration);
	}
}
