package kr.ac.kaist.se.artool.search;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class BestFitnessSelectionStrategy extends
		AbstractRefactoringSelectionStrategy {

	private Vector<MoveMethodCommand> commandList = new Vector<MoveMethodCommand>();
	
	public BestFitnessSelectionStrategy(float prevFitness) {
		super(prevFitness);
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
			cmd = Collections.max(commandList, new Comparator<MoveMethodCommand>(){
	
				@Override
				public int compare(MoveMethodCommand o1, MoveMethodCommand o2) {
					if( o1.fitness > o2.fitness ) return 1;
					else if( o1.fitness < o2.fitness ) return -1;
					else return 0;
				}
				
			});
		}
		catch(Exception ex)
		{
			cmd = null;
		}
		// TODO Auto-generated method stub
		return cmd;
	}

}
