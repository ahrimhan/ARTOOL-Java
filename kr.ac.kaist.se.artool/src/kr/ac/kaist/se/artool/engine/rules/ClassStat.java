package kr.ac.kaist.se.artool.engine.rules;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.apache.commons.collections4.keyvalue.MultiKey;

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
	private static ClassStat dynamicStaticStat = null;

	
	public static ClassStat getDynamicStaticStat()
	{
		if( dynamicStaticStat == null ) 
		{
			dynamicStaticStat = new ClassStat();
			dynamicStaticStat.suffix = ".dynsta.csv";
		}
		return dynamicStaticStat;
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
	
	public void countOnMethodEntries(Map.Entry<MultiKey<AOMMethod>, int[]> methodEntry)
	{

		if( methodEntry == null ) return;
		for( int i = 0; i < methodEntry.getKey().size(); i++ )
		{
			countOn(methodEntry.getKey().getKey(i).getOwner());
		}
	}
	
	public void countOnClassEntries(Map.Entry<MultiKey<AOMClass>, int[]> classEntry)
	{
		
		if( classEntry == null ) return;		
		
		for( int i = 0; i < classEntry.getKey().size(); i++ )
		{
			countOn(classEntry.getKey().getKey(i));
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
