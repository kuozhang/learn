
package learn.java.mt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * A simple implementation of Blocking Queue, use keyword synchronized to control
 *
 * @author Kuo Zhang
 */
public class MyBlockingQueue
{

    private Object notEmpty = new Object();
    private Object notFull = new Object();
    private Queue<Object> linkedList = new LinkedList<Object>();
    private int maxLength = 10;

    public Object take() throws InterruptedException
    {
        // 如果没有获得对象的锁，线程进入到对象的锁池中，进入阻塞状态
        synchronized( notEmpty )
        {
            if( linkedList.size() == 0 )
            {
                // 释放对象的锁，线程进入到对象的等待池中，线程到达阻塞状态，等待 notify 或 notifyAll 方法唤醒，从而进入就绪状态
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
                // 唤醒 调用 notEmpty.wait() 的线程
                // 只是唤醒，并未立即执行， 直到该线程执行完后释放锁, 其他线程才能继续执行
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
