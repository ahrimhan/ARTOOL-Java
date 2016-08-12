package kr.ac.kaist.se.artool.search.fitness.value;

import java.util.Vector;

public class ParetoCompositeFitnessValue extends FitnessValue {

	private Vector<AtomicFitnessValue> valueList = new Vector<AtomicFitnessValue>();
	
	public ParetoCompositeFitnessValue()
	{
		
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
		
		int score = 0;
		
		// XXX: TODO
		
		for( int i = 0; i < valueList.size(); i++ )
		{
			score += valueList.get(i).compareTo(v.valueList.get(i));
		}
		
//		int ret = score > 0 ? 1 : (score < 0 ? -1 : 0 ); 
		
		int ret = score == valueList.size() ? 1 : (score == (-valueList.size()) ? -1 : 0);
		
		return ret;
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
