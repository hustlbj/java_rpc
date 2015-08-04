package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AlgorithmAnimation {
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new AnimationFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
}

class AnimationFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	
	public AnimationFrame()
	{
		ArrayComponent comp = new ArrayComponent();
		add(comp, BorderLayout.CENTER);
		final Sorter sorter = new Sorter(comp);
		
		JButton runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sorter.setRun();
			}
			
		});
		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sorter.setStep();
			}
			
		});
		
		JPanel buttons = new JPanel();
		buttons.add(runButton);
		buttons.add(stepButton);
		add(buttons, BorderLayout.NORTH);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		Thread t = new Thread(sorter);
		t.start();
	}
}

class Sorter implements Runnable
{
	private Double[] values;
	private ArrayComponent component;
	private Semaphore gate;
	private static final int DELAY = 100;
	private volatile boolean run;
	private static final int VALUES_LENGTH = 30;
	
	public Sorter(ArrayComponent comp)
	{
		values = new Double[VALUES_LENGTH];
		for (int i = 0; i < values.length; i ++)
			values[i] = new Double(Math.random());
		this.component = comp;
		//创建一个只有一个许可permit的信号量
		this.gate = new Semaphore(1); 
		this.run = false;
	}
	
	/**
	 * 开始运行并连续运行
	 */
	public void setRun()
	{
		run = true;
		//释放一个许可
		gate.release();
	}
	/**
	 * 单步运行
	 */
	public void setStep()
	{
		run = false;
		gate.release();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//自定义的比较器，里面可以利用信号量实现暂停比较
		Comparator<Double> stepCompare = new Comparator<Double>()
		{
			@Override
			public int compare(Double arg0, Double arg1) {
				// TODO Auto-generated method stub
				//把当前比较的两个元素发送给界面component
				component.setValues(values, arg0, arg1);
				try
				{
					//如果run是true，表示是一直运行，不需要暂停，所以不用信号量acquire
					if (run) 
						Thread.sleep(DELAY);
					else
						//等待信号量，获取到1个许可后返回继续执行
						gate.acquire();
				}
				catch (InterruptedException exception)
				{
					Thread.currentThread().interrupt();
				}
				return arg0.compareTo(arg1);
			}
			
		};
		//开始排序
		Arrays.sort(values, stepCompare);
		//排序完后显示数组，不再需要marked1和marked2
		component.setValues(values, null, null);
	}
	
}

class ArrayComponent extends JComponent
{
	private Double marked1;
	private Double marked2;
	private Double[] values;
	
	public synchronized void setValues(Double[] values, Double marked1, Double marked2)
	{
		this.values = values.clone();
		this.marked1 = marked1;
		this.marked2 = marked2;
		repaint();
	}
	public synchronized void paintComponent(Graphics g)
	{
		if (values == null)
			return;
		Graphics2D g2 = (Graphics2D)g;
		int width = getWidth() / values.length;
		for (int i = 0; i < values.length; i ++)
		{
			double height = values[i] * getHeight();
			Rectangle2D bar = new Rectangle2D.Double(width * i, 0, width, height);
			if (values[i] == marked1 || values[i] == marked2)
				g2.fill(bar);
			else
				g2.draw(bar);
		}
	}
}