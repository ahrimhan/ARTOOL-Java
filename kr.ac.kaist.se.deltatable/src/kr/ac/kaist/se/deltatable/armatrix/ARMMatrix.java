package kr.ac.kaist.se.deltatable.armatrix;

import org.ojalgo.access.Access1D;
import org.ojalgo.matrix.store.SparseStore;

public class ARMMatrix extends ARMObject {	
	private int rowSize;
	private int columnSize;
	
	public ARMMatrix(int row, int col)
	{
		super();
		this.rowSize = row;
		this.columnSize = col;
	}
	
	@Override
	public int getColumnSize()
	{
		return columnSize;
	}
	
	@Override
	public int getRowSize()
	{
		return rowSize;
	}
	
	public void set(int row, int col, double value)
	{
		double oldValue = getResultMatrix().get(row, col);
		getResultMatrix().set(row, col, value);
		commitCellChange(row, col, oldValue, value);
	}
		
	public void setMatrix(SparseStore<Double> matrix)	
	{	
		SparseStore<Double> oldMatrix = super.getResultMatrix();
		super.setResultMatrix(matrix);
		commitMatrixChange(oldMatrix, matrix);
	}

	@Override
	protected void evalCell(ARMObject armObject, int sourceIdx, int row, int column, double oldValue, double newValue) {	
	}

	@Override
	protected void evalRow(ARMObject armObject, int sourceIdx, int row, Access1D<Double> oldSlice, Access1D<Double> newSlice) {
	}

	@Override
	protected void evalColumn(ARMObject armObject, int sourceIdx, int column, Access1D<Double> oldSlice, Access1D<Double> newSlice) {
		
	}

	@Override
	protected void evalMatrix(ARMObject armObject, int sourceIdx, SparseStore<Double> oldMatrix, SparseStore<Double> newMatrix) {
		
	}
}
