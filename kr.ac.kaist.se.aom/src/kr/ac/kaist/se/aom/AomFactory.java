/**
 * <copyright>
 * </copyright>
 *
 * $Id: AomFactory.java,v 1.7 2011-02-07 08:36:33 igsong Exp $
 */
package kr.ac.kaist.se.aom;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.AomPackage
 * @generated
 */
public interface AomFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AomFactory eINSTANCE = kr.ac.kaist.se.aom.impl.AomFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Abstract Object Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Object Model</em>'.
	 * @generated
	 */
	AbstractObjectModel createAbstractObjectModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AomPackage getAomPackage();

} //AomFactory
