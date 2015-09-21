package kr.ac.kaist.se.artool.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.search.fitness.ConnectivityEngine;
import kr.ac.kaist.se.artool.search.fitness.EPMEngine;
import kr.ac.kaist.se.artool.search.fitness.MPCEngine;
import kr.ac.kaist.se.artool.search.fitness.NativeEPMEngineAdapter;
import kr.ac.kaist.se.artool.util.CommandExecutionOperation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class GettingInfoAction implements IObjectActionDelegate {

	public GettingInfoAction() {
		// TODO Auto-generated constructor stub
	}

	private IStructuredSelection selection;
	private IFile selectedFile; 

	@Override
	public void run(IAction action) {
//		WizardDialog dialog = new WizardDialog(shell, new ARSearchWizard(selectedFile));\
		
//		dialog.open();
		
		

		TransactionalEditingDomain myEditingDomain = GMFEditingDomainFactory.INSTANCE
		.createEditingDomain();
		ResourceSet resourceSet = myEditingDomain.getResourceSet();
		final Resource aomResource = resourceSet
				.getResource(URI.createPlatformResourceURI(selectedFile
						.getFullPath().toString(), true), true);

		
		List<IFile> affectedFileList = new LinkedList<IFile>();
		affectedFileList.add(selectedFile);
		
		
		final AbstractObjectModel aom = (AbstractObjectModel) aomResource.getContents().get(0);
		
		SystemEntitySet ses = new SystemEntitySet(aom);
		
		System.out.println("Class#, "+aom.getClasses().size());
		System.out.println("Entity#, " + ses.entities.size());
		System.out.println("Method#, " + ses.methods.size());
		System.out.println("Field#, "+ ses.fields.size());
		

		
		
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				myEditingDomain, "Measuring Metric Suites", affectedFileList) { //$NON-NLS-1$
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				try {
					ConnectivityEngine ce = new ConnectivityEngine(aom);
					System.out.println("Connectivity, " + ce.calculate());
					
					NativeEPMEngineAdapter nepme = new NativeEPMEngineAdapter(ses);
					System.out.println("Native EPM, " + nepme.calculate());
					
					MPCEngine mpce = new MPCEngine(aom);
					System.out.println("MPC, " + mpce.calculate());			
				} finally {
					monitor.done();
				}
				
				return CommandResult.newOKCommandResult();
			}
		};
		
		
		
		
		
		try {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
			
			dialog.run(true, false, new CommandExecutionOperation(command));
//			this.mySelectedProjectList.get(0).getProject().refreshLocal(2,
//					new NullProgressMonitor());
			// IDE.openEditor(myWorkbenchPage, umlFile);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			System.err.println(
					"Unable to create model and diagram"); //$NON-NLS-1$
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println(
					"Unable to create model and diagram"); //$NON-NLS-1$
		}
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if( selection instanceof IStructuredSelection )
		{
			this.selection = (IStructuredSelection)selection;
			selectedFile = (IFile)this.selection.getFirstElement();
		}
	}

	private Shell shell;
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

}
