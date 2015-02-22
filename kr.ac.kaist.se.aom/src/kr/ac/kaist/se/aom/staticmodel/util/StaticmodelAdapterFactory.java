/**
 * <copyright>
 * </copyright>
 *
 * $Id: StaticmodelAdapterFactory.java,v 1.4 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.staticmodel.util;

import kr.ac.kaist.se.aom.staticmodel.StaticDependency;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage
 * @generated
 */
public class StaticmodelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StaticmodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticmodelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StaticmodelPackage.eINSTANCE;
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
	protected StaticmodelSwitch<Adapter> modelSwitch =
		new StaticmodelSwitch<Adapter>() {
			@Override
			public Adapter caseStaticMethodCall(StaticMethodCall object) {
				return createStaticMethodCallAdapter();
			}
			@Override
			public Adapter caseStaticDependency(StaticDependency object) {
				return createStaticDependencyAdapter();
			}
			@Override
			public Adapter caseStaticFieldAccess(StaticFieldAccess object) {
				return createStaticFieldAccessAdapter();
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
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.staticmodel.StaticMethodCall <em>Static Method Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticMethodCall
	 * @generated
	 */
	public Adapter createStaticMethodCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.staticmodel.StaticDependency <em>Static Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticDependency
	 * @generated
	 */
	public Adapter createStaticDependencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess <em>Static Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess
	 * @generated
	 */
	public Adapter createStaticFieldAccessAdapter() {
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

} //StaticmodelAdapterFactory
