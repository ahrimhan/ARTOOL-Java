package kr.ac.kaist.se.artool.util;

import java.util.HashMap;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.core.runtime.IProgressMonitor;

public class AOMIndex {
	private AbstractObjectModel aom;
	private HashMap<String, ClassIndexItem> classIndex;

	private class ClassIndexItem
	{
		private AOMClass aomClass;
		private HashMap<String, AOMMethod> methodIndex;
		private HashMap<String, AOMField> fieldIndex;
		
		public ClassIndexItem(AOMClass aomClass)
		{
			this.aomClass = aomClass;
			methodIndex = new HashMap<String, AOMMethod>();
			fieldIndex = new HashMap<String, AOMField>();
		}
		
		public AOMClass getAOMClass()
		{
			return aomClass;
		}
		
		public void putMethod(String methodName, String signature, AOMMethod method)
		{
			methodIndex.put(methodName + ":" + signature, method);
		}
		
		public AOMMethod getMethod(String methodName, String signature)
		{
			return methodIndex.get(methodName + ":" + signature);
		}	
		
		public void putField(String fieldName, AOMField field)
		{
			fieldIndex.put(fieldName, field);
		}
		
		public AOMField getField(String fieldName)
		{
			return fieldIndex.get(fieldName);
		}	
	}
	
	// boot-up codes
	private AOMIndex(AbstractObjectModel aom)
	{
		this.aom = aom;
		classIndex = new HashMap<String, ClassIndexItem>(aom.getClasses().size() / 2);
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
				createFieldIndex(item);
				monitor.worked(1);
			}
		}
		finally
		{
			monitor.done();
		}
	}
	
	private void createFieldIndex(ClassIndexItem classItem) {
		for(AOMField field : classItem.getAOMClass().getFields() )
		{
			classItem.putField(field.getName(), field);
		}
	}

	private void createMethodIndex(ClassIndexItem classItem)
	{
		for(AOMMethod method: classItem.getAOMClass().getMethods() )
		{
			classItem.putMethod(method.getName(), method.getSignature(), method);
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
		return classItem.getMethod(methodName, signature);
	}
	
	public AOMField getField(String classFQDN, String fieldName)
	{
		ClassIndexItem classItem = classIndex.get(classFQDN);
		if( classItem == null ) return null;
		return classItem.getField(fieldName);
	}
}
