/**
 * <copyright>
 * </copyright>
 *
 * $Id: StructureAdapterFactory.java,v 1.6 2011-01-13 09:48:25 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.util;

import kr.ac.kaist.se.aom.MeasurableElement;
import kr.ac.kaist.se.aom.structure.*;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMElement;
import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.AOMExternalType;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMLocalVariable;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMNamedElement;
import kr.ac.kaist.se.aom.structure.AOMParameter;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.AOMType;
import kr.ac.kaist.se.aom.structure.AOMTypedElement;
import kr.ac.kaist.se.aom.structure.AOMVariableDef;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.structure.StructurePackage
 * @generated
 */
public class StructureAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StructurePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StructurePackage.eINSTANCE;
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
	protected StructureSwitch<Adapter> modelSwitch =
		new StructureSwitch<Adapter>() {
			@Override
			public Adapter caseAOMElement(AOMElement object) {
				return createAOMElementAdapter();
			}
			@Override
			public Adapter caseAOMNamedElement(AOMNamedElement object) {
				return createAOMNamedElementAdapter();
			}
			@Override
			public Adapter caseAOMTypedElement(AOMTypedElement object) {
				return createAOMTypedElementAdapter();
			}
			@Override
			public Adapter caseAOMType(AOMType object) {
				return createAOMTypeAdapter();
			}
			@Override
			public Adapter caseAOMClass(AOMClass object) {
				return createAOMClassAdapter();
			}
			@Override
			public Adapter caseAOMField(AOMField object) {
				return createAOMFieldAdapter();
			}
			@Override
			public Adapter caseAOMMethod(AOMMethod object) {
				return createAOMMethodAdapter();
			}
			@Override
			public Adapter caseAOMScope(AOMScope object) {
				return createAOMScopeAdapter();
			}
			@Override
			public Adapter caseAOMParameter(AOMParameter object) {
				return createAOMParameterAdapter();
			}
			@Override
			public Adapter caseAOMVariableDef(AOMVariableDef object) {
				return createAOMVariableDefAdapter();
			}
			@Override
			public Adapter caseAOMLocalVariable(AOMLocalVariable object) {
				return createAOMLocalVariableAdapter();
			}
			@Override
			public Adapter caseAOMExternalType(AOMExternalType object) {
				return createAOMExternalTypeAdapter();
			}
			@Override
			public Adapter caseAOMEntity(AOMEntity object) {
				return createAOMEntityAdapter();
			}
			@Override
			public Adapter caseIndexedElement(IndexedElement object) {
				return createIndexedElementAdapter();
			}
			@Override
			public Adapter caseAOMLocalVariableAccess(AOMLocalVariableAccess object) {
				return createAOMLocalVariableAccessAdapter();
			}
			@Override
			public Adapter caseMeasurableElement(MeasurableElement object) {
				return createMeasurableElementAdapter();
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
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMElement <em>AOM Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMElement
	 * @generated
	 */
	public Adapter createAOMElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMNamedElement <em>AOM Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMNamedElement
	 * @generated
	 */
	public Adapter createAOMNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMTypedElement <em>AOM Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMTypedElement
	 * @generated
	 */
	public Adapter createAOMTypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMType <em>AOM Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMType
	 * @generated
	 */
	public Adapter createAOMTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMClass <em>AOM Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMClass
	 * @generated
	 */
	public Adapter createAOMClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMField <em>AOM Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMField
	 * @generated
	 */
	public Adapter createAOMFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMMethod <em>AOM Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod
	 * @generated
	 */
	public Adapter createAOMMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMScope <em>AOM Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMScope
	 * @generated
	 */
	public Adapter createAOMScopeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMParameter <em>AOM Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMParameter
	 * @generated
	 */
	public Adapter createAOMParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMVariableDef <em>AOM Variable Def</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMVariableDef
	 * @generated
	 */
	public Adapter createAOMVariableDefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariable <em>AOM Local Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMLocalVariable
	 * @generated
	 */
	public Adapter createAOMLocalVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMExternalType <em>AOM External Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMExternalType
	 * @generated
	 */
	public Adapter createAOMExternalTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMEntity <em>AOM Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMEntity
	 * @generated
	 */
	public Adapter createAOMEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.IndexedElement <em>Indexed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.IndexedElement
	 * @generated
	 */
	public Adapter createIndexedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess <em>AOM Local Variable Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess
	 * @generated
	 */
	public Adapter createAOMLocalVariableAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kr.ac.kaist.se.aom.MeasurableElement <em>Measurable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kr.ac.kaist.se.aom.MeasurableElement
	 * @generated
	 */
	public Adapter createMeasurableElementAdapter() {
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

} //StructureAdapterFactory
