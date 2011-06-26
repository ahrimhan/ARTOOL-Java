package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

public class Rule9_Static extends AbstractRule {

	
	EMap<HashSet<AOMMethod>, Integer> rule9list;
	EMap<HashSet<AOMMethod>, Integer> rule9list2;
	Map.Entry<HashSet<AOMMethod>, Integer>[] sortedRule9list;
	private AOMMethod[] aomMethods;
	
	
	public Rule9_Static(AbstractObjectModel aom, int cutline, int pick) {
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
		ClassStat.getStaticStat().countOnMethodEntries(sortedRule9list);
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
	
	public int getNumFollowingConsecutiveCalls( StaticMethodCall smc )
	{	

		int max = 1;
		
		if(  smc.getCallee().getOwnedScope().getStaticMethodCalls() != null &&
				!smc.getCallee().getOwnedScope().getOwner().equals(smc.getCaller().getOwner()) )
			//not recursive call
		{
			for( StaticMethodCall smc2 : smc.getCallee().getOwnedScope().getStaticMethodCalls() )
			{
				int numFollowingConsecutiveCalls =
					getNumFollowingConsecutiveCalls( smc2 ) + 1;
								
				if( numFollowingConsecutiveCalls > max )
				{
					max = numFollowingConsecutiveCalls;
				}
			}
			
		}
		
		return max;
	}
	
	public Vector<StaticMethodCall> getListFollowingConsecutiveCalls( StaticMethodCall smc )
	{	
		return _getListFollowingConsecutiveCalls(smc, new Vector<StaticMethodCall>());
	}
	
	private Vector<StaticMethodCall> _getListFollowingConsecutiveCalls( StaticMethodCall smc, Vector<StaticMethodCall> vmc)
	{	
		if( vmc.contains(smc) )
		{
			return new Vector<StaticMethodCall>();
		}
		vmc.add(smc);
		
		Vector<StaticMethodCall> maxVisitedStaticMethodCalls = new Vector<StaticMethodCall>();
		maxVisitedStaticMethodCalls.add( smc );
		
		if( smc.getCallee().getOwnedScope() != null &&
				smc.getCallee().getOwnedScope().getStaticMethodCalls() != null &&
				!smc.getCallee().getOwnedScope().getOwner().equals( smc.getCaller().getOwner()) )
				// not recursive call
		{
			for( StaticMethodCall smc2 : smc.getCallee().getOwnedScope().getStaticMethodCalls() )
			{
				Vector<StaticMethodCall> visitedStaticMethodCalls = new Vector<StaticMethodCall>();
				visitedStaticMethodCalls.addAll(_getListFollowingConsecutiveCalls( smc2, vmc));
				visitedStaticMethodCalls.add( smc2 );
								
				if( visitedStaticMethodCalls.size() > maxVisitedStaticMethodCalls.size() )
				{
					maxVisitedStaticMethodCalls.clear();
					maxVisitedStaticMethodCalls.addAll(visitedStaticMethodCalls);
				}
			}
			
		}
		return maxVisitedStaticMethodCalls;
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
					for( StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
					{
						Vector<StaticMethodCall> visitedListFollowingConsecutiveCalls = 
							new Vector<StaticMethodCall>( getListFollowingConsecutiveCalls( smc ) );
												 
						if( visitedListFollowingConsecutiveCalls.size() >= 3)
						{
							for( StaticMethodCall smc2 : visitedListFollowingConsecutiveCalls )
							{
								source_targetMethods[0] = smc2.getCaller().getOwner();
								source_targetMethods[1] = smc2.getCallee();
								
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
