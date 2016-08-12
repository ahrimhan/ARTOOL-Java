package kr.ac.kaist.se.artool.search.strategy;

import kr.ac.kaist.se.artool.search.fitness.value.AtomicFitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;

public class FirstPositiveFitnessSelectionStrategy extends
		AbstractRefactoringSelectionStrategy {
	
	
	private FitnessValue prevValue = null;
	private FitnessValue retValue = null;
	private int iteration = 0;
	
	public FirstPositiveFitnessSelectionStrategy(FitnessValue initialFitnessValue) {
		super(initialFitnessValue);
		
		if( initialFitnessValue instanceof AtomicFitnessValue )
		{
			this.prevValue = (AtomicFitnessValue) initialFitnessValue;
		}
		else
		{
			throw new RuntimeException("Not atomic fitness value");
		
		}
	}

	@Override
	public synchronized boolean next(FitnessValue obj) {
		System.err.print("[" + iteration + "] first positive fitness selection...: " + obj.toString());

		if( prevValue.compareTo(obj) < 0 )
		{
			System.err.println(" Selected");
			prevValue = obj;
			retValue = obj;
			return false;
		}

		System.err.println(" Not Selected");
		return true;
	}

	@Override
	public FitnessValue done() {
		
		
		FitnessValue mmc = retValue;
		
		if( mmc != null )
		{
			System.err.println("[" + iteration + "] selected!!!: " + retValue.toString());
			iteration++;
			retValue = null;
		}
		else
		{
			System.err.println("There are no improvements");
		}
		
		return mmc;
	}

	@Override
	public boolean restrictCandidateCount()
	{
		return false;
	}
}
