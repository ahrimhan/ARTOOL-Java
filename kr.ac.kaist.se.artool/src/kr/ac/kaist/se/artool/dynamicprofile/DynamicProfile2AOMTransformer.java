package kr.ac.kaist.se.artool.dynamicprofile;

import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelFactory;
import kr.ac.kaist.se.aom.profiler.AOMFieldAccessItem;
import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.util.AOMIndex;
import kr.ac.kaist.se.artool.util.DynamicMethodCallLinker;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

public class DynamicProfile2AOMTransformer {
	private static DynamicProfile2AOMTransformer instance;
	private DynamicProfile2AOMTransformer()
	{
		
	}
	
	public static DynamicProfile2AOMTransformer getInstance()
	{
		if( instance == null )
		{
			instance = new DynamicProfile2AOMTransformer();
		}
		return instance;
	}
	
	
	public void transform(AbstractObjectModel aom, Vector<AOMMethodCallItem> methodCallItems, 
			Vector<AOMFieldAccessItem> fieldAccessItems, IProgressMonitor monitor)
	{
		monitor.beginTask("Transforming Dynamic Profile to AOM", 100);
		try
		{
			SubProgressMonitor subMonitor1 = new SubProgressMonitor(monitor, 20);
			AOMIndex index = AOMIndex.getInstance(aom, subMonitor1);
			SubProgressMonitor subMonitor2 = new SubProgressMonitor(monitor, 40);
			transformMethodCalls(aom, index, methodCallItems, subMonitor2);
			SubProgressMonitor subMonitor3 = new SubProgressMonitor(monitor, 40);
			transformFieldAccesses(aom, index, fieldAccessItems, subMonitor3);

		}
		finally 
		{
			monitor.done();
		}
	}
	
	private boolean access$0(String methodName)
	{
		return false;
	}
	
	private void transformFieldAccesses(AbstractObjectModel aom, AOMIndex index,
			Vector<AOMFieldAccessItem> fieldAccessItems, IProgressMonitor monitor) {
		monitor.beginTask("Transforming Dynamic Profile for Field Accesses to AOM with Indices", fieldAccessItems.size());
//		UbigraphClient ubiGraph = new UbigraphClient();
//		int edgeStyleId = ubiGraph.newEdgeStyle(0);
//		ubiGraph.setEdgeStyleAttribute(edgeStyleId, "arrow", "true");
		for(AOMClass clazz : aom.getClasses() )
		{
			for(AOMMethod method : clazz.getMethods() )
			{
				if( method.getOwnedScope() != null )
				{
					for(StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
					{
						sfa.setDynamicAccessCount(0);
					}
				}
			}
		}
		
		try
		{
			for( AOMFieldAccessItem item : fieldAccessItems )
			{
				
				AOMMethod accessingMethod = index.getMethod(item.accessorClassName, item.accessorMethodName,
						item.accessorMethodSignature.replace('/', '.').replace('$', '.'));
				AOMField referencedField = index.getField(item.referencedClassName,
						item.referencedFieldName);

				if( accessingMethod == null || referencedField == null )
				{
				}
				else
				{
//					DynamicFieldAccess dmc = DynamicmodelFactory.eINSTANCE.createDynamicFieldAccess();
//					accessingMethod.getOwnedScope().getDynamicFieldAccesses().add(dmc);
//					dmc.setAccessedField(referencedField);
			
					StaticFieldAccess smc = null;
					for( StaticFieldAccess t : accessingMethod.getOwnedScope().getStaticFieldAccesses() )
					{
						if( t.getAccessedField().getName().equals(referencedField.getName()) )
						{
							if( (t.getLineNumber()) == item.getAccessorLineNumber() 
									|| (t.getLineNumber() == item.getAccessorLineNumber() + 1))
							{
								smc = t;
							}
							else
							{
								System.err.println("line:" + t.getFileName() + "|" + t.getLineNumber() + " : " + item.accessorLineNumber);
							}
							
						}
					}
					
					if( smc == null )
					{
						System.err.println("(" + (accessingMethod != null ? "true " : "false") + "," + (referencedField != null ? "true " : "false") + ")" + item.accessorClassName + "." + item.accessorMethodName + ":" + item.accessorMethodSignature + "(" + item.accessorLineNumber + ") -> " + item.referencedClassName + "." + item.referencedFieldName);
					}
					else
					{
						smc.setDynamicAccessCount(smc.getDynamicAccessCount() + 1);
					}
				}
				monitor.worked(1);				
			}
		}
		finally
		{
			monitor.done();
		}
	}
	

	private void transformMethodCalls(AbstractObjectModel aom, AOMIndex index,
			Vector<AOMMethodCallItem> methodCallItems, IProgressMonitor monitor) {
		monitor.beginTask("Transforming Dynamic Profile for Method Calls to AOM with Indices", methodCallItems.size());
		DynamicMethodCallLinker linker = new DynamicMethodCallLinker();
//		UbigraphClient ubiGraph = new UbigraphClient();
//		int edgeStyleId = ubiGraph.newEdgeStyle(0);
//		ubiGraph.setEdgeStyleAttribute(edgeStyleId, "arrow", "true");
		try
		{
			for( AOMMethodCallItem item : methodCallItems )
			{
				
				AOMMethod callerMethod = index.getMethod(item.callerClassName, item.callerMethodName,
						item.callerMethodSignature.replace('/', '.').replace('$', '.'));
				AOMMethod calleeMethod = index.getMethod(item.calleeDynamicClassName,
						item.calleeMethodName, item.calleeMethodSignature.replace('/', '.').replace('$', '.'));

				if( callerMethod == null || calleeMethod == null )
				{
					if( !Character.isDigit(item.callerClassName.charAt(item.callerClassName.length() - 1)) && 
							!Character.isDigit(item.calleeDynamicClassName.charAt(item.calleeDynamicClassName.length() - 1)) )
					{
//						System.err.println("(" + (callerMethod != null ? "true " : "false") + "," + (calleeMethod != null ? "true " : "false") + ")" + item.callerClassName + "." + item.callerMethodName + ":" + item.callerMethodSignature + "(" + item.callerLineNumber + ") -> " + item.calleeDynamicClassName + "." + item.calleeMethodName + ":"+ item.calleeMethodSignature);
					}
				}
				else
				{
					DynamicMethodCall dmc = DynamicmodelFactory.eINSTANCE.createDynamicMethodCall();
					callerMethod.getOwnedScope().getDynamicMethodCalls().add(dmc);
					dmc.setCallee(calleeMethod);
			
					StaticMethodCall smc = null;
					for( StaticMethodCall t : callerMethod.getOwnedScope().getStaticMethodCalls() )
					{
						if( t.getCallee().getName().equals(calleeMethod.getName()) ) 
						{
							if( (t.getLineNumber()) == item.callerLineNumber 
									|| (t.getLineNumber() == item.callerLineNumber + 1))
							{
								smc = t;
							}
							else
							{
								System.err.println("line:" + t.getFileName() + "|" + t.getLineNumber() + " : " + item.callerLineNumber);
							}
						}
					}
					
					if( smc == null )
					{
					
						System.err.println("(" + (callerMethod != null ? "true " : "false") + "," + (calleeMethod != null ? "true " : "false") + ")" + item.callerClassName + "." + item.callerMethodName + ":" + item.callerMethodSignature + "(" + item.callerLineNumber + ") -> " + item.calleeDynamicClassName + "." + item.calleeMethodName + ":"+ item.calleeMethodSignature);
					}
					dmc.setStatic(smc);
					dmc.setTid(item.methodCallId);
					linker.add(item.threadId, item.prevMethodCallId, dmc);
					
//					ubiGraph.newVertex(item.methodCallId);
//					ubiGraph.setVertexAttribute(item.methodCallId, "label", item.calleeDynamicClassName + "." + item.calleeMethodName + "(" + item.callerFileName + ":" + item.callerLineNumber + ")");
//					
//					if( dmc.getPreviousCall() != null )
//					{
//						int edgeId = ubiGraph.newEdge(item.prevMethodCallId, item.methodCallId);
//						
//						ubiGraph.changeEdgeStyle(edgeId, edgeStyleId);
//					}
//					
				}
				monitor.worked(1);				
			}
		}
		finally
		{
			monitor.done();
		}
	}
	
}
