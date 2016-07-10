package kr.ac.kaist.se.artool.search.candidate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class RandomCandidateSelection implements CandidateSelection {
	private AbstractObjectModel aom;
	private Vector<AOMMethod> methodList;
	
	public RandomCandidateSelection(AbstractObjectModel aom)
	{
		this.aom = aom;
		
		this.methodList = new Vector<AOMMethod>();
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				methodList.add(method);
			}
		}
		
		/*
		if( (this.candidateCount * 2) > (aom.getClasses().size() * methodList.size()) )
		{
			throw new RuntimeException("RandomCandidateSelection: too large candidate count");
		}
		*/
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
		RandomCandidateIterator rci = new RandomCandidateIterator(this, maxCandidateCount, 1000); 
		return rci;
	}
}
