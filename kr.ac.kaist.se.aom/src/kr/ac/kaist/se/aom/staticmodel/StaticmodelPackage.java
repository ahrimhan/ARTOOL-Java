/**
 * <copyright>
 * </copyright>
 *
 * $Id: StaticmodelPackage.java,v 1.7 2011-01-05 07:42:55 igsong Exp $
 */
package kr.ac.kaist.se.aom.staticmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelFactory
 * @model kind="package"
 * @generated
 */
public interface StaticmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "staticmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://se.kaist.ac.kr/aom/staticmodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "staticmodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StaticmodelPackage eINSTANCE = kr.ac.kaist.se.aom.staticmodel.impl.StaticmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticDependencyImpl <em>Static Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.staticmodel.impl.StaticDependencyImpl
	 * @see kr.ac.kaist.se.aom.staticmodel.impl.StaticmodelPackageImpl#getStaticDependency()
	 * @generated
	 */
	int STATIC_DEPENDENCY = 1;

	/**
	 * The number of structural features of the '<em>Static Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_DEPENDENCY_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl <em>Static Method Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl
	 * @see kr.ac.kaist.se.aom.staticmodel.impl.StaticmodelPackageImpl#getStaticMethodCall()
	 * @generated
	 */
	int STATIC_METHOD_CALL = 0;

	/**
	 * The feature id for the '<em><b>Caller</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_METHOD_CALL__CALLER = STATIC_DEPENDENCY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Callee</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_METHOD_CALL__CALLEE = STATIC_DEPENDENCY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Calling Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_METHOD_CALL__CALLING_TYPE = STATIC_DEPENDENCY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Line Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_METHOD_CALL__LINE_NUMBER = STATIC_DEPENDENCY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Column Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_METHOD_CALL__COLUMN_NUMBER = STATIC_DEPENDENCY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_METHOD_CALL__FILE_NAME = STATIC_DEPENDENCY_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Static Method Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_METHOD_CALL_FEATURE_COUNT = STATIC_DEPENDENCY_FEATURE_COUNT + 6;


	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall <em>Static Method Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Method Call</em>'.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall
	 * @generated
	 */
	EClass getStaticMethodCall();

	/**
	 * Returns the meta object for the container reference '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCaller <em>Caller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Caller</em>'.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCaller()
	 * @see #getStaticMethodCall()
	 * @generated
	 */
	EReference getStaticMethodCall_Caller();

	/**
	 * Returns the meta object for the reference '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Callee</em>'.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallee()
	 * @see #getStaticMethodCall()
	 * @generated
	 */
	EReference getStaticMethodCall_Callee();

	/**
	 * Returns the meta object for the reference '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallingType <em>Calling Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Calling Type</em>'.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getCallingType()
	 * @see #getStaticMethodCall()
	 * @generated
	 */
	EReference getStaticMethodCall_CallingType();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getLineNumber <em>Line Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Number</em>'.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getLineNumber()
	 * @see #getStaticMethodCall()
	 * @generated
	 */
	EAttribute getStaticMethodCall_LineNumber();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getColumnNumber <em>Column Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Number</em>'.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getColumnNumber()
	 * @see #getStaticMethodCall()
	 * @generated
	 */
	EAttribute getStaticMethodCall_ColumnNumber();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall#getFileName()
	 * @see #getStaticMethodCall()
	 * @generated
	 */
	EAttribute getStaticMethodCall_FileName();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.staticmodel.StaticDependency <em>Static Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Dependency</em>'.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticDependency
	 * @generated
	 */
	EClass getStaticDependency();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StaticmodelFactory getStaticmodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl <em>Static Method Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl
		 * @see kr.ac.kaist.se.aom.staticmodel.impl.StaticmodelPackageImpl#getStaticMethodCall()
		 * @generated
		 */
		EClass STATIC_METHOD_CALL = eINSTANCE.getStaticMethodCall();

		/**
		 * The meta object literal for the '<em><b>Caller</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATIC_METHOD_CALL__CALLER = eINSTANCE.getStaticMethodCall_Caller();

		/**
		 * The meta object literal for the '<em><b>Callee</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATIC_METHOD_CALL__CALLEE = eINSTANCE.getStaticMethodCall_Callee();

		/**
		 * The meta object literal for the '<em><b>Calling Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATIC_METHOD_CALL__CALLING_TYPE = eINSTANCE.getStaticMethodCall_CallingType();

		/**
		 * The meta object literal for the '<em><b>Line Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATIC_METHOD_CALL__LINE_NUMBER = eINSTANCE.getStaticMethodCall_LineNumber();

		/**
		 * The meta object literal for the '<em><b>Column Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATIC_METHOD_CALL__COLUMN_NUMBER = eINSTANCE.getStaticMethodCall_ColumnNumber();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATIC_METHOD_CALL__FILE_NAME = eINSTANCE.getStaticMethodCall_FileName();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticDependencyImpl <em>Static Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.staticmodel.impl.StaticDependencyImpl
		 * @see kr.ac.kaist.se.aom.staticmodel.impl.StaticmodelPackageImpl#getStaticDependency()
		 * @generated
		 */
		EClass STATIC_DEPENDENCY = eINSTANCE.getStaticDependency();

	}

} //StaticmodelPackage
