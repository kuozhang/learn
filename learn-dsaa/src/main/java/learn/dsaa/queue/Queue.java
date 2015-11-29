
package learn.dsaa.queue;

/**
 * @author Kuo Zhang
 */
public interface Queue<T>
{

    public void enqueue( T t );

    public T dequeue();

    public boolean isEmpty();
}
