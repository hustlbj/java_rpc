package com.jvm;

/**
 * VM Args: -Xss128k
 * @author BJ
 *
 */
public class StackError {
	private int stackLength = 1;
	
	public void stackLeak() 
	{
		stackLength ++;
		stackLeak();
	}
	
	public static void main(String[] args)
	{
		StackError se = new StackError();
		try
		{
			se.stackLeak();
		}
		catch(Throwable e)
		{
			System.out.println("stack length: " + se.stackLength);
			throw e;
		}
	}
	
}
