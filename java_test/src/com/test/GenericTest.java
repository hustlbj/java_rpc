package com.test;

public class GenericTest {
	public static void main(String[] args)
	{
		String[] wordsStrings = {"Mary", "had", "a", "little", "lamb"};
		Pair<String> mmPair = GenericTest.minmax(wordsStrings);
		System.out.println("min: " + mmPair.getFirsT());
		System.out.println("max: " + mmPair.getSecondT());
	}

	/**
	 * 定义在普通类中的泛型方法
	 * @param a 	T[]泛型数组
	 * @return		返回数组中值
	 */
	public static <T> T getMiddle(T[] a)
	{
		return a[a.length / 2];
	}
	
	/**
	 * 测试泛型类Pair<T>
	 * @param a
	 * @return
	 */
	public static Pair<String> minmax(String[] a)
	{
		if (a == null || a.length == 0)
			return null;
		String min = a[0];
		String max = a[0];
		for (int i = 1; i < a.length; i ++)
		{
			if (min.compareTo(a[i]) > 0)
				min = a[i];
			if (max.compareTo(a[i]) < 0)
				max = a[i];
		}
		return new Pair<String>(min, max);
	}
	
	public static <T extends Comparable> Pair<T> newMinMax(T[] a)
	{
		if (a == null || a.length == 0)
			return null;
		T min = a[0];
		T max = a[0];
		for (int i = 1; i < a.length; i ++)
		{
			if (min.compareTo(a[i]) > 0) 
				min = a[i];
			if (max.compareTo(a[i]) < 0)
				max = a[i];
		}
		return new Pair<T>(min, max);
	}
}
