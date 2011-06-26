package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.CollapseClassHierarchyCommand;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RelocateGeneralizationCommand;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

public class Rule10 extends AbstractRule {

	
	EMap<HashSet<AOMMethod>, Integer> rule10list;
	EMap<HashSet<AOMMethod>, Integer> rule10list2;
	Map.Entry<HashSet<AOMMethod>, Integer>[] sortedRule10list;
	private AOMMethod[] aomMethods;
	
	
	public Rule10(AbstractObjectModel aom, int cutline, int pick) {
		// TODO Auto-generated constructor stub
		super(aom, pick);
		
		if( (sortedRule10list = ListCache.getInstance().getMethodList(getRuleName())) == null )
		{
			rule10list = new BasicEMap<HashSet<AOMMethod>, Integer>();
			rule10list2 = getRule10List();
			sortedRule10list = UtilityFunctions.getInstance().__getSortedIBDP(rule10list2, cutline);
			ListCache.getInstance().putMethodList(getRuleName(), sortedRule10list);
		}
		
		aomMethods = sortedRule10list[pick].getKey().toArray(new AOMMethod[0]);
		
		ClassStat.getDynamicStat().countOnMethodEntries(sortedRule10list);
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
	
	
	public int getDistanceFromCallingTypeToActualImpl(AOMClass callingTypeClass, AOMClass actualImplementingClass)
	{
		int parentDistance =  _getDistanceFromCallingTypeToActualImpl1(callingTypeClass, actualImplementingClass, new HashSet<AOMClass>());
		int childDistance = _getDistanceFromCallingTypeToActualImpl2(callingTypeClass, actualImplementingClass, new HashSet<AOMClass>());
		
		if( childDistance > parentDistance )
		{
			return childDistance;
		}
		else
		{
			return parentDistance;
		}
	}
	
//	private EMap<AOMClass,Integer> _getDistanceClassSetFromCallingTypeToActualImpl1(AOMClass callingTypeClass, AOMClass actualImplementingClass, HashSet<AOMClass> visitedClasses)
//	{
//		EMap<AOMClass, Integer> targetDistance = new BasicEMap<AOMClass, Integer>();
//		
//		int distance = -1;
//		
//		if( visitedClasses.contains(callingTypeClass) )
//		{
//			targetDistance.put(callingTypeClass, -1); 
//			return targetDistance;
//			 //return -1;
//		}
//		visitedClasses.add(callingTypeClass);
//
//		if( callingTypeClass.equals(actualImplementingClass) )
//		{
//			targetDistance.put(callingTypeClass, 0); 
//			return targetDistance;
//			//return 0;
//		}
//		
//		for ( AOMClass clazz2 : callingTypeClass.getAncestor() )
//		{
//			int parentsDistance = _getDistanceClassSetFromCallingTypeToActualImpl1(clazz2, actualImplementingClass, visitedClasses).get(clazz2);
//			
//			if( parentsDistance < 0 ) continue; //if parent = null or if parent is already visited
//			
//			parentsDistance++;
//			
//			if( parentsDistance > distance ) //select max among the distance of parent
//			{
//				distance = parentsDistance;
//				
//			}
//				
//		}
//		
//		return targetDistance;	
//	}
//	 
	private int _getDistanceFromCallingTypeToActualImpl1(AOMClass callingTypeClass, AOMClass actualImplementingClass, HashSet<AOMClass> visitedClasses)
	{
		int distance = -1;
		
		if( visitedClasses.contains(callingTypeClass) )
		{
			return -1;
		}
		visitedClasses.add(callingTypeClass);

		if( callingTypeClass.equals(actualImplementingClass) )
		{
			return 0;
		}
		
		for ( AOMClass clazz2 : callingTypeClass.getAncestor() )
		{
			int parentsDistance = _getDistanceFromCallingTypeToActualImpl1(clazz2, actualImplementingClass, visitedClasses);
			
			if( parentsDistance < 0 ) continue; //if parent = null or if parent is already visited
			
			parentsDistance++;
			
			if( parentsDistance > distance ) //select max among the distance of parent
			{
				distance = parentsDistance;
			}
				
		}
		
		return distance;	
	}
		
	
	private int _getDistanceFromCallingTypeToActualImpl2(AOMClass callingTypeClass, AOMClass actualImplementingClass, HashSet<AOMClass> visitedClasses)
	{
		int distance = -1;
		
		if( visitedClasses.contains(callingTypeClass) )
		{
			return -1;
		}
		visitedClasses.add(callingTypeClass);

		if( callingTypeClass.equals(actualImplementingClass) )
		{
			return 0;
		}
		
		for ( AOMClass clazz2 : callingTypeClass.getDescendant() )
		{
			int childDistance = _getDistanceFromCallingTypeToActualImpl2(clazz2, actualImplementingClass, visitedClasses);
			
			if( childDistance < 0 ) continue;
			
			childDistance++;
			
			if( childDistance > distance )
			{
				distance = childDistance;
			}
		}
		
		return distance;	
	}
	
		
	
	
	public EMap<HashSet<AOMMethod>, Integer> getRule10List()
	{
		AOMMethod[] source_targetMethods = new AOMMethod[2]; 
		AOMClass actualImplementingClass;
		AOMClass callingTypeClass;
		int DFAIM = 0; //distance for actual impemented method
		
		for( AOMClass clazz : aom.getClasses() )
		{			
			for( AOMMethod method : clazz.getMethods())
			{		
						
				if( method.getOwnedScope() != null )
				{
					for( DynamicMethodCall dmc : method.getOwnedScope().getDynamicMethodCalls() )
					{
						if( dmc.getStatic() != null )
						{
							actualImplementingClass = dmc.getCallee().getOwner();
							callingTypeClass = dmc.getStatic().getCallingType();
							
							DFAIM = getDistanceFromCallingTypeToActualImpl(callingTypeClass, actualImplementingClass);
						}
					
						
						//if DFAIM = 0, then callingTypeClass = actualImplementingClass
						if( DFAIM >= 2)
						{
							source_targetMethods[0] = dmc.getCaller().getOwner();
							source_targetMethods[1] = dmc.getCallee();
							
							if(source_targetMethods[0] != null && source_targetMethods[1] != null)
							{
								UtilityFunctions.getInstance().increase(rule10list, source_targetMethods[0], source_targetMethods[1]);
							}
							
//							for( int i = 0; i < dmc.getNextCalls().size() ; i++)
//							{
//								source_targetMethods[0] = dmc.getNextCalls().get(i).getCaller().getOwner();
//								source_targetMethods[1] = dmc.getNextCalls().get(i).getCallee();
//								
//								if(source_targetMethods[0] != null && source_targetMethods[1] != null)
//								{
//									UtilityFunctions.getInstance().increase(rule10list, source_targetMethods[0], source_targetMethods[1]);
//								}
//							}
														
						}
					}
				}
			}
		}
		return rule10list;
	}
	
	@Override
	public RefactoringCommand getCommand() {
		if( aomMethods.length != 2 )
		{
			System.err.println("Error");
			return null;
		}
		
		//aomMethod[1]을 aomMethods[0]를 정의하고 있는 class로 옮긴다.
		MoveMethodCommand mmc = new MoveMethodCommand(aomMethods[1], aomMethods[0].getOwner());
		return mmc;
	}

	@Override
	public String getRuleName() {
		// TODO Auto-generated method stub
		return "Rule10";
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		//return aomClasses[0].getFqdn() + " relocated from " + aomClasses[1].getFqdn() + " to " + newParentRelocatingClass.getFqdn(); 
		
		return aomMethods[0].getOwner().getFqdn() + " <- " + aomMethods[1].getName(); 
	}

}
