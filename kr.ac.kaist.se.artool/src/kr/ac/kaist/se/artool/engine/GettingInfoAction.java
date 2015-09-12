package kr.ac.kaist.se.artool.engine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import kr.ac.kaist.se.aom.AbstractObjectModel;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
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
		
		
		AbstractObjectModel aom = (AbstractObjectModel) aomResource.getContents().get(0);
		
		SystemEntitySet ses = new SystemEntitySet(aom);
		
		System.err.println("CEMF: "+aom.getClasses().size() + "," + ses.entities.size() + "," + ses.methods.size() + "," + ses.fields.size() + ")");
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
