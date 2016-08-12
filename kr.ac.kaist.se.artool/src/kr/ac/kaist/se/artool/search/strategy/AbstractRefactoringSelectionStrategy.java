package kr.ac.kaist.se.artool.search.strategy;

import java.util.Comparator;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;

public abstract class AbstractRefactoringSelectionStrategy {

	protected FitnessValue initialFitnessValue;
	
	
	public AbstractRefactoringSelectionStrategy(FitnessValue initialFitnessValue)
	{
		this.initialFitnessValue = initialFitnessValue;
	}
	
	public abstract boolean next(FitnessValue fitnessValue);
	public abstract FitnessValue done();
	
	public abstract boolean restrictCandidateCount();
}
