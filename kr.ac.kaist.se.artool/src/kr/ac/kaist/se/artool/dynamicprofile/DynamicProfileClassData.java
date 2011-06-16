package kr.ac.kaist.se.artool.dynamicprofile;

import java.util.HashMap;
import java.util.Vector;

import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;

public class DynamicProfileClassData {
	private HashMap<String, DynamicProfileMethodData> dataSet;
	private Vector<DynamicProfileMethodData> dataList;
	private long count;
	private String callerClass;
	private String calleeDynamicClass;
	private DynamicProfileDataSet rootDataSet;
	
	public DynamicProfileClassData(AOMMethodCallItem item, DynamicProfileDataSet rootDataSet)
	{
		dataSet = new HashMap<String, DynamicProfileMethodData>();
		dataList = new Vector<DynamicProfileMethodData>();
		count = 0;
		callerClass = item.callerClassName;
		calleeDynamicClass = item.calleeDynamicClassName;
		this.rootDataSet = rootDataSet;
	}
	
	public long getCount()
	{
		return count;
	}

	public String getCallerClass()
	{
		return callerClass;
	}
	
	
	public String getCalleeDynamicClass()
	{
		return calleeDynamicClass;
	}
	
	public void addItem(AOMMethodCallItem item)
	{
		String key = getKey(item);
		DynamicProfileMethodData pm = null;
		if( dataSet.containsKey(key) )
		{
			pm = dataSet.get(key);
			pm.increase();
		}
		else
		{
			pm = new DynamicProfileMethodData(item, rootDataSet);
			dataSet.put(key, pm);
			dataList.add(pm);
			pm.increase();
		}

		count ++;
	}
	
	private String getKey(AOMMethodCallItem item)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(item.callerMethodName);
		sb.append(':');
		sb.append(item.callerLineNumber);
		sb.append('>');
		sb.append(item.calleeMethodName);
		sb.append(':');
		sb.append(item.calleeMethodSignature);
		return sb.toString();
	}
	
	public int getIndex(DynamicProfileMethodData item)
	{
		return dataList.indexOf(item);
	}
	
	public Object[] getItemAsArray()
	{
		return dataList.toArray();
	}
	
	public DynamicProfileMethodData getItem(int i)
	{
		return dataList.get(i);
	}

	public int size() {
		return dataList.size();
	}

	public void clear() {
		dataSet.clear();
		dataList.clear();
	}
}
