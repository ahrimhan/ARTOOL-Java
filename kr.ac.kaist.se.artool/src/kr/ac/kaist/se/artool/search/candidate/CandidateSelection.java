package kr.ac.kaist.se.artool.search.candidate;


public interface CandidateSelection {
	CandidateIterator getCandidateIterator(int maxCandidateCount, boolean shuffle);
}
