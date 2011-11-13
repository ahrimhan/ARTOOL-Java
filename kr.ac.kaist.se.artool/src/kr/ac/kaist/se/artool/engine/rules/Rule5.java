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

public class Rule5 extends AbstractRule {

	HashMap<HashSet<AOMClass>, int[]> rule5list;
	HashMap<HashSet<AOMClass>, int[]> rule5list2;
	Map.Entry<HashSet<AOMClass>, int[]>[] sortedRule5list;
	private AOMClass[] aomClasses;
	
	public Rule5(AbstractObjectModel aom, int cutline, int pick) {
		// TODO Auto-generated constructor stub
		super(aom, pick);
		if( (sortedRule5list = ListCache.getInstance().getClassList(getRuleName())) == null )
		{
			rule5list = new HashMap<HashSet<AOMClass>, int[]>();
			rule5list2 = getRule5List();
	
			sortedRule5list = UtilityFunctions.getInstance().__getSortedIBDP(rule5list2, cutline);
			ListCache.getInstance().putClassList(getRuleName(), sortedRule5list);
		}
		aomClasses = sortedRule5list[pick].getKey().toArray(new AOMClass[0]);
		
		
		ClassStat.getDynamicStat().countOnClassEntries(sortedRule5list);
	}

	public HashMap<HashSet<AOMClass>, int[]> getRule5List()
	{
		AOMClass[] source_targetClasses = new AOMClass[2]; 
		
		for( AOMClass clazz : aom.getClasses() )
		{
			if( clazz.getAncestor() != null &&
					(BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMA")) == 0 || 
							BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMA")) == 1) &&
							BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NUIM")) == 0 )
			{

				//20110407
//				source_targetClasses[0] = clazz;
//				source_targetClasses[1] = clazz.getAncestor().get(0);
//				UtilityFunctions.getInstance().increase(rule5list, source_targetClasses[0], source_targetClasses[1]);
				
				source_targetClasses[0] = clazz;
				for( int i = 0; i < clazz.getAncestor().size() ; i++ )
				{
					source_targetClasses[1] = clazz.getAncestor().get(i);
					UtilityFunctions.getInstance().increase(rule5list, source_targetClasses[0], source_targetClasses[1]);
				}
			}
		}
		return rule5list;
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
		return "Rule5";
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return aomClasses[0].getFqdn() + " + " + aomClasses[1].getFqdn(); 
	}

}
