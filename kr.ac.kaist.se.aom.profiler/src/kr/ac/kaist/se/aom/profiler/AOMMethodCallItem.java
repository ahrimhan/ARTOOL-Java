package kr.ac.kaist.se.aom.profiler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AOMMethodCallItem implements AOMLoggingItem {

	private static final long serialVersionUID = -3648086293739497068L;

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
	
	public static final AOMMethodCallItem nullItem = new AOMMethodCallItem();
	
	static{
		nullItem.methodCallId =-1;
	}
	
	public void write(DataOutputStream ds) throws IOException
	{
		ds.writeUTF(callerClassName);
		ds.writeUTF(callerMethodName);
		ds.writeUTF(callerMethodSignature);
		ds.writeUTF(callerFileName);
		ds.writeInt(callerLineNumber);

		
		ds.writeUTF(calleeDynamicClassName);
		ds.writeUTF(calleeMethodName);
		ds.writeUTF(calleeMethodSignature);
		
		ds.writeLong(threadId);
		ds.writeInt(methodCallId);
		ds.writeInt(prevMethodCallId);
	}
	
	private AOMMethodCallItem()
	{
		
	}
	
	public static AOMMethodCallItem getInstance()
	{
		if( pool == null )
		{
			pool = new ConcurrentLinkedQueue<AOMMethodCallItem>();
		}
		
		if( pool.size() == 0 )
		{
			AOMProfilingLogger.getErrorStream().println("pool size is 0!!");
			for( int i = 0; i < 20000; i++ )
			{
				pool.add(new AOMMethodCallItem());
			}
			AOMProfilingLogger.getErrorStream().println("20000 elements are added");
			
		}
//		else
//		{
//			if( pool != null )
//			{
//				AOMProfilingLogger.getErrorStream().println("pool size:" + pool.size());
//			}
//		}
		return pool.poll();
//		return new AOMMethodCallItem();
	}
	
	public static void returnInstance(AOMMethodCallItem item)
	{
//		AOMProfilingLogger.getErrorStream().println("item is returned");
		pool.add(item);
	}
	
	public static void returnInstance(Collection<AOMMethodCallItem> item)
	{
//		AOMProfilingLogger.getErrorStream().println("item is returned");
		pool.addAll(item);
	}
	
	
	public void writeAndReturn(DataOutputStream ds) throws IOException
	{
		write(ds);
		returnInstance(this);
	}
	
	public static AOMMethodCallItem getInstance(DataInputStream di) throws IOException
	{
		AOMMethodCallItem item = new AOMMethodCallItem();
		item.read(di);
		return item;
	}
	

	@Override
	public void read(DataInputStream di) throws IOException{
		callerClassName = di.readUTF();
		callerMethodName = di.readUTF();
		callerMethodSignature = di.readUTF();
		callerFileName = di.readUTF();
		callerLineNumber = di.readInt();
		
		calleeDynamicClassName = di.readUTF();
		calleeMethodName = di.readUTF();
		calleeMethodSignature = di.readUTF();
		
		threadId = di.readLong();
		methodCallId = di.readInt();
		prevMethodCallId = di.readInt();
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

