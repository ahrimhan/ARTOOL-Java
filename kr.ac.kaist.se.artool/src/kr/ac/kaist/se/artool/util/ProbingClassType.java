package kr.ac.kaist.se.artool.util;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ProbingClassType implements IObjectActionDelegate {

	private Shell shell;
	private List<IProject> projectList;
	private IStructuredSelection selection;
	private IWorkbenchPart myPart;
	/**
	 * Constructor for Action1.
	 */
	public ProbingClassType() {
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
		MessageDialog.openInformation(
			shell,
			"Clicked class has following type",
			selection.getFirstElement().getClass().getName());
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if( selection instanceof IStructuredSelection )
		{
			this.selection = (IStructuredSelection)selection;
		}
	}
}
