package kr.ac.kaist.se.artool.util;

import java.util.HashMap;
import java.util.Stack;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;

public class DynamicMethodCallLinker
{
	private HashMap<Long, ThreadCallLinker> subLinkers;
	
	public DynamicMethodCallLinker()
	{
		subLinkers = new HashMap<Long, ThreadCallLinker>();
	}
	
	public void add(long threadId, int previousCallId, DynamicMethodCall call)
	{
		ThreadCallLinker subLinker = null;
		if( !subLinkers.containsKey(threadId) )
		{
			subLinker = new ThreadCallLinker();
			subLinkers.put(threadId, subLinker);
		}
		else
		{
			subLinker = subLinkers.get(threadId);
		}
		subLinker.add(previousCallId, call);
	}
	
	
	
	private class ThreadCallLinker 
	{
		private Stack<DynamicMethodCall> stack;
		private HashMap<Integer, DynamicMethodCall> idMap;
		
		public ThreadCallLinker()
		{
			stack = new Stack<DynamicMethodCall>();
			idMap = new HashMap<Integer, DynamicMethodCall>(3000);
		}
		
		
		private DynamicMethodCall getPreviousCall(int previousCallId)
		{
			if( previousCallId == -1 ) return null;
			
			DynamicMethodCall previousCall = idMap.get(previousCallId);
			
			if( previousCall == null ) return null;
			
			while( !stack.isEmpty() && stack.peek() != previousCall )
			{
				idMap.remove(stack.pop().getTid());
			}
			
			if( stack.isEmpty() )
			{
				return null;
			}
			
			return previousCall;
//			return idMap.get(previousCallId);
		}
		
		private void putCurrentCall(DynamicMethodCall call)
		{
			stack.push(call);
			idMap.put(call.getTid(), call);
//			idMap.put(call.getId(), call);
		}
		
		public void add(int previousCallId, DynamicMethodCall call)
		{
			DynamicMethodCall previousCall = getPreviousCall(previousCallId);
			call.setPreviousCall(previousCall);	
			putCurrentCall(call);		
		}
	}

	

}