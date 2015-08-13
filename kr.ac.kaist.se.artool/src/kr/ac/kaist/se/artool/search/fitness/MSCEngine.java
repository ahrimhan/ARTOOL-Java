package kr.ac.kaist.se.artool.search.fitness;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.search.fitness.QMoodEngine.TYPE;

public class MSCEngine extends FitnessFunction {
	private MinimalBasicMetricSuite bms;
	private AbstractObjectModel aom;
	
	public MSCEngine(AbstractObjectModel aom)
	{
		bms = new MinimalBasicMetricSuite();
		this.aom = aom;
	}
	
	public static float getFloat(Object obj)
	{
		return MinimalBasicMetricSuite.getFloat(obj);
	}
	
	
	
	@Override
	public float calculate() {
		float mscTotal = 0;
		int mscCount = 0;
		int negCount = 0;
		float ret = 0;
		
		bms.measure(aom, true);

		for( AOMClass clazz : aom.getClasses() )
		{
			float msc = getFloat(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.MSC));
			
			if( msc >= 0 )
			{
				mscTotal += msc;
				mscCount++;
			}
			else
			{
				negCount++;
			}
		}
		
		//System.err.println("Total:" + aom.getClasses().size() + " mscCount:" + mscCount + " negCount:" + negCount);
		
		ret = mscTotal/mscCount;
		
		return ret;
	}
	
	public boolean isBiggerValueMeantBetterFitness()
	{
		return true;
	}
}
