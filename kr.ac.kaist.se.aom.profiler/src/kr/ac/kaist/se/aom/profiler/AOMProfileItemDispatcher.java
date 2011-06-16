package kr.ac.kaist.se.aom.profiler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.SynchronousQueue;

public class AOMProfileItemDispatcher implements Runnable {

	private SynchronousQueue<AOMLoggingItem> queue;
	private static PrintStream systemOut;
	private PrintStream ps;
	private static AOMProfileItemDispatcher instance = null;
	private boolean isStarted;
	private static Socket socket;
	private static OutputStream outputStream;
	private static DataOutputStream dos;

	private AOMProfileItemDispatcher() {
		queue = new SynchronousQueue<AOMLoggingItem>();
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
			if (socket == null) {
				try {
					socket = new Socket("127.0.0.1", 54321);
					outputStream = socket.getOutputStream();
					dos = new DataOutputStream(outputStream);
				} catch (UnknownHostException e) {
					e.printStackTrace(systemOut);
					socket = null;
					dos = null;
				} catch (IOException e) {
					e.printStackTrace(systemOut);
					socket = null;
					dos = null;
				}
			}
			try {
				while (true) {
					AOMMethodCallItem item = (AOMMethodCallItem)queue.take();

					if (item != null && dos != null) {
						try {
							item.write(dos);
							
//							AOMProfilingLogger.getErrorStream().println(item.toString());
						} catch (Throwable e) {
							try {
								dos.close();
								outputStream.close();
								socket.close();
								break;
							} catch (IOException e1) {
								dos = null;
								outputStream = null;
								socket = null;
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
