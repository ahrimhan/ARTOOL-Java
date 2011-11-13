package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.FitnessFunction;
import kr.ac.kaist.se.artool.engine.metrics.N_DCICM_Dynamic;
import kr.ac.kaist.se.artool.engine.refactoring.CollapseClassHierarchyCommand;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringException;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringTransaction;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

public class Rule4 extends AbstractRule {

	private Map.Entry<HashSet<AOMClass>, int[]>[] n_IBDPC;
	private AOMClass[] aomClasses;
	
	
	public Rule4(AbstractObjectModel aom,
			Entry<HashSet<AOMClass>, int[]>[] n_DCICM,int pick) {
		super(aom, pick);
		this.n_IBDPC = n_DCICM;
		aomClasses = n_DCICM[pick].getKey().toArray(new AOMClass[0]);
		
		ClassStat.getDynamicStat().countOnClassEntries(n_DCICM);
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
	public String getRuleName()
	{
		return "Rule4";
	}
	
	@Override
	public String getStatus()
	{
		return aomClasses[0].getFqdn() + " + " + aomClasses[1].getFqdn(); 
	}
}

