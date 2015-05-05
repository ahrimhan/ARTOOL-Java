/**
 * <copyright>
 * </copyright>
 *
 * $Id: AomFactoryImpl.java,v 1.9 2011-02-07 08:36:33 igsong Exp $
 */
package kr.ac.kaist.se.aom.impl;

import java.util.Map;

import kr.ac.kaist.se.aom.*;
import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.AomFactory;
import kr.ac.kaist.se.aom.AomPackage;

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
public class AomFactoryImpl extends EFactoryImpl implements AomFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AomFactory init() {
		try {
			AomFactory theAomFactory = (AomFactory)EPackage.Registry.INSTANCE.getEFactory(AomPackage.eNS_URI);
			if (theAomFactory != null) {
				return theAomFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AomFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AomFactoryImpl() {
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
			case AomPackage.ABSTRACT_OBJECT_MODEL: return createAbstractObjectModel();
			case AomPackage.STRING_TO_OBJECT: return (EObject)createStringToObject();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractObjectModel createAbstractObjectModel() {
		AbstractObjectModelImpl abstractObjectModel = new AbstractObjectModelImpl();
		return abstractObjectModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Object> createStringToObject() {
		StringToObjectImpl stringToObject = new StringToObjectImpl();
		return stringToObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AomPackage getAomPackage() {
		return (AomPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AomPackage getPackage() {
		return AomPackage.eINSTANCE;
	}

} //AomFactoryImpl
