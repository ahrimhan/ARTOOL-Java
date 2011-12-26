package kr.ac.kaist.se.artool.engine.refactoring;

import java.util.Vector;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class MoveMethodCommand implements RefactoringCommand {

	private AOMMethod movingMethod;
	private AOMClass targetClass;
	
	private AOMClass originalClass;
	private AOMMethod originalOverriding;
	
	public MoveMethodCommand(AOMMethod movingMethod, AOMClass targetClass)
	{
		this.movingMethod = movingMethod;
		this.targetClass = targetClass;
	}

	private static boolean isIdenticalMethod(AOMMethod m1, AOMMethod m2)
	{
		boolean ret = false;
		
		if( m1.getName().equals(m2.getName()) && m1.getSignature().equals(m2.getSignature()) )
		{
			ret = true;
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
		
		originalOverriding = movingMethod.getOverriding();
		AOMMethod overriding = null;
		overriding = findOverriding(targetClass, movingMethod);
		movingMethod.setOverriding(overriding);
		
		originalClass = movingMethod.getOwner();
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
		}
	}

}
