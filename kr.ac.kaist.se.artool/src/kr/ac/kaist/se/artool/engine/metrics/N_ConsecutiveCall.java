package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.MultiKeyMap;

public abstract class N_ConsecutiveCall {
	//interaction between different pair of classes
	public N_ConsecutiveCall() {
		map4N_CCC = new MultiKeyMap<AOMClass, int[]>();
		map4N_CCM = new MultiKeyMap<AOMMethod, int[]>();
	}
	

	private static N_ConsecutiveCall dynamic_instance = null;
	private static N_ConsecutiveCall static_instance = null;
	
	public static N_ConsecutiveCall createInstance(boolean isDynamic)
	{
		N_ConsecutiveCall ret;
		
		if( isDynamic )
		{
			if( dynamic_instance == null )
			{
				dynamic_instance = new N_ConsecutiveCall_Dynamic();
			}
			ret = dynamic_instance;
		}
		else
		{
			if( static_instance == null )
			{
				static_instance = new N_ConsecutiveCall_Static();
			}
			ret = static_instance;
		}
		
		return ret;
	}
	
	
	public MultiKeyMap<AOMClass, int[]> map4N_CCC;
	public MultiKeyMap<AOMMethod, int[]> map4N_CCM;

	
	public Map.Entry<MultiKey<AOMMethod>, int[]>[] getSortedCCM(int cutline)
	{
		return UtilityFunctions.getInstance().__getSortedIBDP(map4N_CCM, cutline);
	}

	public Map.Entry<MultiKey<AOMClass>, int[]>[] getSortedCCC(int cutline)
	{
		return UtilityFunctions.getInstance().__getSortedIBDP(map4N_CCC, cutline);
	}
	
	protected abstract void _measure(AOMMethod method);

	public void measure(AbstractObjectModel aom) 
	{
		map4N_CCC.clear();
		map4N_CCM.clear();
		
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method: clazz.getMethods() )
			{
				if( method.getOwnedScope() != null )
				{
					
					_measure(method);
					
				}
			}
		}
	}
}
