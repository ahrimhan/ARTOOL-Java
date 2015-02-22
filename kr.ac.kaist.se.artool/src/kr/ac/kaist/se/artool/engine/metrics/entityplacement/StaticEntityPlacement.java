package kr.ac.kaist.se.artool.engine.metrics.entityplacement;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class StaticEntityPlacement extends EntityPlacement {

	protected StaticEntityPlacement()
	{
		super();
	}
	
	protected AOMBag<AOMEntity> _getEntitySet(AOMMethod method)
	{
		AOMBag<AOMEntity> ret = new AOMBag<AOMEntity>(useUnique());
		
		if( method.getOwnedScope() != null )
		{
			for( StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
			{
				ret.add(smc.getCallee());
			}
			
			for( StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
			{
				ret.add(sfa.getAccessedField());
			}
		}
		return ret;
	}
	
	protected AOMBag<AOMEntity> _getEntitySet(AOMField field)
	{
		AOMBag<AOMEntity> ret = new AOMBag<AOMEntity>(useUnique());
		
		for( StaticFieldAccess sfa : field.getStaticReferer() )
		{
			ret.add(sfa.getAccessingScope().getOwner() );
		}

		return ret;
	}
	
	protected boolean useUnique()
	{
		return true;
	}
}
