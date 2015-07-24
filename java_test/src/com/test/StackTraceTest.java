package com.test;

import java.util.Scanner;

public class StackTraceTest {
	public static int factorial(int n)
	{
		System.out.println("factorial(" + n + "):");
		Throwable throwable = new Throwable();
		StackTraceElement[] frameStackTraceElements = throwable.getStackTrace();
		for (StackTraceElement f : frameStackTraceElements)
			System.out.println(f);
		int r;
		if (n <= 1) r = 1;
		else r = n * factorial(n-1);
		System.out.println("return " + r);
		return r;
	}
	public static void main(String[] args)
	{
		Scanner inScanner = new Scanner(System.in);
		System.out.print("Enter n: ");
		int n = inScanner.nextInt();
		factorial(n);
	}
}
