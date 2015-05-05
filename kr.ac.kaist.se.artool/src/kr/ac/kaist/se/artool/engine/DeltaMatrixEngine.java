package kr.ac.kaist.se.artool.engine;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.jblas.DoubleMatrix;

public class DeltaMatrixEngine {
	DoubleMatrix membershipMatrix;
	SystemEntitySet sts;
	
	
	public DeltaMatrixEngine(SystemEntitySet sts)
	{
		this.sts = sts;
		initMembershipMatrix();
		initLinkMatrix();
	}
	
	
	private void initMembershipMatrix()
	{
		membershipMatrix = DoubleMatrix.zeros(sts.entities.size(), sts.classes.size());
		for( AOMClass clazz : sts.classes )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				membershipMatrix.put(method.getIndex(), clazz.getIndex(), 1);
			}
			
			for( AOMField field : clazz.getFields() )
			{
				membershipMatrix.put(field.getIndex(), clazz.getIndex(), 1);
			}
		}

	}
	
	DoubleMatrix linkMatrix;
	
	private void initLinkMatrix()
	{
		linkMatrix = DoubleMatrix.zeros(sts.entities.size(), sts.entities.size());

		for (AOMMethod method : sts.methods) {
			for(StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
			{
				linkMatrix.put(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
				linkMatrix.put(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
			}
			
			for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
			{
				linkMatrix.put(smc.getCallee().getIndex(), method.getIndex(), 1);
				linkMatrix.put(method.getIndex(), smc.getCallee().getIndex(), 1);
			}
		}
	}
	
	private DoubleMatrix getInternalLinkMatrix()
	{
		DoubleMatrix internalLinkMask = membershipMatrix.mmul(membershipMatrix.transpose());
		return linkMatrix.mmul(internalLinkMask);
	}
	
	private DoubleMatrix getExternalLinkMatrix(DoubleMatrix internalLinkMatrix)
	{
		DoubleMatrix externalLinkMatrix = linkMatrix.sub(internalLinkMatrix);
		return externalLinkMatrix;
	}
	
	private DoubleMatrix getInvertedMembershipMatrix(DoubleMatrix m)
	{
		DoubleMatrix ret;
		
		ret = DoubleMatrix.zeros(sts.entities.size(), sts.classes.size());
		
		int[] indices = m.findIndices();

		for( int index : indices )
		{
			int row = m.indexRows(index);
			int col = m.indexColumns(index);
			double v = m.get(index);
			
			//ret.putRow(row, DoubleMatrix.ones(1, classes.size()).mul(v));
			
			for( int i = 0 ; i < sts.classes.size(); i++ )
			{
				ret.put(row, i, v);
			}
			ret.put(row, col, 0);
		}

		return ret;
	}
	
	public DoubleMatrix getDeltaMatrix()
	{
		DoubleMatrix internalLinkMatrix = getInternalLinkMatrix();
		DoubleMatrix externalLinkMatrix = getExternalLinkMatrix(internalLinkMatrix);
		DoubleMatrix IP = internalLinkMatrix.mmul(membershipMatrix);
		DoubleMatrix EP = externalLinkMatrix.mmul(membershipMatrix);
		DoubleMatrix IIP = getInvertedMembershipMatrix(IP);
		DoubleMatrix deltaMatrix = IIP.sub(EP);
		
		return deltaMatrix;
	}
	
	public void moveMethod(AOMMethod method, AOMClass targetClass)
	{
		membershipMatrix.put(method.getIndex(), method.getOwner().getIndex(), 0);
		membershipMatrix.put(method.getIndex(), targetClass.getIndex(), 1);
	}
}
