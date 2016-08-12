package kr.ac.kaist.se.artool.search.fitness;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.search.fitness.value.AtomicFitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.BiggerBetterFitnessValue;

public class ConnectivityEngine extends AtomicFitnessFunction {
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
	public AtomicFitnessValue calculateAtomic() {
		float ret = 0;
		float div = 0;
		int methodSize;
		int attrSize;
		
		bms.measure(aom, false, true);

		for( AOMClass clazz : aom.getClasses() )
		{
			float conn = getFloat(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.CONN));
			
			if( conn >= 0 )
			{
				methodSize = clazz.getMethods().size();
				attrSize = clazz.getFields().size();
				
				if( methodSize > 0 )
				{
					ret +=  attrSize * methodSize * (methodSize - 1) * conn;
					div += attrSize * methodSize * (methodSize - 1);
				}
			}
		}
		
		//System.err.println("Total:" + aom.getClasses().size() + " mscCount:" + mscCount + " negCount:" + negCount);
		
		ret = ret / div;
		
		return new BiggerBetterFitnessValue(ret);
	}
	
/*
	public double getCohesiveFactor()
	{
		return 1;
	}
	
	public double getCouplingFactor()
	{
		return 1;
	}
*/
	
}
