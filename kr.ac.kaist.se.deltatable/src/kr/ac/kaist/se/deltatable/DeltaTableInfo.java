package kr.ac.kaist.se.deltatable;

public class DeltaTableInfo {
	static {
		System.loadLibrary("libfastdelta.dylib");
	}
	   
	public static synchronized DeltaTableInfo getInstance(int classCount, int entityCount, int methodCount)
	{
		DeltaTableInfo ret = new DeltaTableInfo();
		ret.initialize(classCount, entityCount, methodCount);
		return ret;
	}
	
	private DeltaTableInfo()
	{
		
	}
	
	private native void initialize(int classCount, int entityCount, int methodCount);
	public native void addLink(int fromEntityIdx, int toEntityIdx);
	public native void addMembership(int entityIdx, int classIdx);
	public native int getClassCount();
	public native int getEntityCount();
	
    private long nativeHandle;
    
    public native void dispose();
}
