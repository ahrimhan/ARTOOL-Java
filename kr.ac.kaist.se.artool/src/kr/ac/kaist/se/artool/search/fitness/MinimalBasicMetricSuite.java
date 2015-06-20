package kr.ac.kaist.se.artool.search.fitness;

import java.util.HashSet;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMParameter;

import org.eclipse.emf.common.util.EList;

public class MinimalBasicMetricSuite {

	public MinimalBasicMetricSuite() {
	}
	
	
	public static final String DCC="DCC";
	public static final String CAM="CAM";
	public static final String NOP = "NOP"; //parent
	public static final String CIS = "CIS";
	public static final String NOM = "NOM"; //methods

	
	
	
	public void measure(AbstractObjectModel aom) {
		for( AOMClass clazz : aom.getClasses() )
		{
			measure(clazz);
		}			
	}
	
	
	private HashSet<AOMClass> visitedClasses = new HashSet<AOMClass>();
	
	
	
	public static int factorial(int n) {
        if      (n <  0) throw new RuntimeException("Underflow error in factorial");
        else if (n > 20) throw new RuntimeException("Overflow error in factorial");
        else if (n == 0) return 1;
        else             return n * factorial(n-1);
    }
	
	public void _initializeMetric(AOMClass clazz, String s, int intValue)
	{
		if( clazz.getMeasuredDataSet().get(s) == null )
			clazz.getMeasuredDataSet().put(s, new int[] { intValue });
		else
		{
			int[] i = (int[])clazz.getMeasuredDataSet().get(s);
			i[0] = intValue;
		}
	}
	
	public void _initializeMetric(AOMMethod method, String s, int intValue)
	{
		if( method.getMeasuredDataSet().get(s) == null )
			method.getMeasuredDataSet().put(s, new int[] { intValue });
		else
		{
			int[] i = (int[])method.getMeasuredDataSet().get(s);
			i[0] = intValue;
		}
	}	
	
	public void _initializeMetric(AOMClass clazz, String s, float intValue)
	{
		if( clazz.getMeasuredDataSet().get(s) == null )
			clazz.getMeasuredDataSet().put(s, new float[] { intValue });
		else
		{
			float[] i = (float[])clazz.getMeasuredDataSet().get(s);
			i[0] = intValue;
		}
	}
	//HashSet<AOMField> referringField1 = new HashSet<AOMField>();
	//HashSet<AOMField> referringField2 = new HashSet<AOMField>();
	public void measure(AOMClass clazz)
	{			
				
		_initializeMetric(clazz, DCC, 0);
		_initializeMetric(clazz, CAM, 0);
		_initializeMetric(clazz, NOP, 0);
		_initializeMetric(clazz, CIS, 0);
		_initializeMetric(clazz, NOM, 0);
		
				
		HashSet<AOMClass> dccSet = new HashSet<AOMClass>();
		HashSet<AOMClass> camcTotalSet = new HashSet<AOMClass>();
		HashSet<AOMClass> camcMethodSet =  new HashSet<AOMClass>();
		int camcMethodParameterCount = 0;
		
		for( AOMMethod method : clazz.getMethods() )
		{
			measure(method);
			
			camcMethodSet.clear();
			for( AOMParameter param : method.getParameters() )
			{
				if( param.getType() instanceof AOMClass )
				{
					dccSet.add((AOMClass)param.getType());
					camcMethodSet.add((AOMClass)param.getType());
					camcTotalSet.add((AOMClass)param.getType());
				}
			}
			
			if( method.getType() instanceof AOMClass )
			{
				dccSet.add((AOMClass)method.getType());
				camcMethodSet.add((AOMClass)method.getType());
				camcTotalSet.add((AOMClass)method.getType());
			}
			
			camcMethodParameterCount += camcMethodSet.size();
		}
		
		float camc = camcMethodParameterCount / ((float)camcTotalSet.size() * clazz.getMethods().size());
		
		_initializeMetric(clazz, CAM, camc);
		
		
		
		for( AOMField field : clazz.getFields() )
		{
			measure(field);
			if( field.getType() instanceof AOMClass )
			{
				dccSet.add((AOMClass)field.getType());		
			}
		}
		
		increase(clazz.getMeasuredDataSet().get(DCC), dccSet.size());
	}
	
	public static int getInt(Object obj)
	{
		if( obj == null  )
		{
			return 0;
		}
		if( !(obj instanceof int[]) )
		{
			throw new RuntimeException("not int[]");
		}
		return ((int[])obj)[0];
	}
	
	public static float getFloat(Object obj)
	{
		if( obj == null  )
		{
			return 0;
		}
		if( !(obj instanceof float[]) )
		{
			throw new RuntimeException("not float[]");
		}
		return ((float[])obj)[0];
	}
	
	public static void increase(Object obj, int j)
	{
		if( obj == null  )
		{
			return;
		}
		if( !(obj instanceof int[]) )
		{
			throw new RuntimeException("not int[]");
		}
		int[] i = ((int[])obj);
		
		i[0] += j; 
	}
	
	public static void setInt(Object obj, int j)
	{
		if( obj == null  )
		{
			return;
		}
		if( !(obj instanceof int[]) )
		{
			throw new RuntimeException("not int[]");
		}
		((int[])obj)[0] = j;
	}

	public static void setFloat(Object obj, float j)
	{
		if( obj == null  )
		{
			return;
		}
		if( !(obj instanceof float[]) )
		{
			throw new RuntimeException("not float[]");
		}
		((float[])obj)[0] = j;
	}
		
	
	public void measure(AOMMethod method)
	{
		AOMClass clazz = method.getOwner();
		
								
		if( !method.isConstructor() )
		{	
			increase(clazz.getMeasuredDataSet().get(NOM), 1);
		}
		
		
		if( method.getOverridedBy().size() > 0 )
		{
			increase(clazz.getMeasuredDataSet().get(NOP), 1);
		}
							
		
		if( method.isPublicEntity() )
		{
			increase(clazz.getMeasuredDataSet().get(CIS), 1);
		}
	}
	
	
	public void measure(AOMField field)
	{
		//AOMClass clazz = field.getOwner();	
	}
}
