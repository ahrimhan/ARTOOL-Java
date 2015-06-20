package kr.ac.kaist.se.artool.search.strategy;

import java.util.Comparator;
import java.util.Random;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class SimulatedAnnealingStrategy extends
		AbstractRefactoringSelectionStrategy {
	
	private MoveMethodCommand prevCmd = null;
	private MoveMethodCommand bestCmd = null;
	
	
	private double temperature = 10000;
	private double coolingRate = 0.003;
	private Random random;
	
	
	public SimulatedAnnealingStrategy(float initFitness, Comparator<MoveMethodCommand> comparator) {
		super(initFitness, comparator);
		
		random = new Random(System.currentTimeMillis());
	}
	
	private double acceptanceProbability(MoveMethodCommand prevCmd, MoveMethodCommand newCmd, double temperature)
	{
		if( prevCmd == null || comparator.compare(prevCmd, newCmd) < 0 )
		{
			return 1;
		}
		else
		{
			//return Math.exp(-(prevCmd.fitness - newCmd.fitness) / temperature);
			if( newCmd.fitness < prevCmd.fitness )
			{
				return Math.exp((newCmd.fitness - prevCmd.fitness) / temperature);
			}
			else
			{
				return Math.exp((prevCmd.fitness - newCmd.fitness) / temperature);
			}
		}
	}

	@Override
	public boolean next(MoveMethodCommand newCmd, float fitness) {
		boolean ret = true;
		
		newCmd.fitness = fitness;
		
		if( random.nextDouble() < acceptanceProbability(prevCmd, newCmd, temperature) )
		{
			prevCmd = newCmd;
			ret = true;
		}
		else
		{
			ret = false;
		}
		
		if( comparator.compare(bestCmd, newCmd) < 0 )
		{
			bestCmd = newCmd;
		}
		
		temperature *= 1 - coolingRate;
		
		return ret;
	}

	@Override
	public MoveMethodCommand done() {
		if( temperature <= 1 )
		{
			return null;
		}
		return prevCmd;
	}

}
