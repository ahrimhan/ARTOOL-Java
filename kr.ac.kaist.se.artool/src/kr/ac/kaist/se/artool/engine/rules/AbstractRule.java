package kr.ac.kaist.se.artool.engine.rules;

import java.util.HashSet;
import java.util.Map;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.ARToolMain;
import kr.ac.kaist.se.artool.engine.FitnessFunction;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringException;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringTransaction;

public abstract class AbstractRule {
	protected AbstractObjectModel aom;
	
	public AbstractRule(AbstractObjectModel aom, int pick) {
		super();
		this.aom = aom;
		this.pick = pick;
	}
	
	private int pick;
	
	public int getPickNumber()
	{
		return pick;
	}
	
	public void setPickNumber(int pick)
	{
		this.pick = pick;
	}


	protected float fitness;

	public float getFitness() {
		return fitness;
	}

	public void setFitness(float fitness) {
		this.fitness = fitness;
	}

	public AbstractRule() {
		super();
	}
	
	public abstract RefactoringCommand getCommand();
	
	
	private RefactoringCommand refactoringCommand;
	
	public void trial()
	{
		refactoringCommand = getCommand();

		float ret = 0;
		if( refactoringCommand  != null )
		{
			try {
				refactoringCommand.doCommand();
			} catch (RefactoringException e) {
				try {
					refactoringCommand.undoCommand();
				} catch (RefactoringException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
			}
			
			ret =  FitnessFunction.getInstance().calculate(aom);
						
			try {
				refactoringCommand.undoCommand();
			} catch (RefactoringException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.err.println();
			ARToolMain.getInstance().getPrintStream().println();
		}
		setFitness(ret);
	}
	
	public boolean perform()
	{
		try {
			if(refactoringCommand == null)
			{
				return false;
			}
			else
			{
				refactoringCommand.doCommand();
			}
			
		} catch (RefactoringException e) {
			return false;
		}
		return true;
	}

	public void setAom(AbstractObjectModel aom) {
		this.aom = aom;
	}

	public AbstractObjectModel getAom() {
		return aom;
	}
	
	public abstract String getRuleName();
	
	public final String getName()
	{
		return getRuleName()+"[" + pick +"]";
	}
	public abstract String getStatus();
}