package kr.ac.kaist.se.deltatable.armatrix;

public enum ARMChangeEventType {
	CellChanged(4, 4), RowChanged(2, 3), ColumnChanged(3, 2), MatrixChanged(1, 1);
	
	private final int rowPriorityValue;
	private final int columnPriorityValue;
	
	ARMChangeEventType(int row, int col)
	{
		rowPriorityValue = row;
		columnPriorityValue = col;
	}
	
	public int getRowPriorityValue()
	{
		return rowPriorityValue;
	}
	
	public int getColumnPriorityValue()
	{
		return columnPriorityValue;
	}
}
