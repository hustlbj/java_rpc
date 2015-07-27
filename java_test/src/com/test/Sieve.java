package com.test;

import java.util.BitSet;

public class Sieve {
	public static void main(String[] args)
	{
		int n = 2000000;
		long start = System.currentTimeMillis();
		BitSet bitSet = new BitSet(n + 1);
		int count = 0;
		int i;
		//首先将所有位置为开状态
		for (i = 2; i <= n; i ++)
			bitSet.set(i);
		//已知素数
		i = 2;
		while (i * i <= n)//最外层循环到n的开方即可
		{
			//已知素数
			if (bitSet.get(i))
			{
				count ++;
				//把已知素数的倍数所对应的位都置为关状态
				int k = 2 * i; //2倍
				while (k <= n)
				{
					bitSet.clear(k);
					k += i; //3倍及以后
				}
			}
			i ++;
		}
		while (i <= n)
		{
			if (bitSet.get(i))
				count ++;
			i ++;
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " primes");
		System.out.println((end - start) + " milliseconds");
	}
}
