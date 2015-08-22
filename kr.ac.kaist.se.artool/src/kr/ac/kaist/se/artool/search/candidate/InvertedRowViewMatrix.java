package kr.ac.kaist.se.artool.search.candidate;

import java.util.Iterator;

import no.uib.cipr.matrix.AbstractMatrix;
import no.uib.cipr.matrix.Matrix;
import no.uib.cipr.matrix.MatrixEntry;

public class InvertedRowViewMatrix extends AbstractMatrix {

	
	private double[] data;
	private int[] columns;
	
	public InvertedRowViewMatrix(Matrix A) {
		this(A, false);
	}
	
	protected InvertedRowViewMatrix(Matrix A, boolean copy)
	{
		super(A);
		
		if( !copy )
		{
			Iterator<MatrixEntry> iterator = A.iterator();
			
			data = new double[A.numRows()];
			columns = new int[A.numRows()];
			
			for( MatrixEntry entry = iterator.next(); iterator.hasNext(); entry = iterator.next() )
			{
				data[entry.row()] = entry.get();
				columns[entry.row()] = entry.column();
				
			}
		}
	}
	
	@Override
	public Matrix copy() {
		InvertedRowViewMatrix ret = new InvertedRowViewMatrix(this, true);
		ret.data = new double[this.data.length];
		ret.columns = new int[this.columns.length];

		System.arraycopy(this.data, 0, ret.data, 0, this.data.length);
		System.arraycopy(this.columns, 0, ret.columns, 0, this.columns.length);

		return ret;
	}

	@Override
	public void set(int row, int column, double value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double get(int row, int column) {
		return columns[row] == column ? 0 : data[row];
	}	
	

}
