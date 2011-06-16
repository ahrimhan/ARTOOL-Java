/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMField.java,v 1.4 2011-01-14 11:36:22 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import kr.ac.kaist.se.aom.MeasurableElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMField#getOwner <em>Owner</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMField#getReferer <em>Referer</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMField()
 * @model
 * @generated
 */
public interface AOMField extends AOMVariableDef, MeasurableElement {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMClass#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(AOMClass)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMField_Owner()
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getFields
	 * @model opposite="fields" transient="false"
	 * @generated
	 */
	AOMClass getOwner();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMField#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(AOMClass value);

	/**
	 * Returns the value of the '<em><b>Referer</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMScope}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMScope#getReferringFields <em>Referring Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referer</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referer</em>' reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMField_Referer()
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getReferringFields
	 * @model opposite="referringFields"
	 * @generated
	 */
	EList<AOMScope> getReferer();

} // AOMField
