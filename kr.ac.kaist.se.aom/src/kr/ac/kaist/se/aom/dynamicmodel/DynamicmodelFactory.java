/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicmodelFactory.java,v 1.4 2011-01-04 00:56:36 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage
 * @generated
 */
public interface DynamicmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DynamicmodelFactory eINSTANCE = kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Dynamic Method Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dynamic Method Call</em>'.
	 * @generated
	 */
	DynamicMethodCall createDynamicMethodCall();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DynamicmodelPackage getDynamicmodelPackage();

} //DynamicmodelFactory
