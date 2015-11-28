
package learn.dsaa.tree;

/**
 * @author Kuo Zhang
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{

    private static class BinaryNode<AnyType>
    {

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        public BinaryNode( AnyType element )
        {
            this( element, null, null );
        }

        public BinaryNode( AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right )
        {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree()
    {
        root = null;
    }

    public void makeEmpty()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public boolean contains( AnyType x )
    {
        return contains( root, x );
    }

    private boolean contains( BinaryNode<AnyType> root, AnyType x )
    {
        if( root == null )
        {
            return false;
        }

        if( root.element.compareTo( x ) > 0 )
        {
            return contains( root.left, x );
        }
        else if( root.element.compareTo( x ) < 0 )
        {
            return contains( root.right, x );
        }
        else
        {
            return true;
        }
    }

    public AnyType findMin()
    {
        if( isEmpty() )
        {
            return null;
        }

        return findMin( root ).element;
    }

    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> root )
    {
        if( root.left == null )
        {
            return root;
        }

        return findMin( root.left );
    }

    public AnyType findMax()
    {
        if( isEmpty() )
        {
            return null;
        }

        return findMax( root ).element;
    }

    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> root )
    {
        if( root.right == null )
        {
            return root;
        }

        return findMax( root.right );
    }

    public void insert( AnyType x )
    {
        if( !contains( x ) )
        {
            root = insert( root, x );
        }
    }

    private BinaryNode<AnyType> insert( BinaryNode<AnyType> root, AnyType x )
    {
        if( root == null )
        {
            return new BinaryNode<>( x );
        }

        if( x.compareTo( root.element ) < 0 )
        {
            root.left = insert( root.left, x );
        }
        else if( x.compareTo( root.element ) > 0 )
        {
            root.right = insert( root.right, x );
        }

        return root;
    }

    public void remove( AnyType x )
    {
        if( contains( x ) )
        {
            root = remove( root, x );
        }
    }

    private BinaryNode<AnyType> remove( BinaryNode<AnyType> root, AnyType x )
    {
        if( x.compareTo( root.element ) < 0 )
        {
            root.left = remove( root.left, x );
        }
        else if( x.compareTo( root.element ) > 0 )
        {
            root.right = remove( root.right, x );
        }
        else
        {
            if( root.left == null && root.right == null )
            {
                root = null;
            }
            else if( root.left == null && root.right != null )
            {
                root = root.right;
            }
            else if( root.right == null && root.left != null )
            {
                root = root.left;
            }
            else
            {
                // Replace this element with the minimum value of right child tree and remove that element
                AnyType thisElement = findMin( root.right ).element;
                root.element = thisElement;
                root.right = remove( root.right, thisElement );
            }

            /*
             * Can also be like this, the above one is easier to be understood
             */
            /*
             * if( root.left != null && root.right != null ) { AnyType thisElement = findMin( root.right ).element;
             * root.element = thisElement; root.right = remove( root.right, thisElement ); } else { root = ( root.left
             * != null ) ? root.left : root.right; }
             */
        }

        return root;
    }

    public void preordeTraversal()
    {
        if( isEmpty() )
        {
            System.out.println( "Empty Tree!" );
        }
        else
        {
            preorderTraversal( root );
        }
    }

    private void preorderTraversal( BinaryNode<AnyType> x )
    {
        if( x != null )
        {
            System.out.println( x.element );
            preorderTraversal( x.left );
            preorderTraversal( x.right );
        }
    }

    public void inorderTraversal()
    {
        if( isEmpty() )
        {
            System.out.println( "Empty Tree!" );
        }
        else
        {
            inorderTraversal( root );
        }
    }

    private void inorderTraversal( BinaryNode<AnyType> x )
    {
        if( x != null )
        {
            inorderTraversal( x.left );
            System.out.println( x.element );
            inorderTraversal( x.right );
        }
    }

    public void postorderTraversal()
    {
        if( isEmpty() )
        {
            System.out.println( "Empty Tree!" );
        }
        else
        {
            postorderTraversal( root );
        }
    }

    private void postorderTraversal( BinaryNode<AnyType> x )
    {
        if( x != null )
        {
            postorderTraversal( x.left );
            postorderTraversal( x.right );
            System.out.println( x.element );
        }
    }
}
