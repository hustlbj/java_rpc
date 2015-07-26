package com.test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args)
	{
		Map<String, Employee> staffMap = new HashMap<String, Employee>();
		staffMap.put("144-25-5464",  new Employee("Amy Lee"));
		staffMap.put("567-24-2546", new Employee("Harry Hacker"));
		staffMap.put("157-62-7935", new Employee("Gary Cooper"));
		staffMap.put("456-62-5527", new Employee("Francesca Cruz"));
		
		System.out.println(staffMap);
		
		staffMap.remove("567-24-2546");
		
		staffMap.put(null, new Employee("Key null"));
		staffMap.put("567-24-2546", new Employee("Francesca Miller"));
		
		System.out.println(staffMap.get("157-62-7935"));
		
		for (Map.Entry<String, Employee> entry : staffMap.entrySet())
		{
			String keyString = entry.getKey();
			Employee value = entry.getValue();
			System.out.println("key=" + keyString + ", value=" + value);
		}
	}
}
