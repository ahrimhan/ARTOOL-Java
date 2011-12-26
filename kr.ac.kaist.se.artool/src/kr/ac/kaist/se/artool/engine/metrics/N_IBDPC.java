package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.MultiKeyMap;

public  abstract class N_IBDPC {
	//interaction between different pair of classes
	public N_IBDPC() {
		map4N_IBDPC = new MultiKeyMap<AOMClass, int[]>();
		map4N_IBDPM = new MultiKeyMap<AOMMethod, int[]>();
	}
	
	private static N_IBDPC dynamic_instance = null;
	private static N_IBDPC static_instance = null;
	
	public static N_IBDPC createInstance(boolean isDynamic)
	{
		N_IBDPC ret;
		
		if( isDynamic )
		{
			if( dynamic_instance == null )
			{
				dynamic_instance = new N_IBDPC_Dynamic();
			}
			ret = dynamic_instance;
		}
		else
		{
			if( static_instance == null )
			{
				static_instance = new N_IBDPC_Static();
			}
			ret = static_instance;
		}
		
		return ret;
	}

	public static final String N_IBDPC = "N_IBDPC";
	
	public MultiKeyMap<AOMClass, int[]> map4N_IBDPC;
	public MultiKeyMap<AOMMethod, int[]> map4N_IBDPM;
	
	
//	private HashSet key = new HashSet();
	
	public Map.Entry<MultiKey<AOMMethod>, int[]>[] getSortedIBDPM(int cutline)
	{
		return UtilityFunctions.getInstance().__getSortedIBDP(map4N_IBDPM, cutline);
	}

	public Map.Entry<MultiKey<AOMClass>, int[]>[] getSortedIBDPC(int cutline)
	{
		return UtilityFunctions.getInstance().__getSortedIBDP(map4N_IBDPC, cutline);
	}
	
	protected abstract void _measure(AOMClass clazz, AOMMethod method);

	public void measure(AbstractObjectModel aom) {
		map4N_IBDPC.clear();
		map4N_IBDPM.clear();
		
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method: clazz.getMethods() )
			{
				if( method.getOwnedScope() != null )
				{
					_measure(clazz, method);
				}
			}
		}
	}
}
