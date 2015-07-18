package com.test;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This program demonstrates the use of inner clases
 * @author pixel
 *
 */
public class InnerClass {
	public static void main(String[] args)
	{
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
		
	}

}

/**
 * A clock that prints the time in regular intervals
 */
class TalkingClock
{
	private int interval;
	private boolean beep;
	
	public TalkingClock(int interval, boolean beep)
	{
		this.interval = interval;
		this.beep = beep;
	}
	
	public void start()
	{
		//创建一个实现了ActionListener的回调对象
		ActionListener listener = new TimerPrinter();
		//java.swing.Timer，用于窗口界面定时
		Timer t = new Timer(interval, listener);
		t.start();
	}
	
	/**
	 * inner class
	 */
	public class TimerPrinter implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Date now = new Date();
			System.out.println("At the tone, the time is " + now);
			// inner class can access outer class's private fields
			if (beep)
				Toolkit.getDefaultToolkit().beep();
		}
		
	}
}
