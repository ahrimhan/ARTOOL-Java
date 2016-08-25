package kr.ac.kaist.se.artool.search;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public interface MoveMethodEventListener {
	public void moveMethodPerformed(AOMClass fromClass, AOMMethod method, AOMClass toClass, boolean isRollbackAction, boolean isVirtualMove);
}
