package kr.ac.kaist.se.artool.search;

import java.util.Comparator;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public abstract class AbstractRefactoringSelectionStrategy {

	protected double initialFitness;
	protected Comparator<MoveMethodCommand> comparator;
	
	
	public AbstractRefactoringSelectionStrategy(float initialFitness, Comparator<MoveMethodCommand> comparator)
	{
		this.initialFitness = initialFitness;
		this.comparator = comparator;
	}
	
	public abstract boolean next(MoveMethodCommand obj, float fitness);
	public abstract MoveMethodCommand done();
}
