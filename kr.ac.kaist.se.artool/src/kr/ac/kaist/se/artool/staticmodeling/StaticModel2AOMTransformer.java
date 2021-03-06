package kr.ac.kaist.se.artool.staticmodeling;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTRequestor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.AomFactory;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMLocalVariable;
import kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMParameter;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.AOMType;

public class StaticModel2AOMTransformer {
	

	
	private StaticModel2AOMTransformer()
	{	

	}
	
	private static StaticModel2AOMTransformer instance = null;
	
	public static StaticModel2AOMTransformer getInstance()
	{
		if( instance == null )
		{
			instance = new StaticModel2AOMTransformer();
		}
		return instance;
	}
	
	public AbstractObjectModel transform2AOM(List<IJavaProject> projectList, IProgressMonitor monitor) throws JavaModelException, CoreException
	{
		AbstractObjectModel aom = AomFactory.eINSTANCE.createAbstractObjectModel();
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_5, options);
		parser.setCompilerOptions(options);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		parser.setIgnoreMethodBodies(false);
		
		
		AOMTransformingVisitor visitor = new AOMTransformingVisitor(aom);
		
		try
		{
			monitor.beginTask("Transforming Java Source to AOM", 100);
			SubProgressMonitor subMonitor1 = new SubProgressMonitor(monitor, 75);
			extractClasses(projectList.get(0), parser, visitor, subMonitor1);
		
			ExternalTypeGenerator etg = new ExternalTypeGenerator(aom);

			SubProgressMonitor subMonitor2 = new SubProgressMonitor(monitor, 20);
			restoringReferences(aom, visitor, etg, subMonitor2);
			
			SubProgressMonitor subMonitor3 = new SubProgressMonitor(monitor, 5);
			findingOverrides(aom, subMonitor3);	
			
			
			figureOutGetterAndSetter(aom, etg);
			
			figureOutDelegateMethod(aom);
			
		}
		finally
		{
			monitor.done();
		}
		return aom;
	}
	
	private void figureOutDelegateMethod(AbstractObjectModel aom)
	{
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				method.setDelegate(null);
			}
		}
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				if( method.getOwnedScope() != null && method.getOwnedScope().getStaticMethodCalls().size() == 1 &&
						method.getOwnedScope().getStaticFieldAccesses().size() < 3 )
				{
					boolean isDelegate = false;

					AOMMethod calledMethod = method.getOwnedScope().getStaticMethodCalls().get(0).getCallee();
					
					if( calledMethod.getParameters().size() == method.getParameters().size() )
					{
						boolean isDelegate2 = true;
						for( int i = 0; i < method.getParameters().size(); i++ )
						{
							if( calledMethod.getParameters().get(i).getType().getFqdn().equals(
									method.getParameters().get(i).getType().getFqdn()) &&
								calledMethod.getType().getFqdn().equals(method.getType().getFqdn()))
							{
								continue;
							}
							else
							{
								isDelegate2 = false;
								break;
							}
						}
						
						isDelegate = isDelegate2;
					}
					method.setDelegate(calledMethod);
				}
			}
		}
	}
	
	private void figureOutGetterAndSetter(AbstractObjectModel aom, ExternalTypeGenerator etg)
	{
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				if( (method.getName().startsWith("get") || method.getName().startsWith("is")) &&
					((method.getEndLine() - method.getStartLine()) < 5) &&
					method.getParameters().size() == 0 
				)
				{
					for( AOMField field : clazz.getFields() )
					{
						if( method.getName().endsWith(field.getName()) && 
								method.getType() != null && field.getType() != null &&
								method.getType().getFqdn().equals(field.getType().getFqdn()) 
								)
						{
							method.setGetter(field);
						}
					}
				}
				else if( (method.getName().startsWith("set") ) &&
						((method.getEndLine() - method.getStartLine()) < 5) &&
						method.getParameters().size() == 1 &&
						method.getType() == etg.getVoid() 
				)
				{
					AOMParameter param = method.getParameters().get(0);
					
					for( AOMField field : clazz.getFields() )
					{
						if( method.getName().endsWith(field.getName()) && 
								param.getType() != null && field.getType() != null &&
								param.getType().getFqdn().equals(field.getType().getFqdn()) 
								)
						{
							method.setSetter(field);
						}
					}
				}
			}
		}
	}
	
	private void findingOverrides(AbstractObjectModel aom,
			SubProgressMonitor monitor) {
		Vector<AOMClass> rootClasses = new Vector<AOMClass>();
		for( AOMClass clazz : aom.getClasses() )
		{
			if( clazz.getAncestor().size() == 0 ) 
			{
				rootClasses.add(clazz);
			}
		}
		monitor.beginTask("Finding overriding relations", rootClasses.size());
		try
		{
			for( AOMClass rootClass : rootClasses )
			{
				HierarchyTraverser traverser = new HierarchyTraverser();
				traverser.traverse(rootClass, new OverridingHelper());
				monitor.worked(1);
			}
		}
		finally
		{
			monitor.done();
		}
	}
	


	private void restoringReferences(AbstractObjectModel aom, AOMTransformingVisitor visitor, ExternalTypeGenerator etg, IProgressMonitor monitor)
	{
		
		monitor.beginTask("Restoring references", aom.getClasses().size());
		try
		{
			for( AOMClass aomClass : aom.getClasses() )
			{
				aomClass.setLOCfromTempLOC();
				for( ITypeBinding superTypeBinding : aomClass.getAncestorTypeBindings() )
				{
					AOMType superType = visitor.getInferredType(superTypeBinding, null);
					if( superType != null && superType instanceof AOMClass )
					{
						aomClass.getAncestor().add((AOMClass)superType);
					}
				}

				
				//restoring field type references
				for( AOMField field : aomClass.getFields() )
				{
					AOMType referedType = visitor.getInferredType(field.getTypeBinding(), etg);
					field.setType(referedType);
					
				}
				
				
				visitor.buildVariableBinding();
				
				// restoring method related references
				for( AOMMethod method : aomClass.getMethods() )
				{
					// restoring return type references
					AOMType returnType = visitor.getInferredType(method.getTypeBinding(), etg);
					if( method.getTypeBinding() == null  )
					{
						returnType = etg.getVoid();
					}
					method.setType(returnType);
					//restoring parameter type references
					for( AOMParameter parameter: method.getParameters() )
					{
						AOMType parameterType = visitor.getInferredType(parameter.getTypeBinding(), etg);
						parameter.setType(parameterType);
					}
					
					
//					String[] parameterTypes = new String[method.getParameters().size()]; 
//					
//					for(int i = 0; i < method.getParameters().size(); i++ ) 
//					{
//						
//						parameterTypes[i] = Signature.createTypeSignature(
//								method.getParameters().get(i).getType().getFqdn(), true);
//					}
//					String returnTypeString = "";
//					returnTypeString = Signature.createTypeSignature(returnType.getFqdn(), true);
//					
//					String sig = Signature.createMethodSignature(parameterTypes, returnTypeString);
//					method.setSignature(Signature.getTypeErasure(sig));
					
					method.setMethodId(aomClass.getFqdn() + "." + method.getName() + ":" + method.getSignature());
					
					//restoring body references
					AOMScope scope = method.getOwnedScope();
					
										
					
					if( scope != null )
					{
//						for( IVariableBinding varBinding : scope.getVariableBindings() )
//						{
//							if( varBinding.isField() )
//							{
////								AOMField aomField = visitor.getAOMField(varBinding);
////								if( aomField != null )
////								{
////									
////									scope.getReferringFields().add(aomField);
////								}
//							}
//							else if( varBinding.isParameter() )
//							{
//								AOMParameter aomParameter = method.getVarBinding2AOMParameter().get(varBinding);
//								// TODO: local references are not handled now.
//							}
//							else 
//							{
//								// TODO: local references are not handled now.
//							}
//						}
						
						for( AOMLocalVariableAccess lva : scope.getLocalVariableAccesses() )
						{
							if( lva.isParameterAccess() )
							{
								AOMParameter aomParameter = method.getVarBinding2AOMParameter().get(lva.getVariableBinding());
								
								lva.setAccessedVariableDef(aomParameter);
							}
							else
							{
								AOMLocalVariable localVariable = scope.getVarBinding2AOMLocalVariable().get(lva.getVariableBinding());
								lva.setAccessedVariableDef(localVariable);
							}
						}
					
						
						Vector<StaticFieldAccess> removeStaticFieldAccess = new Vector<StaticFieldAccess>();
						for( StaticFieldAccess fieldAccess : scope.getStaticFieldAccesses() )
						{
							AOMField aomField = visitor.getAOMField(fieldAccess.getVariableBinding());
							AOMClass aomCallingClass = visitor.getAOMClass(fieldAccess.getTypeBinding());
							
							if( aomField == null ) 
							{
								removeStaticFieldAccess.add(fieldAccess);
							}
							else
							{
								fieldAccess.setAccessedField(aomField);
								if( aomCallingClass == null )
								{
									aomCallingClass = aomClass;
								}
								fieldAccess.setAccessingType(aomCallingClass);
							}
						}
						
						for( StaticFieldAccess smc : removeStaticFieldAccess )
						{
							smc.setAccessingScope(null);
						}
						
						Vector<StaticMethodCall> removeMethodCall = new Vector<StaticMethodCall>();
						for( StaticMethodCall methodCall : scope.getStaticMethodCalls() )
						{
							AOMMethod aomMethod = visitor.getAOMMethod(methodCall.getMethodBinding());
							AOMClass aomCallingClass = visitor.getAOMClass(methodCall.getTypeBinding());
							
							if( aomMethod == null ) 
							{
								removeMethodCall.add(methodCall);
							}
							else if( aomCallingClass == null )
							{
								removeMethodCall.add(methodCall);
							}
							else
							{
								methodCall.setCallee(aomMethod);
								methodCall.setCallingType(aomCallingClass);
							}
						}
						
						for( StaticMethodCall smc : removeMethodCall )
						{
							smc.setCaller(null);
						}
						
						
						for( AOMLocalVariable var : scope.getVariables() )
						{
							AOMType localType = visitor.getInferredType(var.getTypeBinding(), etg);
							var.setType(localType);
						}
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
	
	
	//    ArrayType, ParameterizedType, PrimitiveType, QualifiedType, SimpleType, WildcardType
	private void extractClasses(IJavaProject javaProject, ASTParser parser, AOMTransformingVisitor visitor, IProgressMonitor monitor) throws JavaModelException, CoreException
	{
		// Check if we have a Java project
			
			parser.setProject(javaProject);
			IPackageFragment[] packages = javaProject.getPackageFragments();
			
			List<ICompilationUnit> cus = new Vector<ICompilationUnit>();
			for(IPackageFragment p : packages )
			{
				if( p.getKind() == IPackageFragmentRoot.K_SOURCE )
				{
					IPackageFragment mypackage = (IPackageFragment) p;
					for(ICompilationUnit icu : mypackage.getCompilationUnits() )
					{
						cus.add(icu);
					}
				}
			}
			
			ICompilationUnit[] icuArray = new ICompilationUnit[cus.size()];
			icuArray = cus.toArray(icuArray);
			
			
			monitor.beginTask("ExtractClassTask1", 100);
			try
			{
				SubProgressMonitor subMonitor1 = new SubProgressMonitor(monitor, 90);
				MyASTRequestor astRequestor = new MyASTRequestor();
				parser.createASTs(icuArray, new String[0],  astRequestor, subMonitor1);	
				SubProgressMonitor subMonitor2 = new SubProgressMonitor(monitor, 10);
				subMonitor2.beginTask("Visiting Compilation Units", astRequestor.getCompilationUnits().size());
				try
				{
					for (CompilationUnit result : astRequestor.getCompilationUnits()) {
						result.accept(visitor);
						subMonitor2.worked(1);
					}
				}
				finally
				{
					subMonitor2.done();
				}
			}
			finally
			{
				monitor.done();
			}
		
	} 
	
	public class  MyASTRequestor extends ASTRequestor  {

		private Vector<CompilationUnit> cus;
		public MyASTRequestor()
		{
			cus = new Vector<CompilationUnit>();
		}
		
		public Vector<CompilationUnit> getCompilationUnits()
		{
			return cus;
		}
		@Override
		public void acceptAST(ICompilationUnit source, CompilationUnit ast) {
			cus.add(ast);
		}

		@Override
		public void acceptBinding(String bindingKey, IBinding binding) {
			super.acceptBinding(bindingKey, binding);
		}
	}
}
