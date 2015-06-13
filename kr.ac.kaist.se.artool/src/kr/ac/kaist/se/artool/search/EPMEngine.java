package kr.ac.kaist.se.artool.search;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;

import org.jblas.DoubleMatrix;

public class EPMEngine {
	DoubleMatrix membershipMatrix;
	SystemEntitySet sts;
	
	
	public EPMEngine(SystemEntitySet sts)
	{
		this.sts = sts;
		initMembershipMatrix();
		initEntityMatrix();
	}
	
	
	private void initMembershipMatrix()
	{
		membershipMatrix = DoubleMatrix.zeros(sts.entities.size(), sts.classes.size());
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
	
	DoubleMatrix entityMatrix;
	
	private void initEntityMatrix()
	{
		entityMatrix = DoubleMatrix.zeros(sts.entities.size(), sts.entities.size());

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
	
	private DoubleMatrix getDistanceMatrix()
	{
		DoubleMatrix intersectMatrix = entityMatrix.mmul(membershipMatrix);
		DoubleMatrix colSum = entityMatrix.columnSums();
		DoubleMatrix rowSum = membershipMatrix.rowSums();
		
		DoubleMatrix minusUnionMatrix = intersectMatrix.subColumnVector(colSum).subiRowVector(rowSum);
		DoubleMatrix distanceMatrix = intersectMatrix.divi(minusUnionMatrix).addi(1);
		
		return distanceMatrix;
	}
	
	public double getEPM()
	{
		DoubleMatrix distanceMatrix = getDistanceMatrix();
		
		double epm = 0;
		
		
		for( int i = 0; i < sts.classes.size(); i++ )
		{
			double internal = 0;
			int internalCount = 0;
			double external = 0;
			int externalCount = 0;
			double epmc = 0;
			
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
	
	public void moveMethod(AOMMethod method, AOMClass targetClass)
	{
		membershipMatrix.put(method.getIndex(), method.getOwner().getIndex(), 0);
		membershipMatrix.put(method.getIndex(), targetClass.getIndex(), 1);
	}
}
