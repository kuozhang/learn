
package learn.dsaa.tree;

/**
 * <p>
 * AVL Tree is mostly used for searching, the time complexity is O(logN)
 * </p>
 * 
 * @author Kuo Zhang
 */
public class AVLTree<AnyType extends Comparable<? super AnyType>>
{

    private static class AVLNode<AnyType>
    {

        AnyType element;
        AVLNode<AnyType> left;
        AVLNode<AnyType> right;
        int height;

        public AVLNode( AnyType element )
        {
            this( element, null, null );
        }

        public AVLNode( AnyType element, AVLNode<AnyType> lt, AVLNode<AnyType> rt )
        {
            this.element = element;
            left = lt;
            right = rt;
            height = 0;
        }
    }

    private AVLNode<AnyType> root;
    private static final int ALLOWED_IMBLANCE = 1;

    private AVLNode<AnyType> balance( AVLNode<AnyType> t )
    {
        if( t == null )
        {
            return t;
        }

        if( height( t.left ) - height( t.right ) > ALLOWED_IMBLANCE )
        {
            if( height( t.left.left ) > height( t.left.right ) )
            {
                t = singleRotateWithLeftChild( t );
            }
            else
            {
                t = doubleRotateRightLeft( t );
            }
        }
        else if( height( t.right ) - height( t.left ) > ALLOWED_IMBLANCE )
        {
            if( height( t.right.right ) > height( t.right.left ) )
            {
                t = singleRotateWithRightChild( t );
            }
            else
            {
                t = doubleRotateLeftRight( t );
            }
        }

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;

        return t;
    }

    public boolean contains( AnyType x )
    {
        return contains( root, x );
    }

    private boolean contains( AVLNode<AnyType> t, AnyType x )
    {
        if( t == null )
        {
            return false;
        }

        if( x.compareTo( t.element ) < 0 )
        {
            return contains( t.left, x );
        }
        else if( x.compareTo( t.element ) > 0 )
        {
            return contains( t.right, x );
        }
        else
        {
            return true;
        }
    }

    // for test
    // public void checkBalance()
    // {
    // checkBalance( root );
    // }
    //
    // public void checkBalance( AVLNode<AnyType> t )
    // {
    //
    // }

    public AVLNode<AnyType> findMax( AVLNode<AnyType> t )
    {
        if( t == null )
        {
            return t;
        }

        while( t.right != null )
        {
            t = t.right;
        }

        return t;
    }

    public AVLNode<AnyType> findMin( AVLNode<AnyType> t )
    {
        if( t == null )
        {
            return t;
        }

        while( t.left != null )
        {
            t = t.left;
        }

        return t;
    }

    private int height( AVLNode<AnyType> x )
    {
        return x == null ? -1 : x.height;
    }

    public void insert( AnyType x )
    {
        if( !contains( x ) )
        {
            insert( root, x );
        }
    }

    private AVLNode<AnyType> insert( AVLNode<AnyType> t, AnyType x )
    {
        if( t == null )
        {
            return new AVLNode<AnyType>( x );
        }

        /*
         * if( x.compareTo( t.element ) < 0 ) { t.left = insert( t.left, x ); if( height( t.left ) - height( t.right )
         * == 2 ) { if( x.compareTo( t.left.element ) < 0 ) { singleRotateWithLeftChild( t ); } else {
         * doubleRotateRightLeft( t ); } } } else if( x.compareTo( t.element ) > 0 ) { t.right = insert( t.right, x );
         * if( height( t.right ) - height( t.left ) == 2 ) { if( x.compareTo( t.right.element ) > 0 ) {
         * singleRotateWithRightChild( t ); } else { doubleRotateLeftRight( t ); } } }
         */

        if( x.compareTo( t.element ) < 0 )
        {
            t.left = insert( t.left, x );
        }
        else if( x.compareTo( t.element ) > 0 )
        {
            t.right = insert( t.right, x );
        }
        else if( x.compareTo( t.element ) == 0 )
        {
            // directly return t, don't need to balance t, since nothing changed
            return t;
        }

        return balance( t );
    }

    private boolean isEmpty()
    {
        return root == null;
    }

    public void makeEmpty()
    {
        root = null;
        // TODO, need to destroy other elements?
    }

    public void printTree()
    {
        if( isEmpty() )
        {
            System.out.println( "Empty tree" );
        }
        else
        {
            printTree( root );
        }
    }

    // inorder traversal, print elements in order
    private void printTree( AVLNode<AnyType> x )
    {
        if( x != null )
        {
            printTree( x.left );
            System.out.println( x.element );
            printTree( x.right );
        }
    }

    public void remove( AnyType x )
    {
        remove( x, root );
    }

    public AVLNode<AnyType> remove( AnyType x, AVLNode<AnyType> t )
    {
        if( t == null )
        {
            return t;
        }

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
        {
            t.left = remove( x, t.left );
        }
        else if( compareResult > 0 )
        {
            t.right = remove( x, t.right );
        }
        else
        {
            if( t.left != null && t.right != null )
            {
                /*
                 * use the min value of right child tree to replace the element, remove that node. or you can use the
                 * max value of left child, the same way. also you can reason that the node going to be removed has at
                 * most node, because if it has two nodes, there must be one less than it ( not right for removing min
                 * value of right child tree) and larger than it(not right for removing max value of left child tree)
                 */
                t.element = findMin( t.right ).element;
                t.right = remove( t.element, t.right );
            }
            else
            {
                t = ( t.left != null ) ? t.left : t.right;
            }
        }

        return balance( t );
    }

    /*
     * Insert left leaf node to the left tree
     */
    private AVLNode<AnyType> singleRotateWithLeftChild( AVLNode<AnyType> x )
    {
        AVLNode<AnyType> left = x.left;
        x.left = left.right;
        left.right = x;
        x.height = Math.max( height( x.left ), height( x.right ) ) + 1;
        left.height = Math.max( height( left.left ), height( left.right ) ) + 1;

        return left;
    }

    /*
     * Insert right leaf node to the right tree
     */
    private AVLNode<AnyType> singleRotateWithRightChild( AVLNode<AnyType> x )
    {
        AVLNode<AnyType> right = x.right;
        x.right = right.left;
        right.left = x;
        x.height = Math.max( height( x.left ), height( x.right ) ) + 1;
        right.height = Math.max( height( right.left ), height( right.right ) ) + 1;

        return right;
    }

    /*
     * Insert left leaf node to right tree SingleRotateWithLeftChild + SingRotateWithRightChild
     */
    private AVLNode<AnyType> doubleRotateLeftRight( AVLNode<AnyType> x )
    {
        x.right = singleRotateWithLeftChild( x.right );
        x = singleRotateWithRightChild( x );
        return x;
    }

    /*
     * Insert right leaf node to left tree SingleRotateWithRightChild + SingRotateWithLeftChild
     */
    private AVLNode<AnyType> doubleRotateRightLeft( AVLNode<AnyType> x )
    {
        x.left = singleRotateWithRightChild( x.left );
        x = singleRotateWithLeftChild( x );
        return x;
    }
}
