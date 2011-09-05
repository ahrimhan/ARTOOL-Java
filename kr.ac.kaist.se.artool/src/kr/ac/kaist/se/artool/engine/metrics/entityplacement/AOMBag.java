package kr.ac.kaist.se.artool.engine.metrics.entityplacement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class AOMBag<T> {
	private HashMap<T, AOMBagEntry> entrySet;
	private boolean isUnique;
	
	
	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public AOMBag(boolean isUnique)
	{
		entrySet = new HashMap<T, AOMBagEntry>();
		this.isUnique = isUnique;
	}

	private class AOMBagEntry
	{
		public T item;
		public long count; 
		
		public AOMBagEntry(T item, long count)
		{
			this.item = item;
			this.count = count;
		}
		
		public int hashCode()
		{
			return item.hashCode();
		}
		
		public boolean equals(Object obj)
		{
			if( obj instanceof AOMBag.AOMBagEntry )
			{
				AOMBag<T>.AOMBagEntry b = (AOMBag<T>.AOMBagEntry)obj;
				return item.equals(b.item);
			}
			else
			{
				return item.equals(obj);
			}
		}
	}
	
	
	public void add(T element, long count)
	{
		if( isUnique )
		{
			if( entrySet.containsKey(element) )
			{
			}
			else
			{
				entrySet.put(element, new AOMBagEntry(element, 1));
			}		
		}
		else
		{
			if( entrySet.containsKey(element) )
			{
				AOMBagEntry entry = entrySet.get(element);
				entry.count+=count;
			}
			else
			{
				entrySet.put(element, new AOMBagEntry(element, count));
			}
		}
	}
	
	public void add(T element)
	{
		add(element, 1);
	}
	
	public void addAll(AOMBag<T> bag)
	{
		for(AOMBag<T>.AOMBagEntry entry : bag.entrySet.values())
		{
			add(entry.item, entry.count);
		}
	}
	
	public boolean contains(T element)
	{
		return entrySet.containsKey(element);
	}
	
	public void remove(T element)
	{
		entrySet.remove(element);
	}
	
	public long size()
	{
		long ret = 0;
		for(AOMBag<T>.AOMBagEntry entry : entrySet.values())
		{
			ret += entry.count;
		}
		return ret;
	}
	
	
	public AOMBag<T> union(AOMBag<T> b)
	{
		AOMBag<T> ret = new AOMBag<T>(isUnique);
		
		Set<T> keySet = new HashSet<T>();
		keySet.addAll(entrySet.keySet());
		keySet.addAll(b.entrySet.keySet());
		
		for( T key : keySet )
		{
			AOMBagEntry ae = entrySet.get(key);
			AOMBagEntry be = b.entrySet.get(key);
			long ac = ae == null ? 0 : ae.count;
			long bc = be == null ? 0 : be.count;
			ret.entrySet.put(key, new AOMBagEntry(key, ac + bc));
		}
		
		return ret;
	}
	
	public AOMBag<T> intersect(AOMBag<T> b)
	{
		AOMBag<T> ret = new AOMBag<T>(isUnique);
		
		Set<T> keySet = new HashSet<T>();
		keySet.addAll(entrySet.keySet());
		keySet.retainAll(b.entrySet.keySet());
		
		for( T key : keySet )
		{
			AOMBagEntry ae = entrySet.get(key);
			AOMBagEntry be = b.entrySet.get(key);
			long ac = ae == null ? 0 : ae.count;
			long bc = be == null ? 0 : be.count;
			long rc = ac > bc ? bc : ac;
			
			if( rc != 0 )
			{
				ret.entrySet.put(key, new AOMBagEntry(key, rc));
			}
		}
		
		return ret;
	}
}
