package kr.ac.kaist.se.aom.profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;

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
	
	public boolean write(ByteBuffer buffer) {
		String str = toString();
		if( str == null ) return false;
		buffer.put(str.getBytes());
		return true;
	}
	
	public String toString()
	{
		StringWriter wr = new StringWriter();
		PrintWriter ds = new PrintWriter(wr);
		
		String ret = null;
		try {
			write(ds);
			ds.flush();		
			
			ret = wr.toString();
			ds.close();
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
