package kr.ac.kaist.se.artool.engine.refactoring;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess;
import kr.ac.kaist.se.aom.structure.AOMMethod;


public class MoveMethodApplicabilityChecker
{
	
	
	private MoveMethodApplicabilityChecker()
	{
	}
		
	public static boolean isApplicable(AOMMethod movingMethod, AOMClass targetClass)
	{	
		return isApplicableForGivenMethod(movingMethod) && isApplicableForTargetClass(movingMethod, targetClass);
	}
	/*
	if(!isSynchronized() && 
			!containsSuperMethodInvocation() && 
			!overridesMethod() && 
			!containsFieldAssignment() && 
			!isTargetClassAnInterface() &&
			validTargetObject() && 
//			!oneToManyRelationshipWithTargetClass() && 
//			!containsAssignmentToTargetClassVariable() && // Strange condition...
			!containsMethodCallWithThisExpressionAsArgument() && 
//			!isTargetClassAnEnum() &&  // In our model, AOMClass cannot be enum
//			!isSourceClassATestClass() && // test???
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
	
	public static boolean isApplicableForTargetClass(AOMMethod movingMethod, AOMClass targetClass)
	{
		// 3.2.1 - 4
		if( isTargetClassAnInterface(targetClass) )
		{
			return false;
		}
		
		if( !validTargetObject(movingMethod, targetClass) )
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean isApplicableForGivenMethod(AOMMethod movingMethod) {
		// 3.2.2 - 4
		if( isSynchronized(movingMethod) )
		{
			return false;
		}
		
		// 3.2.1 - 3
		if( containsSuperMethodInvocation(movingMethod) )
		{
			return false;
		}
		
		// 3.2.2 - 2
		if( overridesMethod(movingMethod) )
		{
			return false;
		}
		
		// 3.2.3 - 1
		if( containsFieldAssignment(movingMethod) )
		{
			return false;
		}
		
	
		
		if( containsMethodCallWithThisExpressionAsArgument(movingMethod) )
		{
			return false;
		}
		
		return true;
    }
	
	
    
    private static boolean isSynchronized(AOMMethod movingMethod)
    {
    	return movingMethod.isSynchronized();
    }
    
    private static boolean containsSuperMethodInvocation(AOMMethod movingMethod)
    {
    	return movingMethod.isSuperMethodInvocation() || movingMethod.isSuperFieldAccess();
    }
    
    private static boolean overridesMethod(AOMMethod movingMethod)
    {
    	return movingMethod.getOverriding() != null;
    }
    
    private static boolean containsFieldAssignment(AOMMethod movingMethod)
    {
    	return movingMethod.isContainsFieldAssignment();
    }
    
    private static boolean isTargetClassAnInterface(AOMClass targetClass)
    {
    	return targetClass.isInterface();
    }
    
    private static boolean isFamilyClass(AOMClass ancestorOrSelf, AOMClass descendant)
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
    
    private static boolean validTargetObject(AOMMethod movingMethod, AOMClass targetClass)
    {
    	if( movingMethod.getOwnedScope() != null )
    	{
    		for( AOMLocalVariableAccess lva : movingMethod.getOwnedScope().getLocalVariableAccesses() )
    		{
    			// 3.2.2 - 3
    			if( lva.isParameterAccess() && lva.getAccessedVariableDef() != null && lva.getAccessedVariableDef().getType() == targetClass )
    			{
    				return true;
    			}
    		}
    		
    		for( StaticFieldAccess sfa : movingMethod.getOwnedScope().getStaticFieldAccesses() )
    		{
    			// 3.2.2 - 3
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

    private static boolean containsMethodCallWithThisExpressionAsArgument(AOMMethod movingMethod)
    {
    	// TODO: In this time, let's ignore this condition.
    	return false;
    }
    
}
