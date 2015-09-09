package kr.ac.kaist.se.artool.search.candidate;

import java.util.Iterator;
import java.util.Set;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class DeltaMatrixCandidateIterator implements CandidateIterator {
	private DeltaMatrixEngine dme;
	private Iterator<MoveMethodCommand> iter;
	private int iterationCount = 0;
	private int maxIterationCount = -1;
	
	public DeltaMatrixCandidateIterator(DeltaMatrixEngine dme, Set<MoveMethodCommand> mmcSet, int maxIterationCount) {
		this.dme = dme;
		iter = mmcSet.iterator();
		this.maxIterationCount = maxIterationCount;
		iterationCount = 0;
	}
	
	
	@Override
	public boolean hasNext() {
		
		if( this.maxIterationCount > 0 && iterationCount >= maxIterationCount )
		{
			return false;
		}
		
		iterationCount++;
		
		return iter.hasNext();
	}

	@Override
	public MoveMethodCommand getNextCandidate() {
		MoveMethodCommand ret = iter.next();
		dme.markAsUsed(ret);
		return ret;
	}
}
