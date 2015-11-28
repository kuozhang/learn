
package learn.dsaa.sort;

import static org.junit.Assert.assertEquals;
import learn.dsaa.heap.BinaryHeap;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kuo Zhang
 */
public class HeapSort
{

    private int[] actual;
    private int[] excepted;

    @Before
    public void init()
    {
        actual =
            new int[] { 11, 12, 13, 14, 15, 6, 8, 7, 9, 5, 1, 3, 20, 2, 4, 0, 16, 17, 18, 19, 10, 0, 5, 15, 10, 20 };

        excepted =
            new int[] { 0, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 15, 16, 17, 18, 19, 20, 20 };
    }

    @Test
    public void test()
    {
        heapSort( actual );
        assertEquals( true, SortUtil.arrayEquals( actual, excepted ) );
    }

    public static void heapSort( int[] values )
    {
        BinaryHeap heap = new BinaryHeap( values );

        if( heap.size() > 0 )
        {
            for( int i = 0; i < values.length && !heap.isEmpty(); i++ )
            {
                values[i] = heap.deleteMin();
            }
        }
    }
}
