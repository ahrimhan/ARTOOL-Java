package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.FitnessFunction;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringException;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringTransaction;

public class MoveMethod1Rule extends AbstractRule {
	
	private Map.Entry<HashSet<AOMMethod>, int[]>[] n_IBDPM;
	private AOMMethod[] aomMethods;
	
	public MoveMethod1Rule(AbstractObjectModel aom,
			Entry<HashSet<AOMMethod>, int[]>[] n_IBDPM, int pick, String ruleName, boolean isDynamic) {
		super(aom, pick, ruleName);
		this.n_IBDPM = n_IBDPM;
		aomMethods = n_IBDPM[pick].getKey().toArray(new AOMMethod[0]);
		if( isDynamic )
			ClassStat.getDynamicStat().countOnMethodEntries(n_IBDPM);
		else
			ClassStat.getStaticStat().countOnMethodEntries(n_IBDPM);
	}


	public RefactoringCommand getCommand()
	{
		
		if( aomMethods.length != 2 )
		{
			System.err.println("Error");
			return null;
		}
		
		//aomMethod[1]�� aomMethods[0]�� �����ϰ� �ִ� class�� �ű��.
		MoveMethodCommand mmc = new MoveMethodCommand(aomMethods[1], aomMethods[0].getOwner());
		return mmc;
	}
	
	
	@Override
	public String getStatus()
	{
		//return "Class "+aomMethods[0].getOwner().getFqdn() + " <- " + "Method "+aomMethods[1].getName();
		return aomMethods[0].getOwner().getFqdn() + " <- " + aomMethods[1].getName();
		
	}
}
