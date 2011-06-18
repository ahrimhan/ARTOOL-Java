/**
 * <copyright>
 * </copyright>
 *
 * $Id: StaticmodelFactoryImpl.java,v 1.4 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.staticmodel.impl;

import kr.ac.kaist.se.aom.staticmodel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StaticmodelFactoryImpl extends EFactoryImpl implements StaticmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StaticmodelFactory init() {
		try {
			StaticmodelFactory theStaticmodelFactory = (StaticmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://se.kaist.ac.kr/aom/staticmodel"); 
			if (theStaticmodelFactory != null) {
				return theStaticmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StaticmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticmodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StaticmodelPackage.STATIC_METHOD_CALL: return createStaticMethodCall();
			case StaticmodelPackage.STATIC_FIELD_ACCESS: return createStaticFieldAccess();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticMethodCall createStaticMethodCall() {
		StaticMethodCallImpl staticMethodCall = new StaticMethodCallImpl();
		return staticMethodCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticFieldAccess createStaticFieldAccess() {
		StaticFieldAccessImpl staticFieldAccess = new StaticFieldAccessImpl();
		return staticFieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticmodelPackage getStaticmodelPackage() {
		return (StaticmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StaticmodelPackage getPackage() {
		return StaticmodelPackage.eINSTANCE;
	}

} //StaticmodelFactoryImpl
