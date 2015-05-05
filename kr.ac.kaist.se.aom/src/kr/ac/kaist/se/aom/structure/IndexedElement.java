/**
 */
package kr.ac.kaist.se.aom.structure;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indexed Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.IndexedElement#getIndex <em>Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getIndexedElement()
 * @model abstract="true"
 * @generated
 */
public interface IndexedElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see #setIndex(int)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getIndexedElement_Index()
	 * @model volatile="true"
	 * @generated
	 */
	int getIndex();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.IndexedElement#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(int value);

} // IndexedElement
