/**
 * <copyright>
 * </copyright>
 *
 * $Id: StructurePackageImpl.java,v 1.14 2011-01-14 11:36:22 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.impl;

import kr.ac.kaist.se.aom.AomPackage;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;
import kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicmodelPackageImpl;
import kr.ac.kaist.se.aom.impl.AomPackageImpl;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.staticmodel.impl.StaticmodelPackageImpl;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMElement;
import kr.ac.kaist.se.aom.structure.AOMExternalType;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMLocalVariable;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMModifier;
import kr.ac.kaist.se.aom.structure.AOMNamedElement;
import kr.ac.kaist.se.aom.structure.AOMParameter;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.AOMType;
import kr.ac.kaist.se.aom.structure.AOMTypedElement;
import kr.ac.kaist.se.aom.structure.AOMVariableDef;
import kr.ac.kaist.se.aom.structure.StructureFactory;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StructurePackageImpl extends EPackageImpl implements StructurePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomNamedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomTypedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomMethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomScopeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomVariableDefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomLocalVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aomExternalTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum aomModifierEEnum = null;

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
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StructurePackageImpl() {
		super(eNS_URI, StructureFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link StructurePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StructurePackage init() {
		if (isInited) return (StructurePackage)EPackage.Registry.INSTANCE.getEPackage(StructurePackage.eNS_URI);

		// Obtain or create and register package
		StructurePackageImpl theStructurePackage = (StructurePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StructurePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StructurePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		AomPackageImpl theAomPackage = (AomPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AomPackage.eNS_URI) instanceof AomPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AomPackage.eNS_URI) : AomPackage.eINSTANCE);
		StaticmodelPackageImpl theStaticmodelPackage = (StaticmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI) instanceof StaticmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI) : StaticmodelPackage.eINSTANCE);
		DynamicmodelPackageImpl theDynamicmodelPackage = (DynamicmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI) instanceof DynamicmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI) : DynamicmodelPackage.eINSTANCE);

		// Create package meta-data objects
		theStructurePackage.createPackageContents();
		theAomPackage.createPackageContents();
		theStaticmodelPackage.createPackageContents();
		theDynamicmodelPackage.createPackageContents();

		// Initialize created meta-data
		theStructurePackage.initializePackageContents();
		theAomPackage.initializePackageContents();
		theStaticmodelPackage.initializePackageContents();
		theDynamicmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStructurePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StructurePackage.eNS_URI, theStructurePackage);
		return theStructurePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMElement() {
		return aomElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMNamedElement() {
		return aomNamedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMNamedElement_Name() {
		return (EAttribute)aomNamedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMTypedElement() {
		return aomTypedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMTypedElement_Type() {
		return (EReference)aomTypedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMType() {
		return aomTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMType_Referer() {
		return (EReference)aomTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMType_Fqdn() {
		return (EAttribute)aomTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMClass() {
		return aomClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMClass_Fields() {
		return (EReference)aomClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMClass_Methods() {
		return (EReference)aomClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMClass_Ancestor() {
		return (EReference)aomClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMClass_Descendant() {
		return (EReference)aomClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMClass_Abstract() {
		return (EAttribute)aomClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMClass_Interface() {
		return (EAttribute)aomClassEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMClass_InnerClass() {
		return (EAttribute)aomClassEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMClass_AnonymousClass() {
		return (EAttribute)aomClassEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMClass_Modifier() {
		return (EAttribute)aomClassEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMClass_RemainingLOC() {
		return (EAttribute)aomClassEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMClass_LOC() {
		return (EAttribute)aomClassEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMClass_Static() {
		return (EAttribute)aomClassEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMField() {
		return aomFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMField_Owner() {
		return (EReference)aomFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMField_StaticReferer() {
		return (EReference)aomFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMField_DynamicReferer() {
		return (EReference)aomFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMMethod() {
		return aomMethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMMethod_Parameters() {
		return (EReference)aomMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMMethod_Owner() {
		return (EReference)aomMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMMethod_Signature() {
		return (EAttribute)aomMethodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMMethod_StaticReferer() {
		return (EReference)aomMethodEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMMethod_Overriding() {
		return (EReference)aomMethodEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMMethod_OverridedBy() {
		return (EReference)aomMethodEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMMethod_OwnedScope() {
		return (EReference)aomMethodEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMMethod_DynamicReferer() {
		return (EReference)aomMethodEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMMethod_MethodId() {
		return (EAttribute)aomMethodEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMMethod_StartLine() {
		return (EAttribute)aomMethodEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMMethod_EndLine() {
		return (EAttribute)aomMethodEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMMethod_Abstract() {
		return (EAttribute)aomMethodEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMMethod_LOC() {
		return (EAttribute)aomMethodEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMMethod_Static() {
		return (EAttribute)aomMethodEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOMMethod_Constructor() {
		return (EAttribute)aomMethodEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMScope() {
		return aomScopeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMScope_Variables() {
		return (EReference)aomScopeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMScope_Owner() {
		return (EReference)aomScopeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMScope_StaticMethodCalls() {
		return (EReference)aomScopeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMScope_DynamicMethodCalls() {
		return (EReference)aomScopeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMScope_StaticFieldAccesses() {
		return (EReference)aomScopeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMScope_DynamicFieldAccesses() {
		return (EReference)aomScopeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMParameter() {
		return aomParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMParameter_Owner() {
		return (EReference)aomParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMVariableDef() {
		return aomVariableDefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMLocalVariable() {
		return aomLocalVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOMLocalVariable_Owner() {
		return (EReference)aomLocalVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOMExternalType() {
		return aomExternalTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAOMModifier() {
		return aomModifierEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureFactory getStructureFactory() {
		return (StructureFactory)getEFactoryInstance();
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
		aomElementEClass = createEClass(AOM_ELEMENT);

		aomNamedElementEClass = createEClass(AOM_NAMED_ELEMENT);
		createEAttribute(aomNamedElementEClass, AOM_NAMED_ELEMENT__NAME);

		aomTypedElementEClass = createEClass(AOM_TYPED_ELEMENT);
		createEReference(aomTypedElementEClass, AOM_TYPED_ELEMENT__TYPE);

		aomTypeEClass = createEClass(AOM_TYPE);
		createEReference(aomTypeEClass, AOM_TYPE__REFERER);
		createEAttribute(aomTypeEClass, AOM_TYPE__FQDN);

		aomClassEClass = createEClass(AOM_CLASS);
		createEReference(aomClassEClass, AOM_CLASS__FIELDS);
		createEReference(aomClassEClass, AOM_CLASS__METHODS);
		createEReference(aomClassEClass, AOM_CLASS__ANCESTOR);
		createEReference(aomClassEClass, AOM_CLASS__DESCENDANT);
		createEAttribute(aomClassEClass, AOM_CLASS__ABSTRACT);
		createEAttribute(aomClassEClass, AOM_CLASS__INTERFACE);
		createEAttribute(aomClassEClass, AOM_CLASS__INNER_CLASS);
		createEAttribute(aomClassEClass, AOM_CLASS__ANONYMOUS_CLASS);
		createEAttribute(aomClassEClass, AOM_CLASS__MODIFIER);
		createEAttribute(aomClassEClass, AOM_CLASS__REMAINING_LOC);
		createEAttribute(aomClassEClass, AOM_CLASS__LOC);
		createEAttribute(aomClassEClass, AOM_CLASS__STATIC);

		aomFieldEClass = createEClass(AOM_FIELD);
		createEReference(aomFieldEClass, AOM_FIELD__OWNER);
		createEReference(aomFieldEClass, AOM_FIELD__STATIC_REFERER);
		createEReference(aomFieldEClass, AOM_FIELD__DYNAMIC_REFERER);

		aomMethodEClass = createEClass(AOM_METHOD);
		createEReference(aomMethodEClass, AOM_METHOD__PARAMETERS);
		createEReference(aomMethodEClass, AOM_METHOD__OWNER);
		createEAttribute(aomMethodEClass, AOM_METHOD__SIGNATURE);
		createEReference(aomMethodEClass, AOM_METHOD__STATIC_REFERER);
		createEReference(aomMethodEClass, AOM_METHOD__OVERRIDING);
		createEReference(aomMethodEClass, AOM_METHOD__OVERRIDED_BY);
		createEReference(aomMethodEClass, AOM_METHOD__OWNED_SCOPE);
		createEReference(aomMethodEClass, AOM_METHOD__DYNAMIC_REFERER);
		createEAttribute(aomMethodEClass, AOM_METHOD__METHOD_ID);
		createEAttribute(aomMethodEClass, AOM_METHOD__START_LINE);
		createEAttribute(aomMethodEClass, AOM_METHOD__END_LINE);
		createEAttribute(aomMethodEClass, AOM_METHOD__ABSTRACT);
		createEAttribute(aomMethodEClass, AOM_METHOD__LOC);
		createEAttribute(aomMethodEClass, AOM_METHOD__STATIC);
		createEAttribute(aomMethodEClass, AOM_METHOD__CONSTRUCTOR);

		aomScopeEClass = createEClass(AOM_SCOPE);
		createEReference(aomScopeEClass, AOM_SCOPE__VARIABLES);
		createEReference(aomScopeEClass, AOM_SCOPE__OWNER);
		createEReference(aomScopeEClass, AOM_SCOPE__STATIC_METHOD_CALLS);
		createEReference(aomScopeEClass, AOM_SCOPE__DYNAMIC_METHOD_CALLS);
		createEReference(aomScopeEClass, AOM_SCOPE__STATIC_FIELD_ACCESSES);
		createEReference(aomScopeEClass, AOM_SCOPE__DYNAMIC_FIELD_ACCESSES);

		aomParameterEClass = createEClass(AOM_PARAMETER);
		createEReference(aomParameterEClass, AOM_PARAMETER__OWNER);

		aomVariableDefEClass = createEClass(AOM_VARIABLE_DEF);

		aomLocalVariableEClass = createEClass(AOM_LOCAL_VARIABLE);
		createEReference(aomLocalVariableEClass, AOM_LOCAL_VARIABLE__OWNER);

		aomExternalTypeEClass = createEClass(AOM_EXTERNAL_TYPE);

		// Create enums
		aomModifierEEnum = createEEnum(AOM_MODIFIER);
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
		AomPackage theAomPackage = (AomPackage)EPackage.Registry.INSTANCE.getEPackage(AomPackage.eNS_URI);
		StaticmodelPackage theStaticmodelPackage = (StaticmodelPackage)EPackage.Registry.INSTANCE.getEPackage(StaticmodelPackage.eNS_URI);
		DynamicmodelPackage theDynamicmodelPackage = (DynamicmodelPackage)EPackage.Registry.INSTANCE.getEPackage(DynamicmodelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		aomNamedElementEClass.getESuperTypes().add(this.getAOMElement());
		aomTypedElementEClass.getESuperTypes().add(this.getAOMElement());
		aomTypeEClass.getESuperTypes().add(this.getAOMNamedElement());
		aomClassEClass.getESuperTypes().add(this.getAOMType());
		aomClassEClass.getESuperTypes().add(theAomPackage.getMeasurableElement());
		aomFieldEClass.getESuperTypes().add(this.getAOMVariableDef());
		aomFieldEClass.getESuperTypes().add(theAomPackage.getMeasurableElement());
		aomMethodEClass.getESuperTypes().add(this.getAOMNamedElement());
		aomMethodEClass.getESuperTypes().add(this.getAOMTypedElement());
		aomMethodEClass.getESuperTypes().add(theAomPackage.getMeasurableElement());
		aomParameterEClass.getESuperTypes().add(this.getAOMVariableDef());
		aomVariableDefEClass.getESuperTypes().add(this.getAOMTypedElement());
		aomVariableDefEClass.getESuperTypes().add(this.getAOMNamedElement());
		aomLocalVariableEClass.getESuperTypes().add(this.getAOMVariableDef());
		aomExternalTypeEClass.getESuperTypes().add(this.getAOMType());

		// Initialize classes and features; add operations and parameters
		initEClass(aomElementEClass, AOMElement.class, "AOMElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(aomNamedElementEClass, AOMNamedElement.class, "AOMNamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAOMNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, AOMNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomTypedElementEClass, AOMTypedElement.class, "AOMTypedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAOMTypedElement_Type(), this.getAOMType(), this.getAOMType_Referer(), "type", null, 0, 1, AOMTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomTypeEClass, AOMType.class, "AOMType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAOMType_Referer(), this.getAOMTypedElement(), this.getAOMTypedElement_Type(), "referer", null, 0, -1, AOMType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMType_Fqdn(), ecorePackage.getEString(), "fqdn", null, 0, 1, AOMType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomClassEClass, AOMClass.class, "AOMClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAOMClass_Fields(), this.getAOMField(), this.getAOMField_Owner(), "fields", null, 0, -1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMClass_Methods(), this.getAOMMethod(), this.getAOMMethod_Owner(), "methods", null, 0, -1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMClass_Ancestor(), this.getAOMClass(), this.getAOMClass_Descendant(), "ancestor", null, 0, -1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMClass_Descendant(), this.getAOMClass(), this.getAOMClass_Ancestor(), "descendant", null, 0, -1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMClass_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMClass_Interface(), ecorePackage.getEBoolean(), "interface", null, 0, 1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMClass_InnerClass(), ecorePackage.getEBoolean(), "innerClass", null, 0, 1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMClass_AnonymousClass(), ecorePackage.getEBoolean(), "anonymousClass", null, 0, 1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMClass_Modifier(), this.getAOMModifier(), "modifier", null, 0, 1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMClass_RemainingLOC(), ecorePackage.getEInt(), "remainingLOC", null, 0, 1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMClass_LOC(), ecorePackage.getEInt(), "LOC", null, 0, 1, AOMClass.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMClass_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, AOMClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomFieldEClass, AOMField.class, "AOMField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAOMField_Owner(), this.getAOMClass(), this.getAOMClass_Fields(), "owner", null, 0, 1, AOMField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMField_StaticReferer(), theStaticmodelPackage.getStaticFieldAccess(), theStaticmodelPackage.getStaticFieldAccess_AccessedField(), "staticReferer", null, 0, -1, AOMField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMField_DynamicReferer(), theDynamicmodelPackage.getDynamicFieldAccess(), theDynamicmodelPackage.getDynamicFieldAccess_AccessedField(), "dynamicReferer", null, 0, -1, AOMField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomMethodEClass, AOMMethod.class, "AOMMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAOMMethod_Parameters(), this.getAOMParameter(), this.getAOMParameter_Owner(), "parameters", null, 0, -1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMMethod_Owner(), this.getAOMClass(), this.getAOMClass_Methods(), "owner", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMMethod_Signature(), ecorePackage.getEString(), "signature", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAOMMethod_StaticReferer(), theStaticmodelPackage.getStaticMethodCall(), theStaticmodelPackage.getStaticMethodCall_Callee(), "staticReferer", null, 0, -1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMMethod_Overriding(), this.getAOMMethod(), this.getAOMMethod_OverridedBy(), "overriding", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMMethod_OverridedBy(), this.getAOMMethod(), this.getAOMMethod_Overriding(), "overridedBy", null, 0, -1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMMethod_OwnedScope(), this.getAOMScope(), this.getAOMScope_Owner(), "ownedScope", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMMethod_DynamicReferer(), theDynamicmodelPackage.getDynamicMethodCall(), theDynamicmodelPackage.getDynamicMethodCall_Callee(), "dynamicReferer", null, 0, -1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMMethod_MethodId(), ecorePackage.getEString(), "methodId", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMMethod_StartLine(), ecorePackage.getEInt(), "startLine", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMMethod_EndLine(), ecorePackage.getEInt(), "endLine", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMMethod_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMMethod_LOC(), ecorePackage.getEInt(), "LOC", null, 0, 1, AOMMethod.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMMethod_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAOMMethod_Constructor(), ecorePackage.getEBoolean(), "constructor", null, 0, 1, AOMMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomScopeEClass, AOMScope.class, "AOMScope", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAOMScope_Variables(), this.getAOMLocalVariable(), this.getAOMLocalVariable_Owner(), "variables", null, 0, -1, AOMScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMScope_Owner(), this.getAOMMethod(), this.getAOMMethod_OwnedScope(), "owner", null, 0, 1, AOMScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMScope_StaticMethodCalls(), theStaticmodelPackage.getStaticMethodCall(), theStaticmodelPackage.getStaticMethodCall_Caller(), "staticMethodCalls", null, 0, -1, AOMScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMScope_DynamicMethodCalls(), theDynamicmodelPackage.getDynamicMethodCall(), theDynamicmodelPackage.getDynamicMethodCall_Caller(), "dynamicMethodCalls", null, 0, -1, AOMScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMScope_StaticFieldAccesses(), theStaticmodelPackage.getStaticFieldAccess(), theStaticmodelPackage.getStaticFieldAccess_AccessingScope(), "staticFieldAccesses", null, 0, -1, AOMScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOMScope_DynamicFieldAccesses(), theDynamicmodelPackage.getDynamicFieldAccess(), theDynamicmodelPackage.getDynamicFieldAccess_AccessingScope(), "dynamicFieldAccesses", null, 0, -1, AOMScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomParameterEClass, AOMParameter.class, "AOMParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAOMParameter_Owner(), this.getAOMMethod(), this.getAOMMethod_Parameters(), "owner", null, 0, 1, AOMParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomVariableDefEClass, AOMVariableDef.class, "AOMVariableDef", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(aomLocalVariableEClass, AOMLocalVariable.class, "AOMLocalVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAOMLocalVariable_Owner(), this.getAOMScope(), this.getAOMScope_Variables(), "owner", null, 0, 1, AOMLocalVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aomExternalTypeEClass, AOMExternalType.class, "AOMExternalType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(aomModifierEEnum, AOMModifier.class, "AOMModifier");
		addEEnumLiteral(aomModifierEEnum, AOMModifier.PUBLIC);
		addEEnumLiteral(aomModifierEEnum, AOMModifier.PRIVATE);
		addEEnumLiteral(aomModifierEEnum, AOMModifier.PROTECTED);
		addEEnumLiteral(aomModifierEEnum, AOMModifier.PACKAGE);
	}

} //StructurePackageImpl
