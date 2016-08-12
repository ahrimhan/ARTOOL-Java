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
		
		int count = 0;
		
		for( int i = 0; i < array.length; i++ )
		{
			count += array[i] > cdv.array[i] ? 1 : (array[i] < cdv.array[i] ? -1 : 0); 
		}
		
		if( count == array.length ) return 1;
		else if( count == (-array.length) ) return -1;
		
		return 0;
	}
}
