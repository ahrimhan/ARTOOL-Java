/**
 * <copyright>
 * </copyright>
 *
 * $Id: MeasurableElement.java,v 1.4 2011-02-07 08:36:33 igsong Exp $
 */
package kr.ac.kaist.se.aom;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.MeasurableElement#getMeasuredDataSet <em>Measured Data Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.AomPackage#getMeasurableElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface MeasurableElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Measured Data Set</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.Object},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measured Data Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measured Data Set</em>' map.
	 * @see kr.ac.kaist.se.aom.AomPackage#getMeasurableElement_MeasuredDataSet()
	 * @model mapType="kr.ac.kaist.se.aom.StringToObject<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EJavaObject>"
	 * @generated
	 */
	EMap<String, Object> getMeasuredDataSet();

} // MeasurableElement
