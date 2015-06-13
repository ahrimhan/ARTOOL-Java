package kr.ac.kaist.se.artool.engine.rules;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.artool.engine.ARToolMain;
import kr.ac.kaist.se.artool.engine.ARFitnessFunction;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringCommand;
import kr.ac.kaist.se.artool.engine.refactoring.RefactoringException;

public abstract class AbstractRule {
	protected AbstractObjectModel aom;
	
	private String ruleName;
	
	public AbstractRule(AbstractObjectModel aom, int pick, String ruleName) {
		super();
		this.aom = aom;
		this.pick = pick;
		this.ruleName = ruleName;
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
		long mem_usage;
		long curTime = System.currentTimeMillis();
		double refactoring_cost = 0;
		refactoringCommand = getCommand();

		float ret = 0;
		if( refactoringCommand  != null )
		{
			try {
				refactoring_cost = refactoringCommand.doCommand();
			} catch (RefactoringException e) {
				try {
					refactoringCommand.undoCommand();
				} catch (RefactoringException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
			}
			System.err.println("doCommand:" + Long.toString(System.currentTimeMillis() - curTime));
			mem_usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			System.err.println("doCommand!:" + mem_usage);
			
			curTime = System.currentTimeMillis();
			ret =  ARFitnessFunction.getInstance().calculate(aom, refactoring_cost);
			System.err.println("FitnessFunction:" + Long.toString(System.currentTimeMillis() - curTime));
			mem_usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			System.err.println("FitnessFunction!:" + mem_usage);
			
			try {
				curTime = System.currentTimeMillis();
				refactoringCommand.undoCommand();
				System.err.println("undoCommand:" + Long.toString(System.currentTimeMillis() - curTime));
				mem_usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.err.println("undoCommand!:" + mem_usage);
				
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
	
	public double perform()
	{
		double ret = 0;
		try {
			if(refactoringCommand == null)
			{
				return ret;
			}
			else
			{
				ret = refactoringCommand.doCommand();
			}
			
		} catch (RefactoringException e) {
			return ret;
		}
		return ret;
	}

	public void setAom(AbstractObjectModel aom) {
		this.aom = aom;
	}

	public AbstractObjectModel getAom() {
		return aom;
	}
	
	public String getRuleName()
	{
		return ruleName;
	}
	
	public final String getName()
	{
		return getRuleName()+"[" + pick +"]";
	}
	public abstract String getStatus();
}