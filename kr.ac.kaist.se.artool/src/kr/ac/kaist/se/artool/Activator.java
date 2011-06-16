package kr.ac.kaist.se.artool;

import kr.ac.kaist.se.artool.dynamicprofile.connector.ObjectBroadcastNetworkAnnouncer;
import kr.ac.kaist.se.artool.dynamicprofile.connector.ObjectBroadcaster;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "kr.ac.kaist.se.artool";

	// The shared instance
	private static Activator plugin;
	
	
	private static Thread broadCasterThread;
	private static Thread networkAnnouncerThread; 
	/**
	 * 
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		if( broadCasterThread == null )
		{
			broadCasterThread = new Thread(ObjectBroadcaster.getInstance());
			broadCasterThread.start();
		}
		
		if( networkAnnouncerThread == null )
		{
			networkAnnouncerThread = new Thread(ObjectBroadcastNetworkAnnouncer.getInstance());
			networkAnnouncerThread.start();
		}
		
		
	}
	


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		Thread b = broadCasterThread;
		broadCasterThread = null;
		b.interrupt();

		Thread n = networkAnnouncerThread;
		networkAnnouncerThread = null;
		n.interrupt();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
