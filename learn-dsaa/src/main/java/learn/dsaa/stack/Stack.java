
package learn.dsaa.stack;

/**
 * <p> LIFO: last in, first out
 *
 * @author Kuo Zhang
 */
public interface Stack<T>
{

    public void push( T t );

    public T pop();

    // return the element, but does not remove
    public T element();

    public boolean isEmpty();
}
