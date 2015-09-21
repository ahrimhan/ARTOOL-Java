package kr.ac.kaist.se.artool.search.fitness;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.search.MoveMethodEventListener;
import no.uib.cipr.matrix.DenseVector;
import no.uib.cipr.matrix.Matrix;
import no.uib.cipr.matrix.sparse.LinkedSparseMatrix;

public class EPMEngine extends FitnessFunction implements MoveMethodEventListener {
	private Matrix membershipMatrix;
	private SystemEntitySet sts;	
	private no.uib.cipr.matrix.Vector oneVector;
	
	private ExecutorService executorService;
	private final int executorPoolSize = 8;
	
	public EPMEngine(SystemEntitySet sts)
	{
		this.sts = sts;
		initMembershipMatrix();
		initEntityMatrix();
		
		
		
		oneVector = new DenseVector(sts.entities.size());
		
		for( int i = 0; i < sts.entities.size(); i++ )
		{
			oneVector.set(i, 1);
		}
		
		executorService = Executors.newFixedThreadPool(executorPoolSize);
	}
	
	
	private void initMembershipMatrix()
	{
		LinkedSparseMatrix mm = new LinkedSparseMatrix(sts.entities.size(), sts.classes.size());
		membershipMatrix = mm;
		
		for( AOMClass clazz : sts.classes )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				try
				{
					membershipMatrix.set(method.getIndex(), clazz.getIndex(), 1);
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
				membershipMatrix.set(field.getIndex(), clazz.getIndex(), 1);
			}
		}

	}
	
	Matrix entityMatrix;
	
	private void initEntityMatrix()
	{
		entityMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.entities.size());

		for (AOMMethod method : sts.methods) {
			
			if( method.getOwnedScope() != null )
			{
				for(StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
				{
					entityMatrix.set(sfa.getAccessedField().getIndex(), method.getIndex(), 1);
//					entityMatrix.set(method.getIndex(), sfa.getAccessedField().getIndex(),  1);
				}

				for(StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
				{
					entityMatrix.set(method.getIndex(), smc.getCallee().getIndex(), 1);
				}
			}
		}
	}
	
	private void printMatrixSpec(String prefix, Matrix matrix)
	{
		System.out.println(prefix + ": " + matrix.numRows() + " x " + matrix.numColumns());
	}
	
	
	
	private Matrix getIntersectMatrix()
	{
		Matrix intersectMatrix = new LinkedSparseMatrix(sts.entities.size(), sts.classes.size());
		
		intersectMatrix = entityMatrix.mult(membershipMatrix, intersectMatrix);
		
		return intersectMatrix;
	}
	
//	private class RowColVectorMatrix
//	{
//		no.uib.cipr.matrix.Vector colVector;
//		no.uib.cipr.matrix.Vector rowVector;
//		
//		public RowColVectorMatrix(no.uib.cipr.matrix.Vector rowVector, no.uib.cipr.matrix.Vector colVector)
//		{
//			this.rowVector = rowVector;
//			this.colVector = colVector;
//		}
//		
//		public double getValue(int row, int col)
//		{
//			return rowVector.get(row) + colVector.get(col);
//		}
//	}
		
	
//	private RowColVectorMatrix getSumMatrix()
//	{
//			
//		return new RowColVectorMatrix(rowSumColVector, colSumRowVector);
//	}
	
	public class EPMHelper implements Callable<Float>
	{
		
		private Matrix intersectMatrix; 
		private int id;
		private int stepSize;
		private no.uib.cipr.matrix.Vector rowVector;
		private no.uib.cipr.matrix.Vector colVector;
		/*
		no.uib.cipr.matrix.Vector rowSumColVector = new DenseVector(sts.entities.size());
		rowSumColVector = entityMatrix.mult(oneVector, rowSumColVector);
		
		no.uib.cipr.matrix.Vector colSumRowVector = new DenseVector(sts.classes.size());		
		colSumRowVector = membershipMatrix.transMult(oneVector, colSumRowVector);
		*/		
		public EPMHelper(Matrix intersectMatrix, no.uib.cipr.matrix.Vector rowVector, no.uib.cipr.matrix.Vector colVector, int id, int stepSize)
		{
			this.intersectMatrix = intersectMatrix;
			this.id = id;
			this.stepSize = stepSize;
			this.rowVector = rowVector;
			this.colVector = colVector;
		}
		
		@Override
		public Float call() throws Exception {
			float epm = 0;
			
			float internalTotal = 0;
			float externalTotal = 0;
			float externalCountTotal = 0;
			
			long intersectTotal = 0;
			long sumTotal = 0;
			long unionTotal = 0;
			long linkTotal = 0;

			for( int j = 0 ; j < sts.entities.size(); j++ )
			{
				linkTotal += rowVector.get(j);
			}
			
			
			for( int i = id; i < sts.classes.size(); i+= stepSize )
			{
				float internal = 0;
				float external = 0;
				int externalCount = 0;
				float epmc = 0;
				double vv, vs;
				float union_result = 0;
				for( int j = 0 ; j < sts.entities.size(); j++ )
				{	
					vv = intersectMatrix.get(j, i);
					vs = rowVector.get(j) + colVector.get(i) - membershipMatrix.get(j, i);
					
					if( vs <= vv ) continue;
					
					union_result = (float)(vs - vv);
					intersectTotal += vv;
					sumTotal += vs;
					unionTotal += union_result;
					
					vv = 1f - (vv / union_result);
					
					if( membershipMatrix.get(j, i) > 0 )
					{
						internal += vv;
//						internalCount ++;
					}
					else
					{
						external += vv;
						externalCount ++;
					}
				}
				
				if( external != 0 )
//					&& internalCount != 0 )
				{
					internalTotal += internal;
					externalTotal += external;
					externalCountTotal += externalCount;
					
					epmc = internal * externalCount / external ;
					epm += epmc;
				}
			}
			
			System.err.println("interal total:" + internalTotal);
			System.err.println("external total:" + externalTotal);
			System.err.println("external count total:" + externalCountTotal);
			System.err.println("intersect total:" + intersectTotal);
			System.err.println("sum total:" + sumTotal);
			System.err.println("union total:" + unionTotal);
			System.err.println("Java Total Link Count:" + linkTotal);
			System.err.println("total epm:" + epm);
			return epm;		
		}
	}
	
	
	
	public float calculate()
	{
		Matrix intersectMatrix = getIntersectMatrix();
		
		
		
		no.uib.cipr.matrix.Vector rowSumColVector = new DenseVector(sts.entities.size());
		rowSumColVector = entityMatrix.mult(oneVector, rowSumColVector);
		
		no.uib.cipr.matrix.Vector colSumRowVector = new DenseVector(sts.classes.size());		
		colSumRowVector = membershipMatrix.transMult(oneVector, colSumRowVector);
		
		
		float epm = 0;

		/*
		@SuppressWarnings("unchecked")
		Future<Float>[] future = new Future[executorPoolSize]; 
		
		
		for( int i = 0; i < executorPoolSize; i++ )
		{
			future[i] = executorService.submit(new EPMHelper(intersectMatrix, sumMatrix, i));
		}
		
		for( int i = 0; i < executorPoolSize; i++ )
		{
			try {
				epm += future[i].get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Future crash");
			}
		}
		*/
		EPMHelper hh = new EPMHelper(intersectMatrix, rowSumColVector, colSumRowVector, 0, 1);
		
		try {
			epm = hh.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		epm = epm / sts.entities.size();
		
		return epm;
	}
	
	public boolean isBiggerValueMeantBetterFitness()
	{
		return false;
	}
	
	public void moveMethodPerformed(AOMClass fromClass, AOMMethod method, AOMClass toClass, boolean isRollbackAction)
	{
		membershipMatrix.set(method.getIndex(), fromClass.getIndex(), 0);
		membershipMatrix.set(method.getIndex(), toClass.getIndex(), 1);
	}
}
