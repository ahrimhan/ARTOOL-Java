/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMTypedElement.java,v 1.4 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import org.eclipse.jdt.core.dom.ITypeBinding;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Typed Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMTypedElement#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMTypedElement()
 * @model abstract="true"
 * @generated
 */
public interface AOMTypedElement extends AOMElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMType#getReferer <em>Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(AOMType)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMTypedElement_Type()
	 * @see kr.ac.kaist.se.aom.structure.AOMType#getReferer
	 * @model opposite="referer"
	 * @generated
	 */
	AOMType getType();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMTypedElement#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(AOMType value);
	
	public void setTypeBinding(ITypeBinding typeBinding) ;

	public ITypeBinding getTypeBinding();

} // AOMTypedElement
