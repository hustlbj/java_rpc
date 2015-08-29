package com.jvm;

public class StackLeakByThread {
	private void dontStop()
	{
		while(true)
			;
	}
	public void stackLeakByThread()
	{
		while(true)
		{
			Thread thread = new Thread(new Runnable()
			{
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dontStop();
				}
				
			});
			thread.start();
		}
	}
}
