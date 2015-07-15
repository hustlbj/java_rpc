package com.monitor;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

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
		classesToSkipList.add("org.jdom");
		
		this.instrumentation.addTransformer(this);
		TraceManager.getInstance().startTraceManager();
	}
	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// TODO Auto-generated method stub
		String dotClassName = className.replace('/', '.');
		
		if (logger.isInfoEnabled())
		{
			logger.info("Transforming class: " + className);
		}
		// Only instrument a class once
		if (instrumentedClassesSet.contains(className))
		{
			return null;
		}
		instrumentedClassesSet.add(className);
		
		// Skip in the list of class prefixes to skip
		for(String classToSkip : classesToSkipList)
		{
			if (dotClassName.startsWith(classToSkip))
			{
				return null;
			}
		}
		try {
			//Create a Javassist CtClass from the byte code
			classPool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
			CtClass cc= classPool.get(dotClassName);
			
			// only instrument unfrozen classes
			if (!cc.isFrozen())
			{
				// We'll add new methods after we modify the existing methods
				List<CtMethod> newMetohdsCtMethods = new ArrayList<CtMethod>();
				
				// Instrument the methods
				CtMethod[] methods = cc.getMethods();
				for (int k = 0; k < methods.length; k ++)
				{
					// Only instrument methods in this class ,not in the super class 
					if (methods[k].getLongName().startsWith(dotClassName))
					{
						String methodNameString = methods[k].getName();
						String longMethodNameString = methods[k].getLongName();
						//methods[k].setName(methodNameString + "$impl");
						
						if(logger.isDebugEnabled())
						{
							logger.debug("Instrumenting method: " + longMethodNameString);
						}
						//{ ( ( com.geekcap.openapm.trace.Trace )com.geekcap.openapm.trace.Tracer.getInstance().get() ).addAction( com.geekcap.openapm.trace.MethodAction.Action.START, \"" + longMethodName + "\" ); }
						methods[k].insertBefore("{((com.monitor.Trace)com.monitor.Tracer.getInstance().get()).addAction(com.monitor.MethodAction.Action.START, \"" + longMethodNameString + "\"); }");
						methods[k].insertAfter("{((com.monitor.Trace)com.monitor.Tracer.getInstance().get()).addAction(com.monitor.MethodAction.Action.END, \"" + longMethodNameString + "\"); }");
					}
				}
				
				if (logger.isDebugEnabled())
				{
					logger.debug("Returning instrumented class: " + cc.getName());
				}
				byte[] newClassfileBuffer = cc.toBytecode();
				return newClassfileBuffer;
			}
			
		}
		catch (IOException ioe)
		{
			logger.error(ioe.getMessage() + ", transforming class " + className + ": returning uninstrumented class", ioe);
		}
		catch (NotFoundException nfe)
		{
			logger.error(nfe.getMessage() + ", transforming class " + className + ": returning uninstrumented class", nfe);
		}
		catch (CannotCompileException cce)
		{
			logger.error(cce.getMessage() + ", transforming class " + className + ": returning uninstrumented class", cce);
		}
		catch (Exception e) 
		{
			logger.error("An error occurred durringg class transformation: " + e.getMessage(), e);
		}
		return null;
	}

}
