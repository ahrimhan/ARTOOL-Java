package kr.ac.kaist.se.deltatable;

public class DeltaValueEngine {
	static {
		System.loadLibrary("libfastdelta.dylib");
	}
	
	public static synchronized DeltaValueEngine getInstance(DeltaTableInfo info)
	{
		DeltaValueEngine ret = new DeltaValueEngine();
		ret.initialize(info);
		info.dispose();
		return ret;
	}
	
	private DeltaValueEngine()
	{
		
	}
	
	private native void initialize(DeltaTableInfo info);
    private long nativeHandle;
    
	public native void move(int entityIdx, int fromClassIdx, int toClassIdx);
	public native int createClass();
	public native float getDValue(int entityIdx, int fromClassIdx, int toClassIdx);
	public native float getExtractClassDValue(int sourceClassIdx, int[] entityIndices);
	
	
    public native void dispose();
}
