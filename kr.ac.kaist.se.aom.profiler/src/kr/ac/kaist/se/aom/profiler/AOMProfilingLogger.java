package kr.ac.kaist.se.aom.profiler;

import java.io.PrintStream;
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

		
		item.callerClassName = callerClassName;
		item.callerMethodName = callerMethodName;
		item.callerMethodSignature = callerMethodSignature;
		item.callerFileName = callerFileName;
		item.callerLineNumber = callerLineNumber;


		item.calleeMethodName = calleeMethodName;
		item.calleeMethodSignature = calleeMethodSignature;

		Stack<AOMMethodCallItem> prevMethodCallStack = prevMethodCall.get();
		prevMethodCallStack.push(item);
	}

	public static void logMethodCallEnd()
	{
		Stack<AOMMethodCallItem> prevMethodCallStack = prevMethodCall.get();		
		if( !prevMethodCallStack.isEmpty() ) prevMethodCallStack.pop();
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
			
			if((item.calleeMethodName.equals(calleeMethodName) || item.calleeMethodName.equals("super") ||
					item.calleeMethodName.equals("this")) && 
					item.calleeMethodSignature.equals(calleeMethodSignature) )
			{
				item.threadId = Thread.currentThread().getId();
				item.methodCallId = System.identityHashCode(item);
				if(!prevStack.isEmpty())
				{
					AOMMethodCallItem prevItem = prevStack.peek();
					item.prevMethodCallId = prevItem.methodCallId;
				}
				else
				{
					item.prevMethodCallId = -1;
				}


				item.calleeDynamicClassName = dynamicCalleeClass;
				item.calleeMethodName = calleeMethodName;
				item.calleeMethodSignature = calleeMethodSignature;

				try {
					workingQueue.put(item);
					prevStack.push(item);
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
