package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.engine.ARToolMain;
import kr.ac.kaist.se.artool.engine.refactoring.CollapseClassHierarchyCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;

import org.apache.commons.collections.keyvalue.MultiKey;

public class CollapseClasRule extends AbstractRule {

	private Map.Entry<MultiKey<AOMClass>, int[]>[] n_IBDPC;
	private AOMClass[] aomClasses = new AOMClass[2];
	
	
	public CollapseClasRule(AbstractObjectModel aom,
			Entry<MultiKey<AOMClass>, int[]>[] n_IBDPC, int pick, String ruleName, int mode) {
		super(aom, pick, ruleName);
		this.n_IBDPC = n_IBDPC;

		Object[] obj = n_IBDPC[pick].getKey().getKeys();
		aomClasses[0] = (AOMClass)obj[0];
		aomClasses[1] = (AOMClass)obj[1];
				
		switch( mode )
		{
		case ARToolMain.DYNAMIC_MODE:
			ClassStat.getDynamicStat().countOnClassEntries(n_IBDPC[pick]);
			break;
		case ARToolMain.STATIC_MODE:
			ClassStat.getStaticStat().countOnClassEntries(n_IBDPC[pick]);
			break;
		case ARToolMain.DYNAMIC_STATIC_MODE:
			ClassStat.getDynamicStaticStat().countOnClassEntries(n_IBDPC[pick]);
			break;
		}

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

