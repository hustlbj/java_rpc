package com.test;

import java.util.*;

public class ManagerTest {
	public static void main(String[] args)
	{
		Manager boss = new Manager("Carl", 8000, 1987, 12, 15);
		boss.setBonus(5000);
		
		Employee[] staff = new Employee[3];
		
		staff[0] = boss;
		staff[1] = new Employee("Harry", 5000, 1989, 10, 1);
		staff[2] = new Employee("Tom", 4000, 1990, 3, 5);
		
		//Employeeʵ����Comparable�ӿڣ�����Employee�����������ʹ��Arrays.sort()
		Arrays.sort(staff);
		
		Employee clonedEmployee = null;
		try {
			clonedEmployee = staff[1].clone();
			//String值不可变，修改后会创建新的String对象，不再引用原来的，浅拷贝不会影响String子对象
			clonedEmployee.setName("cloned");
			clonedEmployee.raiseSalary(10);
			
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//多态绑定
		for (Employee e : staff)
			System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
		if (clonedEmployee != null)
			System.out.println("name=" + clonedEmployee.getName() + ", salary=" + clonedEmployee.getSalary());		
	}
}

/**
 * ��abstract������������ǳ�����
 * @author BJ
 *
 */
abstract class Person
{
	private String name;
	public Person(String n)
	{
		name = n;
	}
	public abstract String getDescription();
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}

/**
 * ����ʵ�ֳ�����ĳ��󷽷�
 * @author BJ
 *
 */
class Employee extends Person implements Comparable<Employee>, Cloneable
{
	//û����ʽ��Persion()��������������ʽ���� Person����������
	public Employee(String n, double s, int year, int month, int day)
	{
		super(n);
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}
	public Employee(String n)
	{
		super(n);
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
		//�ȼ�������Ƿ����
		if (this == otherObject) return true;
		//��μ��otherObject�ǲ��� null
		if (otherObject == null) return false;
		//��������Ƿ���ͬ
		if (getClass() != otherObject.getClass())
			return false;
		Employee other = (Employee)otherObject;
		//���� salary�Ǹ����������
		return getName().equals(other.getName())
				&& salary == other.salary
				&& hireDay.equals(other.hireDay);
	}
	public int hashCode() 
	{
		return 7 * super.getName().hashCode() + 11 * new Double(salary).hashCode() + 13 * hireDay.hashCode();
	}
	public String toString()
	{
		return getClass().getName() + "[name=" + super.getName() + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}
	
	//����ֻ��ͨ��getter����˽����
	private double salary;
	private Date hireDay;
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return String.format("an employee with a salary of $%.2f", salary);
	}

	//ʵ����Comparable�ӿڣ�����ʵ��compareTo����
	@Override
	public int compareTo(Employee other) {
		// TODO Auto-generated method stub
		if (salary < other.salary) return -1;
		if (salary > other.salary) return 1;
		return 0;
	}

	@Override
	protected Employee clone() throws CloneNotSupportedException {
		//Call Object.clone()
		Employee cloned = (Employee)super.clone();
		//clone mutable fields, deeply clone
		cloned.hireDay = (Date)hireDay.clone();
		return cloned;
	}
	
	
}

class Manager extends Employee
{

	public Manager(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		bonus = 0;
	}
	//���Ǹ��෽��
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
	public int hashCode()
	{
		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}
	public String toString()
	{
		return super.toString() + "[bonus=" + bonus + "]";  
	}
	private double bonus;
}