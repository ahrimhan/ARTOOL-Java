package kr.ac.kaist.se.artool.staticmodeling;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.util.CommandExecutionOperation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;


public class StaticModel2AOMWizard extends Wizard {

	/**
	 * @generated
	 */
	private TransactionalEditingDomain myEditingDomain;
	private Combo combo;
	/**
	 * @generated
	 */
	private WizardNewFileCreationPage myFileCreationPage;

	/**
	 * @generated
	 */
	private List<IJavaProject>  mySelectedProjectList;

	/**
	 * @generated
	 */
	private IWorkbenchPage myWorkbenchPage;

	/**
	 * @generated
	 */
	private IStructuredSelection mySelection;

	/**
	 * @generated
	 */
	public StaticModel2AOMWizard(List<IJavaProject> selectedProjectList,
			IWorkbenchPage workbenchPage, IStructuredSelection selection,
			TransactionalEditingDomain editingDomain) {
		assert selectedProjectList != null : "Null selectedProjectList in CFGPartitioningWizard constructor"; //$NON-NLS-1$
		assert workbenchPage != null : "Null workbenchPage in CFGPartitioningWizard constructor"; //$NON-NLS-1$
		assert selection != null : "Null selection in CFGPartitioningWizard constructor"; //$NON-NLS-1$
		assert editingDomain != null : "Null editingDomain in CFGPartitioningWizard constructor"; //$NON-NLS-1$

		mySelectedProjectList = selectedProjectList;
		myWorkbenchPage = workbenchPage;
		mySelection = selection;
		myEditingDomain = editingDomain;

	}

	/**
	 * @generated
	 */
	public void addPages() {
		myFileCreationPage =  new WizardNewFileCreationPage("Transforming Java Projects to AOM",  mySelection)
		 {

			public void createControl(Composite parent) {
				super.createControl(parent);

				IContainer parentContainer = mySelectedProjectList.get(0).getProject();
				String originalFileName = mySelectedProjectList.get(0).getProject().getName();
				String fileExtension = ".aom"; //$NON-NLS-1$
				String fileName = originalFileName + fileExtension;
				for (int i = 1; parentContainer.getFile(new Path(fileName))
				.exists(); i++) {
					fileName = originalFileName + i + fileExtension;
				}
				setFileName(fileName);
			}

		 };
		 myFileCreationPage.setTitle("New AOM file");
		 myFileCreationPage.setDescription("Creating new AOM file which is transformed from Java Projects");
		 addPage(myFileCreationPage);
	}

	/**
	 * @generated
	 */

	public boolean performFinish() {
		final IFile chorFile = myFileCreationPage.createNewFile();
		try {
			chorFile.setCharset("UTF-8", new NullProgressMonitor()); //$NON-NLS-1$
		} catch (CoreException e) {
			e.printStackTrace();
			System.err.println("UTF-8 error");
		}
		List affectedFiles = new LinkedList();
		String path = chorFile.getLocation().removeLastSegments(1).toOSString();
		
		affectedFiles.add(chorFile);
		

		ResourceSet resourceSet = myEditingDomain.getResourceSet();
		final Resource aomResource = resourceSet
				.createResource(URI.createPlatformResourceURI(chorFile
						.getFullPath().toString(), true));

		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				myEditingDomain, "Transforming Java Projects to AOM", affectedFiles) { //$NON-NLS-1$
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				try {
					AbstractObjectModel aom = StaticModel2AOMTransformer.getInstance().transform2AOM(mySelectedProjectList, monitor);
					aomResource.getContents().add(aom);
				
					aomResource.save(null);
				} catch (IOException e) {
					return CommandResult.newErrorCommandResult("save failed");
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return CommandResult.newErrorCommandResult("save failed");
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return CommandResult.newErrorCommandResult("save failed");
				}
				
				return CommandResult.newOKCommandResult();
			}
		};

		try {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(myWorkbenchPage.getWorkbenchWindow().getShell());
			dialog.run(true, false, new CommandExecutionOperation(command));
			this.mySelectedProjectList.get(0).getProject().refreshLocal(2,
					new NullProgressMonitor());
			// IDE.openEditor(myWorkbenchPage, umlFile);
		} catch (CoreException e) {
			e.printStackTrace();
			System.err.println(
					"Unable to create model and diagram"); //$NON-NLS-1$
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			System.err.println(
					"Unable to create model and diagram"); //$NON-NLS-1$
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println(
					"Unable to create model and diagram"); //$NON-NLS-1$
			return false;
		}
		return true;
	}
	

}