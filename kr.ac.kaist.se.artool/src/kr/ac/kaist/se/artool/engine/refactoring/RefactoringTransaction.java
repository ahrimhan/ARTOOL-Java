package kr.ac.kaist.se.artool.engine.refactoring;

import java.util.Stack;
import java.util.Vector;

public class RefactoringTransaction {

	private Stack<RefactoringCommand> undoStack;
	private Vector<RefactoringCommand> readyQueue;
	
	
	private RefactoringTransaction()
	{
		undoStack = new Stack<RefactoringCommand>();
		readyQueue = new Vector<RefactoringCommand>();
	}
	
	public static RefactoringTransaction instance = null;
	
	public static RefactoringTransaction getInstance()
	{
		if( instance == null )
		{
			instance = new RefactoringTransaction();
		}
		return instance;
	}
	
	public void addCommand(RefactoringCommand command)
	{
		readyQueue.add(command);
	}
	
	public void trialRefactoring() throws RefactoringException
	{
		System.err.println();
		while( !readyQueue.isEmpty() )
		{
			RefactoringCommand rc = readyQueue.remove(0);
			rc.doCommand();
			undoStack.push(rc);
		}
	}
	
	public void commitRefactoring()
	{
		undoStack.clear();
	}
	
	public void rollbackRefactoring() throws RefactoringException
	{
		while( undoStack.isEmpty() )
		{
			RefactoringCommand rc = undoStack.pop();
			rc.undoCommand();
		}
	}
}
