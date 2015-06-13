package kr.ac.kaist.se.artool.search;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public abstract class AbstractRefactoringSelectionStrategy {

	protected double initialFitness;
	
	
	public AbstractRefactoringSelectionStrategy(float initialFitness)
	{
		this.initialFitness = initialFitness;
	}
	
	public abstract boolean next(MoveMethodCommand obj, float fitness);
	public abstract MoveMethodCommand done();
}
