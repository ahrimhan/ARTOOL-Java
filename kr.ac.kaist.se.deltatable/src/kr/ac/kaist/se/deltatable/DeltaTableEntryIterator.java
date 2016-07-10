package kr.ac.kaist.se.deltatable;

public class DeltaTableEntryIterator {

	
	public static synchronized DeltaTableEntryIterator getInstance(long nativeHandle)
	{
		DeltaTableEntryIterator ret = new DeltaTableEntryIterator(nativeHandle);
		return ret;
	}
	
	private DeltaTableEntryIterator(long nativeHandle)
	{
		this.nativeHandle = nativeHandle;
	}
	
    private long nativeHandle;
    private native int _hasNext();
    private native int _next(DeltaTableEntry entry);
    public native void dispose();
    
    public boolean hasNext()
    {
    	if( _hasNext() == 0 )
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    public class DeltaTableEntry
    {
    	public int toClassIdx;
    	public int entityIdx;
    	public float deltaValue;
    }
    
    public DeltaTableEntry next()
    {
    	DeltaTableEntry entry = new DeltaTableEntry();
    	
    	if( _next(entry) == 0 )
    	{
    		return null;
    	}
    	
    	System.err.println("entry:" + entry.deltaValue);
    	
    	return entry;
    }
}
