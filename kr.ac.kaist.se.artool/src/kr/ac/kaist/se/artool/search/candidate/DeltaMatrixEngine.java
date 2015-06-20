package kr.ac.kaist.se.artool.search.candidate;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.MoveMethodEventListener;
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.Matrix;
import no.uib.cipr.matrix.MatrixEntry;
import no.uib.cipr.matrix.sparse.LinkedSparseMatrix;

public class DeltaMatrixEngine implements MoveMethodEventListener, CandidateSelection {
	Matrix membershipMatrix;
	SystemEntitySet sts;
//	Matrix linkMatrix;
	Matrix internalLinkMatrix;
	Matrix externalLinkMatrix;
	int maxCandidate;

	
	public DeltaMatrixEngine(SystemEntitySet sts, int maxCandidate)
	{
		this.sts = sts;
		this.maxCandidate = maxCandidate;
		initBasicMatrix();	
	}
	
	
	private void initBasicMatrix()
	{
		membershipMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.classes.size());
		internalLinkMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());
		externalLinkMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());
		
		
		
		for (AOMMethod method : sts.methods) {
			AOMClass clazz = method.getOwner();
			try {
				membershipMatrix.set(method.getIndex(), clazz.getIndex(), 1);
			} catch (ArrayIndexOutOfBoundsException ex) {
				dLog("Method index:" + method.getIndex());
				dLog("Class index:" + clazz.getIndex());
				throw ex;
			}
			
			if( method.getOwnedScope() != null )
			{
				for(StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
				{
					if( method.getOwner() == sfa.getAccessedField().getOwner() )
					{
						internalLinkMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
						internalLinkMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
					}
					else
					{
						externalLinkMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
						externalLinkMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
					}
					
					
					// Experimental: Cohesive factor
					for(StaticFieldAccess sfaReverse: sfa.getAccessedField().getStaticReferer() )
					{
						AOMMethod cohesiveMethod = sfaReverse.getAccessingScope().getOwner();
						
						if( cohesiveMethod != method )
						{
							if( method.getOwner() == cohesiveMethod.getOwner() )
							{
								internalLinkMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 1);
								internalLinkMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 1);
							}
							else
							{
								externalLinkMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 1);
								externalLinkMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 1);
							}							
						}
					}
					
				}

				for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
				{
					if( method.getOwner() == smc.getCallee().getOwner() )
					{
						internalLinkMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 1);
						internalLinkMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
					}
					else
					{
						externalLinkMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 1);
						externalLinkMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
					}
				}
			}
		}

		for (AOMField field : sts.fields) {
			AOMClass clazz = field.getOwner();
			membershipMatrix.set(field.getIndex(), clazz.getIndex(), 1);
		}

	}
	
		
	private Matrix getInvertedMembershipMatrix(Matrix m)
	{
		Matrix ret;
		
		ret = new DenseMatrix(sts.entities.size(), sts.classes.size());
		
		
		Iterator<MatrixEntry> iterator = m.iterator();
		
		for( MatrixEntry entry = iterator.next(); iterator.hasNext(); entry = iterator.next() )
		{
			int row = entry.row();
			int col = entry.column();
			float v = (float) entry.get();
			
			for( int i = 0 ; i < sts.classes.size(); i++ )
			{
				ret.set(row, i, ret.get(row, i) + v);
			}
			
			ret.set(row, col, 0);
		}
		

		return ret;
	}
	
	public static void dLog(String s)
	{
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.err.println(time.toString() + s);
	}
	
	private Matrix getDeltaMatrix()
	{
		
		Matrix temp1;
		Matrix temp2;
		
		/*
		temp 1 = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());
		Matrix localInternalLinkMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());
		
		dLog("get delta matrix 1.1: internal Link Matrix transBmult");

		temp1 = membershipMatrix.transBmult(membershipMatrix, temp1);
		
		dLog("get delta matrix 1.2: internal Link Matrix mult");
		
		localInternalLinkMatrix = linkMatrix.mult(temp1, localInternalLinkMatrix);
		dLog("get delta matrix 1.3: internal Link Matrix completed...");

		Matrix temp2 = linkMatrix.copy();
		dLog("get delta matrix 2: external Link Matrix");
		Matrix localExternalLinkMatrix = temp2.add(-1, localInternalLinkMatrix);
		*/
		
		
		
		
		
		
		temp1 = new LinkedSparseMatrix(sts.entities.size(), sts.classes.size());
		temp2 = new LinkedSparseMatrix(sts.entities.size(), sts.classes.size());
		
		Matrix IP = internalLinkMatrix.mult(membershipMatrix, temp1);
		
		Matrix EP = externalLinkMatrix.mult(membershipMatrix, temp2);
		
		Matrix IIP = getInvertedMembershipMatrix(IP);
		
		Matrix deltaMatrix = IIP.add(-1, EP);
		

		return deltaMatrix;
	}
	
	public Set<MoveMethodCommand> getPositiveRefactorings()
	{
		Matrix dm = getDeltaMatrix();
		
		
		TreeSet<MoveMethodCommand> mmcSet = new TreeSet<MoveMethodCommand>(new Comparator<MoveMethodCommand>() {

			@Override
			public int compare(MoveMethodCommand o1, MoveMethodCommand o2) {
				float dd = o1.getDeltaValue() - o2.getDeltaValue();
				if( dd < 0 )
				{
					return -1;
				}
				
				// do not permit equality
				return 1;
			}
			
		});
		
		
		System.err.print  ("dm.col  :" + dm.numColumns());
		System.err.println(" dm.row  :" + dm.numRows());
		System.err.print  ("classes :" + sts.classes.size());
		System.err.println(" entities:" + sts.entities.size());
		System.err.println("methods:" + sts.methods.size());
		System.err.println("fields:" + sts.fields.size());
		System.err.println("maxCandidate:" + maxCandidate);
		
		
		
		for( int i = 0; i < sts.methods.size(); i++ )
		{
			double minV = 9999999999f;
			int minCol = -1;
			
			for( int j = 0; j < dm.numColumns(); j++ )
			{
				if( minV > dm.get(i, j) || minCol == -1 )
				{
					minCol = j;
					minV = dm.get(i, j);
				}
			}
			
			//if( minV < 0 )
			{
				AOMMethod method = sts.methods.get(i);
				AOMClass clazz = sts.classes.get(minCol);
				MoveMethodCommand mmc = new MoveMethodCommand(method, clazz, (float)minV);
				mmcSet.add(mmc);
				if( mmcSet.size() > maxCandidate )
				{
					mmcSet.pollLast();
				}
			}
		}
		System.err.println("selCandidate:" + mmcSet.size());

		
		/*
		Iterator<MatrixEntry> iterator = dm.iterator();
		
		for( MatrixEntry entry = iterator.next(); iterator.hasNext(); entry = iterator.next() )
		{
			int row = entry.row();
			int col = entry.column();
			float v = (float) entry.get();
			
			if( v < 0 && row < sts.methods.size())
			{
				AOMMethod method = sts.methods.get(row);
				AOMClass clazz = sts.classes.get(col);
				MoveMethodCommand mmc = new MoveMethodCommand(method, clazz, (float)v);
				mmcSet.add(mmc);
				if( mmcSet.size() > maxCandidate )
				{
					mmcSet.remove(mmcSet.last());
				}
			}
		}
		*/


		return mmcSet;
	}
	
	@Override
	public void moveMethodPerformed(AOMClass fromClass, AOMMethod method, AOMClass toClass, boolean isRollbackAction)
	{
		membershipMatrix.set(method.getIndex(), fromClass.getIndex(), 0);
		membershipMatrix.set(method.getIndex(), toClass.getIndex(), 1);
		
		
		if( method.getOwnedScope() != null )
		{
			for(StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
			{
				if( method.getOwner() == sfa.getAccessedField().getOwner() )
				{
					internalLinkMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
					internalLinkMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
					externalLinkMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 0);
					externalLinkMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 0);

				}
				else
				{
					internalLinkMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 0);
					internalLinkMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 0);
					externalLinkMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
					externalLinkMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
				}
				
				// Experimental: Cohesive factor
				for(StaticFieldAccess sfaReverse: sfa.getAccessedField().getStaticReferer() )
				{
					AOMMethod cohesiveMethod = sfaReverse.getAccessingScope().getOwner();
					
					if( cohesiveMethod != method )
					{
						if( method.getOwner() == cohesiveMethod.getOwner() )
						{
							internalLinkMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 1);
							internalLinkMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 1);
							externalLinkMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 0);
							externalLinkMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 0);
						}
						else
						{
							internalLinkMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 0);
							internalLinkMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 0);
							externalLinkMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 1);
							externalLinkMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 1);
						}							
					}
				}
				
			}

			for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
			{
				if( method.getOwner() == smc.getCallee().getOwner() )
				{
					internalLinkMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 1);
					internalLinkMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
					externalLinkMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 0);
					externalLinkMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 0);
				}
				else
				{
					internalLinkMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 0);
					internalLinkMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 0);
					externalLinkMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 1);
					externalLinkMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
				}
			}
		}	
	}


	@Override
	public Set<MoveMethodCommand> getCandidates() {
		return this.getPositiveRefactorings();
	}
}
