package learn.dsaa.list;


/**
 * @author Kuo Zhang
 * 
 * Double list means the nodes in the list has previous pointer and next pointer
 */
public class DoubleList<T>
{
    // header node doesn't store any value
    private DoubleListNode<T> header;
    private DoubleListNode<T> current;

    public DoubleList()
    {
        // header without value
        this.header = new DoubleListNode<T>( null, null, null );
        this.current = this.header;
    }

    public DoubleList( DoubleListNode<T> node )
    {
        // node with value is not required, node can have no value
        this.header = node;
        this.current = this.header;
    }

    public boolean add( T data )
    {
        if( this.current == null )
        {
            this.header = new DoubleListNode<T>( null, null, null );
            this.current = this.header;
        }

        DoubleListNode<T> nodeToAdd = new DoubleListNode<T>( data, null, this.current );

        this.current.next = nodeToAdd;
        this.current = nodeToAdd;

        return true;
    }

/*    public DoubleListNode<T> getCurrent()
    {
        return this.current;
    }*/

}

