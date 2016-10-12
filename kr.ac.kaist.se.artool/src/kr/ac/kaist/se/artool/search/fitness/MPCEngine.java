package kr.ac.kaist.se.artool.search.fitness;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.search.fitness.value.AtomicFitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.SmallerBetterFitnessValue;

public class MPCEngine extends AtomicFitnessFunction {
	private MinimalBasicMetricSuite bms;
	private AbstractObjectModel aom;
	
	public MPCEngine(AbstractObjectModel aom)
	{
		bms = new MinimalBasicMetricSuite();
		this.aom = aom;
	}
	
	public static int getInt(Object obj)
	{
		return MinimalBasicMetricSuite.getInt(obj);
	}
	
	public static long getLong(Object obj)
	{
		return MinimalBasicMetricSuite.getLong(obj);
	}
	
	
	
	@Override
	public AtomicFitnessValue calculateAtomic() {
		long mpcTotal = 0;
		int mpcCount = 0;
		
		bms.measure(aom, false, false);

		for( AOMClass clazz : aom.getClasses() )
		{
			long mpc = getLong(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.MPC));
			mpcTotal += mpc;
			mpcCount += clazz.getMethods().size();
		}
		
		double ret = (double)mpcTotal / aom.getClasses().size();
		
		
		return new SmallerBetterFitnessValue((float)ret);

	}
	
	
	public boolean isBiggerValueMeantBetterFitness()
	{
		return false;
	}
}
