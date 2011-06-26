package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

public class Rule9 extends AbstractRule {

	
	EMap<HashSet<AOMMethod>, Integer> rule9list;
	EMap<HashSet<AOMMethod>, Integer> rule9list2;
	Map.Entry<HashSet<AOMMethod>, Integer>[] sortedRule9list;
	private AOMMethod[] aomMethods;
	
	//dccm (different classes containing consecutive calling methods)
	public Rule9(AbstractObjectModel aom, int cutline, int pick) {
		// TODO Auto-generated constructor stub
		super(aom, pick);
		if( (sortedRule9list = ListCache.getInstance().getMethodList(getRuleName())) == null )
		{
			rule9list = new BasicEMap<HashSet<AOMMethod>, Integer>();
			rule9list2 = getRule9List();
			
			sortedRule9list = UtilityFunctions.getInstance().__getSortedIBDP(rule9list2, cutline);
			ListCache.getInstance().putMethodList(getRuleName(), sortedRule9list);
		}
		
		if( sortedRule9list != null && sortedRule9list.length > 0 )
		{
			aomMethods = sortedRule9list[pick].getKey().toArray(new AOMMethod[0]);
		}
		ClassStat.getDynamicStat().countOnMethodEntries(sortedRule9list);
	}
	
	public int getNumPreviousConsecutiveCalls( DynamicMethodCall dmc )
	{	

		int numPreviousConsecutiveCalls = 0;
		
		if( dmc.getPreviousCall() != null &&
				!dmc.getCallee().getOwnedScope().getOwner().equals(dmc.getCaller().getOwner()) )
			//not recursive call
		{
			numPreviousConsecutiveCalls =
			getNumPreviousConsecutiveCalls( dmc.getPreviousCall() ) + 1;
		}
		
		return numPreviousConsecutiveCalls;
	}
	
	public int getNumFollowingConsecutiveCalls( DynamicMethodCall dmc )
	{	

		int max = 1;
		
		if( dmc.getNextCalls() != null &&
				!dmc.getCallee().getOwnedScope().getOwner().equals(dmc.getCaller().getOwner()) )
			//not recursive call
		{
			for( DynamicMethodCall dmc2 : dmc.getNextCalls() )
			{
				int numFollowingConsecutiveCalls =
					getNumFollowingConsecutiveCalls( dmc2 ) + 1;
								
				if( numFollowingConsecutiveCalls > max )
				{
					max = numFollowingConsecutiveCalls;
				}
			}
			
		}
		
		return max;
	}
	
	public Vector<DynamicMethodCall> getListFollowingConsecutiveCalls( DynamicMethodCall dmc )
	{	

		Vector<DynamicMethodCall> maxVisitedDynamicMethodCalls = new Vector<DynamicMethodCall>();
		maxVisitedDynamicMethodCalls.add( dmc );
		
		if( dmc.getNextCalls() != null &&
				!dmc.getCallee().getOwnedScope().getOwner().equals(dmc.getCaller().getOwner()) )
			//not recursive call
		{
			for( DynamicMethodCall dmc2 : dmc.getNextCalls() )
			{
				Vector<DynamicMethodCall> visitedDynamicMethodCalls = new Vector<DynamicMethodCall>();
				visitedDynamicMethodCalls.addAll(getListFollowingConsecutiveCalls( dmc2 ));
				visitedDynamicMethodCalls.add( dmc2 );
								
				if( visitedDynamicMethodCalls.size() > maxVisitedDynamicMethodCalls.size() )
				{
					maxVisitedDynamicMethodCalls.clear();
					maxVisitedDynamicMethodCalls.addAll(visitedDynamicMethodCalls);
				}
			}
			
		}
		return maxVisitedDynamicMethodCalls;
	}
	
	
	public EMap<HashSet<AOMMethod>, Integer> getRule9List()
	{
		AOMMethod[] source_targetMethods = new AOMMethod[2]; 
		
		for( AOMClass clazz : aom.getClasses() )
		{			
			for( AOMMethod method : clazz.getMethods())
			{		
						
				if( method.getOwnedScope() != null )
				{
					for( DynamicMethodCall dmc : method.getOwnedScope().getDynamicMethodCalls() )
					{
						//int numConsecutiveCalls = getNumFollowingConsecutiveCalls(dmc);
						Vector<DynamicMethodCall> visitedListFollowingConsecutiveCalls = 
							new Vector<DynamicMethodCall>( getListFollowingConsecutiveCalls( dmc ) );
												
						if( visitedListFollowingConsecutiveCalls.size() >= 3)
						{
							for( DynamicMethodCall dmc2 : visitedListFollowingConsecutiveCalls )
							{
								source_targetMethods[0] = dmc2.getCaller().getOwner();
								source_targetMethods[1] = dmc2.getCallee();
								
								if(source_targetMethods[0] != null && source_targetMethods[1] != null)
								{
									UtilityFunctions.getInstance().increase(rule9list, source_targetMethods[0], source_targetMethods[1]);
								}
							}
						}
					}
				}
			}
		}
		return rule9list;
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
		return "Rule9";
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		//return aomClasses[0].getFqdn() + " relocated from " + aomClasses[1].getFqdn() + " to " + newParentRelocatingClass.getFqdn(); 
		
		return aomMethods[0].getOwner().getFqdn() + " <- " + aomMethods[1].getName(); 
	}

}
