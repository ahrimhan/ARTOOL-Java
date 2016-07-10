/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMType.java,v 1.4 2011-01-05 02:48:54 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMType#getReferer <em>Referer</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMType#getFqdn <em>Fqdn</em>}</li>
 * </ul>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMType()
 * @model abstract="true"
 * @generated
 */
public interface AOMType extends AOMNamedElement {
	/**
	 * Returns the value of the '<em><b>Referer</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMTypedElement}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMTypedElement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referer</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referer</em>' reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMType_Referer()
	 * @see kr.ac.kaist.se.aom.structure.AOMTypedElement#getType
	 * @model opposite="type"
	 * @generated
	 */
	EList<AOMTypedElement> getReferer();

	/**
	 * Returns the value of the '<em><b>Fqdn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fqdn</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fqdn</em>' attribute.
	 * @see #setFqdn(String)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMType_Fqdn()
	 * @model
	 * @generated
	 */
	String getFqdn();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMType#getFqdn <em>Fqdn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fqdn</em>' attribute.
	 * @see #getFqdn()
	 * @generated
	 */
	void setFqdn(String value);

} // AOMType
