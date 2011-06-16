/**
 * <copyright>
 * </copyright>
 *
 * $Id: StructureSwitch.java,v 1.7 2011-01-14 11:36:22 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.util;

import java.util.List;

import kr.ac.kaist.se.aom.MeasurableElement;
import kr.ac.kaist.se.aom.structure.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.structure.StructurePackage
 * @generated
 */
public class StructureSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StructurePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureSwitch() {
		if (modelPackage == null) {
			modelPackage = StructurePackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case StructurePackage.AOM_ELEMENT: {
				AOMElement aomElement = (AOMElement)theEObject;
				T result = caseAOMElement(aomElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_NAMED_ELEMENT: {
				AOMNamedElement aomNamedElement = (AOMNamedElement)theEObject;
				T result = caseAOMNamedElement(aomNamedElement);
				if (result == null) result = caseAOMElement(aomNamedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_TYPED_ELEMENT: {
				AOMTypedElement aomTypedElement = (AOMTypedElement)theEObject;
				T result = caseAOMTypedElement(aomTypedElement);
				if (result == null) result = caseAOMElement(aomTypedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_TYPE: {
				AOMType aomType = (AOMType)theEObject;
				T result = caseAOMType(aomType);
				if (result == null) result = caseAOMNamedElement(aomType);
				if (result == null) result = caseAOMElement(aomType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_CLASS: {
				AOMClass aomClass = (AOMClass)theEObject;
				T result = caseAOMClass(aomClass);
				if (result == null) result = caseAOMType(aomClass);
				if (result == null) result = caseMeasurableElement(aomClass);
				if (result == null) result = caseAOMNamedElement(aomClass);
				if (result == null) result = caseAOMElement(aomClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_FIELD: {
				AOMField aomField = (AOMField)theEObject;
				T result = caseAOMField(aomField);
				if (result == null) result = caseAOMVariableDef(aomField);
				if (result == null) result = caseMeasurableElement(aomField);
				if (result == null) result = caseAOMTypedElement(aomField);
				if (result == null) result = caseAOMNamedElement(aomField);
				if (result == null) result = caseAOMElement(aomField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_METHOD: {
				AOMMethod aomMethod = (AOMMethod)theEObject;
				T result = caseAOMMethod(aomMethod);
				if (result == null) result = caseAOMNamedElement(aomMethod);
				if (result == null) result = caseAOMTypedElement(aomMethod);
				if (result == null) result = caseMeasurableElement(aomMethod);
				if (result == null) result = caseAOMElement(aomMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_SCOPE: {
				AOMScope aomScope = (AOMScope)theEObject;
				T result = caseAOMScope(aomScope);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_PARAMETER: {
				AOMParameter aomParameter = (AOMParameter)theEObject;
				T result = caseAOMParameter(aomParameter);
				if (result == null) result = caseAOMVariableDef(aomParameter);
				if (result == null) result = caseAOMTypedElement(aomParameter);
				if (result == null) result = caseAOMNamedElement(aomParameter);
				if (result == null) result = caseAOMElement(aomParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_VARIABLE_DEF: {
				AOMVariableDef aomVariableDef = (AOMVariableDef)theEObject;
				T result = caseAOMVariableDef(aomVariableDef);
				if (result == null) result = caseAOMTypedElement(aomVariableDef);
				if (result == null) result = caseAOMNamedElement(aomVariableDef);
				if (result == null) result = caseAOMElement(aomVariableDef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_LOCAL_VARIABLE: {
				AOMLocalVariable aomLocalVariable = (AOMLocalVariable)theEObject;
				T result = caseAOMLocalVariable(aomLocalVariable);
				if (result == null) result = caseAOMVariableDef(aomLocalVariable);
				if (result == null) result = caseAOMTypedElement(aomLocalVariable);
				if (result == null) result = caseAOMNamedElement(aomLocalVariable);
				if (result == null) result = caseAOMElement(aomLocalVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StructurePackage.AOM_EXTERNAL_TYPE: {
				AOMExternalType aomExternalType = (AOMExternalType)theEObject;
				T result = caseAOMExternalType(aomExternalType);
				if (result == null) result = caseAOMType(aomExternalType);
				if (result == null) result = caseAOMNamedElement(aomExternalType);
				if (result == null) result = caseAOMElement(aomExternalType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMElement(AOMElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMNamedElement(AOMNamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMTypedElement(AOMTypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMType(AOMType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMClass(AOMClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMField(AOMField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMMethod(AOMMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Scope</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Scope</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMScope(AOMScope object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMParameter(AOMParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Variable Def</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Variable Def</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMVariableDef(AOMVariableDef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM Local Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM Local Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMLocalVariable(AOMLocalVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOM External Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOM External Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOMExternalType(AOMExternalType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurableElement(MeasurableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //StructureSwitch
