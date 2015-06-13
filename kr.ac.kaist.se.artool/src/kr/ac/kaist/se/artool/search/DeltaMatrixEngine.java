package kr.ac.kaist.se.artool.search;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.Vector;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

import org.jblas.FloatMatrix;

public class DeltaMatrixEngine implements MoveMethodEventListener, CandidateSelection {
	FloatMatrix membershipMatrix;
	SystemEntitySet sts;
	
	
	public DeltaMatrixEngine(SystemEntitySet sts)
	{
		this.sts = sts;
		initMembershipMatrix();
		initLinkMatrix();
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
	
	FloatMatrix linkMatrix;
	
	private void initLinkMatrix()
	{
		linkMatrix = FloatMatrix.zeros(sts.entities.size(), sts.entities.size());

		for (AOMMethod method : sts.methods) {
			
			if( method.getOwnedScope() != null )
			{
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
	}
	
	private FloatMatrix getInternalLinkMatrix()
	{
		FloatMatrix internalLinkMask = membershipMatrix.mmul(membershipMatrix.transpose());
		return linkMatrix.mmul(internalLinkMask);
	}
	
	private FloatMatrix getExternalLinkMatrix(FloatMatrix internalLinkMatrix)
	{
		FloatMatrix externalLinkMatrix = linkMatrix.sub(internalLinkMatrix);
		return externalLinkMatrix;
	}
	
	private FloatMatrix getInvertedMembershipMatrix(FloatMatrix m)
	{
		FloatMatrix ret;
		
		ret = FloatMatrix.zeros(sts.entities.size(), sts.classes.size());
		
		int[] indices = m.findIndices();

		for( int index : indices )
		{
			int row = m.indexRows(index);
			int col = m.indexColumns(index);
			float v = m.get(index);
			
			for( int i = 0 ; i < sts.classes.size(); i++ )
			{
				ret.put(row, i, ret.get(row, i) + v);
			}
			ret.put(row, col, 0);
		}

		return ret;
	}
	
	private FloatMatrix getDeltaMatrix()
	{
		System.err.println("get delta matrix 1");
		FloatMatrix internalLinkMatrix = getInternalLinkMatrix();
		System.err.println("get delta matrix 2");
		FloatMatrix externalLinkMatrix = getExternalLinkMatrix(internalLinkMatrix);
		System.err.println("get delta matrix 3");
		FloatMatrix IP = internalLinkMatrix.mmuli(membershipMatrix);
		System.err.println("get delta matrix 4");
		FloatMatrix EP = externalLinkMatrix.mmuli(membershipMatrix);
		System.err.println("get delta matrix 5");
		FloatMatrix IIP = getInvertedMembershipMatrix(IP);
		FloatMatrix deltaMatrix = IIP.subi(EP);
		
		return deltaMatrix;
	}
	
	public Vector<MoveMethodCommand> getPositiveRefactorings()
	{
		FloatMatrix dm = getDeltaMatrix();
		Vector<MoveMethodCommand> mmcSet = new Vector<MoveMethodCommand>();
		
		for( int row = 0; row < sts.methods.size(); row++ )
		{
			for( int col = 0; col < sts.classes.size(); col++ )
			{
				float d = dm.get(row, col);
				if( d > 0 )
				{
					AOMMethod method = sts.methods.get(row);
					AOMClass clazz = sts.classes.get(col);
					MoveMethodCommand mmc = new MoveMethodCommand(method, clazz, d);
					mmcSet.add(mmc);
				}
			}
		}
		
		mmcSet.sort(new Comparator<MoveMethodCommand>() {

			@Override
			public int compare(MoveMethodCommand o1, MoveMethodCommand o2) {
				float dd = o1.getDeltaValue() - o2.getDeltaValue();
				if( dd == 0 )
				{
					return 0;
				}
				else if( dd < 0 )
				{
					return -1;
				}
				return 1;
			}
			
		});
		
		return mmcSet;
	}
	
	@Override
	public void moveMethodPerformed(AOMMethod method, AOMClass targetClass, boolean isRollbackAction)
	{
		membershipMatrix.put(method.getIndex(), method.getOwner().getIndex(), 0);
		membershipMatrix.put(method.getIndex(), targetClass.getIndex(), 1);
	}


	@Override
	public Vector<MoveMethodCommand> getCandidates() {
		return this.getPositiveRefactorings();
	}
}
