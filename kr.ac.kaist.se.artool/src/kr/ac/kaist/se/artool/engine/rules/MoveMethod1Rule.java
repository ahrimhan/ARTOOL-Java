package kr.ac.kaist.se.artool.engine.rules;

import java.util.Map;
import java.util.Map.Entry;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.ARToolMain;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;

import org.apache.commons.collections.keyvalue.MultiKey;

public class MoveMethod1Rule extends AbstractRule {
	
	private Map.Entry<MultiKey<AOMMethod>, int[]>[] n_IBDPM;
	private AOMMethod[] aomMethods = new AOMMethod[2];
	
	public MoveMethod1Rule(AbstractObjectModel aom,
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
		
		//aomMethod[1]을 aomMethods[0]를 정의하고 있는 class로 옮긴다.
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
