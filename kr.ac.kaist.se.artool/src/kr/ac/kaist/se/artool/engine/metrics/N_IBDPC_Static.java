package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.emf.common.util.EList;

public class N_IBDPC_Static extends N_IBDPC {
	//interaction between different pair of classes
	public N_IBDPC_Static() {
		super();
	}
	
	public void _measure(AOMClass clazz, AOMMethod method) {

		EList<StaticMethodCall> smcList = method.getOwnedScope().getStaticMethodCalls();
		// In first iteration of the below for-loop,  dmc1 would be null.
		StaticMethodCall smc1 = null;

		for( int k = 0; k < (smcList.size() - 1); k++ )
		{
			StaticMethodCall smc2 = smcList.get(k);
			AOMMethod aomMethod2 = smc2.getCallee();
			AOMClass aomClass2 = smc2.getCallee().getOwner();
			if( aomClass2 != clazz)
			{
				if( smc1 != null )
				{
					AOMClass aomClass1 = smc1.getCallee().getOwner();
					AOMMethod aomMethod1 = smc1.getCallee();
					if( aomClass1 != aomClass2 && aomClass1 != clazz )
					{
						if( aomMethod1 != aomMethod2 && aomMethod1 != method && aomMethod2 != method )
						{
							increase(map4N_IBDPM, aomMethod1, aomMethod2);
						}
						increase(map4N_IBDPC, aomClass1, aomClass2);
					}
				}
				smc1 = smc2;
			}
		}
	}
}
