package kr.ac.kaist.se.artool.dynamicprofile;

import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;

public class DynamicProfileMethodData {
	public long count;
	
	public String callerMethodName;
	public String callerClassName;
	public String callerFileName;
	public int callerLineNumber;

	public String calleeMethodName;
	public String calleeMethodSignature;
	public String calleeDynamicClassName;
	
	private DynamicProfileDataSet rootDataSet;
	
	public DynamicProfileMethodData(AOMMethodCallItem item, DynamicProfileDataSet rootDataSet)
	{
		count = 0;
		callerClassName = item.callerClassName;
		callerMethodName = item.callerMethodName;
		callerFileName = item.callerFileName;
		callerLineNumber = item.callerLineNumber;
		
		calleeDynamicClassName = item.calleeDynamicClassName;
		calleeMethodName = item.calleeMethodName;
		calleeMethodSignature = item.calleeMethodSignature;
		
		
		this.rootDataSet = rootDataSet;
	}
	
	public void increase()
	{
	
		count++;
	}
}
