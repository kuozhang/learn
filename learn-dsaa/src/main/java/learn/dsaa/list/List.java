
package learn.dsaa.list;

/**
 * <p>
 * a simple interface of List
 *
 * @author kuo
 */
public interface List<T> extends Iterable<T>
{

    int size();

    boolean isEmpty();

    boolean contains( T e );

    T get( int index );

    int indexOf( T e );

    boolean add( T e );

    T set( int index, T newVal );

    /**
     * @return oldValue
     */
    T remove( int index );

    boolean remove( T e );

    void clear();

}
