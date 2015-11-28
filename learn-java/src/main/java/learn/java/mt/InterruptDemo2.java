
package learn.java.mt;

/**
 * @author Kuo Zhang This proves that if a thread is blocked, then the method interrupt() works.
 */
public class InterruptDemo2 extends Thread
{

    private static boolean sleep = false;

    public static void main( String[] args ) throws InterruptedException
    {
        InterruptDemo2 it = new InterruptDemo2();
        System.out.println( "Start the app." );
        it.start();
        Thread.sleep( 1 );
        System.out.println( "Let the thread sleep for a while" );
        sleep = true;
        System.out.println( "Interrupt the running thread." );
        it.interrupt();
    }

    @Override
    public void run()
    {
        while( true )
        {
            System.out.println( "Thread is running..........." );

            if( sleep )
            {
                try
                {
                    System.out.println( "Sleeping..." );
                    Thread.sleep( 1000 );
                }
                catch( InterruptedException e )
                {
                    System.out.println( "Thread got interrupted" );
                    System.out.println( "Thread ends" );
                    return;
                }
            }
        }
    }

}
