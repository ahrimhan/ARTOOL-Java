package kr.ac.kaist.se.artool.engine.metrics;

import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class N_ConsecutiveCall_Static extends N_ConsecutiveCall{
	//interaction between different pair of classes
	public N_ConsecutiveCall_Static() {
		super();
	}
	
	@Override
	protected void _measure(AOMMethod method) {
		for( StaticMethodCall dmc : method.getOwnedScope().getStaticMethodCalls() )
		{
			AOMMethod aomMethod1 = dmc.getCaller().getOwner();
			AOMMethod aomMethod2 = dmc.getCallee();
			
			AOMClass aomClass1 = aomMethod1.getOwner();
			AOMClass aomClass2 = aomMethod2.getOwner();
					
			if( aomMethod1 != aomMethod2 )
			{
				increase(map4N_CCM, aomMethod1, aomMethod2);
			}
			
			if( aomClass1 != aomClass2 )
			{
				increase(map4N_CCC, aomClass1, aomClass2);
			}
		}		
	}
}
