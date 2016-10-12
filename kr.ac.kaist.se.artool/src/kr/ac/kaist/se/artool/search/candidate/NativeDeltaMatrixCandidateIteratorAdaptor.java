package kr.ac.kaist.se.artool.search.candidate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;
import kr.ac.kaist.se.deltatable.DeltaTableEntryIterator;
import kr.ac.kaist.se.deltatable.DeltaTableEntryIterator.DeltaTableEntry;

public class NativeDeltaMatrixCandidateIteratorAdaptor implements CandidateIterator {
	
	private Vector<DeltaCandidate> list = new Vector<DeltaCandidate>();
	private SystemEntitySet ses;
	
	private Iterator<DeltaCandidate> candidateIterator;
	private DeltaCandidateManager dcm;
	
	private int maxCandidateCount;
	
	public NativeDeltaMatrixCandidateIteratorAdaptor(
			SystemEntitySet ses, 
			DeltaTableEntryIterator nativeIterator, 
			DeltaCandidateManager dcm, 
			int maxCandidateCount)
	{
		this.maxCandidateCount = maxCandidateCount;
		this.ses = ses;
		this.dcm = dcm;
		int prevFitnessCandidateCount = 0;
		HashSet<DeltaCandidate> pairSet = new HashSet<DeltaCandidate>();
		
		if( maxCandidateCount > 0 ) 
		{
			prevFitnessCandidateCount = maxCandidateCount * 2 / 10;
		}
		else
		{
			prevFitnessCandidateCount = 0;
		}

		LinkedList<DeltaCandidate> prevFitnessBasedCandidateList = dcm.getPrevFitnessBasedCandidateList(prevFitnessCandidateCount * 3);
		
		
		for( int i = 0; i < prevFitnessBasedCandidateList.size() &&
				list.size() < prevFitnessCandidateCount; i++ )
		{
			DeltaCandidate dc = prevFitnessBasedCandidateList.get(i);
			if( ses.methods.get(dc.getEntityIdx()).getOwner() != ses.classes.get(dc.getToClassIdx()) )
			{
				pairSet.add(dc);
				list.add(dc);
			}
		}
		
		prevFitnessCandidateCount = list.size();

		int deltaCandidateCount = 0;
		
		if( maxCandidateCount > 0 )
		{
			deltaCandidateCount = (maxCandidateCount * 5 / 10) - prevFitnessCandidateCount;
		}
		else
		{
			deltaCandidateCount = -1;
		}
		
		while( nativeIterator.hasNext() &&
				(deltaCandidateCount < 0 || list.size() < (deltaCandidateCount + prevFitnessCandidateCount)))
		{
			DeltaTableEntry entry = nativeIterator.next();
			
			if( ses.methods.get(entry.entityIdx).getOwner() != ses.classes.get(entry.toClassIdx) )
			{
				DeltaCandidate dc = dcm.getCandidate(entry.entityIdx, entry.toClassIdx);
				dc.setDeltaTableEntry(entry);
				if( !pairSet.contains(dc) )
				{
					pairSet.add(dc);
					list.add(dc);
				}
			}
		}
		
		deltaCandidateCount = list.size() - prevFitnessCandidateCount;
	
		LinkedList<DeltaCandidate> randomCandidateList = dcm.getRandomList();
		

		int randomCandidateCount = 0;
		
		if( maxCandidateCount > 0 )
		{
			randomCandidateCount = maxCandidateCount - list.size();
		}
		else
		{
			randomCandidateCount = dcm.getPossibleCandidateCount() / 10;
		}
		

		while( list.size() < (prevFitnessCandidateCount + deltaCandidateCount + randomCandidateCount) 
				&& randomCandidateList.size() > 0)
		{
			DeltaCandidate dc = randomCandidateList.pop();
			
			if( ses.methods.get(dc.getEntityIdx()).getOwner() != ses.classes.get(dc.getToClassIdx()) )
			{
				if( !pairSet.contains(dc) )
				{
					pairSet.add(dc);
					list.add(dc);
				}
			}
		}
		
		randomCandidateCount = list.size() - deltaCandidateCount - prevFitnessCandidateCount;
		
		System.err.println("top(" + maxCandidateCount + "), fit(" + prevFitnessCandidateCount + "), delta(" + 
		deltaCandidateCount + "), rand(" + randomCandidateCount + "), prevListSize(" + prevFitnessBasedCandidateList.size() + ")");
		
		
		nativeIterator.dispose();
		
		candidateIterator = list.iterator();
	}

	@Override
	public boolean hasNext() {
		return candidateIterator.hasNext();
	}

	@Override
	public MoveMethodCommand getNextCandidate() {
		
		
		DeltaCandidate dc = candidateIterator.next();
		
		if( dc == null ) return null;
		
		AOMMethod movingMethod = ses.methods.get(dc.getEntityIdx());
		AOMClass  targetClass  = ses.classes.get(dc.getToClassIdx());
		
	
		MoveMethodCommand mmc = null;
		
		if( dc.getEntry() != null )
		{
			mmc = new MoveMethodCommand(movingMethod, targetClass, 
					new CompositeDeltaValue(dc.getEntry().deltaValueList));
		}
		else
		{
			mmc = new MoveMethodCommand(movingMethod, targetClass);
		}
		
		return mmc;
	}
	
	@Override 
	public void feedback(MoveMethodCommand mmc, FitnessValue prevValue, FitnessValue current)
	{
		if( maxCandidateCount > 0 )
		{
			DeltaValue fitnessDiffValue = prevValue.diffWith(current);
			dcm.setFitness(mmc.getMethod().getIndex(), mmc.getToClass().getIndex(), fitnessDiffValue);
		}
	}

	@Override
	public void dispose() {
	}
}
