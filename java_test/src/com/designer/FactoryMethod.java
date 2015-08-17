package com.designer;

/**
 * ��������
 * �����ӿںͲ�Ʒ�ӿڶ������ã��þ���Ĺ���ʵ������������Ĳ�Ʒʵ��
 * @author BJ
 *
 */
public class FactoryMethod {
	public static void main(String args[])
	{
		//���ã������ӿںͲ�Ʒ�ӿ�
		Creator creator1, creator2;
		Product product1, product2;
		
		//ʵ��
		creator1 = new ConcreteCreator1();
		creator2 = new ConcreteCreator2();
		//����ʵ��1���ز�Ʒ1
		product1 = creator1.factory();
		//����ʵ��2���ز�Ʒ2
		product2 = creator2.factory();
		
		product1.Say();
		product2.Say();
	}
}
/**
 * �����ӿ�
 * @author BJ
 *
 */
interface Creator
{
	public Product factory();
}
/**
 * ��Ʒ�ӿ�
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

//��ͬ�Ĳ�Ʒ��ʵ�ֲ�Ʒ�ӿ�
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


