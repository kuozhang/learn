package learn.dsaa.list;


/**
 * @author Kuo Zhang
 *
 * for convenient use, ususally Node should be a private class in a tree
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
