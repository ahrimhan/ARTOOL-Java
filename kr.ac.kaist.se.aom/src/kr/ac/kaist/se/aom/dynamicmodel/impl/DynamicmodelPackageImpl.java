/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicmodelPackageImpl.java,v 1.10 2011-01-18 13:56:36 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel.impl;

import kr.ac.kaist.se.aom.AomPackage;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicDependency;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelFactory;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;

import kr.ac.kaist.se.aom.impl.AomPackageImpl;

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
public class DynamicmodelPackageImpl extends EPackageImpl implements DynamicmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicDependencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicMethodCallEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicFieldAccessEClass = null;

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
	 * @see kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DynamicmodelPackageImpl() {
		super(eNS_URI, DynamicmodelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DynamicmodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DynamicmodelPackage init() {
		if (isInited) return (DynamicmodelPackage)EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI);

		// Obtain or create and register package
		DynamicmodelPackageImpl theDynamicmodelPackage = (DynamicmodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DynamicmodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DynamicmodelPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		AomPackageImpl theAomPackage = (AomPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AomPackage.eNS_URI) instanceof AomPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AomPackage.eNS_URI) : AomPackage.eINSTANCE);
		StructurePackageImpl theStructurePackage = (StructurePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StructurePackage.eNS_URI) instanceof StructurePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StructurePackage.eNS_URI) : StructurePackage.eINSTANCE);
		StaticmodelPackageImpl theStaticmodelPackage = (StaticmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI) instanceof StaticmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI) : StaticmodelPackage.eINSTANCE);

		// Create package meta-data objects
		theDynamicmodelPackage.createPackageContents();
		theAomPackage.createPackageContents();
		theStructurePackage.createPackageContents();
		theStaticmodelPackage.createPackageContents();

		// Initialize created meta-data
		theDynamicmodelPackage.initializePackageContents();
		theAomPackage.initializePackageContents();
		theStructurePackage.initializePackageContents();
		theStaticmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDynamicmodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DynamicmodelPackage.eNS_URI, theDynamicmodelPackage);
		return theDynamicmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicDependency() {
		return dynamicDependencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicMethodCall() {
		return dynamicMethodCallEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicMethodCall_Caller() {
		return (EReference)dynamicMethodCallEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicMethodCall_Callee() {
		return (EReference)dynamicMethodCallEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicMethodCall_PreviousCall() {
		return (EReference)dynamicMethodCallEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicMethodCall_NextCalls() {
		return (EReference)dynamicMethodCallEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicMethodCall_Static() {
		return (EReference)dynamicMethodCallEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicMethodCall_Tid() {
		return (EAttribute)dynamicMethodCallEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicFieldAccess() {
		return dynamicFieldAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicFieldAccess_AccessingScope() {
		return (EReference)dynamicFieldAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicFieldAccess_AccessedField() {
		return (EReference)dynamicFieldAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicFieldAccess_LineNumber() {
		return (EAttribute)dynamicFieldAccessEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicFieldAccess_ColumnNumber() {
		return (EAttribute)dynamicFieldAccessEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicFieldAccess_FileName() {
		return (EAttribute)dynamicFieldAccessEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicFieldAccess_StaticFieldAccess() {
		return (EReference)dynamicFieldAccessEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicFieldAccess_IsReader() {
		return (EAttribute)dynamicFieldAccessEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicFieldAccess_IsWriter() {
		return (EAttribute)dynamicFieldAccessEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicmodelFactory getDynamicmodelFactory() {
		return (DynamicmodelFactory)getEFactoryInstance();
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
		dynamicDependencyEClass = createEClass(DYNAMIC_DEPENDENCY);

		dynamicMethodCallEClass = createEClass(DYNAMIC_METHOD_CALL);
		createEReference(dynamicMethodCallEClass, DYNAMIC_METHOD_CALL__CALLER);
		createEReference(dynamicMethodCallEClass, DYNAMIC_METHOD_CALL__CALLEE);
		createEReference(dynamicMethodCallEClass, DYNAMIC_METHOD_CALL__PREVIOUS_CALL);
		createEReference(dynamicMethodCallEClass, DYNAMIC_METHOD_CALL__NEXT_CALLS);
		createEReference(dynamicMethodCallEClass, DYNAMIC_METHOD_CALL__STATIC);
		createEAttribute(dynamicMethodCallEClass, DYNAMIC_METHOD_CALL__TID);

		dynamicFieldAccessEClass = createEClass(DYNAMIC_FIELD_ACCESS);
		createEReference(dynamicFieldAccessEClass, DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE);
		createEReference(dynamicFieldAccessEClass, DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD);
		createEAttribute(dynamicFieldAccessEClass, DYNAMIC_FIELD_ACCESS__LINE_NUMBER);
		createEAttribute(dynamicFieldAccessEClass, DYNAMIC_FIELD_ACCESS__COLUMN_NUMBER);
		createEAttribute(dynamicFieldAccessEClass, DYNAMIC_FIELD_ACCESS__FILE_NAME);
		createEReference(dynamicFieldAccessEClass, DYNAMIC_FIELD_ACCESS__STATIC_FIELD_ACCESS);
		createEAttribute(dynamicFieldAccessEClass, DYNAMIC_FIELD_ACCESS__IS_READER);
		createEAttribute(dynamicFieldAccessEClass, DYNAMIC_FIELD_ACCESS__IS_WRITER);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		dynamicMethodCallEClass.getESuperTypes().add(this.getDynamicDependency());
		dynamicFieldAccessEClass.getESuperTypes().add(this.getDynamicDependency());

		// Initialize classes and features; add operations and parameters
		initEClass(dynamicDependencyEClass, DynamicDependency.class, "DynamicDependency", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dynamicMethodCallEClass, DynamicMethodCall.class, "DynamicMethodCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDynamicMethodCall_Caller(), theStructurePackage.getAOMScope(), theStructurePackage.getAOMScope_DynamicMethodCalls(), "caller", null, 0, 1, DynamicMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicMethodCall_Callee(), theStructurePackage.getAOMMethod(), theStructurePackage.getAOMMethod_DynamicReferer(), "callee", null, 0, 1, DynamicMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicMethodCall_PreviousCall(), this.getDynamicMethodCall(), this.getDynamicMethodCall_NextCalls(), "previousCall", null, 0, 1, DynamicMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicMethodCall_NextCalls(), this.getDynamicMethodCall(), this.getDynamicMethodCall_PreviousCall(), "nextCalls", null, 0, -1, DynamicMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicMethodCall_Static(), theStaticmodelPackage.getStaticMethodCall(), null, "static", null, 0, 1, DynamicMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicMethodCall_Tid(), ecorePackage.getEInt(), "tid", null, 0, 1, DynamicMethodCall.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dynamicFieldAccessEClass, DynamicFieldAccess.class, "DynamicFieldAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDynamicFieldAccess_AccessingScope(), theStructurePackage.getAOMScope(), theStructurePackage.getAOMScope_DynamicFieldAccesses(), "accessingScope", null, 0, 1, DynamicFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicFieldAccess_AccessedField(), theStructurePackage.getAOMField(), theStructurePackage.getAOMField_DynamicReferer(), "accessedField", null, 0, 1, DynamicFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicFieldAccess_LineNumber(), ecorePackage.getEInt(), "lineNumber", null, 0, 1, DynamicFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicFieldAccess_ColumnNumber(), ecorePackage.getEInt(), "columnNumber", null, 0, 1, DynamicFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicFieldAccess_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, DynamicFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicFieldAccess_StaticFieldAccess(), theStaticmodelPackage.getStaticFieldAccess(), null, "staticFieldAccess", null, 0, 1, DynamicFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicFieldAccess_IsReader(), ecorePackage.getEBoolean(), "isReader", null, 0, 1, DynamicFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicFieldAccess_IsWriter(), ecorePackage.getEBoolean(), "isWriter", null, 0, 1, DynamicFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //DynamicmodelPackageImpl
