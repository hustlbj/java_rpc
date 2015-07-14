package com.monitor;

/**
 * A class that extends ThreadLocal and exposes the Trace
 * element for the current thread
 * @author pixel
 *
 */
public class Tracer extends ThreadLocal<Trace>
{
	private static Tracer instance = new Tracer();
	public static Tracer getInstance()
	{
		return instance;
	}
	private Tracer()
	{
		
	}

	@Override
	protected Trace initialValue() {
		// TODO Auto-generated method stub
		return new Trace();
	}
	
}
