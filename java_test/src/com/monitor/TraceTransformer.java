package com.monitor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javassist.ClassPool;

import org.apache.log4j.Logger;

public class TraceTransformer implements ClassFileTransformer {
	
	private static final Logger logger = Logger.getLogger(TraceTransformer.class);
	
	protected Instrumentation instrumentation = null;
	protected ClassPool classPool;
	protected Set<String> instrumentedClassesSet = new HashSet<String>();
	protected List<String> classesToSkipList = new ArrayList<String>();
	
	public TraceTransformer(Instrumentation instrumentation)
	{
		this.instrumentation = instrumentation;
		classPool = ClassPool.getDefault();
		classesToSkipList.add("javax.");
		classesToSkipList.add("java.");
		classesToSkipList.add("sun.");
		classesToSkipList.add("com.sun.");
		classesToSkipList.add("org.apache.");
		this.instrumentation.addTransformer(this);

	}
	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// TODO Auto-generated method stub
		return null;
	}

}
