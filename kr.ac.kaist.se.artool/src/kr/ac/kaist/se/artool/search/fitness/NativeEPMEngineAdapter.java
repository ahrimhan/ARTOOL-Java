package kr.ac.kaist.se.artool.search.fitness;


import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.search.MoveMethodEventListener;
import kr.ac.kaist.se.artool.search.fitness.value.AtomicFitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.SmallerBetterFitnessValue;


public class NativeEPMEngineAdapter extends AtomicFitnessFunction implements MoveMethodEventListener {

  
    private SystemEntitySet ses;
    private NativeEPMEngine nee;
    
    public NativeEPMEngineAdapter(SystemEntitySet ses)
    {
        this.ses = ses;
        this.nee = new NativeEPMEngine();
        
        System.err.println("input matrix");
        nee.startToInitialize(ses.classes.size(), ses.methods.size(), ses.fields.size());
        initMembershipMatrix();
        initEntityMatrix();
        System.err.println("create matrix..");

        nee.doneToInitialize();
        System.err.println("done to create matrix..");
    }
    
    private void initMembershipMatrix()
	{
		for( AOMClass clazz : ses.classes )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				nee.setInitialMembershipMatrix(method.getIndex(), clazz.getIndex(), 1);
			}
			
			for( AOMField field : clazz.getFields() )
			{
				nee.setInitialMembershipMatrix(field.getIndex(), clazz.getIndex(), 1);
			}
		}

	}
	
	private void initEntityMatrix()
	{		
		for (AOMMethod method : ses.methods) {
			
			if( method.getOwnedScope() != null )
			{
				for(StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
				{
//					entityMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
					nee.setInitialLinkMatrix(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
//					nee.setInitialLinkMatrix(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
				}

				for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
				{
//					entityMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
					nee.setInitialLinkMatrix(method.getIndex(), smc.getCallee().getIndex(), 1);
				}
			}
		}		
	}
	


    
    
    @Override
    public void finalize()
    {
    	nee.dispose();
    }

	@Override
	public void moveMethodPerformed(AOMClass fromClass, AOMMethod method,
			AOMClass toClass, boolean isRollbackAction, boolean isVirtualMove) {
		nee.moveMethod(fromClass.getIndex(), method.getIndex(), toClass.getIndex());
	}

	@Override
	public AtomicFitnessValue calculateAtomic() {
		return new SmallerBetterFitnessValue(nee.calculateFitness());
	}
}
