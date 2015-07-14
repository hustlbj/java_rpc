package com.monitor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;


public class DurationTransformer implements ClassFileTransformer {
	final static List<String> methodList = new ArrayList<String>();
	static {
		methodList.add("com.test.BasicJava.sayHello");
		methodList.add("com.test.BasicJava.sayHello2");
	}
	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// TODO Auto-generated method stub
		byte[] byteCode = classfileBuffer;
		System.out.println("class: " + className);
		if (className.startsWith("com/test")) {
			ClassPool classPool = ClassPool.getDefault();
			try {
				//Compile-time class，允许你查找方法、注释、域、构造器，和反射机制不同的地方是CtClass还允许你修改字节码
				CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
				//解冻之后才能修改，否则会报出class is frozen
				ctClass.defrost();
				CtMethod[] methods = ctClass.getDeclaredMethods();
				for (CtMethod method : methods) {
					System.out.println("method: " + method.getName());
					// addParameter()可以给方法签名添加新的参数
					// addLocalVariable()则是给方法添加局部变量
					// setBody()可以直接设置方法的整个方法体
					method.addLocalVariable("startTime", CtClass.longType);
					method.insertBefore("startTime = System.nanoTime(); ");
					method.insertAfter("System.out.println(\"Execution Duration(nano secs): \" + (System.nanoTime() - startTime));");
					byteCode = ctClass.toBytecode();
					ctClass.detach();
					System.out.println("Instrumentation complete!");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return byteCode;
	}

}
