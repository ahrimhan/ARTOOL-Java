package kr.ac.kaist.se.artool.search.candidate;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.deltatable.DeltaTableEntryIterator;
import kr.ac.kaist.se.deltatable.DeltaTableEntryIterator.DeltaTableEntry;

public class NativeDeltaMatrixCandidateIteratorAdaptor implements
		CandidateIterator {
	
	private DeltaTableEntryIterator nativeIterator;
	private SystemEntitySet ses;
	
	public NativeDeltaMatrixCandidateIteratorAdaptor(SystemEntitySet ses, DeltaTableEntryIterator nativeIterator)
	{
		this.nativeIterator = nativeIterator;
		this.ses = ses;
	}

	@Override
	public boolean hasNext() {
		return nativeIterator.hasNext();
	}

	@Override
	public MoveMethodCommand getNextCandidate() {
		
		DeltaTableEntry entry = nativeIterator.next();
		
		if( entry == null )
		{
			return null;
		}
		
		AOMMethod movingMethod = ses.methods.get(entry.entityIdx);
		AOMClass targetClass = ses.classes.get(entry.toClassIdx);
		
		MoveMethodCommand mmc = new MoveMethodCommand(movingMethod, targetClass, new CompositeDeltaValue(entry.deltaValueList));
		
		return mmc;
	}
}
