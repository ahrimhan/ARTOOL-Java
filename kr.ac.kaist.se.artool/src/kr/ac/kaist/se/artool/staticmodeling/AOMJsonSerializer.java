package kr.ac.kaist.se.artool.staticmodeling;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class AOMJsonSerializer {

	private AbstractObjectModel aom;
	
	private Vector<AOMMethod> methodList;
	private Vector<AOMField> fieldList;
	
	public AOMJsonSerializer(AbstractObjectModel aom)
	{
		this.aom = aom;
		
		methodList = new Vector<AOMMethod>();
		fieldList = new Vector<AOMField>();
	}
	
	public InputStream serialize() throws IOException
	{
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(fos, true);
		int idx = 0;
		
		writer.println("{");
		writer.println("\t\"classes\":[");
	
		boolean isFirst = true;
		methodList.clear();
		fieldList.clear();
		
		for(AOMClass clazz : aom.getClasses())
		{
			if( isFirst )
			{
				isFirst = false;
			}
			else
			{
				writer.println(",");
			}
			clazz.setIndex(idx);
			idx++;
			writer.print("\t\t\"" + clazz.getFqdn() + "\"");
			
			for( AOMMethod method : clazz.getMethods() )
			{
				methodList.add(method);
			}
			
			for( AOMField field : clazz.getFields() )
			{
				fieldList.add(field);
			}
		}
		
		writer.println("\n\t],");
		
		idx = 0;
		isFirst = true;
		writer.println("\t\"methods\":[");
		
		for( AOMMethod method : methodList )
		{
			if( isFirst )
			{
				isFirst = false;
			}
			else
			{
				writer.println(",");
			}
			
			method.setIndex(idx);
			idx++;
			writer.print("\t\t\"" + method.getSignature() + "\"," + method.getOwner().getIndex());
		}
		
		writer.println("\n\t],");
		
		isFirst = true;
		writer.println("\t\"fields\":[");

		
		for( AOMField field : fieldList )
		{
			if( isFirst )
			{
				isFirst = false;
			}
			else
			{
				writer.println(",");
			}
			
			field.setIndex(idx);
			idx++;
			writer.print("\t\t\"" + field.getName() + "\"," + field.getOwner().getIndex());
		}
		
		writer.println("\n\t],");
		
		isFirst = true;
		writer.println("\t\"methodCalls\":[");
		
		for( AOMMethod method : methodList )
		{
			if( method.getOwnedScope() != null )
			{
				for( StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
				{
					AOMMethod calleeMethod = smc.getCallee();
					if( isFirst )
					{
						isFirst = false;
					}
					else
					{
						writer.println(",");
					}
					
					writer.print("\t\t" + method.getIndex() + "," + calleeMethod.getIndex());  
				}
			}
		}
		
		writer.println("\n\t],");
		
		isFirst = true;
		writer.println("\t\"fieldAccess\":[");
		
		for( AOMMethod method : methodList )
		{
			if( method.getOwnedScope() != null )
			{
				for( StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
				{
					AOMField accessedField = sfa.getAccessedField();
					if( isFirst )
					{
						isFirst = false;
					}
					else
					{
						writer.println(",");
					}
					
					writer.print("\t\t" + method.getIndex() + "," + accessedField.getIndex());  
				}
			}
		}
		
		writer.println("\n\t]");
		
		writer.println("}");
		
		writer.flush();
		fos.flush();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(fos.toByteArray());
		
		writer.close();
		fos.close();
		
		return bais;
	}
}
