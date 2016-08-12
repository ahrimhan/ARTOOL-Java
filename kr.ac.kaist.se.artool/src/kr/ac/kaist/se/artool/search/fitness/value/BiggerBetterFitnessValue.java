package kr.ac.kaist.se.artool.search.fitness.value;

public class BiggerBetterFitnessValue extends AtomicFitnessValue {

	public BiggerBetterFitnessValue(float value)
	{
		this.value = value;
	}
	
	@Override
	public int compareTo(FitnessValue f) {
		AtomicFitnessValue o = (AtomicFitnessValue)f;
		if( value > o.value ) return 1;
		else if( value < o.value ) return -1;
		else return 0;
	}


}
