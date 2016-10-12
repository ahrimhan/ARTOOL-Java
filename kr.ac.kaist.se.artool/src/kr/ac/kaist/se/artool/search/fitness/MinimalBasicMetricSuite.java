package kr.ac.kaist.se.artool.search.fitness;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import com.google.common.collect.Sets;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMParameter;

public class MinimalBasicMetricSuite {

	public MinimalBasicMetricSuite() {
	}
	
	
	public static final String DCC = "DCC";
	public static final String CAM = "CAM";
	public static final String NOP = "NOP"; //parent
	public static final String CIS = "CIS";
	public static final String NOM = "NOM"; //methods
	public static final String MPC = "MPC";
	public static final String MSC = "MSC";
	public static final String CONN = "CONN";
//	public static final String RAW_CONN = "RAW_CONN";
	
	
	
	public void measure(AbstractObjectModel aom, boolean msc, boolean connectivity) {
		for( AOMClass clazz : aom.getClasses() )
		{
			measure(clazz, msc, connectivity);
		}			
	}
	
	
//	private HashSet<AOMClass> visitedClasses = new HashSet<AOMClass>();
	
	
	
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

	public void _initializeMetric(AOMClass clazz, String s, long longValue)
	{
		if( clazz.getMeasuredDataSet().get(s) == null )
			clazz.getMeasuredDataSet().put(s, new long[] { longValue });
		else
		{
			long[] i = (long[])clazz.getMeasuredDataSet().get(s);
			i[0] = longValue;
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

	public void measure(AOMClass clazz, boolean msc, boolean connectivity)
	{			
				
		_initializeMetric(clazz, DCC, 0);
		//_initializeMetric(clazz, CAM, (float)0);
		_initializeMetric(clazz, NOP, 0);
		_initializeMetric(clazz, CIS, 0);
		_initializeMetric(clazz, NOM, 0);
	
		
				
		HashSet<AOMClass> dccSet = new HashSet<AOMClass>();
		HashSet<AOMClass> camcTotalSet = new HashSet<AOMClass>();
		HashSet<AOMClass> camcMethodSet =  new HashSet<AOMClass>();
		
//		HashSet<AOMMethod> mpcSet = new HashSet<AOMMethod>();
		long mpcCount = 0;
		int camcMethodParameterCount = 0;
		
		@SuppressWarnings("unchecked")
		HashSet<AOMField>[] mscFieldList = new HashSet[clazz.getMethods().size()];
		
		
		_initializeMetric(clazz, CONN, (float)0);

		if( connectivity && clazz.getMethods().size() > 1 )
		{
			int[] connCheckField = new int[clazz.getMethods().size() * clazz.getMethods().size()];
			for( int i = 0 ; i < connCheckField.length; i++ )
			{
				connCheckField[i] = 0;	
			}
			
			@SuppressWarnings("unused")
			Vector<AOMMethod> calleeMethods = new Vector<AOMMethod>(); 
					
			int ii= 0; 
			for( AOMMethod method : clazz.getMethods() )
			{

				if( method.getOwnedScope() != null )
				{
					for( StaticMethodCall call : method.getOwnedScope().getStaticMethodCalls() )
					{
						if( call.getCallee() != method && call.getCallee().getOwner() == clazz )
						{
							int calleeIndex = clazz.getMethods().indexOf(call.getCallee());
							connCheckField[ii * clazz.getMethods().size() + calleeIndex] = 1;
							connCheckField[calleeIndex * clazz.getMethods().size() + ii] = 1;
						}
					}
					
					for( StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
					{
						if( sfa.getAccessedField() != null )
						{
							for( StaticFieldAccess sfa2 : sfa.getAccessedField().getStaticReferer() )
							{
								if( sfa != sfa2 && sfa2.getAccessingScope().getOwner() != method 
										&& sfa2.getAccessingScope().getOwner().getOwner() == clazz )
								{
									int calleeIndex = clazz.getMethods().indexOf(sfa2.getAccessingScope().getOwner());
									connCheckField[ii * clazz.getMethods().size() + calleeIndex] = 1;
									connCheckField[calleeIndex * clazz.getMethods().size() + ii] = 1;
								}
							}
						}
					}
				}
				ii++;
			}
			
			int total = 0;
			
			for( int i = 0 ; i < connCheckField.length; i++ )
			{
				total  += connCheckField[i];
			}
			
			float connectivityValue = ((float)total);
					
			
			_initializeMetric(clazz, CONN, connectivityValue);
		}
		else
		{
			_initializeMetric(clazz, CONN, (float)-1);
		}
		
		int index = 0;
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
			
			if( method.getOwnedScope() != null )
			{
				for( StaticMethodCall call : method.getOwnedScope().getStaticMethodCalls() )
				{
					if( call.getCallee().getOwner() != clazz )
					{
//						mpcSet.add(call.getCallee());
						mpcCount++;
					}
				}
			}
			
			if( method.getType() instanceof AOMClass )
			{
				dccSet.add((AOMClass)method.getType());
				camcMethodSet.add((AOMClass)method.getType());
				camcTotalSet.add((AOMClass)method.getType());
			}
			
			camcMethodParameterCount += camcMethodSet.size();
			
			
			
			if( msc )
			{
				HashSet<AOMField> fieldSet = new HashSet<AOMField>();
				
				if( method.getOwnedScope() != null )
				{
					for( StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
					{
						fieldSet.add(sfa.getAccessedField()); 
					}
				}
				
				mscFieldList[index] = fieldSet;	
			}
			
			index++;
		}
		
		
		if( msc )
		{
			List<AOMMethod> methodList = clazz.getMethods();
			double mscValue = 0;
	
			int totalMSCValueCount = 0;
			
			for( int i = 0; i < methodList.size() - 1; i++ )
			{
				for( int j = i + 1; j < methodList.size(); j++ )
				{
					if( mscFieldList[i] == null || mscFieldList[j] == null ) continue;
					
					int unionSize = Sets.union(mscFieldList[i], mscFieldList[j]).size();	
					
					if( unionSize != 0 )
					{
						int intersectionSize = Sets.intersection(mscFieldList[i], mscFieldList[j]).size();
						double ms = intersectionSize / ((double) unionSize);
						mscValue += ms;
						totalMSCValueCount++;
					}
				}
			}
			
			if( totalMSCValueCount != 0 )
			{
				mscValue = mscValue / totalMSCValueCount;
				
				if( Double.isFinite(mscValue) )
				{
					_initializeMetric(clazz, MSC, (float)mscValue);
				}
				else
				{
					_initializeMetric(clazz, MSC, (float)-1);
				}
			}
			else
			{
				_initializeMetric(clazz, MSC, (float)-1);
			}
		}
		
		
		float camc = -1;
		
		if( camcTotalSet.size() != 0 && clazz.getMethods().size() != 0 )
		{
			camc = camcMethodParameterCount / ((float)camcTotalSet.size() * clazz.getMethods().size());
		}
		
		_initializeMetric(clazz, CAM, camc);
		_initializeMetric(clazz, MPC, mpcCount);
		
		
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
	
	public static long getLong(Object obj)
	{
		if( obj == null  )
		{
			return 0;
		}
		if( !(obj instanceof long[]) )
		{
			throw new RuntimeException("not long[]");
		}
		return ((long[])obj)[0];
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
