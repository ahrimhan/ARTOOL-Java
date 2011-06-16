package kr.ac.kaist.se.artool.dynamicprofile.connector;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;

public class ObjectBroadcastNetworkAnnouncer implements Runnable {
	
	private static ObjectBroadcastNetworkAnnouncer instance;
	
	public static ObjectBroadcastNetworkAnnouncer getInstance()
	{
		if( instance == null )
		{
			instance = new ObjectBroadcastNetworkAnnouncer();
		}
		return instance;
	}
	
	private int port = 0;
	
	public int getSocketPort()
	{
		return port;
	}
	
	private ObjectBroadcastNetworkAnnouncer()
	{
		
	}
	
	
	
	@Override
	public void run() {
		ServerSocket serverSocket;
		try {
			
			serverSocket = new ServerSocket(54321);
			port = serverSocket.getLocalPort();

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.err.println("network announcer is started...");
		while( true )
		{
			try {
				Socket socket = null;
				try
				{
					socket = serverSocket.accept();
				} catch(IOException e)
				{
					break;
				}
				InputStream inputStream = socket.getInputStream();
				DataInputStream ois = new DataInputStream(new BufferedInputStream(inputStream));
				AOMMethodCallItem object;
				while( true )
				{
					object = AOMMethodCallItem.getInstance(ois);
					try
					{
						ObjectBroadcaster.getInstance().broadcast(object);
					}
					catch(Exception ex)
					{
						
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.err.println("network announcer is stopped...");
	}

}
