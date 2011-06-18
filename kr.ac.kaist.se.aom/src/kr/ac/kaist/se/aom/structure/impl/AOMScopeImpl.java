/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMScopeImpl.java,v 1.8 2011-01-05 07:42:54 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.impl;

import java.util.Collection;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMLocalVariable;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AOM Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl#getStaticMethodCalls <em>Static Method Calls</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl#getDynamicMethodCalls <em>Dynamic Method Calls</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl#getStaticFieldAccesses <em>Static Field Accesses</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMScopeImpl#getDynamicFieldAccesses <em>Dynamic Field Accesses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AOMScopeImpl extends EObjectImpl implements AOMScope {
	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMLocalVariable> variables;

	/**
	 * The cached value of the '{@link #getStaticMethodCalls() <em>Static Method Calls</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticMethodCalls()
	 * @generated
	 * @ordered
	 */
	protected EList<StaticMethodCall> staticMethodCalls;

	/**
	 * The cached value of the '{@link #getDynamicMethodCalls() <em>Dynamic Method Calls</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicMethodCalls()
	 * @generated
	 * @ordered
	 */
	protected EList<DynamicMethodCall> dynamicMethodCalls;

	/**
	 * The cached value of the '{@link #getStaticFieldAccesses() <em>Static Field Accesses</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticFieldAccesses()
	 * @generated
	 * @ordered
	 */
	protected EList<StaticFieldAccess> staticFieldAccesses;

	/**
	 * The cached value of the '{@link #getDynamicFieldAccesses() <em>Dynamic Field Accesses</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicFieldAccesses()
	 * @generated
	 * @ordered
	 */
	protected EList<DynamicFieldAccess> dynamicFieldAccesses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AOMScopeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StructurePackage.Literals.AOM_SCOPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMLocalVariable> getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentWithInverseEList<AOMLocalVariable>(AOMLocalVariable.class, this, StructurePackage.AOM_SCOPE__VARIABLES, StructurePackage.AOM_LOCAL_VARIABLE__OWNER);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMMethod getOwner() {
		if (eContainerFeatureID() != StructurePackage.AOM_SCOPE__OWNER) return null;
		return (AOMMethod)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(AOMMethod newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, StructurePackage.AOM_SCOPE__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(AOMMethod newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != StructurePackage.AOM_SCOPE__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, StructurePackage.AOM_METHOD__OWNED_SCOPE, AOMMethod.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_SCOPE__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StaticMethodCall> getStaticMethodCalls() {
		if (staticMethodCalls == null) {
			staticMethodCalls = new EObjectContainmentWithInverseEList<StaticMethodCall>(StaticMethodCall.class, this, StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS, StaticmodelPackage.STATIC_METHOD_CALL__CALLER);
		}
		return staticMethodCalls;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicMethodCall> getDynamicMethodCalls() {
		if (dynamicMethodCalls == null) {
			dynamicMethodCalls = new EObjectContainmentWithInverseEList<DynamicMethodCall>(DynamicMethodCall.class, this, StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS, DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER);
		}
		return dynamicMethodCalls;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StaticFieldAccess> getStaticFieldAccesses() {
		if (staticFieldAccesses == null) {
			staticFieldAccesses = new EObjectWithInverseResolvingEList<StaticFieldAccess>(StaticFieldAccess.class, this, StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE);
		}
		return staticFieldAccesses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicFieldAccess> getDynamicFieldAccesses() {
		if (dynamicFieldAccesses == null) {
			dynamicFieldAccesses = new EObjectWithInverseResolvingEList<DynamicFieldAccess>(DynamicFieldAccess.class, this, StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE);
		}
		return dynamicFieldAccesses;
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
			case StructurePackage.AOM_SCOPE__VARIABLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVariables()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((AOMMethod)otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStaticMethodCalls()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDynamicMethodCalls()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStaticFieldAccesses()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDynamicFieldAccesses()).basicAdd(otherEnd, msgs);
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
			case StructurePackage.AOM_SCOPE__VARIABLES:
				return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__OWNER:
				return basicSetOwner(null, msgs);
			case StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS:
				return ((InternalEList<?>)getStaticMethodCalls()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS:
				return ((InternalEList<?>)getDynamicMethodCalls()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES:
				return ((InternalEList<?>)getStaticFieldAccesses()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES:
				return ((InternalEList<?>)getDynamicFieldAccesses()).basicRemove(otherEnd, msgs);
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
			case StructurePackage.AOM_SCOPE__OWNER:
				return eInternalContainer().eInverseRemove(this, StructurePackage.AOM_METHOD__OWNED_SCOPE, AOMMethod.class, msgs);
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
			case StructurePackage.AOM_SCOPE__VARIABLES:
				return getVariables();
			case StructurePackage.AOM_SCOPE__OWNER:
				return getOwner();
			case StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS:
				return getStaticMethodCalls();
			case StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS:
				return getDynamicMethodCalls();
			case StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES:
				return getStaticFieldAccesses();
			case StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES:
				return getDynamicFieldAccesses();
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
			case StructurePackage.AOM_SCOPE__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection<? extends AOMLocalVariable>)newValue);
				return;
			case StructurePackage.AOM_SCOPE__OWNER:
				setOwner((AOMMethod)newValue);
				return;
			case StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS:
				getStaticMethodCalls().clear();
				getStaticMethodCalls().addAll((Collection<? extends StaticMethodCall>)newValue);
				return;
			case StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS:
				getDynamicMethodCalls().clear();
				getDynamicMethodCalls().addAll((Collection<? extends DynamicMethodCall>)newValue);
				return;
			case StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES:
				getStaticFieldAccesses().clear();
				getStaticFieldAccesses().addAll((Collection<? extends StaticFieldAccess>)newValue);
				return;
			case StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES:
				getDynamicFieldAccesses().clear();
				getDynamicFieldAccesses().addAll((Collection<? extends DynamicFieldAccess>)newValue);
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
			case StructurePackage.AOM_SCOPE__VARIABLES:
				getVariables().clear();
				return;
			case StructurePackage.AOM_SCOPE__OWNER:
				setOwner((AOMMethod)null);
				return;
			case StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS:
				getStaticMethodCalls().clear();
				return;
			case StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS:
				getDynamicMethodCalls().clear();
				return;
			case StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES:
				getStaticFieldAccesses().clear();
				return;
			case StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES:
				getDynamicFieldAccesses().clear();
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
			case StructurePackage.AOM_SCOPE__VARIABLES:
				return variables != null && !variables.isEmpty();
			case StructurePackage.AOM_SCOPE__OWNER:
				return getOwner() != null;
			case StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS:
				return staticMethodCalls != null && !staticMethodCalls.isEmpty();
			case StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS:
				return dynamicMethodCalls != null && !dynamicMethodCalls.isEmpty();
			case StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES:
				return staticFieldAccesses != null && !staticFieldAccesses.isEmpty();
			case StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES:
				return dynamicFieldAccesses != null && !dynamicFieldAccesses.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	private List<IVariableBinding> variableBinding = new Vector<IVariableBinding>();
	private List<IMethodBinding> methodBinding = new Vector<IMethodBinding>();
	
	public List<IVariableBinding> getVariableBindings(){
		return variableBinding;
	}
	
	public List<IMethodBinding> getMethodBindings()
	{
		return methodBinding;
	}
	
	private HashMap<IVariableBinding, AOMLocalVariable> varBinding2AOMLocalVariable = new HashMap<IVariableBinding, AOMLocalVariable>();
	public HashMap<IVariableBinding, AOMLocalVariable> getVarBinding2AOMLocalVariable()
	{
		return varBinding2AOMLocalVariable;
	}

} //AOMScopeImpl
