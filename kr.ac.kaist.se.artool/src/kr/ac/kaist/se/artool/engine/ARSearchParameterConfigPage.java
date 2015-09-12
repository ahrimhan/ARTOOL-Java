package kr.ac.kaist.se.artool.engine;

import java.util.LinkedList;
import java.util.List;

import kr.ac.kaist.se.artool.search.ARSearchMain;
import kr.ac.kaist.se.artool.search.ARSearchMain.CandidateSelectionType;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class ARSearchParameterConfigPage extends WizardPage {

	protected ARSearchParameterConfigPage(String pageName) {
		super(pageName);
		super.setTitle("Configure search-based refactoring experiments");
		super.setDescription("Set up your experiments. Combinations of each experimental condition are executed.");
	}
	
	
	
//	private Combo fitnessFunction;
	private int fitnessSelectionBits = 0;
	private String[] fitnessFunctionItems = {
			"EPM (Entitiy Placement Metric)",
//			"QMOOD-Reusability",
//			"QMOOD-Flexibility",
//			"QMOOD-Understandability",
			"MSC (Message Similiarity Cohesion)",
			"MPC (Message Passing Coupling)",
			"Connectivity"
	};
	
	
	private int searchTechniqueBits = 0;
	
	private String[] searchTechniqueItems = {
			"First-ascent hill-climbing",
			"Steepest-ascent hill-climbing",
			"Low-temperature simulated annealing"

			/*
			"Multiple-restart hill-climbing",
			*/
	};

	
	private int candidateSelectionBits = 0;
	
	private String[] candidateSelectionItems = {
			"Random",
			//"External Dependency",
			"Delta Table"
	};
	
	private Text maxIterationCount;
	private Text maxCandidateCount;
	private Text saMaxPermissibleIdelIteration;
	
	//private Button useDeltaTableButton;

	@Override
	public void createControl(Composite parent) {
		// create the composite to hold the widgets
		Composite globalComposite = new Composite(parent, SWT.NONE);
		fitnessSelectionBits = 0;
		// create the desired layout for this wizard page
		GridLayout gl1 = new GridLayout();
		gl1.numColumns = 1;
		globalComposite.setLayout(gl1);
		
		
		GridLayout gl = new GridLayout();
		int ncol = 2;
		gl.numColumns = ncol;
		
		
		Group composite = new Group(globalComposite, SWT.SHADOW_IN);
		composite.setText("Experiment Conditions");
		composite.setLayout(gl);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		// create the widgets and their grid data objects
		// Date of travel
//		new Label(composite, SWT.NONE).setText("Fitness Function:");
//		fitnessFunction = new Combo(composite, SWT.BORDER | SWT.READ_ONLY | SWT.DROP_DOWN);
//		fitnessFunction.setItems(fitnessFunctionItems);
//		fitnessFunction.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	    Group group1 = new Group(composite, SWT.SHADOW_IN);
		GridData gridDataGroup1 = new GridData(GridData.FILL_HORIZONTAL);
		gridDataGroup1.horizontalSpan = ncol;
		group1.setLayoutData(gridDataGroup1);
	    group1.setText("Fitness Function:");
	    group1.setLayout(new RowLayout(SWT.VERTICAL));
	    for( int i = 0; i < fitnessFunctionItems.length; i++ )
	    {
	    	final int j = i;
	    	Button btn = new Button(group1, SWT.CHECK);
	    	btn.setText(fitnessFunctionItems[i]);
	    	btn.addSelectionListener(new SelectionListener(){
	    		@Override
	    		public void widgetDefaultSelected(SelectionEvent e) {
	    			fitnessSelectionBits ^= 1 << j; 
	    		}
	    		
	    		@Override
	    		public void widgetSelected(SelectionEvent e) {
	    			fitnessSelectionBits ^= 1 << j;
	    		}
	    	});
	    	btn.setSelection(true);
	    	
	    	fitnessSelectionBits |= 1 << j;
	    }
	    
	    
	    
	    
	    group1 = new Group(composite, SWT.SHADOW_IN);
		gridDataGroup1 = new GridData(GridData.FILL_HORIZONTAL);
		gridDataGroup1.horizontalSpan = ncol;
		group1.setLayoutData(gridDataGroup1);
		
	    group1.setText("Search Technique:");
	    group1.setLayout(new RowLayout(SWT.VERTICAL));
	    for( int i = 0; i < searchTechniqueItems.length; i++ )
	    {
	    	final int j = i;
	    	Button btn = new Button(group1, SWT.CHECK);
	    	btn.setText(searchTechniqueItems[i]);
	    	btn.addSelectionListener(new SelectionListener(){
	    		@Override
	    		public void widgetDefaultSelected(SelectionEvent e) {
	    			searchTechniqueBits ^= 1 << j; 
	    		}
	    		
	    		@Override
	    		public void widgetSelected(SelectionEvent e) {
	    			searchTechniqueBits ^= 1 << j;
	    		}
	    	});
	    	btn.setSelection(true);
	    	
	    	searchTechniqueBits |= 1 << j;
	    }
	    
	    
//		new Label(composite, SWT.NONE).setText("Candidate Selection:");
//		candidateSelection = new Combo(composite, SWT.BORDER | SWT.READ_ONLY | SWT.DROP_DOWN);
//		candidateSelection.setItems(candidateSelectionItems);
//		candidateSelection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//	    candidateSelection.select(0);
	    
	    group1 = new Group(composite, SWT.SHADOW_IN);
		gridDataGroup1 = new GridData(GridData.FILL_HORIZONTAL);
		gridDataGroup1.horizontalSpan = ncol;
		group1.setLayoutData(gridDataGroup1);
		
	    group1.setText("Candidate Selection:");
	    group1.setLayout(new RowLayout(SWT.VERTICAL));
	    for( int i = 0; i < candidateSelectionItems.length; i++ )
	    {
	    	final int j = i;
	    	Button btn = new Button(group1, SWT.CHECK);
	    	btn.setText(candidateSelectionItems[i]);
	    	btn.addSelectionListener(new SelectionListener(){
	    		@Override
	    		public void widgetDefaultSelected(SelectionEvent e) {
	    			candidateSelectionBits ^= 1 << j; 
	    		}
	    		
	    		@Override
	    		public void widgetSelected(SelectionEvent e) {
	    			candidateSelectionBits ^= 1 << j;
	    		}
	    	});
	    	btn.setSelection(true);
	    	
	    	candidateSelectionBits |= 1 << j;
	    }
	    
	    
	    
	    
	    composite = new Group(globalComposite, SWT.SHADOW_IN);
		composite.setText("Experiment Parameter");
		composite.setLayout(gl);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		
		new Label(composite, SWT.NONE).setText("Max Iteraction Count:");
		maxIterationCount = new Text(composite, SWT.BORDER | SWT.SINGLE);
		maxIterationCount.setMessage("Set Max-Iteration Counts. Default is 1000");
		maxIterationCount.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		maxIterationCount.addListener(SWT.Verify, new Listener() {
	        public void handleEvent(Event e) {
	          String string = e.text;
	          char[] chars = new char[string.length()];
	          string.getChars(0, chars.length, chars, 0);
	          for (int i = 0; i < chars.length; i++) {
	            if (!('0' <= chars[i] && chars[i] <= '9')) {
	              e.doit = false;
	              return;
	            }
	          }
	        }
	      });
		
		
		new Label(composite, SWT.NONE).setText("Max Candidates Count:");
		maxCandidateCount = new Text(composite, SWT.BORDER | SWT.SINGLE);
		maxCandidateCount.setMessage("Set Max-Candidate Counts. Default is 100");
		maxCandidateCount.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		maxCandidateCount.addListener(SWT.Verify, new Listener() {
	        public void handleEvent(Event e) {
	          String string = e.text;
	          char[] chars = new char[string.length()];
	          string.getChars(0, chars.length, chars, 0);
	          for (int i = 0; i < chars.length; i++) {
	            if (!('0' <= chars[i] && chars[i] <= '9')) {
	              e.doit = false;
	              return;
	            }
	          }
	        }
	      });
		
		
		new Label(composite, SWT.NONE).setText("(SA) Permissible Idle Iteration Count:");
		saMaxPermissibleIdelIteration = new Text(composite, SWT.BORDER | SWT.SINGLE);
		saMaxPermissibleIdelIteration.setMessage("Set Max Permissible Idle Iterations. Default is 500");
		saMaxPermissibleIdelIteration.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		saMaxPermissibleIdelIteration.addListener(SWT.Verify, new Listener() {
	        public void handleEvent(Event e) {
	          String string = e.text;
	          char[] chars = new char[string.length()];
	          string.getChars(0, chars.length, chars, 0);
	          for (int i = 0; i < chars.length; i++) {
	            if (!('0' <= chars[i] && chars[i] <= '9')) {
	              e.doit = false;
	              return;
	            }
	          }
	        }
	      });
		
		
		/*
		useDeltaTableButton = new Button(composite, SWT.CHECK);
		useDeltaTableButton.setText("Use Delta Table Acceleration?");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol;
		useDeltaTableButton.setLayoutData(gd);
		useDeltaTableButton.setSelection(true);
		*/
		
		//fitnessFunction.select(0);
		//searchTechnique.select(0);
		
		
		setControl(globalComposite);
		
	}

	
	public List<ARSearchMain.FitnessType> getFitnessType()
	{
		ARSearchMain.FitnessType type;
		List<ARSearchMain.FitnessType> typeList = new LinkedList<ARSearchMain.FitnessType>();

		if( (fitnessSelectionBits & (1 << 0)) != 0 )
		{
			type = ARSearchMain.FitnessType.EPM;
			typeList.add(type);
		}
		
//		if( (fitnessSelectionBits & (1 << 1)) != 0 )
//		{
//			type = ARSearchMain.FitnessType.REUSABILITY;
//			typeList.add(type);
//		}
//		
//		if( (fitnessSelectionBits & (1 << 2)) != 0 )
//		{
//			type = ARSearchMain.FitnessType.FLEXIBILITY;
//			typeList.add(type);
//		}
//		
//		if( (fitnessSelectionBits & (1 << 3)) != 0 )
//		{
//			type = ARSearchMain.FitnessType.UNDERSTANDABILITY;
//			typeList.add(type);
//		}
		
		if( (fitnessSelectionBits & (1 << 1)) != 0 )
		{
			type = ARSearchMain.FitnessType.MSC;
			typeList.add(type);
		}
		
		if( (fitnessSelectionBits & (1 << 2)) != 0 )
		{
			type = ARSearchMain.FitnessType.MPC;
			typeList.add(type);
		}

		if( (fitnessSelectionBits & (1 << 3)) != 0 )
		{
			type = ARSearchMain.FitnessType.CONNECTIVITY;
			typeList.add(type);
		}
		
		return typeList;
	}
	
	public List<ARSearchMain.SearchTechType> getSearchTechType()
	{
		ARSearchMain.SearchTechType type;
		List<ARSearchMain.SearchTechType> typeList = new LinkedList<ARSearchMain.SearchTechType>();
		
		
		if( (searchTechniqueBits & (1 << 0)) != 0 )
		{
			type = ARSearchMain.SearchTechType.SELECT_FIRST;
			typeList.add(type);
		}
		
		if( (searchTechniqueBits & (1 << 1)) != 0 )
		{
			type = ARSearchMain.SearchTechType.SELECT_BEST;
			typeList.add(type);
		}

		
		if( (searchTechniqueBits & (1 << 2)) != 0 )
		{
			type = ARSearchMain.SearchTechType.SIMULATED_ANNEALING;
			typeList.add(type);
		}
		
		
		if( (searchTechniqueBits & (1 << 3)) != 0 )
		{
			type = ARSearchMain.SearchTechType.SELECT_FIRST_RESTART;
			typeList.add(type);
		}
	
		
		return typeList;
	}
	
	public List<ARSearchMain.CandidateSelectionType> getCandidateSelectionType()
	{
		ARSearchMain.CandidateSelectionType type;

		List<ARSearchMain.CandidateSelectionType> typeList = new LinkedList<ARSearchMain.CandidateSelectionType>();
		
		
		if( (candidateSelectionBits & (1 << 0)) != 0 )
		{
			type = CandidateSelectionType.RANDOM;
			typeList.add(type);
		}
		
		if( (candidateSelectionBits & (1 << 1)) != 0 )
		{
			type = CandidateSelectionType.DELTA;
			typeList.add(type);
		}
		
		
		return typeList;
	}


	public int getMaxCandidateCount() {
		int ret = 100;
		
		try
		{
			ret = Integer.parseInt(maxCandidateCount.getText());
		}
		catch(Exception e)
		{
			ret = 100;
		}
		
		
		return ret;
	}


	public int getMaxIterationCount() {
		int ret = 1000;
		
		try
		{
			ret = Integer.parseInt(maxIterationCount.getText());
		}
		catch(Exception e)
		{
			ret = 1000;
		}
		
		
		return ret;	
	}


	public int getSAPermissibleIdleIteration() {
		int ret = 500;
		
		try
		{
			ret = Integer.parseInt(saMaxPermissibleIdelIteration.getText());
		}
		catch(Exception e)
		{
			ret = 500;
		}
		
		
		return ret;	
	}
}
