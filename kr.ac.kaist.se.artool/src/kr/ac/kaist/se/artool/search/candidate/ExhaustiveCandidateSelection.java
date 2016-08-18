package kr.ac.kaist.se.artool.search.candidate;

import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class ExhaustiveCandidateSelection implements CandidateSelection {

	private AbstractObjectModel aom;
	private Vector<AOMMethod> methodList;
	
	public void setMethodList(Vector<AOMMethod> methodList) {
		this.methodList = methodList;
	}

	public ExhaustiveCandidateSelection(AbstractObjectModel aom)
	{
		this.aom = aom;
		this.methodList = new Vector<AOMMethod>();
		
		System.err.println("ExhaustiveCandidateSelection: preparing...!!!");
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				methodList.add(method);
			}
		}
		

//		
//		System.err.println("ExhaustiveCandidateSelection: ready to action!!!");
//		System.err.println("Candidate Count:" + candidateList.size());
	}
	
	public Vector<AOMMethod> getMethodList()
	{
		return methodList;
	}
	
	public AbstractObjectModel getAOM()
	{
		return aom;
	}
	
	@Override
	public CandidateIterator getCandidateIterator(int maxCandidateCount) {
		ExhaustiveCandidateIterator sci = new ExhaustiveCandidateIterator(this, maxCandidateCount); 
		return sci;
	}

}
