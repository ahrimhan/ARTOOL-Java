package kr.ac.kaist.se.artool.dynamicprofile.connector;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.SynchronousQueue;

import kr.ac.kaist.se.aom.profiler.AOMMethodCallItem;

public class ObjectBroadcaster implements Runnable {
	
	
	private static ObjectBroadcaster instance;
	
	public static ObjectBroadcaster getInstance()
	{
		if( instance == null )
		{
			instance = new ObjectBroadcaster();
		}
		
		return instance;
	}
	private HashMap<Class<?>, Vector<ObjectBroadcastListener>> listenerSet;
	private Vector<Class<?>> typeSet;
	private SynchronousQueue<AOMMethodCallItem> queue;
	
	
	private ObjectBroadcaster()
	{
		listenerSet = new HashMap<Class<?>, Vector<ObjectBroadcastListener>>();
		typeSet = new Vector<Class<?>>();
		queue = new SynchronousQueue<AOMMethodCallItem>();
	}
	
	
	@Override
	public void run() {
		try {
			System.err.println("Broadcaster is started...");
			while(true)
			{
				AOMMethodCallItem obj = queue.take();
				Vector<ObjectBroadcastListener> listeners = listenerSet.get(obj.getClass());
				for( ObjectBroadcastListener listener : listeners )
				{
					listener.broadcastedObject(obj);
				}
			}
		} catch (InterruptedException e) {

		}
		System.err.println("Broadcaster is stopped...");
	}
	
	public void broadcast(AOMMethodCallItem object) throws InterruptedException
	{
		queue.put(object);
	}
	
	public void addListener(Class<?> type, ObjectBroadcastListener listener)
	{
		Vector<ObjectBroadcastListener> listeners = null;
		if( listenerSet.containsKey(type) )
		{
			listeners = listenerSet.get(type);
		}
		else
		{
			listeners = new Vector<ObjectBroadcastListener>();
			listenerSet.put(type, listeners);
			typeSet.add(type);
		}
		
		listeners.add(listener);
	}
	
	public void removeListener(Class<?> type, ObjectBroadcastListener listener)
	{
		Vector<ObjectBroadcastListener> listeners = null;
		if( listenerSet.containsKey(type) )
		{
			listeners = listenerSet.get(type);
			listeners.remove(listener);
//			if( listeners.isEmpty() )
//			{
//				listenerSet.remove(type);
//				typeSet.remove(type);
//			}
		}		
	}
}
