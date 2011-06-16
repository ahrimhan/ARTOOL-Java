package kr.ac.kaist.se.artool.dynamicprofile;

import java.util.HashMap;
import java.util.Vector;

import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;

public class DynamicProfileDataSet {
	private HashMap<String, DynamicProfileClassData> dataSet;
	private Vector<DynamicProfileClassData> dataList;
	
	public DynamicProfileDataSet()
	{	
		dataSet = new HashMap<String, DynamicProfileClassData>();
		dataList = new Vector<DynamicProfileClassData>();
	}
	

	
	public int size()
	{
		return dataSet.size();
	}
	
	public int getIndex(DynamicProfileClassData item)
	{
		return dataList.indexOf(item);
	}
	
	public Object[] getItemAsArray()
	{
		return dataList.toArray();
	}
	
	public DynamicProfileClassData getItem(int i)
	{
		return dataList.get(i);
	}
	
	public DynamicProfileClassData addItem(AOMMethodCallItem item)
	{
		String key = getKey(item);
		DynamicProfileClassData pm = null;
		if( dataSet.containsKey(key) )
		{
			pm = dataSet.get(key);
			pm.addItem(item);
		}
		else
		{
			pm = new DynamicProfileClassData(item, this);
			dataSet.put(key, pm);
			dataList.add(pm);
			pm.addItem(item);
		}
		
		return pm;
	}
	
	private String getKey(AOMMethodCallItem item)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(item.callerClassName);
		sb.append('>');
		sb.append(item.calleeDynamicClassName);
		return sb.toString();
	}
		
	public void clear()
	{
		for( DynamicProfileClassData data : dataList )
		{
			data.clear();
		}
		dataSet.clear();
		dataList.clear();
	}
}
