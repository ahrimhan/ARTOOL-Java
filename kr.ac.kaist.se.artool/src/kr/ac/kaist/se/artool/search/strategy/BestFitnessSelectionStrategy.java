package kr.ac.kaist.se.artool.search.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class BestFitnessSelectionStrategy extends
		AbstractRefactoringSelectionStrategy {

	private Vector<MoveMethodCommand> commandList = new Vector<MoveMethodCommand>();
	
	public BestFitnessSelectionStrategy(float prevFitness, Comparator<MoveMethodCommand> comparator) {
		super(prevFitness, comparator);
	}

	@Override
	public boolean next(MoveMethodCommand obj, float fitness) {
		obj.fitness = fitness;
		commandList.addElement(obj);
		return true;
	}

	@Override
	public MoveMethodCommand done() {
		
		MoveMethodCommand cmd = null;
		
		try
		{
			cmd = Collections.max(commandList, comparator);
			prevFitness = cmd.fitness;	
		}
		catch(Exception ex)
		{
			cmd = null;
		}
		
		
		commandList.clear();
		
		return cmd;
	}

}
