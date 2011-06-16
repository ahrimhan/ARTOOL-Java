package kr.ac.kaist.se.aom.profiler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
		return new AOMMethodCallItem();
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

