
package learn.java.mt;

/**
 * <p>
 * start multiple threads to print the increment values form 1 to 100
 *
 * @author Kuo Zhang
 */
public class PrintIncrementsDemo implements Runnable
{

    private static int count = 0;

    public static void main( String[] args )
    {
        for( int i = 0; i < 100; i++ )
        {
            new Thread( new PrintIncrementsDemo() ).start();
        }
    }

    @Override
    public void run()
    {
        print();
    }

    // the "static synchronized"
    public synchronized static void print()
    {
        System.out.println( ++count );
    }

    // or
    // public static void print()
    // {
    // synchronized(PrintIncrementsDemo.class){
    // System.out.println( ++count );
    // }
    // }

}
