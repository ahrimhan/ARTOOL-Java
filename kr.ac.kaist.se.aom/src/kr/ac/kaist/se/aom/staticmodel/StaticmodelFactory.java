/**
 * <copyright>
 * </copyright>
 *
 * $Id: StaticmodelFactory.java,v 1.4 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.staticmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage
 * @generated
 */
public interface StaticmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StaticmodelFactory eINSTANCE = kr.ac.kaist.se.aom.staticmodel.impl.StaticmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Static Method Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Method Call</em>'.
	 * @generated
	 */
	StaticMethodCall createStaticMethodCall();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StaticmodelPackage getStaticmodelPackage();

} //StaticmodelFactory
