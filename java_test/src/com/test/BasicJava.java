package com.test;

public class BasicJava {

	public static void main(String[] args) {
		System.out.println("This is a Java test program..");
		sayHello();
	}
	
	public static void sayHello() {
		try{
			Thread.sleep(2000);
			System.out.println("sayhello 2000");
			sayHello2("sayHello2....");
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
