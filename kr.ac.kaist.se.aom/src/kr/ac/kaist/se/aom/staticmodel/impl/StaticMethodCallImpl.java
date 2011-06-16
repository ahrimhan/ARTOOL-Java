/**
 * <copyright>
 * </copyright>
 *
 * $Id: StaticMethodCallImpl.java,v 1.9 2011-01-05 07:42:55 igsong Exp $
 */
package kr.ac.kaist.se.aom.staticmodel.impl;

import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Method Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl#getCaller <em>Caller</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl#getCallee <em>Callee</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl#getCallingType <em>Calling Type</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl#getColumnNumber <em>Column Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticMethodCallImpl#getFileName <em>File Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StaticMethodCallImpl extends StaticDependencyImpl implements StaticMethodCall {
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
	 * The cached value of the '{@link #getCallingType() <em>Calling Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallingType()
	 * @generated
	 * @ordered
	 */
	protected AOMClass callingType;

	/**
	 * The default value of the '{@link #getLineNumber() <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLineNumber() <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineNumber()
	 * @generated
	 * @ordered
	 */
	protected int lineNumber = LINE_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getColumnNumber() <em>Column Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int COLUMN_NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getColumnNumber() <em>Column Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnNumber()
	 * @generated
	 * @ordered
	 */
	protected int columnNumber = COLUMN_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected String fileName = FILE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StaticMethodCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StaticmodelPackage.Literals.STATIC_METHOD_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMScope getCaller() {
		if (eContainerFeatureID() != StaticmodelPackage.STATIC_METHOD_CALL__CALLER) return null;
		return (AOMScope)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCaller(AOMScope newCaller, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCaller, StaticmodelPackage.STATIC_METHOD_CALL__CALLER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCaller(AOMScope newCaller) {
		if (newCaller != eInternalContainer() || (eContainerFeatureID() != StaticmodelPackage.STATIC_METHOD_CALL__CALLER && newCaller != null)) {
			if (EcoreUtil.isAncestor(this, newCaller))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCaller != null)
				msgs = ((InternalEObject)newCaller).eInverseAdd(this, StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS, AOMScope.class, msgs);
			msgs = basicSetCaller(newCaller, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_METHOD_CALL__CALLER, newCaller, newCaller));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StaticmodelPackage.STATIC_METHOD_CALL__CALLEE, oldCallee, callee));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_METHOD_CALL__CALLEE, oldCallee, newCallee);
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
				msgs = ((InternalEObject)callee).eInverseRemove(this, StructurePackage.AOM_METHOD__STATIC_REFERER, AOMMethod.class, msgs);
			if (newCallee != null)
				msgs = ((InternalEObject)newCallee).eInverseAdd(this, StructurePackage.AOM_METHOD__STATIC_REFERER, AOMMethod.class, msgs);
			msgs = basicSetCallee(newCallee, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_METHOD_CALL__CALLEE, newCallee, newCallee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMClass getCallingType() {
		if (callingType != null && callingType.eIsProxy()) {
			InternalEObject oldCallingType = (InternalEObject)callingType;
			callingType = (AOMClass)eResolveProxy(oldCallingType);
			if (callingType != oldCallingType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StaticmodelPackage.STATIC_METHOD_CALL__CALLING_TYPE, oldCallingType, callingType));
			}
		}
		return callingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMClass basicGetCallingType() {
		return callingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallingType(AOMClass newCallingType) {
		AOMClass oldCallingType = callingType;
		callingType = newCallingType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_METHOD_CALL__CALLING_TYPE, oldCallingType, callingType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineNumber(int newLineNumber) {
		int oldLineNumber = lineNumber;
		lineNumber = newLineNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_METHOD_CALL__LINE_NUMBER, oldLineNumber, lineNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getColumnNumber() {
		return columnNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnNumber(int newColumnNumber) {
		int oldColumnNumber = columnNumber;
		columnNumber = newColumnNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_METHOD_CALL__COLUMN_NUMBER, oldColumnNumber, columnNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileName(String newFileName) {
		String oldFileName = fileName;
		fileName = newFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_METHOD_CALL__FILE_NAME, oldFileName, fileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCaller((AOMScope)otherEnd, msgs);
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLEE:
				if (callee != null)
					msgs = ((InternalEObject)callee).eInverseRemove(this, StructurePackage.AOM_METHOD__STATIC_REFERER, AOMMethod.class, msgs);
				return basicSetCallee((AOMMethod)otherEnd, msgs);
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
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLER:
				return basicSetCaller(null, msgs);
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLEE:
				return basicSetCallee(null, msgs);
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
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLER:
				return eInternalContainer().eInverseRemove(this, StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS, AOMScope.class, msgs);
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
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLER:
				return getCaller();
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLEE:
				if (resolve) return getCallee();
				return basicGetCallee();
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLING_TYPE:
				if (resolve) return getCallingType();
				return basicGetCallingType();
			case StaticmodelPackage.STATIC_METHOD_CALL__LINE_NUMBER:
				return getLineNumber();
			case StaticmodelPackage.STATIC_METHOD_CALL__COLUMN_NUMBER:
				return getColumnNumber();
			case StaticmodelPackage.STATIC_METHOD_CALL__FILE_NAME:
				return getFileName();
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
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLER:
				setCaller((AOMScope)newValue);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLEE:
				setCallee((AOMMethod)newValue);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLING_TYPE:
				setCallingType((AOMClass)newValue);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__LINE_NUMBER:
				setLineNumber((Integer)newValue);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__COLUMN_NUMBER:
				setColumnNumber((Integer)newValue);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__FILE_NAME:
				setFileName((String)newValue);
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
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLER:
				setCaller((AOMScope)null);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLEE:
				setCallee((AOMMethod)null);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLING_TYPE:
				setCallingType((AOMClass)null);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__LINE_NUMBER:
				setLineNumber(LINE_NUMBER_EDEFAULT);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__COLUMN_NUMBER:
				setColumnNumber(COLUMN_NUMBER_EDEFAULT);
				return;
			case StaticmodelPackage.STATIC_METHOD_CALL__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
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
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLER:
				return getCaller() != null;
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLEE:
				return callee != null;
			case StaticmodelPackage.STATIC_METHOD_CALL__CALLING_TYPE:
				return callingType != null;
			case StaticmodelPackage.STATIC_METHOD_CALL__LINE_NUMBER:
				return lineNumber != LINE_NUMBER_EDEFAULT;
			case StaticmodelPackage.STATIC_METHOD_CALL__COLUMN_NUMBER:
				return columnNumber != COLUMN_NUMBER_EDEFAULT;
			case StaticmodelPackage.STATIC_METHOD_CALL__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
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
		result.append(" (lineNumber: ");
		result.append(lineNumber);
		result.append(", columnNumber: ");
		result.append(columnNumber);
		result.append(", fileName: ");
		result.append(fileName);
		result.append(')');
		return result.toString();
	}

	private IMethodBinding methodBinding;
	private ITypeBinding typeBinding;
	
	@Override
	public IMethodBinding getMethodBinding() {
		return methodBinding;
	}

	@Override
	public void setMethodBinding(IMethodBinding binding) {
		this.methodBinding = binding;
	}

	@Override
	public ITypeBinding getTypeBinding() {
		return typeBinding;
	}

	@Override
	public void setTypeBinding(ITypeBinding binding) {
		this.typeBinding = binding;
	}

} //StaticMethodCallImpl
