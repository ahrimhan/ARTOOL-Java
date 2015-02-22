/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMMethod.java,v 1.11 2011-01-14 11:36:22 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import java.util.HashMap;

import kr.ac.kaist.se.aom.MeasurableElement;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.IVariableBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getParameters <em>Parameters</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOwner <em>Owner</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getSignature <em>Signature</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getStaticReferer <em>Static Referer</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOverriding <em>Overriding</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOverridedBy <em>Overrided By</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOwnedScope <em>Owned Scope</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getDynamicReferer <em>Dynamic Referer</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getMethodId <em>Method Id</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getStartLine <em>Start Line</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getEndLine <em>End Line</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#getLOC <em>LOC</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#isStatic <em>Static</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMMethod#isConstructor <em>Constructor</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod()
 * @model
 * @generated
 */
public interface AOMMethod extends AOMNamedElement, AOMTypedElement, MeasurableElement, AOMEntity {
	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMParameter}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMParameter#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_Parameters()
	 * @see kr.ac.kaist.se.aom.structure.AOMParameter#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<AOMParameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMClass#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(AOMClass)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_Owner()
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getMethods
	 * @model opposite="methods" transient="false"
	 * @generated
	 */
	AOMClass getOwner();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(AOMClass value);

	/**
	 * Returns the value of the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signature</em>' attribute.
	 * @see #setSignature(String)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_Signature()
	 * @model derived="true"
	 * @generated
	 */
	String getSignature();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getSignature <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' attribute.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(String value);

	/**
	 * Returns the value of the '<em><b>Static Referer</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Referer</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Referer</em>' reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_StaticReferer()
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallee
	 * @model opposite="callee"
	 * @generated
	 */
	EList<StaticMethodCall> getStaticReferer();

	/**
	 * Returns the value of the '<em><b>Overriding</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOverridedBy <em>Overrided By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overriding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overriding</em>' reference.
	 * @see #setOverriding(AOMMethod)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_Overriding()
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getOverridedBy
	 * @model opposite="overridedBy"
	 * @generated
	 */
	AOMMethod getOverriding();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOverriding <em>Overriding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overriding</em>' reference.
	 * @see #getOverriding()
	 * @generated
	 */
	void setOverriding(AOMMethod value);

	/**
	 * Returns the value of the '<em><b>Overrided By</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMMethod}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOverriding <em>Overriding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overrided By</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overrided By</em>' reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_OverridedBy()
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getOverriding
	 * @model opposite="overriding"
	 * @generated
	 */
	EList<AOMMethod> getOverridedBy();

	/**
	 * Returns the value of the '<em><b>Owned Scope</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMScope#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Scope</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Scope</em>' containment reference.
	 * @see #setOwnedScope(AOMScope)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_OwnedScope()
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	AOMScope getOwnedScope();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOwnedScope <em>Owned Scope</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Scope</em>' containment reference.
	 * @see #getOwnedScope()
	 * @generated
	 */
	void setOwnedScope(AOMScope value);

	/**
	 * Returns the value of the '<em><b>Dynamic Referer</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Referer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Referer</em>' reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_DynamicReferer()
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCallee
	 * @model opposite="callee"
	 * @generated
	 */
	EList<DynamicMethodCall> getDynamicReferer();

	/**
	 * Returns the value of the '<em><b>Method Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Id</em>' attribute.
	 * @see #setMethodId(String)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_MethodId()
	 * @model
	 * @generated
	 */
	String getMethodId();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getMethodId <em>Method Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Id</em>' attribute.
	 * @see #getMethodId()
	 * @generated
	 */
	void setMethodId(String value);

	/**
	 * Returns the value of the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Line</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Line</em>' attribute.
	 * @see #setStartLine(int)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_StartLine()
	 * @model
	 * @generated
	 */
	int getStartLine();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getStartLine <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Line</em>' attribute.
	 * @see #getStartLine()
	 * @generated
	 */
	void setStartLine(int value);

	/**
	 * Returns the value of the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Line</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Line</em>' attribute.
	 * @see #setEndLine(int)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_EndLine()
	 * @model
	 * @generated
	 */
	int getEndLine();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getEndLine <em>End Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Line</em>' attribute.
	 * @see #getEndLine()
	 * @generated
	 */
	void setEndLine(int value);

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_Abstract()
	 * @model
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>LOC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LOC</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LOC</em>' attribute.
	 * @see #setLOC(int)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_LOC()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	int getLOC();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getLOC <em>LOC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LOC</em>' attribute.
	 * @see #getLOC()
	 * @generated
	 */
	void setLOC(int value);

	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_Static()
	 * @model
	 * @generated
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

	/**
	 * Returns the value of the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constructor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constructor</em>' attribute.
	 * @see #setConstructor(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMMethod_Constructor()
	 * @model
	 * @generated
	 */
	boolean isConstructor();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMMethod#isConstructor <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constructor</em>' attribute.
	 * @see #isConstructor()
	 * @generated
	 */
	void setConstructor(boolean value);

	public HashMap<IVariableBinding, AOMParameter> getVarBinding2AOMParameter();

} // AOMMethod
