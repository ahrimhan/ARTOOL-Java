package kr.ac.kaist.se.artool.search.fitness.value;

public abstract class AtomicFitnessValue extends FitnessValue {
	protected float value;
	
//	public float getValue()
//	{
//		return value;
//	}
	
	@Override
	public String toString()
	{
		return Float.toString(value);
	}
	
	@Override
	public float distance(FitnessValue f) {
		AtomicFitnessValue v = (AtomicFitnessValue)f;
		return value > v.value ? value - v.value : v.value - value;
	}
}
