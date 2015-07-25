package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HashSetTest {
	public static void main(String[] args) throws FileNotFoundException
	{
		Set<String> words = new HashSet<String>();
		long totalTime = 0;
		//设置System.in为文件流输入
		System.setIn(new FileInputStream(new File("words.txt")));
		//Scanner以换行符作为一次输入的截止放到输入缓冲区（整个输入过程还没终止），以空白作为分隔
		Scanner in = new Scanner(System.in);
		//以文件结束符作为输入流的终止
		while (in.hasNext())
		{
			String word = in.next();
			long callTime = System.currentTimeMillis();
			words.add(word);
			callTime = System.currentTimeMillis() - callTime;
			totalTime += callTime;
		}
		
		Iterator<String> iter = words.iterator();
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}
		System.out.println("...");
		System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
	}
}
