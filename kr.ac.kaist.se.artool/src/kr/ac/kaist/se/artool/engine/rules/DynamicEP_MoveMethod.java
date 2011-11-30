package kr.ac.kaist.se.artool.engine.rules;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.metrics.entityplacement.EPMoveMethodCandidate;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;

public class DynamicEP_MoveMethod extends AbstractRule {
	
	private EPMoveMethodCandidate[] candidates;
	private AOMMethod method;
	private AOMClass clazz;
	
	public DynamicEP_MoveMethod(AbstractObjectModel aom,
			EPMoveMethodCandidate[] candidates, int pick) {
		super(aom, pick, "DynamicEP_MoveMethod");
		this.candidates = candidates;
		method = candidates[pick].getMethod();
		clazz = candidates[pick].getClazz();
	}


	public RefactoringCommand getCommand()
	{
		if( method == null || clazz == null )
		{
			return null;
		}
		MoveMethodCommand mmc = new MoveMethodCommand(method, clazz);
		return mmc;
	}
	
	
	@Override
	public String getStatus()
	{
		return clazz.getFqdn() + " <- " + method.getOwner().getFqdn() + "." + method.getName();
		
	}
}
