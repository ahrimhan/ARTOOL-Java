/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMScope.java,v 1.8 2011-01-05 07:42:54 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import java.util.HashMap;
import java.util.List;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.dom.IVariableBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMScope#getVariables <em>Variables</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMScope#getOwner <em>Owner</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMScope#getStaticMethodCalls <em>Static Method Calls</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMScope#getDynamicMethodCalls <em>Dynamic Method Calls</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMScope#getStaticFieldAccesses <em>Static Field Accesses</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMScope#getLocalVariableAccesses <em>Local Variable Accesses</em>}</li>
 * </ul>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMScope()
 * @model
 * @generated
 */
public interface AOMScope extends EObject {
	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMLocalVariable}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariable#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMScope_Variables()
	 * @see kr.ac.kaist.se.aom.structure.AOMLocalVariable#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<AOMLocalVariable> getVariables();

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOwnedScope <em>Owned Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(AOMMethod)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMScope_Owner()
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getOwnedScope
	 * @model opposite="ownedScope" transient="false"
	 * @generated
	 */
	AOMMethod getOwner();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMScope#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(AOMMethod value);

	/**
	 * Returns the value of the '<em><b>Static Method Calls</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCaller <em>Caller</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Method Calls</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Method Calls</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMScope_StaticMethodCalls()
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCaller
	 * @model opposite="caller" containment="true"
	 * @generated
	 */
	EList<StaticMethodCall> getStaticMethodCalls();

	/**
	 * Returns the value of the '<em><b>Dynamic Method Calls</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCaller <em>Caller</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Method Calls</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Method Calls</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMScope_DynamicMethodCalls()
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCaller
	 * @model opposite="caller" containment="true"
	 * @generated
	 */
	EList<DynamicMethodCall> getDynamicMethodCalls();

	/**
	 * Returns the value of the '<em><b>Static Field Accesses</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getAccessingScope <em>Accessing Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Field Accesses</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Field Accesses</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMScope_StaticFieldAccesses()
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getAccessingScope
	 * @model opposite="accessingScope" containment="true"
	 * @generated
	 */
	EList<StaticFieldAccess> getStaticFieldAccesses();

	/**
	 * Returns the value of the '<em><b>Local Variable Accesses</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#getAccessingScope <em>Accessing Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Variable Accesses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Variable Accesses</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMScope_LocalVariableAccesses()
	 * @see kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess#getAccessingScope
	 * @model opposite="accessingScope" containment="true"
	 * @generated
	 */
	EList<AOMLocalVariableAccess> getLocalVariableAccesses();

	List<IVariableBinding> getVariableBindings();
	
	HashMap<IVariableBinding, AOMLocalVariable> getVarBinding2AOMLocalVariable();

} // AOMScope
