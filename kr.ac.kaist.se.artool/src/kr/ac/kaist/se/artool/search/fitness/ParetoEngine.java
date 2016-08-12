package kr.ac.kaist.se.artool.search.fitness;

import java.util.Vector;

import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.ParetoCompositeFitnessValue;

public class ParetoEngine extends FitnessFunction {

	private Vector<AtomicFitnessFunction> fitnessFunctionList;
	
	public ParetoEngine()
	{
		fitnessFunctionList = new Vector<AtomicFitnessFunction>();
	}
		
	public void addFitnessFunction(AtomicFitnessFunction fitnessFunction)
	{
		fitnessFunctionList.add(fitnessFunction);
		System.err.println("Pareto Objective Function:" + fitnessFunction.getClass().getName());
	}
	
	
	@Override
	public FitnessValue calculate() {
		ParetoCompositeFitnessValue ret = new ParetoCompositeFitnessValue();
		for( AtomicFitnessFunction fitnessFunction : fitnessFunctionList )
		{
			ret.addValue(fitnessFunction.calculateAtomic());
		}
		return ret;
	}
}