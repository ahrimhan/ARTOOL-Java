package kr.ac.kaist.se.aom.profiler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AOMProfileItemDispatcher implements Runnable {

	private BlockingQueue<AOMLoggingItem> queue;
	private static PrintStream systemOut;
	private PrintStream ps;
	private static AOMProfileItemDispatcher instance = null;
	private boolean isStarted;
//	private static Socket socket;
	private static FileWriter mcWriter;
	private static PrintWriter mcPrintWriter;
	private static FileWriter faWriter;
	private static PrintWriter faPrintWriter;
	
	private AOMProfileItemDispatcher() {
		queue = new ArrayBlockingQueue<AOMLoggingItem>(5000);
	
		ps = systemOut;
		isStarted = false;
	}

	static {
		systemOut = System.err;
	}

	public static synchronized AOMProfileItemDispatcher getInstance() {
		if (instance == null) {
			instance = new AOMProfileItemDispatcher();
			new Thread(instance).start();
		}

		return instance;
	}

	public void put(AOMLoggingItem item) throws InterruptedException {

		queue.put(item);
	}

	@Override
	public void run() {

		while (true) {
			if (mcWriter == null) {
				try {
					mcWriter = new FileWriter(AOMProfiler.getLogPath() + java.io.File.separator + "DynamicMethodCallLog.txt");
					mcPrintWriter = new PrintWriter(mcWriter);
				} catch (IOException e) {
					e.printStackTrace(systemOut);
					mcPrintWriter = null;
				}
			}
			
			if (faWriter == null) {
				try {
					faWriter = new FileWriter(AOMProfiler.getLogPath() + java.io.File.separator + "DynamicFieldAccessLog.txt");
					faPrintWriter = new PrintWriter(faWriter);
				} catch (IOException e) {
					e.printStackTrace(systemOut);
					faPrintWriter = null;
				}
			}
			
			try {
				ArrayList<AOMLoggingItem> al = new ArrayList<AOMLoggingItem>(500);
				while (true) {
					al.clear();
					while( queue.isEmpty() )
					{
						Thread.currentThread().sleep(200);
					}

					int c = queue.drainTo(al, 500);

					
					if( mcPrintWriter != null && faPrintWriter != null ) {
						try {
							for( int i = 0; i < c; i++ )
							{
								AOMLoggingItem li = al.get(i);
								if( li != null )
								{
									if( li instanceof AOMMethodCallItem )
									{
										li.write(mcPrintWriter);
										((AOMMethodCallItem)li).setOccupied(false);
										AOMMethodCallItem.returnInstance((AOMMethodCallItem)li);
									}
									else if(li instanceof AOMFieldAccessItem )
									{
										li.write(faPrintWriter);
										((AOMFieldAccessItem)li).setOccupied(false);
										AOMFieldAccessItem.returnInstance((AOMFieldAccessItem)li);	
									}
								}
							}
					
						} catch (Throwable e) {
							try {
								mcPrintWriter.close();
								mcWriter.close();
							} catch (IOException e1) {
								mcPrintWriter = null;
								mcWriter = null;
							}

							try {
								faPrintWriter.close();
								faWriter.close();
							} catch (IOException e1) {
								faPrintWriter = null;
								faWriter = null;
							}								
							
							break;
						}
					}
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace(systemOut);
			}

		}

	}

}
