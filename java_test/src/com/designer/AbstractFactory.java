package com.designer;

/**
 * AbstractFactory designer
 * 抽象工厂
 * @author BJ
 *
 */
abstract class BreakfastFactory
{
	public abstract StapleFood MakeStapleFood();
	public abstract Drinks MakeDrinks();
}
abstract class StapleFood
{
	public abstract void eating();
}
abstract class Drinks
{
	public abstract void drinking();
}
class DeepFriedDoughSticks extends StapleFood
{

	@Override
	public void eating() {
		// TODO Auto-generated method stub
		System.out.println("我在吃油条！");
	}
	
}
class SoybeanMilk extends Drinks
{

	@Override
	public void drinking() {
		// TODO Auto-generated method stub
		System.out.println("我在喝豆浆！");
	}
	
}
class Bread extends StapleFood
{

	@Override
	public void eating() {
		// TODO Auto-generated method stub
		System.out.println("我在吃面包！");
	}
	
}
class Milk extends Drinks
{

	@Override
	public void drinking() {
		// TODO Auto-generated method stub
		System.out.println("我在喝牛奶！");
	}
	
}
class BreakfastCStyle extends BreakfastFactory
{

	@Override
	public StapleFood MakeStapleFood() {
		// TODO Auto-generated method stub
		return new DeepFriedDoughSticks();
	}

	@Override
	public Drinks MakeDrinks() {
		// TODO Auto-generated method stub
		return new SoybeanMilk();
	}
	
}
class BreakfastWStyle extends BreakfastFactory
{

	@Override
	public StapleFood MakeStapleFood() {
		// TODO Auto-generated method stub
		return new Bread();
	}

	@Override
	public Drinks MakeDrinks() {
		// TODO Auto-generated method stub
		return new Milk();
	}
	
}
/**
 * client
 * @author BJ
 *
 */
public class AbstractFactory {
	public static void main(String args[])
	{
		StapleFood sf;
		Drinks dk;
		System.out.println("--------------第一天------------");
		System.out.println("我要吃中餐");
		BreakfastFactory bf1 = new BreakfastCStyle();
		sf = bf1.MakeStapleFood();
		dk = bf1.MakeDrinks();
		sf.eating();
		dk.drinking();
		
		System.out.println("--------------第二天------------");
		System.out.println("我要吃西餐");
		BreakfastFactory bf2 = new BreakfastWStyle();
		sf = bf2.MakeStapleFood();
		dk = bf2.MakeDrinks();
		sf.eating();
		dk.drinking();
	}
}
