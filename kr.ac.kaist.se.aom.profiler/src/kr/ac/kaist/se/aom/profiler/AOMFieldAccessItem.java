package kr.ac.kaist.se.aom.profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AOMFieldAccessItem extends AOMLoggingItem {
	public String accessorClassName;
	public String accessorMethodName;
	public String accessorMethodSignature;
	public String accessorFileName;
	public int accessorLineNumber;
	public String referencedClassName; 
	public String referencedFieldName;
	public boolean isReadAccess;
	public boolean isWriteAccess;
	
	private static Queue<AOMFieldAccessItem> pool;
	
	public static int getPoolSize()
	{
		return pool.size();
	}

	public static AOMFieldAccessItem getInstance()
	{
		if( pool == null )
		{
			pool = new ConcurrentLinkedQueue<AOMFieldAccessItem>();
		}
		
		if( pool.size() == 0 )
		{
			for( int i = 0; i < 20000; i++ )
			{
				pool.add(new AOMFieldAccessItem());
			}
			AOMProfilingLogger.getErrorStream().println("20000 elements are added");
		}

		return pool.poll();
	}
	
	public static void returnInstance(AOMFieldAccessItem item)
	{
		pool.add(item);
	}
	
	public static void returnInstance(Collection<AOMFieldAccessItem> item)
	{
		pool.addAll(item);
	}
	
	public void write(PrintWriter ds) throws IOException
	{
		if( accessorClassName.endsWith("y$ErrorHighlight") ) return;
		
		ds.print(accessorClassName);
		ds.print(',');
		ds.print(accessorMethodName);
		ds.print(',');
		ds.print(accessorMethodSignature);
		ds.print(',');
		ds.print(accessorFileName);
		ds.print(',');
		ds.print(accessorLineNumber);
		ds.print(',');
		
		ds.print(referencedClassName);
		ds.print(',');
		ds.print(referencedFieldName);
		ds.print(',');
		ds.print(isReadAccess);
		ds.print(',');
		ds.print(isWriteAccess);
		ds.println();
	}
	
	private AOMFieldAccessItem()
	{
	}
	
	public static AOMFieldAccessItem getInstance(BufferedReader di) throws IOException
	{
		AOMFieldAccessItem item = new AOMFieldAccessItem();
		if( !item.read(di) ) return null;
		return item;
	}
	
	public boolean read(BufferedReader reader) throws IOException{
		String line = reader.readLine();
		if( line == null ) return false;
		String[] items = line.split(",");
		
		if( items.length < 9) return false;

		accessorClassName = items[0];
		accessorMethodName = items[1];
		accessorMethodSignature = items[2];
		accessorFileName = items[3];
		accessorLineNumber = Integer.parseInt(items[4]);
		
		referencedClassName = items[5];
		referencedFieldName = items[6];
		isReadAccess = Boolean.parseBoolean(items[7]);
		isWriteAccess = Boolean.parseBoolean(items[8]);
		
		return true;
	}
	
	public String toString()
	{
		StringWriter wr = new StringWriter();
		PrintWriter ds = new PrintWriter(wr);
		
		ds.print(accessorClassName);
		ds.print(',');
		ds.print(accessorMethodName);
		ds.print(',');
		ds.print(accessorMethodSignature);
		ds.print(',');
		ds.print(accessorFileName);
		ds.print(',');
		ds.print(accessorLineNumber);
		ds.print(',');
		
		ds.print(referencedClassName);
		ds.print(',');
		ds.print(referencedFieldName);
		ds.print(',');
		ds.print(isReadAccess);
		ds.print(',');
		ds.print(isWriteAccess);
		ds.println();
		ds.flush();		
		
		String ret = wr.toString();
		ds.close();
		try {
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}

