package kr.ac.kaist.se.artool.staticmodeling;

import kr.ac.kaist.se.aom.structure.AOMClass;

public class HierarchyTraverser {
	public void traverse(AOMClass aomClass, HierarchyTraverserHelper helper)
	{
		helper.preVisit(aomClass);
		for( AOMClass descClass :  aomClass.getDescendant() )
		{
			traverse(descClass, helper.getChildHelper());
		}
		helper.postVisit(aomClass);
	}
}
