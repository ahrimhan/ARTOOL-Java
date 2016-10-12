package kr.ac.kaist.se.artool.search.fitness.value;

import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.candidate.DeltaValue;

public abstract class FitnessValue implements Comparable<FitnessValue>{
	public MoveMethodCommand ownedCommand;
	
	public abstract float distance(FitnessValue v);
	
	public abstract DeltaValue diffWith(FitnessValue newOne);
}
