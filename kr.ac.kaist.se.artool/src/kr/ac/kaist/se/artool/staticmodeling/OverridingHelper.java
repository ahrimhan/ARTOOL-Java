package kr.ac.kaist.se.artool.staticmodeling;

import java.util.HashMap;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class OverridingHelper implements HierarchyTraverserHelper {
	private HashMap<String, AOMMethod> methodTable;
	
	public OverridingHelper()
	{
		methodTable = new HashMap<String, AOMMethod>();
	}
	
	public OverridingHelper(HashMap<String, AOMMethod> table)
	{
		methodTable = table;
	}
	
	private String getKey(AOMMethod method)
	{
		return method.getName() + ":" + method.getSignature();
	}
	
	@Override
	public void preVisit(AOMClass clazz) {
		for( AOMMethod method : clazz.getMethods() )
		{
			String key = getKey(method);
			if( methodTable.containsKey(key) )
			{
				AOMMethod ancestorMethod = methodTable.get(key);
				method.setOverriding(ancestorMethod);
			}
			methodTable.put(key, method);
		}
	}

	@Override
	public void postVisit(AOMClass clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HierarchyTraverserHelper getChildHelper() {
		return new OverridingHelper((HashMap<String, AOMMethod>) methodTable.clone());
	}
}
