package kr.ac.kaist.se.artool.engine.metrics;

import java.util.HashSet;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

public abstract class N_DCICM {
	//different classes implementing calling methods (dcicm)
	public EMap< AOMMethod, HashSet<AOMClass> > map4N_DCICM;
	//different methods implementing calling methods (dmicm)
	public EMap< AOMMethod, HashSet<AOMMethod> > map4N_DMICM;
	
	public N_DCICM(){
		
		map4N_DCICM = new BasicEMap<AOMMethod, HashSet<AOMClass>>();
		map4N_DMICM = new BasicEMap<AOMMethod, HashSet<AOMMethod>>();
	}
	
	
	public abstract String getName();
	//HashSet<AOMClass> key;
	
	
	public EMap<AOMMethod, HashSet<AOMClass>> getDCICM()
	{
		return map4N_DCICM;
	}

	public EMap<AOMMethod, HashSet<AOMMethod>> getMap4N_DMICM() {
		return map4N_DMICM;
	}
	
	public void measure(AbstractObjectModel aom) {
		int ndcicm = 0;
		int max = 0;
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method: clazz.getMethods() )
			{
				HashSet<AOMClass> visitedClass =  new HashSet<AOMClass>();
				HashSet<AOMMethod> visitedMethod =  new HashSet<AOMMethod>();
				
				visitedClass.clear();
				visitedMethod.clear();
				
				ndcicm = 0; 
				
				if( method.getOwnedScope() != null )
				{					

					ndcicm = helpMeasure(ndcicm, method, visitedClass,
							visitedMethod);

				}
				
				map4N_DCICM.put(method, visitedClass);
				map4N_DMICM.put(method, visitedMethod);
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
