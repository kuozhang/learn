
package learn.java.mt;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kuo Zhang start multiple threads to print the increment values form 1 to 100
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
    public static synchronized void print()
    {
        System.out.println( ++count );
    }

}
