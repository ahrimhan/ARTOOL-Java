package kr.ac.kaist.se.deltatable.armatrix;

import java.util.LinkedList;

import org.ojalgo.access.Access1D;
import org.ojalgo.matrix.store.SparseStore;

public abstract class ARMObject {	
	private LinkedList<ARMChangeEvent<SparseStore<Double>>> eventMatrixQueue;
	private LinkedList<ARMChangeEvent<Access1D<Double>>> eventRowQueue;
	private LinkedList<ARMChangeEvent<Access1D<Double>>> eventColumnQueue;
	private LinkedList<ARMChangeEvent<Double>> eventCellQueue;
	
//	private LinkedList<ARMChangeEvent<?>> processingQueue;
	private SparseStore<Double> resultMatrix = null;
	protected ARMObject source[] = null;

	public ARMObject()
	{
		eventMatrixQueue = new LinkedList<ARMChangeEvent<SparseStore<Double>>>();
		eventRowQueue = new LinkedList<ARMChangeEvent<Access1D<Double>>>();
		eventColumnQueue = new LinkedList<ARMChangeEvent<Access1D<Double>>>();
		eventCellQueue = new LinkedList<ARMChangeEvent<Double>>();
		
//		processingQueue =new LinkedList<ARMChangeEvent<?>>();
		source = new ARMObject[2];
		source[0] = null;
		source[1] = null;
	}
	
	public void setSource(ARMObject s1, ARMObject s2)
	{
		source[0] = s1;
		source[1] = s2;
	}
	
	public void setSource(ARMObject s1)
	{
		source[0] = s1;
	}
	
	protected void commitCellChange(int row, int column, double oldValue, double newValue)
	{
		eventCellQueue.add(ARMChangeEvent.cellChange(this, row, column, oldValue, newValue));
	}
	
	protected void commitRowChange(int row, Access1D<Double> oldSlice, Access1D<Double> newSlice)
	{
		eventRowQueue.add(ARMChangeEvent.rowChange(this, row, oldSlice, newSlice));
	}
	
	protected void commitColumnChange(final int column, Access1D<Double> oldSlice, Access1D<Double> newSlice)
	{
		eventColumnQueue.add(ARMChangeEvent.columnChange(this, column, oldSlice, newSlice));
	}
	
	protected void commitMatrixChange(SparseStore<Double> oldMatrix, SparseStore<Double> newMatrix)
	{
		eventMatrixQueue.add(ARMChangeEvent.matrixChange(this, oldMatrix, newMatrix));
	}
	
	public SparseStore<Double> getResultMatrix()
	{
		if( resultMatrix == null )
		{
			resultMatrix = SparseStore.makePrimitive(getRowSize(), getColumnSize());
		}
		
		return resultMatrix;
	}
	
	protected void setResultMatrix(SparseStore<Double> matrix)
	{
		this.resultMatrix = matrix;
	}
	
	
	public final void eval()
	{
		if( source[0] != null ) source[0].eval();
		if( source[1] != null ) source[1].eval();
		
		
		if( source[0].eventMatrixQueue.size() > 0 )
		{
			evalForEvent(source[0].eventMatrixQueue.getFirst(), 0);
		}
		else if( source[1].eventMatrixQueue.size() > 0 )
		{
			evalForEvent(source[1].eventMatrixQueue.getFirst(), 1);
		}
		else
		{
			source[0].eventColumnQueue.forEach(event -> {evalForEvent(event, 0);});
			source[1].eventRowQueue.forEach(event -> {evalForEvent(event, 1);});
			source[0].eventRowQueue.forEach(event -> {evalForEvent(event, 0);});
			source[1].eventColumnQueue.forEach(event -> {evalForEvent(event, 1);});
			source[0].eventCellQueue.forEach(event -> {evalForEvent(event, 0);});
			source[1].eventCellQueue.forEach(event -> {evalForEvent(event, 1);});
		}
	}
	
	@SuppressWarnings("unchecked")
	private void evalForEvent(ARMChangeEvent<?> changeEvent, int idx)
	{
		switch(changeEvent.getType())
		{
		case CellChanged:
			evalCell(changeEvent.getObject(), idx, changeEvent.getRow(), changeEvent.getColumn(), (Double)changeEvent.getOldValue(), (Double)changeEvent.getNewValue());
			break;
		case RowChanged:
			evalRow(changeEvent.getObject(), idx, changeEvent.getRow(), (Access1D<Double>)changeEvent.getOldValue(), (Access1D<Double>)changeEvent.getNewValue()); 
			break;
		case ColumnChanged:
			evalColumn(changeEvent.getObject(), idx, changeEvent.getColumn(), (Access1D<Double>)changeEvent.getOldValue(), (Access1D<Double>)changeEvent.getNewValue());
			break;
		case MatrixChanged:
			evalMatrix(changeEvent.getObject(), idx, (SparseStore<Double>)changeEvent.getOldValue(), (SparseStore<Double>)changeEvent.getNewValue());
			break;
		default:
			break;
		}
	}
	
	protected final void clearEvents()
	{
		eventMatrixQueue.clear();
		eventRowQueue.clear();
		eventColumnQueue.clear();
		eventCellQueue.clear();
		
		if( source[0] != null ) source[0].clearEvents();
		if( source[1] != null ) source[1].clearEvents();
	}

	
//	abstract protected void scheduleInDetail(LinkedList<ARMChangeEvent<?>>[] eventQueue, LinkedList<ARMChangeEvent<?>>[] processingQueue);

	abstract protected void evalCell(ARMObject armObject, int sourceIdx, int row, int column, double oldValue, double newValue);
	
	abstract protected void evalRow(ARMObject armObject, int sourceIdx, int row, Access1D<Double> oldSlice, Access1D<Double> newSlice);
	
	abstract protected void evalColumn(ARMObject armObject, int sourceIdx, int column, Access1D<Double> oldSlice, Access1D<Double> newSlice);

	abstract protected void evalMatrix(ARMObject armObject, int sourceIdx, SparseStore<Double> oldMatrix, SparseStore<Double> newMatrix);	
		
	abstract protected int getRowSize();
	
	abstract protected int getColumnSize();
}
