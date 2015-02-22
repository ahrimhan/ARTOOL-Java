package kr.ac.kaist.se.artool.engine.refactoring;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

public class CollapseClassHierarchyCommand implements RefactoringCommand {
	private AbstractObjectModel aom;
	private AOMClass mergingClass;
	private AOMClass mergedClass;

	//backup for undo
	private EList<AOMClass> ancestors;
	private EList<AOMClass> descendants;
	private EList<AOMField> aomFields;
	private EList<AOMMethod> aomMethods;
	private int LOC;
	private String fqdn;
	private String name;
	
	public CollapseClassHierarchyCommand(AbstractObjectModel aom, AOMClass mergingClass, AOMClass mergedClass)
	{
		this.aom = aom;
		this.mergingClass = mergingClass;
		this.mergedClass = mergedClass;
		ancestors = new BasicEList<AOMClass>();
		descendants = new BasicEList<AOMClass>();
		aomFields = new BasicEList<AOMField>();
		aomMethods = new BasicEList<AOMMethod>(); 
	}


	@Override
	public double doCommand() throws RefactoringException {
		int min_field_size;
		int min_method_size;
		
		min_field_size = mergedClass.getFields().size() + mergingClass.getFields().size() ;
		min_method_size = mergedClass.getMethods().size() + mergingClass.getMethods().size() ;
		
		aom.getClasses().remove(mergedClass);
		
		ancestors.addAll(mergedClass.getAncestor());
		mergedClass.getAncestor().clear();
		mergingClass.getAncestor().addAll(ancestors);
		
		descendants.addAll(mergedClass.getDescendant());
		mergedClass.getDescendant().clear();
		mergingClass.getDescendant().addAll(mergedClass.getDescendant());
		
		aomFields.addAll(mergedClass.getFields());
//		mergingClass.getFields().addAll(mergedClass.getFields());
		mergedClass.getFields().clear();
		mergingClass.getFields().addAll(aomFields);

		aomMethods.addAll(mergedClass.getMethods());
//		mergingClass.getMethods().addAll(mergedClass.getMethods());
		mergedClass.getMethods().clear();
		mergingClass.getMethods().addAll(aomMethods);
		
		
		LOC = mergingClass.getRemainingLOC();
		mergingClass.setRemainingLOC(mergingClass.getRemainingLOC() + mergedClass.getRemainingLOC());
		
		fqdn = mergingClass.getFqdn();
		mergingClass.setFqdn(mergingClass.getFqdn() + " Plus " + mergedClass.getName());
		
		name = mergingClass.getName();
		mergingClass.setName(mergingClass.getName() + " Plus " + mergedClass.getName());
		
		for( AOMMethod method : aomMethods )
		{
			System.err.print( method.getOwner().getName() );
		}
		System.err.println();
		
		return (min_field_size * 0.5f + min_method_size + 0.0001) / 2.;
	}


	@Override
	public void undoCommand() throws RefactoringException {
		aom.getClasses().add(mergedClass);
		
		mergingClass.getAncestor().removeAll(ancestors);
		mergedClass.getAncestor().addAll(ancestors);
		ancestors.clear();
		
		mergingClass.getDescendant().removeAll(descendants);
		mergedClass.getDescendant().addAll(descendants);
		descendants.clear();
		
		mergingClass.getFields().removeAll(aomFields);
		mergedClass.getFields().addAll(aomFields);
		aomFields.clear();
		
		mergingClass.getMethods().removeAll(aomMethods);
		mergedClass.getMethods().addAll(aomMethods);
		aomMethods.clear();
		
		mergingClass.setRemainingLOC(LOC);
		
		mergingClass.setFqdn(fqdn);
		
		mergingClass.setName(name);
		
		for( AOMMethod method : mergedClass.getMethods() )
		{
			System.err.print( method.getOwner().getName() );
		}
		System.err.println();
	}
	
	
}
