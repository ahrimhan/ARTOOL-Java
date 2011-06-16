/**
 * <copyright>
 * </copyright>
 *
 * $Id: AbstractObjectModel.java,v 1.9 2011-02-07 08:36:33 igsong Exp $
 */
package kr.ac.kaist.se.aom;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicDependency;

import kr.ac.kaist.se.aom.staticmodel.StaticDependency;

import kr.ac.kaist.se.aom.structure.AOMClass;

import kr.ac.kaist.se.aom.structure.AOMExternalType;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Object Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.AbstractObjectModel#getClasses <em>Classes</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.AbstractObjectModel#getExternalTypes <em>External Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.AomPackage#getAbstractObjectModel()
 * @model
 * @generated
 */
public interface AbstractObjectModel extends MeasurableElement {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.AomPackage#getAbstractObjectModel_Classes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AOMClass> getClasses();

	/**
	 * Returns the value of the '<em><b>External Types</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMExternalType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Types</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.AomPackage#getAbstractObjectModel_ExternalTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AOMExternalType> getExternalTypes();
} // AbstractObjectModel
