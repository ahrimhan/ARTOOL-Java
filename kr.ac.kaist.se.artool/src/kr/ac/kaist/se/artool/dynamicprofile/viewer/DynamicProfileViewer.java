package kr.ac.kaist.se.artool.dynamicprofile.viewer;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.util.Vector;

import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;
import kr.ac.kaist.se.artool.dynamicprofile.DynamicProfileClassData;
import kr.ac.kaist.se.artool.dynamicprofile.DynamicProfileDataSet;
import kr.ac.kaist.se.artool.dynamicprofile.DynamicProfileMethodData;
import kr.ac.kaist.se.artool.dynamicprofile.connector.ObjectBroadcastListener;
import kr.ac.kaist.se.artool.dynamicprofile.connector.ObjectBroadcaster;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;

public class DynamicProfileViewer extends ViewPart implements ObjectBroadcastListener{

	private DynamicProfileDataSet dataSet;
	private Vector<AOMMethodCallItem> itemList;
	private TreeViewer viewer;
	private Display display;

	public DynamicProfileViewer() {
		dataSet = new DynamicProfileDataSet();
		itemList = new Vector<AOMMethodCallItem>();
	}

	@Override
	public void dispose() {
		
		display = null;
		ObjectBroadcaster.getInstance().removeListener(AOMMethodCallItem.class, this);
		super.dispose();
	}

	@Override
	public void init(IViewSite site) throws PartInitException
	{
		super.init(site);
		ObjectBroadcaster.getInstance().addListener(AOMMethodCallItem.class, this);
	}

	public class DynamicProfileDataSetContentProvider implements ILazyTreeContentProvider
	{
		TreeViewer viewer;
		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			this.viewer = (TreeViewer) viewer;
		}

		@Override
		public void updateElement(Object parent, int index) {
			if( parent instanceof DynamicProfileDataSet )
			{
				DynamicProfileDataSet pp = (DynamicProfileDataSet)parent;
				viewer.replace(parent, index, pp.getItem(index));
				viewer.setHasChildren(pp.getItem(index), true);
			}
			else if( parent instanceof DynamicProfileClassData )
			{
				DynamicProfileClassData pp = (DynamicProfileClassData)parent;
				viewer.replace(parent, index, pp.getItem(index));
				viewer.setHasChildren(pp.getItem(index), false);
			}
		}

		@Override
		public void updateChildCount(Object element, int currentChildCount) {
			if( element instanceof DynamicProfileDataSet )
			{
				DynamicProfileDataSet pp = (DynamicProfileDataSet)element;
				viewer.setChildCount(element, pp.size());
			}
			else if( element instanceof DynamicProfileClassData )
			{
				DynamicProfileClassData pp = (DynamicProfileClassData)element;
				viewer.setChildCount(element, pp.size());
			}
		}

		@Override
		public Object getParent(Object element) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}



	@Override
	public void createPartControl(Composite parent) {
		createViewer(parent);
	}
	

	private void createViewer(Composite parent) {
		display = parent.getDisplay();
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER | SWT.VIRTUAL);
		viewer.setUseHashlookup(true);
		createColumns(parent, viewer);
		Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		

		viewer.setContentProvider(new DynamicProfileDataSetContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(dataSet);
		// Make the selection available to other views
		getSite().setSelectionProvider(viewer);
		// Set the sorter for the table

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
		
		Object obj = new Object(){
			public String toString()
			{
				return "hello";
			}
		};
		
		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				if(event.getSelection() instanceof IStructuredSelection)
				{
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					Object obj = selection.getFirstElement();
					if( obj  instanceof DynamicProfileMethodData )
					{
						DynamicProfileMethodData data = (DynamicProfileMethodData)obj;
						for( IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects() )
						{
							try {
								if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
									IJavaProject javaProject = JavaCore.create(project);
									IType type = javaProject.findType(data.callerClassName);
									if( type != null )
									{
										ICompilationUnit cu = type.getCompilationUnit();
										IEditorPart editor = JavaUI.openInEditor(cu, true, true);
										AbstractDecoratedTextEditor javaEditor = (AbstractDecoratedTextEditor)editor;
										IDocumentProvider provider= javaEditor.getDocumentProvider();
										IDocument document= provider.getDocument(editor.getEditorInput());
										try {

											int start= document.getLineOffset(data.callerLineNumber - 1);
											javaEditor.selectAndReveal(start, 0);

											IWorkbenchPage page= editor.getSite().getPage();
											page.activate(editor);

										} catch (BadLocationException x) {
											// ignore
										}
							
										break;
									}
								}
							} catch (CoreException e) {
							}
						}
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					}
				}
			}
			
		});
		
		final FileTransfer fileTransfer = FileTransfer.getInstance();
		DropTargetListener dropListener = new DropTargetListener()
		{

			@Override
			public void dragEnter(DropTargetEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dragLeave(DropTargetEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dragOver(DropTargetEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void drop(DropTargetEvent event) {
				if( fileTransfer.isSupportedType(event.currentDataType) )
				{
					String[] files = (String[])event.data;
					if( files != null && files.length > 0 && files[0].endsWith(".aom") )
					{
						DynamicProfile2AOMAction.run(getSite().getShell(), getMethodCallItems(), files[0]);
					}
				}
			}

			@Override
			public void dropAccept(DropTargetEvent event) {
				// TODO Auto-generated method stub
				
			}

		};
		
		viewer.addDropSupport(DND.DROP_MOVE, new Transfer[]{ fileTransfer }, dropListener);
	}

	// This will create the columns for the table
	private void createColumns(final Composite parent, final TreeViewer viewer) {
		String[] titles = { "Caller Class/Method", "Callee Class/Method", "Count" };
		int[] bounds = { 300, 300, 50 };

		TreeViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				String ret = "";
				if( element instanceof DynamicProfileClassData )
				{
					DynamicProfileClassData p = (DynamicProfileClassData) element;
					ret = p.getCallerClass();
				}
				else if( element instanceof DynamicProfileMethodData )
				{
					DynamicProfileMethodData p = (DynamicProfileMethodData) element;
					ret = p.callerMethodName + "(" + p.callerFileName + ":" + p.callerLineNumber +")";
				}
				return ret;
			}
		});

		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				String ret = "";
				if( element instanceof DynamicProfileClassData )
				{
					DynamicProfileClassData p = (DynamicProfileClassData) element;
					ret = p.getCalleeDynamicClass();
				}
				else if( element instanceof DynamicProfileMethodData )
				{
					DynamicProfileMethodData p = (DynamicProfileMethodData) element;
					ret = p.calleeMethodName;
				}
				return ret;
			}
		});
		

		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				long ret = 0;
				if( element instanceof DynamicProfileClassData )
				{
					DynamicProfileClassData p = (DynamicProfileClassData) element;
					ret = p.getCount();
				}
				else if( element instanceof DynamicProfileMethodData )
				{
					DynamicProfileMethodData p = (DynamicProfileMethodData) element;
					ret = p.count;
				}
				return Long.toString(ret);
			}
		});
	}

	private TreeViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TreeViewerColumn viewerColumn = new TreeViewerColumn(viewer,
				SWT.NONE);
		final TreeColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}


	public void refresh() {
		viewer.refresh();
	}

	public void clearDataSet() {
		dataSet.clear();
		viewer.refresh();
		itemList.clear();
	}

	@Override
	public void broadcastedObject(AOMMethodCallItem item) {
		final DynamicProfileClassData data  = dataSet.addItem(item);
		itemList.add(item);
	}
	
	public Vector<AOMMethodCallItem> getMethodCallItems()
	{
		return itemList;
	}
}
