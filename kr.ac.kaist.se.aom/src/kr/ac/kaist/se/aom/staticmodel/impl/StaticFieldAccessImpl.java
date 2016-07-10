/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kr.ac.kaist.se.aom.staticmodel.impl;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Field Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticFieldAccessImpl#getAccessingScope <em>Accessing Scope</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticFieldAccessImpl#getAccessedField <em>Accessed Field</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticFieldAccessImpl#getAccessingType <em>Accessing Type</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticFieldAccessImpl#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticFieldAccessImpl#getColumnNumber <em>Column Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticFieldAccessImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.staticmodel.impl.StaticFieldAccessImpl#getDynamicAccessCount <em>Dynamic Access Count</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StaticFieldAccessImpl extends StaticDependencyImpl implements StaticFieldAccess {
	/**
	 * The cached value of the '{@link #getAccessedField() <em>Accessed Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessedField()
	 * @generated
	 * @ordered
	 */
	protected AOMField accessedField;

	/**
	 * The cached value of the '{@link #getAccessingType() <em>Accessing Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessingType()
	 * @generated
	 * @ordered
	 */
	protected AOMClass accessingType;

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
	 * The default value of the '{@link #getDynamicAccessCount() <em>Dynamic Access Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicAccessCount()
	 * @generated
	 * @ordered
	 */
	protected static final long DYNAMIC_ACCESS_COUNT_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getDynamicAccessCount() <em>Dynamic Access Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicAccessCount()
	 * @generated
	 * @ordered
	 */
	protected long dynamicAccessCount = DYNAMIC_ACCESS_COUNT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StaticFieldAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StaticmodelPackage.Literals.STATIC_FIELD_ACCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMScope getAccessingScope() {
		if (eContainerFeatureID() != StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE) return null;
		return (AOMScope)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAccessingScope(AOMScope newAccessingScope, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAccessingScope, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccessingScope(AOMScope newAccessingScope) {
		if (newAccessingScope != eInternalContainer() || (eContainerFeatureID() != StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE && newAccessingScope != null)) {
			if (EcoreUtil.isAncestor(this, newAccessingScope))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAccessingScope != null)
				msgs = ((InternalEObject)newAccessingScope).eInverseAdd(this, StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES, AOMScope.class, msgs);
			msgs = basicSetAccessingScope(newAccessingScope, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE, newAccessingScope, newAccessingScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMField getAccessedField() {
		if (accessedField != null && accessedField.eIsProxy()) {
			InternalEObject oldAccessedField = (InternalEObject)accessedField;
			accessedField = (AOMField)eResolveProxy(oldAccessedField);
			if (accessedField != oldAccessedField) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD, oldAccessedField, accessedField));
			}
		}
		return accessedField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMField basicGetAccessedField() {
		return accessedField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAccessedField(AOMField newAccessedField, NotificationChain msgs) {
		AOMField oldAccessedField = accessedField;
		accessedField = newAccessedField;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD, oldAccessedField, newAccessedField);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccessedField(AOMField newAccessedField) {
		if (newAccessedField != accessedField) {
			NotificationChain msgs = null;
			if (accessedField != null)
				msgs = ((InternalEObject)accessedField).eInverseRemove(this, StructurePackage.AOM_FIELD__STATIC_REFERER, AOMField.class, msgs);
			if (newAccessedField != null)
				msgs = ((InternalEObject)newAccessedField).eInverseAdd(this, StructurePackage.AOM_FIELD__STATIC_REFERER, AOMField.class, msgs);
			msgs = basicSetAccessedField(newAccessedField, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD, newAccessedField, newAccessedField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMClass getAccessingType() {
		if (accessingType != null && accessingType.eIsProxy()) {
			InternalEObject oldAccessingType = (InternalEObject)accessingType;
			accessingType = (AOMClass)eResolveProxy(oldAccessingType);
			if (accessingType != oldAccessingType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_TYPE, oldAccessingType, accessingType));
			}
		}
		return accessingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMClass basicGetAccessingType() {
		return accessingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccessingType(AOMClass newAccessingType) {
		AOMClass oldAccessingType = accessingType;
		accessingType = newAccessingType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_TYPE, oldAccessingType, accessingType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_FIELD_ACCESS__LINE_NUMBER, oldLineNumber, lineNumber));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_FIELD_ACCESS__COLUMN_NUMBER, oldColumnNumber, columnNumber));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_FIELD_ACCESS__FILE_NAME, oldFileName, fileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getDynamicAccessCount() {
		return dynamicAccessCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamicAccessCount(long newDynamicAccessCount) {
		long oldDynamicAccessCount = dynamicAccessCount;
		dynamicAccessCount = newDynamicAccessCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StaticmodelPackage.STATIC_FIELD_ACCESS__DYNAMIC_ACCESS_COUNT, oldDynamicAccessCount, dynamicAccessCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAccessingScope((AOMScope)otherEnd, msgs);
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD:
				if (accessedField != null)
					msgs = ((InternalEObject)accessedField).eInverseRemove(this, StructurePackage.AOM_FIELD__STATIC_REFERER, AOMField.class, msgs);
				return basicSetAccessedField((AOMField)otherEnd, msgs);
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
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE:
				return basicSetAccessingScope(null, msgs);
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD:
				return basicSetAccessedField(null, msgs);
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
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE:
				return eInternalContainer().eInverseRemove(this, StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES, AOMScope.class, msgs);
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
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE:
				return getAccessingScope();
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD:
				if (resolve) return getAccessedField();
				return basicGetAccessedField();
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_TYPE:
				if (resolve) return getAccessingType();
				return basicGetAccessingType();
			case StaticmodelPackage.STATIC_FIELD_ACCESS__LINE_NUMBER:
				return getLineNumber();
			case StaticmodelPackage.STATIC_FIELD_ACCESS__COLUMN_NUMBER:
				return getColumnNumber();
			case StaticmodelPackage.STATIC_FIELD_ACCESS__FILE_NAME:
				return getFileName();
			case StaticmodelPackage.STATIC_FIELD_ACCESS__DYNAMIC_ACCESS_COUNT:
				return getDynamicAccessCount();
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
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE:
				setAccessingScope((AOMScope)newValue);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD:
				setAccessedField((AOMField)newValue);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_TYPE:
				setAccessingType((AOMClass)newValue);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__LINE_NUMBER:
				setLineNumber((Integer)newValue);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__COLUMN_NUMBER:
				setColumnNumber((Integer)newValue);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__FILE_NAME:
				setFileName((String)newValue);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__DYNAMIC_ACCESS_COUNT:
				setDynamicAccessCount((Long)newValue);
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
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE:
				setAccessingScope((AOMScope)null);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD:
				setAccessedField((AOMField)null);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_TYPE:
				setAccessingType((AOMClass)null);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__LINE_NUMBER:
				setLineNumber(LINE_NUMBER_EDEFAULT);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__COLUMN_NUMBER:
				setColumnNumber(COLUMN_NUMBER_EDEFAULT);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__DYNAMIC_ACCESS_COUNT:
				setDynamicAccessCount(DYNAMIC_ACCESS_COUNT_EDEFAULT);
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
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_SCOPE:
				return getAccessingScope() != null;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSED_FIELD:
				return accessedField != null;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__ACCESSING_TYPE:
				return accessingType != null;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__LINE_NUMBER:
				return lineNumber != LINE_NUMBER_EDEFAULT;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__COLUMN_NUMBER:
				return columnNumber != COLUMN_NUMBER_EDEFAULT;
			case StaticmodelPackage.STATIC_FIELD_ACCESS__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case StaticmodelPackage.STATIC_FIELD_ACCESS__DYNAMIC_ACCESS_COUNT:
				return dynamicAccessCount != DYNAMIC_ACCESS_COUNT_EDEFAULT;
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
		result.append(", dynamicAccessCount: ");
		result.append(dynamicAccessCount);
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

} //StaticFieldAccessImpl
