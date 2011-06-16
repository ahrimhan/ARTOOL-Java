package kr.ac.kaist.se.artool.dynamicprofile;

import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelFactory;
import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
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
	
	
	public void transform(AbstractObjectModel aom, Vector<AOMMethodCallItem> methodCallItems, IProgressMonitor monitor)
	{
		monitor.beginTask("Transforming Dynamic Profile to AOM", 100);
		try
		{
			SubProgressMonitor subMonitor1 = new SubProgressMonitor(monitor, 30);
			AOMIndex index = AOMIndex.getInstance(aom, subMonitor1);
			SubProgressMonitor subMonitor2 = new SubProgressMonitor(monitor, 70);
			transform(aom, index, methodCallItems, subMonitor2);
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

	private void transform(AbstractObjectModel aom, AOMIndex index,
			Vector<AOMMethodCallItem> methodCallItems, IProgressMonitor monitor) {
		monitor.beginTask("Transforming Dynamic Profile to AOM with Indices", methodCallItems.size());
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
						if( t.getCallee().getName().equals(calleeMethod.getName()) && t.getLineNumber() == item.callerLineNumber )
						{
							smc = t;
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
