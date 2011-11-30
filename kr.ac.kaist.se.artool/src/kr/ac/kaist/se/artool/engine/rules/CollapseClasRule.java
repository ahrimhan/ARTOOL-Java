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

public class CollapseClasRule extends AbstractRule {

	private Map.Entry<HashSet<AOMClass>, int[]>[] n_IBDPC;
	private AOMClass[] aomClasses;
	
	
	public CollapseClasRule(AbstractObjectModel aom,
			Entry<HashSet<AOMClass>, int[]>[] n_IBDPC, int pick, String ruleName, boolean isDynamic) {
		super(aom, pick, ruleName);
		this.n_IBDPC = n_IBDPC;
		aomClasses = n_IBDPC[pick].getKey().toArray(new AOMClass[0]);
		if( isDynamic ) 
			ClassStat.getDynamicStat().countOnClassEntries(n_IBDPC);
		else
			ClassStat.getStaticStat().countOnClassEntries(n_IBDPC);

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
	public String getStatus()
	{
		return aomClasses[0].getFqdn() + " + " + aomClasses[1].getFqdn(); 
	}
}

