package kr.ac.kaist.se.artool.search.fitness;

import kr.ac.kaist.se.artool.search.fitness.value.AtomicFitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;

public abstract class AtomicFitnessFunction extends FitnessFunction {
	public FitnessValue calculate()
	{
		return calculateAtomic();
	}

	protected abstract AtomicFitnessValue calculateAtomic();
}
