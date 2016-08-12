package kr.ac.kaist.se.artool.search.fitness;

import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;

public abstract class FitnessFunction {
	public abstract FitnessValue calculate();
	
	public double getCohesiveFactor()
	{
		return 1;
	}
	
	public double getCouplingFactor()
	{
		return 2;
	}
}
