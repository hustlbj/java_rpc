package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * OutOfMemoryError test
 * @author BJ
 *
 */
public class HeapError {
	static class OOMObject {
		
	}
	public static void main(String[] args)
	{
		List<OOMObject> list = new ArrayList<OOMObject>();
		long number = 0;
		try{
			while (true)
			{
				list.add(new OOMObject());
				number ++;
			}
		}
		catch (OutOfMemoryError e)
		{
			System.out.println("Object numbers: " + number);
			e.printStackTrace();
		}
	}
	
	
}
