/**
 */
package kr.ac.kaist.se.aom.structure;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Local Variable Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#getAccessedVariableDef <em>Accessed Variable Def</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#getAccessingScope <em>Accessing Scope</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#isParameterAccess <em>Parameter Access</em>}</li>
 * </ul>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMLocalVariableAccess()
 * @model
 * @generated
 */
public interface AOMLocalVariableAccess extends EObject {
	/**
	 * Returns the value of the '<em><b>Accessed Variable Def</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMVariableDef#getReferer <em>Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accessed Variable Def</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accessed Variable Def</em>' reference.
	 * @see #setAccessedVariableDef(AOMVariableDef)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMLocalVariableAccess_AccessedVariableDef()
	 * @see kr.ac.kaist.se.aom.structure.AOMVariableDef#getReferer
	 * @model opposite="referer"
	 * @generated
	 */
	AOMVariableDef getAccessedVariableDef();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#getAccessedVariableDef <em>Accessed Variable Def</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accessed Variable Def</em>' reference.
	 * @see #getAccessedVariableDef()
	 * @generated
	 */
	void setAccessedVariableDef(AOMVariableDef value);

	/**
	 * Returns the value of the '<em><b>Accessing Scope</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMScope#getLocalVariableAccesses <em>Local Variable Accesses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accessing Scope</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accessing Scope</em>' container reference.
	 * @see #setAccessingScope(AOMScope)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMLocalVariableAccess_AccessingScope()
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getLocalVariableAccesses
	 * @model opposite="localVariableAccesses" transient="false"
	 * @generated
	 */
	AOMScope getAccessingScope();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#getAccessingScope <em>Accessing Scope</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accessing Scope</em>' container reference.
	 * @see #getAccessingScope()
	 * @generated
	 */
	void setAccessingScope(AOMScope value);

	/**
	 * Returns the value of the '<em><b>Parameter Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Access</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Access</em>' attribute.
	 * @see #setParameterAccess(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMLocalVariableAccess_ParameterAccess()
	 * @model
	 * @generated
	 */
	boolean isParameterAccess();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#isParameterAccess <em>Parameter Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Access</em>' attribute.
	 * @see #isParameterAccess()
	 * @generated
	 */
	void setParameterAccess(boolean value);

	IVariableBinding getVariableBinding();
	void setVariableBinding(IVariableBinding binding);
	
	ITypeBinding getTypeBinding();
	void setTypeBinding(ITypeBinding binding);
	
} // AOMLocalVariableAccess
