
package learn.java.mt;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * A simple implementation of Blocking Queue, use Lock and Condition to control
 *
 * @author Kuo Zhang
 */
public class MyBlockingQueue2
{

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private Queue<Object> linkedList = new LinkedList<Object>();
    private int maxLength = 10;

    public Object dequeue() throws InterruptedException
    {
        lock.lock();

        try
        {
            if( linkedList.size() == 0 )
            {
                notEmpty.await();
            }

            if( linkedList.size() == maxLength )
            {
                notFull.signalAll();
            }

            return linkedList.poll();
        }
        finally
        {
            lock.unlock();
        }
    }

    public boolean enqueue( Object object ) throws InterruptedException
    {
        lock.lock();

        try
        {
            if( linkedList.size() == maxLength )
            {
                notFull.await();
            }

            if( linkedList.size() == 0 )
            {
                notEmpty.signalAll();
            }

            return linkedList.add( object );
        }
        finally
        {
            lock.unlock();
        }
    }

}
