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
//			"QMOOD-Reusability",
//			"QMOOD-Flexibility",
//			"QMOOD-Understandability",
			"MSC (Message Similiarity Cohesion)",
			"MPC (Message Passing Coupling)",
			"Connectivity"
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
			"Random",
			"Delta Table",
			"Exhaustive"
	};
	
	private int fitnessSelectionBits = 0;
	private int searchTechniqueBits = 0;
	private int candidateSelectionBits = 0;

	private int maxIterationCount;
	private int maxCandidateCount;
	private int saMaxPermissibleIdelIteration;
	
	public void saveSettings() {
		// saves plugin preferences at the workspace level
		IEclipsePreferences prefs =
				InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID); // does all the above behind the scenes

		prefs.putInt("fitnessSelectionBits", fitnessSelectionBits);
		prefs.putInt("searchTechniqueBits", searchTechniqueBits);
		prefs.putInt("candidateSelectionBits", candidateSelectionBits);
		prefs.putInt("maxIterationCount", maxIterationCount);
		prefs.putInt("maxCandidateCount", maxCandidateCount);
		prefs.putInt("saMaxPermissibleIdelIteration", saMaxPermissibleIdelIteration);
		try {
			prefs.flush();
		} catch(BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public void loadSettings() {
		IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);

		fitnessSelectionBits = prefs.getInt("fitnessSelectionBits", 0xffff);
		searchTechniqueBits = prefs.getInt("searchTechniqueBits", 0xffff);
		candidateSelectionBits = prefs.getInt("candidateSelectionBits", 0xffff);
		maxIterationCount = prefs.getInt("maxIterationCount", 100);
		maxCandidateCount = prefs.getInt("maxCandidateCount", 1000);
		saMaxPermissibleIdelIteration = prefs.getInt("saMaxPermissibleIdelIteration", 500);
	}
	
	@Override
	public void createControl(Composite parent) {
		// create the composite to hold the widgets
		Composite globalComposite = new Composite(parent, SWT.NONE);
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
		composite.setLayout(gl);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		VerifyListener verifyListener = new VerifyListener()
		{
			@Override
			public void verifyText(VerifyEvent e) {
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
		};
		
		
		new Label(composite, SWT.NONE).setText("Max Iteraction Count:");
		Text maxIterationCountText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		maxIterationCountText.setMessage("Set Max-Iteration Counts. Default is 1000");
		maxIterationCountText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		maxIterationCountText.setText(Integer.toString(maxIterationCount));
		maxIterationCountText.addVerifyListener(verifyListener);
		maxIterationCountText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.widget;
				try
				{
					maxIterationCount = Integer.parseInt(text.toString());
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		new Label(composite, SWT.NONE).setText("Max Candidates Count:");
		Text maxCandidateCountText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		maxCandidateCountText.setMessage("Set Max-Candidate Counts. Default is 100");
		maxCandidateCountText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		maxCandidateCountText.setText(Integer.toString(maxCandidateCount));
		maxCandidateCountText.addVerifyListener(verifyListener);
		maxCandidateCountText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.widget;
				try
				{
					maxCandidateCount = Integer.parseInt(text.toString());
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		new Label(composite, SWT.NONE).setText("(SA) Permissible Idle Iteration Count:");
		Text saMaxPermissibleIdelIterationText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		saMaxPermissibleIdelIterationText.setMessage("Set Max Permissible Idle Iterations. Default is 500");
		saMaxPermissibleIdelIterationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		saMaxPermissibleIdelIterationText.setText(Integer.toString(saMaxPermissibleIdelIteration));
		saMaxPermissibleIdelIterationText.addVerifyListener(verifyListener);
		saMaxPermissibleIdelIterationText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.widget;
				try
				{
					saMaxPermissibleIdelIteration = Integer.parseInt(text.toString());
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
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
		
		if( (candidateSelectionBits & (1 << 2)) != 0 )
		{
			type = CandidateSelectionType.EXHAUSTIVE;
			typeList.add(type);
		}
		
		return typeList;
	}


	public int getMaxCandidateCount() {
		return maxCandidateCount;
	}


	public int getMaxIterationCount() {		
		return maxIterationCount;	
	}


	public int getSAPermissibleIdleIteration() {		
		return saMaxPermissibleIdelIteration;	
	}
}
