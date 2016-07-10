/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMTypeImpl.java,v 1.4 2011-01-05 02:48:54 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.impl;

import java.util.Collection;

import kr.ac.kaist.se.aom.structure.AOMType;
import kr.ac.kaist.se.aom.structure.AOMTypedElement;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AOM Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMTypeImpl#getReferer <em>Referer</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMTypeImpl#getFqdn <em>Fqdn</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AOMTypeImpl extends AOMNamedElementImpl implements AOMType {
	/**
	 * The cached value of the '{@link #getReferer() <em>Referer</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferer()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMTypedElement> referer;

	/**
	 * The default value of the '{@link #getFqdn() <em>Fqdn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFqdn()
	 * @generated
	 * @ordered
	 */
	protected static final String FQDN_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getFqdn() <em>Fqdn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFqdn()
	 * @generated
	 * @ordered
	 */
	protected String fqdn = FQDN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AOMTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StructurePackage.Literals.AOM_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMTypedElement> getReferer() {
		if (referer == null) {
			referer = new EObjectWithInverseResolvingEList<AOMTypedElement>(AOMTypedElement.class, this, StructurePackage.AOM_TYPE__REFERER, StructurePackage.AOM_TYPED_ELEMENT__TYPE);
		}
		return referer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFqdn() {
		return fqdn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFqdn(String newFqdn) {
		String oldFqdn = fqdn;
		fqdn = newFqdn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_TYPE__FQDN, oldFqdn, fqdn));
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
			case StructurePackage.AOM_TYPE__REFERER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferer()).basicAdd(otherEnd, msgs);
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
			case StructurePackage.AOM_TYPE__REFERER:
				return ((InternalEList<?>)getReferer()).basicRemove(otherEnd, msgs);
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
			case StructurePackage.AOM_TYPE__REFERER:
				return getReferer();
			case StructurePackage.AOM_TYPE__FQDN:
				return getFqdn();
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
			case StructurePackage.AOM_TYPE__REFERER:
				getReferer().clear();
				getReferer().addAll((Collection<? extends AOMTypedElement>)newValue);
				return;
			case StructurePackage.AOM_TYPE__FQDN:
				setFqdn((String)newValue);
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
			case StructurePackage.AOM_TYPE__REFERER:
				getReferer().clear();
				return;
			case StructurePackage.AOM_TYPE__FQDN:
				setFqdn(FQDN_EDEFAULT);
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
			case StructurePackage.AOM_TYPE__REFERER:
				return referer != null && !referer.isEmpty();
			case StructurePackage.AOM_TYPE__FQDN:
				return FQDN_EDEFAULT == null ? fqdn != null : !FQDN_EDEFAULT.equals(fqdn);
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
		result.append(" (fqdn: ");
		result.append(fqdn);
		result.append(')');
		return result.toString();
	}

} //AOMTypeImpl
