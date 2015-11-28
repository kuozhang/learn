
package learn.dsaa.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kuo Zhang
 */
public class QuickSort
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
        quickSort( actual );
        assertEquals( true, SortUtil.arrayEquals( actual, excepted ) );
    }

    // /*** Quick Sort ***/
    //
    // public static void quickSort( int[] value )
    // {
    // quickSort( value, 0, value.length - 1 );
    // }
    //
    // private static void quickSort( int[] value, int startPos, int endPos )
    // {
    // // Must use ">=", startPos can larger than endPos, when the set is sorted, "divide position" is at the last item,
    // // then dividePos + 1 > endPos, see the last line of this method.
    // if( startPos >= endPos )
    // {
    // return;
    // }
    //
    // // this need to be improved
    // int pivot = value[startPos];
    //
    // int[] temp = new int[endPos-startPos+1];
    // int lowPos = 0;
    // int highPos = temp.length-1;
    //
    // for( int i = startPos + 1; i <= endPos; i++ )
    // {
    // if( value[i] <= pivot )
    // temp[lowPos++] = value[i];
    // else
    // temp[highPos--] = value[i];
    // }
    //
    // if( lowPos == highPos )
    // temp[lowPos] = pivot;
    //
    // for( int i = startPos; i <= endPos; i++ )
    // value[i] = temp[i-startPos];
    //
    // int dividePos = startPos + lowPos;
    //
    // // Important: middle - 1 and middle +1 , middle can't be used, will fall in dead loop.
    // quickSort( value, startPos, dividePos - 1 );
    // quickSort( value, dividePos + 1, endPos );
    // }

    /*
     * Quick Sort improved than the above one: 1. use only one array, sort locally 2. use median3 to decide the povit,
     * better perfomence
     */
    public static void quickSort( int[] a )
    {
        quickSort( a, 0, a.length - 1 );
    }

    /**
     * Median of Three Partitioning Sort a[left], a[right] and a[center], and return pivot value
     */
    private static int median3( int[] a, int left, int right )
    {
        int center = ( left + right ) / 2;

        // Here is very easy to make mistakes, when from left to right, there are only two elements
        if( a[center] < a[left] )
        {
            swap( a, center, left );
        }

        if( a[right] < a[left] )
        {
            swap( a, right, left );
        }

        if( a[right] < a[center] )
        {
            swap( a, right, center );
        }

        // a[center] is the pivot value, put it at the last of the array
        // here should be able to be improved, because if the last if statement is executed
        // here do it back
        swap( a, center, right );

        return a[right];

    }

    private static void swap( int[] a, int pos1, int pos2 )
    {
        int tmp = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = tmp;
    }

    // the value CUTOFF is not unique, usually got by experience
    private static int CUTOFF = 3;

    private static void quickSort( int[] a, int left, int right )
    {
        if( left == right )
        {
            return;
        }

        if( left + 1 == right )
        {
            if( a[left] > a[right] )
            {
                swap( a, left, right );
            }

            return;
        }

        // make sure the left is less than right, that means the size of the array is larger than 1
        // if use the CUTOFF, let the insertion sort to sort the small size of array, than this check is not needed
        if( left + CUTOFF <= right )
        {
            // actually the pivotValue is a[right]
            int pivotValue = median3( a, left, right );

            int i = left, j = right - 1;

            while( i < j )
            {
                // after this while loop a[i] >= pivotValue
                while( a[i] < pivotValue )
                {
                    i++;
                }

                // after this while loop a[j] <= pivotValue
                while( a[j] > pivotValue )
                {
                    j--;
                }

                if( i < j )
                {
                    swap( a, i, j );
                }
            }

            // now a[i] >= a[right], swap them
            swap( a, i, right );

            quickSort( a, left, i - 1 );
            quickSort( a, i + 1, right );
        }
        else
        {
            InsertSort.insertSort( a, left, right );
        }
    }
}
