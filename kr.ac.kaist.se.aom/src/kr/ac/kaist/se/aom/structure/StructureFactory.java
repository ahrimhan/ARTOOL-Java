/**
 * <copyright>
 * </copyright>
 *
 * $Id: StructureFactory.java,v 1.5 2011-01-05 02:48:54 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.structure.StructurePackage
 * @generated
 */
public interface StructureFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StructureFactory eINSTANCE = kr.ac.kaist.se.aom.structure.impl.StructureFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>AOM Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AOM Class</em>'.
	 * @generated
	 */
	AOMClass createAOMClass();

	/**
	 * Returns a new object of class '<em>AOM Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AOM Field</em>'.
	 * @generated
	 */
	AOMField createAOMField();

	/**
	 * Returns a new object of class '<em>AOM Method</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AOM Method</em>'.
	 * @generated
	 */
	AOMMethod createAOMMethod();

	/**
	 * Returns a new object of class '<em>AOM Scope</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AOM Scope</em>'.
	 * @generated
	 */
	AOMScope createAOMScope();

	/**
	 * Returns a new object of class '<em>AOM Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AOM Parameter</em>'.
	 * @generated
	 */
	AOMParameter createAOMParameter();

	/**
	 * Returns a new object of class '<em>AOM Local Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AOM Local Variable</em>'.
	 * @generated
	 */
	AOMLocalVariable createAOMLocalVariable();

	/**
	 * Returns a new object of class '<em>AOM External Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AOM External Type</em>'.
	 * @generated
	 */
	AOMExternalType createAOMExternalType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StructurePackage getStructurePackage();

} //StructureFactory
