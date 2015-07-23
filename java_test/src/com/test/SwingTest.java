package com.test;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.*;

public class SwingTest {
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				NotHellpWorldFrame frame = new NotHellpWorldFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
//JFrame主框架
class NotHellpWorldFrame extends JFrame
{
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	public NotHellpWorldFrame()
	{
		setTitle("NotHelloWorld");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		NotHelloWorldPanel panel = new NotHelloWorldPanel();
		add(panel);
	}
}
// 组件，可以添加到主框架的内容窗格中
class NotHelloWorldPanel extends JPanel
{
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	public void paintComponent(Graphics g)
	{
		g.drawString("Not a Hello World program", MESSAGE_X, MESSAGE_Y);
	}
}
