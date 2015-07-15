package com.monitor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * A Trace is a collection of method actions
 * Maintains a list of "actions", such as the start of a method,
 * the end of a method, or the exceptional exit of a method, 
 * as well as the time stamp when the action occurred.
 * @author pixel
 *
 */
public class Trace implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(Trace.class);
	
	/**
	 * The trace key, which will be assigned by the TraceManager
	 */
	private String traceKey;
	
	/**
	 * The ID of the head of the trace, which is used to identify the end of the trace
	 */
	private String headId;
	
	/**
	 * The list of MethodActions (START, END, or EXCEPTION) that participated in this trace
	 */
	private List<MethodAction> actions = new LinkedList<MethodAction>();
	
	public String getTraceKey() {
		return traceKey;
	}

	public void setTraceKey(String traceKey) {
		this.traceKey = traceKey;
	}

	public List<MethodAction> getActions() {
		return actions;
	}

	public void setActions(List<MethodAction> actions) {
		this.actions = actions;
	}

	/**
	 * Create a new Trace
	 */
	public Trace()
	{
		
	}
	
	public void addAction(MethodAction.Action action, String id)
	{
		if (logger.isTraceEnabled())
		{
			logger.trace("Add action: [" + action + "] " + id);
		}
		if (actions.size() == 0)
		{
			headId = id;
			traceKey = TraceManager.getInstance().startTrace(this);
		}
		actions.add(new MethodAction(action, id));
		if (action == MethodAction.Action.END && id.equals(headId))
		{
			TraceManager.getInstance().endTrace(traceKey);
		}
	}
	
}
