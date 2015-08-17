package com.designer;

/**
 * 工厂方法
 * 工厂接口和产品接口定义引用，用具体的工厂实现来创建具体的产品实现
 * @author BJ
 *
 */
public class FactoryMethod {
	public static void main(String args[])
	{
		//引用，工厂接口和产品接口
		Creator creator1, creator2;
		Product product1, product2;
		
		//实现
		creator1 = new ConcreteCreator1();
		creator2 = new ConcreteCreator2();
		//工厂实现1返回产品1
		product1 = creator1.factory();
		//工厂实现2返回产品2
		product2 = creator2.factory();
		
		product1.Say();
		product2.Say();
	}
}
/**
 * 工厂接口
 * @author BJ
 *
 */
interface Creator
{
	public Product factory();
}
/**
 * 产品接口
 * @author BJ
 *
 */
interface Product
{
	public void Say();
}

class ConcreteCreator1 implements Creator
{

	@Override
	public Product factory() {
		// TODO Auto-generated method stub
		return new ConcreteProduct1();
	}
	
}

class ConcreteCreator2 implements Creator
{

	@Override
	public Product factory() {
		// TODO Auto-generated method stub
		return new ConcreteProduct2();
	}
	
}

//不同的产品，实现产品接口
class ConcreteProduct1 implements Product
{

	@Override
	public void Say() {
		// TODO Auto-generated method stub
		System.out.println("Product1 Say");
	}
	
}

class ConcreteProduct2 implements Product
{

	@Override
	public void Say() {
		// TODO Auto-generated method stub
		System.out.println("Product2 Say");
	}
	
}


