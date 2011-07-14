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
	
	public static Queue<AOMMethodCallItem> pool;
	
	public static AOMMethodCallItem getInstance()
	{
//		if( pool == null )
//		{
//			pool = new ConcurrentLinkedQueue<AOMMethodCallItem>();
//		}
//		
//		if( pool.size() == 0 )
//		{
//			for( int i = 0; i < 20000; i++ )
//			{
//				pool.add(new AOMMethodCallItem());
//			}
//			AOMProfilingLogger.getErrorStream().println("20000 elements are added");
//		}
//
//		return pool.poll();
		return new AOMMethodCallItem();
	}
	
	public static void returnInstance(AOMMethodCallItem item)
	{
//		pool.add(item);
	}
	
	public static void returnInstance(Collection<AOMMethodCallItem> item)
	{
//		pool.addAll(item);
	}
	
	public static final AOMMethodCallItem nullItem = new AOMMethodCallItem();
	
	static{
		nullItem.setMethodCallId(-1);
	}
	
	public void write(PrintWriter ds) throws IOException
	{
		if( getCallerClassName().endsWith("y$ErrorHighlight") ) return;
		
		ds.print(getCallerClassName());
		ds.print(',');
		ds.print(getCallerMethodName());
		ds.print(',');
		ds.print(getCallerMethodSignature());
		ds.print(',');
		ds.print(getCallerFileName());
		ds.print(',');
		ds.print(getCallerLineNumber());
		ds.print(',');
		
		ds.print(getCalleeDynamicClassName());
		ds.print(',');
		ds.print(getCalleeMethodName());
		ds.print(',');
		ds.print(getCalleeMethodSignature());
		ds.print(',');
		
		ds.print(getThreadId());
		ds.print(',');
		ds.print(getMethodCallId());
		ds.print(',');
		ds.print(getPrevMethodCallId());
		ds.println();
	}
	
	private AOMMethodCallItem()
	{
		super();
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
		
		setCallerClassName(items[0]);
		setCallerMethodName(items[1]);
		setCallerMethodSignature(items[2]);
		setCallerFileName(items[3]);
		setCallerLineNumber(Integer.parseInt(items[4]));
		
		setCalleeDynamicClassName(items[5]);
		setCalleeMethodName(items[6]);
		setCalleeMethodSignature(items[7]);
		
		setThreadId(Long.parseLong(items[8]));
		setMethodCallId(Integer.parseInt(items[9]));
		setPrevMethodCallId(Integer.parseInt(items[10]));
		return true;
	}

	public String getCallerClassName() {
		return callerClassName;
	}

	public void setCallerClassName(String callerClassName) {
		this.callerClassName = callerClassName;
	}

	public String getCallerMethodName() {
		return callerMethodName;
	}

	public void setCallerMethodName(String callerMethodName) {
		this.callerMethodName = callerMethodName;
	}

	public String getCallerMethodSignature() {
		return callerMethodSignature;
	}

	public void setCallerMethodSignature(String callerMethodSignature) {
		this.callerMethodSignature = callerMethodSignature;
	}

	public String getCallerFileName() {
		return callerFileName;
	}

	public void setCallerFileName(String callerFileName) {
		this.callerFileName = callerFileName;
	}

	public int getCallerLineNumber() {
		return callerLineNumber;
	}

	public void setCallerLineNumber(int callerLineNumber) {
		this.callerLineNumber = callerLineNumber;
	}

	public String getCalleeDynamicClassName() {
		return calleeDynamicClassName;
	}

	public void setCalleeDynamicClassName(String calleeDynamicClassName) {
		this.calleeDynamicClassName = calleeDynamicClassName;
	}

	public String getCalleeMethodName() {
		return calleeMethodName;
	}

	public void setCalleeMethodName(String calleeMethodName) {
		this.calleeMethodName = calleeMethodName;
	}

	public String getCalleeMethodSignature() {
		return calleeMethodSignature;
	}

	public void setCalleeMethodSignature(String calleeMethodSignature) {
		this.calleeMethodSignature = calleeMethodSignature;
	}

	public long getThreadId() {
		return threadId;
	}

	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	public int getMethodCallId() {
		return methodCallId;
	}

	public void setMethodCallId(int methodCallId) {
		this.methodCallId = methodCallId;
	}

	public int getPrevMethodCallId() {
		return prevMethodCallId;
	}

	public void setPrevMethodCallId(int prevMethodCallId) {
		this.prevMethodCallId = prevMethodCallId;
	}

	public boolean isSynthetic() {
		return isSynthetic;
	}

	public void setSynthetic(boolean isSynthetic) {
		this.isSynthetic = isSynthetic;
	}


}

