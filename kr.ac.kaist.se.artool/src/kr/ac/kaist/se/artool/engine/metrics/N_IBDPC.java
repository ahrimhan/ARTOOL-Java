package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

public class N_IBDPC {
	//interaction between different pair of classes
	public N_IBDPC() {
		map4N_IBDPC = new BasicEMap<HashSet<AOMClass>, Integer>();
		map4N_IBDPM = new BasicEMap<HashSet<AOMMethod>, Integer>();
	}
	

	public static final String N_IBDPC = "N_IBDPC";
	
	public EMap<HashSet<AOMClass>, Integer> map4N_IBDPC;
	public EMap<HashSet<AOMMethod>, Integer> map4N_IBDPM;
	
	
	private HashSet key = new HashSet();
	
	public <T> void increase(EMap<HashSet<T>, Integer> map, T aomElement1, T aomElement2)
	{
		key.clear();
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
			key = new HashSet();
		}
	}
	
	public <T> Map.Entry<HashSet<T>, Integer>[] __getSortedIBDP(EMap<HashSet<T>, Integer> map, int cutline)
	{	
		HashSet<Map.Entry<HashSet<T>, Integer>> entries = new HashSet<Map.Entry<HashSet<T>, Integer>>(map.entrySet());
		
		
		Map.Entry[] ret = new Map.Entry[cutline];
		
		for( int i = 0 ; i < cutline ; i++ )
		{
			if( entries.size() <= 0 ) break;
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
