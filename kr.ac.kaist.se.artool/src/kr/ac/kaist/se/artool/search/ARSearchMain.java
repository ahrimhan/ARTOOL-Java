package kr.ac.kaist.se.artool.search;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import kr.ac.kaist.se.aom.AbstractObjectModel;

public class ARSearchMain {
	
	private static ARSearchMain instance;
	//public static Logger candidateLogger = LogManager.getLogger("Candidate");
	//public static Logger selectionLogger = LogManager.getLogger("Selection");
	public static Logger logger = LogManager.getLogger("SimpleLogger");
	public static final String baseLogPath = "/Users/igsong/log/";
	
	
	public enum FitnessType
	{
		FLEXIBILITY, REUSABILITY, UNDERSTANDABILITY, EPM, MPC, MSC, CONNECTIVITY, PARETO_COMPOSITE
	}
	
	public enum SearchTechType
	{
		SELECT_FIRST, SELECT_BEST, SELECT_FIRST_RESTART, SIMULATED_ANNEALING
	}
	
	public enum CandidateSelectionType
	{
		DELTA, RANDOM, EXHAUSTIVE
	}
	
	private ARSearchMain()
	{
	}

	public static ARSearchMain getInstance()
	{
		if( instance == null )
		{
			instance = new ARSearchMain();
		}

		return instance;
	}
	
	public void run(int caseIdx, 
			String project, 
			String timestamp, 
			AbstractObjectModel originalAOM, 
			FitnessType fitnessType, 
			List<FitnessType> multiFitnessTypeList,
			SearchTechType searchType, 
			CandidateSelectionType candidateSelectionType, 
			int max_iteration, 
			int max_candidate_selection, 
			int saMaxPermissibleIdleIteration, 
			int timeLimitForIteration,
			IProgressMonitor monitor) throws IOException
	{	
		String candidateMode = candidateSelectionType.name().toLowerCase();
		
		String searchTypeStr = "nosearch";
		switch( searchType )
		{
		case SELECT_BEST:
			searchTypeStr = "best";
			break;
		case SELECT_FIRST:
			searchTypeStr = "first";
			break;
		case SELECT_FIRST_RESTART:
			throw new RuntimeException("Not Yet Implemented");
		case SIMULATED_ANNEALING:
			searchTypeStr = "sa";
			break;
		}
		
		String fitnessTypeStr = fitnessType.name().toLowerCase();

		String projectSimplename = project.equals("org.eclipse.jgit") ? "jgit" : project;
		projectSimplename = projectSimplename.equals("apache-ant") ? "ant" : projectSimplename;
		String candidateCount = max_candidate_selection > 0 ? Integer.toString(max_candidate_selection) : "ul";
		String rootDirName = "XX-" + projectSimplename + "-" + candidateCount + "-" + timestamp; 
				
		StringBuilder fitnessTypeDetailStrBuilder = new StringBuilder(fitnessTypeStr);
		
		if( fitnessType == FitnessType.PARETO_COMPOSITE )
		{
			for( FitnessType detailFitnessType : multiFitnessTypeList )
			{
				fitnessTypeDetailStrBuilder.append('_');
				fitnessTypeDetailStrBuilder.append(detailFitnessType.name().toLowerCase());
			}
		}
		
		String fitnessTypeDetailStr = fitnessTypeDetailStrBuilder.toString();
		
		System.setProperty("candidate_filename", baseLogPath + rootDirName + "/" + caseIdx + "-" + projectSimplename + "-" + candidateMode + "-" + searchTypeStr + "-" + fitnessTypeDetailStr + "/candidate.log");
		System.setProperty("selection_filename", baseLogPath + rootDirName + "/" + caseIdx + "-" + projectSimplename + "-" + candidateMode + "-" + searchTypeStr + "-" + fitnessTypeDetailStr + "/selection.log");
		org.apache.logging.log4j.core.LoggerContext ctx =
			    (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		ctx.reconfigure();
		
		Logger candidateLogger = LogManager.getLogger("Candidate");
		Logger selectionLogger = LogManager.getLogger("Selection");
		Logger iterationTimeLogger = LogManager.getLogger("IterationTime");
		
		monitor.setTaskName(project + " " + candidateMode + " " + searchTypeStr + " " + fitnessTypeStr);
		
		ARSearchWorker worker = new ARSearchWorker(originalAOM, projectSimplename, fitnessType, multiFitnessTypeList, searchType, candidateSelectionType, max_iteration, max_candidate_selection, saMaxPermissibleIdleIteration, timeLimitForIteration, candidateLogger, selectionLogger, iterationTimeLogger);
		worker.run(monitor);
	}
	
//	public static void dLog(String s)
//	{
//		Timestamp time = new Timestamp(System.currentTimeMillis());
//		System.out.println(time.toString() + s);
//	}
}
