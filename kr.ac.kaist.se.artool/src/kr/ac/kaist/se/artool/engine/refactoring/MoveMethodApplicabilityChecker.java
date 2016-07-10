package kr.ac.kaist.se.artool.engine.refactoring;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess;
import kr.ac.kaist.se.aom.structure.AOMMethod;


public class MoveMethodApplicabilityChecker
{
	
	private AOMMethod movingMethod;
	private AOMClass targetClass;
	
	private MoveMethodApplicabilityChecker()
	{
	}
		
	public static boolean isApplicable(AOMMethod movingMethod, AOMClass targetClass)
	{
		MoveMethodApplicabilityChecker instance = new MoveMethodApplicabilityChecker();
		instance.movingMethod = movingMethod;
		instance.targetClass = targetClass;
		
		return instance._isApplicable();
	}
	
	private boolean _isApplicable() {
		
		if( isSynchronized() )
		{
			return false;
		}
		
		if( containsSuperMethodInvocation() )
		{
			return false;
		}
		
		if( overridesMethod() )
		{
			return false;
		}
		
		if( containsFieldAssignment() )
		{
			return false;
		}
		
		if( isTargetClassAnInterface() )
		{
			return false;
		}
		
		if( !validTargetObject() )
		{
			return false;
		}
		
		if( containsMethodCallWithThisExpressionAsArgument() )
		{
			return false;
		}
		
		return true;
		
		/*
    	if(!isSynchronized() && 
    			!containsSuperMethodInvocation() && 
    			!overridesMethod() && 
    			!containsFieldAssignment() && 
    			!isTargetClassAnInterface() &&
    			validTargetObject() && 
//    			!oneToManyRelationshipWithTargetClass() && 
//    			!containsAssignmentToTargetClassVariable() && // Strange condition...
    			!containsMethodCallWithThisExpressionAsArgument() && 
//    			!isTargetClassAnEnum() &&  // In our model, AOMClass cannot be enum
//    			!isSourceClassATestClass() && // test???
    			true
    			)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	*/
    }
    
    private boolean isSynchronized()
    {
    	return movingMethod.isSynchronized();
    }
    
    private boolean containsSuperMethodInvocation()
    {
    	return movingMethod.isSuperMethodInvocation() || movingMethod.isSuperFieldAccess();
    }
    
    private boolean overridesMethod()
    {
    	return movingMethod.getOverriding() != null;
    }
    
    private boolean containsFieldAssignment()
    {
    	return movingMethod.isContainsFieldAssignment();
    }
    
    private boolean isTargetClassAnInterface()
    {
    	return targetClass.isInterface();
    }
    
    private boolean isFamilyClass(AOMClass ancestorOrSelf, AOMClass descendant)
    {
    	AOMClass curClass = descendant; 
    	
    	if( curClass == ancestorOrSelf )
    	{
    		return true;
    	}
    	
    	for( AOMClass nextClass : descendant.getAncestor() )
    	{
    		if( isFamilyClass(ancestorOrSelf, nextClass) )
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private boolean validTargetObject()
    {
    	if( movingMethod.getOwnedScope() != null )
    	{
    		for( AOMLocalVariableAccess lva : movingMethod.getOwnedScope().getLocalVariableAccesses() )
    		{
    			if( lva.isParameterAccess() && lva.getAccessedVariableDef() != null && lva.getAccessedVariableDef().getType() == targetClass )
    			{
    				return true;
    			}
    		}
    		
    		for( StaticFieldAccess sfa : movingMethod.getOwnedScope().getStaticFieldAccesses() )
    		{
    			if( sfa.getAccessedField() != null && 
    					isFamilyClass(sfa.getAccessedField().getOwner(), movingMethod.getOwner()) &&
    					sfa.getAccessedField().getType() == targetClass )
    			{
    				return true;
    			}
    		}
    		
    		for( StaticMethodCall smc : movingMethod.getOwnedScope().getStaticMethodCalls() )
    		{
    			if( isFamilyClass(smc.getCallee().getOwner(), movingMethod.getOwner()) )
    			{
    				if( smc.getCallee().getGetter() != null &&
	    					smc.getCallee().getGetter().getType() == targetClass )
	    			{
	    				return true;
	    			}
    				
    				if( smc.getCallee().getDelegate() != null && smc.getCallee().getDelegate().getOwner() == targetClass )
    				{
    					return true;
    				}
	    		
    			}
    		}
    	}
    	return false;
    }

    private boolean containsMethodCallWithThisExpressionAsArgument()
    {
    	// TODO: In this time, let's ignore this condition.
    	return false;
    }
    
}
