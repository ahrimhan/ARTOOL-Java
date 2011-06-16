package kr.ac.kaist.se.artool.util;

import java.util.HashMap;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.core.runtime.IProgressMonitor;

public class AOMIndex {
	private AbstractObjectModel aom;
	private HashMap<String, ClassIndexItem> classIndex;
	private HashMap<AOMMethod, MethodIndexItem> bMethodIndex;

	
	private class ClassIndexItem
	{
		private AOMClass aomClass;
		private HashMap<String, MethodIndexItem> methodIndex;
		
		public ClassIndexItem(AOMClass aomClass)
		{
			this.aomClass = aomClass;
			methodIndex = new HashMap<String, MethodIndexItem>();
		}
		
		public AOMClass getAOMClass()
		{
			return aomClass;
		}
		
		public void putMethod(String methodName, String signature, MethodIndexItem methodItem)
		{
			methodIndex.put(methodName + ":" + signature, methodItem);
			bMethodIndex.put(methodItem.getAOMMethod(), methodItem);
		}
		
		public MethodIndexItem getMethod(String methodName, String signature)
		{
			return methodIndex.get(methodName + ":" + signature);
		}	
	}
	
	private class MethodIndexItem
	{
		private AOMMethod aomMethod;
		private HashMap<AOMMethod, HashMap<Integer, StaticMethodCall>> staticMethodCallIndex;
		
		public MethodIndexItem(AOMMethod aomMethod)
		{
			this.aomMethod = aomMethod;
			staticMethodCallIndex = new HashMap<AOMMethod, HashMap<Integer, StaticMethodCall>>();
		}
		
		public AOMMethod getAOMMethod()
		{
			return aomMethod;
		}
		
		public void putStaticMethodCall(AOMMethod calleeMethod, Integer lineNumber, StaticMethodCall methodCall)
		{
			HashMap<Integer, StaticMethodCall> subIndex = null;
			if( staticMethodCallIndex.containsKey(calleeMethod) )
			{
				subIndex = staticMethodCallIndex.get(calleeMethod);
			}
			else
			{
				subIndex = new HashMap<Integer, StaticMethodCall>();
				staticMethodCallIndex.put(calleeMethod, subIndex);
			}
			subIndex.put(lineNumber, methodCall);
		}
		
		public StaticMethodCall getStaticMethodCall(AOMMethod calleeMethod, Integer lineNumber)
		{
			HashMap<Integer, StaticMethodCall> subIndex = staticMethodCallIndex.get(calleeMethod);
			if( subIndex != null )
			{
				return subIndex.get(lineNumber);
			}
			return null;
		}
	}
	
	// boot-up codes
	private AOMIndex(AbstractObjectModel aom)
	{
		this.aom = aom;
		classIndex = new HashMap<String, ClassIndexItem>(aom.getClasses().size() / 2);
		bMethodIndex = new HashMap<AOMMethod, MethodIndexItem>(aom.getClasses().size() * 4);
	}
	
	public static AOMIndex getInstance(AbstractObjectModel aom, IProgressMonitor monitor)
	{
		AOMIndex ret = new AOMIndex(aom);
		ret.createIndices(monitor);
		return ret;
	}

	private void createIndices(IProgressMonitor monitor)
	{
		monitor.beginTask("Create AOM Indices", aom.getClasses().size());
		try
		{
			for(AOMClass clazz : aom.getClasses())
			{
				ClassIndexItem item = new ClassIndexItem(clazz);
				classIndex.put(clazz.getFqdn(), item);
				createMethodIndex(item);
				monitor.worked(1);
			}
		}
		finally
		{
			monitor.done();
		}
	}
	
	private void createMethodIndex(ClassIndexItem classItem)
	{
		for(AOMMethod method: classItem.getAOMClass().getMethods() )
		{
			MethodIndexItem item = new MethodIndexItem(method);
			classItem.putMethod(method.getName(), method.getSignature(), item);
			if( method.getOwnedScope() != null )
			{
				createStaticMethodCallIndex(item);
			}
		}
	}
	
	private void createStaticMethodCallIndex(MethodIndexItem item)
	{
		for(StaticMethodCall smc : item.getAOMMethod().getOwnedScope().getStaticMethodCalls() )
		{
			item.putStaticMethodCall(smc.getCallee(), smc.getLineNumber(), smc);
		}
	}
	
	public AOMClass getClass(String fqdn)
	{
		ClassIndexItem classItem = classIndex.get(fqdn);
		if( classItem == null ) return null;
		return classItem.getAOMClass();
	}
	
	public AOMMethod getMethod(String classFQDN, String methodName, String signature)
	{
		ClassIndexItem classItem = classIndex.get(classFQDN);
		if( classItem == null ) return null;
		MethodIndexItem methodItem = classItem.getMethod(methodName, signature);
		if( methodItem == null ) return null;
		return methodItem.getAOMMethod();
	}
	
	public StaticMethodCall getStaticMethodCall(String callerClassFQDN, String callerMethodName, String callerSignature,
			String calleeClassFQDN, String calleeMethodName, String calleeSignature, int lineNumber)
	{
		AOMMethod calleeMethod = getMethod(calleeClassFQDN, calleeMethodName, calleeSignature);
		if( calleeMethod == null ) return null;
		
		ClassIndexItem classItem = classIndex.get(callerClassFQDN);
		if( classItem == null ) return null;
		MethodIndexItem methodItem = classItem.getMethod(callerMethodName, callerSignature);
		if( methodItem == null ) return null;
		
		return methodItem.getStaticMethodCall(calleeMethod, lineNumber);
	}
	
	public StaticMethodCall getStaticMethodCall(AOMMethod caller, AOMMethod callee, int lineNumber)
	{
		MethodIndexItem methodItem = bMethodIndex.get(caller);
		if( methodItem == null ) System.err.println("caller not found");
		StaticMethodCall smc = methodItem.getStaticMethodCall(callee, lineNumber);
		if( smc == null ) 
		{
			System.err.println("callee not found");
		}
		return smc;
	}
}
