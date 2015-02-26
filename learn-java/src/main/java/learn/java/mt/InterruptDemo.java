package learn.java.mt;


/**
 * @author Kuo Zhang
 * 
 * This class proves that if a thread is running, but not blocked, than method interrupt() doesn't work.
 */
public class InterruptDemo extends Thread
{

    private static boolean stop = false;

    public static void main( String[] args ) throws InterruptedException
    {
        InterruptDemo it = new InterruptDemo();
        System.out.println( "Start the app." );
        it.start();
        Thread.sleep( 1 );
        System.out.println( "Interrupt the running thread, you will see it doesn't work!" );
        it.interrupt();
        System.out.println( "End the app." );
        stop = true;
    }

    @Override
    public void run()
    {
        while( !stop )
        {
            System.out.println( "Thread is running..........." );
        }

        System.out.println( "Thread ends" );
    }

}
