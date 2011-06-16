/**
 * <copyright>
 * </copyright>
 *
 * $Id: MeasurableElementImpl.java,v 1.1 2011-01-18 13:56:36 igsong Exp $
 */
package kr.ac.kaist.se.aom.impl;

import kr.ac.kaist.se.aom.AomPackage;
import kr.ac.kaist.se.aom.MeasurableElement;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurable Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kr.ac.kaist.se.aom.impl.MeasurableElementImpl#getMeasuredDataSet <em>Measured Data Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurableElementImpl extends EObjectImpl implements MeasurableElement {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurableElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AomPackage.Literals.MEASURABLE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Object> getMeasuredDataSet() {
		if (measuredDataSet == null) {
			measuredDataSet = new EcoreEMap<String,Object>(AomPackage.Literals.STRING_TO_OBJECT, StringToObjectImpl.class, this, AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET);
		}
		return measuredDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET:
				return ((InternalEList<?>)getMeasuredDataSet()).basicRemove(otherEnd, msgs);
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
			case AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET:
				if (coreType) return getMeasuredDataSet();
				else return getMeasuredDataSet().map();
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
			case AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET:
				((EStructuralFeature.Setting)getMeasuredDataSet()).set(newValue);
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
			case AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET:
				getMeasuredDataSet().clear();
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
			case AomPackage.MEASURABLE_ELEMENT__MEASURED_DATA_SET:
				return measuredDataSet != null && !measuredDataSet.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MeasurableElementImpl
