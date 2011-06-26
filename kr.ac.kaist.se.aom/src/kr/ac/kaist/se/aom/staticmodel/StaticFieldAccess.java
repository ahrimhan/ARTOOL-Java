/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kr.ac.kaist.se.aom.staticmodel;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMScope;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Field Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getAccessingScope <em>Accessing Scope</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getAccessedField <em>Accessed Field</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getAccessingType <em>Accessing Type</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getColumnNumber <em>Column Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getFileName <em>File Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticFieldAccess()
 * @model
 * @generated
 */
public interface StaticFieldAccess extends StaticDependency {
	/**
	 * Returns the value of the '<em><b>Accessing Scope</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMScope#getStaticFieldAccesses <em>Static Field Accesses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accessing Scope</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accessing Scope</em>' container reference.
	 * @see #setAccessingScope(AOMScope)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticFieldAccess_AccessingScope()
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getStaticFieldAccesses
	 * @model opposite="staticFieldAccesses" transient="false"
	 * @generated
	 */
	AOMScope getAccessingScope();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getAccessingScope <em>Accessing Scope</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accessing Scope</em>' container reference.
	 * @see #getAccessingScope()
	 * @generated
	 */
	void setAccessingScope(AOMScope value);

	/**
	 * Returns the value of the '<em><b>Accessed Field</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMField#getStaticReferer <em>Static Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accessed Field</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accessed Field</em>' reference.
	 * @see #setAccessedField(AOMField)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticFieldAccess_AccessedField()
	 * @see kr.ac.kaist.se.aom.structure.AOMField#getStaticReferer
	 * @model opposite="staticReferer"
	 * @generated
	 */
	AOMField getAccessedField();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getAccessedField <em>Accessed Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accessed Field</em>' reference.
	 * @see #getAccessedField()
	 * @generated
	 */
	void setAccessedField(AOMField value);

	/**
	 * Returns the value of the '<em><b>Accessing Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accessing Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accessing Type</em>' reference.
	 * @see #setAccessingType(AOMClass)
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticFieldAccess_AccessingType()
	 * @model
	 * @generated
	 */
	AOMClass getAccessingType();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getAccessingType <em>Accessing Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accessing Type</em>' reference.
	 * @see #getAccessingType()
	 * @generated
	 */
	void setAccessingType(AOMClass value);

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
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticFieldAccess_LineNumber()
	 * @model
	 * @generated
	 */
	int getLineNumber();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getLineNumber <em>Line Number</em>}' attribute.
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
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticFieldAccess_ColumnNumber()
	 * @model
	 * @generated
	 */
	int getColumnNumber();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getColumnNumber <em>Column Number</em>}' attribute.
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
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#getStaticFieldAccess_FileName()
	 * @model
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);
	
	IVariableBinding getVariableBinding();
	void setVariableBinding(IVariableBinding binding);
	
	ITypeBinding getTypeBinding();
	void setTypeBinding(ITypeBinding binding);
} // StaticFieldAccess
