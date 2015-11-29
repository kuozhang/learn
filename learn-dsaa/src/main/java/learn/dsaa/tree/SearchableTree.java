
package learn.dsaa.tree;

/**
 * @author kuo
 *
 * @param <AnyType>
 */
public interface SearchableTree<AnyType extends Comparable<? super AnyType>>
{

    public AnyType findMax();

    public AnyType findMin();

}
