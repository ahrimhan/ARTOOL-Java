package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

public class N_IBDPC_Static {
	//interaction between different pair of classes
	public N_IBDPC_Static() {
		map4N_IBDPC = new BasicEMap<HashSet<AOMClass>, Integer>();
		map4N_IBDPM = new BasicEMap<HashSet<AOMMethod>, Integer>();
	}
	

	public static final String N_IBDPC = "N_IBDPC";
	
	public EMap<HashSet<AOMClass>, Integer> map4N_IBDPC;
	public EMap<HashSet<AOMMethod>, Integer> map4N_IBDPM;
	
	
	public <T> void increase(EMap<HashSet<T>, Integer> map, T aomElement1, T aomElement2)
	{
		HashSet<T> key = new HashSet<T>();
		key.add(aomElement1);
		key.add(aomElement2);
		
		if( map.containsKey(key) )
		{
			int idx = map.indexOfKey(key);
			Map.Entry<HashSet<T>, Integer> entry = map.get(idx);
			entry.setValue(entry.getValue() + 1);
		}
		else
		{
			map.put(key, 1);		
		}
	}
	
	public <T> Map.Entry<HashSet<T>, Integer>[] __getSortedIBDP(EMap<HashSet<T>, Integer> map, int cutline)
	{	
		HashSet<Map.Entry<HashSet<T>, Integer>> entries = new HashSet<Map.Entry<HashSet<T>, Integer>>(map.entrySet());
		
		
		Map.Entry[] ret = new Map.Entry[cutline];
		
		for( int i = 0 ; i < cutline ; i++ )
		{
			Map.Entry<HashSet<T>, Integer> maxEntry = Collections.max(entries, new Comparator<Map.Entry<HashSet<T>, Integer>>(){
				@Override
				public int compare(Map.Entry<HashSet<T>, Integer> arg0,
						Map.Entry<HashSet<T>, Integer> arg1) {
					return arg0.getValue().compareTo(arg1.getValue()) ;
				}
			}
			);
			ret[i] = maxEntry;
			entries.remove(maxEntry);
		}
		return ret;
	}
	
	public Map.Entry<HashSet<AOMMethod>, Integer>[] getSortedIBDPM(int cutline)
	{
		return __getSortedIBDP(map4N_IBDPM, cutline);
	}

	public Map.Entry<HashSet<AOMClass>, Integer>[] getSortedIBDPC(int cutline)
	{
		return __getSortedIBDP(map4N_IBDPC, cutline);
	}

	public void measure(AbstractObjectModel aom) {
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method: clazz.getMethods() )
			{
				if( method.getOwnedScope() != null )
				{
					EList<StaticMethodCall> smcList = method.getOwnedScope().getStaticMethodCalls();
					// In first iteration of the below for-loop,  dmc1 would be null.
					StaticMethodCall smc1 = null;
					
					for( int k = 0; k < (smcList.size() - 1); k++ )
					{
						StaticMethodCall smc2 = smcList.get(k);
						AOMMethod aomMethod2 = smc2.getCallee();
						AOMClass aomClass2 = smc2.getCallee().getOwner();
						if( aomClass2 != clazz)
						{
							if( smc1 != null )
							{
								AOMClass aomClass1 = smc1.getCallee().getOwner();
								AOMMethod aomMethod1 = smc1.getCallee();
								if( aomClass1 != aomClass2 && aomClass1 != clazz )
								{
									if( aomMethod1 != aomMethod2 && aomMethod1 != method && aomMethod2 != method )
									{
										increase(map4N_IBDPM, aomMethod1, aomMethod2);
									}
									increase(map4N_IBDPC, aomClass1, aomClass2);
								}
							}
							smc1 = smc2;
						}
					}
				}
			}
		}
	}
}
