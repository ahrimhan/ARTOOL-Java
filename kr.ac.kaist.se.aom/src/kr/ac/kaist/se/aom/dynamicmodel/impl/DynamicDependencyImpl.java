/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicDependencyImpl.java,v 1.3 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel.impl;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicDependency;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Dependency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class DynamicDependencyImpl extends EObjectImpl implements DynamicDependency {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicDependencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DynamicmodelPackage.Literals.DYNAMIC_DEPENDENCY;
	}

} //DynamicDependencyImpl
