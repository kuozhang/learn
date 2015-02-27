package learn.dsaa.dp;

/**
 * @author Kuo Zhang
 *
 * Dynamic Programming: max distance of two nodes in a tree, use binary tree for instance
 */
public class MaxDistanceOfTree
{
    
    public static void main( String[] args )
    {
       BinaryTree tree = new BinaryTree( 10 ); 
       tree.addNode( 9 );
       tree.addNode( 11 );
       tree.addNode( 8 );
       tree.addNode( 12 );
       
       Node root = tree.getRoot();

       maxDistance( root );

       System.out.println( root.maxDistance );
       
    }
    
    public static void maxDistance( Node node )
    {
        // the logic can be improved
        
        if( node == null )
        {
            return;
        }

        if( node.left == null && node.right == null )
        {
            node.maxLeftDistance = 0;
            node.maxRightDistance = 0;
            node.maxDistance = 0;
            return;
        }
        else if( node.left == null )
        {
            maxDistance( node.right );
            node.maxLeftDistance = 0;

            int temp =  node.right.maxLeftDistance >= node.right.maxRightDistance ? node.right.maxLeftDistance : node.right.maxRightDistance; 
            node.maxRightDistance = temp + 1;

            node.maxDistance = Math.max( node.right.maxDistance, node.maxRightDistance );
        }
        else if( node.right == null )
        {
            maxDistance( node.left );
            node.maxRightDistance = 0;

            int temp =  node.left.maxLeftDistance >= node.left.maxRightDistance ? node.left.maxLeftDistance : node.left.maxRightDistance; 
            node.maxLeftDistance = temp + 1;

            node.maxDistance = Math.max( node.left.maxDistance, node.maxLeftDistance );
        }
        else
        {
            maxDistance( node.left );
            maxDistance( node.right );

            int temp1 = node.left.maxLeftDistance >= node.left.maxRightDistance ? node.left.maxLeftDistance : node.left.maxRightDistance;
            node.maxLeftDistance = temp1 + 1;

            int temp2 = node.right.maxLeftDistance >= node.right.maxRightDistance ? node.right.maxLeftDistance : node.right.maxRightDistance;
            node.maxRightDistance = temp2 + 1;

            node.maxDistance = maxOfThree( node.left.maxDistance, node.right.maxDistance, node.maxLeftDistance + node.maxRightDistance );
            
        }
        
    }
    
    public static int maxOfThree( int a , int b ,int c )
    {
        
        int max = a;

        if( b > max )
        {
            max = b;
        }
        
        if( c > max )
        {
            max = c ;
        }
        
        return max;
    }
}

class Node
{
    public Node left;
    public Node right;
    public int value;
    public int maxDistance; // keep the max distance for every node,
                            // indeed we can use one global variable for compute the maxDistance of root node
    public int maxLeftDistance;
    public int maxRightDistance;

    public Node( int value )
    {
        left = null;
        right = null;
        this.value = value;
        maxDistance = 0;
        maxLeftDistance = 0;
        maxRightDistance = 0;
    }
}

class BinaryTree
{
    private Node root;
    
    public BinaryTree( int value )
    {
        this.root = new Node( value );
    }
    
    public void addNode( int value )
    {
        Node newNode = new Node( value );
        
        doAddNode( root, newNode );
    }
    
    private void doAddNode( Node node, Node newNode )
    {
       if( node.value == newNode.value )  
       {
           return; // do nothing
       }
       
       if( node.value > newNode.value )
       {
           if( node.left == null )
           {
               node.left = newNode;
               return;
           }
           else
           {
               doAddNode( node.left, newNode );
           }
       }
       
       if( node.value < newNode.value )
       {
           if( node.right == null )
           {
               node.right = newNode;
               return;
           }
           else
           {
               doAddNode( node.right, newNode );
           }
       }
    }
    
    public Node getRoot()
    {
        return root;
    }
}
