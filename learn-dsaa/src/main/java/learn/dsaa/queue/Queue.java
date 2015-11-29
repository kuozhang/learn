
package learn.dsaa.queue;

/**
 * <p> FIFO: first in, first out 
 *
 *
 * @author Kuo Zhang
 */
public interface Queue<T>
{

    public void enqueue( T t );

    public T dequeue();
    
    // returns the element, but does not remove
    public T element();

    public boolean isEmpty();
}
