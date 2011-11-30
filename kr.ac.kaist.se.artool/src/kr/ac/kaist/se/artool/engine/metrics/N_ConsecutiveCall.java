package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public abstract class N_ConsecutiveCall {
	//interaction between different pair of classes
	public N_ConsecutiveCall() {
		map4N_CCC = new HashMap<HashSet<AOMClass>, int[]>();
		map4N_CCM = new HashMap<HashSet<AOMMethod>, int[]>();
	}
	
	public static N_ConsecutiveCall createInstance(boolean isDynamic)
	{
		N_ConsecutiveCall ret;
		
		if( isDynamic )
		{
			ret = new N_ConsecutiveCall_Dynamic();
		}
		else
		{
			ret = new N_ConsecutiveCall_Static();
		}
		
		return ret;
	}

	
	public HashMap<HashSet<AOMClass>, int[]> map4N_CCC;
	public HashMap<HashSet<AOMMethod>, int[]> map4N_CCM;
	
	
	
	
	public class AOMTuple<T>
	{
		private T from;
		private T to;
		
		public AOMTuple(T from, T to)
		{
			this.from = from;
			this.to = to;
		}
		
		public T getFrom()
		{
			return from;
		}
		
		public T getTo()
		{
			return to;
		}
		
		public int hashCode()
		{
			return from.hashCode() + to.hashCode();
		}
		
		public boolean equals(Object obj)
		{
			if( obj instanceof AOMTuple )
			{
				AOMTuple tuple = (AOMTuple)obj;
				return ( tuple.from.equals(this.from) && tuple.from.equals(this.to) ) || ( tuple.from.equals(this.to) && tuple.from.equals(this.from) );
			}
			return false;
		}
	}
	
	
	public <T> void increase(HashMap<HashSet<T>, int[]> map, T aomElement1, T aomElement2)
	{
//		AOMTuple<T> key = new AOMTuple<T>(aomElement1, aomElement2);
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
	}
	
	private <T> Map.Entry<HashSet<T>, int[]>[] __getSortedIBDP(HashMap<HashSet<T>, int[]> map, int cutline)
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
	
	public Map.Entry<HashSet<AOMMethod>, int[]>[] getSortedCCM(int cutline)
	{
		return __getSortedIBDP(map4N_CCM, cutline);
	}

	public Map.Entry<HashSet<AOMClass>, int[]>[] getSortedCCC(int cutline)
	{
		return __getSortedIBDP(map4N_CCC, cutline);
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
