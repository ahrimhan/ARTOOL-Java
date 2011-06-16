package kr.ac.kaist.se.artool.staticmodeling;

import java.util.List;
import java.util.Vector;

import kr.ac.kaist.se.artool.Activator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class StaticModel2AOMAction implements IObjectActionDelegate {

	private Shell shell;
	private List<IJavaProject> projectList;
	private IStructuredSelection selection;
	private IWorkbenchPart myPart;
	/**
	 * Constructor for Action1.
	 */
	public StaticModel2AOMAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		myPart = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if( projectList != null && !projectList.isEmpty()  ) 
		{
			TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
			.createEditingDomain();
			Wizard wizard = new StaticModel2AOMWizard(projectList, myPart
					.getSite().getPage(), selection, editingDomain);
			IDialogSettings pluginDialogSettings = Activator.getDefault().getDialogSettings();
			
			IDialogSettings initDiagramFileSettings = pluginDialogSettings
					.getSection("InisDiagramFile"); //$NON-NLS-1$
			if (initDiagramFileSettings == null) {
				initDiagramFileSettings = pluginDialogSettings
						.addNewSection("InisDiagramFile"); //$NON-NLS-1$
			}
			wizard.setDialogSettings(initDiagramFileSettings);
			wizard.setForcePreviousAndNextButtons(false);
			wizard.setWindowTitle("Detect implied scenarios");

			WizardDialog dialog = new WizardDialog(myPart.getSite().getShell(),
					wizard);
			dialog.create();
			dialog.getShell().setSize(Math.max(500, dialog.getShell().getSize().x),
					600);
			dialog.open();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if( selection instanceof IStructuredSelection )
		{
			this.selection = (IStructuredSelection)selection;
			for(Object obj : this.selection.toArray() )
			{
				if( projectList == null )
				{
					projectList = new Vector<IJavaProject>();
				}
				else
				{
					projectList.clear();
				}
				if( obj instanceof IJavaProject )
				{
					projectList.add((IJavaProject)obj);
				}
				else if( obj instanceof IProject )
				{
					IProject project = (IProject)obj;
					try {
						if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
							IJavaProject javaProject = JavaCore.create(project);
							projectList.add(javaProject);
						}
					} catch (CoreException e) {
					}
				}
			}
		}
	}
}
