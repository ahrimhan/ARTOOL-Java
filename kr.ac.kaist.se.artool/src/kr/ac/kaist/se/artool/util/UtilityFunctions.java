package kr.ac.kaist.se.artool.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.artool.engine.ARToolMain;

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

		if( cutline > entries.size() )
		{
			cutline = entries.size();
		}
		
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
	
}
