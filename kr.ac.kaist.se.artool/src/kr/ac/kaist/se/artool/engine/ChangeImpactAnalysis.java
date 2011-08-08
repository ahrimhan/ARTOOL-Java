package kr.ac.kaist.se.artool.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class ChangeImpactAnalysis {
	
	private static ChangeImpactAnalysis instance;
	protected AbstractObjectModel aom;
	private int fieldImpactedMode = 1;
	//true when you want 2 steps impact analysis
//	private boolean stepTwo = true;
	private boolean stepTwo = true;
	//fieldImpactedMode 1 (only the method that is referring the field.)
	//fieldImpactedMode 2 (include propagated methods from the method that is referring the field.)
	
	
	public static ChangeImpactAnalysis getInstance()
	//(AbstractObjectModel aom)
	{
		
		//String changeFile = "./resultARTool/modifiedMethod.csv";
		
		if( instance == null )
		{
			instance = new ChangeImpactAnalysis();
			
		}
		
		//init(aom, changeFile);
		return instance;
	}
	
	
	private ChangeImpactAnalysis()
	{
	}
	
	public boolean isStepTwo() {
		return stepTwo;
	}

	public void setStepTwo(boolean stepTwo) {
		this.stepTwo = stepTwo;
	}

	public int numImpactedClasses(String methodSignature)
	{
		return 0;
	}
	
		
	public void analysisOnMethod(AOMMethod method, HashSet<AOMMethod> firstImpact, HashSet<AOMMethod> secondImpact)
	{
//		HashSet<AOMMethod> firstImpact = new HashSet<AOMMethod>();
//		HashSet<AOMMethod> secondImpact = new HashSet<AOMMethod>();
		this.getListImpactedMethods(method, firstImpact, secondImpact);
	}

	public void analysisOnClass(AOMMethod method, HashSet<AOMClass> firstImpact, HashSet<AOMClass> secondImpact )
	{
		this.getListImpactedClasses(method, firstImpact, secondImpact);
	}
	
	public double analysisOnMethod()
	{
		HashSet<AOMMethod> firstImpact = new HashSet<AOMMethod>();
		HashSet<AOMMethod> secondImpact = new HashSet<AOMMethod>();
		for( AOMMethod method : changedMethod )
		{
			analysisOnMethod(method, firstImpact, secondImpact);
		}

//		for( AOMClass clazz : aom.getClasses() )
//		{
//			for( AOMMethod method : clazz.getMethods() )
//			{
//				ret += analysisOnMethod(method);
//			}
//		}
		
		return firstImpact.size() + secondImpact.size() * 0.0;
	}
	
	public double analysisOnClass()
	{
		HashSet<AOMClass> firstImpact = new HashSet<AOMClass>();
		HashSet<AOMClass> secondImpact = new HashSet<AOMClass>();

		for( AOMMethod method : changedMethod )
		{
			analysisOnClass(method, firstImpact, secondImpact);
		}

		return firstImpact.size() + secondImpact.size() * 0.0;
	}
	
	private AOMMethod findMethod(String className, String methodName, int lineNumber)
	{
		for(AOMClass clazz : aom.getClasses() )
		{
			if( clazz.getFqdn().equals(className) )
			{
				for( AOMMethod method : clazz.getMethods() )
				{
					if( method.getName().equals(methodName) 
//							&&
//							method.getStartLine() - 1 <= lineNumber 
//							&&
//							method.getStartLine() + 1 >= lineNumber 
							)
					{
						return method;
					}
				}
			}
		}
		
		return null;
	}
	
	public void getListImpactedMethods(AOMMethod method, HashSet<AOMMethod> firstImpact, HashSet<AOMMethod> secondImpact)
	{
		AOMClass caller_class = null;
		int i = 0;
		
		for( StaticMethodCall smc1 : method.getStaticReferer() )
		{
			if( smc1.getCaller().getOwner() != method &&
					smc1.getCaller().getOwner().getOwner() != method.getOwner() )
			{
				AOMMethod caller_method1 = smc1.getCaller().getOwner();
				firstImpact.add(caller_method1);
				
				if(stepTwo)
				{
					for ( StaticMethodCall smc2 : caller_method1.getStaticReferer() )
					{
						if( smc2.getCaller().getOwner() != caller_method1 &&
								smc2.getCaller().getOwner().getOwner() != caller_method1.getOwner())
						{
							AOMMethod caller_method2 = smc2.getCaller().getOwner();
							secondImpact.add(caller_method2);
						}
					}
				}
			
			}
		
		}
		
	}

	
	public void getListImpactedClasses(AOMMethod method, HashSet<AOMClass> firstImpact, HashSet<AOMClass> secondImpact)
	{
		HashSet<AOMMethod> firstImpactMethod = new HashSet<AOMMethod>();
		HashSet<AOMMethod> secondImpactMethod = new HashSet<AOMMethod>();
		getListImpactedMethods( method, firstImpactMethod, secondImpactMethod );
		
		for( AOMMethod am : firstImpactMethod)
		{
			if( am.getOwner() != method.getOwner())
			{
				firstImpact.add(am.getOwner());
			}			
		}
		
		for( AOMMethod am : secondImpactMethod)
		{
			if( am.getOwner() != method.getOwner())
			{
				secondImpact.add(am.getOwner());
			}			
		}
		
		return;
	}
	
//	public Vector<AOMMethod> getListWithoutRedundantMethods( Vector<AOMMethod> list )
//	{
//		Vector<AOMMethod> listImpactedMethods = new Vector<AOMMethod>();
//		
//		for( AOMMethod am : list)
//		{
//			if( !listImpactedMethods.contains(am) )
//			{
//				listImpactedMethods.add(am);
//			}
//					
//		}
//		return listImpactedMethods;
//	}
	
	private Vector<AOMMethod> changedMethod = new Vector<AOMMethod>();
	
	public void init(AbstractObjectModel aom, String filename)
	{
		int ret = 0;
		int missingNum = 0;
		changedMethod.clear();
		this.aom = aom;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			for( String line = reader.readLine(); line != null; line = reader.readLine() )
			{
				String[] str = line.split(",");
				AOMMethod method = findMethod(str[0], str[1], Integer.parseInt(str[2]));
				
				if( method == null )
				{
//					System.err.println("className:" + str[0] + " methodName:" + str[1]);
					missingNum++;
					continue;
				}
				changedMethod.add(method);
			}
			reader.close();
			return ;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} finally
		{
			System.err.println("Missing Num:" + missingNum);
		}
	}
}
