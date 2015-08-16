package kr.ac.kaist.se.artool.search.fitness;

public abstract class FitnessFunction {
	public abstract float calculate();
	public abstract boolean isBiggerValueMeantBetterFitness();
	
	public double getCohesiveFactor()
	{
		return 1;
	}
	
	public double getCouplingFactor()
	{
		return 2;
	}
}
