/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kr.ac.kaist.se.aom.dynamicmodel.provider;


import java.util.Collection;
import java.util.List;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess;
import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

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
 * This is the item provider adapter for a {@link kr.ac.kaist.se.aom.dynamicmodel.DynamicFieldAccess} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DynamicFieldAccessItemProvider
	extends DynamicDependencyItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicFieldAccessItemProvider(AdapterFactory adapterFactory) {
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

			addAccessingScopePropertyDescriptor(object);
			addAccessedFieldPropertyDescriptor(object);
			addLineNumberPropertyDescriptor(object);
			addColumnNumberPropertyDescriptor(object);
			addFileNamePropertyDescriptor(object);
			addStaticFieldAccessPropertyDescriptor(object);
			addIsReaderPropertyDescriptor(object);
			addIsWriterPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Accessing Scope feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAccessingScopePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicFieldAccess_accessingScope_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicFieldAccess_accessingScope_feature", "_UI_DynamicFieldAccess_type"),
				 DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS__ACCESSING_SCOPE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Accessed Field feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAccessedFieldPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicFieldAccess_accessedField_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicFieldAccess_accessedField_feature", "_UI_DynamicFieldAccess_type"),
				 DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS__ACCESSED_FIELD,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Line Number feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLineNumberPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicFieldAccess_lineNumber_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicFieldAccess_lineNumber_feature", "_UI_DynamicFieldAccess_type"),
				 DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS__LINE_NUMBER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Column Number feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addColumnNumberPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicFieldAccess_columnNumber_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicFieldAccess_columnNumber_feature", "_UI_DynamicFieldAccess_type"),
				 DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS__COLUMN_NUMBER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the File Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFileNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicFieldAccess_fileName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicFieldAccess_fileName_feature", "_UI_DynamicFieldAccess_type"),
				 DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS__FILE_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Static Field Access feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStaticFieldAccessPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicFieldAccess_staticFieldAccess_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicFieldAccess_staticFieldAccess_feature", "_UI_DynamicFieldAccess_type"),
				 DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS__STATIC_FIELD_ACCESS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Reader feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsReaderPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicFieldAccess_isReader_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicFieldAccess_isReader_feature", "_UI_DynamicFieldAccess_type"),
				 DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS__IS_READER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Writer feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsWriterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicFieldAccess_isWriter_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicFieldAccess_isWriter_feature", "_UI_DynamicFieldAccess_type"),
				 DynamicmodelPackage.Literals.DYNAMIC_FIELD_ACCESS__IS_WRITER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns DynamicFieldAccess.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DynamicFieldAccess"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((DynamicFieldAccess)object).getFileName();
		return label == null || label.length() == 0 ?
			getString("_UI_DynamicFieldAccess_type") :
			getString("_UI_DynamicFieldAccess_type") + " " + label;
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

		switch (notification.getFeatureID(DynamicFieldAccess.class)) {
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__LINE_NUMBER:
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__COLUMN_NUMBER:
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__FILE_NAME:
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_READER:
			case DynamicmodelPackage.DYNAMIC_FIELD_ACCESS__IS_WRITER:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

}
