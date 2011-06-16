package kr.ac.kaist.se.artool.engine.metrics;

import java.util.HashSet;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.emf.common.util.EList;

public class BasicMetricSuite {

	public BasicMetricSuite() {
	}
	

	public static final String LOC = "LOC";
	public static final String NOM = "NOM"; //methods
	public static final String NOA = "NOA"; //attributes
	public static final String NOCON = "NOCON"; //constructors
	public static final String NOO = "NOO"; //operations
	public static final String DIT = "DIT"; //depth of inheritance tree
	public static final String CLD = "CLD"; //class-to-leaf depth
	public static final String NOC = "NOC"; //children
	public static final String NOP = "NOP"; //parent
	public static final String NMO = "NMO"; //overridden
	public static final String NMI = "NMI"; //inherited
	public static final String NMA = "NMA"; //newly added
	public static final String WMC = "WMC"; //weighted method per class
	//coupling
	public static final String DynamicImport = "DynamicImport";
	public static final String DynamicExport = "DynamicExport"; 
	public static final String StaticImport = "StaticImport"; //alike with RFC
	public static final String StaticExport = "StaticExport";
	//message-level, a() a() 이러면 a() 두번 count vs.
	//method-level, a() a() 이러면 a() 한번 count
	public static final String DynamicBoth = "DynamicBoth";
	public static final String StaticBoth = "StaticBoth"; //alike with CBO
	//cohesion
	public static final String LCOM2 = "LCOM2";
	public static final String LCOM3 = "LCOM3";
	public static final String MSC	 = "MSC";
	//etc.
	public static final String NUIM = "NUIM"; //number of used inherited method (dynamic)
	public static final String NUOM = "NUOM"; //number of used overriding method (dynamic)
	//dynamic coupling (to reduce the effect of loop)
	//new(20110421)
	public static final String MPCDE = "MPCDE"; //"Message" Passing Coupling (DynamicMethodCalls에서 export + import & distinct한것만 함.) 
	public static final String MPCDI = "MPCDI";
	public static final String MPCDBoth = "MPCDBoth";
	//new(20110511)
	public static final String MPCSE = "MPCSE"; //"Method" Passing Coupling (StaticMethodCalls에서 export + import & distinct한것만 함.) 
	public static final String MPCSI = "MPCSI";
	public static final String MPCSBoth = "MPCSBoth";
	
	public void measure(AbstractObjectModel aom) {
		for( AOMClass clazz : aom.getClasses() )
		{
			measure(clazz);
		}	
		
		for( AOMClass clazz : aom.getClasses() )
		{
			measureBoth(clazz);
		}	
		
		//nuim & nuom
		//dynamic
		
		AOMClass dynamicCalleeClass; //real implemented class
		AOMClass staticCalleeClass;
		int numUsedInheritedMethods = 0;
		int numUsedOverringMethods = 0;
		
		for( AOMClass clazz : aom.getClasses() )
		{
		
			for( AOMMethod method : clazz.getMethods())
			{
				if( method.getOwnedScope() != null )
				{
					for( DynamicMethodCall dmc : method.getOwnedScope().getDynamicMethodCalls() )
					{
						dynamicCalleeClass = dmc.getCallee().getOwner(); //c4
						if( dynamicCalleeClass != null &&
								dmc.getStatic() != null)
						{
							staticCalleeClass = dmc.getStatic().getCallingType(); //c3
								
							if( !staticCalleeClass.getFqdn().equals(dynamicCalleeClass.getFqdn()) ) //inherited
							{
								increase(staticCalleeClass.getMeasuredDataSet().get(NUIM), 1);
							}
						}
						
						if( dmc.getCallee().getOverriding() != null)
						{
							increase(dynamicCalleeClass.getMeasuredDataSet().get(NUOM), 1);
						}
						
					}
						
				}
			}
		}

	}
	
	private static boolean isIdenticalMethod(AOMMethod m1, AOMMethod m2)
	{
		boolean ret = false;
		
		if( m1.getName().equals(m2.getName()) && m1.getSignature().equals(m2.getSignature()) )
		{
			ret = true;
		}
		
		return ret;
	}
	
	private static AOMMethod findOverriding(AOMClass c, AOMMethod m)
	{
		Vector<AOMClass> queue = new Vector<AOMClass>(c.getAncestor());
		Vector<AOMClass> visitedClass = new Vector<AOMClass>();
		while( !queue.isEmpty() )
		{
			AOMClass aomClass = queue.remove(0);
			visitedClass.add(aomClass);
			for( AOMMethod lm : aomClass.getMethods() )
			{
				if( isIdenticalMethod(lm, m) )
				{
					return lm;
				}
			}
			
			for( AOMClass p : aomClass.getAncestor() )
			{
				if( !visitedClass.contains(p) )
				{
					queue.add(p);
				}
			}
		}

		return null;
	}
	
	private HashSet<AOMClass> visitedClasses = new HashSet<AOMClass>();
	
	
	public int getMaximumAncestorDepth (AOMClass clazz)
	{
		int max = 0;
	
		if( visitedClasses.contains(clazz) )
		{
			return max;
		}
		
		visitedClasses.add(clazz);
		
		for ( AOMClass clazz2 : clazz.getAncestor())
		{
			int depth = getMaximumAncestorDepth(clazz2) + 1;
			
			if( depth > max )
			{
				max = depth;
			}
		}
	
		return max;	
	}
	
	public int getNumAncestor(AOMClass clazz)
	{
		int numAncestor = 0;
		Vector<AOMClass> queue = new Vector<AOMClass>(clazz.getAncestor());
		Vector<AOMClass> visitedClass = new Vector<AOMClass>();
		
		while( !queue.isEmpty() )
		{
			AOMClass aomClass = queue.remove(0);
			numAncestor++;
			visitedClass.add(aomClass);
						
			for( AOMClass p : aomClass.getAncestor() )
			{
				if( !visitedClass.contains(p) )
				{
					queue.add(p);
				}
			}
		}
		return numAncestor;
	}
	
	public Vector<AOMClass> getListAncestor(AOMClass clazz)
	{
		Vector<AOMClass> queue = new Vector<AOMClass>(clazz.getAncestor());
		Vector<AOMClass> visitedClass = new Vector<AOMClass>();
		
		while( !queue.isEmpty() )
		{
			AOMClass aomClass = queue.remove(0);
			visitedClass.add(aomClass);
						
			for( AOMClass p : aomClass.getAncestor() )
			{
				if( !visitedClass.contains(p) )
				{
					queue.add(p);
				}
			}
		}
		return visitedClass;
	}
	
	public int getNumDescendant(AOMClass clazz)
	{
		int numDescendant = 0;
		Vector<AOMClass> queue = new Vector<AOMClass>(clazz.getDescendant());
		Vector<AOMClass> visitedClass = new Vector<AOMClass>();
		
		while( !queue.isEmpty() )
		{
			AOMClass aomClass = queue.remove(0);
			numDescendant++;
			visitedClass.add(aomClass);
						
			for( AOMClass p : aomClass.getDescendant() )
			{
				if( !visitedClass.contains(p) )
				{
					queue.add(p);
				}
			}
		}
		return numDescendant;
	}
	
	public Vector<AOMClass> getListDescendant(AOMClass clazz)
	{
		Vector<AOMClass> queue = new Vector<AOMClass>(clazz.getDescendant());
		Vector<AOMClass> visitedClass = new Vector<AOMClass>();
		
		while( !queue.isEmpty() )
		{
			AOMClass aomClass = queue.remove(0);
			visitedClass.add(aomClass);
						
			for( AOMClass p : aomClass.getDescendant() )
			{
				if( !visitedClass.contains(p) )
				{
					queue.add(p);
				}
			}
		}
		return visitedClass;
	}
	
	
	public int getMaximemDescendantDepth(AOMClass clazz)
	{
		int max = 0;
		
		if( visitedClasses.contains(clazz) )
		{
			return max;
		}
		
		visitedClasses.add(clazz);
		
		for( AOMClass clazz2 : clazz.getDescendant() )
		{
			int depth = getMaximemDescendantDepth(clazz2) + 1;
			
			if( depth > max )
			{
				max = depth;
			}
		}
		
		return max;
	}
	
	public boolean isInherited ( AOMClass clazz, AOMMethod method)
	{
		boolean inherited = false;
	
		for ( AOMClass clazz2 : clazz.getAncestor())
		{
			for (AOMMethod method2 : clazz2.getMethods() )
			{
				if ( method2.getName().equals(method.getName()) &&
					method2.getSignature().equals(method.getSignature()) )
					{
						inherited = true;
					}
			}
		}
		
		return inherited;
	}
	
	public void measureBoth (AOMClass clazz)
	{
		clazz.getMeasuredDataSet().put(StaticBoth, new int[] { 
				getInt(clazz.getMeasuredDataSet().get(StaticImport)) +
				 getInt(clazz.getMeasuredDataSet().get(StaticExport)) 
		});
		clazz.getMeasuredDataSet().put(DynamicBoth, new int[]{ 
				getInt(clazz.getMeasuredDataSet().get(DynamicImport)) +
				 getInt(clazz.getMeasuredDataSet().get(DynamicExport)) 
		});
		clazz.getMeasuredDataSet().put(MPCDBoth, new int[] {
				getInt(clazz.getMeasuredDataSet().get(MPCDI)) +
				 getInt(clazz.getMeasuredDataSet().get(MPCDE))
		});
		clazz.getMeasuredDataSet().put(MPCSBoth, new int[]{
				getInt(clazz.getMeasuredDataSet().get(MPCSI)) +
				 getInt(clazz.getMeasuredDataSet().get(MPCSE))
		});
	}
	
	private static int[] newZeroInt() { return new int[] { 0 }; }
	
	private static float[] newZeroFloat() { return new float[] { 0 }; }
	
	public static int factorial(int n) {
        if      (n <  0) throw new RuntimeException("Underflow error in factorial");
        else if (n > 20) throw new RuntimeException("Overflow error in factorial");
        else if (n == 0) return 1;
        else             return n * factorial(n-1);
    }
		
	public void measure(AOMClass clazz)
	{
		clazz.getMeasuredDataSet().put(LOC, new int[] { clazz.getLOC() });
		clazz.getMeasuredDataSet().put(NOCON, newZeroInt());
		clazz.getMeasuredDataSet().put(NOO, newZeroInt());
		clazz.getMeasuredDataSet().put(NOA, newZeroInt());
		clazz.getMeasuredDataSet().put(NOM, newZeroInt());
//		clazz.getMeasuredDataSet().put(DIT, newZeroInt());
//		clazz.getMeasuredDataSet().put(CLD, newZeroInt());
//		clazz.getMeasuredDataSet().put(NOC, newZeroInt());
//		clazz.getMeasuredDataSet().put(NOP, newZeroInt());
		clazz.getMeasuredDataSet().put(NMO, newZeroInt());
		clazz.getMeasuredDataSet().put(NMI, newZeroInt());
		clazz.getMeasuredDataSet().put(NMA, newZeroInt());
//		clazz.getMeasuredDataSet().put(WMC, newZeroInt());
		clazz.getMeasuredDataSet().put(StaticImport, newZeroInt());
		clazz.getMeasuredDataSet().put(DynamicImport, newZeroInt());
		clazz.getMeasuredDataSet().put(StaticExport, newZeroInt());
		clazz.getMeasuredDataSet().put(DynamicExport, newZeroInt());
		clazz.getMeasuredDataSet().put(LCOM2, newZeroFloat());
		clazz.getMeasuredDataSet().put(LCOM3, newZeroFloat());
		clazz.getMeasuredDataSet().put(NUIM, newZeroInt());
		clazz.getMeasuredDataSet().put(NUOM, newZeroInt());
		//20110425
		clazz.getMeasuredDataSet().put(MPCDE, newZeroInt());
		clazz.getMeasuredDataSet().put(MPCDI, newZeroInt());
		clazz.getMeasuredDataSet().put(MPCSE, newZeroInt());
		clazz.getMeasuredDataSet().put(MPCSI, newZeroInt());
		// MSC
		clazz.getMeasuredDataSet().put(MSC, newZeroFloat());
	
								
		int dit = 0; int cld = 0;
		int max_anc = 0; int max_des = 0;

		visitedClasses.clear();
		clazz.getMeasuredDataSet().put(DIT, new int[]{ getMaximumAncestorDepth(clazz) });
		visitedClasses.clear();
		clazz.getMeasuredDataSet().put(CLD, new int[]{ getMaximemDescendantDepth(clazz) });
		//
		clazz.getMeasuredDataSet().put(NOC, new int[]{ getNumDescendant(clazz) });
		clazz.getMeasuredDataSet().put(NOP, new int[]{ getNumAncestor(clazz) });
		clazz.getMeasuredDataSet().put(WMC, new int[]{ clazz.getMethods().size() });	
		
		//
		for( AOMMethod method : clazz.getMethods() )
		{
			measure(method);
		}
		
		//nmi (must be calculated after measure(method)
		Object nmiObj = clazz.getMeasuredDataSet().get(NMI);
		for ( AOMClass clazz2 : clazz.getAncestor() )
		{
			increase(nmiObj, getInt(clazz2.getMeasuredDataSet().get(NMA)));
		}
		
		
		
		int num_methods = 0;
		int num_attributes = 0;
		int sum_mA = 0;
		
		num_methods = clazz.getMethods().size();
		num_attributes = clazz.getFields().size();
		
		
		for( AOMField field : clazz.getFields() )
		{
			measure(field);
			sum_mA += field.getReferer().size();
		}
		
		if( num_methods * num_attributes != 0 )
		{
//			clazz.getMeasuredDataSet().put(LCOM2, 1 - ( sum_mA/(num_methods * num_attributes)) );
			setFloat(clazz.getMeasuredDataSet().get(LCOM2), ((float)1.) - ( ((float)sum_mA)/((float)(num_methods * num_attributes))));
		}
		else
		{
			//System.err.println("LCOM2 is not be able to be calculated.");
		}
		
		if( num_methods-1 != 0 && num_attributes !=0 )
		{
//			clazz.getMeasuredDataSet().put(LCOM3, ( num_methods - (sum_mA/num_attributes) )/(num_methods-1) );	
			setFloat(clazz.getMeasuredDataSet().get(LCOM3), ( ((float)num_methods) - (((float)sum_mA)/num_attributes) )/((float)(num_methods-1)));
		}
		else
		{
			//System.err.println("LCOM3 is not be able to be calculated.");
		}
		
		// MSC
		
		int methodSize = clazz.getMethods().size();
		int unionSize = 0;
		int intersectSize = 0;
		int methodPairNum = 0;
		float totalRatio = 0;
		float ms = 0;
		for( int i = 0; i < methodSize - 1; i++ )
		{
			for( int j = i + 1; j < methodSize; j++ )
			{
				AOMMethod method1 = clazz.getMethods().get(i);
				AOMMethod method2 = clazz.getMethods().get(j);
				if( method1.getOwnedScope() != null && method2.getOwnedScope() != null )
				{
					EList<AOMField> referringField1 = method1.getOwnedScope().getReferringFields();
					EList<AOMField> referringField2 = method2.getOwnedScope().getReferringFields();
					
					unionSize = referringField1.size() + referringField2.size();
					intersectSize = 0;
					for( AOMField f : referringField1 )
					{
						if( referringField2.contains(f) )
						{
							intersectSize++;
						}
					}
					unionSize -= intersectSize;
					
					if( unionSize > 0 )
					{
						totalRatio += ((float)intersectSize)/((float)unionSize);
					}
					methodPairNum++;
				}
			}
		}
		
		if( methodPairNum != 0 )
		{
			ms = totalRatio / methodPairNum;
		}
		
		setFloat(clazz.getMeasuredDataSet().get(MSC), ms);
		
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
	
	Vector<StaticMethodCall> containedDynamictoStaticMethodCall = new Vector<StaticMethodCall>();
	Vector<StaticMethodCall> containedStaticMethodCall = new Vector<StaticMethodCall>();
	
	
	public void measure(AOMMethod method)
	{
		method.getMeasuredDataSet().put(LOC, method.getLOC());
		AOMClass clazz = method.getOwner();
		
//		int nom = getInt(clazz.getMeasuredDataSet().get(NOM));
//		int noo = getInt(clazz.getMeasuredDataSet().get(NOO));
//		int nocon = getInt(clazz.getMeasuredDataSet().get(NOCON));
//		int nmo = getInt(clazz.getMeasuredDataSet().get(NMO));
//		int nma = getInt(clazz.getMeasuredDataSet().get(NMA));
				
		if ( method.getOverriding() != null)
		{
			increase(clazz.getMeasuredDataSet().get(NMO), 1);
		}
		else //newly added
		{
			increase(clazz.getMeasuredDataSet().get(NMA), 1);
		}
						
		if( method.isConstructor() )
		{
			increase(clazz.getMeasuredDataSet().get(NOCON), 1);
		}
		else
		{	
			increase(clazz.getMeasuredDataSet().get(NOO), 1);
			increase(clazz.getMeasuredDataSet().get(NOM), 1);
		}
		
		containedDynamictoStaticMethodCall.clear();
		containedStaticMethodCall.clear();
		//import
//		int SI = getInt(clazz.getMeasuredDataSet().get(StaticImport));
//		int DI = getInt(clazz.getMeasuredDataSet().get(DynamicImport));
		Object SIobj = clazz.getMeasuredDataSet().get(StaticImport);
		Object DIobj = clazz.getMeasuredDataSet().get(DynamicImport);
		AOMClass caller = null;
		int valueMPCDI = 0;
		int valueMPCSI = 0;
		
		if( method.getOwnedScope() != null )
		{
		
			
			for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls())
			{
				caller = smc.getCaller().getOwner().getOwner();
				AOMClass callee2 = smc.getCallee().getOwner();
				
				if( caller != null && caller != callee2 )
				{
					increase(SIobj, 1);
					
					if( !containedStaticMethodCall.contains(smc) )
					{
						containedDynamictoStaticMethodCall.add(smc);
	//					valueMPCSI = getInt(caller.getMeasuredDataSet().get(MPCSI));
	//					valueMPCSI++;
	//					caller.getMeasuredDataSet().put(MPCSI, valueMPCSI);
						
						increase(caller.getMeasuredDataSet().get(MPCSI), 1);
					}
				}
			}
			
			for(DynamicMethodCall dmc : method.getOwnedScope().getDynamicMethodCalls())
			{
				caller = dmc.getCaller().getOwner().getOwner();
				AOMClass calle2 = dmc.getCallee().getOwner();
				
				if( caller != null && caller != calle2 )
				{
					increase(DIobj, 1); 
	
					if( !containedDynamictoStaticMethodCall.contains(dmc.getStatic()) 
							&& caller != null)
					{
						containedDynamictoStaticMethodCall.add(dmc.getStatic());
	//					valueMPCDI = getInt(caller.getMeasuredDataSet().get(MPCDI));
	//					valueMPCDI++;
	//					caller.getMeasuredDataSet().put(MPCDI, valueMPCDI);
						
						increase(caller.getMeasuredDataSet().get(MPCDI), 1);
					}
				}
			}
		}
		containedStaticMethodCall.clear();
		containedDynamictoStaticMethodCall.clear();
		
		//export
		int SE = 0; int DE = 0; 
		int valueMPCDE = 0;
		int valueMPCSE = 0;
		AOMClass callee =  null;
		
		for ( StaticMethodCall smc : method.getStaticReferer() )
		{
			callee = smc.getCallee().getOwner();
			AOMClass caller2 = smc.getCaller().getOwner().getOwner();
			if( callee != null && callee != caller2 )
			{
//				SE = getInt(callee.getMeasuredDataSet().get(StaticExport));
//				SE++;
//				callee.getMeasuredDataSet().put(StaticExport, SE);
				
				increase(callee.getMeasuredDataSet().get(StaticExport), 1);
				if( !containedStaticMethodCall.contains(smc) )
				{
					containedStaticMethodCall.add(smc);
//					valueMPCSE = getInt(callee.getMeasuredDataSet().get(MPCSE));
//					valueMPCSE++;
//					callee.getMeasuredDataSet().put(MPCSE, valueMPCSE);
					
					increase(callee.getMeasuredDataSet().get(MPCSE), 1);
				}
			}
			
		}
		containedStaticMethodCall.clear();
		
		for ( DynamicMethodCall dmc : method.getDynamicReferer() )
		{
			callee = dmc.getCallee().getOwner();
			AOMClass caller2 = dmc.getCaller().getOwner().getOwner();
			if( callee != null && callee != caller2 )
			{
//				DE = getInt(callee.getMeasuredDataSet().get(DynamicExport));
//				DE++;
//				callee.getMeasuredDataSet().put(DynamicExport, DE);
				
				increase(callee.getMeasuredDataSet().get(DynamicExport), 1);
				if( !containedDynamictoStaticMethodCall.contains(dmc.getStatic()) )
				{
					containedDynamictoStaticMethodCall.add(dmc.getStatic());
//					valueMPCDE =  getInt(callee.getMeasuredDataSet().get(MPCDE));
//					valueMPCDE++;
//					callee.getMeasuredDataSet().put(MPCDE, valueMPCDE);
					increase(callee.getMeasuredDataSet().get(MPCDE), 1);
				}
			}
			
		}
		containedDynamictoStaticMethodCall.clear();
		
		
							
	}
	
	
	public void measure(AOMField field)
	{
		AOMClass clazz = field.getOwner();
		
//		int nom = getInt(clazz.getMeasuredDataSet().get(NOM));
//		int noa = getInt(clazz.getMeasuredDataSet().get(NOA));
//		noa++;
//		nom++;
//		clazz.getMeasuredDataSet().put(NOA, noa);
//		clazz.getMeasuredDataSet().put(NOM, nom);
			
		increase(clazz.getMeasuredDataSet().get(NOM), 1);
		increase(clazz.getMeasuredDataSet().get(NOA), 1);
	}
}
