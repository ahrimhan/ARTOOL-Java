/**
 * <copyright>
 * </copyright>
 *
 * $Id: StaticmodelPackageImpl.java,v 1.8 2011-01-05 07:42:55 igsong Exp $
 */
package kr.ac.kaist.se.aom.staticmodel.impl;

import kr.ac.kaist.se.aom.AomPackage;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;

import kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelPackageImpl;

import kr.ac.kaist.se.aom.impl.AomPackageImpl;

import kr.ac.kaist.se.aom.staticmodel.StaticDependency;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelFactory;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;

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
public class StaticmodelPackageImpl extends EPackageImpl implements StaticmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticMethodCallEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticDependencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticFieldAccessEClass = null;

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
	 * @see kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StaticmodelPackageImpl() {
		super(eNS_URI, StaticmodelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link StaticmodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StaticmodelPackage init() {
		if (isInited) return (StaticmodelPackage)EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI);

		// Obtain or create and register package
		StaticmodelPackageImpl theStaticmodelPackage = (StaticmodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StaticmodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StaticmodelPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		AomPackageImpl theAomPackage = (AomPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AomPackage.eNS_URI) instanceof AomPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AomPackage.eNS_URI) : AomPackage.eINSTANCE);
		StructurePackageImpl theStructurePackage = (StructurePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StructurePackage.eNS_URI) instanceof StructurePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StructurePackage.eNS_URI) : StructurePackage.eINSTANCE);
		DynamicmodelPackageImpl theDynamicmodelPackage = (DynamicmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI) instanceof DynamicmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI) : DynamicmodelPackage.eINSTANCE);

		// Create package meta-data objects
		theStaticmodelPackage.createPackageContents();
		theAomPackage.createPackageContents();
		theStructurePackage.createPackageContents();
		theDynamicmodelPackage.createPackageContents();

		// Initialize created meta-data
		theStaticmodelPackage.initializePackageContents();
		theAomPackage.initializePackageContents();
		theStructurePackage.initializePackageContents();
		theDynamicmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStaticmodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StaticmodelPackage.eNS_URI, theStaticmodelPackage);
		return theStaticmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticMethodCall() {
		return staticMethodCallEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStaticMethodCall_Caller() {
		return (EReference)staticMethodCallEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStaticMethodCall_Callee() {
		return (EReference)staticMethodCallEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStaticMethodCall_CallingType() {
		return (EReference)staticMethodCallEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticMethodCall_LineNumber() {
		return (EAttribute)staticMethodCallEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticMethodCall_ColumnNumber() {
		return (EAttribute)staticMethodCallEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticMethodCall_FileName() {
		return (EAttribute)staticMethodCallEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticDependency() {
		return staticDependencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticFieldAccess() {
		return staticFieldAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStaticFieldAccess_AccessingScope() {
		return (EReference)staticFieldAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStaticFieldAccess_AccessedField() {
		return (EReference)staticFieldAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStaticFieldAccess_AccessingType() {
		return (EReference)staticFieldAccessEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticFieldAccess_LineNumber() {
		return (EAttribute)staticFieldAccessEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticFieldAccess_ColumnNumber() {
		return (EAttribute)staticFieldAccessEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticFieldAccess_FileName() {
		return (EAttribute)staticFieldAccessEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticmodelFactory getStaticmodelFactory() {
		return (StaticmodelFactory)getEFactoryInstance();
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
		staticMethodCallEClass = createEClass(STATIC_METHOD_CALL);
		createEReference(staticMethodCallEClass, STATIC_METHOD_CALL__CALLER);
		createEReference(staticMethodCallEClass, STATIC_METHOD_CALL__CALLEE);
		createEReference(staticMethodCallEClass, STATIC_METHOD_CALL__CALLING_TYPE);
		createEAttribute(staticMethodCallEClass, STATIC_METHOD_CALL__LINE_NUMBER);
		createEAttribute(staticMethodCallEClass, STATIC_METHOD_CALL__COLUMN_NUMBER);
		createEAttribute(staticMethodCallEClass, STATIC_METHOD_CALL__FILE_NAME);

		staticDependencyEClass = createEClass(STATIC_DEPENDENCY);

		staticFieldAccessEClass = createEClass(STATIC_FIELD_ACCESS);
		createEReference(staticFieldAccessEClass, STATIC_FIELD_ACCESS__ACCESSING_SCOPE);
		createEReference(staticFieldAccessEClass, STATIC_FIELD_ACCESS__ACCESSED_FIELD);
		createEReference(staticFieldAccessEClass, STATIC_FIELD_ACCESS__ACCESSING_TYPE);
		createEAttribute(staticFieldAccessEClass, STATIC_FIELD_ACCESS__LINE_NUMBER);
		createEAttribute(staticFieldAccessEClass, STATIC_FIELD_ACCESS__COLUMN_NUMBER);
		createEAttribute(staticFieldAccessEClass, STATIC_FIELD_ACCESS__FILE_NAME);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		staticMethodCallEClass.getESuperTypes().add(this.getStaticDependency());
		staticFieldAccessEClass.getESuperTypes().add(this.getStaticDependency());

		// Initialize classes and features; add operations and parameters
		initEClass(staticMethodCallEClass, StaticMethodCall.class, "StaticMethodCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStaticMethodCall_Caller(), theStructurePackage.getAOMScope(), theStructurePackage.getAOMScope_StaticMethodCalls(), "caller", null, 0, 1, StaticMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStaticMethodCall_Callee(), theStructurePackage.getAOMMethod(), theStructurePackage.getAOMMethod_StaticReferer(), "callee", null, 0, 1, StaticMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStaticMethodCall_CallingType(), theStructurePackage.getAOMClass(), null, "callingType", null, 0, 1, StaticMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStaticMethodCall_LineNumber(), ecorePackage.getEInt(), "lineNumber", null, 0, 1, StaticMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStaticMethodCall_ColumnNumber(), ecorePackage.getEInt(), "columnNumber", null, 0, 1, StaticMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStaticMethodCall_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, StaticMethodCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(staticDependencyEClass, StaticDependency.class, "StaticDependency", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(staticFieldAccessEClass, StaticFieldAccess.class, "StaticFieldAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStaticFieldAccess_AccessingScope(), theStructurePackage.getAOMScope(), theStructurePackage.getAOMScope_StaticFieldAccesses(), "accessingScope", null, 0, 1, StaticFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStaticFieldAccess_AccessedField(), theStructurePackage.getAOMField(), theStructurePackage.getAOMField_StaticReferer(), "accessedField", null, 0, 1, StaticFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStaticFieldAccess_AccessingType(), theStructurePackage.getAOMClass(), null, "accessingType", null, 0, 1, StaticFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStaticFieldAccess_LineNumber(), ecorePackage.getEInt(), "lineNumber", null, 0, 1, StaticFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStaticFieldAccess_ColumnNumber(), ecorePackage.getEInt(), "columnNumber", null, 0, 1, StaticFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStaticFieldAccess_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, StaticFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //StaticmodelPackageImpl
