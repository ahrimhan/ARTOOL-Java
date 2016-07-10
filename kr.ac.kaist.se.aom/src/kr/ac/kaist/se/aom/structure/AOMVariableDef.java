/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMVariableDef.java,v 1.3 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Variable Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMVariableDef#getReferer <em>Referer</em>}</li>
 * </ul>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMVariableDef()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface AOMVariableDef extends AOMTypedElement, AOMNamedElement {

	/**
	 * Returns the value of the '<em><b>Referer</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#getAccessedVariableDef <em>Accessed Variable Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referer</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referer</em>' reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMVariableDef_Referer()
	 * @see kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#getAccessedVariableDef
	 * @model opposite="accessedVariableDef"
	 * @generated
	 */
	EList<AOMLocalVariableAccess> getReferer();
} // AOMVariableDef
