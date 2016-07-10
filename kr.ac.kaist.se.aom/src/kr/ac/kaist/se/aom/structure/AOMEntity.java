/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kr.ac.kaist.se.aom.structure;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMEntity#getOccurrence <em>Occurrence</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMEntity#isPublicEntity <em>Public Entity</em>}</li>
 * </ul>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMEntity()
 * @model abstract="true"
 * @generated
 */
public interface AOMEntity extends IndexedElement {

	/**
	 * Returns the value of the '<em><b>Occurrence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occurrence</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occurrence</em>' attribute.
	 * @see #setOccurrence(int)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMEntity_Occurrence()
	 * @model transient="true"
	 * @generated
	 */
	int getOccurrence();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMEntity#getOccurrence <em>Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Occurrence</em>' attribute.
	 * @see #getOccurrence()
	 * @generated
	 */
	void setOccurrence(int value);

	/**
	 * Returns the value of the '<em><b>Public Entity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Public Entity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Public Entity</em>' attribute.
	 * @see #setPublicEntity(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMEntity_PublicEntity()
	 * @model
	 * @generated
	 */
	boolean isPublicEntity();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMEntity#isPublicEntity <em>Public Entity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Public Entity</em>' attribute.
	 * @see #isPublicEntity()
	 * @generated
	 */
	void setPublicEntity(boolean value);
} // AOMEntity
