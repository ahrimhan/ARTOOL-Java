package kr.ac.kaist.se.artool.search;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;

import org.jblas.FloatMatrix;

public class EPMEngine implements FitnessFunction, MoveMethodEventListener {
	FloatMatrix membershipMatrix;
	SystemEntitySet sts;
	
	
	public EPMEngine(SystemEntitySet sts)
	{
		this.sts = sts;
		initMembershipMatrix();
		initEntityMatrix();
	}
	
	
	private void initMembershipMatrix()
	{
		membershipMatrix = FloatMatrix.zeros(sts.entities.size(), sts.classes.size());
		for( AOMClass clazz : sts.classes )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				try
				{
					membershipMatrix.put(method.getIndex(), clazz.getIndex(), 1);
				} 
				catch(ArrayIndexOutOfBoundsException ex)
				{
					System.err.println("Method index:" + method.getIndex());
					System.err.println("Class index:" + clazz.getIndex());
					throw ex;
				}
			}
			
			for( AOMField field : clazz.getFields() )
			{
				membershipMatrix.put(field.getIndex(), clazz.getIndex(), 1);
			}
		}

	}
	
	FloatMatrix entityMatrix;
	
	private void initEntityMatrix()
	{
		entityMatrix = FloatMatrix.zeros(sts.entities.size(), sts.entities.size());

		for (AOMMethod method : sts.methods) {
			
			if( method.getOwnedScope() != null )
			{
				for(StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
				{
					entityMatrix.put(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
				}

				for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
				{
					entityMatrix.put(method.getIndex(), smc.getCallee().getIndex(), 1);
				}
			}
		}
	}
	
	private FloatMatrix getDistanceMatrix()
	{
		FloatMatrix intersectMatrix = entityMatrix.mmul(membershipMatrix);
		FloatMatrix colSum = entityMatrix.columnSums();
		FloatMatrix rowSum = membershipMatrix.rowSums();
		
		FloatMatrix minusUnionMatrix = intersectMatrix.subColumnVector(colSum).subiRowVector(rowSum);
		FloatMatrix distanceMatrix = intersectMatrix.divi(minusUnionMatrix).addi(1);
		
		return distanceMatrix;
	}
	
	public float calculate()
	{
		FloatMatrix distanceMatrix = getDistanceMatrix();
		
		float epm = 0;
		
		
		for( int i = 0; i < sts.classes.size(); i++ )
		{
			float internal = 0;
			int internalCount = 0;
			float external = 0;
			int externalCount = 0;
			float epmc = 0;
			
			for( int j = 0 ; j < sts.entities.size(); j++ )
			{
				
				if( membershipMatrix.get(j, i) == 1 )
				{
					internal += distanceMatrix.get(j, i);
					internalCount ++;
				}
				else
				{
					external += distanceMatrix.get(j, i);
					externalCount ++;
				}
			
			}
			
			epmc = internal * externalCount / external / internalCount;
			epm += (sts.classes.get(i).getMethods().size() + sts.classes.get(i).getFields().size()) * epmc;
		}
		
		epm = epm / sts.entities.size();
		
		
		return epm;
	}
	
	public void moveMethodPerformed(AOMMethod method, AOMClass targetClass, boolean isRollbackAction)
	{
		membershipMatrix.put(method.getIndex(), method.getOwner().getIndex(), 0);
		membershipMatrix.put(method.getIndex(), targetClass.getIndex(), 1);
	}
}
