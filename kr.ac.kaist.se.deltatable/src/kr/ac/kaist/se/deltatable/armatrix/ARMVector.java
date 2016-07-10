package kr.ac.kaist.se.deltatable.armatrix;

import org.ojalgo.access.Access1D;

public class ARMVector implements Access1D<Double> {
	private double[] doubleValues;
	
	public ARMVector(double[] doubleValues)
	{
		this.doubleValues = doubleValues;
	}

	@Override
	public long count() {
		return doubleValues.length;
	}

	@Override
	public double doubleValue(long index) {
		return doubleValues[(int)index];
	}

	@Override
	public Double get(long index) {
		return doubleValues[(int)index];
	}
}
