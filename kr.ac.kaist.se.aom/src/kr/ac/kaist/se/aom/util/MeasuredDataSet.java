package kr.ac.kaist.se.aom.util;

import java.io.Serializable;
import java.util.HashMap;

public class MeasuredDataSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3928000947189484074L;
	private HashMap<String, Object> dataSet;
	
	public MeasuredDataSet()
	{
		dataSet = new HashMap<String, Object>();
	}
	
	public void putData(String metricName, Object data)
	{
		if( data instanceof Serializable )
		{
			dataSet.put(metricName, data);
		}
	}
	
	public Object getData(String metricName)
	{
		return dataSet.get(metricName);
	}
}
