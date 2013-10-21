package org.clubrockisen.entrycounter;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Launcher for the EntryCounter application.<br />
 * @author Alex
 */
public final class App {
	/** Logger */
	private static Logger		lg			= Logger.getLogger(App.class.getName());
	
	/** The name of the application */
	public static final String	APP_NAME	= "EntryCounter";

	/**
	 * Constructor #1.<br />
	 * @param args
	 *        the arguments of the application.
	 */
	public App (final String[] args) {
		super();
		if (lg.isLoggable(Level.INFO)) {
			lg.info("Launching EntryCounter with arguments " + Arrays.toString(args));
		}
	}
	
	/**
	 * Launch the application.
	 */
	public void launch () {
		if (lg.isLoggable(Level.INFO)) {
			lg.info(APP_NAME + " application is starting...");
		}
		
		
		if (lg.isLoggable(Level.INFO)) {
			lg.info(APP_NAME + " application is running.");
		}
	}
	
	/**
	 * Entry point of the application.
	 * @param args
	 *        the arguments from the command line.
	 */
	public static void main (final String[] args) {
		new App(args).launch();
	}
}
