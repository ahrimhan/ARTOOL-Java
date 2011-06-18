/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kr.ac.kaist.se.aom.dynamicmodel.impl;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;

import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Field Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicFieldAccessImpl#getAccessingScope <em>Accessing Scope</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicFieldAccessImpl#getAccessedField <em>Accessed Field</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicFieldAccessImpl#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicFieldAccessImpl#getColumnNumber <em>Column Number</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicFieldAccessImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicFieldAccessImpl#getStaticFieldAccess <em>Static Field Access</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicFieldAccessImpl#isIsReader <em>Is Reader</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.dynamicmodel.impl.DynamicFieldAccessImpl#isIsWriter <em>Is Writer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicFieldAccessImpl extends DynamicDependencyImpl implements DynamicFieldAccess {
	/**
	 * The cached value of the '{@link #getAccessingScope() <em>Accessing Scope</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessingScope()
	 * @generated
	 * @ordered
	 */
	protected AOMScope accessingScope;

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
	 * The cached value of the '{@link #getStaticFieldAccess() <em>Static Field Access</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticFieldAccess()
	 * @generated
	 * @ordered
	 */
	protected StaticFieldAccess staticFieldAccess;

	/**
	 * The default value of the '{@link #isIsReader() <em>Is Reader</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsReader()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_READER_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsReader() <em>Is Reader</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsReader()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_READER_EFLAG = 1 << 8;

	/**
	 * The default value of the '{@link #isIsWriter() <em>Is Writer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsWriter()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_WRITER_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsWriter() <em>Is Writer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsWriter()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_WRITER_EFLAG = 1 << 9;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicFieldAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMScope getAccessingScope() {
		if (accessingScope != null && accessingScope.eIsProxy()) {
			InternalEObject oldAccessingScope = (InternalEObject)accessingScope;
			accessingScope = (AOMScope)eResolveProxy(oldAccessingScope);
			if (accessingScope != oldAccessingScope) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE, oldAccessingScope, accessingScope));
			}
		}
		return accessingScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMScope basicGetAccessingScope() {
		return accessingScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAccessingScope(AOMScope newAccessingScope, NotificationChain msgs) {
		AOMScope oldAccessingScope = accessingScope;
		accessingScope = newAccessingScope;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE, oldAccessingScope, newAccessingScope);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccessingScope(AOMScope newAccessingScope) {
		if (newAccessingScope != accessingScope) {
			NotificationChain msgs = null;
			if (accessingScope != null)
				msgs = ((InternalEObject)accessingScope).eInverseRemove(this, StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES, AOMScope.class, msgs);
			if (newAccessingScope != null)
				msgs = ((InternalEObject)newAccessingScope).eInverseAdd(this, StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES, AOMScope.class, msgs);
			msgs = basicSetAccessingScope(newAccessingScope, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE, newAccessingScope, newAccessingScope));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD, oldAccessedField, accessedField));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD, oldAccessedField, newAccessedField);
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
				msgs = ((InternalEObject)accessedField).eInverseRemove(this, StructurePackage.AOM_FIELD__DYNAMIC_REFERER, AOMField.class, msgs);
			if (newAccessedField != null)
				msgs = ((InternalEObject)newAccessedField).eInverseAdd(this, StructurePackage.AOM_FIELD__DYNAMIC_REFERER, AOMField.class, msgs);
			msgs = basicSetAccessedField(newAccessedField, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD, newAccessedField, newAccessedField));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__LINE_NUMBER, oldLineNumber, lineNumber));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__COLUMN_NUMBER, oldColumnNumber, columnNumber));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__FILE_NAME, oldFileName, fileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticFieldAccess getStaticFieldAccess() {
		if (staticFieldAccess != null && staticFieldAccess.eIsProxy()) {
			InternalEObject oldStaticFieldAccess = (InternalEObject)staticFieldAccess;
			staticFieldAccess = (StaticFieldAccess)eResolveProxy(oldStaticFieldAccess);
			if (staticFieldAccess != oldStaticFieldAccess) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__STATIC_FIELD_ACCESS, oldStaticFieldAccess, staticFieldAccess));
			}
		}
		return staticFieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticFieldAccess basicGetStaticFieldAccess() {
		return staticFieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticFieldAccess(StaticFieldAccess newStaticFieldAccess) {
		StaticFieldAccess oldStaticFieldAccess = staticFieldAccess;
		staticFieldAccess = newStaticFieldAccess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__STATIC_FIELD_ACCESS, oldStaticFieldAccess, staticFieldAccess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsReader() {
		return (eFlags & IS_READER_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReader(boolean newIsReader) {
		boolean oldIsReader = (eFlags & IS_READER_EFLAG) != 0;
		if (newIsReader) eFlags |= IS_READER_EFLAG; else eFlags &= ~IS_READER_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_READER, oldIsReader, newIsReader));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsWriter() {
		return (eFlags & IS_WRITER_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsWriter(boolean newIsWriter) {
		boolean oldIsWriter = (eFlags & IS_WRITER_EFLAG) != 0;
		if (newIsWriter) eFlags |= IS_WRITER_EFLAG; else eFlags &= ~IS_WRITER_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_WRITER, oldIsWriter, newIsWriter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE:
				if (accessingScope != null)
					msgs = ((InternalEObject)accessingScope).eInverseRemove(this, StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES, AOMScope.class, msgs);
				return basicSetAccessingScope((AOMScope)otherEnd, msgs);
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD:
				if (accessedField != null)
					msgs = ((InternalEObject)accessedField).eInverseRemove(this, StructurePackage.AOM_FIELD__DYNAMIC_REFERER, AOMField.class, msgs);
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
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE:
				return basicSetAccessingScope(null, msgs);
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE:
				if (resolve) return getAccessingScope();
				return basicGetAccessingScope();
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD:
				if (resolve) return getAccessedField();
				return basicGetAccessedField();
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__LINE_NUMBER:
				return getLineNumber();
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__COLUMN_NUMBER:
				return getColumnNumber();
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__FILE_NAME:
				return getFileName();
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__STATIC_FIELD_ACCESS:
				if (resolve) return getStaticFieldAccess();
				return basicGetStaticFieldAccess();
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_READER:
				return isIsReader();
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_WRITER:
				return isIsWriter();
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
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE:
				setAccessingScope((AOMScope)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD:
				setAccessedField((AOMField)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__LINE_NUMBER:
				setLineNumber((Integer)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__COLUMN_NUMBER:
				setColumnNumber((Integer)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__FILE_NAME:
				setFileName((String)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__STATIC_FIELD_ACCESS:
				setStaticFieldAccess((StaticFieldAccess)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_READER:
				setIsReader((Boolean)newValue);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_WRITER:
				setIsWriter((Boolean)newValue);
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
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE:
				setAccessingScope((AOMScope)null);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD:
				setAccessedField((AOMField)null);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__LINE_NUMBER:
				setLineNumber(LINE_NUMBER_EDEFAULT);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__COLUMN_NUMBER:
				setColumnNumber(COLUMN_NUMBER_EDEFAULT);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__STATIC_FIELD_ACCESS:
				setStaticFieldAccess((StaticFieldAccess)null);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_READER:
				setIsReader(IS_READER_EDEFAULT);
				return;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_WRITER:
				setIsWriter(IS_WRITER_EDEFAULT);
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
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE:
				return accessingScope != null;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD:
				return accessedField != null;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__LINE_NUMBER:
				return lineNumber != LINE_NUMBER_EDEFAULT;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__COLUMN_NUMBER:
				return columnNumber != COLUMN_NUMBER_EDEFAULT;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__STATIC_FIELD_ACCESS:
				return staticFieldAccess != null;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_READER:
				return ((eFlags & IS_READER_EFLAG) != 0) != IS_READER_EDEFAULT;
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_WRITER:
				return ((eFlags & IS_WRITER_EFLAG) != 0) != IS_WRITER_EDEFAULT;
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
		result.append(", isReader: ");
		result.append((eFlags & IS_READER_EFLAG) != 0);
		result.append(", isWriter: ");
		result.append((eFlags & IS_WRITER_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

} //DynamicFieldAccessImpl
