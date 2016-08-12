package kr.ac.kaist.se.artool.search;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.prefs.BackingStoreException;

import kr.ac.kaist.se.artool.Activator;
import kr.ac.kaist.se.artool.search.ARSearchMain.CandidateSelectionType;

public class ARSearchParameterConfigPage extends WizardPage {

	protected ARSearchParameterConfigPage(String pageName) {
		super(pageName);
		super.setTitle("Configure search-based refactoring experiments");
		super.setDescription("Set up your experiments. Combinations of each experimental condition are executed.");
	}
	
	
	
	private String[] fitnessFunctionItems = {
			"EPM (Entitiy Placement Metric)",
			"MSC (Message Similiarity Cohesion)",
			"MPC (Message Passing Coupling)",
			"Connectivity"
//			"QMOOD-Reusability",
//			"QMOOD-Flexibility",
//			"QMOOD-Understandability",
	};
			
	
	private String[] searchTechniqueItems = {
			"First-ascent hill-climbing",
			"Steepest-ascent hill-climbing",
			"Low-temperature simulated annealing"

			/*
			"Multiple-restart hill-climbing",
			*/
	};
	
	private String[] candidateSelectionItems = {
			"Delta Table",
			"Exhaustive"
	};
	
	private int fitnessSelectionBits = 0;
	private int searchTechniqueBits = 0;
	private int candidateSelectionBits = 0;
	private int multiObjectiveBits = 0;

	private String maxIterationCountStr;
	private String maxCandidateCountStr;
	private String saMaxPermissibleIdelIterationStr;
	private Group multiObjectiveGroup;
	
	public void saveSettings() {
		// saves plugin preferences at the workspace level
		IEclipsePreferences prefs =
				InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID); // does all the above behind the scenes

		prefs.putInt("fitnessSelectionBits", fitnessSelectionBits);
		prefs.putInt("searchTechniqueBits", searchTechniqueBits);
		prefs.putInt("candidateSelectionBits", candidateSelectionBits);
		prefs.putInt("multiObjectiveBits", multiObjectiveBits);
		prefs.put("maxIterationCount", maxIterationCountStr);
		prefs.put("maxCandidateCount", maxCandidateCountStr);
		prefs.put("saMaxPermissibleIdelIteration", saMaxPermissibleIdelIterationStr);
		try {
			prefs.flush();
		} catch(BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public void loadSettings() {
		IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);

		fitnessSelectionBits = prefs.getInt("fitnessSelectionBits", 0);
		searchTechniqueBits = prefs.getInt("searchTechniqueBits", 0);
		candidateSelectionBits = prefs.getInt("candidateSelectionBits", 0);
		multiObjectiveBits = prefs.getInt("multiObjectiveBits", 0);
		maxIterationCountStr = prefs.get("maxIterationCount", "100");
		maxCandidateCountStr = prefs.get("maxCandidateCount", "1000");
		saMaxPermissibleIdelIterationStr = prefs.get("saMaxPermissibleIdelIteration", "500");
	}
	
	private static void setEnabledChildren(Control ctrl, boolean enabled)
	{
		ctrl.setEnabled(enabled);
		
		if (ctrl instanceof Composite) {
			Composite comp = (Composite) ctrl;
			for (Control c : comp.getChildren())
				setEnabledChildren(c, enabled);
		}	
	}
	
	@Override
	public void createControl(Composite parent) {
		Group group1;
		GridData gridDataGroup1;
		gridDataGroup1 = new GridData(GridData.FILL_HORIZONTAL);
		gridDataGroup1.horizontalSpan = 1;
		
		GridData gridDataGroupV1;
		gridDataGroupV1 = new GridData(GridData.FILL_BOTH);
		gridDataGroupV1.horizontalSpan = 2;
		
		GridData gridDataGroup2;
		gridDataGroup2 = new GridData(GridData.FILL_HORIZONTAL);
		gridDataGroup2.horizontalSpan = 2;
		
		
		GridData gridDataGroupV2;
		gridDataGroupV2 = new GridData(GridData.FILL_BOTH);
		gridDataGroupV2.horizontalSpan = 2;
		
		// create the composite to hold the widgets
		Composite globalComposite = new Composite(parent, SWT.NONE);
		// create the desired layout for this wizard page
		GridLayout gl1 = new GridLayout();
		gl1.numColumns = 1;
		parent.setLayout(gl1);
		globalComposite.setLayout(gl1);
		globalComposite.setLayoutData(gridDataGroupV1);
		
		
		GridLayout gl2 = new GridLayout();
		int ncol = 2;
		gl2.numColumns = ncol;
		
		
		Group composite = new Group(globalComposite, SWT.SHADOW_IN);
		composite.setText("Experiment Conditions");
		composite.setLayout(gl1);
		composite.setLayoutData(gridDataGroupV1);
			
		
		

//		Group singleValueFitness = new Group(composite, SWT.SHADOW_IN);
		
		
	    group1 = new Group(composite, SWT.SHADOW_IN);
		group1.setLayoutData(gridDataGroupV1);
	    group1.setText("Fitness Function:");
	    group1.setLayout(gl2);
	    for( int i = 0; i < fitnessFunctionItems.length; i++ )
	    {
	    	final int j = i;
	    	Button btn = new Button(group1, SWT.CHECK);
	    	btn.setText(fitnessFunctionItems[i]);
	    	if( fitnessFunctionItems.length % 2 == 1 &&
	    			(i+1) == fitnessFunctionItems.length )
	    	{
	    		btn.setLayoutData(gridDataGroup2);
	    	}
	    	else
	    	{
		    	btn.setLayoutData(gridDataGroup1);
	    	}
	    	
	    	if( (fitnessSelectionBits & (1 << j)) != 0 )	    	
	    		btn.setSelection(true);
	    	
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
	    }
	    

    	
	    Button paretoBtn = new Button(group1, SWT.CHECK);
	    paretoBtn.setText("Pareto Optimization");
	    paretoBtn.setLayoutData(gridDataGroup2);


    		
	    multiObjectiveGroup = new Group(group1, SWT.SHADOW_IN);
	    

		gridDataGroup2 = new GridData(GridData.FILL_HORIZONTAL);
		gridDataGroup2.horizontalSpan = 2;
//		group1.setLayoutData(gridDataGroup1);
//	    group1.setText("Pareto front:");
		multiObjectiveGroup.setLayoutData(gridDataGroup2);
		multiObjectiveGroup.setLayout(gl2);
    	paretoBtn.setSelection(multiObjectiveBits != 0);

	    for( int i = 0; i < fitnessFunctionItems.length; i++ )
	    {
	    	final int j = i;
	    	Button btn = new Button(multiObjectiveGroup, SWT.CHECK);
	    	btn.setText(fitnessFunctionItems[i]);
	    	btn.setLayoutData(gridDataGroup1);
	    	if( (multiObjectiveBits & (1 << j)) != 0 )
	    	{
	    		btn.setSelection(true);
	    	}
	    	
	    	
	    	btn.addSelectionListener(new SelectionListener(){
	    		@Override
	    		public void widgetDefaultSelected(SelectionEvent e) {
	    			multiObjectiveBits ^= 1 << j; 
	    		}
	    		
	    		@Override
	    		public void widgetSelected(SelectionEvent e) {
	    			multiObjectiveBits ^= 1 << j;
	    		}
	    	});
	    }
	    
    	if( (fitnessSelectionBits & (1 << 31)) != 0 )
    	{
    		paretoBtn.setSelection(true);
    		setEnabledChildren(multiObjectiveGroup, true);
    	}
    	else
    	{
    		paretoBtn.setSelection(false);
    		setEnabledChildren(multiObjectiveGroup, false);
    	}
    	
    	paretoBtn.addSelectionListener(new SelectionListener(){
    		@Override
    		public void widgetDefaultSelected(SelectionEvent e) {
    			fitnessSelectionBits ^= 1 << 31; 
    			setEnabledChildren(multiObjectiveGroup, !multiObjectiveGroup.getEnabled());
    		}
    		
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			fitnessSelectionBits ^= 1 << 31;
    			setEnabledChildren(multiObjectiveGroup, !multiObjectiveGroup.getEnabled());
    		}
    	});
	    

	    
	    group1 = new Group(composite, SWT.SHADOW_IN);
		gridDataGroup1 = new GridData(GridData.FILL_HORIZONTAL);
		gridDataGroup1.horizontalSpan = 1;
		group1.setLayoutData(gridDataGroup1);
		
	    group1.setText("Search Technique:");
	    group1.setLayout(new RowLayout(SWT.VERTICAL));
	    for( int i = 0; i < searchTechniqueItems.length; i++ )
	    {
	    	final int j = i;
	    	Button btn = new Button(group1, SWT.CHECK);
	    	btn.setText(searchTechniqueItems[i]);
	    	
	    	if( (searchTechniqueBits & (1 << j)) != 0 )	    	
	    		btn.setSelection(true);
	    	
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
	    }
	    
	    
	    group1 = new Group(composite, SWT.SHADOW_IN);
		gridDataGroup1 = new GridData(GridData.FILL_HORIZONTAL);
		gridDataGroup1.horizontalSpan = 1;
		group1.setLayoutData(gridDataGroup1);
		
	    group1.setText("Candidate Selection:");
	    group1.setLayout(gl2);
	    for( int i = 0; i < candidateSelectionItems.length; i++ )
	    {
	    	final int j = i;
	    	Button btn = new Button(group1, SWT.CHECK);
	    	btn.setText(candidateSelectionItems[i]);
	    	
	    	if( (candidateSelectionBits & (1 << j)) != 0 )	    	
	    		btn.setSelection(true);
	    	
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
	    	

	    }
	    
	    
	    composite = new Group(globalComposite, SWT.SHADOW_IN);
		composite.setText("Experiment Parameter");
		composite.setLayout(gl1);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		VerifyListener verifyListener = new VerifyListener()
		{
			@Override
			public void verifyText(VerifyEvent e) {
				String typedString = e.text;
				char[] typedChars = new char[typedString.length()];
				typedString.getChars(0, typedChars.length, typedChars, 0);
				
				System.err.println("typedString:"+typedString);
				
				for (int i = 0; i < typedChars.length; i++) {
					if (!(('0' <= typedChars[i] && typedChars[i] <= '9'))) {
						if( !(i == 0 && e.start == 0 && typedChars[i] == '-') )
						{
							e.doit = false;
							return;
						}
					}
				}	
			}	
		};
		
		
		new Label(composite, SWT.NONE).setText("Max Iteraction Count:");
		Text maxIterationCountText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		maxIterationCountText.setMessage("Set Max-Iteration Counts. Default is 1000");
		maxIterationCountText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		maxIterationCountText.setText(maxIterationCountStr);
		maxIterationCountText.addVerifyListener(verifyListener);
		maxIterationCountText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.widget;
				maxIterationCountStr = text.getText();
			}
		});
		
		new Label(composite, SWT.NONE).setText("Max Candidates Count:");
		Text maxCandidateCountText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		maxCandidateCountText.setMessage("Set Max-Candidate Counts. Default is 100");
		maxCandidateCountText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		maxCandidateCountText.setText(maxCandidateCountStr);
		maxCandidateCountText.addVerifyListener(verifyListener);
		maxCandidateCountText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.widget;
				maxCandidateCountStr = text.getText();	
			}
		});
		
		new Label(composite, SWT.NONE).setText("(SA) Permissible Idle Iteration Count:");
		Text saMaxPermissibleIdelIterationText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		saMaxPermissibleIdelIterationText.setMessage("Set Max Permissible Idle Iterations. Default is 500");
		saMaxPermissibleIdelIterationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		saMaxPermissibleIdelIterationText.setText(saMaxPermissibleIdelIterationStr);
		saMaxPermissibleIdelIterationText.addVerifyListener(verifyListener);
		saMaxPermissibleIdelIterationText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.widget;
				saMaxPermissibleIdelIterationStr = text.getText();
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
		
		if( (fitnessSelectionBits & (1 << 31)) != 0 )
		{
			type = ARSearchMain.FitnessType.PARETO_COMPOSITE;
			typeList.add(type);
		}
		
		return typeList;
	}
	
	public List<ARSearchMain.FitnessType> getMultiFitnessType()
	{
		ARSearchMain.FitnessType type;
		List<ARSearchMain.FitnessType> typeList = new LinkedList<ARSearchMain.FitnessType>();

		if( (multiObjectiveBits & (1 << 0)) != 0 )
		{
			type = ARSearchMain.FitnessType.EPM;
			typeList.add(type);
		}
		
		if( (multiObjectiveBits & (1 << 1)) != 0 )
		{
			type = ARSearchMain.FitnessType.MSC;
			typeList.add(type);
		}
		
		if( (multiObjectiveBits & (1 << 2)) != 0 )
		{
			type = ARSearchMain.FitnessType.MPC;
			typeList.add(type);
		}

		if( (multiObjectiveBits & (1 << 3)) != 0 )
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
		
		
//		if( (searchTechniqueBits & (1 << 3)) != 0 )
//		{
//			type = ARSearchMain.SearchTechType.SELECT_FIRST_RESTART;
//			typeList.add(type);
//		}
	
		
		return typeList;
	}
	
	public List<ARSearchMain.CandidateSelectionType> getCandidateSelectionType()
	{
		ARSearchMain.CandidateSelectionType type;

		List<ARSearchMain.CandidateSelectionType> typeList = new LinkedList<ARSearchMain.CandidateSelectionType>();
		
		
//		if( (candidateSelectionBits & (1 << 0)) != 0 )
//		{
//			type = CandidateSelectionType.RANDOM;
//			typeList.add(type);
//		}
		
		if( (candidateSelectionBits & (1 << 0)) != 0 )
		{
			type = CandidateSelectionType.DELTA;
			typeList.add(type);
		}
		
		if( (candidateSelectionBits & (1 << 1)) != 0 )
		{
			type = CandidateSelectionType.EXHAUSTIVE;
			typeList.add(type);
		}
		
		return typeList;
	}


	public int getMaxCandidateCount() {
		return Integer.parseInt(maxCandidateCountStr);
	}


	public int getMaxIterationCount() {		
		return Integer.parseInt(maxIterationCountStr);	
	}


	public int getSAPermissibleIdleIteration() {		
		return Integer.parseInt(saMaxPermissibleIdelIterationStr);	
	}
}
