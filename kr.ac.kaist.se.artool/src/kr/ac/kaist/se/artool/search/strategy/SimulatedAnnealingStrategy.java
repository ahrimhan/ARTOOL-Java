package kr.ac.kaist.se.artool.search.strategy;

import java.util.Comparator;
import java.util.Random;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class SimulatedAnnealingStrategy extends
		AbstractRefactoringSelectionStrategy {
	
	private MoveMethodCommand prevCmd = null;
	
	
	private double temperature;
	private Random random;
	
	
	public SimulatedAnnealingStrategy(float initFitness, Comparator<MoveMethodCommand> comparator, double initTemp) {
		super(initFitness, comparator);
		
		temperature = initTemp;
		random = new Random(System.currentTimeMillis());
	}
	
	private double accept_level(MoveMethodCommand newCmd)
	{
		if( prevCmd == null || comparator.compare(prevCmd, newCmd) > 0 )
		{
			return 1;
		}
		else
		{
			//return Math.exp(-(prevCmd.fitness - newCmd.fitness) / temperature);
			return Math.exp((newCmd.fitness - prevCmd.fitness) / temperature);
		}
	}

	@Override
	public boolean next(MoveMethodCommand obj, float fitness) {
		obj.fitness = fitness;
		
		if( random.nextDouble() < accept_level(obj) )
		{
			prevCmd = obj;
			
			return true;
		}
		
		return false;
	}

	@Override
	public MoveMethodCommand done() {
		return prevCmd;
	}

}
