/**
 * <copyright>
 * </copyright>
 *
 * $Id: StaticDependencyImpl.java,v 1.3 2010-12-27 16:36:30 igsong Exp $
 */
package kr.ac.kaist.se.aom.staticmodel.impl;

import kr.ac.kaist.se.aom.staticmodel.StaticDependency;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class StaticDependencyImpl extends EObjectImpl implements StaticDependency {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StaticDependencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StaticmodelPackage.Literals.STATIC_DEPENDENCY;
	}

} //StaticDependencyImpl
