/**
 * <copyright>
 * </copyright>
 *
 * $Id: AbstractObjectModelImpl.java,v 1.10 2011-02-07 08:36:33 igsong Exp $
 */
package kr.ac.kaist.se.aom.impl;

import java.util.Collection;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.AomPackage;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMExternalType;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Object Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.impl.AbstractObjectModelImpl#getMeasuredDataSet <em>Measured Data Set</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.impl.AbstractObjectModelImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link kr.ac.kaist.se.aom.impl.AbstractObjectModelImpl#getExternalTypes <em>External Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractObjectModelImpl extends EObjectImpl implements AbstractObjectModel {
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
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMClass> classes;

	/**
	 * The cached value of the '{@link #getExternalTypes() <em>External Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<AOMExternalType> externalTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractObjectModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AomPackage.Literals.ABSTRACT_OBJECT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Object> getMeasuredDataSet() {
		if (measuredDataSet == null) {
			measuredDataSet = new EcoreEMap<String,Object>(AomPackage.Literals.STRING_TO_OBJECT, StringToObjectImpl.class, this, AomPackage.ABSTRACT_OBJECT_MODEL__MEASURED_DATA_SET);
		}
		return measuredDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMClass> getClasses() {
		if (classes == null) {
			classes = new EObjectContainmentEList<AOMClass>(AOMClass.class, this, AomPackage.ABSTRACT_OBJECT_MODEL__CLASSES);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AOMExternalType> getExternalTypes() {
		if (externalTypes == null) {
			externalTypes = new EObjectContainmentEList<AOMExternalType>(AOMExternalType.class, this, AomPackage.ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES);
		}
		return externalTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AomPackage.ABSTRACT_OBJECT_MODEL__MEASURED_DATA_SET:
				return ((InternalEList<?>)getMeasuredDataSet()).basicRemove(otherEnd, msgs);
			case AomPackage.ABSTRACT_OBJECT_MODEL__CLASSES:
				return ((InternalEList<?>)getClasses()).basicRemove(otherEnd, msgs);
			case AomPackage.ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES:
				return ((InternalEList<?>)getExternalTypes()).basicRemove(otherEnd, msgs);
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
			case AomPackage.ABSTRACT_OBJECT_MODEL__MEASURED_DATA_SET:
				if (coreType) return getMeasuredDataSet();
				else return getMeasuredDataSet().map();
			case AomPackage.ABSTRACT_OBJECT_MODEL__CLASSES:
				return getClasses();
			case AomPackage.ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES:
				return getExternalTypes();
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
			case AomPackage.ABSTRACT_OBJECT_MODEL__MEASURED_DATA_SET:
				((EStructuralFeature.Setting)getMeasuredDataSet()).set(newValue);
				return;
			case AomPackage.ABSTRACT_OBJECT_MODEL__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends AOMClass>)newValue);
				return;
			case AomPackage.ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES:
				getExternalTypes().clear();
				getExternalTypes().addAll((Collection<? extends AOMExternalType>)newValue);
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
			case AomPackage.ABSTRACT_OBJECT_MODEL__MEASURED_DATA_SET:
				getMeasuredDataSet().clear();
				return;
			case AomPackage.ABSTRACT_OBJECT_MODEL__CLASSES:
				getClasses().clear();
				return;
			case AomPackage.ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES:
				getExternalTypes().clear();
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
			case AomPackage.ABSTRACT_OBJECT_MODEL__MEASURED_DATA_SET:
				return measuredDataSet != null && !measuredDataSet.isEmpty();
			case AomPackage.ABSTRACT_OBJECT_MODEL__CLASSES:
				return classes != null && !classes.isEmpty();
			case AomPackage.ABSTRACT_OBJECT_MODEL__EXTERNAL_TYPES:
				return externalTypes != null && !externalTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AbstractObjectModelImpl
