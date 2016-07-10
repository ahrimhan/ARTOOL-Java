/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kr.ac.kaist.se.aom.structure.impl;

import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AOM Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMEntityImpl#getOccurrence <em>Occurrence</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMEntityImpl#isPublicEntity <em>Public Entity</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AOMEntityImpl extends IndexedElementImpl implements AOMEntity {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AOMEntityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StructurePackage.Literals.AOM_ENTITY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_ENTITY__OCCURRENCE, oldOccurrence, occurrence));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_ENTITY__PUBLIC_ENTITY, oldPublicEntity, newPublicEntity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StructurePackage.AOM_ENTITY__OCCURRENCE:
				return getOccurrence();
			case StructurePackage.AOM_ENTITY__PUBLIC_ENTITY:
				return isPublicEntity();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StructurePackage.AOM_ENTITY__OCCURRENCE:
				setOccurrence((Integer)newValue);
				return;
			case StructurePackage.AOM_ENTITY__PUBLIC_ENTITY:
				setPublicEntity((Boolean)newValue);
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
			case StructurePackage.AOM_ENTITY__OCCURRENCE:
				setOccurrence(OCCURRENCE_EDEFAULT);
				return;
			case StructurePackage.AOM_ENTITY__PUBLIC_ENTITY:
				setPublicEntity(PUBLIC_ENTITY_EDEFAULT);
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
			case StructurePackage.AOM_ENTITY__OCCURRENCE:
				return occurrence != OCCURRENCE_EDEFAULT;
			case StructurePackage.AOM_ENTITY__PUBLIC_ENTITY:
				return ((eFlags & PUBLIC_ENTITY_EFLAG) != 0) != PUBLIC_ENTITY_EDEFAULT;
		}
		return super.eIsSet(featureID);
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
		result.append(" (occurrence: ");
		result.append(occurrence);
		result.append(", publicEntity: ");
		result.append((eFlags & PUBLIC_ENTITY_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

} //AOMEntityImpl
