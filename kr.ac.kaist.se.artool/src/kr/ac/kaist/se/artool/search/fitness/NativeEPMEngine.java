package kr.ac.kaist.se.artool.search.fitness;

public class NativeEPMEngine {
	static
	{
		System.loadLibrary("libNativeEPMEngine.dylib");
	}

	private long context;

	public NativeEPMEngine()
	{
		context = 0;
	}
	
    public native void startToInitialize(int classSize, int methodSize, int fieldSize);
    public native void setInitialLinkMatrix(int fromEntityId, int toEntityId, float value);
    public native void setInitialMembershipMatrix(int entityId, int classId, float value);
    public native void doneToInitialize();

    public native void moveMethod(int fromClassId, int methodId, int toClassId);
    public native float calculateFitness();

    public native void dispose();
}
