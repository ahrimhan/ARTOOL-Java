/**
 * <copyright>
 * </copyright>
 *
 * $Id: StructurePackage.java,v 1.15 2011-01-14 11:36:22 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see kr.ac.kaist.se.aom.structure.StructureFactory
 * @model kind="package"
 * @generated
 */
public interface StructurePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "structure";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://se.kaist.ac.kr/aom/structure";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "structure";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StructurePackage eINSTANCE = kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl.init();

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.AOMElement <em>AOM Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.AOMElement
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMElement()
	 * @generated
	 */
	int AOM_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>AOM Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMNamedElementImpl <em>AOM Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMNamedElementImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMNamedElement()
	 * @generated
	 */
	int AOM_NAMED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_NAMED_ELEMENT__NAME = AOM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>AOM Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_NAMED_ELEMENT_FEATURE_COUNT = AOM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMTypedElementImpl <em>AOM Typed Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMTypedElementImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMTypedElement()
	 * @generated
	 */
	int AOM_TYPED_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_TYPED_ELEMENT__TYPE = AOM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>AOM Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_TYPED_ELEMENT_FEATURE_COUNT = AOM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMTypeImpl <em>AOM Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMTypeImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMType()
	 * @generated
	 */
	int AOM_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_TYPE__NAME = AOM_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Referer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_TYPE__REFERER = AOM_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fqdn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_TYPE__FQDN = AOM_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>AOM Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_TYPE_FEATURE_COUNT = AOM_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl <em>AOM Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMClassImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMClass()
	 * @generated
	 */
	int AOM_CLASS = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__NAME = AOM_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Referer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__REFERER = AOM_TYPE__REFERER;

	/**
	 * The feature id for the '<em><b>Fqdn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__FQDN = AOM_TYPE__FQDN;

	/**
	 * The feature id for the '<em><b>Measured Data Set</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__MEASURED_DATA_SET = AOM_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__FIELDS = AOM_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__METHODS = AOM_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ancestor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__ANCESTOR = AOM_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Descendant</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__DESCENDANT = AOM_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__ABSTRACT = AOM_TYPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__INTERFACE = AOM_TYPE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Inner Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__INNER_CLASS = AOM_TYPE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Anonymous Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__ANONYMOUS_CLASS = AOM_TYPE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Modifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__MODIFIER = AOM_TYPE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Remaining LOC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__REMAINING_LOC = AOM_TYPE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>LOC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__LOC = AOM_TYPE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS__STATIC = AOM_TYPE_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>AOM Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_CLASS_FEATURE_COUNT = AOM_TYPE_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.AOMVariableDef <em>AOM Variable Def</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.AOMVariableDef
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMVariableDef()
	 * @generated
	 */
	int AOM_VARIABLE_DEF = 9;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_VARIABLE_DEF__TYPE = AOM_TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_VARIABLE_DEF__NAME = AOM_TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>AOM Variable Def</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_VARIABLE_DEF_FEATURE_COUNT = AOM_TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl <em>AOM Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMField()
	 * @generated
	 */
	int AOM_FIELD = 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_FIELD__TYPE = AOM_VARIABLE_DEF__TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_FIELD__NAME = AOM_VARIABLE_DEF__NAME;

	/**
	 * The feature id for the '<em><b>Measured Data Set</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_FIELD__MEASURED_DATA_SET = AOM_VARIABLE_DEF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_FIELD__OWNER = AOM_VARIABLE_DEF_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Static Referer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_FIELD__STATIC_REFERER = AOM_VARIABLE_DEF_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Dynamic Referer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_FIELD__DYNAMIC_REFERER = AOM_VARIABLE_DEF_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>AOM Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_FIELD_FEATURE_COUNT = AOM_VARIABLE_DEF_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl <em>AOM Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMMethod()
	 * @generated
	 */
	int AOM_METHOD = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__NAME = AOM_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__TYPE = AOM_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Measured Data Set</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__MEASURED_DATA_SET = AOM_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__PARAMETERS = AOM_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__OWNER = AOM_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__SIGNATURE = AOM_NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Static Referer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__STATIC_REFERER = AOM_NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Overriding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__OVERRIDING = AOM_NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Overrided By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__OVERRIDED_BY = AOM_NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Owned Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__OWNED_SCOPE = AOM_NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Dynamic Referer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__DYNAMIC_REFERER = AOM_NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Method Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__METHOD_ID = AOM_NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__START_LINE = AOM_NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__END_LINE = AOM_NAMED_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__ABSTRACT = AOM_NAMED_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>LOC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__LOC = AOM_NAMED_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__STATIC = AOM_NAMED_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD__CONSTRUCTOR = AOM_NAMED_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The number of structural features of the '<em>AOM Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_METHOD_FEATURE_COUNT = AOM_NAMED_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl <em>AOM Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMScope()
	 * @generated
	 */
	int AOM_SCOPE = 7;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_SCOPE__VARIABLES = 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_SCOPE__OWNER = 1;

	/**
	 * The feature id for the '<em><b>Static Method Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_SCOPE__STATIC_METHOD_CALLS = 2;

	/**
	 * The feature id for the '<em><b>Dynamic Method Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_SCOPE__DYNAMIC_METHOD_CALLS = 3;

	/**
	 * The feature id for the '<em><b>Static Field Accesses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_SCOPE__STATIC_FIELD_ACCESSES = 4;

	/**
	 * The feature id for the '<em><b>Dynamic Field Accesses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_SCOPE__DYNAMIC_FIELD_ACCESSES = 5;

	/**
	 * The number of structural features of the '<em>AOM Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_SCOPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMParameterImpl <em>AOM Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMParameterImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMParameter()
	 * @generated
	 */
	int AOM_PARAMETER = 8;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_PARAMETER__TYPE = AOM_VARIABLE_DEF__TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_PARAMETER__NAME = AOM_VARIABLE_DEF__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_PARAMETER__OWNER = AOM_VARIABLE_DEF_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>AOM Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_PARAMETER_FEATURE_COUNT = AOM_VARIABLE_DEF_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableImpl <em>AOM Local Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMLocalVariable()
	 * @generated
	 */
	int AOM_LOCAL_VARIABLE = 10;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_LOCAL_VARIABLE__TYPE = AOM_VARIABLE_DEF__TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_LOCAL_VARIABLE__NAME = AOM_VARIABLE_DEF__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_LOCAL_VARIABLE__OWNER = AOM_VARIABLE_DEF_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>AOM Local Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_LOCAL_VARIABLE_FEATURE_COUNT = AOM_VARIABLE_DEF_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMExternalTypeImpl <em>AOM External Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.impl.AOMExternalTypeImpl
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMExternalType()
	 * @generated
	 */
	int AOM_EXTERNAL_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_EXTERNAL_TYPE__NAME = AOM_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Referer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_EXTERNAL_TYPE__REFERER = AOM_TYPE__REFERER;

	/**
	 * The feature id for the '<em><b>Fqdn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_EXTERNAL_TYPE__FQDN = AOM_TYPE__FQDN;

	/**
	 * The number of structural features of the '<em>AOM External Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOM_EXTERNAL_TYPE_FEATURE_COUNT = AOM_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link kr.ac.kaist.se.aom.structure.AOMModifier <em>AOM Modifier</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kr.ac.kaist.se.aom.structure.AOMModifier
	 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMModifier()
	 * @generated
	 */
	int AOM_MODIFIER = 12;

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMElement <em>AOM Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Element</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMElement
	 * @generated
	 */
	EClass getAOMElement();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMNamedElement <em>AOM Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Named Element</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMNamedElement
	 * @generated
	 */
	EClass getAOMNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMNamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMNamedElement#getName()
	 * @see #getAOMNamedElement()
	 * @generated
	 */
	EAttribute getAOMNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMTypedElement <em>AOM Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Typed Element</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMTypedElement
	 * @generated
	 */
	EClass getAOMTypedElement();

	/**
	 * Returns the meta object for the reference '{@link kr.ac.kaist.se.aom.structure.AOMTypedElement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMTypedElement#getType()
	 * @see #getAOMTypedElement()
	 * @generated
	 */
	EReference getAOMTypedElement_Type();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMType <em>AOM Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Type</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMType
	 * @generated
	 */
	EClass getAOMType();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.structure.AOMType#getReferer <em>Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Referer</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMType#getReferer()
	 * @see #getAOMType()
	 * @generated
	 */
	EReference getAOMType_Referer();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMType#getFqdn <em>Fqdn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fqdn</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMType#getFqdn()
	 * @see #getAOMType()
	 * @generated
	 */
	EAttribute getAOMType_Fqdn();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMClass <em>AOM Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Class</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass
	 * @generated
	 */
	EClass getAOMClass();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.structure.AOMClass#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getFields()
	 * @see #getAOMClass()
	 * @generated
	 */
	EReference getAOMClass_Fields();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.structure.AOMClass#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getMethods()
	 * @see #getAOMClass()
	 * @generated
	 */
	EReference getAOMClass_Methods();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.structure.AOMClass#getAncestor <em>Ancestor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ancestor</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getAncestor()
	 * @see #getAOMClass()
	 * @generated
	 */
	EReference getAOMClass_Ancestor();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.structure.AOMClass#getDescendant <em>Descendant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Descendant</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getDescendant()
	 * @see #getAOMClass()
	 * @generated
	 */
	EReference getAOMClass_Descendant();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMClass#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#isAbstract()
	 * @see #getAOMClass()
	 * @generated
	 */
	EAttribute getAOMClass_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMClass#isInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#isInterface()
	 * @see #getAOMClass()
	 * @generated
	 */
	EAttribute getAOMClass_Interface();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMClass#isInnerClass <em>Inner Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inner Class</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#isInnerClass()
	 * @see #getAOMClass()
	 * @generated
	 */
	EAttribute getAOMClass_InnerClass();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMClass#isAnonymousClass <em>Anonymous Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Anonymous Class</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#isAnonymousClass()
	 * @see #getAOMClass()
	 * @generated
	 */
	EAttribute getAOMClass_AnonymousClass();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMClass#getModifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modifier</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getModifier()
	 * @see #getAOMClass()
	 * @generated
	 */
	EAttribute getAOMClass_Modifier();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMClass#getRemainingLOC <em>Remaining LOC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining LOC</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getRemainingLOC()
	 * @see #getAOMClass()
	 * @generated
	 */
	EAttribute getAOMClass_RemainingLOC();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMClass#getLOC <em>LOC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>LOC</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getLOC()
	 * @see #getAOMClass()
	 * @generated
	 */
	EAttribute getAOMClass_LOC();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMClass#isStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#isStatic()
	 * @see #getAOMClass()
	 * @generated
	 */
	EAttribute getAOMClass_Static();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMField <em>AOM Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Field</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMField
	 * @generated
	 */
	EClass getAOMField();

	/**
	 * Returns the meta object for the container reference '{@link kr.ac.kaist.se.aom.structure.AOMField#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMField#getOwner()
	 * @see #getAOMField()
	 * @generated
	 */
	EReference getAOMField_Owner();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.structure.AOMField#getStaticReferer <em>Static Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Static Referer</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMField#getStaticReferer()
	 * @see #getAOMField()
	 * @generated
	 */
	EReference getAOMField_StaticReferer();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.structure.AOMField#getDynamicReferer <em>Dynamic Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dynamic Referer</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMField#getDynamicReferer()
	 * @see #getAOMField()
	 * @generated
	 */
	EReference getAOMField_DynamicReferer();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMMethod <em>AOM Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Method</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod
	 * @generated
	 */
	EClass getAOMMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getParameters()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EReference getAOMMethod_Parameters();

	/**
	 * Returns the meta object for the container reference '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getOwner()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EReference getAOMMethod_Owner();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getSignature()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EAttribute getAOMMethod_Signature();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getStaticReferer <em>Static Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Static Referer</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getStaticReferer()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EReference getAOMMethod_StaticReferer();

	/**
	 * Returns the meta object for the reference '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOverriding <em>Overriding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Overriding</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getOverriding()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EReference getAOMMethod_Overriding();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOverridedBy <em>Overrided By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Overrided By</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getOverridedBy()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EReference getAOMMethod_OverridedBy();

	/**
	 * Returns the meta object for the containment reference '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOwnedScope <em>Owned Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Scope</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getOwnedScope()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EReference getAOMMethod_OwnedScope();

	/**
	 * Returns the meta object for the reference list '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getDynamicReferer <em>Dynamic Referer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dynamic Referer</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getDynamicReferer()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EReference getAOMMethod_DynamicReferer();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getMethodId <em>Method Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Id</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getMethodId()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EAttribute getAOMMethod_MethodId();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getStartLine <em>Start Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Line</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getStartLine()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EAttribute getAOMMethod_StartLine();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getEndLine <em>End Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Line</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getEndLine()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EAttribute getAOMMethod_EndLine();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMMethod#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#isAbstract()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EAttribute getAOMMethod_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getLOC <em>LOC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>LOC</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getLOC()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EAttribute getAOMMethod_LOC();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMMethod#isStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#isStatic()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EAttribute getAOMMethod_Static();

	/**
	 * Returns the meta object for the attribute '{@link kr.ac.kaist.se.aom.structure.AOMMethod#isConstructor <em>Constructor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constructor</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#isConstructor()
	 * @see #getAOMMethod()
	 * @generated
	 */
	EAttribute getAOMMethod_Constructor();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMScope <em>AOM Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Scope</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMScope
	 * @generated
	 */
	EClass getAOMScope();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.structure.AOMScope#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getVariables()
	 * @see #getAOMScope()
	 * @generated
	 */
	EReference getAOMScope_Variables();

	/**
	 * Returns the meta object for the container reference '{@link kr.ac.kaist.se.aom.structure.AOMScope#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getOwner()
	 * @see #getAOMScope()
	 * @generated
	 */
	EReference getAOMScope_Owner();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.structure.AOMScope#getStaticMethodCalls <em>Static Method Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Static Method Calls</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getStaticMethodCalls()
	 * @see #getAOMScope()
	 * @generated
	 */
	EReference getAOMScope_StaticMethodCalls();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.structure.AOMScope#getDynamicMethodCalls <em>Dynamic Method Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic Method Calls</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getDynamicMethodCalls()
	 * @see #getAOMScope()
	 * @generated
	 */
	EReference getAOMScope_DynamicMethodCalls();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.structure.AOMScope#getStaticFieldAccesses <em>Static Field Accesses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Static Field Accesses</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getStaticFieldAccesses()
	 * @see #getAOMScope()
	 * @generated
	 */
	EReference getAOMScope_StaticFieldAccesses();

	/**
	 * Returns the meta object for the containment reference list '{@link kr.ac.kaist.se.aom.structure.AOMScope#getDynamicFieldAccesses <em>Dynamic Field Accesses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic Field Accesses</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMScope#getDynamicFieldAccesses()
	 * @see #getAOMScope()
	 * @generated
	 */
	EReference getAOMScope_DynamicFieldAccesses();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMParameter <em>AOM Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Parameter</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMParameter
	 * @generated
	 */
	EClass getAOMParameter();

	/**
	 * Returns the meta object for the container reference '{@link kr.ac.kaist.se.aom.structure.AOMParameter#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMParameter#getOwner()
	 * @see #getAOMParameter()
	 * @generated
	 */
	EReference getAOMParameter_Owner();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMVariableDef <em>AOM Variable Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Variable Def</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMVariableDef
	 * @generated
	 */
	EClass getAOMVariableDef();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariable <em>AOM Local Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM Local Variable</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMLocalVariable
	 * @generated
	 */
	EClass getAOMLocalVariable();

	/**
	 * Returns the meta object for the container reference '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariable#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMLocalVariable#getOwner()
	 * @see #getAOMLocalVariable()
	 * @generated
	 */
	EReference getAOMLocalVariable_Owner();

	/**
	 * Returns the meta object for class '{@link kr.ac.kaist.se.aom.structure.AOMExternalType <em>AOM External Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOM External Type</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMExternalType
	 * @generated
	 */
	EClass getAOMExternalType();

	/**
	 * Returns the meta object for enum '{@link kr.ac.kaist.se.aom.structure.AOMModifier <em>AOM Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>AOM Modifier</em>'.
	 * @see kr.ac.kaist.se.aom.structure.AOMModifier
	 * @generated
	 */
	EEnum getAOMModifier();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StructureFactory getStructureFactory();

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
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.AOMElement <em>AOM Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.AOMElement
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMElement()
		 * @generated
		 */
		EClass AOM_ELEMENT = eINSTANCE.getAOMElement();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMNamedElementImpl <em>AOM Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMNamedElementImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMNamedElement()
		 * @generated
		 */
		EClass AOM_NAMED_ELEMENT = eINSTANCE.getAOMNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_NAMED_ELEMENT__NAME = eINSTANCE.getAOMNamedElement_Name();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMTypedElementImpl <em>AOM Typed Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMTypedElementImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMTypedElement()
		 * @generated
		 */
		EClass AOM_TYPED_ELEMENT = eINSTANCE.getAOMTypedElement();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_TYPED_ELEMENT__TYPE = eINSTANCE.getAOMTypedElement_Type();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMTypeImpl <em>AOM Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMTypeImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMType()
		 * @generated
		 */
		EClass AOM_TYPE = eINSTANCE.getAOMType();

		/**
		 * The meta object literal for the '<em><b>Referer</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_TYPE__REFERER = eINSTANCE.getAOMType_Referer();

		/**
		 * The meta object literal for the '<em><b>Fqdn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_TYPE__FQDN = eINSTANCE.getAOMType_Fqdn();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl <em>AOM Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMClassImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMClass()
		 * @generated
		 */
		EClass AOM_CLASS = eINSTANCE.getAOMClass();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_CLASS__FIELDS = eINSTANCE.getAOMClass_Fields();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_CLASS__METHODS = eINSTANCE.getAOMClass_Methods();

		/**
		 * The meta object literal for the '<em><b>Ancestor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_CLASS__ANCESTOR = eINSTANCE.getAOMClass_Ancestor();

		/**
		 * The meta object literal for the '<em><b>Descendant</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_CLASS__DESCENDANT = eINSTANCE.getAOMClass_Descendant();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_CLASS__ABSTRACT = eINSTANCE.getAOMClass_Abstract();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_CLASS__INTERFACE = eINSTANCE.getAOMClass_Interface();

		/**
		 * The meta object literal for the '<em><b>Inner Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_CLASS__INNER_CLASS = eINSTANCE.getAOMClass_InnerClass();

		/**
		 * The meta object literal for the '<em><b>Anonymous Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_CLASS__ANONYMOUS_CLASS = eINSTANCE.getAOMClass_AnonymousClass();

		/**
		 * The meta object literal for the '<em><b>Modifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_CLASS__MODIFIER = eINSTANCE.getAOMClass_Modifier();

		/**
		 * The meta object literal for the '<em><b>Remaining LOC</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_CLASS__REMAINING_LOC = eINSTANCE.getAOMClass_RemainingLOC();

		/**
		 * The meta object literal for the '<em><b>LOC</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_CLASS__LOC = eINSTANCE.getAOMClass_LOC();

		/**
		 * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_CLASS__STATIC = eINSTANCE.getAOMClass_Static();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl <em>AOM Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMField()
		 * @generated
		 */
		EClass AOM_FIELD = eINSTANCE.getAOMField();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_FIELD__OWNER = eINSTANCE.getAOMField_Owner();

		/**
		 * The meta object literal for the '<em><b>Static Referer</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_FIELD__STATIC_REFERER = eINSTANCE.getAOMField_StaticReferer();

		/**
		 * The meta object literal for the '<em><b>Dynamic Referer</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_FIELD__DYNAMIC_REFERER = eINSTANCE.getAOMField_DynamicReferer();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl <em>AOM Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMMethod()
		 * @generated
		 */
		EClass AOM_METHOD = eINSTANCE.getAOMMethod();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_METHOD__PARAMETERS = eINSTANCE.getAOMMethod_Parameters();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_METHOD__OWNER = eINSTANCE.getAOMMethod_Owner();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_METHOD__SIGNATURE = eINSTANCE.getAOMMethod_Signature();

		/**
		 * The meta object literal for the '<em><b>Static Referer</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_METHOD__STATIC_REFERER = eINSTANCE.getAOMMethod_StaticReferer();

		/**
		 * The meta object literal for the '<em><b>Overriding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_METHOD__OVERRIDING = eINSTANCE.getAOMMethod_Overriding();

		/**
		 * The meta object literal for the '<em><b>Overrided By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_METHOD__OVERRIDED_BY = eINSTANCE.getAOMMethod_OverridedBy();

		/**
		 * The meta object literal for the '<em><b>Owned Scope</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_METHOD__OWNED_SCOPE = eINSTANCE.getAOMMethod_OwnedScope();

		/**
		 * The meta object literal for the '<em><b>Dynamic Referer</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_METHOD__DYNAMIC_REFERER = eINSTANCE.getAOMMethod_DynamicReferer();

		/**
		 * The meta object literal for the '<em><b>Method Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_METHOD__METHOD_ID = eINSTANCE.getAOMMethod_MethodId();

		/**
		 * The meta object literal for the '<em><b>Start Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_METHOD__START_LINE = eINSTANCE.getAOMMethod_StartLine();

		/**
		 * The meta object literal for the '<em><b>End Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_METHOD__END_LINE = eINSTANCE.getAOMMethod_EndLine();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_METHOD__ABSTRACT = eINSTANCE.getAOMMethod_Abstract();

		/**
		 * The meta object literal for the '<em><b>LOC</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_METHOD__LOC = eINSTANCE.getAOMMethod_LOC();

		/**
		 * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_METHOD__STATIC = eINSTANCE.getAOMMethod_Static();

		/**
		 * The meta object literal for the '<em><b>Constructor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOM_METHOD__CONSTRUCTOR = eINSTANCE.getAOMMethod_Constructor();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl <em>AOM Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMScope()
		 * @generated
		 */
		EClass AOM_SCOPE = eINSTANCE.getAOMScope();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_SCOPE__VARIABLES = eINSTANCE.getAOMScope_Variables();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_SCOPE__OWNER = eINSTANCE.getAOMScope_Owner();

		/**
		 * The meta object literal for the '<em><b>Static Method Calls</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_SCOPE__STATIC_METHOD_CALLS = eINSTANCE.getAOMScope_StaticMethodCalls();

		/**
		 * The meta object literal for the '<em><b>Dynamic Method Calls</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_SCOPE__DYNAMIC_METHOD_CALLS = eINSTANCE.getAOMScope_DynamicMethodCalls();

		/**
		 * The meta object literal for the '<em><b>Static Field Accesses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_SCOPE__STATIC_FIELD_ACCESSES = eINSTANCE.getAOMScope_StaticFieldAccesses();

		/**
		 * The meta object literal for the '<em><b>Dynamic Field Accesses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_SCOPE__DYNAMIC_FIELD_ACCESSES = eINSTANCE.getAOMScope_DynamicFieldAccesses();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMParameterImpl <em>AOM Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMParameterImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMParameter()
		 * @generated
		 */
		EClass AOM_PARAMETER = eINSTANCE.getAOMParameter();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_PARAMETER__OWNER = eINSTANCE.getAOMParameter_Owner();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.AOMVariableDef <em>AOM Variable Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.AOMVariableDef
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMVariableDef()
		 * @generated
		 */
		EClass AOM_VARIABLE_DEF = eINSTANCE.getAOMVariableDef();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableImpl <em>AOM Local Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMLocalVariable()
		 * @generated
		 */
		EClass AOM_LOCAL_VARIABLE = eINSTANCE.getAOMLocalVariable();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOM_LOCAL_VARIABLE__OWNER = eINSTANCE.getAOMLocalVariable_Owner();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.impl.AOMExternalTypeImpl <em>AOM External Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.impl.AOMExternalTypeImpl
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMExternalType()
		 * @generated
		 */
		EClass AOM_EXTERNAL_TYPE = eINSTANCE.getAOMExternalType();

		/**
		 * The meta object literal for the '{@link kr.ac.kaist.se.aom.structure.AOMModifier <em>AOM Modifier</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kr.ac.kaist.se.aom.structure.AOMModifier
		 * @see kr.ac.kaist.se.aom.structure.impl.StructurePackageImpl#getAOMModifier()
		 * @generated
		 */
		EEnum AOM_MODIFIER = eINSTANCE.getAOMModifier();

	}

} //StructurePackage
