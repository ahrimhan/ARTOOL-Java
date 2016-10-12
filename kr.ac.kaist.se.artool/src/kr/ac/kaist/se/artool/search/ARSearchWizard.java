package kr.ac.kaist.se.artool.search;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.wizard.Wizard;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.search.ARSearchMain.CandidateSelectionType;
import kr.ac.kaist.se.artool.search.ARSearchMain.FitnessType;
import kr.ac.kaist.se.artool.search.ARSearchMain.SearchTechType;
import kr.ac.kaist.se.artool.util.CommandExecutionOperation;

public class ARSearchWizard extends Wizard {

	protected ARSearchParameterConfigPage paramConfigPage;
	
	private IFile selectedFile;

	private List<FitnessType> fitnessTypeList;
	private List<FitnessType> multiFitnessTypeList;

	private List<SearchTechType> searchTechTypeList;

	private List<CandidateSelectionType> candidateSelectionTypeList;
	

	private int maxIterationCount;

	private List<Integer> maxCandidateCountList; 
	
	private int timeLimitForIteration;
	
	private int saMaxPermissibleIdleIteration;

	public ARSearchWizard(IFile selectedFile) {
		super();
		setNeedsProgressMonitor(true);
		
		this.selectedFile = selectedFile;
	}

	@Override
	public String getWindowTitle() {
		return "Finding Move Method with Delta Matrix";
	}

	@Override
	public void addPages() {
		paramConfigPage = new ARSearchParameterConfigPage("Setup Search Parameter");
		paramConfigPage.loadSettings();
		addPage(paramConfigPage);
	}
	
	@Override
	public boolean performFinish() {
		paramConfigPage.saveSettings();
		
		TransactionalEditingDomain myEditingDomain = GMFEditingDomainFactory.INSTANCE
		.createEditingDomain();
		ResourceSet resourceSet = myEditingDomain.getResourceSet();
		final Resource aomResource = resourceSet
				.getResource(URI.createPlatformResourceURI(selectedFile
						.getFullPath().toString(), true), true);

		
		List<IFile> affectedFileList = new LinkedList<IFile>();
		affectedFileList.add(selectedFile);
		
		fitnessTypeList = paramConfigPage.getFitnessType();
		multiFitnessTypeList = paramConfigPage.getMultiFitnessType();
		searchTechTypeList = paramConfigPage.getSearchTechType();
		candidateSelectionTypeList = paramConfigPage.getCandidateSelectionType();
		//useDeltaTable = paramConfigPage.useDeltaTable();
		maxIterationCount = paramConfigPage.getMaxIterationCount();
		maxCandidateCountList = paramConfigPage.getMaxCandidateCountList();
		timeLimitForIteration = paramConfigPage.getTimeLimitForIteration();
		saMaxPermissibleIdleIteration = paramConfigPage.getSAPermissibleIdleIteration();
		
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				myEditingDomain, "Measuring Metric Suites", affectedFileList) { //$NON-NLS-1$
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				try {
					
					AbstractObjectModel aom = (AbstractObjectModel) aomResource.getContents().get(0);
					
					SystemEntitySet ses = new SystemEntitySet(aom);
					
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String timestamp = format.format(new Date());
					
					monitor.beginTask("Search Refactoring, CEMF(" + aom.getClasses().size() + "," + ses.entities.size() + "," + ses.methods.size() + "," + ses.fields.size() + ")", 
							fitnessTypeList.size() * searchTechTypeList.size() * candidateSelectionTypeList.size() * maxIterationCount);
					
					for( int maxCandidateCount : maxCandidateCountList )
					{
						int caseIdx = 10;
					
						for(FitnessType fitnessType : fitnessTypeList )
						{
							for(SearchTechType searchTechType: searchTechTypeList )
							{
								for(CandidateSelectionType candidateSelectionType: candidateSelectionTypeList )
								{
									ARSearchMain.getInstance().run(caseIdx, selectedFile.getProject().getName(), timestamp, aom, fitnessType, multiFitnessTypeList, searchTechType, candidateSelectionType, maxIterationCount, maxCandidateCount, saMaxPermissibleIdleIteration, timeLimitForIteration, monitor);
									caseIdx++;
								}
							}
						}
					}
					
				} catch (IOException e) {
					return CommandResult.newErrorCommandResult("save failed");
				} finally {
					monitor.done();
				}
				
				return CommandResult.newOKCommandResult();
			}
		};
		
		
		
		
		
		try {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(this.getShell());
			
			dialog.run(true, false, new CommandExecutionOperation(command));
//			this.mySelectedProjectList.get(0).getProject().refreshLocal(2,
//					new NullProgressMonitor());
			// IDE.openEditor(myWorkbenchPage, umlFile);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			System.err.println(
					"Unable to create model and diagram"); //$NON-NLS-1$
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println(
					"Unable to create model and diagram"); //$NON-NLS-1$
			return false;
		}

		return true;
	}

}
