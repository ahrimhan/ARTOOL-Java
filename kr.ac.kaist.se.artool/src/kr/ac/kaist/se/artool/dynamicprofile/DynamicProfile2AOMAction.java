package kr.ac.kaist.se.artool.dynamicprofile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import kr.ac.kaist.se.aom.profiler.AOMFieldAccessItem;
import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;

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
	private IWorkbenchPart targetPart;

	@Override
	public void run(IAction action) {
		String workingDir = selectedFile.getProject().getLocation().makeAbsolute().toOSString() + File.separator + "dynamic_profile";
		String methodCallLogFile = workingDir + File.separator + "DynamicMethodCallLog.txt";
		String fieldAccessLogFile = workingDir + File.separator + "DynamicFieldAccessLog.txt";
		
		Vector<AOMMethodCallItem> methodCallItems = new Vector<AOMMethodCallItem>();
		Vector<AOMFieldAccessItem> fieldAccessItems = new Vector<AOMFieldAccessItem>();
		
		try
		{
			FileReader fr = new FileReader(methodCallLogFile);
			BufferedReader br = new BufferedReader(fr);
			
			for(AOMMethodCallItem mci = AOMMethodCallItem.getInstance(br); mci != null;
				mci = AOMMethodCallItem.getInstance(br))
			{
				methodCallItems.add(mci);
			}
			br.close();
			fr.close();
			
			 fr = new FileReader(fieldAccessLogFile);
			 br = new BufferedReader(fr);
			
			for(AOMFieldAccessItem fai = AOMFieldAccessItem.getInstance(br); fai != null;
				fai = AOMFieldAccessItem.getInstance(br))
			{
				fieldAccessItems.add(fai);
			}
			br.close();
			fr.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return;
		}
		
		DynamicProfile2AOMActionHelper.run(shell, methodCallItems, fieldAccessItems, selectedFile);
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
		this.targetPart = targetPart;
	}


}
