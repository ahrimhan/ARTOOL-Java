package kr.ac.kaist.se.artool.search.candidate;

import java.util.Iterator;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class ExhaustiveCandidateIterator implements CandidateIterator {

	private Iterator<MoveMethodCommand> candidateIterator;
	
	public ExhaustiveCandidateIterator(Iterator<MoveMethodCommand> iterator)
	{
		candidateIterator = iterator;
	}
	
	
	@Override
	public boolean hasNext() {
		return candidateIterator.hasNext();
	}

	@Override
	public MoveMethodCommand getNextCandidate() {
		return candidateIterator.next();
	}

}
