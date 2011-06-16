package kr.ac.kaist.se.artool.engine.rules;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class ClassStat {
	private HashMap<String, int[]> stat;
	private String suffix;
	
	private ClassStat()
	{
		stat = new HashMap<String, int[]>();
	}
	
	private static ClassStat staticStat = null;
	
	public static ClassStat getStaticStat()
	{
		if( staticStat == null ) 
		{
			staticStat = new ClassStat();
			staticStat.suffix = ".static.csv";
		}
		return staticStat;
	}
	
	private static ClassStat dynamicStat = null;
	
	public static ClassStat getDynamicStat()
	{
		if( dynamicStat == null ) 
		{
			dynamicStat = new ClassStat();
			dynamicStat.suffix = ".dynamic.csv";
		}
		return dynamicStat;
	}
	
	
	private void countOn(AOMClass clazz)
	{
		if( clazz != null && clazz.getFqdn() != null )
		{
			int[] i = stat.get(clazz.getFqdn());
			
			if( i == null )
			{
				i = new int[1];
				i[0] = 0;
			
				stat.put(clazz.getFqdn(), i);
			}

			i[0] = i[0] + 1;
		}
	}
	
	public void countOnMethodEntries(Map.Entry<HashSet<AOMMethod>, Integer>[] methodEntries)
	{
//		HashSet<AOMClass> visitedClass = new HashSet<AOMClass>();
//
//		for( Entry<HashSet<AOMMethod>, Integer> entry : methodEntries )
//		{
//			for( AOMMethod aomMethod : entry.getKey() )
//			{
//				if( !visitedClass.contains(aomMethod.getOwner()) )
//				{
//					countOn(aomMethod.getOwner());
//					visitedClass.add(aomMethod.getOwner());
//				}
//			}
//		}
		if( methodEntries == null || methodEntries.length == 0 || methodEntries[0] == null ) return;
		
		int max = methodEntries[0].getValue();
		HashSet<AOMMethod> classSet = methodEntries[0].getKey();
		for( Entry<HashSet<AOMMethod>, Integer> entry : methodEntries )
		{
			if( entry != null && max < entry.getValue() )
			{
				max = entry.getValue();
				classSet = entry.getKey();
			}
		}
		
		for( AOMMethod aomMethod : classSet )
		{
			countOn(aomMethod.getOwner());
		}
	}
	
	public void countOnClassEntries(Map.Entry<HashSet<AOMClass>, Integer>[] classEntries)
	{
//		for( Entry<HashSet<AOMClass>, Integer> entry : classEntries )
//		{
//			for( AOMClass aomClass : entry.getKey() )
//			{
//				countOn(aomClass);
//			}
//		}
		
		if( classEntries == null || classEntries.length == 0 || classEntries[0] == null ) return;
		
		int max = classEntries[0].getValue();
		HashSet<AOMClass> classSet = classEntries[0].getKey();
		for( Entry<HashSet<AOMClass>, Integer> entry : classEntries )
		{
			if(entry != null &&  max < entry.getValue() )
			{
				max = entry.getValue();
				classSet = entry.getKey();
			}
		}
		
		for( AOMClass aomClass : classSet )
		{
			countOn(aomClass);
		}
	}
	
	public void reset()
	{
		stat.clear();
	}
	
	public void export(OutputStream os)
	{
		PrintStream ps = new PrintStream(os);
		
		Set<Map.Entry<String,int[]>> entrySet = stat.entrySet();
		for( Map.Entry<String, int[]> entry : entrySet )
		{
			if( entry.getKey() != null )
			{
				String s = entry.getKey();
				ps.println( s + "," + entry.getValue()[0] );
			}
		}
	}
	
	public void export(String filename) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(filename + suffix);
		export(fos);
		fos.close();
	}
}
