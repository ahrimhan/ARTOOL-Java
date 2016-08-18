package kr.ac.kaist.se.artool.search.candidate;

import java.util.Random;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodApplicabilityChecker;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class RandomCandidateIterator implements CandidateIterator {
	private RandomCandidateSelection rcs;
	private int iterationCount = 0;
	private int maxIterationCount = -1;
	private int maxTrialCount = -1;
	private int trialCount = 0;
	private MoveMethodCommand nextMMC = null;
	
	public RandomCandidateIterator(RandomCandidateSelection rcs, int maxIterationCount, int maxTrialCount)
	{
		this.random = new Random(System.currentTimeMillis());

		this.rcs = rcs;
		this.maxIterationCount = maxIterationCount;
		this.maxTrialCount = maxTrialCount;
		iterationCount = 0;
	}
	
	@Override
	public boolean hasNext() {
		if( maxIterationCount > 0 )
		{
			if( iterationCount >= maxIterationCount )
			{
				return false;
			}
		}
		
		if( nextMMC == null )
		{
			nextMMC = getRandomMoveMethodCommand();
		}
		
		if( nextMMC == null )
		{
			return false;
		}
		
		iterationCount++;
		return true;
	}
	
	private Random random;

	public MoveMethodCommand getRandomMoveMethodCommand()
	{
		MoveMethodCommand newMMC = null;
		
		while(maxTrialCount <= 0 || trialCount < maxTrialCount)
		{
			int ci = random.nextInt(rcs.getAOM().getClasses().size());
			int mi = random.nextInt(rcs.getMethodList().size());
			AOMMethod movingMethod = rcs.getMethodList().elementAt(mi);
			AOMClass targetClass = rcs.getAOM().getClasses().get(ci);
			
			if( MoveMethodApplicabilityChecker.isApplicable(movingMethod, targetClass) )
			{
				newMMC = new MoveMethodCommand(movingMethod, targetClass);
				trialCount = 0;
				break;
			}
			
			trialCount++;
		}
		
		return newMMC;
	}

	@Override
	public MoveMethodCommand getNextCandidate() {
		if( nextMMC == null )
		{
			return getRandomMoveMethodCommand();
		}
		
		MoveMethodCommand mmc = nextMMC;
		
		nextMMC = null;
		
		return mmc;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
