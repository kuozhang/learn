
package learn.dsaa.list;

/**
 * @author Kuo Zhang
 */
public class DoubleListNode<T>
{

    public T data;
    public DoubleListNode<T> next;
    public DoubleListNode<T> prev;

    public DoubleListNode( T data )
    {
        this( data, null, null );
    }

    public DoubleListNode( T data, DoubleListNode<T> next, DoubleListNode<T> prev )
    {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

}
