package kr.ac.kaist.se.artool.engine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import kr.ac.kaist.se.aom.AbstractObjectModel;
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

public class BootStrapAction implements IObjectActionDelegate {

	public BootStrapAction() {
		// TODO Auto-generated constructor stub
	}

	private IStructuredSelection selection;
	private IFile selectedFile; 

	@Override
	public void run(IAction action) {
		
		TransactionalEditingDomain myEditingDomain = GMFEditingDomainFactory.INSTANCE
		.createEditingDomain();
		
		ResourceSet resourceSet = myEditingDomain.getResourceSet();
		final Resource aomResource = resourceSet
				.getResource(URI.createPlatformResourceURI(selectedFile
						.getFullPath().toString(), true), true);
		List affectedFiles = new LinkedList();
		affectedFiles.add(selectedFile);
		
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				myEditingDomain, "Measuring Metric Suites", affectedFiles) { //$NON-NLS-1$
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				try {
					
					AbstractObjectModel aom = (AbstractObjectModel) aomResource.getContents().get(0);
					ARToolMain.getInstance().run(aom, shell);
					
				} catch (IOException e) {
					return CommandResult.newErrorCommandResult("save failed");
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
			return ;
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println(
					"Unable to create model and diagram"); //$NON-NLS-1$
			return ;
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
