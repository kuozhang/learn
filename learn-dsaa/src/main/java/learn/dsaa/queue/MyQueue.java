package learn.dsaa.queue;


/**
 * @author Kuo Zhang
 * 
 */
public interface MyQueue<T>
{
    public void enqueue( T t );

    public T dequeue();

    public boolean isEmpty();
}
