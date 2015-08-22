package com.test;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TextFileTest {
	public static void main(String args[])
	{
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Carl", 7500, 1987, 12, 15);
		staff[1] = new Employee("Marry", 5000, 1989, 10, 1);
		staff[2] = new Employee("Tony", 4900, 1990, 3, 15);
		try
		{
			PrintWriter out = new PrintWriter("employee.txt");
			writeData(staff, out);
			out.close();
			
			Scanner in = new Scanner(new FileReader("employee.txt"));
			Employee[] newStaffs = readData(in);
			in.close();
			for (Employee e : newStaffs)
			{
				System.out.println(e);
			}
		}
		catch(IOException exception)
		{
			exception.printStackTrace();
		}
	}
	
	private static void writeData(Employee[] employees, PrintWriter out) throws IOException
	{
		//write number of employees
		out.println(employees.length);
		for (Employee e : employees)
		{
			e.writeData(out);
		}
	}
	private static Employee[] readData(Scanner in)
	{
		int n = in.nextInt();
		in.nextLine(); //jump to next line
		Employee[] employees = new Employee[n];
		for (int i = 0; i < n; i ++)
		{
			employees[i] = new Employee();
			employees[i].readData(in);
		}
		return employees;
	}
	
	
}