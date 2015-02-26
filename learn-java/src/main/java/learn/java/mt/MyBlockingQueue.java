package learn.java.mt;

import java.util.LinkedList;
import java.util.Queue;


/**
 * @author Kuo Zhang
 * 
 * A simple implementation of Blocking Queue, use keyword synchronized to control 
 */
public class MyBlockingQueue
{
    private Object notEmpty = new Object();
    private Object notFull = new Object();
    private Queue<Object> linkedList = new LinkedList<Object>();
    private int maxLength = 10;

    public Object take() throws InterruptedException
    {
        synchronized( notEmpty )
        {
            if( linkedList.size() == 0 )
            {
                notEmpty.wait();
            }

            synchronized( notFull )
            {
                if( linkedList.size() == maxLength )
                {
                    notFull.notifyAll();
                }

                return linkedList.poll();
            }

        }
    }

    public Object offer( Object object ) throws InterruptedException
    {
        synchronized( notEmpty )
        {
            if( linkedList.size() == 0 )
            {
                notEmpty.notifyAll();
            }

            synchronized( notFull )
            {
                if( linkedList.size() == maxLength )
                {
                    notFull.wait();
                }

                return linkedList.add( object );
            }

        }
    }

}
