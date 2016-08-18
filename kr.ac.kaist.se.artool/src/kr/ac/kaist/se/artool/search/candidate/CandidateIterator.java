package kr.ac.kaist.se.artool.search.candidate;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public interface CandidateIterator {
	public boolean hasNext();
	public MoveMethodCommand getNextCandidate();
	
	public void dispose();
}
