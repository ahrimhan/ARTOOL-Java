/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMLocalVariable.java,v 1.3 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Local Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMLocalVariable#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMLocalVariable()
 * @model
 * @generated
 */
public interface AOMLocalVariable extends AOMVariableDef {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMScope#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(AOMScope)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMLocalVariable_Owner()
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getVariables
	 * @model opposite="variables" transient="false"
	 * @generated
	 */
	AOMScope getOwner();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariable#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(AOMScope value);

} // AOMLocalVariable
