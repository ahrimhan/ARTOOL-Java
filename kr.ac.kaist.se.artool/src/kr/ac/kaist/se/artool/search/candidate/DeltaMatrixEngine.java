package kr.ac.kaist.se.artool.search.candidate;

import kr.ac.kaist.se.artool.search.MoveMethodEventListener;

public interface DeltaMatrixEngine extends MoveMethodEventListener,
		CandidateSelection {
	public void setAdjust(boolean adjustOption);
	public void setCohesiveFactorRate(double coupling, double cohesive);
}
