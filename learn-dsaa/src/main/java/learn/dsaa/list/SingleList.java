package learn.dsaa.list;



/**
 * @author Kuo Zhang
 * 
 * Single list means the nodes in the list has no previous pointer
 */
public class SingleList<T>
{
    // header doesn't store value
    private SingleListNode<T> header;

    // usually the current node is the tail node
    private SingleListNode<T> current;

    public SingleList()
    {
        this.header = new SingleListNode<T>( null, null );
        this.current = this.header;
    }

    public SingleList( T data )
    {
        this.header = new SingleListNode<T>( null, null );
        this.current = this.header;

        add( data );
    }

    public void add( T data )
    {
        if( this.current == null )
        {
            this.header = new SingleListNode<T>( null, null );
            this.current = this.header;
        }

        SingleListNode<T> nodeToAdd = new SingleListNode<T>( data, null );

        this.current.next = nodeToAdd;
        this.current = nodeToAdd;
    }

    public SingleListNode<T> getHeader()
    {
        return this.header;
    }

    public SingleListNode<T> getCurrent()
    {
        return this.current;
    }

    public SingleListNode<T> getTail()
    {
        return this.current;
    }

}

