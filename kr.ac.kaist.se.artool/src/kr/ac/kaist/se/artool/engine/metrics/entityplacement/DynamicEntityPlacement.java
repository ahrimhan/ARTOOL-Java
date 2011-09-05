package kr.ac.kaist.se.artool.engine.metrics.entityplacement;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class DynamicEntityPlacement extends EntityPlacement {

	protected DynamicEntityPlacement()
	{
		super();
	}
	
	
	protected AOMBag<AOMEntity> getEntitySet(AOMMethod method)
	{
		AOMBag<AOMEntity> ret = new AOMBag<AOMEntity>(useUnique());
		
		if( method.getOwnedScope() != null )
		{
			for( DynamicMethodCall dmc : method.getOwnedScope().getDynamicMethodCalls() )
			{
				ret.add(dmc.getCallee());
			}
			
			for( StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
			{
				ret.add(sfa.getAccessedField(), sfa.getDynamicAccessCount());
			}
		}
		return ret;
	}
	
	protected AOMBag<AOMEntity> getEntitySet(AOMField field)
	{
		AOMBag<AOMEntity> ret = new AOMBag<AOMEntity>(useUnique());
		
		for( StaticFieldAccess sfa : field.getStaticReferer() )
		{
			ret.add(sfa.getAccessingScope().getOwner(), sfa.getDynamicAccessCount());
		}

		return ret;
	}
	
	protected boolean useUnique()
	{
		return false;
	}
}
