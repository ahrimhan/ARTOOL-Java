package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.metrics.BasicMetricSuite;
import kr.ac.kaist.se.artool.engine.refactoring.CollapseClassHierarchyCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RelocateGeneralizationCommand;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

public class Rule8 extends AbstractRule {

	
	EMap<HashSet<AOMClass>, Integer> rule8list;
	EMap<HashSet<AOMClass>, Integer> rule8list2;
	Map.Entry<HashSet<AOMClass>, Integer>[] sortedRule8list;
	private AOMClass[] aomClasses;
	private AOMClass newParentRelocatingClass;
	
	public Rule8(AbstractObjectModel aom, int cutline) {
		// TODO Auto-generated constructor stub
		super(aom);
		rule8list = new BasicEMap<HashSet<AOMClass>, Integer>();
		rule8list2 = getRule8List();
		newParentRelocatingClass = null;

		sortedRule8list = UtilityFunctions.getInstance().__getSortedIBDP(rule8list2, cutline);
		if( sortedRule8list != null && sortedRule8list.length > 0 )
		{
			aomClasses = sortedRule8list[0].getKey().toArray(new AOMClass[0]);
		}

		ClassStat.getDynamicStat().countOnClassEntries(sortedRule8list);
	}
	
	public AOMClass getNewParentRelocatingClass() {
		return newParentRelocatingClass;
	}

	public void setNewParentRelocatingClass(AOMClass newParentRelocatingClass) {
		this.newParentRelocatingClass = newParentRelocatingClass;
	}

	public EMap<HashSet<AOMClass>, Integer> getRule8List()
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
				if( clazz.getAncestor().size() > 0)
				{
					source_targetClasses[0] = clazz;
					source_targetClasses[1] = clazz.getAncestor().get(0);
					UtilityFunctions.getInstance().increase(rule8list, source_targetClasses[0], source_targetClasses[1]);
				}
			}

		}
		return rule8list;
	}
	
	@Override
	public RefactoringCommand getCommand() {
		
		if( aomClasses.length != 2 ) 
		{
			System.err.println("Error");
			return null;
		}
		AOMClass tempNewParentRelocatingClass = null;
		
		for( int j = 0; j < sortedRule8list.length ; j++)
		{
			aomClasses = sortedRule8list[j].getKey().toArray(new AOMClass[0]);
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

//		for( int i = 0; i < aomClasses[0].getAncestor().size(); i++)
//		{
//			newParentRelocatingClass = aomClasses[0].getAncestor().get(i);
//			if( !newParentRelocatingClass.equals(aomClasses[1]) )
//			{
//				RelocateGeneralizationCommand rgc = 
//					new RelocateGeneralizationCommand(aom, aomClasses[0], aomClasses[1], newParentRelocatingClass);
//				return rgc;
//			}
//		}
//		return null;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Rule8";
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		//return aomClasses[0].getFqdn() + " relocated from " + aomClasses[1].getFqdn() + " to " + newParentRelocatingClass.getFqdn(); 
		
		if( getCommand() != null ){
			return 
			aomClasses[0].getFqdn() + " relocated from " + 
			aomClasses[1].getFqdn() + " to " + getNewParentRelocatingClass().getFqdn(); 
		}
		
		return "N/A";
		
	}

}

