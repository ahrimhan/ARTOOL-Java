package kr.ac.kaist.se.artool.engine.metrics;

import java.awt.List;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class N_DCICM_Static {
	//different classes implementing calling methods
	public EMap< AOMMethod, HashSet<AOMClass> > map4N_DCICM_Static;
	
	public N_DCICM_Static(){
		
		map4N_DCICM_Static = new BasicEMap<AOMMethod, HashSet<AOMClass>>();
	}
	
	public static final String N_DCICM_Static = "N_DCICM_Static";
	
	//HashSet<AOMClass> key;
	
	
	public EMap<AOMMethod, HashSet<AOMClass>> getDCICM_Static()
	{
		return map4N_DCICM_Static;
	}

	public void measure(AbstractObjectModel aom) {
		int ndcicm = 0;
		int max = 0;
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method: clazz.getMethods() )
			{
				HashSet<AOMClass> visitedClass =  new HashSet<AOMClass>();
				visitedClass.clear();
				ndcicm = 0; 
				
				if( method.getOwnedScope() != null )
				{					

					for( StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
					{
						if( !visitedClass.contains(smc.getCallee().getOwner()) )
						{
							ndcicm++;
							visitedClass.add(smc.getCallee().getOwner());
						
						}
					}

				}
				
				map4N_DCICM_Static.put(method, visitedClass);
				//EList<AOMClass> diffrentClasses = new BasicEList<AOMClass>();
				//diffrentClasses = visitedClass.toArray(new AOMClass[]);
				
				method.getMeasuredDataSet().put(N_DCICM_Static, new int[]{ndcicm});
				//method.getMeasuredDataSet().put("visitedClass", visitedClass);
				
			}
		}
	}
}
