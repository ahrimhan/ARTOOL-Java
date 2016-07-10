package kr.ac.kaist.se.deltatable.armatrix.result;

public interface ARMResult<T> {
	void result(int row, int col, T oldValue, T newValue);
}
