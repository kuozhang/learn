
package learn.dsaa.tree;

/**
 * <p>
 * same as AVLTree$AVLNode, for normal use
 * </p>
 * 
 * @author Kuo Zhang
 */
@SuppressWarnings( "rawtypes" )
public class AVLNode<T extends Comparable<? super T>> extends BinaryNode
{

    private int height;

    public AVLNode( T data )
    {
        this( data, null, null );
    }

    public AVLNode( T data, AVLNode<T> left, AVLNode<T> right )
    {
        this( data, left, right, 0 );
    }

    @SuppressWarnings( "unchecked" )
    public AVLNode( T data, AVLNode<T> left, AVLNode<T> right, int height )
    {
        super( data, left, right );
        this.height = height;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight( int height )
    {
        this.height = height;
    }

}
