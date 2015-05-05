package kr.ac.kaist.se.artool.engine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

import org.eclipse.swt.widgets.Shell;
import org.jblas.DoubleMatrix;

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
		SystemEntitySet ses = new SystemEntitySet(aom);
		DeltaMatrixEngine dme = new DeltaMatrixEngine(ses);
		
		DoubleMatrix dm = dme.getDeltaMatrix();
	}
}
