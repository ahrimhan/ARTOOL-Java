/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMClass.java,v 1.9 2011-01-13 09:48:25 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure;

import java.util.List;

import kr.ac.kaist.se.aom.MeasurableElement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ITypeBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AOM Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#getFields <em>Fields</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#getMethods <em>Methods</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#getDescendant <em>Descendant</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#isInterface <em>Interface</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#isInnerClass <em>Inner Class</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#isAnonymousClass <em>Anonymous Class</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#getModifier <em>Modifier</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#getRemainingLOC <em>Remaining LOC</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#getLOC <em>LOC</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.AOMClass#isStatic <em>Static</em>}</li>
 * </ul>
 * </p>
 *
 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass()
 * @model
 * @generated
 */
public interface AOMClass extends AOMType, MeasurableElement {
	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMField}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMField#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_Fields()
	 * @see kr.ac.kaist.se.aom.structure.AOMField#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<AOMField> getFields();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMMethod}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMMethod#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_Methods()
	 * @see kr.ac.kaist.se.aom.structure.AOMMethod#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<AOMMethod> getMethods();

	/**
	 * Returns the value of the '<em><b>Ancestor</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMClass}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMClass#getDescendant <em>Descendant</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ancestor</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ancestor</em>' reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_Ancestor()
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getDescendant
	 * @model opposite="descendant"
	 * @generated
	 */
	EList<AOMClass> getAncestor();

	/**
	 * Returns the value of the '<em><b>Descendant</b></em>' reference list.
	 * The list contents are of type {@link kr.ac.kaist.se.aom.structure.AOMClass}.
	 * It is bidirectional and its opposite is '{@link kr.ac.kaist.se.aom.structure.AOMClass#getAncestor <em>Ancestor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descendant</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descendant</em>' reference list.
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_Descendant()
	 * @see kr.ac.kaist.se.aom.structure.AOMClass#getAncestor
	 * @model opposite="ancestor"
	 * @generated
	 */
	EList<AOMClass> getDescendant();

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_Abstract()
	 * @model
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMClass#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' attribute.
	 * @see #setInterface(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_Interface()
	 * @model
	 * @generated
	 */
	boolean isInterface();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMClass#isInterface <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' attribute.
	 * @see #isInterface()
	 * @generated
	 */
	void setInterface(boolean value);

	/**
	 * Returns the value of the '<em><b>Inner Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inner Class</em>' attribute.
	 * @see #setInnerClass(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_InnerClass()
	 * @model
	 * @generated
	 */
	boolean isInnerClass();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMClass#isInnerClass <em>Inner Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inner Class</em>' attribute.
	 * @see #isInnerClass()
	 * @generated
	 */
	void setInnerClass(boolean value);

	/**
	 * Returns the value of the '<em><b>Anonymous Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anonymous Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anonymous Class</em>' attribute.
	 * @see #setAnonymousClass(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_AnonymousClass()
	 * @model
	 * @generated
	 */
	boolean isAnonymousClass();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMClass#isAnonymousClass <em>Anonymous Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anonymous Class</em>' attribute.
	 * @see #isAnonymousClass()
	 * @generated
	 */
	void setAnonymousClass(boolean value);

	/**
	 * Returns the value of the '<em><b>Modifier</b></em>' attribute.
	 * The literals are from the enumeration {@link kr.ac.kaist.se.aom.structure.AOMModifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifier</em>' attribute.
	 * @see kr.ac.kaist.se.aom.structure.AOMModifier
	 * @see #setModifier(AOMModifier)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_Modifier()
	 * @model
	 * @generated
	 */
	AOMModifier getModifier();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMClass#getModifier <em>Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modifier</em>' attribute.
	 * @see kr.ac.kaist.se.aom.structure.AOMModifier
	 * @see #getModifier()
	 * @generated
	 */
	void setModifier(AOMModifier value);

	/**
	 * Returns the value of the '<em><b>Remaining LOC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remaining LOC</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remaining LOC</em>' attribute.
	 * @see #setRemainingLOC(int)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_RemainingLOC()
	 * @model
	 * @generated
	 */
	int getRemainingLOC();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMClass#getRemainingLOC <em>Remaining LOC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining LOC</em>' attribute.
	 * @see #getRemainingLOC()
	 * @generated
	 */
	void setRemainingLOC(int value);

	/**
	 * Returns the value of the '<em><b>LOC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LOC</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LOC</em>' attribute.
	 * @see #setLOC(int)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_LOC()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	int getLOC();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMClass#getLOC <em>LOC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LOC</em>' attribute.
	 * @see #getLOC()
	 * @generated
	 */
	void setLOC(int value);
	
	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see kr.ac.kaist.se.aom.structure.StructurePackage#getAOMClass_Static()
	 * @model
	 * @generated
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link kr.ac.kaist.se.aom.structure.AOMClass#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

	void setTempLOC(int newLOC);
	
	void setLOCfromTempLOC();

	List<ITypeBinding> getAncestorTypeBindings();
	
} // AOMClass
