package kr.ac.kaist.se.artool.engine.refactoring;

public class RefactoringException extends Exception {
	public RefactoringException(String s)
	{
		super(s);
	}
	
	public RefactoringException(Throwable t)
	{
		super(t);
	}
}
