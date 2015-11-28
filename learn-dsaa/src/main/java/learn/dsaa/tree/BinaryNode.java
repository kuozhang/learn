
package learn.dsaa.tree;

/**
 * <p>
 * same as the BinaryTree$BinaryNode, for normal use
 * </p>
 *
 * @author Kuo Zhang
 */
public class BinaryNode<T extends Comparable<? super T>> implements Node
{

    T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode( T data )
    {
        this( data, null, null );
    }

    public BinaryNode( T data, BinaryNode<T> left, BinaryNode<T> right )
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData()
    {
        return data;
    }

    public void setData( T data )
    {
        this.data = data;
    }

    public BinaryNode<T> getLeft()
    {
        return left;
    }

    public void setLeft( BinaryNode<T> left )
    {
        this.left = left;
    }

    public BinaryNode<T> getRight()
    {
        return right;
    }

    public void setRight( BinaryNode<T> right )
    {
        this.right = right;
    }

}
