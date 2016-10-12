package kr.ac.kaist.se.artool.search.candidate;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import kr.ac.kaist.se.artool.engine.SystemEntitySet;

public class DeltaCandidateManager {
	
	private TreeMap<Long, DeltaCandidate> entireCandidateMap;
	private LinkedList<DeltaCandidate> prevFitnessDiffCandidateList;
	private SystemEntitySet ses;
	
	public DeltaCandidateManager(SystemEntitySet ses)
	{
		this.ses = ses;
		entireCandidateMap = new TreeMap<Long, DeltaCandidate>();
		prevFitnessDiffCandidateList = new LinkedList<DeltaCandidate>();
	}

//	public DeltaCandidateManager(DeltaCandidateManager dcm)
//	{
//		entireCandidateMap = new TreeMap<Long, DeltaCandidate>(dcm.entireCandidateMap);
//		prevFitnessDiffCandidateList = new LinkedList<DeltaCandidate>(dcm.prevFitnessDiffCandidateList);
//	}
	
	public DeltaCandidate addCandidate(int entityIdx, int toClassIdx)
	{
		long id = entityIdx;
		id = id << 32;
		id |= toClassIdx;
		DeltaCandidate dc = new DeltaCandidate(entityIdx, toClassIdx);
		entireCandidateMap.put(id, dc);
		
		return dc;
	}
	
	public DeltaCandidate getCandidate(int entityIdx, int toClassIdx)
	{
		long id = entityIdx;
		id = id << 32;
		id |= toClassIdx;
		
		return entireCandidateMap.get(id);
	}
	
//	public DeltaCandidate removeCandidate(int entityIdx, int toClassIdx)
//	{
//		long id = entityIdx;
//		id = id << 32;
//		id |= toClassIdx;
//		
//		return entireCandidateMap.remove(id);		
//	}
	
	public void setFitness(int entityIdx, int toClassIdx, DeltaValue fitnessDiffValue)
	{
		DeltaCandidate dc = getCandidate(entityIdx, toClassIdx);
		
		dc.setFitnessDiffValue(fitnessDiffValue);

		if( !prevFitnessDiffCandidateList.contains(dc) )
		{
			prevFitnessDiffCandidateList.add(dc);
		}
	}
	
	public void setFitness(DeltaCandidate dc, DeltaValue fitnessDiffValue) {
		if( entireCandidateMap.containsValue(dc) )
		{		
			dc.setFitnessDiffValue(fitnessDiffValue);

			if( !prevFitnessDiffCandidateList.contains(dc) )
			{
				prevFitnessDiffCandidateList.add(dc);
			}
		}
		else
		{
			throw new RuntimeException("abnormally created delta candidate...!!");
		}
		
		
	}
	
	public Collection<DeltaCandidate> getList()
	{
		return entireCandidateMap.values();
	}
	
	class ParetoDeltaComparator implements Comparator<DeltaCandidate>
	{
		private int index;
		public ParetoDeltaComparator(int index) {
			this.index = index;
		}
		@Override
		public int compare(DeltaCandidate o1, DeltaCandidate o2) {
			CompositeDeltaValue cdv1 = (CompositeDeltaValue)o1.getFitnessDiffValue();
			CompositeDeltaValue cdv2 = (CompositeDeltaValue)o2.getFitnessDiffValue();
			
			if( cdv1.getArray()[index] < cdv2.getArray()[index] )
			{
				return -1;
			}
			else if( cdv1.getArray()[index] > cdv2.getArray()[index] )
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}

	public LinkedList<DeltaCandidate> getPrevFitnessBasedCandidateList(int cutoff)
	{
		if( prevFitnessDiffCandidateList.size() == 0 )
		{
			return prevFitnessDiffCandidateList;
		}
		else if( cutoff == 0 )
		{
			prevFitnessDiffCandidateList.clear();
			return prevFitnessDiffCandidateList;
		}
		else if( prevFitnessDiffCandidateList.get(0).getFitnessDiffValue() instanceof CompositeDeltaValue ) 
		{
			LinkedList<DeltaCandidate> l1 = new LinkedList<DeltaCandidate>(prevFitnessDiffCandidateList);
			
			Collections.sort(l1, new ParetoDeltaComparator(0));

			LinkedList<DeltaCandidate> l2 = new LinkedList<DeltaCandidate>(prevFitnessDiffCandidateList);

			Collections.sort(l2, new ParetoDeltaComparator(1));
			
			prevFitnessDiffCandidateList.clear();
			
			while( l1.size() > 0 || l2.size() > 0)
			{
				DeltaCandidate dc;
				
				if( l1.size() > 0 )
				{
					dc = l1.removeFirst();
					prevFitnessDiffCandidateList.add(dc);
					if( l2.size() > 0 ) l2.remove(dc);
				}
				
				if( l2.size() > 0 )
				{
					dc = l2.removeFirst();
					prevFitnessDiffCandidateList.add(dc);
					if( l1.size() > 0 ) l1.remove(dc);
				}
			}
		}
		else
		{
			Collections.sort(prevFitnessDiffCandidateList, new Comparator<DeltaCandidate>()
			{
				@Override
				public int compare(DeltaCandidate o1, DeltaCandidate o2) {
					return o1.getFitnessDiffValue().compareTo(o2.getFitnessDiffValue());
				}

			}); 
		}
		
		List<DeltaCandidate> ll = prevFitnessDiffCandidateList.stream().
				filter(dc -> ses.methods.get(dc.getEntityIdx()).getOwner() != ses.classes.get(dc.getToClassIdx())).collect(Collectors.toList());
		
		prevFitnessDiffCandidateList.retainAll(ll);
		
		int removeCount = prevFitnessDiffCandidateList.size() - cutoff;

		for( int i = 0; i < removeCount && prevFitnessDiffCandidateList.size() > 0; i++ )
		{
			DeltaCandidate dc = prevFitnessDiffCandidateList.removeLast();
			dc.setFitnessDiffValue(null);
		}

		System.err.println("cutoff:"+ cutoff + " removeCount:" + removeCount);
		return prevFitnessDiffCandidateList;
	}

	public LinkedList<DeltaCandidate> getRandomList() {
		LinkedList<DeltaCandidate> list = new LinkedList<DeltaCandidate>(entireCandidateMap.values());
		Collections.shuffle(list);;
		return list;
	}
	
	public int getPossibleCandidateCount()
	{
		return entireCandidateMap.size();
	}

	public void invalidateCandidate(int index, int index2) {
		DeltaCandidate dc = getCandidate(index, index2);
		prevFitnessDiffCandidateList.remove(dc);
	}
}
