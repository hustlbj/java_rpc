package com.algorithm;

/**
 * 在主串中匹配模式串，并返回匹配到模式串的起始位置
 * 蛮力搜索O(m*n)的时间复杂度，重复匹配步骤太多
 * KMP算法的核心是发现模式串中的“前缀=后缀”，避免在模式串j位置发生匹配失败时还要回退到0位置开始匹配
 * 发现“前缀=后缀”，只需要将模式串向前移动合适的位置，跳过了重复匹配前缀的步骤
 * @author pixel
 *
 */
public class KMP {
	public static int kmp(String majorString, String pattern)
	{
		if (majorString == null || pattern == null || majorString == "" || pattern == "")
			return 0;
		int[] next = makeNext(pattern);
		int i = 0, j = 0;
		//只扫一遍主串并且不回退，每次当匹配模式串中的pattern[j]失败时，就将模式串前移next[j]，并且避免了重复匹配
		while (i < majorString.length() && j < pattern.length())
		{
			if (j == -1 || majorString.charAt(i) == pattern.charAt(j))
			{
				i ++;
				j ++;
			}
			else
			{
				j = next[j];
			}
		}
		if (j == pattern.length())
			return i - j;
		return -1;
	}
	/**
	 * 构造next数组，next[j]表示：
	 * 为了寻找在pattern串的子串P0...Pj中，
	 * 前缀P0 P1 ... Pk-1 = 后缀Pj-k Pj-k+1 ... Pj时，最大的k值
	 * @param pattern
	 * @return next数组
	 */
	private static int[] makeNext(String pattern)
	{
		int[] next = new int[pattern.length()];
		int k = -1;
		int j = 0;
		//当j=0时next[j]为-1
		next[j] = -1;
		//递推next[j+1]
		//next[j]是在P0...Pj-1中找前缀=后缀的最大k使得P0...Pk-1 = Pj-k...Pj-1
		//所以next[j+1]就是在P0...Pj中找，而next[j]时已经判断了P[-1]=P[j-1]，现在只需判断P[k]?P[j]
		while (j < pattern.length() - 1)
		{
			//Pk == Pj的情况：next[j+1]=k+1 也就是 next[j+1]=next[k]+1
			if (k == -1 || pattern.charAt(k) == pattern.charAt(j))
			{
				next[++j] = ++k;
			}
			else
			{
				//Pk != Pj的情况：递推k = next[k]，不断在上一次的前缀中寻找Pk=Pj
				k = next[k];
			}
		}
		return next;
	}
	
	public static void main(String args[])
	{
		String major = "aababcdad";
		String pattern = "abcd";
		System.out.println(kmp(major, pattern));
	}
}
