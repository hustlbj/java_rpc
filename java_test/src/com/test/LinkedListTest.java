package com.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListTest {
	public static void main(String[] args)
	{
		List<String> aList = new LinkedList<String>();
		aList.add("Amy");
		aList.add("Carl");
		aList.add("Erica");
		
		List<String> bList = new LinkedList<String>();
		bList.add("Bob");
		bList.add("Doung");
		bList.add("Frances");
		bList.add("Gloria");
		
		//可以反向previous遍历的子迭代器ListIterator，
		//LinkedList中有ListIterator迭代器，使得LinkedList可以从尾部访问、添加、删除元素
		ListIterator<String> aIterator = aList.listIterator();
		Iterator<String> bIterator = bList.iterator();
		
		while (bIterator.hasNext())
		{
			if (aIterator.hasNext())
				aIterator.next();
			aIterator.add(bIterator.next());
		}
		
		System.out.println(aList);
		
		bIterator = bList.iterator();
		
		while (bIterator.hasNext())
		{
			bIterator.next(); //skip one element
			if (bIterator.hasNext())
			{
				bIterator.next(); //skip next element
				bIterator.remove(); //remove that element
			}
		}
		
		System.out.println(bList);
		
		// bulk operation: remove all words in bList from aList
		aList.removeAll(bList);
		System.out.println(aList);

	}
}
