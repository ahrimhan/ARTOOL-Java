package kr.ac.kaist.se.artool.search.candidate;

import java.util.Collections;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodApplicabilityChecker;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class ExhaustiveCandidateSelection implements CandidateSelection {

	private AbstractObjectModel aom;
	private Vector<AOMMethod> methodList;
	private Vector<MoveMethodCommand> candidateList;
	private boolean shuffle;
	
	public ExhaustiveCandidateSelection(AbstractObjectModel aom, int maxCandidateCount)
	{
		this.aom = aom;
		this.methodList = new Vector<AOMMethod>();
		this.candidateList = new Vector<MoveMethodCommand>();
		this.shuffle = maxCandidateCount >= 0;
		
		System.err.println("ExhaustiveCandidateSelection: preparing...!!!");
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				methodList.add(method);
			}
		}
		
		for( AOMMethod method : methodList )
		{
			if( !MoveMethodApplicabilityChecker.isApplicableForGivenMethod(method) )
			{
				continue;
			}
			
			for( AOMClass clazz : aom.getClasses() )
			{
				if( MoveMethodApplicabilityChecker.isApplicableForTargetClass(method, clazz) )
				{
					candidateList.add(new MoveMethodCommand(method, clazz));
					if( maxCandidateCount > 0 && candidateList.size() > maxCandidateCount )
					{
						break;
					}
				}
			}
			
			if( maxCandidateCount > 0 && candidateList.size() > maxCandidateCount )
			{
				break;
			}
		}
		
		System.err.println("ExhaustiveCandidateSelection: ready to action!!!");
	}
	
	public Vector<AOMMethod> getMethodList()
	{
		return methodList;
	}
	
	public AbstractObjectModel getAOM()
	{
		return aom;
	}
	
	@Override
	public CandidateIterator getCandidateIterator(int maxCandidateCount) {
		if( shuffle )
		{
			Collections.shuffle(candidateList);
		}
		ExhaustiveCandidateIterator sci = new ExhaustiveCandidateIterator(candidateList.iterator()); 
		return sci;
	}

}
