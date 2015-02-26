package learn.dsaa.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeNode
{
    private int data;
    private BinaryTreeNode leftChildNode;
    private BinaryTreeNode rightChildNode;

    public BinaryTreeNode( int data )
    {
        this( data, null, null );
    }

    public BinaryTreeNode( int data, BinaryTreeNode leftChildNode, BinaryTreeNode rightChildNode )
    {
        this.data = data;
        this.leftChildNode = leftChildNode;
        this.rightChildNode = rightChildNode;
    }

    public static void inOrderTraversal( BinaryTreeNode node )
    {
        if( node == null )
        {
            return;
        }

        inOrderTraversal( node.leftChildNode );
        System.out.println( node.data);
        inOrderTraversal( node.rightChildNode );
    }

    public static void preOrderTraversal( BinaryTreeNode node )
    {
        if( node == null)
        {
            return;
        }

        System.out.println( node.data );
        preOrderTraversal( node.leftChildNode );
        preOrderTraversal( node.rightChildNode );
    }

    public static void postOrderTraversal( BinaryTreeNode node )
    {
        if( node == null )
        {
            return;
        }

        postOrderTraversal( node.leftChildNode );
        postOrderTraversal( node.rightChildNode );
        System.out.println( node.data );
    }

    public static void insertNode( BinaryTreeNode insertedNode, BinaryTreeNode rootNode )
    {
        if( insertedNode.data < rootNode.data )
        {
            if( rootNode.leftChildNode != null )
            {
                insertNode( insertedNode, rootNode.leftChildNode ); 
            }
            else
            {
                rootNode.leftChildNode = insertedNode;
            }
        }
        else
        {
            if( rootNode.rightChildNode != null )
            {
                insertNode( insertedNode, rootNode.rightChildNode );
            }
            else
            {
                rootNode.rightChildNode = insertedNode;
            }
        }
    }

    public static void main( String[] args )
    {
        List<BinaryTreeNode> nodes = new ArrayList<BinaryTreeNode>();
        BinaryTreeNode rootNode = new BinaryTreeNode( 5 );
        nodes.add( new BinaryTreeNode( 5 ) );
        nodes.add( new BinaryTreeNode( 1 ) );
        nodes.add( new BinaryTreeNode( 6 ) );
        nodes.add( new BinaryTreeNode( 8 ) );
        nodes.add( new BinaryTreeNode( 9 ) );
        nodes.add( new BinaryTreeNode( 4 ) );
        nodes.add( new BinaryTreeNode( 3 ) );
        nodes.add( new BinaryTreeNode( 0 ) );
        nodes.add( new BinaryTreeNode( 2 ) );
        nodes.add( new BinaryTreeNode( 7 ) );

        for( BinaryTreeNode node : nodes )
        {
            insertNode( node, rootNode );
        }

        inOrderTraversal( rootNode );
    }
}