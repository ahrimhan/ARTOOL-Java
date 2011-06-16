package kr.ac.kaist.se.artool.dynamicprofile.viewer;


import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class RefreshAction implements IViewActionDelegate {

	@Override
	public void run(IAction action) {
		viewPart.refresh();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}
	
	private DynamicProfileViewer viewPart;

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		if( view instanceof DynamicProfileViewer )
		{
			viewPart = (DynamicProfileViewer)view;
		}
	}

}
