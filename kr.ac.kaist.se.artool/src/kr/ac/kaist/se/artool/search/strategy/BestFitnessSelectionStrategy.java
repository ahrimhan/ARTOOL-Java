package kr.ac.kaist.se.artool.search.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import kr.ac.kaist.se.artool.search.fitness.value.AtomicFitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.FitnessValue;
import kr.ac.kaist.se.artool.search.fitness.value.ParetoCompositeFitnessValue;

public class BestFitnessSelectionStrategy extends
		AbstractRefactoringSelectionStrategy {

	private Vector<FitnessValue> valueList = new Vector<FitnessValue>();
	private FitnessValue prevValue = null;
	private AtomicFitnessValue[] minValues;
	private AtomicFitnessValue[] maxValues;
	
	
	public BestFitnessSelectionStrategy(FitnessValue initialFitnessValue) {
		super(initialFitnessValue);
		
		prevValue = initialFitnessValue;
		
		if( prevValue instanceof ParetoCompositeFitnessValue )
		{
			ParetoCompositeFitnessValue v = (ParetoCompositeFitnessValue) prevValue;
			minValues = new AtomicFitnessValue[v.getValueList().size()];
			maxValues = new AtomicFitnessValue[v.getValueList().size()];
			
			for( int i = 0; i < minValues.length; i++ )
			{
				minValues[i] = v.getValueList().get(i).getMaxInstance();
				maxValues[i] = v.getValueList().get(i).getMinInstance();
			}
		}
	}

	@Override
	public synchronized boolean next(FitnessValue obj) {
		valueList.addElement(obj);
		
		if( prevValue instanceof ParetoCompositeFitnessValue )
		{
			ParetoCompositeFitnessValue v = (ParetoCompositeFitnessValue) obj;

			for( int i = 0; i < minValues.length; i++ )
			{
				if( minValues[i].compareTo(v.getValueList().get(i)) > 0 )
				{
					minValues[i] = v.getValueList().get(i);
				}
				
				if( maxValues[i].compareTo(v.getValueList().get(i)) < 0 )
				{
					maxValues[i] = v.getValueList().get(i);
				}
			}
		}
		
		return true;
	}

	@Override
	public FitnessValue done() {
		FitnessValue value = null;
		Comparator<FitnessValue> paretoComparator = null;

		if( prevValue instanceof ParetoCompositeFitnessValue )
		{
			paretoComparator = new Comparator<FitnessValue>()
			{
				@Override
				public int compare(FitnessValue o1, FitnessValue o2) {
					int res = o1.compareTo(o2);
					if( res != ParetoCompositeFitnessValue.INCOMPARABLE ) return res;
					
					
					ParetoCompositeFitnessValue v1 = (ParetoCompositeFitnessValue)o1;
					ParetoCompositeFitnessValue v2 = (ParetoCompositeFitnessValue)o2;
					
					double t = 0;
					double d1 = 0;
					double d2 = 0;
					
					for( int i = 0; i < minValues.length; i++ )
					{
						t = v1.getValueList().get(i).distance(minValues[i]) / maxValues[i].distance(minValues[i]);
						d1 += t * t;
						t = v2.getValueList().get(i).distance(minValues[i]) / maxValues[i].distance(minValues[i]);
						d2 += t * t;
					}		
					
					if( d1 > d2 ) return 1;
					else if( d1 < d2 ) return -1;
					else return 0;
				}
				
			};
		}
		
		
		try
		{
			if( paretoComparator != null )
			{
				value = Collections.max(valueList, paretoComparator);
			}
			else
			{
				value = Collections.max(valueList);
			}
			
			if( paretoComparator != null  && paretoComparator.compare(prevValue, value) < 0 )
			{
				prevValue = value;
			}
			else if( paretoComparator == null && prevValue.compareTo(value) < 0 )
			{
				prevValue = value;
			}
			else
			{
				value = null;
			}
		}
		catch(Exception ex)
		{
			value = null;
		}
		
		
		valueList.clear();
		
		return value;
	}
	
	@Override
	public boolean restrictCandidateCount()
	{
		return true;
	}

}
