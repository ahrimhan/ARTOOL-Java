package kr.ac.kaist.se.deltatable;

public class DeltaTableEngine {
	static {
		System.loadLibrary("libfastdelta.dylib");
	}
	
	public static synchronized DeltaTableEngine getInstance(DeltaTableInfo info)
	{
		DeltaTableEngine ret = new DeltaTableEngine();
		ret.initialize(info);
		info.dispose();
		return ret;
	}
		
	private DeltaTableEngine()
	{
	}
	
	private native void initialize(DeltaTableInfo info);
    private long nativeHandle;
    
	public native void move(int entityIdx, int fromClassIdx, int toClassIdx);
	public native void eval();
	public native long _getTopK(int k);
	
	public DeltaTableEntryIterator getTopK(int k)
	{
		System.err.println("TopK:" + k);
		long iteratorNativeHandle = _getTopK(k);
		DeltaTableEntryIterator ret = DeltaTableEntryIterator.getInstance(iteratorNativeHandle);
		return ret;
	}
	
    private native void dispose();
    
    @Override
    public void finalize()
    {
    	dispose();
    }
}
