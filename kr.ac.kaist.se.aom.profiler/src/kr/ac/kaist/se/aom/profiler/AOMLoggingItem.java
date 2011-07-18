package kr.ac.kaist.se.aom.profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public abstract class AOMLoggingItem  {
	public abstract void write(PrintWriter ds) throws IOException;
	public abstract boolean read(BufferedReader ds) throws IOException;
	private static Charset charset = Charset.forName("US-ASCII");
	private static CharsetEncoder charEncoder;
	private boolean occupied = false;
	
	static
	{
		charEncoder = charset.newEncoder();
	}
	
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
	
	public ByteBuffer write(ByteBuffer buffer) {
		String str = toString();
		if( str == null ) return null;
		return charset.encode(str);
//		buffer.asCharBuffer().put(str);
//		return null;
	}
	
	public String toString()
	{
		StringWriter wr = new StringWriter();
		PrintWriter ds = new PrintWriter(wr);
		
		String ret = null;
		try {
			write(ds);
			ds.flush();		
			wr.flush();
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
