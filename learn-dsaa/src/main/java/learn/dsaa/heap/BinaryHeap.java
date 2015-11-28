
package learn.dsaa.heap;

import learn.dsaa.book.src.UnderflowException;

/**
 * <p>
 * The index starts from 1, to satisfy the feature of heap (priority queue) ,so be very careful with the
 * ArrayIndexOutofBoundsException
 * </p>
 *
 * @author Kuo Zhang
 */
public class BinaryHeap
{

    private int currentSize;
    private int[] array;
    private static int DEFAULT_CAPACITY = 10;
    private static int ROOT_POSITION = 1;

    public BinaryHeap()
    {
        this( DEFAULT_CAPACITY );
    }

    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = new int[capacity + 1]; // + 1 because the index starts from 1 for the heap to satisfy the features
    }

    public BinaryHeap( int[] items )
    {
        currentSize = items.length;
        array = new int[currentSize + 1];
        System.arraycopy( items, 0, array, 1, currentSize );
        buildHeap();
    }

    public void insert( int x )
    {
        if( currentSize == array.length - 1 )
        {
            enlargeArray( currentSize * 2 );
        }

        // make sure the size of array is enough to use
        int hole = ++currentSize;
        for( ; hole > 1 && x < array[hole / 2]; hole /= 2 )
        {
            array[hole] = array[hole / 2];
        }

        array[hole] = x;

    }

    public int findMin()
    {
        if( isEmpty() )
        {
            throw new UnderflowException();
        }

        return array[ROOT_POSITION];
    }

    public int deleteMin()
    {
        if( isEmpty() )
        {
            throw new UnderflowException();
        }

        int minItem = findMin();

        array[1] = array[currentSize--];

        percolateDown( 1 );

        return minItem;
    }

    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    public void makeEmpty()
    {
        currentSize = 0;
    }

    private void percolateDown( int hole )
    {
        int tmp = array[hole];

        int child;
        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;

            // find the the minimal value in children,
            // use if( child != currentSize ) to check if it has the right child
            if( child != currentSize && array[child + 1] < array[child] )
            {
                child++;
            }

            if( array[child] < tmp )
            {
                array[hole] = array[child];
            }
            else
            {
                break;
            }
        }

        array[hole] = tmp;
    }

    private void buildHeap()
    {
        for( int i = currentSize / 2; i > 0; i-- )
        {
            percolateDown( i );
        }
    }

    private void enlargeArray( int newSize )
    {
        int[] old = array;
        int[] array = new int[newSize + 1];
        for( int i = 1; i < old.length; i++ )
        {
            array[i] = old[i];
        }
    }

    public void printHeap()
    {
        for( int i = 1; i <= currentSize; i++ )
        {
            System.out.print( array[i] + " " );
        }
    }

    public int size()
    {
        return currentSize;
    }

    private static int[] valuesForTest = { 11, 12, 13, 14, 15, 6, 8, 7, 9, 5, 1, 3, 20, 2, 4, 0, 16, 17, 18, 19, 10 };

    public static void main( String[] args )
    {
        BinaryHeap testHeap = new BinaryHeap( valuesForTest );
        testHeap.printHeap();
    }
}
