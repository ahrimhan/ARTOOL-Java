package kr.ac.kaist.se.artool.search;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public interface MoveMethodEventListener {
	public void moveMethodPerformed(AOMMethod method, AOMClass targetClass, boolean isRollbackAction);
}
