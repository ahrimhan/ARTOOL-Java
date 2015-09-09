package kr.ac.kaist.se.artool.search.candidate;

import java.util.Random;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class RandomCandidateIterator implements CandidateIterator {
	private RandomCandidateSelection rcs;
	private int iterationCount = 0;
	private int maxIterationCount = -1;
	
	
	public RandomCandidateIterator(RandomCandidateSelection rcs, int maxIterationCount)
	{
		this.rcs = rcs;
		this.maxIterationCount = maxIterationCount;
		iterationCount = 0;
	}
	
	@Override
	public boolean hasNext() {
		if( maxIterationCount > 0 && iterationCount >= maxIterationCount )
		{
			return false;
		}
		else
		{
			return true;
		}
				
	}

	@Override
	public MoveMethodCommand getNextCandidate() {
		return rcs.getRandomMoveMethodCommand();
	}

}
