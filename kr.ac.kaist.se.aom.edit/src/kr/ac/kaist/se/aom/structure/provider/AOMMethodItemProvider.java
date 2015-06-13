/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kr.ac.kaist.se.aom.structure.provider;


import java.util.Collection;
import java.util.List;

import kr.ac.kaist.se.aom.AomPackage;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.aom.structure.StructureFactory;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link kr.ac.kaist.se.aom.structure.AOMMethod} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AOMMethodItemProvider
	extends AOMNamedElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AOMMethodItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addTypePropertyDescriptor(object);
			addMeasuredDataSetPropertyDescriptor(object);
			addIndexPropertyDescriptor(object);
			addOccurrencePropertyDescriptor(object);
			addPublicEntityPropertyDescriptor(object);
			addSignaturePropertyDescriptor(object);
			addStaticRefererPropertyDescriptor(object);
			addOverridingPropertyDescriptor(object);
			addOverridedByPropertyDescriptor(object);
			addDynamicRefererPropertyDescriptor(object);
			addMethodIdPropertyDescriptor(object);
			addStartLinePropertyDescriptor(object);
			addEndLinePropertyDescriptor(object);
			addAbstractPropertyDescriptor(object);
			addLOCPropertyDescriptor(object);
			addStaticPropertyDescriptor(object);
			addConstructorPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMTypedElement_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMTypedElement_type_feature", "_UI_AOMTypedElement_type"),
				 StructurePackage.Literals.AOM_TYPED_ELEMENT__TYPE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Measured Data Set feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMeasuredDataSetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MeasurableElement_measuredDataSet_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MeasurableElement_measuredDataSet_feature", "_UI_MeasurableElement_type"),
				 AomPackage.Literals.MEASURABLE_ELEMENT__MEASURED_DATA_SET,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Index feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIndexPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IndexedElement_index_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IndexedElement_index_feature", "_UI_IndexedElement_type"),
				 StructurePackage.Literals.INDEXED_ELEMENT__INDEX,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Occurrence feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOccurrencePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMEntity_occurrence_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMEntity_occurrence_feature", "_UI_AOMEntity_type"),
				 StructurePackage.Literals.AOM_ENTITY__OCCURRENCE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Public Entity feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPublicEntityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMEntity_publicEntity_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMEntity_publicEntity_feature", "_UI_AOMEntity_type"),
				 StructurePackage.Literals.AOM_ENTITY__PUBLIC_ENTITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Signature feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSignaturePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_signature_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_signature_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__SIGNATURE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Static Referer feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStaticRefererPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_staticReferer_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_staticReferer_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__STATIC_REFERER,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Overriding feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOverridingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_overriding_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_overriding_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__OVERRIDING,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Overrided By feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOverridedByPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_overridedBy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_overridedBy_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__OVERRIDED_BY,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dynamic Referer feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDynamicRefererPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_dynamicReferer_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_dynamicReferer_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__DYNAMIC_REFERER,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Method Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMethodIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_methodId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_methodId_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__METHOD_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Start Line feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStartLinePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_startLine_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_startLine_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__START_LINE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the End Line feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEndLinePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_endLine_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_endLine_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__END_LINE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Abstract feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAbstractPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_abstract_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_abstract_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__ABSTRACT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the LOC feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLOCPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_LOC_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_LOC_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__LOC,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Static feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStaticPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_static_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_static_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__STATIC,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Constructor feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConstructorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMMethod_constructor_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMMethod_constructor_feature", "_UI_AOMMethod_type"),
				 StructurePackage.Literals.AOM_METHOD__CONSTRUCTOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(StructurePackage.Literals.AOM_METHOD__PARAMETERS);
			childrenFeatures.add(StructurePackage.Literals.AOM_METHOD__OWNED_SCOPE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns AOMMethod.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AOMMethod"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AOMMethod)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AOMMethod_type") :
			getString("_UI_AOMMethod_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(AOMMethod.class)) {
			case StructurePackage.AOM_METHOD__MEASURED_DATA_SET:
			case StructurePackage.AOM_METHOD__INDEX:
			case StructurePackage.AOM_METHOD__OCCURRENCE:
			case StructurePackage.AOM_METHOD__PUBLIC_ENTITY:
			case StructurePackage.AOM_METHOD__SIGNATURE:
			case StructurePackage.AOM_METHOD__METHOD_ID:
			case StructurePackage.AOM_METHOD__START_LINE:
			case StructurePackage.AOM_METHOD__END_LINE:
			case StructurePackage.AOM_METHOD__ABSTRACT:
			case StructurePackage.AOM_METHOD__LOC:
			case StructurePackage.AOM_METHOD__STATIC:
			case StructurePackage.AOM_METHOD__CONSTRUCTOR:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case StructurePackage.AOM_METHOD__PARAMETERS:
			case StructurePackage.AOM_METHOD__OWNED_SCOPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(StructurePackage.Literals.AOM_METHOD__PARAMETERS,
				 StructureFactory.eINSTANCE.createAOMParameter()));

		newChildDescriptors.add
			(createChildParameter
				(StructurePackage.Literals.AOM_METHOD__OWNED_SCOPE,
				 StructureFactory.eINSTANCE.createAOMScope()));
	}

}
