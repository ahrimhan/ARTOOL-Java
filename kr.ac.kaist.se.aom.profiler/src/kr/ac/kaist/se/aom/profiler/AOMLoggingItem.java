package kr.ac.kaist.se.aom.profiler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

public interface AOMLoggingItem extends Serializable {
	public void write(DataOutputStream ds) throws IOException;
	public void read(DataInputStream ds) throws IOException;
}
