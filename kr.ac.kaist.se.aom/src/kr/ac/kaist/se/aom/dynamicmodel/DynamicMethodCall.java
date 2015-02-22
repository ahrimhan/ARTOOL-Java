/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicMethodCall.java,v 1.7 2011-01-18 13:56:36 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel;

import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMScope;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Method Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCaller <em>Caller</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCallee <em>Callee</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getPreviousCall <em>Previous Call</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getNextCalls <em>Next Calls</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getStatic <em>Static</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getTid <em>Tid</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicMethodCall()
 * @model
 * @generated
 */
public interface DynamicMethodCall extends DynamicDependency {
	/**
	 * Returns the value of the '<em><b>Caller</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMScope#getDynamicMethodCalls <em>Dynamic Method Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caller</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caller</em>' container reference.
	 * @see #setCaller(AOMScope)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicMethodCall_Caller()
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getDynamicMethodCalls
	 * @model opposite="dynamicMethodCalls" transient="false"
	 * @generated
	 */
	AOMScope getCaller();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCaller <em>Caller</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller</em>' container reference.
	 * @see #getCaller()
	 * @generated
	 */
	void setCaller(AOMScope value);

	/**
	 * Returns the value of the '<em><b>Callee</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getDynamicReferer <em>Dynamic Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callee</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callee</em>' reference.
	 * @see #setCallee(AOMMethod)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicMethodCall_Callee()
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getDynamicReferer
	 * @model opposite="dynamicReferer"
	 * @generated
	 */
	AOMMethod getCallee();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCallee <em>Callee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callee</em>' reference.
	 * @see #getCallee()
	 * @generated
	 */
	void setCallee(AOMMethod value);

	/**
	 * Returns the value of the '<em><b>Previous Call</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getNextCalls <em>Next Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous Call</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous Call</em>' reference.
	 * @see #setPreviousCall(DynamicMethodCall)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicMethodCall_PreviousCall()
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getNextCalls
	 * @model opposite="nextCalls"
	 * @generated
	 */
	DynamicMethodCall getPreviousCall();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getPreviousCall <em>Previous Call</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Previous Call</em>' reference.
	 * @see #getPreviousCall()
	 * @generated
	 */
	void setPreviousCall(DynamicMethodCall value);

	/**
	 * Returns the value of the '<em><b>Next Calls</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getPreviousCall <em>Previous Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Calls</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Calls</em>' reference list.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicMethodCall_NextCalls()
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getPreviousCall
	 * @model opposite="previousCall"
	 * @generated
	 */
	EList<DynamicMethodCall> getNextCalls();

	/**
	 * Returns the value of the '<em><b>Static</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' reference.
	 * @see #setStatic(StaticMethodCall)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicMethodCall_Static()
	 * @model
	 * @generated
	 */
	StaticMethodCall getStatic();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getStatic <em>Static</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' reference.
	 * @see #getStatic()
	 * @generated
	 */
	void setStatic(StaticMethodCall value);

	/**
	 * Returns the value of the '<em><b>Tid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tid</em>' attribute.
	 * @see #setTid(int)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicMethodCall_Tid()
	 * @model transient="true"
	 * @generated
	 */
	int getTid();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getTid <em>Tid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tid</em>' attribute.
	 * @see #getTid()
	 * @generated
	 */
	void setTid(int value);

} // DynamicMethodCall
