package kr.ac.kaist.se.aom.profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AOMMethodCallItem extends AOMLoggingItem {
	public String callerClassName;
	public String callerMethodName;
	public String callerMethodSignature;
	public String callerFileName;
	public int callerLineNumber;

	public String calleeDynamicClassName;
	public String calleeMethodName;
	public String calleeMethodSignature;
	
	public long threadId;
	public int methodCallId;
	public int prevMethodCallId;
	
	public boolean isSynthetic;
	
	private static Queue<AOMMethodCallItem> pool;
	
	public static int getPoolSize()
	{
		return pool.size();
	}

	public static AOMMethodCallItem getInstance()
	{
		if( pool == null )
		{
			pool = new ConcurrentLinkedQueue<AOMMethodCallItem>();
		}
		
		if( pool.size() == 0 )
		{
			for( int i = 0; i < 20000; i++ )
			{
				pool.add(new AOMMethodCallItem());
			}
			AOMProfilingLogger.getErrorStream().println("20000 elements are added");
		}

		return pool.poll();
	}
	
	public static void returnInstance(AOMMethodCallItem item)
	{
		pool.add(item);
	}
	
	public static void returnInstance(Collection<AOMMethodCallItem> item)
	{
		pool.addAll(item);
	}
	
	public static final AOMMethodCallItem nullItem = new AOMMethodCallItem();
	
	static{
		nullItem.methodCallId =-1;
	}
	
	public void write(PrintWriter ds) throws IOException
	{
		if( callerClassName.endsWith("y$ErrorHighlight") ) return;
		
		ds.print(callerClassName);
		ds.print(',');
		ds.print(callerMethodName);
		ds.print(',');
		ds.print(callerMethodSignature);
		ds.print(',');
		ds.print(callerFileName);
		ds.print(',');
		ds.print(callerLineNumber);
		ds.print(',');
		
		ds.print(calleeDynamicClassName);
		ds.print(',');
		ds.print(calleeMethodName);
		ds.print(',');
		ds.print(calleeMethodSignature);
		ds.print(',');
		
		ds.print(threadId);
		ds.print(',');
		ds.print(methodCallId);
		ds.print(',');
		ds.print(prevMethodCallId);
		ds.println();
	}
	
	private AOMMethodCallItem()
	{
	}
	
	public static AOMMethodCallItem getInstance(BufferedReader di) throws IOException
	{
		AOMMethodCallItem item = new AOMMethodCallItem();
		if( !item.read(di) ) return null;
		return item;
	}
	
	public boolean read(BufferedReader reader) throws IOException{
		String line = reader.readLine();
		if( line == null ) return false;
		String[] items = line.split(",");
		
		if( items.length < 11) return false;
		
		callerClassName = items[0];
		callerMethodName = items[1];
		callerMethodSignature = items[2];
		callerFileName = items[3];
		callerLineNumber = Integer.parseInt(items[4]);
		
		calleeDynamicClassName = items[5];
		calleeMethodName = items[6];
		calleeMethodSignature = items[7];
		
		threadId = Long.parseLong(items[8]);
		methodCallId = Integer.parseInt(items[9]);
		prevMethodCallId = Integer.parseInt(items[10]);
		return true;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(threadId);
		sb.append("] ");
		sb.append(callerClassName);
		sb.append('.');
		sb.append(callerMethodName);
		sb.append('(');
		sb.append(callerFileName);
		sb.append(':');
		sb.append(callerLineNumber);
		sb.append(')');
		sb.append("->");
		sb.append(calleeDynamicClassName);
		sb.append('.');
		sb.append(calleeMethodName);
		sb.append(':');
		sb.append(calleeMethodSignature);
		
		return sb.toString();
	}
}

