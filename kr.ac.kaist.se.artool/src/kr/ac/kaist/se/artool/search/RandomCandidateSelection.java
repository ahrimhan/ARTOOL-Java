package kr.ac.kaist.se.artool.search;

import java.util.Random;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;

public class RandomCandidateSelection implements CandidateSelection {
	private int candidateCount;
	private AbstractObjectModel aom;
	private Random random;
	private Vector<AOMMethod> methodList;
	
	public RandomCandidateSelection(AbstractObjectModel aom, int candidateCount)
	{
		this.candidateCount = candidateCount;
		this.aom = aom;
		this.random = new Random(System.currentTimeMillis());
		
		this.methodList = new Vector<AOMMethod>();
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				methodList.add(method);
			}
		}
	}
	
	
	private boolean isUniqueMMC(Vector<MoveMethodCommand> ret, MoveMethodCommand newMMC)
	{
		for( MoveMethodCommand mmc: ret)
		{
			if( mmc.isIdenticalOrReversal(newMMC) )
			{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public Vector<MoveMethodCommand> getCandidates() {
		
		Vector<MoveMethodCommand> ret = new Vector<MoveMethodCommand>();
		
		if( (this.candidateCount * 2) > (aom.getClasses().size() * methodList.size()) )
		{
			throw new RuntimeException("RandomCandidateSelection: too large candidate count");
		}
		
		for(int i = 0 ; i < this.candidateCount; i++ )
		{
			MoveMethodCommand newMMC = null;
			
			
			do
			{
				int ci = random.nextInt(aom.getClasses().size());
				int mi = random.nextInt(methodList.size());
				newMMC = new MoveMethodCommand(methodList.elementAt(mi), aom.getClasses().get(ci));
			}
			while( isUniqueMMC(ret, newMMC) );
			
			if( newMMC != null )
			{
				ret.add(newMMC);
			}
		}
		
		
		return ret;
	}
}
