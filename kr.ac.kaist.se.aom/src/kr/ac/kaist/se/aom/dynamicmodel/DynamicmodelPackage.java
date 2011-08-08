/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicmodelPackage.java,v 1.10 2011-01-18 13:56:36 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel;

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
 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelFactory
 * @model kind="package"
 * @generated
 */
public interface DynamicmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dynamicmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://se.kaist.ac.kr/aom/dynamicmodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dynamicmodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DynamicmodelPackage eINSTANCE = kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicDependencyImpl <em>Dynamic Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicDependencyImpl
	 * @see kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelPackageImpl#getDynamicDependency()
	 * @generated
	 */
	int DYNAMIC_DEPENDENCY = 0;

	/**
	 * The number of structural features of the '<em>Dynamic Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_DEPENDENCY_FEATURE_COUNT = 0;


	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl <em>Dynamic Method Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl
	 * @see kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelPackageImpl#getDynamicMethodCall()
	 * @generated
	 */
	int DYNAMIC_METHOD_CALL = 1;

	/**
	 * The feature id for the '<em><b>Caller</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_METHOD_CALL__CALLER = DYNAMIC_DEPENDENCY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Callee</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_METHOD_CALL__CALLEE = DYNAMIC_DEPENDENCY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Previous Call</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_METHOD_CALL__PREVIOUS_CALL = DYNAMIC_DEPENDENCY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Next Calls</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_METHOD_CALL__NEXT_CALLS = DYNAMIC_DEPENDENCY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Static</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_METHOD_CALL__STATIC = DYNAMIC_DEPENDENCY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Tid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_METHOD_CALL__TID = DYNAMIC_DEPENDENCY_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Dynamic Method Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_METHOD_CALL_FEATURE_COUNT = DYNAMIC_DEPENDENCY_FEATURE_COUNT + 6;


	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicDependency <em>Dynamic Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Dependency</em>'.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicDependency
	 * @generated
	 */
	EClass getDynamicDependency();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall <em>Dynamic Method Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Method Call</em>'.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall
	 * @generated
	 */
	EClass getDynamicMethodCall();

	/**
	 * Returns the meta object for the container reference '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCaller <em>Caller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Caller</em>'.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCaller()
	 * @see #getDynamicMethodCall()
	 * @generated
	 */
	EReference getDynamicMethodCall_Caller();

	/**
	 * Returns the meta object for the reference '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Callee</em>'.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getCallee()
	 * @see #getDynamicMethodCall()
	 * @generated
	 */
	EReference getDynamicMethodCall_Callee();

	/**
	 * Returns the meta object for the reference '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getPreviousCall <em>Previous Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Previous Call</em>'.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getPreviousCall()
	 * @see #getDynamicMethodCall()
	 * @generated
	 */
	EReference getDynamicMethodCall_PreviousCall();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getNextCalls <em>Next Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Next Calls</em>'.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getNextCalls()
	 * @see #getDynamicMethodCall()
	 * @generated
	 */
	EReference getDynamicMethodCall_NextCalls();

	/**
	 * Returns the meta object for the reference '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Static</em>'.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getStatic()
	 * @see #getDynamicMethodCall()
	 * @generated
	 */
	EReference getDynamicMethodCall_Static();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getTid <em>Tid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tid</em>'.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall#getTid()
	 * @see #getDynamicMethodCall()
	 * @generated
	 */
	EAttribute getDynamicMethodCall_Tid();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DynamicmodelFactory getDynamicmodelFactory();

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
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicDependencyImpl <em>Dynamic Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicDependencyImpl
		 * @see kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelPackageImpl#getDynamicDependency()
		 * @generated
		 */
		EClass DYNAMIC_DEPENDENCY = eINSTANCE.getDynamicDependency();
		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl <em>Dynamic Method Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl
		 * @see kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelPackageImpl#getDynamicMethodCall()
		 * @generated
		 */
		EClass DYNAMIC_METHOD_CALL = eINSTANCE.getDynamicMethodCall();
		/**
		 * The meta object literal for the '<em><b>Caller</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_METHOD_CALL__CALLER = eINSTANCE.getDynamicMethodCall_Caller();
		/**
		 * The meta object literal for the '<em><b>Callee</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_METHOD_CALL__CALLEE = eINSTANCE.getDynamicMethodCall_Callee();
		/**
		 * The meta object literal for the '<em><b>Previous Call</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_METHOD_CALL__PREVIOUS_CALL = eINSTANCE.getDynamicMethodCall_PreviousCall();
		/**
		 * The meta object literal for the '<em><b>Next Calls</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_METHOD_CALL__NEXT_CALLS = eINSTANCE.getDynamicMethodCall_NextCalls();
		/**
		 * The meta object literal for the '<em><b>Static</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_METHOD_CALL__STATIC = eINSTANCE.getDynamicMethodCall_Static();
		/**
		 * The meta object literal for the '<em><b>Tid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_METHOD_CALL__TID = eINSTANCE.getDynamicMethodCall_Tid();

	}

} //DynamicmodelPackage
