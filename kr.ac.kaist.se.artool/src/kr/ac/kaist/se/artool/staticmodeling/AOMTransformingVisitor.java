package kr.ac.kaist.se.artool.staticmodeling;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelFactory;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMLocalVariable;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMModifier;
import kr.ac.kaist.se.aom.structure.AOMParameter;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.AOMType;
import kr.ac.kaist.se.aom.structure.StructureFactory;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class AOMTransformingVisitor extends ASTVisitor {

	private AbstractObjectModel aom;
	private HashMap<ITypeBinding, AOMClass> typeBinding2AOMClass = null;
	private HashMap<IMethodBinding, AOMMethod> methodBinding2AOMMethod = null;
	private HashMap<IVariableBinding, AOMField> varBinding2AOMField = null;
	
	public AOMTransformingVisitor(AbstractObjectModel aom)
	{
		this.aom = aom;
		typeBinding2AOMClass = new HashMap<ITypeBinding, AOMClass>();
		methodBinding2AOMMethod = new HashMap<IMethodBinding, AOMMethod>();
		varBinding2AOMField = new HashMap<IVariableBinding, AOMField>();
	}
	
	public AOMType getInferredType(ITypeBinding binding, ExternalTypeGenerator ext)
	{
		AOMType ret = null;
		if( binding != null )
		{
			ITypeBinding erasureBinding = binding.getErasure();
			if( binding.isArray() )
			{
				ret = getInferredType(erasureBinding.getElementType(), ext);
			}
			else if( (binding.isClass() || binding.isInterface()) && !binding.isAnonymous() )
			{
				ret = typeBinding2AOMClass.get(erasureBinding);
			}
			
			if( ret == null && ext != null )
			{
				ret = ext.get(erasureBinding);
			}
		}
	
		return ret;
	}
	
	public AOMClass getAOMClass(ITypeBinding binding)
	{
		return typeBinding2AOMClass.get(binding);
	}
	
	public AOMMethod getAOMMethod(IMethodBinding binding)
	{
		return methodBinding2AOMMethod.get(binding);
	}
	
	public Set<IMethodBinding> getMethodBindings()
	{
		return methodBinding2AOMMethod.keySet();
	}
	
	public AOMField getAOMField(IVariableBinding binding)
	{
		return varBinding2AOMField.get(binding);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		TypeDeclaration parentType = getEnclosingTypeDeclaration(node);
		if(parentType == null) return false;
		ITypeBinding binding = parentType.resolveBinding().getErasure();
		AOMClass aomClass = typeBinding2AOMClass.get(binding);

		List<VariableDeclarationFragment> vdfs = (List<VariableDeclarationFragment>) node.fragments();
	
		for( VariableDeclarationFragment vdf : vdfs )
		{
			AOMField aomField = StructureFactory.eINSTANCE.createAOMField();
			aomField.setName(vdf.getName().getIdentifier());
			
			aomField.setTypeBinding(node.getType().resolveBinding());
			aomClass.getFields().add(aomField);
			
			varBinding2AOMField.put(vdf.resolveBinding(), aomField);
		}
		
		return super.visit(node);
	}
	
	private static class MethodVisitor extends ASTVisitor
	{
		private AOMScope scope;
		private AbstractObjectModel aom;

		
		private MethodVisitor()
		{

		}
		
		private static MethodVisitor instance;
		public static MethodVisitor getInstance(AbstractObjectModel aom, AOMScope scope)
		{
			if( instance == null )
			{
				instance = new MethodVisitor();
			}
			instance.scope = scope;
			instance.aom = aom;
			
			return instance;
		}
		
		public boolean visit(MethodInvocation node)
		{
			ITypeBinding typeBinding = null;
			if( node.getExpression() != null )
			{
				typeBinding = node.getExpression().resolveTypeBinding().getErasure();
			}
			else
			{
				TypeDeclaration td = getEnclosingTypeDeclaration(node);
				typeBinding = td.resolveBinding().getErasure();	
			}

			
			StaticMethodCall methodCall = null;
			IMethodBinding methodBinding = node.resolveMethodBinding().getMethodDeclaration();

			CompilationUnit cu = (CompilationUnit)node.getRoot();
			int lineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength());

			methodCall = StaticmodelFactory.eINSTANCE.createStaticMethodCall();
			methodCall.setCaller(scope);
			methodCall.setTypeBinding(typeBinding);
			methodCall.setMethodBinding(methodBinding);
			methodCall.setLineNumber(lineNumber);
			methodCall.setColumnNumber(cu.getColumnNumber(node.getStartPosition()));
			methodCall.setFileName(cu.getTypeRoot().getPath().lastSegment());
	
			return super.visit(node);
		}
		
		public boolean visit(ClassInstanceCreation node)
		{
			if( node.getAnonymousClassDeclaration() == null )
			{

				StaticMethodCall methodCall = null;
				IMethodBinding methodBinding = node.resolveConstructorBinding();

				CompilationUnit cu = (CompilationUnit)node.getRoot();
//				int lineNumber = cu.getLineNumber(node.getStartPosition());
				int lineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength());
					methodCall = StaticmodelFactory.eINSTANCE.createStaticMethodCall();
					methodCall.setCaller(scope);
					methodCall.setTypeBinding(methodBinding.getDeclaringClass());
					methodCall.setMethodBinding(methodBinding);
					methodCall.setLineNumber(lineNumber);
					methodCall.setColumnNumber(cu.getColumnNumber(node.getStartPosition()));
					methodCall.setFileName(cu.getTypeRoot().getPath().lastSegment());
			}
			return super.visit(node);
		}
		
		public boolean visit(SuperConstructorInvocation node)
		{		
				StaticMethodCall methodCall = null;
				IMethodBinding methodBinding = node.resolveConstructorBinding();

				CompilationUnit cu = (CompilationUnit)node.getRoot();
//				int lineNumber = cu.getLineNumber(node.getStartPosition());
				int lineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength());
					methodCall = StaticmodelFactory.eINSTANCE.createStaticMethodCall();
					methodCall.setCaller(scope);
					methodCall.setTypeBinding(methodBinding.getDeclaringClass());
					methodCall.setMethodBinding(methodBinding);
					methodCall.setLineNumber(lineNumber);
					methodCall.setColumnNumber(cu.getColumnNumber(node.getStartPosition()));
					methodCall.setFileName(cu.getTypeRoot().getPath().lastSegment());
			return super.visit(node);
		}
		
		public boolean visit(ConstructorInvocation node)
		{
	
			StaticMethodCall methodCall = null;
			IMethodBinding methodBinding = node.resolveConstructorBinding();

			CompilationUnit cu = (CompilationUnit)node.getRoot();
//			int lineNumber = cu.getLineNumber(node.getStartPosition());
			int lineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength());
				methodCall = StaticmodelFactory.eINSTANCE.createStaticMethodCall();
				methodCall.setCaller(scope);
				methodCall.setTypeBinding(methodBinding.getDeclaringClass());
				methodCall.setMethodBinding(methodBinding);
				methodCall.setLineNumber(lineNumber);
				methodCall.setColumnNumber(cu.getColumnNumber(node.getStartPosition()));
				methodCall.setFileName(cu.getTypeRoot().getPath().lastSegment());
			return super.visit(node);
		}
		
		public boolean visit(FieldAccess node)
		{
			ITypeBinding typeBinding = null;
			if( node.getExpression() != null )
			{
				typeBinding = node.getExpression().resolveTypeBinding().getErasure();
			}
			else
			{
				TypeDeclaration td = getEnclosingTypeDeclaration(node);
				typeBinding = td.resolveBinding().getErasure();	
			}

			
			StaticFieldAccess fieldAccess = null;
			IVariableBinding variableBinding = node.resolveFieldBinding();

			CompilationUnit cu = (CompilationUnit)node.getRoot();
			int lineNumber = cu.getLineNumber(node.getStartPosition());

			fieldAccess = StaticmodelFactory.eINSTANCE.createStaticFieldAccess();
			fieldAccess.setAccessingScope(scope);
			fieldAccess.setTypeBinding(typeBinding);
			fieldAccess.setVariableBinding(variableBinding);
			fieldAccess.setLineNumber(lineNumber);
			fieldAccess.setColumnNumber(cu.getColumnNumber(node.getStartPosition()));
			fieldAccess.setFileName(cu.getTypeRoot().getPath().lastSegment());
			
			return super.visit(node);
		}
		
		public boolean visit(SuperFieldAccess node)
		{
			scope.getVariableBindings().add(node.resolveFieldBinding());
			return super.visit(node);
		}
		
		public boolean visit(SimpleName node)
		{
			_visitName(node);
			return super.visit(node);
		}
		
		
		public boolean visit(QualifiedName node)
		{
			_visitName(node);
			return super.visit(node);
		}
		
		private void _visitName(Name node)
		{
			IBinding binding = node.resolveBinding();
			if( binding != null && binding.getKind() == IBinding.VARIABLE && binding instanceof IVariableBinding )
			{
				scope.getVariableBindings().add((IVariableBinding)binding);
			}
		}

		public boolean visit(VariableDeclarationStatement node)
		{
			visitVariableDeclaration(node.fragments(), node.getType());
			return super.visit(node);
		}
		
		public boolean visit(VariableDeclarationExpression node)
		{
			visitVariableDeclaration(node.fragments(), node.getType());
			return super.visit(node);
		}
		
		private void visitVariableDeclaration(List<VariableDeclarationFragment> fragments, Type type)
		{
			for( VariableDeclarationFragment vdf : fragments)
			{
				AOMLocalVariable aomLocalVariable = StructureFactory.eINSTANCE.createAOMLocalVariable();
				scope.getVariables().add(aomLocalVariable);
				aomLocalVariable.setName(vdf.getName().toString());
				aomLocalVariable.setTypeBinding(type.resolveBinding().getErasure());
				scope.getVarBinding2AOMLocalVariable().put(vdf.resolveBinding(), aomLocalVariable);
			}
		}
	}

	private static TypeDeclaration getEnclosingTypeDeclaration(ASTNode node)
	{
		if( node != null )
		{
			if( node.getParent() instanceof TypeDeclaration )
			{
				return (TypeDeclaration) node.getParent();
			}
			else
			{
				return getEnclosingTypeDeclaration(node.getParent());
			}
		}
		
		return null;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		TypeDeclaration parentType = getEnclosingTypeDeclaration(node);
		if(parentType == null) return false;
		ITypeBinding parentBinding = parentType.resolveBinding();
		AOMClass aomClass = typeBinding2AOMClass.get(parentBinding);

		AOMMethod aomMethod = StructureFactory.eINSTANCE.createAOMMethod();
		aomMethod.setAbstract(Modifier.isAbstract(node.getModifiers()));
		aomMethod.setStatic(Modifier.isStatic(node.getModifiers()));
		aomMethod.setConstructor(node.isConstructor());
		
		if( node.isConstructor() )
		{
			aomMethod.setName(aomClass.getName());
		}
		else
		{
			aomMethod.setName(node.getName().getIdentifier());
		}
		
		// setting signature
		{
			int paramSize = node.parameters().size();
			int s = 0;
			String[] parameterTypes = null;
			if( node.isConstructor() && parentType.isMemberTypeDeclaration() && 
					parentType.getParent() instanceof TypeDeclaration &&
					!Modifier.isStatic(parentType.getModifiers()) )
			{
				TypeDeclaration td = (TypeDeclaration)parentType.getParent();
				s = 1;
				paramSize++;
				parameterTypes = new String[paramSize];
				
				parameterTypes[0] = Signature.createTypeSignature(td.resolveBinding().getErasure().getQualifiedName(), true);
			}
			else
			{
				parameterTypes = new String[paramSize];
			}
			
			for(int i = s; i < paramSize; i++ ) 
			{
				SingleVariableDeclaration svd = (SingleVariableDeclaration)node.parameters().get(i - s);
				ITypeBinding tbb = svd.getType().resolveBinding().getErasure();
				String typeName = tbb.getQualifiedName();
				parameterTypes[i] = Signature.createTypeSignature(typeName, true);
			}
			String returnType = "";
			if( node.getReturnType2() != null )
			{
				ITypeBinding tbb = node.getReturnType2().resolveBinding().getErasure();
				String typeName = tbb.getQualifiedName();
				returnType = Signature.createTypeSignature(typeName, true);
			}
			else
			{
				returnType = Signature.createTypeSignature("void", true);
			}
	
			String sig = Signature.createMethodSignature(parameterTypes, returnType);
			aomMethod.setSignature(Signature.getTypeErasure(sig));
		}
		
		
		aomClass.getMethods().add(aomMethod);
	
		
		// Setting return type
		if( node.getReturnType2() != null )
		{
			aomMethod.setTypeBinding(node.getReturnType2().resolveBinding().getErasure());
		}
		else
		{
			aomMethod.setTypeBinding(null);
		}
		
		// setting parameters
		List<SingleVariableDeclaration> parameters  = node.parameters();
		for( SingleVariableDeclaration svd : parameters )
		{
			AOMParameter aomParameter = StructureFactory.eINSTANCE.createAOMParameter();
			aomParameter.setName(svd.getName().toString());
			aomParameter.setTypeBinding(svd.getType().resolveBinding().getErasure());
			aomMethod.getParameters().add(aomParameter);
			aomMethod.getVarBinding2AOMParameter().put(svd.resolveBinding(), aomParameter);
		}
		
		// setting source range;
		CompilationUnit cu = (CompilationUnit)node.getRoot();
		aomMethod.setStartLine(cu.getLineNumber(node.getStartPosition()));
		aomMethod.setEndLine(cu.getLineNumber(node.getStartPosition() + node.getLength()));
		methodBinding2AOMMethod.put(node.resolveBinding(), aomMethod);
		
		if( node.getBody() != null )
		{
			Block block = node.getBody();
			AOMScope scope = StructureFactory.eINSTANCE.createAOMScope();
			aomMethod.setOwnedScope(scope);
			MethodVisitor mv = MethodVisitor.getInstance(aom, scope);
			block.accept(mv);
		}
		
		return false;
	}
	
	

	@Override
	public boolean visit(TypeDeclaration node) {
		TypeDeclaration type = node;
		
		AOMClass aomClass = StructureFactory.eINSTANCE.createAOMClass();
		
		aomClass.setName(getClassName(type));
		aomClass.setFqdn(getFQDN(type));
		CompilationUnit cu = (CompilationUnit)node.getRoot();
		int startLineNumber = cu.getLineNumber(node.getStartPosition());
		int endLineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength());
		aomClass.setTempLOC(endLineNumber - startLineNumber);
		aomClass.setAbstract(Modifier.isAbstract(node.getModifiers()));
		aomClass.setAnonymousClass(false);
		aomClass.setInnerClass(node.isMemberTypeDeclaration());
		aomClass.setInterface(node.isInterface());
		aomClass.setStatic(Modifier.isStatic(node.getModifiers()));
		aomClass.setModifier( Modifier.isPublic(node.getModifiers()) ? AOMModifier.PUBLIC :
			(Modifier.isPrivate(node.getModifiers()) ? AOMModifier.PRIVATE :
				(Modifier.isProtected(node.getModifiers()) ? AOMModifier.PROTECTED :
					AOMModifier.PACKAGE))
				);
		
		aom.getClasses().add(aomClass);
		if( node.getSuperclassType() != null )
		{
			aomClass.getAncestorTypeBindings().add(node.getSuperclassType().resolveBinding());
		}
		for( Type superInterface : (List<Type>)node.superInterfaceTypes() )
		{
			aomClass.getAncestorTypeBindings().add(superInterface.resolveBinding());
		}
		typeBinding2AOMClass.put(type.resolveBinding().getErasure(), aomClass);		
		return super.visit(node);
	}
	
	
	public static String getClassName(TypeDeclaration type)
	{
		
		StringBuffer sb = new StringBuffer();
		_getFQDN(type, sb, false);
		return sb.toString();
	}
	
	public static String getFQDN(TypeDeclaration type)
	{
		StringBuffer sb = new StringBuffer();
		_getFQDN(type, sb, true);
		return sb.toString();
	}
	
	private static void _getFQDN(ASTNode obj, StringBuffer sb, boolean attachPackage)
	{
		if( obj instanceof CompilationUnit )
		{
			CompilationUnit cu = (CompilationUnit)obj;
			if( attachPackage )
			{
				sb.append(cu.getPackage().getName());
				sb.append('.');
			}
		}
		else if( obj instanceof TypeDeclaration )
		{
			_getFQDN(obj.getParent(), sb, attachPackage);
			if( sb.length() > 0 && sb.charAt(sb.length() - 1) != '.' )
			{
				sb.append('$');
			}
			sb.append(((TypeDeclaration)obj).getName());
		}
		else
		{
			_getFQDN(obj.getParent(), sb, attachPackage);
		}
	}
}
