/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMFieldImpl.java,v 1.4 2011-01-14 11:36:22 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.impl;

import java.util.Collection;

import kr.ac.kaist.se.aom.AomPackage;
import kr.ac.kaist.se.aom.MeasurableElement;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;
import kr.ac.kaist.se.aom.impl.StringToObjectImpl;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMNamedElement;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AOM Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getName <em>Name</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getMeasuredDataSet <em>Measured Data Set</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getStaticReferer <em>Static Referer</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getDynamicReferer <em>Dynamic Referer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AOMFieldImpl extends AOMTypedElementImpl implements AOMField {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getStaticReferer() <em>Static Referer</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticReferer()
	 * @generated
	 * @ordered
	 */
	protected EList<StaticFieldAccess> staticReferer;

	/**
	 * The cached value of the '{@link #getDynamicReferer() <em>Dynamic Referer</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicReferer()
	 * @generated
	 * @ordered
	 */
	protected EList<DynamicFieldAccess> dynamicReferer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AOMFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StructurePackage.Literals.AOM_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_FIELD__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Object> getMeasuredDataSet() {
		if (measuredDataSet == null) {
			measuredDataSet = new EcoreEMap<String,Object>(AomPackage.Literals.STRING_TO_OBJECT, StringToObjectImpl.class, this, StructurePackage.AOM_FIELD__MEASURED_DATA_SET);
		}
		return measuredDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMClass getOwner() {
		if (eContainerFeatureID() != StructurePackage.AOM_FIELD__OWNER) return null;
		return (AOMClass)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(AOMClass newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, StructurePackage.AOM_FIELD__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(AOMClass newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != StructurePackage.AOM_FIELD__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, StructurePackage.AOM_CLASS__FIELDS, AOMClass.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_FIELD__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StaticFieldAccess> getStaticReferer() {
		if (staticReferer == null) {
			staticReferer = new EObjectWithInverseResolvingEList<StaticFieldAccess>(StaticFieldAccess.class, this, StructurePackage.AOM_FIELD__STATIC_REFERER, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD);
		}
		return staticReferer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicFieldAccess> getDynamicReferer() {
		if (dynamicReferer == null) {
			dynamicReferer = new EObjectWithInverseResolvingEList<DynamicFieldAccess>(DynamicFieldAccess.class, this, StructurePackage.AOM_FIELD__DYNAMIC_REFERER, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD);
		}
		return dynamicReferer;
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
			case StructurePackage.AOM_FIELD__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((AOMClass)otherEnd, msgs);
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStaticReferer()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_FIELD__DYNAMIC_REFERER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDynamicReferer()).basicAdd(otherEnd, msgs);
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
			case StructurePackage.AOM_FIELD__MEASURED_DATA_SET:
				return ((InternalEList<?>)getMeasuredDataSet()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_FIELD__OWNER:
				return basicSetOwner(null, msgs);
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				return ((InternalEList<?>)getStaticReferer()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_FIELD__DYNAMIC_REFERER:
				return ((InternalEList<?>)getDynamicReferer()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case StructurePackage.AOM_FIELD__OWNER:
				return eInternalContainer().eInverseRemove(this, StructurePackage.AOM_CLASS__FIELDS, AOMClass.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StructurePackage.AOM_FIELD__NAME:
				return getName();
			case StructurePackage.AOM_FIELD__MEASURED_DATA_SET:
				if (coreType) return getMeasuredDataSet();
				else return getMeasuredDataSet().map();
			case StructurePackage.AOM_FIELD__OWNER:
				return getOwner();
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				return getStaticReferer();
			case StructurePackage.AOM_FIELD__DYNAMIC_REFERER:
				return getDynamicReferer();
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
			case StructurePackage.AOM_FIELD__NAME:
				setName((String)newValue);
				return;
			case StructurePackage.AOM_FIELD__MEASURED_DATA_SET:
				((EStructuralFeature.Setting)getMeasuredDataSet()).set(newValue);
				return;
			case StructurePackage.AOM_FIELD__OWNER:
				setOwner((AOMClass)newValue);
				return;
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				getStaticReferer().clear();
				getStaticReferer().addAll((Collection<? extends StaticFieldAccess>)newValue);
				return;
			case StructurePackage.AOM_FIELD__DYNAMIC_REFERER:
				getDynamicReferer().clear();
				getDynamicReferer().addAll((Collection<? extends DynamicFieldAccess>)newValue);
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
			case StructurePackage.AOM_FIELD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case StructurePackage.AOM_FIELD__MEASURED_DATA_SET:
				getMeasuredDataSet().clear();
				return;
			case StructurePackage.AOM_FIELD__OWNER:
				setOwner((AOMClass)null);
				return;
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				getStaticReferer().clear();
				return;
			case StructurePackage.AOM_FIELD__DYNAMIC_REFERER:
				getDynamicReferer().clear();
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
			case StructurePackage.AOM_FIELD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case StructurePackage.AOM_FIELD__MEASURED_DATA_SET:
				return measuredDataSet != null && !measuredDataSet.isEmpty();
			case StructurePackage.AOM_FIELD__OWNER:
				return getOwner() != null;
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				return staticReferer != null && !staticReferer.isEmpty();
			case StructurePackage.AOM_FIELD__DYNAMIC_REFERER:
				return dynamicReferer != null && !dynamicReferer.isEmpty();
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
		if (baseClass == AOMNamedElement.class) {
			switch (derivedFeatureID) {
				case StructurePackage.AOM_FIELD__NAME: return StructurePackage.AOM_NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == MeasurableElement.class) {
			switch (derivedFeatureID) {
				case StructurePackage.AOM_FIELD__MEASURED_DATA_SET: return AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET;
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
		if (baseClass == AOMNamedElement.class) {
			switch (baseFeatureID) {
				case StructurePackage.AOM_NAMED_ELEMENT__NAME: return StructurePackage.AOM_FIELD__NAME;
				default: return -1;
			}
		}
		if (baseClass == MeasurableElement.class) {
			switch (baseFeatureID) {
				case AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET: return StructurePackage.AOM_FIELD__MEASURED_DATA_SET;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //AOMFieldImpl
