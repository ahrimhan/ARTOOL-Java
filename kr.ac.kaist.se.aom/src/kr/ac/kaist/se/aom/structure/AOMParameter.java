/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMParameter.java,v 1.3 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMParameter#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMParameter()
 * @model
 * @generated
 */
public interface AOMParameter extends AOMVariableDef {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(AOMMethod)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMParameter_Owner()
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getParameters
	 * @model opposite="parameters" transient="false"
	 * @generated
	 */
	AOMMethod getOwner();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMParameter#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(AOMMethod value);

} // AOMParameter
