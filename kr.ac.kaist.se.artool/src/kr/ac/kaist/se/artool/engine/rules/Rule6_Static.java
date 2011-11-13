package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.engine.metrics.BasicMetricSuite;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RelocateGeneralizationCommand;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

public class Rule6_Static extends AbstractRule {

	HashMap<HashSet<AOMClass>, int[]> rule6list;
	HashMap<HashSet<AOMClass>, int[]> rule6list2;
	Map.Entry<HashSet<AOMClass>, int[]>[] sortedRule6list;
	private AOMClass[] aomClasses;
	private AOMClass newParentRelocatingClass;
	
	public AOMClass getNewParentRelocatingClass() {
		return newParentRelocatingClass;
	}

	public void setNewParentRelocatingClass(AOMClass newParentRelocatingClass) {
		this.newParentRelocatingClass = newParentRelocatingClass;
	}

	public Rule6_Static(AbstractObjectModel aom, int cutline, int pick) {
		// TODO Auto-generated constructor stub
		super(aom, pick);
		newParentRelocatingClass = null;
		if( (sortedRule6list = ListCache.getInstance().getClassList(getRuleName())) == null )
		{
			rule6list = new HashMap<HashSet<AOMClass>, int[]>();
			rule6list2 = getRule6List();
			
			sortedRule6list = UtilityFunctions.getInstance().__getSortedIBDP(rule6list2, cutline);
			ListCache.getInstance().putClassList(getRuleName(), sortedRule6list);
		}
		aomClasses = sortedRule6list[pick].getKey().toArray(new AOMClass[0]);
		
		ClassStat.getStaticStat().countOnClassEntries(sortedRule6list);

	}

	public HashMap<HashSet<AOMClass>, int[]> getRule6List()
	{
		AOMClass[] source_targetClasses = new AOMClass[2]; 
		
		for( AOMClass clazz : aom.getClasses() )
		{
			if( clazz.getAncestor() != null &&
					(BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMA")) == 0 || 
							BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMA")) == 1) &&
							BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMI")) == 0 )
			{
				source_targetClasses[0] = clazz;
				for( int i = 0; i < clazz.getAncestor().size() ; i++ )
				{
					source_targetClasses[1] = clazz.getAncestor().get(i);
					UtilityFunctions.getInstance().increase(rule6list, source_targetClasses[0], source_targetClasses[1]);
				}
			}
		}
		return rule6list;
	}
	
	@Override
	public RefactoringCommand getCommand() {
		
		if( aomClasses.length != 2 ) 
		{
			System.err.println("Error");
			return null;
		}
		
		AOMClass tempNewParentRelocatingClass = null;
		
		for( int j = 0; j < sortedRule6list.length ; j++)
		{
			aomClasses = sortedRule6list[j].getKey().toArray(new AOMClass[0]);
			for( int i = 0; i < aomClasses[0].getAncestor().size(); i++)
			{
				tempNewParentRelocatingClass = aomClasses[0].getAncestor().get(i);
				if( !tempNewParentRelocatingClass.equals(aomClasses[1]) && tempNewParentRelocatingClass != null )
				{
					setNewParentRelocatingClass(tempNewParentRelocatingClass);
					RelocateGeneralizationCommand rgc = 
						new RelocateGeneralizationCommand(aom, aomClasses[0], aomClasses[1], tempNewParentRelocatingClass);
					return rgc;
				}
			}
		}
	
		return null;
	}

	@Override
	public String getRuleName() {
		// TODO Auto-generated method stub
		return "Rule6";
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		if( getCommand() != null ){
			return 
			aomClasses[0].getFqdn() + " relocated from " + 
			aomClasses[1].getFqdn() + " to " + getNewParentRelocatingClass().getFqdn(); 
		}
		
		return "N/A";
	}

}
