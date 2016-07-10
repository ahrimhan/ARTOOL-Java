package kr.ac.kaist.se.artool.search.candidate;

import java.util.Iterator;
import java.util.Set;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class JavaDeltaMatrixCandidateIterator implements CandidateIterator {
	private JavaDeltaMatrixEngine dme;
	private Iterator<MoveMethodCommand> iter;
	private int iterationCount = 0;
	private int maxIterationCount = -1;
	
	public JavaDeltaMatrixCandidateIterator(JavaDeltaMatrixEngine dme, Set<MoveMethodCommand> mmcSet, int maxIterationCount) {
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
