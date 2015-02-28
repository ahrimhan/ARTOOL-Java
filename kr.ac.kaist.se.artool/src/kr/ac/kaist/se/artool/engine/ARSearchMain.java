package kr.ac.kaist.se.artool.engine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import kr.ac.kaist.se.aom.AbstractObjectModel;

import org.eclipse.swt.widgets.Shell;

public class ARSearchMain {
	
	private PrintStream ps;
	private static ARSearchMain instance;

	private ARSearchMain()
	{
		try {

			ps = new PrintStream(new FileOutputStream("/Users/wjsong/git/ARTOOL/resultARTool/metric_result1.txt"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println ("Error in writing to file");
		}

	}

	public static ARSearchMain getInstance()
	{
		if( instance == null )
		{
			instance = new ARSearchMain();
		}

		return instance;
	}

	public void run(AbstractObjectModel aom, final Shell shell) throws IOException
	{	
	
	}
}
