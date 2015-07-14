package com.monitor;
import com.monitor.MethodAction;
import com.monitor.Tracer;
import com.monitor.MethodAction.Action;

public class TraceTest {
	private InnerClass ic = new InnerClass();

    public TraceTest()
    {
        Tracer.getInstance().get().AddAction( Action.START, "TraceTest.Constructor" );
        try
        {
            Thread.sleep( 20 );
        }
        catch( Exception e )
        {
        }
        Tracer.getInstance().get().AddAction( Action.END, "TraceTest.Constructor" );
    }

    public void method1()
    {
        Tracer.getInstance().get().AddAction( Action.START, "TraceTest.method1" );
        try
        {
            Thread.sleep( 100 );
        }
        catch( Exception e )
        {
        }
        method11();
        Tracer.getInstance().get().AddAction( Action.END, "TraceTest.method1" );
    }

    public void method11()
    {
        Tracer.getInstance().get().AddAction( Action.START, "TraceTest.method11" );
        try
        {
            Thread.sleep( 1000 );
        }
        catch( Exception e )
        {
        }
        ic.doSomething();
        Tracer.getInstance().get().AddAction( Action.END, "TraceTest.method11" );
    }

    public void method2()
    {
        Tracer.getInstance().get().AddAction( Action.START, "TraceTest.method2" );
        try
        {
            Thread.sleep( 50 );
        }
        catch( Exception e )
        {
        }
        Tracer.getInstance().get().AddAction( Action.END, "TraceTest.method2" );
    }

    public static void main( String[] args )
    {
        Tracer.getInstance().get().AddAction( Action.START, "TraceTest.main()" );
        MyThread thread1 = new MyThread( 1 );
        MyThread thread2 = new MyThread( 2 );
        thread1.start();

        try
        {
            // Try to stagger the threads...
            Thread.sleep( 20 );
        }
        catch( Exception e )
        {
        }

        thread2.start();
        Tracer.getInstance().get().AddAction( Action.END, "TraceTest.main()" );
    }

    class InnerClass
    {
        public InnerClass()
        {
            Tracer.getInstance().get().AddAction( Action.START, "InnerClass.Constructor" );
            try
            {
                Thread.sleep( 50 );
            }
            catch( Exception e )
            {
            }
            Tracer.getInstance().get().AddAction( Action.END, "InnerClass.Constructor" );
        }

        public void doSomething()
        {
            Tracer.getInstance().get().AddAction( Action.START, "InnerClass.doSomething" );
            try
            {
                Thread.sleep( 50 );
            }
            catch( Exception e )
            {
            }
            Tracer.getInstance().get().AddAction( Action.END, "InnerClass.doSomething" );
        }
    }
}

class MyThread extends Thread
{
    private TraceTest TraceTest = new TraceTest();

    private int id;

    public MyThread( int id )
    {
        this.id = id;
    }

    @Override
    public void run()
    {
        Tracer.getInstance().get().AddAction( Action.START, "MyThread.run()" );
        TraceTest.method1();
        TraceTest.method2();
        TraceTest.method1();
        Tracer.getInstance().get().AddAction( Action.END, "MyThread.run()" );
        StringBuilder sb = new StringBuilder();
        sb.append( "Thread " ).append( id ).append( " Methods: \n");
        for( MethodAction ma : Tracer.getInstance().get().getActions() )
        {
            sb.append( "\t[" ).append( id ).append( "] - " ).append( ma ).append( "\n");
        }
        System.out.println( sb );
    }
}
