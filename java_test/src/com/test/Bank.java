package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private final double[] accounts;
	private Lock bankLock;
	private Condition sufficientFunds;
	
	public Bank(int n, double initialBalance)
	{
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i ++)
		{
			accounts[i] = initialBalance;
		}
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}
	/**
	 * Lock和Condition方式实现的同步
	 * 账户from向账户to转钱，如果账户from中的钱不满足数额，则当前线程进入await；
	 * 当前线程处理完转钱以后，用条件.signalAll()解除其他线程的阻塞
	 * @param from
	 * @param to
	 * @param amount
	 * @throws InterruptedException
	 */
	public void transfer(int from, int to, double amount) throws InterruptedException
	{
		bankLock.lock();
		try
		{
			while (accounts[from] < amount)
				sufficientFunds.await();
			System.out.println(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf("%10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf("Total Balance: %10.2f\n", getTotalBalance());
			sufficientFunds.signalAll();
		}
		finally
		{
			bankLock.unlock();
		}
	}
	
	/**
	 * synchronized 对象内部锁实现的同步
	 * @param from
	 * @param to
	 * @param amount
	 * @throws InterruptedException
	 */
	public synchronized void transfer_syn(int from, int to, double amount) throws InterruptedException
	{
		while (accounts[from] < amount)
			wait();
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf("%10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf("Total Balance: %10.2f\n", getTotalBalance());
		notifyAll();
	}

	public double getTotalBalance() {
		bankLock.lock();
		try
		{
			double sum = 0;
			for (double a : accounts)
				sum += a;
			return sum;
		}
		finally
		{
			bankLock.unlock();
		}
		//后面不需要再返回，因为获取到锁以后，不会有什么异常
	}
	
	public int size()
	{
		return accounts.length;
	}
}
