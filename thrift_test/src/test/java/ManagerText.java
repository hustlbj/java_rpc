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

/**
 * 包含abstract方法的类必须是抽象类
 * @author BJ
 *
 */
abstract class Person
{
	public Person(String n)
	{
		name = n;
	}
	public abstract String getDescription();
	public String getName()
	{
		return name;
	}
	private String name;
}

/**
 * 必须实现抽象父类的抽象方法
 * @author BJ
 *
 */
class Employee extends Person
{
	//没有隐式的Persion()构造器，必须显式调用 Person的其他构造器
	public Employee(String n, double s, int year, int month, int day)
	{
		super(n);
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
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
	public boolean equals(Object otherObject)
	{
		//先检查引用是否相等
		if (this == otherObject) return true;
		//其次检查otherObject是不是 null
		if (otherObject == null) return false;
		//检查类型是否相同
		if (getClass() != otherObject.getClass())
			return false;
		Employee other = (Employee)otherObject;
		//这里 salary是浮点数。。。。。
		return getName().equals(other.getName())
				&& salary == other.salary
				&& hireDay.equals(other.hireDay);
	}
	
	//子类只能通过getter访问私有域
	private double salary;
	private Date hireDay;
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return String.format("an employee with a salary of $%.2f", salary);
	}
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
	public boolean equals(Object otherObject)
	{
		if (!super.equals(otherObject)) return false;
		Manager other = (Manager)otherObject;
		return bonus == other.bonus;
	}
	private double bonus;
}