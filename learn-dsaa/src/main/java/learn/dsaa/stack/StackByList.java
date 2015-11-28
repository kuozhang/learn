
package learn.dsaa.stack;

import java.util.LinkedList;

import learn.dsaa.exception.EmptyStackException;
import learn.dsaa.exception.InvalidInputException;

/**
 * @author Kuo Zhang
 */
public class StackByList<T> implements MyStack<T>
{

    private LinkedList<T> data = new LinkedList<T>();

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

        return data.removeLast();
    }

    @Override
    public T top()
    {
        if( isEmpty() )
        {
            throw new EmptyStackException( "Stack empty now, no elements !" );
        }

        return data.getLast();
    }

    @Override
    public boolean isEmpty()
    {
        return data.isEmpty();
    }
}
