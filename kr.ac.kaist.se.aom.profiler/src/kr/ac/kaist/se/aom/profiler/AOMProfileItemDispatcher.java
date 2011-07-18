package kr.ac.kaist.se.aom.profiler;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AOMProfileItemDispatcher implements Runnable {

	private BlockingQueue<ByteBuffer> faqueue;
	private BlockingQueue<ByteBuffer> mcqueue;
	private BlockingQueue<ByteBuffer> pool;
	private static PrintStream systemOut;
	private PrintStream ps;
	private static AOMProfileItemDispatcher instance = null;
	private boolean isStarted;
	private DatagramChannel faSocket;
	private DatagramChannel mcSocket;
	public final static String HOST = "localhost";
	
	private AOMProfileItemDispatcher() {
		mcqueue = new ArrayBlockingQueue<ByteBuffer>(5000);
		faqueue = new ArrayBlockingQueue<ByteBuffer>(5000);
//		pool = new ArrayBlockingQueue<ByteBuffer>(5000);
//		
//		for( int i = 0 ; i < 5000 ; i ++ )
//		{
//			try {
//				pool.put(ByteBuffer.allocate(512));
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
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
	
	public ByteBuffer getFreeBuffer() throws InterruptedException
	{
		return null;
//		return ByteBuffer.allocate(1024);
//		return pool.take();
	}
	
	public void returnFreeBuffer(ByteBuffer buffer) throws InterruptedException 
	{
		buffer.clear();
//		pool.put(buffer);
	}

	public void giveWork(ByteBuffer item, boolean isMC) throws InterruptedException {
		if( isMC ) 
		{
			mcqueue.put(item);
		}
		else
		{
			faqueue.put(item);
		}
	}

	@Override
	public void run() {

		while (true) {
//			if (mcWriter == null) {
//				try {
//					mcWriter = new FileWriter(AOMProfiler.getLogPath() + java.io.File.separator + "DynamicMethodCallLog.txt");
//					mcPrintWriter = new PrintWriter(mcWriter);
//				} catch (IOException e) {
//					e.printStackTrace(systemOut);
//					mcPrintWriter = null;
//				}
//			}
//			
//			if (faWriter == null) {
//				try {
//					faWriter = new FileWriter(AOMProfiler.getLogPath() + java.io.File.separator + "DynamicFieldAccessLog.txt");
//					faPrintWriter = new PrintWriter(faWriter);
//				} catch (IOException e) {
//					e.printStackTrace(systemOut);
//					faPrintWriter = null;
//				}
//			}
			
			if( faSocket == null )
			{
				try {
					faSocket = DatagramChannel.open();
					
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if( mcSocket == null )
			{
				try {
					mcSocket = DatagramChannel.open();
					

				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				ArrayList<ByteBuffer> al = new ArrayList<ByteBuffer>(500);
				int c;
				SocketAddress mcAddr = new InetSocketAddress(HOST, 18001);
				SocketAddress faAddr = new InetSocketAddress(HOST, 18002);
				while (true) {
					
					while( faqueue.isEmpty() && mcqueue.isEmpty() )
					{
						Thread.currentThread().sleep(200);
					}
					al.clear();
					c = mcqueue.drainTo(al, 500);
					
					if(  mcSocket != null ) {
						try {
							for( int i = 0; i < c; i++ )
							{
								ByteBuffer li = al.get(i);
								mcSocket.send(li, mcAddr);
								this.returnFreeBuffer(li);
							}
						} catch (Throwable e) {
							try {
								mcSocket.disconnect();
								mcSocket.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							break;
						}
					}
					
					al.clear();
					c = faqueue.drainTo(al, 500);
					if(  faSocket != null ) {
						try {
							for( int i = 0; i < c; i++ )
							{
								ByteBuffer li = al.get(i);
								faSocket.send(li, faAddr);
								this.returnFreeBuffer(li);

							}
					
						} catch (Throwable e) {
							try {
								faSocket.disconnect();
								faSocket.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
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
