package kr.ac.kaist.se.artool.staticmodeling;

import kr.ac.kaist.se.aom.structure.AOMClass;

public interface HierarchyTraverserHelper {
	void preVisit(AOMClass clazz);
	void postVisit(AOMClass clazz);
	HierarchyTraverserHelper getChildHelper();
}
