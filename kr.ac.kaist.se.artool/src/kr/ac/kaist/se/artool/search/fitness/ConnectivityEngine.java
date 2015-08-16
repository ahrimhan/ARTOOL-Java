package kr.ac.kaist.se.artool.search.fitness;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.search.fitness.QMoodEngine.TYPE;

public class ConnectivityEngine extends FitnessFunction {
	private MinimalBasicMetricSuite bms;
	private AbstractObjectModel aom;
	
	public ConnectivityEngine(AbstractObjectModel aom)
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
		float connTotal = 0;
		int connCount = 0;
		int negCount = 0;
		float ret = 0;
		
		bms.measure(aom, false, true);

		for( AOMClass clazz : aom.getClasses() )
		{
			float conn = getFloat(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.CONN));
			
			if( conn >= 0 )
			{
				connTotal += conn;
				connCount++;
			}
			else
			{
				negCount++;
			}
		}
		
		//System.err.println("Total:" + aom.getClasses().size() + " mscCount:" + mscCount + " negCount:" + negCount);
		
		ret = connTotal/connCount;
		
		return ret;
	}
	
	public boolean isBiggerValueMeantBetterFitness()
	{
		return true;
	}
	
	public double getCohesiveFactor()
	{
		return 8;
	}
	
	public double getCouplingFactor()
	{
		return 2;
	}
	
}
