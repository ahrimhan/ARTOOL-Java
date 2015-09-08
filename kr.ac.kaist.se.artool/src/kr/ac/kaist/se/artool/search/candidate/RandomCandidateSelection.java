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
	private Random random;
	private Vector<AOMMethod> methodList;
	
	public RandomCandidateSelection(AbstractObjectModel aom)
	{
		this.aom = aom;
		this.random = new Random(System.currentTimeMillis());
		
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
	
	public MoveMethodCommand getRandomMoveMethodCommand()
	{
		MoveMethodCommand newMMC = null;
	
		int ci = random.nextInt(aom.getClasses().size());
		int mi = random.nextInt(methodList.size());
		newMMC = new MoveMethodCommand(methodList.elementAt(mi), aom.getClasses().get(ci));
		
		return newMMC;
	}
	
	
	@Override
	public CandidateIterator getCandidateIterator(int maxCandidateCount) {
		RandomCandidateIterator rci = new RandomCandidateIterator(this, maxCandidateCount); 
		return rci;
	}
}
