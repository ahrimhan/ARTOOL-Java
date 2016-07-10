package kr.ac.kaist.se.deltatable.armatrix;

import org.ojalgo.access.Access1D;
import org.ojalgo.matrix.store.SparseStore;

public class ARMMultiply extends ARMObject {

	@Override
	protected void evalCell(ARMObject armObject, int sourceIdx, int row, int column, double oldValue, double newValue) {
		SparseStore<Double> matrix = getResultMatrix();
		switch(sourceIdx)
		{
		case 0:
			for(int i = 0; i < source[1].getColumnSize(); i++ )
			{
				double newMyValue = 0;
				for( int j = 0; j < source[1].getRowSize(); j++ )
				{
				 	newMyValue += source[0].getResultMatrix().get(row, j) * source[1].getResultMatrix().get(j, i);
				}
				matrix.set(row, i, newMyValue);
			}
			commitRowChange(row, null, matrix.sliceRow(row,0));
			break;
		case 1:
			for(int i = 0; i < source[0].getRowSize(); i++ )
			{
				double newMyValue = 0;
				for( int j = 0; j < source[0].getColumnSize(); j++ )
				{
				 	newMyValue += source[0].getResultMatrix().get(i, j) * source[1].getResultMatrix().get(j, column);
				}
				matrix.set(i, column, newMyValue);
			}	
			commitColumnChange(column, null, matrix.sliceColumn(0, column));
			break;
		default:
			throw new IllegalArgumentException("Illegal sourceIdx");
		}	
	}

	@Override
	protected void evalRow(ARMObject armObject, int sourceIdx, int row, Access1D<Double> oldSlice,
			Access1D<Double> newSlice) {
		SparseStore<Double> matrix = getResultMatrix();
		
		switch(sourceIdx)
		{
		case 0:
			for(int i = 0; i < source[1].getColumnSize(); i++ )
			{
				double newMyValue = 0;
				for( int j = 0; j < source[1].getRowSize(); j++ )
				{
				 	newMyValue += source[0].getResultMatrix().get(row, j) * source[1].getResultMatrix().get(j, i);
				}
				matrix.set(row, i, newMyValue);
			}
			commitRowChange(row, null, matrix.sliceRow(row,0));
			break;
		case 1:
			source[0].getResultMatrix().multiply(source[1].getResultMatrix());
			commitMatrixChange(null, matrix);
			break;
		default:
			throw new IllegalArgumentException("Illegal sourceIdx");
		}
	}

	@Override
	protected void evalColumn(ARMObject armObject, int sourceIdx, int column, Access1D<Double> oldSlice,
			Access1D<Double> newSlice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void evalMatrix(ARMObject armObject, int sourceIdx, SparseStore<Double> oldMatrix,
			SparseStore<Double> newMatrix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getRowSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getColumnSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
