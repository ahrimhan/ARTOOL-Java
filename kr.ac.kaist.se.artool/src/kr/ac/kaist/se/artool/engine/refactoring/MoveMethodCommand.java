package kr.ac.kaist.se.artool.engine.refactoring;

import java.util.Vector;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class MoveMethodCommand implements RefactoringCommand {

	private AOMMethod movingMethod;
	private AOMClass targetClass;
	
	private AOMClass originalClass;
	private AOMMethod originalOverriding;
	
	
	private float deltaValue;
	
	public MoveMethodCommand(AOMMethod movingMethod, AOMClass targetClass)
	{
		this.movingMethod = movingMethod;
		this.targetClass = targetClass;
		if( movingMethod == null )
		{
			this.originalClass = null;
			this.originalOverriding = null;
		}
		else
		{
			this.originalClass = movingMethod.getOwner();
			this.originalOverriding = movingMethod.getOverriding();
		}
		this.deltaValue = 0;
	}
	
	public MoveMethodCommand(AOMMethod movingMethod, AOMClass targetClass, float d)
	{
		this.movingMethod = movingMethod;
		this.targetClass = targetClass;
		if( movingMethod == null )
		{
			this.originalClass = null;
			this.originalOverriding = null;
		}
		else
		{
			this.originalClass = movingMethod.getOwner();
			this.originalOverriding = movingMethod.getOverriding();
		}		
		this.deltaValue = d;
	}
	
	public float fitness;
	
	public float getDeltaValue()
	{
		return deltaValue;
	}
	
	public AOMMethod getMethod()
	{
		return movingMethod;
	}
	
	public AOMClass getToClass()
	{
		return targetClass;
	}
	
	public AOMClass getFromClass()
	{
		return originalClass;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		//sb.append('[');
		//sb.append(deltaValue);
		sb.append("'");
		sb.append(originalClass.getFqdn());
		sb.append(" -- ");
		sb.append(movingMethod.getName());
		sb.append(" -> ");
		sb.append(targetClass.getFqdn());
		sb.append("'");
		
		return sb.toString();
	}
	
	public boolean isIdenticalOrReversal(MoveMethodCommand action)
	{
		if (this.movingMethod == action.movingMethod)
		{
			if(this.originalClass == action.originalClass && this.targetClass == action.targetClass)
			{
				return true;
			}
			else if(this.originalClass == action.targetClass && this.targetClass == action.originalClass)
			{
				return true;
			}
		}
		else
		{
			return false;
		}
		
		return false;
	}

	private static boolean isIdenticalMethod(AOMMethod m1, AOMMethod m2)
	{
		boolean ret = false;
	
		try
		{	
			if( m1.getName().equals(m2.getName()) &&
					
					m1.getSignature().equals(m2.getSignature()) )
			{
				ret = true;
			}
		}
		catch( RuntimeException ex )
		{
			System.err.println(m1.getMethodId());
			System.err.println(m2.getMethodId());
			System.err.println(m1.getSignature());
			System.err.println(m2.getSignature());
			throw ex;
		}
		
		return ret;
	}
	

    
	private static AOMMethod findOverriding(AOMClass c, AOMMethod m)
	{
		Vector<AOMClass> queue = new Vector<AOMClass>(c.getAncestor());
		Vector<AOMClass> visitedClass = new Vector<AOMClass>();
		while( !queue.isEmpty() )
		{
			AOMClass aomClass = queue.remove(0);
			visitedClass.add(aomClass);
			for( AOMMethod lm : aomClass.getMethods() )
			{
				if( isIdenticalMethod(lm, m) )
				{
					return lm;
				}
			}
			
			for( AOMClass p : aomClass.getAncestor() )
			{
				if( !visitedClass.contains(p) )
				{
					queue.add(p);
				}
			}
		}

		return null;
	}
	
	boolean did = false;
	
	@Override
	public double doCommand() throws RefactoringException {
		did = false;
		
		/*
		try
		{
			for( AOMMethod lm : targetClass.getMethods() )
			{
				if( isIdenticalMethod(lm, movingMethod) )
				{
					throw new RefactoringException(targetClass.getFqdn() + " already has a method which has the same name and signature");
				}
			}
		}
		catch(Throwable ex)
		{
//			ex.printStackTrace();
			return 0;
		}
		*/
		
		if( targetClass.getMethods().contains(movingMethod) )
		{
			return 0;
		}
		
		AOMMethod overriding = null;
		overriding = findOverriding(targetClass, movingMethod);
		movingMethod.setOverriding(overriding);
		movingMethod.setOwner(targetClass);
		did = true;
		
		return 1;
	}

	@Override
	public void undoCommand() throws RefactoringException {
		if( did )
		{
			movingMethod.setOwner(originalClass);
			movingMethod.setOverriding(originalOverriding);
			did = false;
		}
	}

}
