/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicMethodCallImpl.java,v 1.7 2011-01-18 13:56:36 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel.impl;

import java.util.Collection;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMMethod;
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
 * An implementation of the model object '<em><b>Dynamic Method Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl#getCaller <em>Caller</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl#getCallee <em>Callee</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl#getPreviousCall <em>Previous Call</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl#getNextCalls <em>Next Calls</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl#getStatic <em>Static</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicMethodCallImpl#getTid <em>Tid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicMethodCallImpl extends DynamicDependencyImpl implements DynamicMethodCall {
	/**
	 * The cached value of the '{@link #getCallee() <em>Callee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallee()
	 * @generated
	 * @ordered
	 */
	protected AOMMethod callee;

	/**
	 * The cached value of the '{@link #getPreviousCall() <em>Previous Call</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreviousCall()
	 * @generated
	 * @ordered
	 */
	protected DynamicMethodCall previousCall;

	/**
	 * The cached value of the '{@link #getNextCalls() <em>Next Calls</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextCalls()
	 * @generated
	 * @ordered
	 */
	protected EList<DynamicMethodCall> nextCalls;

	/**
	 * The cached value of the '{@link #getStatic() <em>Static</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatic()
	 * @generated
	 * @ordered
	 */
	protected StaticMethodCall static_;

	/**
	 * The default value of the '{@link #getTid() <em>Tid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTid()
	 * @generated
	 * @ordered
	 */
	protected static final int TID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTid() <em>Tid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTid()
	 * @generated
	 * @ordered
	 */
	protected int tid = TID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicMethodCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DynamicmodelPackage.Literals.DYNAMIC_METHOD_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMScope getCaller() {
		if (eContainerFeatureID() != DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER) return null;
		return (AOMScope)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCaller(AOMScope newCaller, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCaller, DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCaller(AOMScope newCaller) {
		if (newCaller != eInternalContainer() || (eContainerFeatureID() != DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER && newCaller != null)) {
			if (EcoreUtil.isAncestor(this, newCaller))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCaller != null)
				msgs = ((InternalEObject)newCaller).eInverseAdd(this, StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS, AOMScope.class, msgs);
			msgs = basicSetCaller(newCaller, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER, newCaller, newCaller));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMMethod getCallee() {
		if (callee != null && callee.eIsProxy()) {
			InternalEObject oldCallee = (InternalEObject)callee;
			callee = (AOMMethod)eResolveProxy(oldCallee);
			if (callee != oldCallee) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE, oldCallee, callee));
			}
		}
		return callee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMMethod basicGetCallee() {
		return callee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallee(AOMMethod newCallee, NotificationChain msgs) {
		AOMMethod oldCallee = callee;
		callee = newCallee;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE, oldCallee, newCallee);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallee(AOMMethod newCallee) {
		if (newCallee != callee) {
			NotificationChain msgs = null;
			if (callee != null)
				msgs = ((InternalEObject)callee).eInverseRemove(this, StructurePackage.AOM_METHOD__DYNAMIC_REFERER, AOMMethod.class, msgs);
			if (newCallee != null)
				msgs = ((InternalEObject)newCallee).eInverseAdd(this, StructurePackage.AOM_METHOD__DYNAMIC_REFERER, AOMMethod.class, msgs);
			msgs = basicSetCallee(newCallee, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE, newCallee, newCallee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicMethodCall getPreviousCall() {
		if (previousCall != null && previousCall.eIsProxy()) {
			InternalEObject oldPreviousCall = (InternalEObject)previousCall;
			previousCall = (DynamicMethodCall)eResolveProxy(oldPreviousCall);
			if (previousCall != oldPreviousCall) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL, oldPreviousCall, previousCall));
			}
		}
		return previousCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicMethodCall basicGetPreviousCall() {
		return previousCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPreviousCall(DynamicMethodCall newPreviousCall, NotificationChain msgs) {
		DynamicMethodCall oldPreviousCall = previousCall;
		previousCall = newPreviousCall;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL, oldPreviousCall, newPreviousCall);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreviousCall(DynamicMethodCall newPreviousCall) {
		if (newPreviousCall != previousCall) {
			NotificationChain msgs = null;
			if (previousCall != null)
				msgs = ((InternalEObject)previousCall).eInverseRemove(this, DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS, DynamicMethodCall.class, msgs);
			if (newPreviousCall != null)
				msgs = ((InternalEObject)newPreviousCall).eInverseAdd(this, DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS, DynamicMethodCall.class, msgs);
			msgs = basicSetPreviousCall(newPreviousCall, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL, newPreviousCall, newPreviousCall));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicMethodCall> getNextCalls() {
		if (nextCalls == null) {
			nextCalls = new EObjectWithInverseResolvingEList<DynamicMethodCall>(DynamicMethodCall.class, this, DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS, DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL);
		}
		return nextCalls;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticMethodCall getStatic() {
		if (static_ != null && static_.eIsProxy()) {
			InternalEObject oldStatic = (InternalEObject)static_;
			static_ = (StaticMethodCall)eResolveProxy(oldStatic);
			if (static_ != oldStatic) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DynamicmodelPackage.DYNAMIC_METHOD_CALL__STATIC, oldStatic, static_));
			}
		}
		return static_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticMethodCall basicGetStatic() {
		return static_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(StaticMethodCall newStatic) {
		StaticMethodCall oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_METHOD_CALL__STATIC, oldStatic, static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTid() {
		return tid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTid(int newTid) {
		int oldTid = tid;
		tid = newTid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_METHOD_CALL__TID, oldTid, tid));
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
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCaller((AOMScope)otherEnd, msgs);
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE:
				if (callee != null)
					msgs = ((InternalEObject)callee).eInverseRemove(this, StructurePackage.AOM_METHOD__DYNAMIC_REFERER, AOMMethod.class, msgs);
				return basicSetCallee((AOMMethod)otherEnd, msgs);
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL:
				if (previousCall != null)
					msgs = ((InternalEObject)previousCall).eInverseRemove(this, DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS, DynamicMethodCall.class, msgs);
				return basicSetPreviousCall((DynamicMethodCall)otherEnd, msgs);
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNextCalls()).basicAdd(otherEnd, msgs);
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
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER:
				return basicSetCaller(null, msgs);
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE:
				return basicSetCallee(null, msgs);
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL:
				return basicSetPreviousCall(null, msgs);
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS:
				return ((InternalEList<?>)getNextCalls()).basicRemove(otherEnd, msgs);
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
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER:
				return eInternalContainer().eInverseRemove(this, StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS, AOMScope.class, msgs);
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
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER:
				return getCaller();
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE:
				if (resolve) return getCallee();
				return basicGetCallee();
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL:
				if (resolve) return getPreviousCall();
				return basicGetPreviousCall();
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS:
				return getNextCalls();
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__STATIC:
				if (resolve) return getStatic();
				return basicGetStatic();
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__TID:
				return getTid();
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
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER:
				setCaller((AOMScope)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE:
				setCallee((AOMMethod)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL:
				setPreviousCall((DynamicMethodCall)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS:
				getNextCalls().clear();
				getNextCalls().addAll((Collection<? extends DynamicMethodCall>)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__STATIC:
				setStatic((StaticMethodCall)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__TID:
				setTid((Integer)newValue);
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
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER:
				setCaller((AOMScope)null);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE:
				setCallee((AOMMethod)null);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL:
				setPreviousCall((DynamicMethodCall)null);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS:
				getNextCalls().clear();
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__STATIC:
				setStatic((StaticMethodCall)null);
				return;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__TID:
				setTid(TID_EDEFAULT);
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
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLER:
				return getCaller() != null;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE:
				return callee != null;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__PREVIOUS_CALL:
				return previousCall != null;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__NEXT_CALLS:
				return nextCalls != null && !nextCalls.isEmpty();
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__STATIC:
				return static_ != null;
			case DynamicmodelPackage.DYNAMIC_METHOD_CALL__TID:
				return tid != TID_EDEFAULT;
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
		result.append(" (tid: ");
		result.append(tid);
		result.append(')');
		return result.toString();
	}

} //DynamicMethodCallImpl
