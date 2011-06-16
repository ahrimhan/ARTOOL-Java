/**
 * <copyright>
 * </copyright>
 *
 * $Id: AomPackage.java,v 1.12 2011-02-07 08:36:33 igsong Exp $
 */
package kr.ac.kaist.se.aom;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see kr.ac.kaist.se.aom.AomFactory
 * @model kind="package"
 * @generated
 */
public interface AomPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "aom";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://se.kaist.ac.kr/aom";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "aom";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AomPackage eINSTANCE = kr.ac.kaist.se.aom.impl.AomPackageImpl.init();

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.MeasurableElement <em>Measurable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.MeasurableElement
	 * @see kr.ac.kaist.se.aom.impl.AomPackageImpl#getMeasurableElement()
	 * @generated
	 */
	int MEASURABLE_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Measured Data Set</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURABLE_ELEMENT__MEASURED_DATA_SET = 0;

	/**
	 * The number of structural features of the '<em>Measurable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURABLE_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.impl.AbstractObjectModelImpl <em>Abstract Object Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.impl.AbstractObjectModelImpl
	 * @see kr.ac.kaist.se.aom.impl.AomPackageImpl#getAbstractObjectModel()
	 * @generated
	 */
	int ABSTRACT_OBJECT_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Measured Data Set</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_OBJECT_MODEL__MEASURED_DATA_SET = MEASURABLE_ELEMENT__MEASURED_DATA_SET;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_OBJECT_MODEL__CLASSES = MEASURABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>External Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES = MEASURABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Object Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_OBJECT_MODEL_FEATURE_COUNT = MEASURABLE_ELEMENT_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.impl.StringToObjectImpl <em>String To Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.impl.StringToObjectImpl
	 * @see kr.ac.kaist.se.aom.impl.AomPackageImpl#getStringToObject()
	 * @generated
	 */
	int STRING_TO_OBJECT = 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_FEATURE_COUNT = 2;

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.AbstractObjectModel <em>Abstract Object Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Object Model</em>'.
	 * @see kr.ac.kaist.se.aom.AbstractObjectModel
	 * @generated
	 */
	EClass getAbstractObjectModel();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.AbstractObjectModel#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see kr.ac.kaist.se.aom.AbstractObjectModel#getClasses()
	 * @see #getAbstractObjectModel()
	 * @generated
	 */
	EReference getAbstractObjectModel_Classes();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.AbstractObjectModel#getExternalTypes <em>External Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Types</em>'.
	 * @see kr.ac.kaist.se.aom.AbstractObjectModel#getExternalTypes()
	 * @see #getAbstractObjectModel()
	 * @generated
	 */
	EReference getAbstractObjectModel_ExternalTypes();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.MeasurableElement <em>Measurable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurable Element</em>'.
	 * @see kr.ac.kaist.se.aom.MeasurableElement
	 * @generated
	 */
	EClass getMeasurableElement();

	/**
	 * Returns the meta object for the map '{@link kr.ac.kaist.se.aom.MeasurableElement#getMeasuredDataSet <em>Measured Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Measured Data Set</em>'.
	 * @see kr.ac.kaist.se.aom.MeasurableElement#getMeasuredDataSet()
	 * @see #getMeasurableElement()
	 * @generated
	 */
	EReference getMeasurableElement_MeasuredDataSet();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Object</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EJavaObject"
	 * @generated
	 */
	EClass getStringToObject();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToObject()
	 * @generated
	 */
	EAttribute getStringToObject_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToObject()
	 * @generated
	 */
	EAttribute getStringToObject_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AomFactory getAomFactory();

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
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.impl.AbstractObjectModelImpl <em>Abstract Object Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.impl.AbstractObjectModelImpl
		 * @see kr.ac.kaist.se.aom.impl.AomPackageImpl#getAbstractObjectModel()
		 * @generated
		 */
		EClass ABSTRACT_OBJECT_MODEL = eINSTANCE.getAbstractObjectModel();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_OBJECT_MODEL__CLASSES = eINSTANCE.getAbstractObjectModel_Classes();

		/**
		 * The meta object literal for the '<em><b>External Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES = eINSTANCE.getAbstractObjectModel_ExternalTypes();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.MeasurableElement <em>Measurable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.MeasurableElement
		 * @see kr.ac.kaist.se.aom.impl.AomPackageImpl#getMeasurableElement()
		 * @generated
		 */
		EClass MEASURABLE_ELEMENT = eINSTANCE.getMeasurableElement();

		/**
		 * The meta object literal for the '<em><b>Measured Data Set</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURABLE_ELEMENT__MEASURED_DATA_SET = eINSTANCE.getMeasurableElement_MeasuredDataSet();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.impl.StringToObjectImpl <em>String To Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.impl.StringToObjectImpl
		 * @see kr.ac.kaist.se.aom.impl.AomPackageImpl#getStringToObject()
		 * @generated
		 */
		EClass STRING_TO_OBJECT = eINSTANCE.getStringToObject();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_OBJECT__KEY = eINSTANCE.getStringToObject_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_OBJECT__VALUE = eINSTANCE.getStringToObject_Value();

	}

} //AomPackage
