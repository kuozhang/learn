
package learn.dsaa.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * TODO not tested
 *
 * @author Kuo Zhang
 */
public class MyLinkedList<T> implements List<T>
{

    private static class Node<T>
    {

        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node( T d, Node<T> p, Node<T> n )
        {
            data = d;
            prev = p;
            next = n;
        }
    }
    private int size;

    private int modCount = 0;
    // header 和 tailer 并不存储实际值， 不可以把他们的位置想象为 -1 和 size()
    private Node<T> header;

    private Node<T> tailer;

    public MyLinkedList()
    {
        init();
    }

    private void add( int index, T e )
    {
        addBefore( getNode( index, 0, size() ), e ); // 注意： 为节点是size() 位置的节点，并非size()-1 位置上的点，代表 tailer
    }

    @Override
    public boolean add( T e )
    {
        add( size(), e );
        return true;
    }

    private void addBefore( Node<T> node, T e )
    {
        Node<T> newNode = new Node<T>( e, node.prev, node );
        node.prev.next = newNode;
        node.prev = newNode;

        size++;
        modCount++;
    }

    @Override
    public void clear()
    {
        init();
    }

    @Override
    public boolean contains( T e )
    {
        if( e == null )
        {
            return false;
        }

        return getNode( e ) != null;
    }

    @Override
    public T get( int index )
    {
        return getNode( index, 0, size() - 1 ).data;
    }

    private Node<T> getNode( int index )
    {
        return getNode( index, 0, size() - 1 );
    }

    private Node<T> getNode( int index, int lower, int upper )
    {
        if( index < lower || index > upper )
        {
            // this should be improved
            throw new IndexOutOfBoundsException( "Get node index: " + index + "; size: " + size() );
        }

        Node<T> p;
        if( index < size() / 2 )
        {
            p = header.next; // 循环从0开始，0的位置有值，因此 p = header.next
            for( int i = 0; i < index; i++ )
            {
                p = p.next;
            }
        }
        else
        {
            p = tailer; // 循环从size()开始，size()的位置没有值，因此 p = tailer, 并不是 p = tailer.prev;
            for( int i = size(); i > index; i-- )
            {
                p = p.prev;
            }
        }

        return p;
    }

    private Node<T> getNode( T e )
    {
        if( e == null )
        {
            return null;
        }

        Node<T> node = header.next;

        while( node != null )
        {
            if( node.data == e )
            {
                return node;
            }

            node = node.next;
        }

        return null;
    }

    @Override
    public int indexOf( T e )
    {
        Node<T> node = header.next;
        int index = 0;

        while( node != null )
        {
            if( node.data == e )
            {
                return index;
            }

            node = node.next;
            index++;
        }

        return -1;
    }

    private void init()
    {
        header = new Node<T>( null, null, null );
        tailer = new Node<T>( null, header, null );
        header.next = tailer;

        size = 0;
        modCount++;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new LinkedListIterator();
    }

    @Override
    public T remove( int index )
    {
        return remove( getNode( index ) );
    }

    private T remove( Node<T> node )
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        size--;
        modCount++;
        return node.data;
    }

    @Override
    public boolean remove( T e )
    {
        if( e == null )
        {
            return false;
        }

        Node<T> node = getNode( e );

        if( node == null )
        {
            return false;
        }

        remove( node );

        return true;
    }

    /**
     * @return oldValue
     */
    @Override
    public T set( int index, T newVal )
    {
        Node<T> node = getNode( index );
        T oldValue = node.data;
        node.data = newVal;
        return oldValue;
    }

    @Override
    public int size()
    {
        return size;
    }

    private class LinkedListIterator implements Iterator<T>
    {

        Node<T> current = MyLinkedList.this.header.next;
        private int expectedModeCound = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext()
        {
            return current != MyLinkedList.this.tailer;
        }

        @Override
        public T next()
        {
            // 防止在Iterator之外，也就是在LinkedList中对数据结构有操作
            if( expectedModeCound != MyLinkedList.this.modCount )
            {
                throw new ConcurrentModificationException();
            }

            if( !hasNext() )
            {
                throw new NoSuchElementException();
            }

            T value = current.data;
            current = current.next;
            okToRemove = true;

            return value;
        }

        // 不能连续删除两次
        @Override
        public void remove()
        {
            // 防止在Iterator之外，也就是在LinkedList中对数据结构有操作
            if( expectedModeCound != MyLinkedList.this.modCount )
            {
                throw new ConcurrentModificationException();
            }

            // 防止连续调用多次remove方法
            if( !okToRemove )
            {
                throw new IllegalStateException();
            }


            MyLinkedList.this.remove( current.prev );
            this.expectedModeCound++;
            okToRemove = false;
        }

    }

}
