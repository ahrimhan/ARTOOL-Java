package kr.ac.kaist.se.artool.search.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.fitness.value.AtomicFitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;

public class BestFitnessSelectionStrategy extends
		AbstractRefactoringSelectionStrategy {

	private Vector<FitnessValue> valueList = new Vector<FitnessValue>();
	private FitnessValue prevValue = null;
	
	public BestFitnessSelectionStrategy(FitnessValue initialFitnessValue) {
		super(initialFitnessValue);
		
		prevValue = initialFitnessValue;
	}

	@Override
	public synchronized boolean next(FitnessValue obj) {
		valueList.addElement(obj);
		return true;
	}

	@Override
	public FitnessValue done() {
		
		FitnessValue value = null;
		
		try
		{
			value = Collections.max(valueList);
			
			if( prevValue.compareTo(value) < 0 )
			{
				prevValue = value;
			}
			else
			{
				value = null;
			}
		}
		catch(Exception ex)
		{
			value = null;
		}
		
		
		valueList.clear();
		
		return value;
	}
	
	@Override
	public boolean restrictCandidateCount()
	{
		return true;
	}

}
