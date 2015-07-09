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
		//��̬���ã���̬��
		for (Employee e : staff)
			System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
	}
}

/**
 * ����abstract������������ǳ�����
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
 * ����ʵ�ֳ�����ĳ��󷽷�
 * @author BJ
 *
 */
class Employee extends Person
{
	//û����ʽ��Persion()��������������ʽ���� Person������������
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
		//�ȼ�������Ƿ����
		if (this == otherObject) return true;
		//��μ��otherObject�ǲ��� null
		if (otherObject == null) return false;
		//��������Ƿ���ͬ
		if (getClass() != otherObject.getClass())
			return false;
		Employee other = (Employee)otherObject;
		//���� salary�Ǹ���������������
		return getName().equals(other.getName())
				&& salary == other.salary
				&& hireDay.equals(other.hireDay);
	}
	
	//����ֻ��ͨ��getter����˽����
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
	private double bonus;
}