package com.monitor;

import java.lang.instrument.Instrumentation;

public class Agent{
	public static void premain(String args, Instrumentation inst) {
		System.out.println("Agent is running...Args: " + args);
		//inst.addTransformer(new DurationTransformer());
		inst.addTransformer(new TraceTransformer(inst));
	}
}
