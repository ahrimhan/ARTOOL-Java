package kr.ac.kaist.se.artool.search.strategy;

import java.util.Random;

import kr.ac.kaist.se.artool.search.fitness.value.AtomicFitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;

public class SimulatedAnnealingStrategy extends
		AbstractRefactoringSelectionStrategy {
	
	private AtomicFitnessValue prevValue = null;
	private AtomicFitnessValue bestValue = null;
	private AtomicFitnessValue initValue = null;
	
	private double temperature = 0;
	private double coolingRate = 0.0025;
	private Random random;
	private double initialProbability = 0.3;
	private boolean isSetInitialTemperature;
	private int iterationCount = 0;
	private int maxIterationCount = 0;
	
	public SimulatedAnnealingStrategy(FitnessValue initialFitnessValue, int maxIterationCount) {
		super(initialFitnessValue);
		
		random = new Random(System.currentTimeMillis());
		isSetInitialTemperature = false;
		bestValue = null;
		initValue = (AtomicFitnessValue) initialFitnessValue;
		
		this.maxIterationCount = maxIterationCount;
		
		System.err.println("SimulatedAnnealingStrategy");
	}
	
	private double acceptanceProbability(AtomicFitnessValue prevValue, AtomicFitnessValue newValue, double temperature)
	{
		if( prevValue == null || prevValue.compareTo(newValue) < 0 )
		{
			return 2;
		}
		else
		{
			return Math.exp(-(prevValue.distance(newValue)) / temperature);
//			if( newValue.compareTo(prevValue) < 0 )
//			{
//				return Math.exp((newValue.getValue() - prevValue.getValue()) / temperature);
//			}
//			else
//			{
//				return Math.exp((prevValue.getValue() - newValue.getValue()) / temperature);
//			}
		}
	}

	@Override
	public boolean next(FitnessValue vv) {
		boolean ret = true;
		
		if( !(vv instanceof AtomicFitnessValue) )
		{
			throw new RuntimeException("Not atomic fitness Value");
		}
		
		AtomicFitnessValue newValue = (AtomicFitnessValue)vv;
		
		if( !isSetInitialTemperature )
		{
			temperature = -1 * newValue.distance(initValue) / Math.log(initialProbability);
			isSetInitialTemperature = true;
		}
		
		if( random.nextDouble() < acceptanceProbability(prevValue, newValue, temperature) )
		{
			prevValue = newValue;
			ret = false;
			bestValue = newValue;
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
	public FitnessValue done() {
		FitnessValue mmc = bestValue;
		
		bestValue = null;
		
		return mmc;
	}
	
	@Override
	public boolean restrictCandidateCount()
	{
		return false;
	}

}
