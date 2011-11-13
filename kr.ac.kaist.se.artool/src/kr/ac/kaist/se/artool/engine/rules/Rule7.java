package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.engine.metrics.BasicMetricSuite;
import kr.ac.kaist.se.artool.engine.refactoring.CollapseClassHierarchyCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

public class Rule7 extends AbstractRule {

	
	HashMap<HashSet<AOMClass>, int[]> rule7list;
	HashMap<HashSet<AOMClass>, int[]> rule7list2;
	Map.Entry<HashSet<AOMClass>, int[]>[] sortedRule7list;
	private AOMClass[] aomClasses;
	
	public Rule7(AbstractObjectModel aom, int cutline, int pick) {
		// TODO Auto-generated constructor stub
		super(aom, pick);
		if( (sortedRule7list = ListCache.getInstance().getClassList(getRuleName())) == null )
		{
			rule7list = new HashMap<HashSet<AOMClass>, int[]>();
			rule7list2 = getRule7List();
	
			sortedRule7list = UtilityFunctions.getInstance().__getSortedIBDP(rule7list2, cutline);
			ListCache.getInstance().putClassList(getRuleName(), sortedRule7list);
		}
		
		if( sortedRule7list != null && sortedRule7list.length > 0 )
		{
			aomClasses = sortedRule7list[pick].getKey().toArray(new AOMClass[0]);
		}
		ClassStat.getDynamicStat().countOnClassEntries(sortedRule7list);
	}

	public HashMap<HashSet<AOMClass>, int[]> getRule7List()
	{
		AOMClass[] source_targetClasses = new AOMClass[2]; 
		
		for( AOMClass clazz : aom.getClasses() )
		{
			if( clazz.getDescendant() != null && clazz.getAncestor() != null &&
					BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMA")) == 0 && 
					BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMO")) != 0 &&
					(BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NUOM")) == 0 || 
							BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NUOM")) == 1 ))
			{
//				source_targetClasses[0] = clazz;
//				for( int i = 0; i < clazz.getAncestor().size(); )
//				{
//					if( clazz.getAncestor().get(i).getAncestor() != null)
//					{
//						source_targetClasses[1] = clazz.getAncestor().get(i);
//						i++;
//						UtilityFunctions.getInstance().increase(rule7list, source_targetClasses[0], source_targetClasses[1]);
//					}
//					
//				}
				
				if( clazz.getAncestor().size() > 0)
				{
					source_targetClasses[0] = clazz;
					source_targetClasses[1] = clazz.getAncestor().get(0);
					UtilityFunctions.getInstance().increase(rule7list, source_targetClasses[0], source_targetClasses[1]);
				}
				
				
//				for( int i = 0; i <  clazz.getAncestor().size()- 1 ; )
//				{
//					source_targetClasses[0] = aomClasses[i];
//					source_targetClasses[1] = aomClasses[++i];
//					UtilityFunctions.getInstance().increase(rule7list, source_targetClasses[0], source_targetClasses[1]);
//				}

			}
		}
		return rule7list;
	}
	
	@Override
	public RefactoringCommand getCommand() {
		
		if( aomClasses.length != 2 ) 
		{
			System.err.println("Error");
			return null;
		}
		
		CollapseClassHierarchyCommand mcc = new CollapseClassHierarchyCommand(aom, aomClasses[0], aomClasses[1]);
		return mcc;
	}

	@Override
	public String getRuleName() {
		// TODO Auto-generated method stub
		return "Rule7";
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return aomClasses[0].getFqdn() + " + " + aomClasses[1].getFqdn(); 
	}

}
