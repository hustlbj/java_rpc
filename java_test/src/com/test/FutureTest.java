package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


public class FutureTest {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory: ");
		String directory = in.nextLine();
		System.out.println("Enter keyword: ");
		String keyword = in .nextLine();
		
		//MatchCounter实现了Callable接口，任务放在call方法中
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		//创建一个FutureTask对象，把call中的任务放在线程中执行
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		Thread t = new Thread(task);
		t.start();
		try
		{
			System.out.println(task.get() + " matching files.");
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			
		}
	}
}

/**
 * MatchCounter类实现了Callable接口，定义了一个异步任务Integer call()返回整型结果
 * @author pixel
 *
 */
class MatchCounter implements Callable<Integer>
{
	private File direcroty;
	private String keyword;
	private int count;
	
	public MatchCounter(File directory, String keyword)
	{
		this.direcroty = directory;
		this.keyword = keyword;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		count = 0;
		try
		{
			File[] files = direcroty.listFiles();
			//对该目录下检索到的数目做统计
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
			for (File file : files)
			{
				//如果是目录的话，就递归查找子目录下的文件
				if (file.isDirectory())
				{
					//还是创建一个异步的任务来递归执行
					MatchCounter counter = new MatchCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				}
				//对文件执行查找关键字，如果找到的话就让count++
				else
				{
					if (search(file)) 
						count++;
				}
			}
			for (Future<Integer> result : results)
			{
				try
				{
					//获取task执行返回的Integer值
					count += result.get();
				}
				catch (ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (InterruptedException e)
		{
		}
		return count;
	}
	
	//读取一个文件，查找keyword，如果找到则返回true
	public boolean search(File file)
	{
		try
		{
			Scanner in = new Scanner(new FileInputStream(file));
			boolean found = false;
			while (!found && in.hasNextLine())
			{
				String line = in.nextLine();
				if (line.contains(keyword))
					found = true;
			}
			in.close();
			return found;
		}
		catch (IOException e)
		{
			return false;
		}
	}
}