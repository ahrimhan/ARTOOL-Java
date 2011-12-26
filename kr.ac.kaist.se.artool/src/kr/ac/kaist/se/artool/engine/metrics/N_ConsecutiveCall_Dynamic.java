package kr.ac.kaist.se.artool.engine.metrics;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

public class N_ConsecutiveCall_Dynamic extends N_ConsecutiveCall {
	//interaction between different pair of classes
	public N_ConsecutiveCall_Dynamic() {
		super();
	}

	@Override
	protected void _measure(AOMMethod method) {
		for( DynamicMethodCall dmc : method.getOwnedScope().getDynamicMethodCalls() )
		{
			AOMMethod aomMethod1 = dmc.getCaller().getOwner();
			AOMMethod aomMethod2 = dmc.getCallee();
			
			AOMClass aomClass1 = aomMethod1.getOwner();
			AOMClass aomClass2 = aomMethod2.getOwner();
					
			if( aomMethod1 != aomMethod2 )
			{
				UtilityFunctions.getInstance().increase(map4N_CCM, aomMethod1, aomMethod2);
			}
			
			if( aomClass1 != aomClass2 )
			{
				UtilityFunctions.getInstance().increase(map4N_CCC, aomClass1, aomClass2);
			}
		}	
		
		
	}
}
