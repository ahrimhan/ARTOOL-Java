package kr.ac.kaist.se.deltatable;

public class DeltaTableInfo {
	static {
		System.loadLibrary("libfastdelta.dylib");
	}
	   
	public static synchronized DeltaTableInfo getInstance(int classCount, int entityCount, int methodCount, int methodPossibleToMoveCount)
	{
		DeltaTableInfo ret = new DeltaTableInfo();
		ret.initialize(classCount, entityCount, methodCount, methodPossibleToMoveCount);
		return ret;
	}
	
	private DeltaTableInfo()
	{
		
	}
	
	private native void initialize(int classCount, int entityCount, int methodCount, int methodPossibleToMoveCount);
	public native void addLink(int fromEntityIdx, int toEntityIdx);
	public native void addMembership(int entityIdx, int classIdx);
	public native void possibleMoveMethod(int entityIdx, int toClassIdx);
	public native int getClassCount();
	public native int getEntityCount();
	
    private long nativeHandle;
    
    private native void dispose();
    
    @Override
    public void finalize()
    {
    	dispose();
    }
}
