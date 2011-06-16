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

public class Rule6_Static extends AbstractRule {

	EMap<HashSet<AOMClass>, Integer> rule6list;
	EMap<HashSet<AOMClass>, Integer> rule6list2;
	Map.Entry<HashSet<AOMClass>, Integer>[] sortedRule6list;
	private AOMClass[] aomClasses;
	private AOMClass newParentRelocatingClass;
	
	public AOMClass getNewParentRelocatingClass() {
		return newParentRelocatingClass;
	}

	public void setNewParentRelocatingClass(AOMClass newParentRelocatingClass) {
		this.newParentRelocatingClass = newParentRelocatingClass;
	}

	public Rule6_Static(AbstractObjectModel aom, int cutline) {
		// TODO Auto-generated constructor stub
		super(aom);
		rule6list = new BasicEMap<HashSet<AOMClass>, Integer>();
		rule6list2 = getRule6List();
		newParentRelocatingClass = null;

		sortedRule6list = UtilityFunctions.getInstance().__getSortedIBDP(rule6list2, cutline);
		aomClasses = sortedRule6list[0].getKey().toArray(new AOMClass[0]);
		
		ClassStat.getStaticStat().countOnClassEntries(sortedRule6list);

	}

	public EMap<HashSet<AOMClass>, Integer> getRule6List()
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
	public String getName() {
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
