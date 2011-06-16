/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMMethodImpl.java,v 1.13 2011-01-18 07:51:59 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.impl;

import java.util.Collection;
import kr.ac.kaist.se.aom.AomPackage;
import kr.ac.kaist.se.aom.MeasurableElement;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicMethodCall;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;
import kr.ac.kaist.se.aom.impl.StringToObjectImpl;
import java.util.HashMap;

import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.AOMParameter;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.AOMType;
import kr.ac.kaist.se.aom.structure.AOMTypedElement;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AOM Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getType <em>Type</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getMeasuredDataSet <em>Measured Data Set</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getSignature <em>Signature</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getStaticReferer <em>Static Referer</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getOverriding <em>Overriding</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getOverridedBy <em>Overrided By</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getOwnedScope <em>Owned Scope</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getDynamicReferer <em>Dynamic Referer</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getMethodId <em>Method Id</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getStartLine <em>Start Line</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getEndLine <em>End Line</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#getLOC <em>LOC</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.structure.impl.AOMMethodImpl#isConstructor <em>Constructor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AOMMethodImpl extends AOMNamedElementImpl implements AOMMethod {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected AOMType type;

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
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMParameter> parameters;

	/**
	 * The default value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected static final String SIGNATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected String signature = SIGNATURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStaticReferer() <em>Static Referer</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticReferer()
	 * @generated
	 * @ordered
	 */
	protected EList<StaticMethodCall> staticReferer;

	/**
	 * The cached value of the '{@link #getOverriding() <em>Overriding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverriding()
	 * @generated
	 * @ordered
	 */
	protected AOMMethod overriding;

	/**
	 * The cached value of the '{@link #getOverridedBy() <em>Overrided By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverridedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMMethod> overridedBy;

	/**
	 * The cached value of the '{@link #getOwnedScope() <em>Owned Scope</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedScope()
	 * @generated
	 * @ordered
	 */
	protected AOMScope ownedScope;

	/**
	 * The cached value of the '{@link #getDynamicReferer() <em>Dynamic Referer</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicReferer()
	 * @generated
	 * @ordered
	 */
	protected EList<DynamicMethodCall> dynamicReferer;

	/**
	 * The default value of the '{@link #getMethodId() <em>Method Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodId()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethodId() <em>Method Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodId()
	 * @generated
	 * @ordered
	 */
	protected String methodId = METHOD_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartLine() <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartLine()
	 * @generated
	 * @ordered
	 */
	protected static final int START_LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStartLine() <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartLine()
	 * @generated
	 * @ordered
	 */
	protected int startLine = START_LINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndLine() <em>End Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndLine()
	 * @generated
	 * @ordered
	 */
	protected static final int END_LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEndLine() <em>End Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndLine()
	 * @generated
	 * @ordered
	 */
	protected int endLine = END_LINE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final int ABSTRACT_EFLAG = 1 << 8;

	/**
	 * The default value of the '{@link #getLOC() <em>LOC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLOC()
	 * @generated
	 * @ordered
	 */
	protected static final int LOC_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final int STATIC_EFLAG = 1 << 9;

	/**
	 * The default value of the '{@link #isConstructor() <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstructor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSTRUCTOR_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isConstructor() <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstructor()
	 * @generated
	 * @ordered
	 */
	protected static final int CONSTRUCTOR_EFLAG = 1 << 10;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AOMMethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StructurePackage.Literals.AOM_METHOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMType getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (AOMType)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StructurePackage.AOM_METHOD__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMType basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(AOMType newType, NotificationChain msgs) {
		AOMType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(AOMType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, StructurePackage.AOM_TYPE__REFERER, AOMType.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, StructurePackage.AOM_TYPE__REFERER, AOMType.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Object> getMeasuredDataSet() {
		if (measuredDataSet == null) {
			measuredDataSet = new EcoreEMap<String,Object>(AomPackage.Literals.STRING_TO_OBJECT, StringToObjectImpl.class, this, StructurePackage.AOM_METHOD__MEASURED_DATA_SET);
		}
		return measuredDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMScope getOwnedScope() {
		return ownedScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedScope(AOMScope newOwnedScope, NotificationChain msgs) {
		AOMScope oldOwnedScope = ownedScope;
		ownedScope = newOwnedScope;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__OWNED_SCOPE, oldOwnedScope, newOwnedScope);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwnedScope(AOMScope newOwnedScope) {
		if (newOwnedScope != ownedScope) {
			NotificationChain msgs = null;
			if (ownedScope != null)
				msgs = ((InternalEObject)ownedScope).eInverseRemove(this, StructurePackage.AOM_SCOPE__OWNER, AOMScope.class, msgs);
			if (newOwnedScope != null)
				msgs = ((InternalEObject)newOwnedScope).eInverseAdd(this, StructurePackage.AOM_SCOPE__OWNER, AOMScope.class, msgs);
			msgs = basicSetOwnedScope(newOwnedScope, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__OWNED_SCOPE, newOwnedScope, newOwnedScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicMethodCall> getDynamicReferer() {
		if (dynamicReferer == null) {
			dynamicReferer = new EObjectWithInverseResolvingEList<DynamicMethodCall>(DynamicMethodCall.class, this, StructurePackage.AOM_METHOD__DYNAMIC_REFERER, DynamicmodelPackage.DYNAMIC_METHOD_CALL__CALLEE);
		}
		return dynamicReferer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMethodId() {
		return methodId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodId(String newMethodId) {
		String oldMethodId = methodId;
		methodId = newMethodId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__METHOD_ID, oldMethodId, methodId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStartLine() {
		return startLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartLine(int newStartLine) {
		int oldStartLine = startLine;
		startLine = newStartLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__START_LINE, oldStartLine, startLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getEndLine() {
		return endLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndLine(int newEndLine) {
		int oldEndLine = endLine;
		endLine = newEndLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__END_LINE, oldEndLine, endLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return (eFlags & ABSTRACT_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = (eFlags & ABSTRACT_EFLAG) != 0;
		if (newAbstract) eFlags |= ABSTRACT_EFLAG; else eFlags &= ~ABSTRACT_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__ABSTRACT, oldAbstract, newAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getLOC() {
		return getEndLine() - getStartLine(); 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setLOC(int newLOC) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		return (eFlags & STATIC_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic) {
		boolean oldStatic = (eFlags & STATIC_EFLAG) != 0;
		if (newStatic) eFlags |= STATIC_EFLAG; else eFlags &= ~STATIC_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__STATIC, oldStatic, newStatic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstructor() {
		return (eFlags & CONSTRUCTOR_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructor(boolean newConstructor) {
		boolean oldConstructor = (eFlags & CONSTRUCTOR_EFLAG) != 0;
		if (newConstructor) eFlags |= CONSTRUCTOR_EFLAG; else eFlags &= ~CONSTRUCTOR_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__CONSTRUCTOR, oldConstructor, newConstructor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMParameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentWithInverseEList<AOMParameter>(AOMParameter.class, this, StructurePackage.AOM_METHOD__PARAMETERS, StructurePackage.AOM_PARAMETER__OWNER);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMClass getOwner() {
		if (eContainerFeatureID() != StructurePackage.AOM_METHOD__OWNER) return null;
		return (AOMClass)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(AOMClass newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, StructurePackage.AOM_METHOD__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(AOMClass newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != StructurePackage.AOM_METHOD__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, StructurePackage.AOM_CLASS__METHODS, AOMClass.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignature(String newSignature) {
		String oldSignature = signature;
		signature = newSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__SIGNATURE, oldSignature, signature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StaticMethodCall> getStaticReferer() {
		if (staticReferer == null) {
			staticReferer = new EObjectWithInverseResolvingEList<StaticMethodCall>(StaticMethodCall.class, this, StructurePackage.AOM_METHOD__STATIC_REFERER, StaticmodelPackage.STATIC_METHOD_CALL__CALLEE);
		}
		return staticReferer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMMethod getOverriding() {
		if (overriding != null && overriding.eIsProxy()) {
			InternalEObject oldOverriding = (InternalEObject)overriding;
			overriding = (AOMMethod)eResolveProxy(oldOverriding);
			if (overriding != oldOverriding) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StructurePackage.AOM_METHOD__OVERRIDING, oldOverriding, overriding));
			}
		}
		return overriding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMMethod basicGetOverriding() {
		return overriding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOverriding(AOMMethod newOverriding, NotificationChain msgs) {
		AOMMethod oldOverriding = overriding;
		overriding = newOverriding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__OVERRIDING, oldOverriding, newOverriding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverriding(AOMMethod newOverriding) {
		if (newOverriding != overriding) {
			NotificationChain msgs = null;
			if (overriding != null)
				msgs = ((InternalEObject)overriding).eInverseRemove(this, StructurePackage.AOM_METHOD__OVERRIDED_BY, AOMMethod.class, msgs);
			if (newOverriding != null)
				msgs = ((InternalEObject)newOverriding).eInverseAdd(this, StructurePackage.AOM_METHOD__OVERRIDED_BY, AOMMethod.class, msgs);
			msgs = basicSetOverriding(newOverriding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructurePackage.AOM_METHOD__OVERRIDING, newOverriding, newOverriding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMMethod> getOverridedBy() {
		if (overridedBy == null) {
			overridedBy = new EObjectWithInverseResolvingEList<AOMMethod>(AOMMethod.class, this, StructurePackage.AOM_METHOD__OVERRIDED_BY, StructurePackage.AOM_METHOD__OVERRIDING);
		}
		return overridedBy;
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
			case StructurePackage.AOM_METHOD__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, StructurePackage.AOM_TYPE__REFERER, AOMType.class, msgs);
				return basicSetType((AOMType)otherEnd, msgs);
			case StructurePackage.AOM_METHOD__PARAMETERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameters()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_METHOD__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((AOMClass)otherEnd, msgs);
			case StructurePackage.AOM_METHOD__STATIC_REFERER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStaticReferer()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_METHOD__OVERRIDING:
				if (overriding != null)
					msgs = ((InternalEObject)overriding).eInverseRemove(this, StructurePackage.AOM_METHOD__OVERRIDED_BY, AOMMethod.class, msgs);
				return basicSetOverriding((AOMMethod)otherEnd, msgs);
			case StructurePackage.AOM_METHOD__OVERRIDED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOverridedBy()).basicAdd(otherEnd, msgs);
			case StructurePackage.AOM_METHOD__OWNED_SCOPE:
				if (ownedScope != null)
					msgs = ((InternalEObject)ownedScope).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StructurePackage.AOM_METHOD__OWNED_SCOPE, null, msgs);
				return basicSetOwnedScope((AOMScope)otherEnd, msgs);
			case StructurePackage.AOM_METHOD__DYNAMIC_REFERER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDynamicReferer()).basicAdd(otherEnd, msgs);
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
			case StructurePackage.AOM_METHOD__TYPE:
				return basicSetType(null, msgs);
			case StructurePackage.AOM_METHOD__MEASURED_DATA_SET:
				return ((InternalEList<?>)getMeasuredDataSet()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_METHOD__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_METHOD__OWNER:
				return basicSetOwner(null, msgs);
			case StructurePackage.AOM_METHOD__STATIC_REFERER:
				return ((InternalEList<?>)getStaticReferer()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_METHOD__OVERRIDING:
				return basicSetOverriding(null, msgs);
			case StructurePackage.AOM_METHOD__OVERRIDED_BY:
				return ((InternalEList<?>)getOverridedBy()).basicRemove(otherEnd, msgs);
			case StructurePackage.AOM_METHOD__OWNED_SCOPE:
				return basicSetOwnedScope(null, msgs);
			case StructurePackage.AOM_METHOD__DYNAMIC_REFERER:
				return ((InternalEList<?>)getDynamicReferer()).basicRemove(otherEnd, msgs);
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
			case StructurePackage.AOM_METHOD__OWNER:
				return eInternalContainer().eInverseRemove(this, StructurePackage.AOM_CLASS__METHODS, AOMClass.class, msgs);
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
			case StructurePackage.AOM_METHOD__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case StructurePackage.AOM_METHOD__MEASURED_DATA_SET:
				if (coreType) return getMeasuredDataSet();
				else return getMeasuredDataSet().map();
			case StructurePackage.AOM_METHOD__PARAMETERS:
				return getParameters();
			case StructurePackage.AOM_METHOD__OWNER:
				return getOwner();
			case StructurePackage.AOM_METHOD__SIGNATURE:
				return getSignature();
			case StructurePackage.AOM_METHOD__STATIC_REFERER:
				return getStaticReferer();
			case StructurePackage.AOM_METHOD__OVERRIDING:
				if (resolve) return getOverriding();
				return basicGetOverriding();
			case StructurePackage.AOM_METHOD__OVERRIDED_BY:
				return getOverridedBy();
			case StructurePackage.AOM_METHOD__OWNED_SCOPE:
				return getOwnedScope();
			case StructurePackage.AOM_METHOD__DYNAMIC_REFERER:
				return getDynamicReferer();
			case StructurePackage.AOM_METHOD__METHOD_ID:
				return getMethodId();
			case StructurePackage.AOM_METHOD__START_LINE:
				return getStartLine();
			case StructurePackage.AOM_METHOD__END_LINE:
				return getEndLine();
			case StructurePackage.AOM_METHOD__ABSTRACT:
				return isAbstract();
			case StructurePackage.AOM_METHOD__LOC:
				return getLOC();
			case StructurePackage.AOM_METHOD__STATIC:
				return isStatic();
			case StructurePackage.AOM_METHOD__CONSTRUCTOR:
				return isConstructor();
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
			case StructurePackage.AOM_METHOD__TYPE:
				setType((AOMType)newValue);
				return;
			case StructurePackage.AOM_METHOD__MEASURED_DATA_SET:
				((EStructuralFeature.Setting)getMeasuredDataSet()).set(newValue);
				return;
			case StructurePackage.AOM_METHOD__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends AOMParameter>)newValue);
				return;
			case StructurePackage.AOM_METHOD__OWNER:
				setOwner((AOMClass)newValue);
				return;
			case StructurePackage.AOM_METHOD__SIGNATURE:
				setSignature((String)newValue);
				return;
			case StructurePackage.AOM_METHOD__STATIC_REFERER:
				getStaticReferer().clear();
				getStaticReferer().addAll((Collection<? extends StaticMethodCall>)newValue);
				return;
			case StructurePackage.AOM_METHOD__OVERRIDING:
				setOverriding((AOMMethod)newValue);
				return;
			case StructurePackage.AOM_METHOD__OVERRIDED_BY:
				getOverridedBy().clear();
				getOverridedBy().addAll((Collection<? extends AOMMethod>)newValue);
				return;
			case StructurePackage.AOM_METHOD__OWNED_SCOPE:
				setOwnedScope((AOMScope)newValue);
				return;
			case StructurePackage.AOM_METHOD__DYNAMIC_REFERER:
				getDynamicReferer().clear();
				getDynamicReferer().addAll((Collection<? extends DynamicMethodCall>)newValue);
				return;
			case StructurePackage.AOM_METHOD__METHOD_ID:
				setMethodId((String)newValue);
				return;
			case StructurePackage.AOM_METHOD__START_LINE:
				setStartLine((Integer)newValue);
				return;
			case StructurePackage.AOM_METHOD__END_LINE:
				setEndLine((Integer)newValue);
				return;
			case StructurePackage.AOM_METHOD__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case StructurePackage.AOM_METHOD__LOC:
				setLOC((Integer)newValue);
				return;
			case StructurePackage.AOM_METHOD__STATIC:
				setStatic((Boolean)newValue);
				return;
			case StructurePackage.AOM_METHOD__CONSTRUCTOR:
				setConstructor((Boolean)newValue);
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
			case StructurePackage.AOM_METHOD__TYPE:
				setType((AOMType)null);
				return;
			case StructurePackage.AOM_METHOD__MEASURED_DATA_SET:
				getMeasuredDataSet().clear();
				return;
			case StructurePackage.AOM_METHOD__PARAMETERS:
				getParameters().clear();
				return;
			case StructurePackage.AOM_METHOD__OWNER:
				setOwner((AOMClass)null);
				return;
			case StructurePackage.AOM_METHOD__SIGNATURE:
				setSignature(SIGNATURE_EDEFAULT);
				return;
			case StructurePackage.AOM_METHOD__STATIC_REFERER:
				getStaticReferer().clear();
				return;
			case StructurePackage.AOM_METHOD__OVERRIDING:
				setOverriding((AOMMethod)null);
				return;
			case StructurePackage.AOM_METHOD__OVERRIDED_BY:
				getOverridedBy().clear();
				return;
			case StructurePackage.AOM_METHOD__OWNED_SCOPE:
				setOwnedScope((AOMScope)null);
				return;
			case StructurePackage.AOM_METHOD__DYNAMIC_REFERER:
				getDynamicReferer().clear();
				return;
			case StructurePackage.AOM_METHOD__METHOD_ID:
				setMethodId(METHOD_ID_EDEFAULT);
				return;
			case StructurePackage.AOM_METHOD__START_LINE:
				setStartLine(START_LINE_EDEFAULT);
				return;
			case StructurePackage.AOM_METHOD__END_LINE:
				setEndLine(END_LINE_EDEFAULT);
				return;
			case StructurePackage.AOM_METHOD__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case StructurePackage.AOM_METHOD__LOC:
				setLOC(LOC_EDEFAULT);
				return;
			case StructurePackage.AOM_METHOD__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case StructurePackage.AOM_METHOD__CONSTRUCTOR:
				setConstructor(CONSTRUCTOR_EDEFAULT);
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
			case StructurePackage.AOM_METHOD__TYPE:
				return type != null;
			case StructurePackage.AOM_METHOD__MEASURED_DATA_SET:
				return measuredDataSet != null && !measuredDataSet.isEmpty();
			case StructurePackage.AOM_METHOD__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case StructurePackage.AOM_METHOD__OWNER:
				return getOwner() != null;
			case StructurePackage.AOM_METHOD__SIGNATURE:
				return SIGNATURE_EDEFAULT == null ? signature != null : !SIGNATURE_EDEFAULT.equals(signature);
			case StructurePackage.AOM_METHOD__STATIC_REFERER:
				return staticReferer != null && !staticReferer.isEmpty();
			case StructurePackage.AOM_METHOD__OVERRIDING:
				return overriding != null;
			case StructurePackage.AOM_METHOD__OVERRIDED_BY:
				return overridedBy != null && !overridedBy.isEmpty();
			case StructurePackage.AOM_METHOD__OWNED_SCOPE:
				return ownedScope != null;
			case StructurePackage.AOM_METHOD__DYNAMIC_REFERER:
				return dynamicReferer != null && !dynamicReferer.isEmpty();
			case StructurePackage.AOM_METHOD__METHOD_ID:
				return METHOD_ID_EDEFAULT == null ? methodId != null : !METHOD_ID_EDEFAULT.equals(methodId);
			case StructurePackage.AOM_METHOD__START_LINE:
				return startLine != START_LINE_EDEFAULT;
			case StructurePackage.AOM_METHOD__END_LINE:
				return endLine != END_LINE_EDEFAULT;
			case StructurePackage.AOM_METHOD__ABSTRACT:
				return ((eFlags & ABSTRACT_EFLAG) != 0) != ABSTRACT_EDEFAULT;
			case StructurePackage.AOM_METHOD__LOC:
				return getLOC() != LOC_EDEFAULT;
			case StructurePackage.AOM_METHOD__STATIC:
				return ((eFlags & STATIC_EFLAG) != 0) != STATIC_EDEFAULT;
			case StructurePackage.AOM_METHOD__CONSTRUCTOR:
				return ((eFlags & CONSTRUCTOR_EFLAG) != 0) != CONSTRUCTOR_EDEFAULT;
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
		if (baseClass == AOMTypedElement.class) {
			switch (derivedFeatureID) {
				case StructurePackage.AOM_METHOD__TYPE: return StructurePackage.AOM_TYPED_ELEMENT__TYPE;
				default: return -1;
			}
		}
		if (baseClass == MeasurableElement.class) {
			switch (derivedFeatureID) {
				case StructurePackage.AOM_METHOD__MEASURED_DATA_SET: return AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET;
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
		if (baseClass == AOMTypedElement.class) {
			switch (baseFeatureID) {
				case StructurePackage.AOM_TYPED_ELEMENT__TYPE: return StructurePackage.AOM_METHOD__TYPE;
				default: return -1;
			}
		}
		if (baseClass == MeasurableElement.class) {
			switch (baseFeatureID) {
				case AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET: return StructurePackage.AOM_METHOD__MEASURED_DATA_SET;
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
		result.append(" (signature: ");
		result.append(signature);
		result.append(", methodId: ");
		result.append(methodId);
		result.append(", startLine: ");
		result.append(startLine);
		result.append(", endLine: ");
		result.append(endLine);
		result.append(", abstract: ");
		result.append((eFlags & ABSTRACT_EFLAG) != 0);
		result.append(", static: ");
		result.append((eFlags & STATIC_EFLAG) != 0);
		result.append(", constructor: ");
		result.append((eFlags & CONSTRUCTOR_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}
	
	public void setTypeBinding(ITypeBinding typeBinding) {
		this.typeBinding = typeBinding;
	}

	public ITypeBinding getTypeBinding() {
		return typeBinding;
	}

	private ITypeBinding typeBinding;
	
	private HashMap<IVariableBinding, AOMParameter> varBinding2AOMParameter = new HashMap<IVariableBinding, AOMParameter>();
	public HashMap<IVariableBinding, AOMParameter> getVarBinding2AOMParameter()
	{
		return varBinding2AOMParameter;
	}

} //AOMMethodImpl
