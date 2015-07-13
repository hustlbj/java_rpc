package com.test;

public class BasicJava {
	public static void main(String[] args) {
		System.out.println("This is a Java test program..");
		sayHello();
		sayHello2("sayHello 1000");
	}
	public static void sayHello() {
		try{
			Thread.sleep(2000);
			System.out.println("sayhello 2000");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void sayHello2(String helloString) {
		try {
			Thread.sleep(1000);
			System.out.println(helloString);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
