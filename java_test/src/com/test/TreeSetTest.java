package com.test;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args)
	{
		//ʹ��Item���е�compareTo��������TreeSet�е�����partNumber�Ƚ�
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("Toaster", 1234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 9912));
		System.out.println(parts);
		
		//ָ����ͬ��compare��������Comparator������󴫵ݸ�TreeSet�Ĺ�����
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

//����TreeSet�еĶ�����Ҫ�ɱ���
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
		//�����Ƿ���ͬ
		if (this == otherObject) return true;
		//��һ�������Ƿ�Ϊ��
		if (otherObject == null) return false;
		//�����Ƿ���ͬ
		if (getClass() != otherObject.getClass()) return false;
		Item other = (Item) otherObject;
		return description.equals(other.description)
				&& partNumber == other.partNumber;
	}
	
	public int hashCode()
	{
		return 13 * description.hashCode() + 17 * partNumber;
	}
	
	//ʵ�ֽӿ�Comparable�ķ���
	@Override
	public int compareTo(Item other) {
		// TODO Auto-generated method stub
		return partNumber - other.partNumber;
	}
	
}