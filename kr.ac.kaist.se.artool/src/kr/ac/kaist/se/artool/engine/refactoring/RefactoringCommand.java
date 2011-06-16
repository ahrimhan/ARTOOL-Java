package kr.ac.kaist.se.artool.engine.refactoring;

public interface RefactoringCommand {
	void doCommand() throws RefactoringException;
	void undoCommand() throws RefactoringException;
}
