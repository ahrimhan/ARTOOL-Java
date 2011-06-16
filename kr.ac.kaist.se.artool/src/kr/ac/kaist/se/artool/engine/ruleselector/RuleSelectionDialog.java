package kr.ac.kaist.se.artool.engine.ruleselector;

import java.text.NumberFormat;
import java.util.List;

import kr.ac.kaist.se.artool.engine.StatusLogger;
import kr.ac.kaist.se.artool.engine.rules.AbstractRule;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class RuleSelectionDialog extends Dialog {
	private TableViewer viewer;

	private List<AbstractRule> rules;
	private AbstractRule selectedRule = null;

	protected RuleSelectionDialog(Shell parentShell, List<AbstractRule> rules) {
		super(parentShell);
		this.rules = rules;
		nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(8);
		nf.setMinimumFractionDigits(8);
	}

	@Override
	protected Button createButton(Composite parent, int id, String label,
			boolean defaultButton) {
		// TODO Auto-generated method stub
		return super.createButton(parent, id, label, defaultButton);
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		return super.createButtonBar(parent);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		super.createButtonsForButtonBar(parent);
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		return super.createContents(parent);
	}

	@Override
	protected void cancelPressed() {
		// TODO Auto-generated method stub
		super.cancelPressed();
	}
	
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Export Settings");
        newShell.setSize(1000, 500);
    }

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = ( Composite )super.createDialogArea(parent);
		
		try 
		{
			createTableViewer(composite);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

		return composite;
	}
	private NumberFormat nf;
	private void createTableViewer(Composite parent) {

		
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(rules);
		//		// Make the selection available to other views
		//		getSite().setSelectionProvider(viewer);
		// Set the sorter for the table

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
		

	}

	@Override
	protected void setButtonLayoutData(Button button) {
		// TODO Auto-generated method stub
		super.setButtonLayoutData(button);
	}

	@Override
	protected void setButtonLayoutFormData(Button button) {
		// TODO Auto-generated method stub
		super.setButtonLayoutFormData(button);
	}

	@Override
	protected boolean isResizable() {
		// TODO Auto-generated method stub
		return super.isResizable();
	}

	private static final String[] statusAttributes = { "fitness2", "fitness3", "fitness_static", "StaticBoth", "DynamicBoth", "MPCDBoth", "MSC", "LCOM2", "LCOM3" };

	
	private void createColumns(final TableViewer viewer) {
		String[] main_titles = { "Rule name", "Status" };
		int[] bounds = { 100, 300 };

		TableViewerColumn col = createTableViewerColumn(main_titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AbstractRule rule = (AbstractRule)element;
				return rule.getName();
			}
		});

		col = createTableViewerColumn(main_titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AbstractRule rule = (AbstractRule) element;
				return rule.getStatus();
			}
		});
		
		
		for( int i = 0; i < statusAttributes.length ; i++ )
		{
			col = createTableViewerColumn(statusAttributes[i], 100, 2);
			final String selectedAttr = statusAttributes[i];
			col.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					AbstractRule rule = (AbstractRule) element;
					BasicEMap<String, Float> p = StatusLogger.getInstance().getTrialPhase(rule);
					if( p == null ) return "-";
					if( p.get(selectedAttr) == null ) return "-";
					return nf.format(p.get(selectedAttr));
				}
			});
		}
		
		for( int i = 0; i < statusAttributes.length ; i++ )
		{
			col = createTableViewerColumn("¡â" + statusAttributes[i], 100, 2);
			final String selectedAttr = statusAttributes[i];
			col.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					AbstractRule rule = (AbstractRule) element;
					BasicEMap<String, Float> p = StatusLogger.getInstance().getTrialPhase(rule);
					if( p == null ) return "-";
					if( p.get(selectedAttr) == null ) return "-";
					float curValue =  p.get(selectedAttr);
					float originalValue =  StatusLogger.getInstance().getOriginalPhase().get(selectedAttr);
					float previousValue =  StatusLogger.getInstance().getPreviousPhase().get(selectedAttr);
					return nf.format(curValue - previousValue);
				}
			});
		}
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;

	}

	@Override
	protected void okPressed() {
		setSelectedRule((AbstractRule) ((IStructuredSelection)viewer.getSelection()).getFirstElement());
		super.okPressed();
	}



	public void setSelectedRule(AbstractRule selectedRule) {
		this.selectedRule = selectedRule;
	}



	public AbstractRule getSelectedRule() {
		return selectedRule;
	}
}
