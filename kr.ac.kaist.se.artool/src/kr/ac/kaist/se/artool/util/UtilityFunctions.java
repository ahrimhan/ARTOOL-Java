package kr.ac.kaist.se.artool.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
		
	public <T> void increase(HashMap<HashSet<T>, int[]> map, T aomElement1, T aomElement2)
	{
		HashSet<T> key = new HashSet<T>();
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
		
		key = null;
	}
	
	public <T> Map.Entry<HashSet<T>, int[]>[] __getSortedIBDP(HashMap<HashSet<T>, int[]> map, int cutline)
	{	
		
		HashSet<Map.Entry<HashSet<T>, int[]>> entries = new HashSet<Map.Entry<HashSet<T>, int[]>>(map.entrySet());

		if( cutline > entries.size() )
		{
			cutline = entries.size();
		}
		
		Map.Entry[] ret = new Map.Entry[cutline];
		
		for( int i = 0 ; i < cutline ; i++ )
		{
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
	
}
