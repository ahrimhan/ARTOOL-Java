package kr.ac.kaist.se.aom.profiler;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
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
	private static OutputStream outputStream;
	private static DataOutputStream dos;

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
			if (outputStream == null) {
				try {
					outputStream = new FileOutputStream(AOMProfiler.getLogPath() + java.io.File.separator + "log.txt");
					dos = new DataOutputStream(outputStream);
				} catch (IOException e) {
					e.printStackTrace(systemOut);
					dos = null;
				}
			}
			try {
				ArrayList<AOMLoggingItem> al = new ArrayList<AOMLoggingItem>(100);
				while (true) {
					al.clear();
					while( queue.isEmpty() )
					{
						Thread.currentThread().sleep(200);
					}

					int c = queue.drainTo(al, 100);


					
					if( dos != null) {
						try {
							for( int i = 0; i < c; i++ )
							{
								AOMLoggingItem li = al.get(i);
								if( li != null )
								{
									li.write(dos);
									AOMMethodCallItem.returnInstance((AOMMethodCallItem)li);
								}
							}
					
						} catch (Throwable e) {
							try {
								dos.close();
								outputStream.close();
								break;
							} catch (IOException e1) {
								dos = null;
								outputStream = null;
								break;
							}
						}
					}
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace(systemOut);
			}

		}

	}

}
