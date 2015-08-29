package com.oj;

import java.util.Scanner;

public class StringTest {
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		int count = CalcCapital(str);
		System.out.println(count);
	}
	public static int CalcCapital(String str)
	{
		int count = 0;
		if (str == null || str.length() == 0)
			return 0;
		else
		{
			for (int i = 0; i < str.length(); i ++)
			{
				if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
				{
					count ++;
				}
			}
			return count;
		}
	}
}
