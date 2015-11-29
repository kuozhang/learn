
package learn.java.misc;

public class InheritanceDemo
{

    public static void main( String[] args )
    {
        B b = new B();
        A a = new B();
        A a2 = (A) new B();

        System.out.println( b.instanceField );
        System.out.println( b.instanceMethod() );

        System.out.println( a.instanceField ); // Fields in super class cannot be override
        System.out.println( a.instanceMethod() );

        System.out.println( a2.instanceField );
        System.out.println( a2.instanceMethod() );

        System.out.println();

        System.out.println( b.staticField );
        System.out.println( b.staticMethod() );

        System.out.println( a.staticField);
        System.out.println( a.staticMethod() );

        System.out.println( a2.staticField);
        System.out.println( a2.staticMethod() );
    }
}

class A
{

    public String instanceField = "instance field in super class";

    protected String instanceMethod()
    {
        return "instance method in super class";
    }

    public static String staticField = "static field in super class";

    protected static String staticMethod()
    {
        return "static method in super class";
    }

}

class B extends A
{

    public String instanceField = "instance field in sub class";

    @Override
    public String instanceMethod()
    {
        return "instance method in sub class";
    }

    public String staticField = "static field in sub class";

    protected static String staticMethod()
    {
        return "static method in sub class";
    }
}
