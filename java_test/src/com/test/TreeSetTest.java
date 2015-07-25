package com.test;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args)
	{
		//使用Item类中的compareTo方法进行TreeSet中的排序，partNumber比较
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("Toaster", 1234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 9912));
		System.out.println(parts);
		
		//指定不同的compare方法，将Comparator子类对象传递给TreeSet的构造器
		SortedSet<Item> sortByDescription = new TreeSet<Item>(new 
				Comparator<Item>()
				{
					public int compare(Item a, Item b)
					{
						String descrA = a.getDescription();
						String descrB = b.getDescription();
						return descrA.compareTo(descrB);
					}
				}
		);
		
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}
	
}

//放入TreeSet中的对象需要可比性
class Item implements Comparable<Item>
{
	private String description;
	private int partNumber;
	
	public Item(String aDescription, int aPartNumber)
	{
		description = aDescription;
		partNumber = aPartNumber;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String toString()
	{
		return "[description=" + description + 
				", partNumber=" + partNumber + "]";
	}
	
	public boolean equals(Object otherObject)
	{
		//引用是否相同
		if (this == otherObject) return true;
		//另一个对象是否为空
		if (otherObject == null) return false;
		//类型是否相同
		if (getClass() != otherObject.getClass()) return false;
		Item other = (Item) otherObject;
		return description.equals(other.description)
				&& partNumber == other.partNumber;
	}
	
	public int hashCode()
	{
		return 13 * description.hashCode() + 17 * partNumber;
	}
	
	//实现接口Comparable的方法
	@Override
	public int compareTo(Item other) {
		// TODO Auto-generated method stub
		return partNumber - other.partNumber;
	}
	
}