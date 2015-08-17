package com.designer;

/**
 * AbstractFactory designer
 * ���󹤳�
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
		System.out.println("���ڳ�������");
	}
	
}
class SoybeanMilk extends Drinks
{

	@Override
	public void drinking() {
		// TODO Auto-generated method stub
		System.out.println("���ںȶ�����");
	}
	
}
class Bread extends StapleFood
{

	@Override
	public void eating() {
		// TODO Auto-generated method stub
		System.out.println("���ڳ������");
	}
	
}
class Milk extends Drinks
{

	@Override
	public void drinking() {
		// TODO Auto-generated method stub
		System.out.println("���ں�ţ�̣�");
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
		System.out.println("--------------��һ��------------");
		System.out.println("��Ҫ���в�");
		BreakfastFactory bf1 = new BreakfastCStyle();
		sf = bf1.MakeStapleFood();
		dk = bf1.MakeDrinks();
		sf.eating();
		dk.drinking();
		
		System.out.println("--------------�ڶ���------------");
		System.out.println("��Ҫ������");
		BreakfastFactory bf2 = new BreakfastWStyle();
		sf = bf2.MakeStapleFood();
		dk = bf2.MakeDrinks();
		sf.eating();
		dk.drinking();
	}
}
