package com.test;

import java.util.ArrayList;

public class ArrayListTest {
	public static void main(String[] args) 
	{
		ArrayList<Employee> staff = new ArrayList<Employee>();
		staff.add(new Employee("Carl", 7500, 1987, 12, 15));
		staff.add(new Employee("Harry", 5000, 1989, 10, 1));
		staff.add(new Employee("Tony", 4000, 1990, 3, 15));
		
		for (Employee e: staff)
		{
			e.raiseSalary(5);
		}
		for (Employee e: staff)
		{
			//e.toString
			System.out.println(e);
		}
	}
}
