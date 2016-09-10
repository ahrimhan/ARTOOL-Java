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
	
	public ARSearchWorker(
			AbstractObjectModel originalAOM, 
			FitnessType fitnessType, 
			List<FitnessType> multiFitnessTypeList,
			SearchTechType searchType, 
			CandidateSelectionType candidateSelectionType, 
			int max_iteration, 
			int max_candidate_selection, 
			int saMaxPermissibleIdleIteration,
			int timeLimitForIteration,
			Logger candidateLogger,
			Logger selectionLogger
			)
	{
		this.aom = EcoreUtil.copy(originalAOM);
		this.fitnessType = fitnessType;
		this.multiFitnessTypeList = multiFitnessTypeList;
		this.searchType = searchType;
		this.candidateSelectionType = candidateSelectionType;
		this.max_iteration = max_iteration;
		this.max_candidate_selection = max_candidate_selection;
		this.saMaxPermissibleIdleIteration = saMaxPermissibleIdleIteration;
		this.timeLimitForIterationInMillis = timeLimitForIteration * 1000;
		this.candidateLogger = candidateLogger;
		this.selectionLogger = selectionLogger;
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
			AbstractRefactoringSelectionStrategy strategy)
	{
		int idx = 0;
		boolean shouldBreak = false;
		MoveMethodCommand mmc = null;
		CandidateIterator candidateIterator = 
				candidateSelection.getCandidateIterator(strategy.restrictCandidateCount() ? max_candidate_selection : -1,
						timeLimitForIterationInMillis > 0);

		long startTimeMillis = System.currentTimeMillis();

		for( idx = 0; candidateIterator.hasNext() && !shouldBreak; idx++  )
		{
			mmc = candidateIterator.getNextCandidate();
			
			if( mmr.doAction(mmc, false) )
			{
				FitnessValue fitnessValue = fitnessFunction.calculate();
				mmc.fitnessValue = fitnessValue;
				fitnessValue.ownedCommand = mmc;
				candidateLogger.debug("{}, {}, {}, {}, {}, {}", iteration, idx, mmc.toString(), fitnessType.toString(), fitnessValue, mmc.getDeltaValue());
				shouldBreak = !strategy.next(fitnessValue);
				mmr.undoAction();
			}
			
			if( (System.currentTimeMillis() - startTimeMillis) > timeLimitForIterationInMillis )
			{
				break;
			}
		}	
		
		candidateIterator.dispose();
		
		FitnessValue selectedValue = strategy.done();
		
		return selectedValue == null ? null : selectedValue.ownedCommand;
	}
	
	public void run(IProgressMonitor monitor)
	{		
		FitnessFunction fitnessFunction = setupFitnessFunction();
		FitnessValue initialFitnessValue = fitnessFunction.calculate();
		AbstractRefactoringSelectionStrategy strategy = setupStrategy(initialFitnessValue);
		CandidateSelection candidateSelection = setupCandidateSelection(fitnessFunction);

		StatusLogger.getInstance().openOriginalPhase();
		int iteration = 0;
		MoveMethodCommand selectedCommand = null;
		
		
		
		for(iteration = 0; iteration < max_iteration; iteration++ )
		{
			monitor.subTask(iteration + "/" + max_iteration);
			selectedCommand = selectMoveMethodCommand(iteration, candidateSelection, fitnessFunction, strategy);
			if( selectedCommand == null ) break;
			selectionLogger.info("{}, {}, {}, {}, {}", iteration, selectedCommand.toString(), fitnessType.toString(), 
					selectedCommand.fitnessValue, selectedCommand.getDeltaValue());	
			mmr.doAction(selectedCommand, true);
			monitor.worked(1);
			
			Runtime.getRuntime().gc();
			
			
		}
		
		monitor.worked(max_iteration - iteration);
	}
}
