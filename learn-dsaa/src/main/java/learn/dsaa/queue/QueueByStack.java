
package learn.dsaa.queue;

import learn.dsaa.exception.EmptyQueueException;
import learn.dsaa.stack.MyStack;
import learn.dsaa.stack.StackByList;

/**
 * <p>
 * Implemented by the two Stacks
 * </p>
 *
 * @author Kuo Zhang
 */
public class QueueByStack<T> implements MyQueue<T>
{

    // stack1 is used to enqueue
    private MyStack<T> stack1;

    // stack2 is used to dequeue
    private MyStack<T> stack2;

    public QueueByStack()
    {
        stack1 = new StackByList<T>();
        stack2 = new StackByList<T>();
    }

    @Override
    public void enqueue( T t )
    {
        stack1.push( t );
    }

    @Override
    public T dequeue()
    {
        T retval = null;

        if( !stack2.isEmpty() )
        {
            retval = stack2.pop();
        }
        else
        {
            if( !stack1.isEmpty() )
            {
                // transfer all elements in stack1 to stack2
                while( !stack1.isEmpty() )
                {
                    stack2.push( stack1.pop() );
                }

                retval = stack2.pop();
            }
            else
            {
                throw new EmptyQueueException();
            }
        }

        return retval;
    }

    @Override
    public boolean isEmpty()
    {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
