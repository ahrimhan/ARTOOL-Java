package kr.ac.kaist.se.artool.engine.metrics;

import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

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
						if( (!aomClass1.getName().endsWith("RebaseCommand")) && (!aomClass2.getName().endsWith("RebaseCommand")))
						{
							if( aomMethod1 != aomMethod2 && aomMethod1 != method && aomMethod2 != method )
							{
								UtilityFunctions.getInstance().increase(map4N_IBDPM, aomMethod1, aomMethod2, 1);
							}
							UtilityFunctions.getInstance().increase(map4N_IBDPC, aomClass1, aomClass2, 1);
						}
					}
				}
				smc1 = smc2;
			}
		}
	}
}
