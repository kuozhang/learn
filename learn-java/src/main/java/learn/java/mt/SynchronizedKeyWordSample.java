
package learn.java.mt;

/**
 * @author Kuo Zhang
 */
public class SynchronizedKeyWordSample
{

    public synchronized void method1()
    {
        // do something
    }

    public void method2()
    {
        synchronized( this )
        {
            // do something
        }
    }

    public synchronized static void method3()
    {
        // do something
    }

    public void method4()
    {
        synchronized( this.getClass() )
        {
            // do something
        }

        // also
        synchronized( SynchronizedKeyWordSample.class )
        {
            // do something
        }
    }

}
