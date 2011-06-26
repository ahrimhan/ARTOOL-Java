package kr.ac.kaist.se.artool.engine.metrics;

import java.awt.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.rules.AbstractRule;

public class N_DCICM {
	//different classes implementing calling methods (dcicm)
	public EMap< AOMMethod, HashSet<AOMClass> > map4N_DCICM;
	//different methods implementing calling methods (dmicm)
	public EMap< AOMMethod, HashSet<AOMMethod> > map4N_DMICM;
	
	public N_DCICM(){
		
		map4N_DCICM = new BasicEMap<AOMMethod, HashSet<AOMClass>>();
		map4N_DMICM = new BasicEMap<AOMMethod, HashSet<AOMMethod>>();
	}
	
	public static final String N_DCICM = "N_DCICM";
	
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

					for( DynamicMethodCall dmc : method.getOwnedScope().getDynamicMethodCalls() )
					{
						if( !visitedClass.contains(dmc.getCallee().getOwner()) )
						{
							ndcicm++;
							visitedClass.add(dmc.getCallee().getOwner());
							visitedMethod.add(dmc.getCallee());
						
						}
					}

				}
				
				map4N_DCICM.put(method, visitedClass);
				map4N_DMICM.put(method, visitedMethod);
				//EList<AOMClass> diffrentClasses = new BasicEList<AOMClass>();
				//diffrentClasses = visitedClass.toArray(new AOMClass[]);
				
				method.getMeasuredDataSet().put(N_DCICM, new int[]{ndcicm});
				//method.getMeasuredDataSet().put("visitedClass", visitedClass);
				
			}
		}
	}

}
