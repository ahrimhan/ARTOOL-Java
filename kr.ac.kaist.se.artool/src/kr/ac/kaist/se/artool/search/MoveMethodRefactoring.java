package kr.ac.kaist.se.artool.search;

import java.util.Stack;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringException;

public class MoveMethodRefactoring {

	private Stack<MoveMethodCommand> history;
	private AbstractObjectModel aom;
	private DeltaMatrixEngine dme;
	
	public MoveMethodRefactoring(AbstractObjectModel aom, DeltaMatrixEngine dme)
	{
		history = new Stack<MoveMethodCommand>();
		this.aom = aom;
		this.dme = dme;
	}
	
	public boolean doAction(MoveMethodCommand action)
	{
		for( MoveMethodCommand mmc : history )
		{
			if( mmc.isIdenticalOrReversal(action) )
			{
				return false;
			}
		}
		
		try
		{
			if( action.doCommand() > 0 )
			{
				dme.moveMethod(action.getMethod(), action.getToClass());
				history.push(action);
				return true;
			}
		}
		catch(RefactoringException e)
		{
			e.printStackTrace();

		}
		
		return false;
	}
	
	public void undoAction()
	{
		MoveMethodCommand action = history.pop();
		
		try {
			action.undoCommand();
			dme.moveMethod(action.getMethod(), action.getFromClass());
		} catch (RefactoringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
