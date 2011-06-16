package kr.ac.kaist.se.artool.util;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class CommandExecutionOperation implements IRunnableWithProgress
{
	private IUndoableOperation operation;
	public CommandExecutionOperation(IUndoableOperation operation)
	{
		this.operation = operation;
	}

	@Override
	public void run(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
		try {
			OperationHistoryFactory.getOperationHistory().execute(operation,
					monitor, null);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			InvocationTargetException ite = new InvocationTargetException(e);
			throw ite;
		}
	}
	
}