/**
 * <copyright>
 * </copyright>
 *
 * $Id: AOMScopeItemProvider.java,v 1.5 2011-01-05 07:42:55 igsong Exp $
 */
package kr.ac.kaist.se.aom.structure.provider;


import java.util.Collection;
import java.util.List;

import kr.ac.kaist.se.aom.dynamicmodel.DynamicmodelFactory;
import kr.ac.kaist.se.aom.provider.AomEditPlugin;

import kr.ac.kaist.se.aom.staticmodel.StaticmodelFactory;
import kr.ac.kaist.se.aom.staticmodel.StaticDependency;
import kr.ac.kaist.se.aom.staticmodel.StaticmodelPackage;
import kr.ac.kaist.se.aom.structure.AOMScope;
import kr.ac.kaist.se.aom.structure.StructureFactory;
import kr.ac.kaist.se.aom.structure.StructurePackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link kr.ac.kaist.se.aom.structure.AOMScope} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AOMScopeItemProvider
	extends ItemProviderAdapter
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
	public AOMScopeItemProvider(AdapterFactory adapterFactory) {
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

			addStaticFieldAccessesPropertyDescriptor(object);
			addDynamicFieldAccessesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Static Field Accesses feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStaticFieldAccessesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMScope_staticFieldAccesses_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMScope_staticFieldAccesses_feature", "_UI_AOMScope_type"),
				 StructurePackage.Literals.AOM_SCOPE__STATIC_FIELD_ACCESSES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dynamic Field Accesses feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDynamicFieldAccessesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AOMScope_dynamicFieldAccesses_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AOMScope_dynamicFieldAccesses_feature", "_UI_AOMScope_type"),
				 StructurePackage.Literals.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES,
				 true,
				 false,
				 true,
				 null,
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
			childrenFeatures.add(StructurePackage.Literals.AOM_SCOPE__VARIABLES);
			childrenFeatures.add(StructurePackage.Literals.AOM_SCOPE__STATIC_METHOD_CALLS);
			childrenFeatures.add(StructurePackage.Literals.AOM_SCOPE__DYNAMIC_METHOD_CALLS);
			childrenFeatures.add(StructurePackage.Literals.AOM_SCOPE__STATIC_FIELD_ACCESSES);
			childrenFeatures.add(StructurePackage.Literals.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES);
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
	 * This returns AOMScope.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AOMScope"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_AOMScope_type");
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

		switch (notification.getFeatureID(AOMScope.class)) {
			case StructurePackage.AOM_SCOPE__VARIABLES:
			case StructurePackage.AOM_SCOPE__STATIC_METHOD_CALLS:
			case StructurePackage.AOM_SCOPE__DYNAMIC_METHOD_CALLS:
			case StructurePackage.AOM_SCOPE__STATIC_FIELD_ACCESSES:
			case StructurePackage.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES:
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
				(StructurePackage.Literals.AOM_SCOPE__VARIABLES,
				 StructureFactory.eINSTANCE.createAOMLocalVariable()));

		newChildDescriptors.add
			(createChildParameter
				(StructurePackage.Literals.AOM_SCOPE__STATIC_METHOD_CALLS,
				 StaticmodelFactory.eINSTANCE.createStaticMethodCall()));

		newChildDescriptors.add
			(createChildParameter
				(StructurePackage.Literals.AOM_SCOPE__DYNAMIC_METHOD_CALLS,
				 DynamicmodelFactory.eINSTANCE.createDynamicMethodCall()));

		newChildDescriptors.add
			(createChildParameter
				(StructurePackage.Literals.AOM_SCOPE__STATIC_FIELD_ACCESSES,
				 StaticmodelFactory.eINSTANCE.createStaticFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(StructurePackage.Literals.AOM_SCOPE__DYNAMIC_FIELD_ACCESSES,
				 DynamicmodelFactory.eINSTANCE.createDynamicFieldAccess()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return AomEditPlugin.INSTANCE;
	}

}
