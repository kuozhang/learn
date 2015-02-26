package learn.dsaa.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * @author Kuo Zhang
 *
 */
public class TestQueue
{
    @Test
    public void testQueueByStack()
    {
        MyQueue<Integer> queue = new QueueByStack<>(); 

        assertEquals( true, queue.isEmpty() );

        queue.enqueue( 1 );
        queue.enqueue( 2 );
        queue.enqueue( 3 );
        queue.enqueue( 4 );
        queue.enqueue( 5 );

        assertEquals( 1, queue.dequeue().intValue() );

        assertEquals( false, queue.isEmpty() );

        assertEquals( 2, queue.dequeue().intValue() );
        assertEquals( 3, queue.dequeue().intValue() );
        assertEquals( 4, queue.dequeue().intValue() );

        queue.enqueue( 6 );
        queue.enqueue( 7 );
        queue.enqueue( 8 );
        queue.enqueue( 9 );
        queue.enqueue( 10 );

        assertEquals( 5, queue.dequeue().intValue() );

        assertEquals( false, queue.isEmpty() );

        assertEquals( 6, queue.dequeue().intValue() );
        assertEquals( 7, queue.dequeue().intValue() );
        assertEquals( 8, queue.dequeue().intValue() );
        assertEquals( 9, queue.dequeue().intValue() );
        assertEquals( 10, queue.dequeue().intValue() );

        assertEquals( true, queue.isEmpty() );
    }

}
