package kr.ac.kaist.se.artool.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.MultiKeyMap;
import org.eclipse.emf.common.util.EMap;

public class UtilityFunctions {

	private static UtilityFunctions instance;
	
	public static UtilityFunctions getInstance()
	{
		if( instance == null )
		{
			instance = new UtilityFunctions();
		}
		return instance;
	}
		
	public <T> void increase(MultiKeyMap<T, int[]> map, T aomElement1, T aomElement2, int inc)
	{
//		AOMTuple<T> key = new AOMTuple<T>(aomElement1, aomElement2);
		
		if( map.containsKey(aomElement1, aomElement2) )
		{
			int[] i = map.get(aomElement1, aomElement2);
			i[0]+=inc;
		}
		else if(map.containsKey(aomElement2, aomElement1) )
		{
			int[] i = map.get(aomElement2, aomElement1);
			i[0]+=inc;
		}
		else
		{
			int[] i = new int[1];
			i[0] = inc;
			map.put(aomElement1, aomElement2, i);
		}
	}
	
	public <T> void increase(MultiKeyMap<T, int[]> map, T aomElement1, T aomElement2)
	{
		increase(map, aomElement1, aomElement2, 1);
	}
	
	public <T> Entry<MultiKey<T>, int[]>[] __getSortedIBDP(MultiKeyMap<T, int[]> map, int cutline)
	{	
		HashSet<Entry<MultiKey<? extends T>, int[]>> entries = new HashSet<Entry<MultiKey<? extends T>, int[]>>(map.entrySet());
		

		Entry[] ret = new Entry[cutline];
		
		for( int i = 0 ; i < cutline ; i++ )
		{
			if( entries.size() <= 0 ) break;
			Entry<MultiKey<? extends T>, int[]> maxEntry = Collections.max(entries, new Comparator<Map.Entry<MultiKey<? extends T>, int[]>>(){
				@Override
				public int compare(Map.Entry<MultiKey<? extends T>, int[]> arg0,
						Map.Entry<MultiKey<? extends T>, int[]> arg1) {
					return arg0.getValue()[0] - arg1.getValue()[0] ;
				}
			}
			);
			ret[i] = maxEntry;
			
			entries.remove(maxEntry);
		}
		return ret;
	}
	
}
