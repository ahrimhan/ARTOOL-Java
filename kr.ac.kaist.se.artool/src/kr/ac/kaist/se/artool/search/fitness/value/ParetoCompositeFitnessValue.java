package kr.ac.kaist.se.artool.search.fitness.value;

import java.util.Vector;

public class ParetoCompositeFitnessValue extends FitnessValue {
	public static final int INCOMPARABLE = 0xffffff;
	
	private Vector<AtomicFitnessValue> valueList = new Vector<AtomicFitnessValue>();
	
	public ParetoCompositeFitnessValue()
	{
		
	}
	
	public Vector<AtomicFitnessValue> getValueList()
	{
		return valueList;
	}
	
	public void addValue(AtomicFitnessValue value)
	{
		valueList.add(value);
	}
	
//	public int[] vectorCompareTo(ParetoCompositeFitnessValue compositeValue)
//	{
//		if( compositeValue.valueList.size() != valueList.size() )
//		{
//			return null;
//		}
//		
//		int[] ret = new int[valueList.size()];
//		
//		for(int i = 0; i < valueList.size(); i++ )
//		{
//			ret[i] = valueList.get(i).compareTo(compositeValue.valueList.get(i));
//		}
//		
//		return ret;
//	}

	@Override
	public int compareTo(FitnessValue f) {
		ParetoCompositeFitnessValue v = (ParetoCompositeFitnessValue)f;
		if( v.valueList.size() != valueList.size() ) throw new RuntimeException("Different size composite fitness");
		
		int less = 0;
		int greater = 0;
		
		for( int i = 0; i < valueList.size(); i++ )
		{
			int res = valueList.get(i).compareTo(v.valueList.get(i));
			
			if( res < 0 ) less++;
			if( res > 0 ) greater++;
		}
		
		if( greater == 0 && less > 0 ) return -1;
		if( greater > 0 && less == 0 ) return 1;
		if( greater == 0 && less == 0 ) return 0;
		
		return INCOMPARABLE;
	}

	@Override
	public float distance(FitnessValue f) {
		float ret = 0;
		ParetoCompositeFitnessValue v = (ParetoCompositeFitnessValue)f;
		
		if( v.valueList.size() != valueList.size() ) throw new RuntimeException("Different size composite fitness");
		
		for( int i = 0; i < valueList.size(); i++ )
		{
			float d = valueList.get(i).distance(v.valueList.get(i)); 
			ret += d * d;
		}
		
		ret = (float) Math.sqrt((double)ret);
		
		return ret;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for( AtomicFitnessValue value : valueList )
		{
			if( sb.length() != 0 ) sb.append(',');
			sb.append(value.toString());
		}
		
		return sb.toString();
	}
}
