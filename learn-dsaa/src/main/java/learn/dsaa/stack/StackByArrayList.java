
package learn.dsaa.stack;

import learn.dsaa.exception.EmptyStackException;
import learn.dsaa.exception.InvalidInputException;
import learn.dsaa.list.List;
import learn.dsaa.list.MyArrayList;

/**
 * @author Kuo Zhang
 */
public class StackByArrayList<T> implements Stack<T>
{
    // private int capacity; control the capacity

    private List<T> data = new MyArrayList<T>();

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
    public T element()
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
