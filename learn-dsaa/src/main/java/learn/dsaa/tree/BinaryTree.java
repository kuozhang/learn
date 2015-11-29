
package learn.dsaa.tree;

/**
 * @author kuo
 * @param <AnyType>
 */
public interface BinaryTree<AnyType extends Comparable<? super AnyType>>
{

    public boolean contains( AnyType x );

    public void insert( AnyType x );

    public void remove( AnyType x );

    public void preorderTraversal();

    public void inorderTraversal();

    public void postorderTraversal();

    public boolean isEmpty();

    public void makeEmpty();

}
