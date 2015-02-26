
package learn.java.innerclass;

/**
 * @author Kuo Zhang
 *
 * usage of inner class and nested class
 */
public class Outter
{

    private static String staticVariable = "staticVariable";

    private String instanceVariable = "instanceVariable";

    public Outter()
    {
    }

    private static class StaticInner
    {
        private static String staticVariable = "sataticVariable"; // 静态内部类中可以有静态的成员,甚至静态类

        public StaticInner()
        {
        }

        public void method()
        {
            // System.out.println( instanceVariable ); cannot use non-static member
            System.out.println( staticVariable ); // can use static member
        }

        // 静态类中还可以有静态类
        private static class StaticInnerInStaticInner
        {
        }
    }

    private class NonStaticInner
    {
        // private static String staticVariable = "staticVariable"; 非静态内部类中不能有静态的成员和静态类

        public NonStaticInner()
        {
            System.out.println( instanceVariable ); // can use non-static member
            System.out.println( staticVariable ); // can use static member
        }
    }

    public void test()
    {
        // create an instance of inner class
        Outter.StaticInner saticInner = new Outter.StaticInner();

        // create an instance of non-inner class
        Outter outter = new Outter();
        NonStaticInner nonStaticInner = outter.new NonStaticInner();
    }
}
