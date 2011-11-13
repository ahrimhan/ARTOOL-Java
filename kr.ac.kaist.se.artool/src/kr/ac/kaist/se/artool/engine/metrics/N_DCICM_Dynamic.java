package kr.ac.kaist.se.artool.engine.metrics;

import java.util.HashSet;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class N_DCICM_Dynamic extends N_DCICM{
	public N_DCICM_Dynamic(){
		super();
	}
	public static final String N_DCICM = "N_DCICM";
	
	public String getName()
	{
		return N_DCICM;
	}
	
	protected int helpMeasure(int ndcicm, AOMMethod method,
			HashSet<AOMClass> visitedClass, HashSet<AOMMethod> visitedMethod) {
		for( DynamicMethodCall dmc : method.getOwnedScope().getDynamicMethodCalls() )
		{
			if( !visitedClass.contains(dmc.getCallee().getOwner()) )
			{
				ndcicm++;
				visitedClass.add(dmc.getCallee().getOwner());			
			}
			visitedMethod.add(dmc.getCallee());
		}
		return ndcicm;
	}

}
