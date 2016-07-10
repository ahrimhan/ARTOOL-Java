package kr.ac.kaist.se.deltatable.armatrix;

public interface ARMObserver {
//	void columnChanged(ARMatrix matrix, int col, double[] values);
//	void rowChanged(ARMatrix matrix, int row, double[] values);
//	void cellChanged(ARMatrix matrix, int row, int col, double value);
//	
	void changePerformed(ARMChangeEvent<?> changeEvent);
}
