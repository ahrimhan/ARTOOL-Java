package kr.ac.kaist.se.aom.profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
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
	

	public static AOMFieldAccessItem getInstance()
	{
//		if( pool == null )
//		{
//			pool = new ConcurrentLinkedQueue<AOMFieldAccessItem>();
//		}
//		
//		if( pool.size() == 0 )
//		{
//			for( int i = 0; i < 20000; i++ )
//			{
//				pool.add(new AOMFieldAccessItem());
//			}
//			AOMProfilingLogger.getErrorStream().println("20000 elements are added");
//		}
//
//		return pool.poll();
		return new AOMFieldAccessItem();
	}
	
	public static void returnInstance(AOMFieldAccessItem item)
	{
//		pool.add(item);
	}
	
	public static void returnInstance(Collection<AOMFieldAccessItem> item)
	{
//		pool.addAll(item);
	}
	
	public void write(PrintWriter ds) throws IOException
	{
		if( getAccessorClassName().endsWith("y$ErrorHighlight") ) return;
		
		ds.print(getAccessorClassName());
		ds.print(',');
		ds.print(getAccessorMethodName());
		ds.print(',');
		ds.print(getAccessorMethodSignature());
		ds.print(',');
		ds.print(getAccessorFileName());
		ds.print(',');
		ds.print(getAccessorLineNumber());
		ds.print(',');
		
		ds.print(getReferencedClassName());
		ds.print(',');
		ds.print(getReferencedFieldName());
		ds.print(',');
		ds.print(isReadAccess());
		ds.print(',');
		ds.print(isWriteAccess());
		ds.println();
	}
	
	private AOMFieldAccessItem()
	{
		super();
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

		setAccessorClassName(items[0]);
		setAccessorMethodName(items[1]);
		setAccessorMethodSignature(items[2]);
		setAccessorFileName(items[3]);
		setAccessorLineNumber(Integer.parseInt(items[4]));
		
		setReferencedClassName(items[5]);
		setReferencedFieldName(items[6]);
		setReadAccess(Boolean.parseBoolean(items[7]));
		setWriteAccess(Boolean.parseBoolean(items[8]));
		
		return true;
	}
	


	public String getAccessorClassName() {
		return accessorClassName;
	}

	public void setAccessorClassName(String accessorClassName) {
		this.accessorClassName = accessorClassName;
	}

	public String getAccessorMethodName() {
		return accessorMethodName;
	}

	public void setAccessorMethodName(String accessorMethodName) {
		this.accessorMethodName = accessorMethodName;
	}

	public String getAccessorMethodSignature() {
		return accessorMethodSignature;
	}

	public void setAccessorMethodSignature(String accessorMethodSignature) {
		this.accessorMethodSignature = accessorMethodSignature;
	}

	public String getAccessorFileName() {
		return accessorFileName;
	}

	public void setAccessorFileName(String accessorFileName) {
		this.accessorFileName = accessorFileName;
	}

	public int getAccessorLineNumber() {
		return accessorLineNumber;
	}

	public void setAccessorLineNumber(int accessorLineNumber) {
		this.accessorLineNumber = accessorLineNumber;
	}

	public String getReferencedClassName() {
		return referencedClassName;
	}

	public void setReferencedClassName(String referencedClassName) {
		this.referencedClassName = referencedClassName;
	}

	public String getReferencedFieldName() {
		return referencedFieldName;
	}

	public void setReferencedFieldName(String referencedFieldName) {
		this.referencedFieldName = referencedFieldName;
	}

	public boolean isReadAccess() {
		return isReadAccess;
	}

	public void setReadAccess(boolean isReadAccess) {
		this.isReadAccess = isReadAccess;
	}

	public boolean isWriteAccess() {
		return isWriteAccess;
	}

	public void setWriteAccess(boolean isWriteAccess) {
		this.isWriteAccess = isWriteAccess;
	}



}

