
package learn.dsaa.stack;

import java.util.ArrayList;
import java.util.List;

import learn.dsaa.exception.EmptyStackException;
import learn.dsaa.exception.InvalidInputException;

/**
 * <p>
 * Implementation of Array, this is a really simple implementation, wraps the ArrayList, the real implementation should
 * be like the inner of ArrayList, use a variable to control the capacity
 * </p>
 *
 * @author Kuo Zhang
 */
public class StackByArray<T> implements MyStack<T>
{
    // private int capacity; control the capacity

    private List<T> data = new ArrayList<>();

    @Override
    public void push( T t )
    {
        if( t == null )
        {
            throw new InvalidInputException( "Input NULL" );
        }

        data.add( t );
    }

    @Override
    public T pop()
    {
        if( isEmpty() )
        {
            throw new EmptyStackException( "Stack empty now, no elements !" );
        }

        T retval = data.get( data.size() - 1 );

        data.remove( data.size() - 1 );

        return retval;
    }

    @Override
    public T top()
    {
        if( isEmpty() )
        {
            throw new EmptyStackException( "Stack empty now, no elements !" );
        }

        return data.get( data.size() - 1 );
    }

    @Override
    public boolean isEmpty()
    {
        return data.isEmpty();
    }

}
