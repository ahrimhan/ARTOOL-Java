package kr.ac.kaist.se.artool.search.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class FirstPositiveFitnessSelectionStrategy extends
		AbstractRefactoringSelectionStrategy {
	
	
	private MoveMethodCommand prevCmd = null;
	private MoveMethodCommand retCmd = null;
	
	public FirstPositiveFitnessSelectionStrategy(float prevFitness, Comparator<MoveMethodCommand> comparator) {
		super(prevFitness, comparator);
	}

	@Override
	public boolean next(MoveMethodCommand obj, float fitness) {
		obj.fitness = fitness;
		if( prevCmd == null || comparator.compare(prevCmd, obj) < 0 )
		{
			prevCmd = obj;
			retCmd = obj;
			return true;
		}
		
		return false;
	}

	@Override
	public MoveMethodCommand done() {
		MoveMethodCommand mmc = retCmd;
		prevFitness = retCmd.fitness;
		
		retCmd = null;
		
		return mmc;
	}

}
