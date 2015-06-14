package kr.ac.kaist.se.artool.search;

import no.uib.cipr.matrix.AbstractMatrix;
import no.uib.cipr.matrix.Matrix;

public class OneMatrix extends AbstractMatrix {

	protected OneMatrix(int numRows, int numColumns) {
		super(numRows, numColumns);
		// TODO Auto-generated constructor stub
	}

	@Override
    public Matrix copy() {
		return this;
	}
	
	@Override
    public void set(int row, int column, double value) {
    	
    }
	
	@Override
    public double get(int row, int column)
    {
    	return 1;
    }
}
