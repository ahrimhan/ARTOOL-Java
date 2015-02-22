package kr.ac.kaist.se.artool.engine.rules;

import java.util.Map;
import java.util.Map.Entry;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.ARToolMain;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;

import org.apache.commons.collections4.keyvalue.MultiKey;

public class MoveMethod2Rule extends AbstractRule {
	private Map.Entry<MultiKey<AOMMethod>, int[]>[] n_IBDPM;
	private AOMMethod[] aomMethods = new AOMMethod[2];
	
	public MoveMethod2Rule(AbstractObjectModel aom,
			Entry<MultiKey<AOMMethod>, int[]>[] n_IBDPM, int pick, String ruleName, int mode) {
		super(aom, pick, ruleName);
		this.n_IBDPM = n_IBDPM;
		Object[] obj = n_IBDPM[pick].getKey().getKeys();
		aomMethods[0] = (AOMMethod)obj[0];
		aomMethods[1] = (AOMMethod)obj[1];		
		switch( mode )
		{
		case ARToolMain.DYNAMIC_MODE:
			ClassStat.getDynamicStat().countOnMethodEntries(n_IBDPM[pick]);
			break;
		case ARToolMain.STATIC_MODE:
			ClassStat.getStaticStat().countOnMethodEntries(n_IBDPM[pick]);
			break;
		case ARToolMain.DYNAMIC_STATIC_MODE:
			ClassStat.getDynamicStaticStat().countOnMethodEntries(n_IBDPM[pick]);
			break;
		}
	}
	public RefactoringCommand getCommand()
	{
		if( aomMethods.length != 2 )
		{
			System.err.println("Error");
			return null;
		}
		
		//aomMethod[1]�� aomMethods[0]�� �����ϰ� �ִ� class�� �ű��.
		MoveMethodCommand mmc = new MoveMethodCommand(aomMethods[0], aomMethods[1].getOwner());
		return mmc;
	}
	
	
	@Override
	public String getStatus()
	{
		return aomMethods[1].getOwner().getFqdn() + " <- " + aomMethods[0].getName(); 
	}
}
