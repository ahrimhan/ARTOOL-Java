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
import kr.ac.kaist.se.aom.impl.StringToObjectImpl;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMNamedElement;
import kr.ac.kaist.se.aom.structure.IndexedElement;
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
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getOccurrence <em>Occurrence</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#isPublicEntity <em>Public Entity</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMFieldImpl#getStaticReferer <em>Static Referer</em>}</li>
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
	 * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int INDEX_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getOccurrence() <em>Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccurrence()
	 * @generated
	 * @ordered
	 */
	protected static final int OCCURRENCE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOccurrence() <em>Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccurrence()
	 * @generated
	 * @ordered
	 */
	protected int occurrence = OCCURRENCE_EDEFAULT;

	/**
	 * The default value of the '{@link #isPublicEntity() <em>Public Entity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPublicEntity()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUBLIC_ENTITY_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isPublicEntity() <em>Public Entity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPublicEntity()
	 * @generated
	 * @ordered
	 */
	protected static final int PUBLIC_ENTITY_EFLAG = 1 << 8;

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
	public int getOccurrence() {
		return occurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOccurrence(int newOccurrence) {
		int oldOccurrence = occurrence;
		occurrence = newOccurrence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_FIELD__OCCURRENCE, oldOccurrence, occurrence));
	}

	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPublicEntity() {
		return (eFlags & PUBLIC_ENTITY_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPublicEntity(boolean newPublicEntity) {
		boolean oldPublicEntity = (eFlags & PUBLIC_ENTITY_EFLAG) != 0;
		if (newPublicEntity) eFlags |= PUBLIC_ENTITY_EFLAG; else eFlags &= ~PUBLIC_ENTITY_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_FIELD__PUBLIC_ENTITY, oldPublicEntity, newPublicEntity));
	}


	private int index = -1;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getIndex() {
		// TODO: implement this method to return the 'Index' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		return index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setIndex(int newIndex) {
		// TODO: implement this method to set the 'Index' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		this.index = newIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMClass getOwner() {
		if (eContainerFeatureID() != StructurePackage.AOM_FIELD__OWNER) return null;
		return (AOMClass)eInternalContainer();
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
			case StructurePackage.AOM_FIELD__INDEX:
				return getIndex();
			case StructurePackage.AOM_FIELD__OCCURRENCE:
				return getOccurrence();
			case StructurePackage.AOM_FIELD__PUBLIC_ENTITY:
				return isPublicEntity();
			case StructurePackage.AOM_FIELD__OWNER:
				return getOwner();
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				return getStaticReferer();
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
			case StructurePackage.AOM_FIELD__INDEX:
				setIndex((Integer)newValue);
				return;
			case StructurePackage.AOM_FIELD__OCCURRENCE:
				setOccurrence((Integer)newValue);
				return;
			case StructurePackage.AOM_FIELD__PUBLIC_ENTITY:
				setPublicEntity((Boolean)newValue);
				return;
			case StructurePackage.AOM_FIELD__OWNER:
				setOwner((AOMClass)newValue);
				return;
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				getStaticReferer().clear();
				getStaticReferer().addAll((Collection<? extends StaticFieldAccess>)newValue);
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
			case StructurePackage.AOM_FIELD__INDEX:
				setIndex(INDEX_EDEFAULT);
				return;
			case StructurePackage.AOM_FIELD__OCCURRENCE:
				setOccurrence(OCCURRENCE_EDEFAULT);
				return;
			case StructurePackage.AOM_FIELD__PUBLIC_ENTITY:
				setPublicEntity(PUBLIC_ENTITY_EDEFAULT);
				return;
			case StructurePackage.AOM_FIELD__OWNER:
				setOwner((AOMClass)null);
				return;
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				getStaticReferer().clear();
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
			case StructurePackage.AOM_FIELD__INDEX:
				return getIndex() != INDEX_EDEFAULT;
			case StructurePackage.AOM_FIELD__OCCURRENCE:
				return occurrence != OCCURRENCE_EDEFAULT;
			case StructurePackage.AOM_FIELD__PUBLIC_ENTITY:
				return ((eFlags & PUBLIC_ENTITY_EFLAG) != 0) != PUBLIC_ENTITY_EDEFAULT;
			case StructurePackage.AOM_FIELD__OWNER:
				return getOwner() != null;
			case StructurePackage.AOM_FIELD__STATIC_REFERER:
				return staticReferer != null && !staticReferer.isEmpty();
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
		if (baseClass == IndexedElement.class) {
			switch (derivedFeatureID) {
				case StructurePackage.AOM_FIELD__INDEX: return StructurePackage.INDEXED_ELEMENT__INDEX;
				default: return -1;
			}
		}
		if (baseClass == AOMEntity.class) {
			switch (derivedFeatureID) {
				case StructurePackage.AOM_FIELD__OCCURRENCE: return StructurePackage.AOM_ENTITY__OCCURRENCE;
				case StructurePackage.AOM_FIELD__PUBLIC_ENTITY: return StructurePackage.AOM_ENTITY__PUBLIC_ENTITY;
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
		if (baseClass == IndexedElement.class) {
			switch (baseFeatureID) {
				case StructurePackage.INDEXED_ELEMENT__INDEX: return StructurePackage.AOM_FIELD__INDEX;
				default: return -1;
			}
		}
		if (baseClass == AOMEntity.class) {
			switch (baseFeatureID) {
				case StructurePackage.AOM_ENTITY__OCCURRENCE: return StructurePackage.AOM_FIELD__OCCURRENCE;
				case StructurePackage.AOM_ENTITY__PUBLIC_ENTITY: return StructurePackage.AOM_FIELD__PUBLIC_ENTITY;
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
		result.append(", occurrence: ");
		result.append(occurrence);
		result.append(", publicEntity: ");
		result.append((eFlags & PUBLIC_ENTITY_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

} //AOMFieldImpl
