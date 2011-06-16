package kr.ac.kaist.se.artool.dynamicprofile.viewer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.dynamicmodel.provider.DynamicmodelItemProviderAdapterFactory;
import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;
import kr.ac.kaist.se.aom.provider.AomItemProviderAdapterFactory;
import kr.ac.kaist.se.aom.staticmodel.provider.StaticmodelItemProviderAdapterFactory;
import kr.ac.kaist.se.aom.structure.provider.StructureItemProviderAdapterFactory;
import kr.ac.kaist.se.artool.dynamicprofile.DynamicProfile2AOMTransformer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

public class DynamicProfile2AOMAction {

	private static class Transformer implements IRunnableWithProgress {
		
		
		private AbstractObjectModel aom;
		private Vector<AOMMethodCallItem> methodCallItems;

		public Transformer(AbstractObjectModel aom, Vector<AOMMethodCallItem> methodCallItems)
		{
			this.aom = aom;
			this.methodCallItems = methodCallItems;
		}
		
		@Override
		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException {
			DynamicProfile2AOMTransformer transformer = DynamicProfile2AOMTransformer.getInstance();
			monitor.beginTask("Transforming Dynamic Profile to AOM", 100);
			try
			{
				SubProgressMonitor subMonitor1 = new SubProgressMonitor(monitor, 75);
				transformer.transform(aom, methodCallItems, subMonitor1);
				SubProgressMonitor subMonitor2 = new SubProgressMonitor(monitor, 25);
				Resource resource = aom.eResource();
				try
				{
					Map<String, Object> options = new HashMap<String, Object>();
					options.put(XMIResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
					options.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList<Object>());
					
					resource.save(null);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			finally
			{
				monitor.done();
			}
		}
	}
	
	public static ComposedAdapterFactory adapterFactory;
	
	static
	{
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new AomItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new StructureItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new StaticmodelItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DynamicmodelItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	

	
	public static void run(Shell shell, Vector<AOMMethodCallItem> methodCallItems, String absoluteFilePath)  {
		

		System.err.println(absoluteFilePath);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(absoluteFilePath));
		
		
		ResourceSet resourceSet = new ResourceSetImpl();

		
		resourceSet.getAdapterFactories().add(adapterFactory);
		try
		{
			Resource resource = resourceSet.getResource(
					URI.createFileURI(absoluteFilePath), true);
			if( resource  == null || resource.getContents().size() == 0 )
			{
				MessageDialog.openError(shell, "Error",
				"Model file loading failed");
				return;
			}
			AbstractObjectModel aom = (AbstractObjectModel)resource.getContents().get(0);
			if (aom == null) {
				MessageDialog.openError(shell, "Error",
						"Model file loading failed");
				return;
			}
			
			ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(shell);
			monitorDialog.run(true, false, new Transformer(aom, methodCallItems));
			
		} catch (WrappedException ex) {
			System.err.println("Unable to load resource: " + file.getFullPath().toString());
			ex.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
