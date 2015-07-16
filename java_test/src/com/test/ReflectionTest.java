package com.test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

public class ReflectionTest {
	public static Object goodArragGrow(Object a)
	{
		Class cl = a.getClass();
		if (!cl.isArray())
			return null;
		Class componentType = cl.getComponentType();
		int length = Array.getLength(a);
		int newLength = length * 11 / 10 + 10;
		
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, length);
		return newArray;
	}
	
	public static void arrayPrint(Object a)
	{
		Class cl = a.getClass();
		if (!cl.isArray()) return;
		Class componentType = cl.getComponentType();
		int length = Array.getLength(a);
		System.out.print(componentType.getName() + "[" + length + "] = {");
		for (int i = 0; i < Array.getLength(a); i ++)
		{
			System.out.print(Array.get(a, i) + " ");
		}
		System.out.println("}");
	}

	public static double square(double x)
	{
		return x*x;
	}
	
	public static void main(String[] args) throws Exception
	{
		int[] a = {1, 2, 3};
		a = (int[])goodArragGrow(a);
		arrayPrint(a);
		
		String[] b = {"Tom", "Dick", "Harry"};
		b = (String[]) goodArragGrow(b);
		arrayPrint(b);
		//利用反射获取class中的某个方法
		Method square = ReflectionTest.class.getMethod("square", double.class);
		//调用method.invoke(object, args)对原方法进行调用
		double y = (Double)square.invoke(null, 10.0);
		System.out.println("method.invoke: " + y);
	}
	
}
