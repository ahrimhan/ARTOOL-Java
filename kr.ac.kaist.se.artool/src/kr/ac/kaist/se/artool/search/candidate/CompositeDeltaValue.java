package kr.ac.kaist.se.artool.search.candidate;

public class CompositeDeltaValue extends DeltaValue {
	private float[] array;
	
	public CompositeDeltaValue(float[] array)
	{
		this.array = array;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for( float f : array )
		{
			if( sb.length() != 0 )
			{
				sb.append(',');
			}
			sb.append(Float.toString(f));
		}
		
		return sb.toString();
	}

	@Override
	public int compareTo(DeltaValue o) {
		CompositeDeltaValue cdv = (CompositeDeltaValue)o;
		
		if( cdv.array.length != array.length ) throw new RuntimeException("Array size is not matched");
		
		boolean less = false;
		boolean greater = false;
		
		for( int i = 0; i < array.length; i++ )
		{
			less = array[i] < cdv.array[i] ? true : less;
			greater = array[i] > cdv.array[i] ? true : greater; 
		}
		
		if( !greater && less ) return -1;
		if( !less && greater ) return 1;
		
		return 0;
	}
}
