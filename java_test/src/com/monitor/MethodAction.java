package com.monitor;

import java.io.Serializable;

/**
 * A method action denotes the start, end or exceptional exit of
 * a method and maintains the time stamp in which the action 
 * occurred 
 * @author pixel
 *
 */
public class MethodAction implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Action means a method is starting / ending / throwing an exception
	 * @author pixel
	 *
	 */
	public enum Action
	{
		START, END, EXCEOTION
	};
	/**
	 * The action for this event: START, END, or EXCEPTION
	 */
	private Action action; 
	/**
	 * The id for this event, which should be the fully qualified method name
	 */
	private String id;
	/**
	 * The timestamp when this event occurred
	 */
	private long timestamp;
	
	/**
	 * Create a new MethodAction
	 * @param action	The method action: START, END or EXCEPTION
	 * @param id		The id/fully qualified name of the method
	 */
	public MethodAction(Action action, String id)
	{
		setTimestamp(System.currentTimeMillis());
		this.setAction(action);
		this.setId(id);
	}
	
	public MethodAction(long timestamp, Action action, String id)
	{
		this.setTimestamp(timestamp);
		this.setAction(action);
		this.setId(id);
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return timestamp + ": [" + action + "]\t" + id;
	}
	
}
