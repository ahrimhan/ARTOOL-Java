/**
 * <copyright>
 * </copyright>
 *
 * $Id: DynamicmodelResourceFactoryImpl.java,v 1.1 2011-01-18 07:51:59 igsong Exp $
 */
package kr.ac.kaist.se.aom.dynamicmodel.util;

import java.util.Map;

import kr.ac.kaist.se.aom.staticmodel.util.StaticmodelResourceImpl;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave.XMLTypeInfo;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see kr.ac.kaist.se.aom.dynamicmodel.util.DynamicmodelResourceImpl
 * @generated
 */
public class DynamicmodelResourceFactoryImpl extends ResourceFactoryImpl {
	/**
	 * Creates an instance of the resource factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicmodelResourceFactoryImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	public Resource createResource(URI uri) {
		DynamicmodelResourceImpl resource = new DynamicmodelResourceImpl(uri);
		Map defaultLoadOptions = resource.getDefaultLoadOptions();

		defaultLoadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION,
				Boolean.TRUE);
		defaultLoadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA,
				Boolean.TRUE);
		defaultLoadOptions.put(XMLResource.OPTION_LAX_FEATURE_PROCESSING,
				Boolean.TRUE);
		defaultLoadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE,
				Boolean.TRUE);

		Map defaultSaveOptions = resource.getDefaultSaveOptions();

		defaultSaveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA,
				Boolean.TRUE);
		defaultSaveOptions
				.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		defaultSaveOptions.put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);

		defaultSaveOptions.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION,
				new XMLTypeInfo() {

					public boolean shouldSaveType(EClass objectType,
							EClassifier featureType, EStructuralFeature feature) {
						return objectType != featureType
								&& objectType != XMLTypePackage.Literals.ANY_TYPE;
					}

					public boolean shouldSaveType(EClass objectType,
							EClass featureType, EStructuralFeature feature) {
						return objectType != featureType;
					}
				});

		return resource;
	}

} //DynamicmodelResourceFactoryImpl
