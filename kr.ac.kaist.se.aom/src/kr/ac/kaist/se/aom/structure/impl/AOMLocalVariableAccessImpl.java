/**
 */
package kr.ac.kaist.se.aom.structure.impl;

import kr.ac.kaist.se.aom.structure.AOMLocalVariableAccess;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.AOMVariableDef;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AOM Local Variable Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableAccessImpl#getAccessedVariableDef <em>Accessed Variable Def</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableAccessImpl#getAccessingScope <em>Accessing Scope</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMLocalVariableAccessImpl#isParameterAccess <em>Parameter Access</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AOMLocalVariableAccessImpl extends EObjectImpl implements AOMLocalVariableAccess {
	/**
	 * The cached value of the '{@link #getAccessedVariableDef() <em>Accessed Variable Def</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessedVariableDef()
	 * @generated
	 * @ordered
	 */
	protected AOMVariableDef accessedVariableDef;

	/**
	 * The default value of the '{@link #isParameterAccess() <em>Parameter Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParameterAccess()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARAMETER_ACCESS_EDEFAULT = false;
	/**
	 * The flag representing the value of the '{@link #isParameterAccess() <em>Parameter Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParameterAccess()
	 * @generated
	 * @ordered
	 */
	protected static final int PARAMETER_ACCESS_EFLAG = 1 << 8;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AOMLocalVariableAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StructurePackage.Literals.AOM_LOCAL_VARIABLE_ACCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMVariableDef getAccessedVariableDef() {
		if (accessedVariableDef != null && accessedVariableDef.eIsProxy()) {
			InternalEObject oldAccessedVariableDef = (InternalEObject)accessedVariableDef;
			accessedVariableDef = (AOMVariableDef)eResolveProxy(oldAccessedVariableDef);
			if (accessedVariableDef != oldAccessedVariableDef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF, oldAccessedVariableDef, accessedVariableDef));
			}
		}
		return accessedVariableDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMVariableDef basicGetAccessedVariableDef() {
		return accessedVariableDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAccessedVariableDef(AOMVariableDef newAccessedVariableDef, NotificationChain msgs) {
		AOMVariableDef oldAccessedVariableDef = accessedVariableDef;
		accessedVariableDef = newAccessedVariableDef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF, oldAccessedVariableDef, newAccessedVariableDef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccessedVariableDef(AOMVariableDef newAccessedVariableDef) {
		if (newAccessedVariableDef != accessedVariableDef) {
			NotificationChain msgs = null;
			if (accessedVariableDef != null)
				msgs = ((InternalEObject)accessedVariableDef).eInverseRemove(this, StructurePackage.AOM_VARIABLE_DEF__REFERER, AOMVariableDef.class, msgs);
			if (newAccessedVariableDef != null)
				msgs = ((InternalEObject)newAccessedVariableDef).eInverseAdd(this, StructurePackage.AOM_VARIABLE_DEF__REFERER, AOMVariableDef.class, msgs);
			msgs = basicSetAccessedVariableDef(newAccessedVariableDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF, newAccessedVariableDef, newAccessedVariableDef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMScope getAccessingScope() {
		if (eContainerFeatureID() != StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE) return null;
		return (AOMScope)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAccessingScope(AOMScope newAccessingScope, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAccessingScope, StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccessingScope(AOMScope newAccessingScope) {
		if (newAccessingScope != eInternalContainer() || (eContainerFeatureID() != StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE && newAccessingScope != null)) {
			if (EcoreUtil.isAncestor(this, newAccessingScope))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAccessingScope != null)
				msgs = ((InternalEObject)newAccessingScope).eInverseAdd(this, StructurePackage.AOM_SCOPE__LOCAL_VARIABLE_ACCESSES, AOMScope.class, msgs);
			msgs = basicSetAccessingScope(newAccessingScope, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE, newAccessingScope, newAccessingScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isParameterAccess() {
		return (eFlags & PARAMETER_ACCESS_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterAccess(boolean newParameterAccess) {
		boolean oldParameterAccess = (eFlags & PARAMETER_ACCESS_EFLAG) != 0;
		if (newParameterAccess) eFlags |= PARAMETER_ACCESS_EFLAG; else eFlags &= ~PARAMETER_ACCESS_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__PARAMETER_ACCESS, oldParameterAccess, newParameterAccess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF:
				if (accessedVariableDef != null)
					msgs = ((InternalEObject)accessedVariableDef).eInverseRemove(this, StructurePackage.AOM_VARIABLE_DEF__REFERER, AOMVariableDef.class, msgs);
				return basicSetAccessedVariableDef((AOMVariableDef)otherEnd, msgs);
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAccessingScope((AOMScope)otherEnd, msgs);
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
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF:
				return basicSetAccessedVariableDef(null, msgs);
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE:
				return basicSetAccessingScope(null, msgs);
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
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE:
				return eInternalContainer().eInverseRemove(this, StructurePackage.AOM_SCOPE__LOCAL_VARIABLE_ACCESSES, AOMScope.class, msgs);
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
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF:
				if (resolve) return getAccessedVariableDef();
				return basicGetAccessedVariableDef();
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE:
				return getAccessingScope();
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__PARAMETER_ACCESS:
				return isParameterAccess();
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
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF:
				setAccessedVariableDef((AOMVariableDef)newValue);
				return;
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE:
				setAccessingScope((AOMScope)newValue);
				return;
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__PARAMETER_ACCESS:
				setParameterAccess((Boolean)newValue);
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
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF:
				setAccessedVariableDef((AOMVariableDef)null);
				return;
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE:
				setAccessingScope((AOMScope)null);
				return;
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__PARAMETER_ACCESS:
				setParameterAccess(PARAMETER_ACCESS_EDEFAULT);
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
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSED_VARIABLE_DEF:
				return accessedVariableDef != null;
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__ACCESSING_SCOPE:
				return getAccessingScope() != null;
			case StructurePackage.AOM_LOCAL_VARIABLE_ACCESS__PARAMETER_ACCESS:
				return ((eFlags & PARAMETER_ACCESS_EFLAG) != 0) != PARAMETER_ACCESS_EDEFAULT;
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
		result.append(" (parameterAccess: ");
		result.append((eFlags & PARAMETER_ACCESS_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

	private IVariableBinding variableBinding;
	private ITypeBinding typeBinding;
	
	@Override
	public IVariableBinding getVariableBinding() {
		return variableBinding;
	}

	@Override
	public void setVariableBinding(IVariableBinding binding) {
		this.variableBinding = binding;
	}

	@Override
	public ITypeBinding getTypeBinding() {
		return typeBinding;
	}

	@Override
	public void setTypeBinding(ITypeBinding binding) {
		this.typeBinding = binding;
	}
} //AOMLocalVariableAccessImpl
