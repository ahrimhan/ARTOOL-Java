package kr.ac.kaist.se.artool.dynamicprofile;

import java.io.File;
import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.internal.launching.LaunchingMessages;
import org.eclipse.jdt.launching.ExecutionArguments;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

public class LaunchConfigurationDelegate extends JavaLaunchDelegate
{

	public LaunchConfigurationDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		
		monitor.beginTask(MessageFormat.format("{0}...", new String[]{configuration.getName()}), 3); //$NON-NLS-1$
		// check for cancellation
		if (monitor.isCanceled()) {
			return;
		}
		try {
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Verifying_launch_attributes____1); 
							
			String mainTypeName = verifyMainTypeName(configuration);
			IVMRunner runner = getVMRunner(configuration, mode);
	
			File workingDir = verifyWorkingDirectory(configuration);
			String workingDirName = null;
			if (workingDir != null) {
				workingDirName = workingDir.getAbsolutePath();
			}
			
			// Environment variables
			String[] envp= getEnvironment(configuration);
			
			// Program & VM arguments
			String pgmArgs = getProgramArguments(configuration);
			String vmArgs = getVMArguments(configuration);
			vmArgs = vmArgs + VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(" -javaagent:${project_loc}/lib/aomprofiler.jar");
			ExecutionArguments execArgs = new ExecutionArguments(vmArgs, pgmArgs);
			
			// VM-specific attributes
			Map vmAttributesMap = getVMSpecificAttributesMap(configuration);
			
			// Classpath
			String[] classpath = getClasspath(configuration);
			
			// Create VM config
			VMRunnerConfiguration runConfig = new VMRunnerConfiguration(mainTypeName, classpath);
			runConfig.setProgramArguments(execArgs.getProgramArgumentsArray());
			runConfig.setEnvironment(envp);
			runConfig.setVMArguments(execArgs.getVMArgumentsArray());
			runConfig.setWorkingDirectory(workingDirName);
			runConfig.setVMSpecificAttributesMap(vmAttributesMap);
	
			// Bootpath
			runConfig.setBootClassPath(getBootpath(configuration));
			
			// check for cancellation
			if (monitor.isCanceled()) {
				return;
			}		
			
			// stop in main
			prepareStopInMain(configuration);
			
			// done the verification phase
			monitor.worked(1);
			
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Creating_source_locator____2); 
			// set the default source locator if required
			setDefaultSourceLocator(launch, configuration);
			monitor.worked(1);		
			
			// Launch the configuration - 1 unit of work
			runner.run(runConfig, launch, monitor);
			
			// check for cancellation
			if (monitor.isCanceled()) {
				return;
			}	
		}
		finally {
			monitor.done();
		}
	}

}
