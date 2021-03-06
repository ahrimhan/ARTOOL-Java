/**
 * <copyright>
 * </copyright>
 *
 * $Id: StructureResourceImpl.java,v 1.1 2011-01-18 07:51:59 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.structure.util.StructureResourceFactoryImpl
 * @generated
 */
public class StructureResourceImpl extends XMIResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public StructureResourceImpl(URI uri) {
		super(uri);
	}
	protected boolean useIDAttributes() {
		return true;
	}

	protected boolean useUUIDs() {
		return true;
	}
} //StructureResourceImpl
