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
				visitedMethod.add(dmc.getCallee());
			
			}
		}
		return ndcicm;
	}

}
