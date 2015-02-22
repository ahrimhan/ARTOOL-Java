/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicmodelFactoryImpl.java,v 1.4 2011-01-04 00:56:36 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel.impl;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelFactory;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;

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
public class DynamicmodelFactoryImpl extends EFactoryImpl implements DynamicmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DynamicmodelFactory init() {
		try {
			DynamicmodelFactory theDynamicmodelFactory = (DynamicmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://se.kaist.ac.kr/aom/dynamicmodel"); 
			if (theDynamicmodelFactory != null) {
				return theDynamicmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DynamicmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicmodelFactoryImpl() {
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
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL: return createDynamicMethodCall();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicMethodCall createDynamicMethodCall() {
		DynamicMethodCallImpl dynamicMethodCall = new DynamicMethodCallImpl();
		return dynamicMethodCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicmodelPackage getDynamicmodelPackage() {
		return (DynamicmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DynamicmodelPackage getPackage() {
		return DynamicmodelPackage.eINSTANCE;
	}

} //DynamicmodelFactoryImpl
