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

public class Rule5_Static extends AbstractRule {

	EMap<HashSet<AOMClass>, Integer> rule5list;
	EMap<HashSet<AOMClass>, Integer> rule5list2;
	Map.Entry<HashSet<AOMClass>, Integer>[] sortedRule5list;
	private AOMClass[] aomClasses;
	
	public Rule5_Static(AbstractObjectModel aom, int cutline) {
		// TODO Auto-generated constructor stub
		super(aom);
		rule5list = new BasicEMap<HashSet<AOMClass>, Integer>();
		rule5list2 = getRule5List();

		sortedRule5list =
			UtilityFunctions.getInstance().__getSortedIBDP(rule5list2, cutline);
		aomClasses = sortedRule5list[0].getKey().toArray(new AOMClass[0]);
		
		ClassStat.getStaticStat().countOnClassEntries(sortedRule5list);

		
	}

	public EMap<HashSet<AOMClass>, Integer> getRule5List()
	{
		AOMClass[] source_targetClasses = new AOMClass[2]; 
		
		for( AOMClass clazz : aom.getClasses() )
		{
			if( clazz.getAncestor() != null &&
					(BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMA")) == 0 || 
							BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMA")) == 1) &&
							BasicMetricSuite.getInt(clazz.getMeasuredDataSet().get("NMI")) == 0 )
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
	public String getName() {
		// TODO Auto-generated method stub
		return "Rule5";
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return aomClasses[0].getFqdn() + " + " + aomClasses[1].getFqdn(); 
	}

}
