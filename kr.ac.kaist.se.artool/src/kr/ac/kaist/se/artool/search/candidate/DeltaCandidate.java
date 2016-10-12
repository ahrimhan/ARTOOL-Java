package kr.ac.kaist.se.artool.search.candidate;

import kr.ac.kaist.se.deltatable.DeltaTableEntryIterator.DeltaTableEntry;

class DeltaCandidate {
    private Integer entityIdx;
    private Integer toClassIdx;
    private DeltaValue fitnessDiffValue;
    private DeltaTableEntry entry;
 
    public DeltaCandidate(Integer p1, Integer p2) {
        this.entityIdx = p1;
        this.toClassIdx = p2;
        this.entry = null;
    }	
    
    public Integer getEntityIdx()
    {
    	return entityIdx;
    }
    
    public Integer getToClassIdx()
    {
    	return toClassIdx;
    }
    
    public DeltaTableEntry getEntry()
    {
    	return entry;
    }
    
    public void setDeltaTableEntry(DeltaTableEntry dte)
    {
    	entry = dte;
    }
    
    public DeltaValue getFitnessDiffValue()
    {
    	return fitnessDiffValue;
    }
    
    public void setFitnessDiffValue(DeltaValue dv)
    {
    	fitnessDiffValue = dv;
    }
    
    @Override
    public int hashCode()
    {
    	return entityIdx.hashCode() ^ toClassIdx.hashCode();
    }
     
	@Override
    public boolean equals(Object t)
    {
    	if( t instanceof DeltaCandidate )
    	{
    		DeltaCandidate np = (DeltaCandidate)t;
    		return entityIdx.equals(np.entityIdx) && toClassIdx.equals(np.toClassIdx); 
    	}
		return false;
    }
}

