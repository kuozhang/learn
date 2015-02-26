package learn.java.initialization;


public class InitializationDemo
{

    // The order is
    // static variable and block in super class ( depend on the orders in class )
    // static variable and block in sub class ( depend on the orders in class )
    // instance variable and block in super class ( depend on the orders in class )
    // constructor in super class 
    // instance variable and block in sub class ( depend on the orders in class )
    // constructor in sub class 
    public static void main( String[] args )
    {
        new Sub();
    }

}

class Sub extends Super
{
    private static String foo = "static variable in Sub class.";

    static
    {
        System.out.println( foo );
        System.out.println( "Static block in Sub class" );
    }

    private String bar = "instance variable in Sub class"; 

    {
        System.out.println( bar );
        System.out.println( "instance block in Sub class" );
    }

    public Sub()
    {
        System.out.println( "Sub constructor" );
    }
}

class Super
{

    private static String foo = "static variable in Super class.";

    static
    {
        System.out.println( foo );
        System.out.println( "Static block in Super class" );
    }

    private String bar = "instance variable in Super class"; 

    {
        System.out.println( bar );
        System.out.println( "instance block" );
    }

    public Super()
    {
        System.out.println( "Super constructor" );
    }
}
