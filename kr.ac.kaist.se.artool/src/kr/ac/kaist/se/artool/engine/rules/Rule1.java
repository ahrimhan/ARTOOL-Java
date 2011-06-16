package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.FitnessFunction;
import kr.ac.kaist.se.artool.engine.refactoring.CollapseClassHierarchyCommand;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringException;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringTransaction;

public class Rule1 extends AbstractRule {

	private Map.Entry<HashSet<AOMClass>, Integer>[] n_IBDPC;
	private AOMClass[] aomClasses;
	
	
	public Rule1(AbstractObjectModel aom,
			Entry<HashSet<AOMClass>, Integer>[] n_IBDPC) {
		super(aom);
		this.n_IBDPC = n_IBDPC;
		aomClasses = n_IBDPC[0].getKey().toArray(new AOMClass[0]);
		
		ClassStat.getDynamicStat().countOnClassEntries(n_IBDPC);
	}



	public RefactoringCommand getCommand()
	{
		if( aomClasses.length != 2 ) 
		{
			System.err.println("Error");
			return null;
		}
		
		//constraint
//		if( !aomClasses[0].getAncestor().contains(aomClasses[1]) &&
//				!aomClasses[1].getAncestor().contains(aomClasses[0]) )
//		{
//			System.err.println(aomClasses[0]+" and "+aomClasses[1]+" are not adjacent. (i.e., not parent-child relation)");
//			return null;
//		}
//				
		CollapseClassHierarchyCommand mcc = new CollapseClassHierarchyCommand(aom, aomClasses[0], aomClasses[1]);
		return mcc;
	}
	
	@Override
	public String getName()
	{
		return "Rule1";
	}
	
	@Override
	public String getStatus()
	{
		return aomClasses[0].getFqdn() + " + " + aomClasses[1].getFqdn(); 
	}
}

