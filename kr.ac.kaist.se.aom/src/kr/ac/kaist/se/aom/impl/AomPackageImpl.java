/**
 * <copyright>
 * </copyright>
 *
 * $Id: AomPackageImpl.java,v 1.11 2011-02-07 08:36:33 igsong Exp $
 */
package kr.ac.kaist.se.aom.impl;

import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.AomFactory;
import kr.ac.kaist.se.aom.AomPackage;
import kr.ac.kaist.se.aom.MeasurableElement;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;
import kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelPackageImpl;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.staticmodel.impl.StaticmodelPackageImpl;
import kr.ac.kaist.se.aom.structure.StructurePackage;
import kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AomPackageImpl extends EPackageImpl implements AomPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractObjectModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurableElementEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToObjectEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see kr.ac.kaist.se.aom.AomPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AomPackageImpl() {
		super(eNS_URI, AomFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link AomPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AomPackage init() {
		if (isInited) return (AomPackage)EPackage.Registry.INSTANCE.getEPackage(AomPackage.eNS_URI);

		// Obtain or create and register package
		AomPackageImpl theAomPackage = (AomPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AomPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AomPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		StructurePackageImpl theStructurePackage = (StructurePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StructurePackage.eNS_URI) instanceof StructurePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StructurePackage.eNS_URI) : StructurePackage.eINSTANCE);
		StaticmodelPackageImpl theStaticmodelPackage = (StaticmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI) instanceof StaticmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI) : StaticmodelPackage.eINSTANCE);
		DynamicmodelPackageImpl theDynamicmodelPackage = (DynamicmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI) instanceof DynamicmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI) : DynamicmodelPackage.eINSTANCE);

		// Create package meta-data objects
		theAomPackage.createPackageContents();
		theStructurePackage.createPackageContents();
		theStaticmodelPackage.createPackageContents();
		theDynamicmodelPackage.createPackageContents();

		// Initialize created meta-data
		theAomPackage.initializePackageContents();
		theStructurePackage.initializePackageContents();
		theStaticmodelPackage.initializePackageContents();
		theDynamicmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAomPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AomPackage.eNS_URI, theAomPackage);
		return theAomPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractObjectModel() {
		return abstractObjectModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractObjectModel_Classes() {
		return (EReference)abstractObjectModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractObjectModel_ExternalTypes() {
		return (EReference)abstractObjectModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurableElement() {
		return measurableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurableElement_MeasuredDataSet() {
		return (EReference)measurableElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToObject() {
		return stringToObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToObject_Key() {
		return (EAttribute)stringToObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToObject_Value() {
		return (EAttribute)stringToObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AomFactory getAomFactory() {
		return (AomFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		abstractObjectModelEClass = createEClass(ABSTRACT_OBJECT_MODEL);
		createEReference(abstractObjectModelEClass, ABSTRACT_OBJECT_MODEL__CLASSES);
		createEReference(abstractObjectModelEClass, ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES);

		measurableElementEClass = createEClass(MEASURABLE_ELEMENT);
		createEReference(measurableElementEClass, MEASURABLE_ELEMENT__MEASURED_DATA_SET);

		stringToObjectEClass = createEClass(STRING_TO_OBJECT);
		createEAttribute(stringToObjectEClass, STRING_TO_OBJECT__KEY);
		createEAttribute(stringToObjectEClass, STRING_TO_OBJECT__VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		StructurePackage theStructurePackage = (StructurePackage)EPackage.Registry.INSTANCE.getEPackage(StructurePackage.eNS_URI);
		StaticmodelPackage theStaticmodelPackage = (StaticmodelPackage)EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI);
		DynamicmodelPackage theDynamicmodelPackage = (DynamicmodelPackage)EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theStructurePackage);
		getESubpackages().add(theStaticmodelPackage);
		getESubpackages().add(theDynamicmodelPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractObjectModelEClass.getESuperTypes().add(this.getMeasurableElement());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractObjectModelEClass, AbstractObjectModel.class, "AbstractObjectModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractObjectModel_Classes(), theStructurePackage.getAOMClass(), null, "classes", null, 0, -1, AbstractObjectModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractObjectModel_ExternalTypes(), theStructurePackage.getAOMExternalType(), null, "externalTypes", null, 0, -1, AbstractObjectModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measurableElementEClass, MeasurableElement.class, "MeasurableElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasurableElement_MeasuredDataSet(), this.getStringToObject(), null, "measuredDataSet", null, 0, -1, MeasurableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToObjectEClass, Map.Entry.class, "StringToObject", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToObject_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringToObject_Value(), ecorePackage.getEJavaObject(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //AomPackageImpl
