package kr.ac.kaist.se.artool.engine;

import kr.ac.kaist.se.artool.search.ARSearchMain;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ARSearchParameterConfigPage extends WizardPage {

	protected ARSearchParameterConfigPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}
	
	private Combo fitnessFunction;
	private String[] fitnessFunctionItems = {
			"EPM",
			"QMOOD-Reusability",
			"QMOOD-Flexibility",
			"QMOOD-Understandability",
	};
	
	private Combo searchTechnique;
	private String[] searchTechniqueItems = {
			"First-ascent hill-climbing",
			"Steepest-ascent hill-climbing",
			/*
			"Multiple-restart hill-climbing",
			"Low-temperature simulated annealing"
			*/
	};
	
	private Button useDeltaTableButton;

	@Override
	public void createControl(Composite parent) {
		// create the composite to hold the widgets
		Composite composite = new Composite(parent, SWT.NONE);
		// create the desired layout for this wizard page
		GridLayout gl = new GridLayout();
		int ncol = 2;
		gl.numColumns = ncol;
		composite.setLayout(gl);
		// create the widgets and their grid data objects
		// Date of travel
		new Label(composite, SWT.NONE).setText("Fitness Function:");
		fitnessFunction = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		fitnessFunction.setItems(fitnessFunctionItems);
		fitnessFunction.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		
		new Label(composite, SWT.NONE).setText("Search Technique:");
		searchTechnique = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		searchTechnique.setItems(searchTechniqueItems);
		searchTechnique.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		useDeltaTableButton = new Button(composite, SWT.CHECK);
		useDeltaTableButton.setText("Use Delta Table Acceleration?");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol;
		useDeltaTableButton.setLayoutData(gd);
		useDeltaTableButton.setSelection(true);
		
		setControl(composite);
	}

	
	public ARSearchMain.FitnessType getFitnessType()
	{
		ARSearchMain.FitnessType type;
		
		switch( fitnessFunction.getSelectionIndex() )
		{
		case 0:
			type = ARSearchMain.FitnessType.EPM;
			break;
		case 1:
			type = ARSearchMain.FitnessType.REUSABILITY;
			break;
		case 2:
			type = ARSearchMain.FitnessType.FLEXIBILITY;
			break;
		case 3:
			type = ARSearchMain.FitnessType.UNDERSTANDABILITY;
			break;
		
		default:
			type = ARSearchMain.FitnessType.EPM;
			break;
			
		}
		
		return type;
	}
	
	public ARSearchMain.SearchTechType getSearchTechType()
	{
		ARSearchMain.SearchTechType type;
		
		switch( searchTechnique.getSelectionIndex() )
		{
		case 0:
			type = ARSearchMain.SearchTechType.SELECT_FIRST;
			break;
		case 1:
			type = ARSearchMain.SearchTechType.SELECT_BEST;
			break;
		case 2:
			type = ARSearchMain.SearchTechType.SELECT_FIRST_RESTART;
			break;
		case 3:
			type = ARSearchMain.SearchTechType.SIMULATED_ANNEALING;
			break;
		default:
			type = ARSearchMain.SearchTechType.SELECT_FIRST;
			break;
			
		}
		
		return type;
	}
	
	public boolean useDeltaTable()
	{
		return useDeltaTableButton.getSelection();
	}
}
