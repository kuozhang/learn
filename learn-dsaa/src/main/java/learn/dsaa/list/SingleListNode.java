
package learn.dsaa.list;

/**
 * <p>
 * Single List Node should be a private class in a tree.
 * </p>
 *
 * @author Kuo Zhang
 */
public class SingleListNode<T>
{

    public T data;
    public SingleListNode<T> next;

    public SingleListNode( T data )
    {
        this( data, null );
    }

    public SingleListNode( T data, SingleListNode<T> next )
    {
        this.data = data;
        this.next = next;
    }
}
