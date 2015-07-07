package test.java;

import java.util.*;

public class ManagerText {
	public static void main(String[] args)
	{
		Manager boss = new Manager("Carl", 8000, 1987, 12, 15);
		boss.setBonus(5000);
		
		Employee[] staff = new Employee[3];
		
		staff[0] = boss;
		staff[1] = new Employee("Harry", 5000, 1989, 10, 1);
		staff[2] = new Employee("Tom", 4000, 1990, 3, 5);
		//多态引用，动态绑定
		for (Employee e : staff)
			System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
	}
}

class Employee
{
	public Employee(String n, double s, int year, int month, int day)
	{
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}
	
	public String getName()
	{
		return name;
	}
	public double getSalary()
	{
		return salary;
	}
	public Date getHireDay()
	{
		return hireDay;
	}
	public void raiseSalary(double byPercent)
	{
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	//子类只能通过getter访问私有域
	private String name;
	private double salary;
	private Date hireDay;
}

class Manager extends Employee
{

	public Manager(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		bonus = 0;
	}
	//覆盖父类方法
	public double getSalary()
	{
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}
	public void setBonus(double b)
	{
		bonus = b;
	}
	private double bonus;
}