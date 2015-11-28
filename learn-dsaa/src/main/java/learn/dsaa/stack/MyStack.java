
package learn.dsaa.stack;

/**
 * @author Kuo Zhang
 */
public interface MyStack<T>
{

    public void push( T t );

    public T pop();

    public T top();

    public boolean isEmpty();
}
