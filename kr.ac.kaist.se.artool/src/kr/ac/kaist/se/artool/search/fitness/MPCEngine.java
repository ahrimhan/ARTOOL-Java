package kr.ac.kaist.se.artool.search.fitness;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;

public class MPCEngine extends FitnessFunction {
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
	
	
	@Override
	public float calculate() {
		float mpcTotal = 0;
		int mpcCount = 0;
		
		bms.measure(aom, false, false);

		for( AOMClass clazz : aom.getClasses() )
		{
			int mpc = getInt(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.MPC));
			mpcTotal += mpc;
			mpcCount++;
		}
		
		float ret = mpcTotal/mpcCount;
		return ret;
	}
	
	
	public boolean isBiggerValueMeantBetterFitness()
	{
		return false;
	}
}
