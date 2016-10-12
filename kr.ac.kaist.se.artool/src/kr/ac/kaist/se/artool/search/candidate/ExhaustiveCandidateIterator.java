package kr.ac.kaist.se.artool.search.candidate;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodApplicabilityChecker;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;

public class ExhaustiveCandidateIterator implements CandidateIterator {
	private Vector<MoveMethodCommand> candidateList;
	private Iterator<MoveMethodCommand> candidateIterator;
	private int maxCandidateCount;
	private int count;
	
	public ExhaustiveCandidateIterator(ExhaustiveCandidateSelection selection, int maxCandidateCount, boolean shuffle)
	{
		candidateList = new Vector<MoveMethodCommand>();
		

		for( AOMMethod method : selection.getMethodList() )
		{
			if( !MoveMethodApplicabilityChecker.isApplicableForGivenMethod(method) )
			{
				continue;
			}
			
	
			for( AOMClass clazz : selection.getAOM().getClasses() )
			{
				if( clazz != method.getOwner() && MoveMethodApplicabilityChecker.isApplicableForTargetClass(method, clazz) )
				{
					candidateList.add(new MoveMethodCommand(method, clazz));
				}
			}
		}
		
		if( maxCandidateCount > 0 || shuffle)
		{
			Collections.shuffle(candidateList);
		}
		
		count = 0;
		this.maxCandidateCount = maxCandidateCount;
		candidateIterator = candidateList.iterator();
	}
	
	
	@Override
	public boolean hasNext() {
		if( maxCandidateCount > 0 && maxCandidateCount <= count ) return false;
		return candidateIterator.hasNext();
	}

	@Override
	public MoveMethodCommand getNextCandidate() {
		if( maxCandidateCount > 0 && maxCandidateCount <= count ) return null;
		
		MoveMethodCommand mmc = candidateIterator.next();
		
		if( mmc != null ) count++;
		
		return mmc;
	}


	@Override
	public void dispose() {
		candidateList.clear();
		candidateIterator = null;
	}
	
	@Override
	public void feedback(MoveMethodCommand mmc, FitnessValue prevValue, FitnessValue current)
	{
		
	}

}
