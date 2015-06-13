package kr.ac.kaist.se.artool.search;

import java.util.Stack;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringException;

public class MoveMethodRefactoring {

	private Stack<MoveMethodCommand> history;
	private AbstractObjectModel aom;
	private Vector<MoveMethodEventListener> listenerList;
	
	
	public MoveMethodRefactoring(AbstractObjectModel aom)
	{
		history = new Stack<MoveMethodCommand>();
		this.aom = aom;
		listenerList = new Vector<MoveMethodEventListener>();
	}
	
	public void addListener(MoveMethodEventListener listener)
	{
		listenerList.add(listener);
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
				for( MoveMethodEventListener listener : listenerList )
				{
					listener.moveMethodPerformed(action.getMethod(), action.getToClass(), false);
				}
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
			for( MoveMethodEventListener listener : listenerList )
			{
				listener.moveMethodPerformed(action.getMethod(), action.getFromClass(), true);
			}
		} catch (RefactoringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
