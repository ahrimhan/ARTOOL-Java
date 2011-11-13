package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class ListCache {
	
	
	private static ListCache cache = null;
	
	public static ListCache getInstance()
	{
		if( cache == null )
		{
			cache = new ListCache();
		}
		
		return cache;
	}
	private HashMap<String, Map.Entry<HashSet<AOMMethod>, int[]>[]> methodCache;
	private HashMap<String, Map.Entry<HashSet<AOMClass>, int[]>[]> classCache;
	
	private ListCache()
	{
		methodCache = new HashMap<String, Map.Entry<HashSet<AOMMethod>, int[]>[]>();
		classCache = new HashMap<String, Map.Entry<HashSet<AOMClass>, int[]>[]>();
	}
	
	public Map.Entry<HashSet<AOMMethod>, int[]>[] getMethodList(String ruleName)
	{
		return methodCache.get(ruleName);
	}
	
	public Map.Entry<HashSet<AOMClass>, int[]>[] getClassList(String ruleName)
	{
		return classCache.get(ruleName);
	}
	
	public void putMethodList(String ruleName, Map.Entry<HashSet<AOMMethod>, int[]>[] list)
	{
		methodCache.put(ruleName, list);
	}
	
	public void putClassList(String ruleName, Map.Entry<HashSet<AOMClass>, int[]>[] list)
	{
		classCache.put(ruleName, list);
	}
	
	public void reset()
	{
		methodCache.clear();
		classCache.clear();
		
	}
}
