package learn.dsaa.queue;

import learn.dsaa.exception.EmptyQueueException;
import learn.dsaa.exception.InvalidInputException;
import learn.dsaa.list.MyLinkedList;

public class QueueByLinkedList<T extends Comparable<? super T>> implements Queue<T> 
{

    private MyLinkedList<T> data = new MyLinkedList<T>();

    @Override
    public void enqueue( T t )
    {
        if(t == null){
            throw new InvalidInputException( "Input NULL" );
        }
        
        data.addLast(t);
    }

    @Override
    public T dequeue()
    {
        if(isEmpty()){
            throw new EmptyQueueException();
        }

        return data.removeFirst();
    }

    @Override
    public T element()
    {
        if(isEmpty()){
            throw new EmptyQueueException();
        }

        return data.getFirst();
    }

    @Override
    public boolean isEmpty()
    {
        return data.isEmpty();
    }

}
