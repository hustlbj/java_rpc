package com.test;

public class Pair<T> {
	private T firstT;
	private T secondT;
	
	public Pair() {firstT = null; secondT = null; }
	public Pair(T first, T second) 
	{ 
		firstT = first;
		secondT = second;
	}
	public T getFirsT()
	{
		return firstT;
	}
	public T getSecondT()
	{
		return secondT;
	}
	public void setFistT(T newValue)
	{
		firstT = newValue;
	}
	public void setSecondT(T newValue)
	{
		secondT = newValue;
	}
}
