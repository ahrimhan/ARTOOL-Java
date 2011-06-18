package kr.ac.kaist.se.artool.dynamicprofile;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class DynamicProfile2AOMAction implements IObjectActionDelegate {

	public DynamicProfile2AOMAction() {
		// TODO Auto-generated constructor stub
	}

	private IStructuredSelection selection;
	private IFile selectedFile; 

	@Override
	public void run(IAction action) {
		
		DynamicProfile2AOMActionHelper.run(shell, methodCallItems, fieldAccessItems, file);
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
