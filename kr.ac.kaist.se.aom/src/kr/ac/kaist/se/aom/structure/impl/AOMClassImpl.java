/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMClassImpl.java,v 1.11 2011-01-18 07:51:59 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.impl;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import kr.ac.kaist.se.aom.AomPackage;
import kr.ac.kaist.se.aom.MeasurableElement;
import kr.ac.kaist.se.aom.impl.StringToObjectImpl;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMModifier;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.core.dom.ITypeBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AOM Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#getMeasuredDataSet <em>Measured Data Set</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#getDescendant <em>Descendant</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#isInterface <em>Interface</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#isInnerClass <em>Inner Class</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#isAnonymousClass <em>Anonymous Class</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#getModifier <em>Modifier</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#getRemainingLOC <em>Remaining LOC</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#getLOC <em>LOC</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMClassImpl#isStatic <em>Static</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AOMClassImpl extends AOMTypeImpl implements AOMClass {
	/**
	 * The cached value of the '{@link #getMeasuredDataSet() <em>Measured Data Set</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasuredDataSet()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Object> measuredDataSet;

	/**
	 * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMField> fields;

	/**
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMMethod> methods;

	/**
	 * The cached value of the '{@link #getAncestor() <em>Ancestor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAncestor()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMClass> ancestor;

	/**
	 * The cached value of the '{@link #getDescendant() <em>Descendant</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescendant()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMClass> descendant;

	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final int ABSTRACT_EFLAG = 1 << 8;

	/**
	 * The default value of the '{@link #isInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterface()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERFACE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterface()
	 * @generated
	 * @ordered
	 */
	protected static final int INTERFACE_EFLAG = 1 << 9;

	/**
	 * The default value of the '{@link #isInnerClass() <em>Inner Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInnerClass()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INNER_CLASS_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isInnerClass() <em>Inner Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInnerClass()
	 * @generated
	 * @ordered
	 */
	protected static final int INNER_CLASS_EFLAG = 1 << 10;

	/**
	 * The default value of the '{@link #isAnonymousClass() <em>Anonymous Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAnonymousClass()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ANONYMOUS_CLASS_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isAnonymousClass() <em>Anonymous Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAnonymousClass()
	 * @generated
	 * @ordered
	 */
	protected static final int ANONYMOUS_CLASS_EFLAG = 1 << 11;

	/**
	 * The default value of the '{@link #getModifier() <em>Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifier()
	 * @generated
	 * @ordered
	 */
	protected static final AOMModifier MODIFIER_EDEFAULT = AOMModifier.PUBLIC;

	/**
	 * The cached value of the '{@link #getModifier() <em>Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifier()
	 * @generated
	 * @ordered
	 */
	protected AOMModifier modifier = MODIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemainingLOC() <em>Remaining LOC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingLOC()
	 * @generated
	 * @ordered
	 */
	protected static final int REMAINING_LOC_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRemainingLOC() <em>Remaining LOC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingLOC()
	 * @generated
	 * @ordered
	 */
	protected int remainingLOC = REMAINING_LOC_EDEFAULT;

	/**
	 * The default value of the '{@link #getLOC() <em>LOC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLOC()
	 * @generated
	 * @ordered
	 */
	protected static final int LOC_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final int STATIC_EFLAG = 1 << 12;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AOMClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StructurePackage.Literals.AOM_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Object> getMeasuredDataSet() {
		if (measuredDataSet == null) {
			measuredDataSet = new EcoreEMap<String,Object>(AomPackage.Literals.STRING_TO_OBJECT, StringToObjectImpl.class, this, StructurePackage.AOM_CLASS__MEASURED_DATA_SET);
		}
		return measuredDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMField> getFields() {
		if (fields == null) {
			fields = new EObjectContainmentWithInverseEList<AOMField>(AOMField.class, this, StructurePackage.AOM_CLASS__FIELDS, StructurePackage.AOM_FIELD__OWNER);
		}
		return fields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMMethod> getMethods() {
		if (methods == null) {
			methods = new EObjectContainmentWithInverseEList<AOMMethod>(AOMMethod.class, this, StructurePackage.AOM_CLASS__METHODS, StructurePackage.AOM_METHOD__OWNER);
		}
		return methods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMClass> getAncestor() {
		if (ancestor == null) {
			ancestor = new EObjectWithInverseResolvingEList.ManyInverse<AOMClass>(AOMClass.class, this, StructurePackage.AOM_CLASS__ANCESTOR, StructurePackage.AOM_CLASS__DESCENDANT);
		}
		return ancestor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMClass> getDescendant() {
		if (descendant == null) {
			descendant = new EObjectWithInverseResolvingEList.ManyInverse<AOMClass>(AOMClass.class, this, StructurePackage.AOM_CLASS__DESCENDANT, StructurePackage.AOM_CLASS__ANCESTOR);
		}
		return descendant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return (eFlags & ABSTRACT_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = (eFlags & ABSTRACT_EFLAG) != 0;
		if (newAbstract) eFlags |= ABSTRACT_EFLAG; else eFlags &= ~ABSTRACT_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_CLASS__ABSTRACT, oldAbstract, newAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInterface() {
		return (eFlags & INTERFACE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(boolean newInterface) {
		boolean oldInterface = (eFlags & INTERFACE_EFLAG) != 0;
		if (newInterface) eFlags |= INTERFACE_EFLAG; else eFlags &= ~INTERFACE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_CLASS__INTERFACE, oldInterface, newInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInnerClass() {
		return (eFlags & INNER_CLASS_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInnerClass(boolean newInnerClass) {
		boolean oldInnerClass = (eFlags & INNER_CLASS_EFLAG) != 0;
		if (newInnerClass) eFlags |= INNER_CLASS_EFLAG; else eFlags &= ~INNER_CLASS_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_CLASS__INNER_CLASS, oldInnerClass, newInnerClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAnonymousClass() {
		return (eFlags & ANONYMOUS_CLASS_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnonymousClass(boolean newAnonymousClass) {
		boolean oldAnonymousClass = (eFlags & ANONYMOUS_CLASS_EFLAG) != 0;
		if (newAnonymousClass) eFlags |= ANONYMOUS_CLASS_EFLAG; else eFlags &= ~ANONYMOUS_CLASS_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_CLASS__ANONYMOUS_CLASS, oldAnonymousClass, newAnonymousClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMModifier getModifier() {
		return modifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifier(AOMModifier newModifier) {
		AOMModifier oldModifier = modifier;
		modifier = newModifier == null ? MODIFIER_EDEFAULT : newModifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_CLASS__MODIFIER, oldModifier, modifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRemainingLOC() {
		return remainingLOC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemainingLOC(int newRemainingLOC) {
		int oldRemainingLOC = remainingLOC;
		remainingLOC = newRemainingLOC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_CLASS__REMAINING_LOC, oldRemainingLOC, remainingLOC));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getLOC() {
		int ret = getRemainingLOC();
		for( AOMMethod method : getMethods() )
		{
			ret += method.getLOC();
		}
		return ret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setLOC(int newLOC) {
		int ret = newLOC;
		for( AOMMethod method : getMethods() )
		{
			ret -= method.getLOC();
		}
		setRemainingLOC(ret);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		return (eFlags & STATIC_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic) {
		boolean oldStatic = (eFlags & STATIC_EFLAG) != 0;
		if (newStatic) eFlags |= STATIC_EFLAG; else eFlags &= ~STATIC_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_CLASS__STATIC, oldStatic, newStatic));
	}

	private int tempLOC;
	
	public void setTempLOC(int newLOC)
	{
		tempLOC = newLOC;
	}
	
	public void setLOCfromTempLOC()
	{
		setLOC(tempLOC);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StructurePackage.AOM_CLASS__FIELDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFields()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_CLASS__METHODS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMethods()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_CLASS__ANCESTOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAncestor()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_CLASS__DESCENDANT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDescendant()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StructurePackage.AOM_CLASS__MEASURED_DATA_SET:
				return ((InternalEList<?>)getMeasuredDataSet()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_CLASS__FIELDS:
				return ((InternalEList<?>)getFields()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_CLASS__METHODS:
				return ((InternalEList<?>)getMethods()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_CLASS__ANCESTOR:
				return ((InternalEList<?>)getAncestor()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_CLASS__DESCENDANT:
				return ((InternalEList<?>)getDescendant()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StructurePackage.AOM_CLASS__MEASURED_DATA_SET:
				if (coreType) return getMeasuredDataSet();
				else return getMeasuredDataSet().map();
			case StructurePackage.AOM_CLASS__FIELDS:
				return getFields();
			case StructurePackage.AOM_CLASS__METHODS:
				return getMethods();
			case StructurePackage.AOM_CLASS__ANCESTOR:
				return getAncestor();
			case StructurePackage.AOM_CLASS__DESCENDANT:
				return getDescendant();
			case StructurePackage.AOM_CLASS__ABSTRACT:
				return isAbstract();
			case StructurePackage.AOM_CLASS__INTERFACE:
				return isInterface();
			case StructurePackage.AOM_CLASS__INNER_CLASS:
				return isInnerClass();
			case StructurePackage.AOM_CLASS__ANONYMOUS_CLASS:
				return isAnonymousClass();
			case StructurePackage.AOM_CLASS__MODIFIER:
				return getModifier();
			case StructurePackage.AOM_CLASS__REMAINING_LOC:
				return getRemainingLOC();
			case StructurePackage.AOM_CLASS__LOC:
				return getLOC();
			case StructurePackage.AOM_CLASS__STATIC:
				return isStatic();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StructurePackage.AOM_CLASS__MEASURED_DATA_SET:
				((EStructuralFeature.Setting)getMeasuredDataSet()).set(newValue);
				return;
			case StructurePackage.AOM_CLASS__FIELDS:
				getFields().clear();
				getFields().addAll((Collection<? extends AOMField>)newValue);
				return;
			case StructurePackage.AOM_CLASS__METHODS:
				getMethods().clear();
				getMethods().addAll((Collection<? extends AOMMethod>)newValue);
				return;
			case StructurePackage.AOM_CLASS__ANCESTOR:
				getAncestor().clear();
				getAncestor().addAll((Collection<? extends AOMClass>)newValue);
				return;
			case StructurePackage.AOM_CLASS__DESCENDANT:
				getDescendant().clear();
				getDescendant().addAll((Collection<? extends AOMClass>)newValue);
				return;
			case StructurePackage.AOM_CLASS__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case StructurePackage.AOM_CLASS__INTERFACE:
				setInterface((Boolean)newValue);
				return;
			case StructurePackage.AOM_CLASS__INNER_CLASS:
				setInnerClass((Boolean)newValue);
				return;
			case StructurePackage.AOM_CLASS__ANONYMOUS_CLASS:
				setAnonymousClass((Boolean)newValue);
				return;
			case StructurePackage.AOM_CLASS__MODIFIER:
				setModifier((AOMModifier)newValue);
				return;
			case StructurePackage.AOM_CLASS__REMAINING_LOC:
				setRemainingLOC((Integer)newValue);
				return;
			case StructurePackage.AOM_CLASS__LOC:
				setLOC((Integer)newValue);
				return;
			case StructurePackage.AOM_CLASS__STATIC:
				setStatic((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StructurePackage.AOM_CLASS__MEASURED_DATA_SET:
				getMeasuredDataSet().clear();
				return;
			case StructurePackage.AOM_CLASS__FIELDS:
				getFields().clear();
				return;
			case StructurePackage.AOM_CLASS__METHODS:
				getMethods().clear();
				return;
			case StructurePackage.AOM_CLASS__ANCESTOR:
				getAncestor().clear();
				return;
			case StructurePackage.AOM_CLASS__DESCENDANT:
				getDescendant().clear();
				return;
			case StructurePackage.AOM_CLASS__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case StructurePackage.AOM_CLASS__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case StructurePackage.AOM_CLASS__INNER_CLASS:
				setInnerClass(INNER_CLASS_EDEFAULT);
				return;
			case StructurePackage.AOM_CLASS__ANONYMOUS_CLASS:
				setAnonymousClass(ANONYMOUS_CLASS_EDEFAULT);
				return;
			case StructurePackage.AOM_CLASS__MODIFIER:
				setModifier(MODIFIER_EDEFAULT);
				return;
			case StructurePackage.AOM_CLASS__REMAINING_LOC:
				setRemainingLOC(REMAINING_LOC_EDEFAULT);
				return;
			case StructurePackage.AOM_CLASS__LOC:
				setLOC(LOC_EDEFAULT);
				return;
			case StructurePackage.AOM_CLASS__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StructurePackage.AOM_CLASS__MEASURED_DATA_SET:
				return measuredDataSet != null && !measuredDataSet.isEmpty();
			case StructurePackage.AOM_CLASS__FIELDS:
				return fields != null && !fields.isEmpty();
			case StructurePackage.AOM_CLASS__METHODS:
				return methods != null && !methods.isEmpty();
			case StructurePackage.AOM_CLASS__ANCESTOR:
				return ancestor != null && !ancestor.isEmpty();
			case StructurePackage.AOM_CLASS__DESCENDANT:
				return descendant != null && !descendant.isEmpty();
			case StructurePackage.AOM_CLASS__ABSTRACT:
				return ((eFlags & ABSTRACT_EFLAG) != 0) != ABSTRACT_EDEFAULT;
			case StructurePackage.AOM_CLASS__INTERFACE:
				return ((eFlags & INTERFACE_EFLAG) != 0) != INTERFACE_EDEFAULT;
			case StructurePackage.AOM_CLASS__INNER_CLASS:
				return ((eFlags & INNER_CLASS_EFLAG) != 0) != INNER_CLASS_EDEFAULT;
			case StructurePackage.AOM_CLASS__ANONYMOUS_CLASS:
				return ((eFlags & ANONYMOUS_CLASS_EFLAG) != 0) != ANONYMOUS_CLASS_EDEFAULT;
			case StructurePackage.AOM_CLASS__MODIFIER:
				return modifier != MODIFIER_EDEFAULT;
			case StructurePackage.AOM_CLASS__REMAINING_LOC:
				return remainingLOC != REMAINING_LOC_EDEFAULT;
			case StructurePackage.AOM_CLASS__LOC:
				return getLOC() != LOC_EDEFAULT;
			case StructurePackage.AOM_CLASS__STATIC:
				return ((eFlags & STATIC_EFLAG) != 0) != STATIC_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == MeasurableElement.class) {
			switch (derivedFeatureID) {
				case StructurePackage.AOM_CLASS__MEASURED_DATA_SET: return AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == MeasurableElement.class) {
			switch (baseFeatureID) {
				case AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET: return StructurePackage.AOM_CLASS__MEASURED_DATA_SET;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (abstract: ");
		result.append((eFlags & ABSTRACT_EFLAG) != 0);
		result.append(", interface: ");
		result.append((eFlags & INTERFACE_EFLAG) != 0);
		result.append(", innerClass: ");
		result.append((eFlags & INNER_CLASS_EFLAG) != 0);
		result.append(", anonymousClass: ");
		result.append((eFlags & ANONYMOUS_CLASS_EFLAG) != 0);
		result.append(", modifier: ");
		result.append(modifier);
		result.append(", remainingLOC: ");
		result.append(remainingLOC);
		result.append(", static: ");
		result.append((eFlags & STATIC_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

	private List<ITypeBinding> ancestors = new Vector<ITypeBinding>();
	
	public List<ITypeBinding> getAncestorTypeBindings()
	{	
		return ancestors;
	}

} //AOMClassImpl
