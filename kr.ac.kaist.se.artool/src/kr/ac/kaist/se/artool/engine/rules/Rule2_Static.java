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

public class Rule2_Static extends AbstractRule {
	
	private Map.Entry<HashSet<AOMMethod>, Integer>[] n_IBDPM;
	private AOMMethod[] aomMethods;
	
	public Rule2_Static(AbstractObjectModel aom,
			Entry<HashSet<AOMMethod>, Integer>[] n_IBDPM, int pick) {
		super(aom, pick);
		this.n_IBDPM = n_IBDPM;
		aomMethods = n_IBDPM[pick].getKey().toArray(new AOMMethod[0]);
		
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
	public String getRuleName()
	{
		return "Rule2";
	}
	
	@Override
	public String getStatus()
	{
		//return "Class "+aomMethods[0].getOwner().getFqdn() + " <- " + "Method "+aomMethods[1].getName();
		return aomMethods[0].getOwner().getFqdn() + " <- " + aomMethods[1].getName();
		
	}
}
