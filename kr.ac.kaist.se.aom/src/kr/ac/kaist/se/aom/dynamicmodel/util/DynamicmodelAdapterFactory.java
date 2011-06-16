/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicmodelAdapterFactory.java,v 1.4 2011-01-04 00:56:36 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel.util;

import kr.ac.kaist.se.aom.dynamicmodel.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage
 * @generated
 */
public class DynamicmodelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DynamicmodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicmodelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DynamicmodelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicmodelSwitch<Adapter> modelSwitch =
		new DynamicmodelSwitch<Adapter>() {
			@Override
			public Adapter caseDynamicDependency(DynamicDependency object) {
				return createDynamicDependencyAdapter();
			}
			@Override
			public Adapter caseDynamicMethodCall(DynamicMethodCall object) {
				return createDynamicMethodCallAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicDependency <em>Dynamic Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicDependency
	 * @generated
	 */
	public Adapter createDynamicDependencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall <em>Dynamic Method Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall
	 * @generated
	 */
	public Adapter createDynamicMethodCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //DynamicmodelAdapterFactory
