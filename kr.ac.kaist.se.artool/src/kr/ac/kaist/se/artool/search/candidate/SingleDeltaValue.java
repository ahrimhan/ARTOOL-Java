package kr.ac.kaist.se.artool.search.candidate;

public class SingleDeltaValue extends DeltaValue {
	private float d;

	public SingleDeltaValue(float d)
	{
		this.d = d;
	}
	
	public String toString()
	{
		return Float.toString(d);
	}

	@Override
	public int compareTo(DeltaValue o) {
		SingleDeltaValue sdv = (SingleDeltaValue)o;
		return d > sdv.d ? 1 : (d < sdv.d ? -1 : 0);
	}
}
