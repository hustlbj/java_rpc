package com.test;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.*;

public class BlockingQueueTest {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory: ");
		String directory = in.nextLine();
		System.out.print("Enter keyword: ");
		String keyword = in.nextLine();
		
		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 8;
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<File> (FILE_QUEUE_SIZE);
		
		FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
		new Thread(enumerator).start();
		for (int i = 1; i <= SEARCH_THREADS; i ++)
		{
			new Thread(new SearchTask(queue, keyword)).start();
		}
	}
	
}

class FileEnumerationTask implements Runnable
{
	public static File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startingDirectory;
	
	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory)
	{
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}

	@Override
	public void run() {
		try {
			enumerate(startingDirectory);
			//添加最后一个哑元，表示结束
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//搜索文件夹下的文件，可递归
	public void enumerate(File directory) throws InterruptedException
	{
		File[] files = directory.listFiles();
		for (File file : files)
		{
			if (file.isDirectory()) enumerate(file);
			//生产者，把搜索到的文件添加到阻塞队列BlockingQueue<File> queue中
			else queue.put(file);
		}
	}
}

class SearchTask implements Runnable
{
	private BlockingQueue<File> queue;
	private String keyword;
	
	public SearchTask(BlockingQueue<File> queue, String keyword)
	{
		this.queue = queue;
		this.keyword = keyword;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			boolean done = false;
			while (!done)
			{
				File file = queue.take();
				//发现哑元结束符，则结束本搜索线程
				//再把哑元结束符放回去，其它搜索线程发现后也会终止
				if (file == FileEnumerationTask.DUMMY)
				{
					queue.put(file);
					done = true;
				}
				else
					search(file);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			
		}
	}
	
	public void search(File file) throws IOException
	{
		Scanner in = new Scanner(new FileInputStream(file));
		int lineNumber = 0;
		while (in.hasNextLine())
		{
			lineNumber ++;
			String line = in.nextLine();
			if (line.contains(keyword))
				System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
		}
		in.close();
	}
	
}