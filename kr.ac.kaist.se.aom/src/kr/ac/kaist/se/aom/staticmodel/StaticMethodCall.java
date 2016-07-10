/**
 * <copyright>
 * </copyright>
 *
 * $Id: StaticMethodCall.java,v 1.9 2011-01-05 07:42:55 igsong Exp $
 */
package kr.ac.kaist.se.aom.staticmodel;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMScope;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Method Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCaller <em>Caller</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallee <em>Callee</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallingType <em>Calling Type</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getColumnNumber <em>Column Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getFileName <em>File Name</em>}</li>
 * </ul>
 *
 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticMethodCall()
 * @model
 * @generated
 */
public interface StaticMethodCall extends StaticDependency {
	/**
	 * Returns the value of the '<em><b>Caller</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMScope#getStaticMethodCalls <em>Static Method Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caller</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caller</em>' container reference.
	 * @see #setCaller(AOMScope)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticMethodCall_Caller()
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getStaticMethodCalls
	 * @model opposite="staticMethodCalls" transient="false"
	 * @generated
	 */
	AOMScope getCaller();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCaller <em>Caller</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller</em>' container reference.
	 * @see #getCaller()
	 * @generated
	 */
	void setCaller(AOMScope value);

	/**
	 * Returns the value of the '<em><b>Callee</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getStaticReferer <em>Static Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callee</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callee</em>' reference.
	 * @see #setCallee(AOMMethod)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticMethodCall_Callee()
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getStaticReferer
	 * @model opposite="staticReferer"
	 * @generated
	 */
	AOMMethod getCallee();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallee <em>Callee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callee</em>' reference.
	 * @see #getCallee()
	 * @generated
	 */
	void setCallee(AOMMethod value);

	/**
	 * Returns the value of the '<em><b>Calling Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calling Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Calling Type</em>' reference.
	 * @see #setCallingType(AOMClass)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticMethodCall_CallingType()
	 * @model
	 * @generated
	 */
	AOMClass getCallingType();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallingType <em>Calling Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calling Type</em>' reference.
	 * @see #getCallingType()
	 * @generated
	 */
	void setCallingType(AOMClass value);

	
	/**
	 * Returns the value of the '<em><b>Line Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Number</em>' attribute.
	 * @see #setLineNumber(int)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticMethodCall_LineNumber()
	 * @model
	 * @generated
	 */
	int getLineNumber();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getLineNumber <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Number</em>' attribute.
	 * @see #getLineNumber()
	 * @generated
	 */
	void setLineNumber(int value);

	/**
	 * Returns the value of the '<em><b>Column Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Number</em>' attribute.
	 * @see #setColumnNumber(int)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticMethodCall_ColumnNumber()
	 * @model
	 * @generated
	 */
	int getColumnNumber();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getColumnNumber <em>Column Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Number</em>' attribute.
	 * @see #getColumnNumber()
	 * @generated
	 */
	void setColumnNumber(int value);

	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticMethodCall_FileName()
	 * @model
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);

	IMethodBinding getMethodBinding();
	void setMethodBinding(IMethodBinding binding);
	
	ITypeBinding getTypeBinding();
	void setTypeBinding(ITypeBinding binding);
} // StaticMethodCall
