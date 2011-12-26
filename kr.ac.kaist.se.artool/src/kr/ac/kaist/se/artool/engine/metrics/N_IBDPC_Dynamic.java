package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

import org.eclipse.emf.common.util.EList;

public class N_IBDPC_Dynamic extends N_IBDPC{
	//interaction between different pair of classes
	public N_IBDPC_Dynamic() {
		super();
	}
	

	public void _measure(AOMClass clazz, AOMMethod method) {
		
		EList<DynamicMethodCall> dmcList = method.getOwnedScope().getDynamicMethodCalls();
		// In first iteration of the below for-loop,  dmc1 would be null.
		DynamicMethodCall dmc1 = null;

		for( int k = 0; k < (dmcList.size() - 1); k++ )
		{
			DynamicMethodCall dmc2 = dmcList.get(k);
			AOMMethod aomMethod2 = dmc2.getCallee();
			AOMClass aomClass2 = dmc2.getCallee().getOwner();
			if( aomClass2 != clazz)
			{
				if( dmc1 != null )
				{
					AOMClass aomClass1 = dmc1.getCallee().getOwner();
					AOMMethod aomMethod1 = dmc1.getCallee();
					if( aomClass1 != aomClass2 && aomClass1 != clazz )
					{
						if( aomMethod1 != aomMethod2 && aomMethod1 != method && aomMethod2 != method )
						{
							UtilityFunctions.getInstance().increase(map4N_IBDPM, aomMethod1, aomMethod2, 1);
						}
						UtilityFunctions.getInstance().increase(map4N_IBDPC, aomClass1, aomClass2, 1);
					}
				}
				dmc1 = dmc2;
			}
		}
		

	}
}
