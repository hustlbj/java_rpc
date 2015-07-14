package com.monitor;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Manages all traces
 * When a trace ends, the trace is sent to the TraceManager, 
 * which maintains all traces for the threads running in the JVM
 * @author pixel
 *
 */
public class TraceManager implements Serializable, Runnable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(TraceManager.class);
	/**
	 * The instance of the TraceManager
	 */
	private static TraceManager instance = new TraceManager();
	/**
	 * Maintains all active traces
	 */
	private Map<String, Trace> activeTraces = new HashMap<String, Trace>();
	/**
	 * Maintains all traces that have been completed
	 */
	private Map<String, Trace> completedTraces = new HashMap<String, Trace>();
	/**
	 * True if the TraceManager thread is running false otherwise
	 */
	private boolean running = false;
	private boolean started = false;
	
	/**
	 * Counter used for ensuring that trace ids are unique 
	 */
	private long counter = 0;
	
	/**
	 * Singleton accessor method
	 * @return	Return the instance of the TraceManager
	 */
	public static TraceManager getInstance() 
	{
		return instance;
	}
	private TraceManager()
	{
		
	}
	
	public synchronized String startTrace(Trace trace)
	{
		String key = Long.toString(System.currentTimeMillis()) + "-" + counter++;
		activeTraces.put(key, trace);
		if (!started)
		{
			started = true;
		}
		return key;
	}
	public void endTrace(String key)
	{
		if (activeTraces.containsKey(key))
		{
			completedTraces.put(key, activeTraces.remove(key));
		}
	}
	private void saveTraces()
	{
		if (logger.isInfoEnabled())
		{
			logger.info("Saving traces");
		}
		try {
			TraceWriter.write(completedTraces, new FileOutputStream("trace-file.xml"));
		} catch (Exception e) {
			logger.error("An error occurred while saving trace file: " + e.getMessage());
		}
		for (String key : completedTraces.keySet())
		{
			Trace trace = completedTraces.get(key);
			StringBuilder sb = new StringBuilder("Trace[").append(key).append("]:\n");
			for (MethodAction maAction : trace.getActions())
			{
				sb.append("\t" + maAction + "\n");
			}
			System.out.println(sb.toString());
		}
	}
	
	public void startTraceManager()
	{
		if (logger.isInfoEnabled())
		{
			logger.info("Starting TraceManager");
		}
		running = true;
		started = false;
		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		if (logger.isInfoEnabled())
		{
			logger.info("TraceManager started");
		}
		while ((!started || activeTraces.size() > 0) && running)
		{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				logger.error("interruped...");
			}
		}
		saveTraces();
		if (logger.isTraceEnabled())
		{
			logger.info("TraceManager shutting down");
		}
	}
	
}
