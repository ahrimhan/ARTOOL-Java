package kr.ac.kaist.se.artool.engine.metrics;

import java.util.HashMap;
import java.util.HashSet;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public abstract class N_DCICM {
	//different classes implementing calling methods (dcicm)
	public HashMap< AOMMethod, AOMClass[] > map4N_DCICM;
	//different methods implementing calling methods (dmicm)
	public HashMap< AOMMethod, AOMMethod[] > map4N_DMICM;
	
	public N_DCICM(){
		
		map4N_DCICM = new HashMap<AOMMethod, AOMClass[]>();
		map4N_DMICM = new HashMap<AOMMethod, AOMMethod[]>();
	}
	
	private static N_DCICM dynamic_instance = null;
	private static N_DCICM static_instance = null;
	
	public static N_DCICM createInstance(boolean isDynamic)
	{
		N_DCICM ret;
		
		if( isDynamic )
		{
			if( dynamic_instance == null )
			{
				dynamic_instance = new N_DCICM_Dynamic();
			}
			ret = dynamic_instance;
		}
		else
		{
			if( static_instance == null )
			{
				static_instance = new N_DCICM_Static();
			}
			ret = static_instance;
		}
		
		return ret;
	}
	
	
	public abstract String getName();
	//HashSet<AOMClass> key;
	
	
	public HashMap<AOMMethod, AOMClass[]> getDCICM()
	{
		return map4N_DCICM;
	}

	public HashMap<AOMMethod, AOMMethod[]> getMap4N_DMICM() {
		return map4N_DMICM;
	}
	
	HashSet<AOMClass> visitedClass =  new HashSet<AOMClass>();
	HashSet<AOMMethod> visitedMethod =  new HashSet<AOMMethod>();
	
	public void measure(AbstractObjectModel aom) {
		int ndcicm = 0;
		int max = 0;
		
		map4N_DCICM.clear();
		map4N_DMICM.clear();
	

		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method: clazz.getMethods() )
			{
				visitedClass.clear();
				visitedMethod.clear();

				ndcicm = 0; 
				
				if( method.getOwnedScope() != null )
				{					

					ndcicm = helpMeasure(ndcicm, method, visitedClass,
							visitedMethod);

				}
				
				map4N_DCICM.put(method, visitedClass.toArray(new AOMClass[visitedClass.size()]));
				map4N_DMICM.put(method, visitedMethod.toArray(new AOMMethod[visitedMethod.size()]));
				//EList<AOMClass> diffrentClasses = new BasicEList<AOMClass>();
				//diffrentClasses = visitedClass.toArray(new AOMClass[]);
				
				method.getMeasuredDataSet().put(getName(), new int[]{ndcicm});
				//method.getMeasuredDataSet().put("visitedClass", visitedClass);
				
			}
		}
	}



	protected abstract  int helpMeasure(int ndcicm, AOMMethod method,
			HashSet<AOMClass> visitedClass, HashSet<AOMMethod> visitedMethod);
}
