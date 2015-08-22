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
	Matrix internalCouplingMatrix;
	Matrix externalCouplingMatrix;
	Matrix internalCohesionMatrix;
	Matrix externalCohesionMatrix;
	Matrix adjustMatrix;
	int maxCandidate;
	Comparator<MoveMethodCommand> mmcComparator;
	
	private double cohesiveFactor = 1.0 / 3.0;
	private double couplingFactor = 2.0 / 3.0;
	
	public DeltaMatrixEngine(SystemEntitySet sts, int maxCandidate)
	{
		this.sts = sts;
		this.maxCandidate = maxCandidate;
		initBasicMatrix();	
		mmcComparator = new Comparator<MoveMethodCommand>() {

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
			
		};
	}
	
	
	private void initBasicMatrix()
	{
		membershipMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.classes.size());
		internalCouplingMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());
		externalCouplingMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());
		internalCohesionMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());
		externalCohesionMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());
		adjustMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.classes.size());
		
		
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
						internalCouplingMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
						internalCouplingMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
					}
					else
					{
						externalCouplingMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
						externalCouplingMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
					}
					
					
					
					// Experimental: Cohesive factor
					for(StaticFieldAccess sfaReverse: sfa.getAccessedField().getStaticReferer() )
					{
						AOMMethod cohesiveMethod = sfaReverse.getAccessingScope().getOwner();
						
						if( cohesiveMethod != method )
						{
							if( method.getOwner() == cohesiveMethod.getOwner() )
							{
								internalCohesionMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 1);
								internalCohesionMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 1);
							}
							else
							{
								externalCohesionMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 1);
								externalCohesionMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 1);
							}							
						}
					}
				}

				for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
				{
					if( method.getOwner() == smc.getCallee().getOwner() )
					{
						internalCouplingMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 1);
						internalCouplingMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
					}
					else
					{
						externalCouplingMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 1);
						externalCouplingMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
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
	
	public class MatrixTuple
	{
		public Matrix deltaMatrix;
		public Matrix externalMatrix;
		public Matrix invertedInternalMatrix;
	}
	
	public void setCohesiveFactorRate(double coupling, double cohesive)
	{
		cohesiveFactor = cohesive / (coupling + cohesive);
		couplingFactor = coupling / (coupling + cohesive);
		
		System.err.println("CohesiveFactor:"+cohesiveFactor);
		System.err.println("CouplingFactor:"+couplingFactor);
	}
	
	protected MatrixTuple getDeltaMatrix()
	{
		
		Matrix temp1;
		
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
		//temp2 = new LinkedSparseMatrix(sts.entities.size(), sts.classes.size());
		
		Matrix internalLinkMatrix;
		
		internalLinkMatrix = internalCouplingMatrix.copy();
//		internalLinkMatrix = internalLinkMatrix.scale(couplingFactor);
		internalLinkMatrix = internalLinkMatrix.add(cohesiveFactor/couplingFactor, internalCohesionMatrix);
		
		Matrix externalLinkMatrix;
		
		externalLinkMatrix = externalCouplingMatrix.copy();
//		externalLinkMatrix = externalLinkMatrix.scale(couplingFactor);
		externalLinkMatrix = externalLinkMatrix.add(cohesiveFactor/couplingFactor, externalCohesionMatrix);
		
		
		Matrix IP = internalLinkMatrix.mult(membershipMatrix, temp1);
		Matrix IIP = getInvertedMembershipMatrix(IP);
		
		MatrixTuple tuple = new MatrixTuple();
		
		Matrix I = IIP.copy();
		
		Matrix EP = externalLinkMatrix.mult(membershipMatrix, temp1);

		
		tuple.deltaMatrix = IIP.add(-1, EP);
		tuple.externalMatrix = EP;
		tuple.invertedInternalMatrix = I;
		
		return tuple;
	}
	
	private boolean adjustOption = false;
	
	public void setAdjust(boolean adjustOption)
	{
		this.adjustOption = adjustOption;
	}
	
	public Set<MoveMethodCommand> getPositiveRefactorings()
	{
		MatrixTuple tuple = getDeltaMatrix();
		Matrix dm = tuple.deltaMatrix;
		Matrix iim = tuple.invertedInternalMatrix;
		
		if( adjustOption )
		{
			dm = dm.add(adjustMatrix);
		}
		
		TreeSet<MoveMethodCommand> mmcSet = new TreeSet<MoveMethodCommand>(mmcComparator);
		
		
		for( int i = 0; i < sts.methods.size(); i++ )
		{
	
			for( int j = 0; j < dm.numColumns(); j++ )
			{
				
				AOMMethod method = sts.methods.get(i);
				AOMClass clazz = sts.classes.get(j);
				if( method.getOwnedScope() != null && (method.getOwnedScope().getStaticMethodCalls().size() + method.getOwnedScope().getStaticFieldAccesses().size() + method.getStaticReferer().size()) != 0)
				{	
					//if( dm.get(i,  j) < 0 )
					{
						
						MoveMethodCommand mmc = new MoveMethodCommand(method, clazz, (float)(dm.get(i, j) / (iim.get(i, j) + 1)) );
						mmcSet.add(mmc);
						if( mmcSet.size() > maxCandidate )
						{
							mmcSet.pollLast();
						}
					}
				}
			}
		}
		
		/*
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
		*/

		
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
		
		if( adjustOption )
		{
			adjustMatrix = adjustMatrix.scale(0.9);

			for (MoveMethodCommand mmc : mmcSet) {
				adjustMatrix.add(mmc.getMethod().getIndex(), mmc.getToClass()
						.getIndex(), 1.31);
			}
		}


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
					internalCouplingMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
					internalCouplingMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
					externalCouplingMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 0);
					externalCouplingMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 0);

				}
				else
				{
					internalCouplingMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 0);
					internalCouplingMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 0);
					externalCouplingMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
					externalCouplingMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(), 1);
				}
				
				// Experimental: Cohesive factor
				for(StaticFieldAccess sfaReverse: sfa.getAccessedField().getStaticReferer() )
				{
					AOMMethod cohesiveMethod = sfaReverse.getAccessingScope().getOwner();
					
					if( cohesiveMethod != method )
					{
						if( method.getOwner() == cohesiveMethod.getOwner() )
						{
							internalCohesionMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 1);
							internalCohesionMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 1);
							externalCohesionMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 0);
							externalCohesionMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 0);
						}
						else
						{
							internalCohesionMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 0);
							internalCohesionMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 0);
							externalCohesionMatrix.set(cohesiveMethod.getIndex(), method.getIndex(), 1);
							externalCohesionMatrix.set(method.getIndex(), cohesiveMethod.getIndex(), 1);
						}							
					}
				}
			}

			for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
			{
				if( method.getOwner() == smc.getCallee().getOwner() )
				{
					internalCouplingMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 1);
					internalCouplingMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
					externalCouplingMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 0);
					externalCouplingMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 0);
				}
				else
				{
					internalCouplingMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 0);
					internalCouplingMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 0);
					externalCouplingMatrix.set(smc.getCallee().getIndex(), method.getIndex(), 1);
					externalCouplingMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
				}
			}
		}	
	}


	@Override
	public Set<MoveMethodCommand> getCandidates() {
		return this.getPositiveRefactorings();
	}
}
