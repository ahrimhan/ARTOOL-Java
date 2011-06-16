/**
 * <copyright>
 * </copyright>
 *
 * $Id: AomResourceImpl.java,v 1.1 2011-01-18 07:51:59 igsong Exp $
 */
package kr.ac.kaist.se.aom.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.util.AomResourceFactoryImpl
 * @generated
 */
public class AomResourceImpl extends XMIResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public AomResourceImpl(URI uri) {
		super(uri);
	}
	protected boolean useIDAttributes() {
		return true;
	}

	protected boolean useUUIDs() {
		return true;
	}
} //AomResourceImpl
