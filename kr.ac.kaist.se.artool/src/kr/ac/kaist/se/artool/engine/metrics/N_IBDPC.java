package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.emf.common.util.EList;

public class N_IBDPC {
	//interaction between different pair of classes
	public N_IBDPC() {
		map4N_IBDPC = new HashMap<HashSet<AOMClass>, int[]>();
		map4N_IBDPM = new HashMap<HashSet<AOMMethod>, int[]>();
	}
	

	public static final String N_IBDPC = "N_IBDPC";
	
	public HashMap<HashSet<AOMClass>, int[]> map4N_IBDPC;
	public HashMap<HashSet<AOMMethod>, int[]> map4N_IBDPM;
	
	
	private HashSet key = new HashSet();
	
	public <T> void increase(HashMap<HashSet<T>, int[]> map, T aomElement1, T aomElement2)
	{
		key.clear();
		key.add(aomElement1);
		key.add(aomElement2);
		
		if( map.containsKey(key) )
		{
			int[] i = map.get(key);
			i[0]++;
		}
		else
		{
			int[] i = new int[1];
			i[0] = 1;
			map.put(key, i);		
		}
	}
	
	public <T> Map.Entry<HashSet<T>, int[]>[] __getSortedIBDP(HashMap<HashSet<T>, int[]> map, int cutline)
	{	
		HashSet<Map.Entry<HashSet<T>, int[]>> entries = new HashSet<Map.Entry<HashSet<T>, int[]>>(map.entrySet());
		
		
		Map.Entry[] ret = new Map.Entry[cutline];
		
		for( int i = 0 ; i < cutline ; i++ )
		{
			if( entries.size() <= 0 ) break;
			Map.Entry<HashSet<T>, int[]> maxEntry = Collections.max(entries, new Comparator<Map.Entry<HashSet<T>, int[]>>(){
				@Override
				public int compare(Map.Entry<HashSet<T>, int[]> arg0,
						Map.Entry<HashSet<T>, int[]> arg1) {
					return arg0.getValue()[0] - arg1.getValue()[0] ;
				}
			}
			);
			ret[i] = maxEntry;
			entries.remove(maxEntry);
		}
		return ret;
	}
	
	public Map.Entry<HashSet<AOMMethod>, int[]>[] getSortedIBDPM(int cutline)
	{
		return __getSortedIBDP(map4N_IBDPM, cutline);
	}

	public Map.Entry<HashSet<AOMClass>, int[]>[] getSortedIBDPC(int cutline)
	{
		return __getSortedIBDP(map4N_IBDPC, cutline);
	}

	public void measure(AbstractObjectModel aom) {
		map4N_IBDPC.clear();
		map4N_IBDPM.clear();
		
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method: clazz.getMethods() )
			{
				if( method.getOwnedScope() != null )
				{
					EList<DynamicMethodCall> dmcList = method.getOwnedScope().getDynamicMethodCalls();
					// In first iteration of the below for-loop,  dmc1 would be null.
					DynamicMethodCall dmc1 = null;
					
					for( int k = 0; k < (dmcList.size() - 1); k++ )
					{
						DynamicMethodCall dmc2 = dmcList.get(k);
						AOMMethod aomMethod2 = dmc2.getCallee();
						AOMClass aomClass2 = dmc2.getCallee().getOwner();
						if( aomClass2 != clazz)
						{
							if( dmc1 != null )
							{
								AOMClass aomClass1 = dmc1.getCallee().getOwner();
								AOMMethod aomMethod1 = dmc1.getCallee();
								if( aomClass1 != aomClass2 && aomClass1 != clazz )
								{
									if( aomMethod1 != aomMethod2 && aomMethod1 != method && aomMethod2 != method )
									{
										increase(map4N_IBDPM, aomMethod1, aomMethod2);
									}
									increase(map4N_IBDPC, aomClass1, aomClass2);
								}
							}
							dmc1 = dmc2;
						}
					}
				}
			}
		}
	}
}
