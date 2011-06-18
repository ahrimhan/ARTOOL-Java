/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kr.ac.kaist.se.aom.dynamicmodel;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;

import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMScope;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Field Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getAccessingScope <em>Accessing Scope</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getAccessedField <em>Accessed Field</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getColumnNumber <em>Column Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getFileName <em>File Name</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getStaticFieldAccess <em>Static Field Access</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#isIsReader <em>Is Reader</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#isIsWriter <em>Is Writer</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess()
 * @model
 * @generated
 */
public interface DynamicFieldAccess extends DynamicDependency {
	/**
	 * Returns the value of the '<em><b>Accessing Scope</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMScope#getDynamicFieldAccesses <em>Dynamic Field Accesses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accessing Scope</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accessing Scope</em>' reference.
	 * @see #setAccessingScope(AOMScope)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess_AccessingScope()
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getDynamicFieldAccesses
	 * @model opposite="dynamicFieldAccesses"
	 * @generated
	 */
	AOMScope getAccessingScope();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getAccessingScope <em>Accessing Scope</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accessing Scope</em>' reference.
	 * @see #getAccessingScope()
	 * @generated
	 */
	void setAccessingScope(AOMScope value);

	/**
	 * Returns the value of the '<em><b>Accessed Field</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMField#getDynamicReferer <em>Dynamic Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accessed Field</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accessed Field</em>' reference.
	 * @see #setAccessedField(AOMField)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess_AccessedField()
	 * @see kr.ac.kaist.se.aom.structure.AOMField#getDynamicReferer
	 * @model opposite="dynamicReferer"
	 * @generated
	 */
	AOMField getAccessedField();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getAccessedField <em>Accessed Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accessed Field</em>' reference.
	 * @see #getAccessedField()
	 * @generated
	 */
	void setAccessedField(AOMField value);

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
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess_LineNumber()
	 * @model
	 * @generated
	 */
	int getLineNumber();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getLineNumber <em>Line Number</em>}' attribute.
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
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess_ColumnNumber()
	 * @model
	 * @generated
	 */
	int getColumnNumber();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getColumnNumber <em>Column Number</em>}' attribute.
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
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess_FileName()
	 * @model
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);

	/**
	 * Returns the value of the '<em><b>Static Field Access</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Field Access</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Field Access</em>' reference.
	 * @see #setStaticFieldAccess(StaticFieldAccess)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess_StaticFieldAccess()
	 * @model
	 * @generated
	 */
	StaticFieldAccess getStaticFieldAccess();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#getStaticFieldAccess <em>Static Field Access</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Field Access</em>' reference.
	 * @see #getStaticFieldAccess()
	 * @generated
	 */
	void setStaticFieldAccess(StaticFieldAccess value);

	/**
	 * Returns the value of the '<em><b>Is Reader</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Reader</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Reader</em>' attribute.
	 * @see #setIsReader(boolean)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess_IsReader()
	 * @model
	 * @generated
	 */
	boolean isIsReader();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#isIsReader <em>Is Reader</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Reader</em>' attribute.
	 * @see #isIsReader()
	 * @generated
	 */
	void setIsReader(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Writer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Writer</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Writer</em>' attribute.
	 * @see #setIsWriter(boolean)
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#getDynamicFieldAccess_IsWriter()
	 * @model
	 * @generated
	 */
	boolean isIsWriter();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess#isIsWriter <em>Is Writer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Writer</em>' attribute.
	 * @see #isIsWriter()
	 * @generated
	 */
	void setIsWriter(boolean value);

} // DynamicFieldAccess
