package kr.ac.kaist.se.deltatable;

public class DeltaTableEngine {
	static {
		System.loadLibrary("libfastdelta.dylib");
	}
	
	public static synchronized DeltaTableEngine getInstance(DeltaTableInfo info, MoveMethodValidityChecker checker)
	{
		DeltaTableEngine ret = new DeltaTableEngine(checker);
		ret.initialize(info);
		info.dispose();
		return ret;
	}
	
	private MoveMethodValidityChecker checker;
	
	private DeltaTableEngine(MoveMethodValidityChecker checker)
	{
		this.checker = checker;
	}
	
	private native void initialize(DeltaTableInfo info);
    private long nativeHandle;
    
	public native void move(int entityIdx, int fromClassIdx, int toClassIdx);
	public native void eval();
	public native long _getTopK(int k);
	
	public DeltaTableEntryIterator getTopK(int k)
	{
		long iteratorNativeHandle = _getTopK(k);
		DeltaTableEntryIterator ret = DeltaTableEntryIterator.getInstance(iteratorNativeHandle);
		return ret;
	}
	
    public native void dispose();
    
    public boolean moveMethodValidityCheck(int entityIdx, int toClassIdx)
    {
    	try
    	{
	    	if( checker == null) return true;    	
	    	return checker.check(entityIdx, toClassIdx);
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		return false;
    	}
    }
}
