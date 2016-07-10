/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMLocalVariableImpl.java,v 1.3 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.impl;

import java.util.Collection;
import kr.ac.kaist.se.aom.structure.AOMLocalVariable;
import kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess;
import kr.ac.kaist.se.aom.structure.AOMNamedElement;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AOM Local Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableImpl#getName <em>Name</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableImpl#getReferer <em>Referer</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableImpl#getOwner <em>Owner</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AOMLocalVariableImpl extends AOMTypedElementImpl implements AOMLocalVariable {
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
	 * The cached value of the '{@link #getReferer() <em>Referer</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferer()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMLocalVariableAccess> referer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AOMLocalVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StructurePackage.Literals.AOM_LOCAL_VARIABLE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_LOCAL_VARIABLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMLocalVariableAccess> getReferer() {
		if (referer == null) {
			referer = new EObjectWithInverseResolvingEList<AOMLocalVariableAccess>(AOMLocalVariableAccess.class, this, StructurePackage.AOM_LOCAL_VARIABLE__REFERER, StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF);
		}
		return referer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMScope getOwner() {
		if (eContainerFeatureID() != StructurePackage.AOM_LOCAL_VARIABLE__OWNER) return null;
		return (AOMScope)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(AOMScope newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, StructurePackage.AOM_LOCAL_VARIABLE__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(AOMScope newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != StructurePackage.AOM_LOCAL_VARIABLE__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, StructurePackage.AOM_SCOPE__VARIABLES, AOMScope.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_LOCAL_VARIABLE__OWNER, newOwner, newOwner));
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
			case StructurePackage.AOM_LOCAL_VARIABLE__REFERER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferer()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_LOCAL_VARIABLE__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((AOMScope)otherEnd, msgs);
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
			case StructurePackage.AOM_LOCAL_VARIABLE__REFERER:
				return ((InternalEList<?>)getReferer()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_LOCAL_VARIABLE__OWNER:
				return basicSetOwner(null, msgs);
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
			case StructurePackage.AOM_LOCAL_VARIABLE__OWNER:
				return eInternalContainer().eInverseRemove(this, StructurePackage.AOM_SCOPE__VARIABLES, AOMScope.class, msgs);
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
			case StructurePackage.AOM_LOCAL_VARIABLE__NAME:
				return getName();
			case StructurePackage.AOM_LOCAL_VARIABLE__REFERER:
				return getReferer();
			case StructurePackage.AOM_LOCAL_VARIABLE__OWNER:
				return getOwner();
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
			case StructurePackage.AOM_LOCAL_VARIABLE__NAME:
				setName((String)newValue);
				return;
			case StructurePackage.AOM_LOCAL_VARIABLE__REFERER:
				getReferer().clear();
				getReferer().addAll((Collection<? extends AOMLocalVariableAccess>)newValue);
				return;
			case StructurePackage.AOM_LOCAL_VARIABLE__OWNER:
				setOwner((AOMScope)newValue);
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
			case StructurePackage.AOM_LOCAL_VARIABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case StructurePackage.AOM_LOCAL_VARIABLE__REFERER:
				getReferer().clear();
				return;
			case StructurePackage.AOM_LOCAL_VARIABLE__OWNER:
				setOwner((AOMScope)null);
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
			case StructurePackage.AOM_LOCAL_VARIABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case StructurePackage.AOM_LOCAL_VARIABLE__REFERER:
				return referer != null && !referer.isEmpty();
			case StructurePackage.AOM_LOCAL_VARIABLE__OWNER:
				return getOwner() != null;
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
				case StructurePackage.AOM_LOCAL_VARIABLE__NAME: return StructurePackage.AOM_NAMED_ELEMENT__NAME;
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
				case StructurePackage.AOM_NAMED_ELEMENT__NAME: return StructurePackage.AOM_LOCAL_VARIABLE__NAME;
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

} //AOMLocalVariableImpl
