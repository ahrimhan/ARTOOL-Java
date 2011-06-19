package kr.ac.kaist.se.aom.profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AOMLoggingItem  {
	public abstract void write(PrintWriter ds) throws IOException;
	public abstract boolean read(BufferedReader ds) throws IOException;
	
	private boolean occupied = false;
	
	public void setOccupied(boolean occupied)
	{
		synchronized(this)
		{
			this.occupied = occupied;
		}
	}
	
	public boolean isOccupied()
	{
		boolean occupied;
		synchronized(this)
		{
			occupied = this.occupied;
		}
		return occupied;
	}
	

	

}
