package com.test;

public class StaticInnerClassTest {
	public static void main(String[] args)
	{
		double[] d = new double[20];
		for (int i = 0; i < d.length; i++) {
			d[i] = 100 * Math.random();
		}
		ArrayAlg.Pair pair = ArrayAlg.minmax(d);
		System.out.println("min=" + pair.getFitst() + ", max=" + pair.getSecond());
	}
}

class ArrayAlg
{
	/**
	 * A pair of floating-point numbers
	 * static inner class
	 */
	public static class Pair
	{
		private double first;
		private double second;
		
		public Pair(double f, double s)
		{
			first = f;
			second = s;
		}
		public double getFitst()
		{
			return first;
		}
		public double getSecond()
		{
			return second;
		}
	}
	/*static inner class end*/
	
	/**
	 * a static method of outer class
	 * @param values
	 * @return
	 */
	public static Pair minmax(double[] values)
	{
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (double v : values)
		{
			if (min > v) min = v;
			if (max < v) max = v;
		}
		return new Pair(min, max);
	}
}