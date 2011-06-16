package kr.ac.kaist.se.artool.dynamicprofile.connector;

import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;

public interface ObjectBroadcastListener {
	void broadcastedObject(AOMMethodCallItem object);
}
