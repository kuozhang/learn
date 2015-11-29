package learn.dsaa.queue;

import learn.dsaa.exception.EmptyQueueException;
import learn.dsaa.exception.InvalidInputException;
import learn.dsaa.list.List;
import learn.dsaa.list.MyArrayList;

public class QueueByArrayList<T extends Comparable<? super T>> implements Queue<T> 
{

    private List<T> data = new MyArrayList<T>();

    @Override
    public void enqueue( T t )
    {
        if(t == null){
            throw new InvalidInputException( "Input NULL" );
        }
        
        data.add(t);
    }

    @Override
    public T dequeue()
    {
        if(isEmpty()){
            throw new EmptyQueueException();
        }

        return data.remove( 0 );
    }

    @Override
    public T element()
    {
        if(isEmpty()){
            throw new EmptyQueueException();
        }

        return data.get( 0 );
    }

    @Override
    public boolean isEmpty()
    {
        return data.isEmpty();
    }

}
