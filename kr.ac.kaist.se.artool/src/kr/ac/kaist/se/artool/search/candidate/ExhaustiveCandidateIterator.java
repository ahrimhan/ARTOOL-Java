package kr.ac.kaist.se.artool.search.candidate;

import java.util.Collections;
import java.util.Vector;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodApplicabilityChecker;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class ExhaustiveCandidateIterator implements CandidateIterator {

	private ExhaustiveCandidateSelection scs;
	private int maxCandidateCount;
	
	private int curMethodIdx;
	private int curTargetClassIdx;
	private int curOutCount;
	
	private Vector<AOMMethod> methodList;
	private Vector<AOMClass> classList;
	
	private MoveMethodCommand nextMMC;
	
	public ExhaustiveCandidateIterator(ExhaustiveCandidateSelection scs, int maxCandidateCount)
	{
		this.scs = scs;
		this.maxCandidateCount = maxCandidateCount;
		curOutCount = 0;
		curMethodIdx = 0;
		curTargetClassIdx = 0;
		
		methodList = new Vector<AOMMethod>(scs.getMethodList());
		classList = new Vector<AOMClass>(scs.getAOM().getClasses());
		
//		Collections.shuffle(methodList);
//		Collections.shuffle(classList);
	}
	
	private boolean _hasNext()
	{
		return (!(maxCandidateCount > 0 && curOutCount >= maxCandidateCount)) && 
				(curMethodIdx < methodList.size() && curTargetClassIdx < classList.size());
	}
	
	private MoveMethodCommand getMoveMethodCommand()
	{		
		while( _hasNext() )
		{
			AOMMethod movingMethod = methodList.get(curMethodIdx);
			AOMClass targetClass = classList.get(curTargetClassIdx);
			
			curMethodIdx++;
			
			if( curMethodIdx >= methodList.size() )
			{
				curTargetClassIdx++;
				curMethodIdx = 0;
			}
			
			if( MoveMethodApplicabilityChecker.isApplicable(movingMethod, targetClass) )
			{
				curOutCount++;
				System.err.println("Move Method Command: curMethodIdx("+curMethodIdx + "), curTargetClassIdx(" + curTargetClassIdx + ")");
				
				return new MoveMethodCommand(movingMethod, targetClass);
			}				
		}
		
		return null;
	}
	
	@Override
	public boolean hasNext() {
		if( _hasNext() )
		{
			nextMMC = getMoveMethodCommand();
		}
		else
		{
			nextMMC = null;
		}
		
		return nextMMC != null;
	}

	@Override
	public MoveMethodCommand getNextCandidate() {
		if( _hasNext() && nextMMC == null )
		{
			nextMMC = getMoveMethodCommand();
		}
		
		return nextMMC;
	}

}
