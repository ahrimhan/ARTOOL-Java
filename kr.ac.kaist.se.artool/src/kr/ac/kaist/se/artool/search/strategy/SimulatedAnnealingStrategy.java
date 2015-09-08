package kr.ac.kaist.se.artool.search.strategy;

import java.util.Comparator;
import java.util.Random;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class SimulatedAnnealingStrategy extends
		AbstractRefactoringSelectionStrategy {
	
	private MoveMethodCommand prevCmd = null;
	private MoveMethodCommand bestCmd = null;
	
	
	private double temperature = 0;
	private double coolingRate = 0.0025;
	private Random random;
	private double initialProbability = 0.3;
	private boolean isSetInitialTemperature;
	private int iterationCount = 0;
	private int maxIterationCount = 0;
	
	public SimulatedAnnealingStrategy(float initFitness, Comparator<MoveMethodCommand> comparator, int maxIterationCount) {
		super(initFitness, comparator);
		
		random = new Random(System.currentTimeMillis());
		isSetInitialTemperature = false;
		bestCmd = null;
		
		this.maxIterationCount = maxIterationCount;
		
		System.err.println("SimulatedAnnealingStrategy");
	}
	
	private double acceptanceProbability(MoveMethodCommand prevCmd, MoveMethodCommand newCmd, double temperature)
	{
		if( prevCmd == null || comparator.compare(prevCmd, newCmd) < 0 )
		{
			return 2;
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
		
		if( !isSetInitialTemperature )
		{
			temperature = -1 * Math.abs(fitness - initialFitness) / Math.log(initialProbability);
			isSetInitialTemperature = true;
		}
		
		if( random.nextDouble() < acceptanceProbability(prevCmd, newCmd, temperature) )
		{
			prevCmd = newCmd;
			ret = false;
			bestCmd = newCmd;
		}
		else if( iterationCount < maxIterationCount ) 
		{
			System.err.println("iterationCount < maxIterationCount");
			ret = true;
		}
		else
		{
			System.err.println("else");
			ret = false;
		}
		

		iterationCount++;
		temperature *= 1 - coolingRate;
		
		return ret;
	}

	@Override
	public MoveMethodCommand done() {
		MoveMethodCommand mmc = bestCmd;
		bestCmd = null;
		
		
		if( bestCmd != null )
		{
			System.err.println("SA:fitness:" + bestCmd.fitness);
		}
		return mmc;
	}
	
	@Override
	public boolean restrictCandidateCount()
	{
		return false;
	}

}
