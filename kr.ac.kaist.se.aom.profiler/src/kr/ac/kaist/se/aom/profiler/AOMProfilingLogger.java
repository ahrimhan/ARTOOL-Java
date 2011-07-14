package kr.ac.kaist.se.aom.profiler;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Stack;

public class AOMProfilingLogger {

	private static PrintStream ps;
	private static AOMProfileItemDispatcher workingQueue;

	private static ThreadLocal<Stack<AOMMethodCallItem>> aomPreviousMethodCallStack;

	private static ThreadLocal<Stack<AOMMethodCallItem>> prevMethodCall;
	static
	{
		aomPreviousMethodCallStack = new ThreadLocal<Stack<AOMMethodCallItem>>() {
			@Override
			protected Stack<AOMMethodCallItem> initialValue() {
				return new Stack<AOMMethodCallItem>();
			}
		};

		prevMethodCall = new ThreadLocal<Stack<AOMMethodCallItem>>() {
			@Override
			protected Stack<AOMMethodCallItem> initialValue() {
				return new Stack<AOMMethodCallItem>();
			}
		};
	}


	public static void logMethodCallBegin(String callerClassName, 
			String callerMethodName, 
			String callerMethodSignature, 
			String callerFileName,
			int callerLineNumber,
			String calleeMethodName,
			String calleeMethodSignature)
	{
		AOMMethodCallItem item = AOMMethodCallItem.getInstance();

		
		item.setCallerClassName(callerClassName);
		item.setCallerMethodName(callerMethodName);
		item.setCallerMethodSignature(callerMethodSignature);
		item.setCallerFileName(callerFileName);
		item.setCallerLineNumber(callerLineNumber);


		item.setCalleeMethodName(calleeMethodName);
		item.setCalleeMethodSignature(calleeMethodSignature);

		Stack<AOMMethodCallItem> prevMethodCallStack = prevMethodCall.get();
		prevMethodCallStack.push(item);
	}

	public static void logMethodCallEnd()
	{
		Stack<AOMMethodCallItem> prevMethodCallStack = prevMethodCall.get();		
		if( !prevMethodCallStack.isEmpty() )
		{
			AOMMethodCallItem item = prevMethodCallStack.pop();
			if( !item.isOccupied() )
			{
				AOMMethodCallItem.returnInstance(item);
			}
		}
	}
	
	public static void logFieldAccess(
			String accessorClassName,
			String accessorMethodName,
			String accessorMethodSignature,
			String accessorFileName,
			int accessorLineNumber,
			String referencedClassName, 
			String referencedFieldName,
			boolean isReadAccess,
			boolean isWriteAccess)
	{
		AOMFieldAccessItem item = AOMFieldAccessItem.getInstance();
		item.setAccessorClassName(accessorClassName);
		item.setAccessorMethodName(accessorMethodName);
		item.setAccessorMethodSignature(accessorMethodSignature);
		item.setAccessorFileName(accessorFileName);
		item.setAccessorLineNumber(accessorLineNumber);
		item.setReferencedClassName(referencedClassName);
		item.setReferencedFieldName(referencedFieldName);
		item.setReadAccess(isReadAccess);
		item.setWriteAccess(isWriteAccess);
		
		ByteBuffer buffer = null;
		
		try {
			buffer = workingQueue.getFreeBuffer();
			if( !item.write(buffer) )
			{
				workingQueue.returnFreeBuffer(buffer);
				buffer = null;
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		AOMFieldAccessItem.returnInstance(item);
		
		try {
			if( buffer != null ) workingQueue.giveWork(buffer, false);
			return;
		} catch (InterruptedException e) {

		}
	}
	

	public static void logMethodEnter(String dynamicCalleeClass, String calleeMethodName, String calleeMethodSignature, boolean isSynthetic)
	{
		Stack<AOMMethodCallItem> prevStack = aomPreviousMethodCallStack.get();	
		Stack<AOMMethodCallItem> prevMethodCallStack  = prevMethodCall.get();

		AOMMethodCallItem item = null;
		if( !prevMethodCallStack.isEmpty() )
		{
			item = prevMethodCallStack.peek();
		}

		if( item != null )
		{
			
			if((item.getCalleeMethodName().equals(calleeMethodName) || item.getCalleeMethodName().equals("super") ||
					item.getCalleeMethodName().equals("this")) && 
					item.getCalleeMethodSignature().equals(calleeMethodSignature) )
			{
				item.setThreadId(Thread.currentThread().getId());
				item.setMethodCallId(System.identityHashCode(item));
				if(!prevStack.isEmpty())
				{
					AOMMethodCallItem prevItem = prevStack.peek();
					item.setPrevMethodCallId(prevItem.getMethodCallId());
				}
				else
				{
					item.setPrevMethodCallId(-1);
				}


				item.setCalleeDynamicClassName(dynamicCalleeClass);
				item.setCalleeMethodName(calleeMethodName);
				item.setCalleeMethodSignature(calleeMethodSignature);
				
				ByteBuffer buffer = null;
				
				try {
					buffer = workingQueue.getFreeBuffer();
					if( !item.write(buffer) )
					{
						workingQueue.returnFreeBuffer(buffer);
						buffer = null;
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
				try {
//					item.setOccupied(true);
					if( buffer != null ) workingQueue.giveWork(buffer, true);
					return;
				} catch (InterruptedException e) {

				}
			}
		}
		prevStack.push(AOMMethodCallItem.nullItem);
		return;
	}

	public static void logMethodExit(boolean isSynthetic)
	{
		Stack<AOMMethodCallItem> prevStack = aomPreviousMethodCallStack.get();		
		if( !prevStack.isEmpty() ) prevStack.pop();
	}

	public static PrintStream getErrorStream()
	{
		return ps;
	}


	static
	{
		ps = System.err;
		workingQueue = AOMProfileItemDispatcher.getInstance();
	}

}
