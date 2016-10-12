package kr.ac.kaist.se.artool.search.candidate;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;

public interface CandidateIterator {
	public boolean hasNext();
	public MoveMethodCommand getNextCandidate();
	
	public void dispose();
	public void feedback(MoveMethodCommand mmc, FitnessValue prevValue, FitnessValue current);
}
