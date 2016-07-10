package kr.ac.kaist.se.deltatable.armatrix;

import org.ojalgo.access.Access1D;
import org.ojalgo.matrix.store.SparseStore;

public class ARMChangeEvent<T> {	
	private ARMChangeEventType type;
	private ARMObject object;
	private int column;
	private int row;
	private T oldValue;
	private T newValue;
	
	
	private ARMChangeEvent(ARMChangeEventType type, ARMObject matrix, int row, int column, T oldValue, T newValue)
	{
		this.object = matrix;
		this.column = column;
		this.row = row;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	public static ARMChangeEvent<Double> cellChange(ARMObject object, int row, int column, Double oldValue, Double newValue)
	{
		ARMChangeEvent<Double> event = new ARMChangeEvent<Double>(ARMChangeEventType.CellChanged, object, row, column, oldValue, newValue);
		return event;
	}
	
	public static ARMChangeEvent<Access1D<Double>> rowChange(ARMObject object, int row, Access1D<Double> oldSlice, Access1D<Double> newSlice)
	{
		ARMChangeEvent<Access1D<Double>> event = new ARMChangeEvent<Access1D<Double>>(ARMChangeEventType.RowChanged, object, row, -1, oldSlice, newSlice);
		return event;
	}
	
	public static ARMChangeEvent<Access1D<Double>> columnChange(ARMObject object, int column, Access1D<Double> oldSlice, Access1D<Double> newSlice)
	{
		ARMChangeEvent<Access1D<Double>> event = new ARMChangeEvent<Access1D<Double>>(ARMChangeEventType.ColumnChanged, object, -1, column, oldSlice, newSlice);
		return event;
	}
	
	public static ARMChangeEvent<SparseStore<Double>> matrixChange(ARMObject object, SparseStore<Double> oldMatrix, SparseStore<Double> newMatrix)
	{
		ARMChangeEvent<SparseStore<Double>> event = new ARMChangeEvent<SparseStore<Double>>(ARMChangeEventType.MatrixChanged, object, -1, -1, oldMatrix, newMatrix);
		return event;
	}
	
	public ARMObject getObject()
	{
		return object;
	}
	
	public ARMChangeEventType getType() {
		return type;
	}
	
	public int getColumn() {
		return column;
	}
		
	public int getRow() {
		return row;
	}	
	
	public T getOldValue()
	{
		return oldValue;
	}
	
	public T getNewValue()
	{
		return newValue;
	}
}
