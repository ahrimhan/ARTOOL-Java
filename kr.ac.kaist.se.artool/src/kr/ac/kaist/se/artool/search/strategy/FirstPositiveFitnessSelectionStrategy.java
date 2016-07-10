package kr.ac.kaist.se.artool.search.strategy;

import java.util.Comparator;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class FirstPositiveFitnessSelectionStrategy extends
		AbstractRefactoringSelectionStrategy {
	
	
	private MoveMethodCommand prevCmd = null;
	private MoveMethodCommand retCmd = null;
	private int iteration = 0;
	
	public FirstPositiveFitnessSelectionStrategy(float prevFitness, Comparator<MoveMethodCommand> comparator) {
		super(prevFitness, comparator);
	}

	@Override
	public synchronized boolean next(MoveMethodCommand obj) {
		System.err.print("[" + iteration + "] first positive fitness selection...: " + obj.fitness);

		if( prevCmd == null || comparator.compare(prevCmd, obj) < 0 )
		{
			System.err.println(" Selected");
			prevCmd = obj;
			retCmd = obj;
			return false;
		}
		
		System.err.println(" Not Selected");
		return true;
	}

	@Override
	public MoveMethodCommand done() {
		MoveMethodCommand mmc = retCmd;
		
		if( retCmd != null )
		{
			prevFitness = retCmd.fitness;
			System.err.println("[" + iteration + "] selected!!!: " + retCmd.fitness);
			iteration++;
			retCmd = null;
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
