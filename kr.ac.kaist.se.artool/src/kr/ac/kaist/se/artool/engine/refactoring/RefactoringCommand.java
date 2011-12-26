package kr.ac.kaist.se.artool.engine.refactoring;

public interface RefactoringCommand {
	double doCommand() throws RefactoringException;
	void undoCommand() throws RefactoringException;
}
