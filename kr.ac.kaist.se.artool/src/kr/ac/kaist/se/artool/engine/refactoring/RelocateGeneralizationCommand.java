package kr.ac.kaist.se.artool.engine.refactoring;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;

public class RelocateGeneralizationCommand implements RefactoringCommand {
	
	private AbstractObjectModel aom;
	//backup for undo
	private EList<AOMClass> ancestors;
	private EList<AOMClass> descendants;
	AOMClass relocatingClass;
	AOMClass oldParentRelocatingClass; 
	AOMClass newParentRelocatingClass;

	public RelocateGeneralizationCommand(AbstractObjectModel aom, 
			AOMClass relocatingClass, AOMClass oldParentRelocatingClass, AOMClass newParentRelocatingClass)
	{
		this.aom = aom;
		this.relocatingClass = relocatingClass;
		this.oldParentRelocatingClass = oldParentRelocatingClass;
		this.newParentRelocatingClass = newParentRelocatingClass;
		ancestors = new BasicEList<AOMClass>();
		descendants = new BasicEList<AOMClass>();
	}
	
	@Override
	public void doCommand() throws RefactoringException {
		
		if(relocatingClass.getAncestor().contains(oldParentRelocatingClass))
		{
			relocatingClass.getAncestor().remove(oldParentRelocatingClass);
		}
		if(!relocatingClass.getAncestor().contains(newParentRelocatingClass))
		{
			relocatingClass.getAncestor().add(newParentRelocatingClass);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void undoCommand() throws RefactoringException {
		// TODO Auto-generated method stub
		
		if( relocatingClass.getAncestor().contains(newParentRelocatingClass) )
		{
			relocatingClass.getAncestor().remove(newParentRelocatingClass);
		}
		
		if( !relocatingClass.getAncestor().contains(oldParentRelocatingClass) )
		{
			relocatingClass.getAncestor().add(oldParentRelocatingClass);
		}

	}

}
