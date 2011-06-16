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

public class Rule3 extends AbstractRule {
	private Map.Entry<HashSet<AOMMethod>, Integer>[] n_IBDPM;
	private AOMMethod[] aomMethods;
	
	public Rule3(AbstractObjectModel aom,
			Entry<HashSet<AOMMethod>, Integer>[] n_IBDPM) {
		super(aom);
		this.n_IBDPM = n_IBDPM;
		aomMethods = n_IBDPM[0].getKey().toArray(new AOMMethod[0]);
		
		ClassStat.getDynamicStat().countOnMethodEntries(n_IBDPM);
	}
	public RefactoringCommand getCommand()
	{
		if( aomMethods.length != 2 )
		{
			System.err.println("Error");
			return null;
		}
		
		//aomMethod[1]을 aomMethods[0]를 정의하고 있는 class로 옮긴다.
		MoveMethodCommand mmc = new MoveMethodCommand(aomMethods[0], aomMethods[1].getOwner());
		return mmc;
	}
	
	@Override
	public String getName()
	{
		return "Rule3";
	}
	
	@Override
	public String getStatus()
	{
		return aomMethods[1].getOwner().getFqdn() + " <- " + aomMethods[0].getName(); 
	}
}