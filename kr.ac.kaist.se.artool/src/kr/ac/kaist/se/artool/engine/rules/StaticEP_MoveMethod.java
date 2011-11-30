package kr.ac.kaist.se.artool.engine.rules;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.metrics.entityplacement.EPMoveMethodCandidate;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;

public class StaticEP_MoveMethod extends AbstractRule {
	
	private EPMoveMethodCandidate[] candidates;
	private AOMMethod method;
	private AOMClass clazz;
	
	public StaticEP_MoveMethod(AbstractObjectModel aom,
			EPMoveMethodCandidate[] candidates, int pick) {
		super(aom, pick, "StaticEP_MoveMethod");
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
		//aomMethod[1]을 aomMethods[0]를 정의하고 있는 class로 옮긴다.
		MoveMethodCommand mmc = new MoveMethodCommand(method, clazz);
		return mmc;
	}

	
	@Override
	public String getStatus()
	{
		return clazz.getFqdn() + " <- " + method.getOwner().getFqdn() + "." + method.getName();
		
	}
}
