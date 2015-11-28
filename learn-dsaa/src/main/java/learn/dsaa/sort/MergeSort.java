
package learn.dsaa.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kuo Zhang
 */
public class MergeSort
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
        mergeSort( actual );
        assertEquals( true, SortUtil.arrayEquals( actual, excepted ) );
    }

    // *** Merge Sort ***

    public static void mergeSort( int[] value )
    {
        int len = value.length;

        if( len == 1 )
            return;
        else if( len > 1 )
        {
            int mid = ( len / 2 );

            int[] temp1 = new int[mid - 0];
            System.arraycopy( value, 0, temp1, 0, temp1.length );
            mergeSort( temp1 );

            int[] temp2 = new int[len - mid];
            System.arraycopy( value, mid, temp2, 0, temp2.length );
            mergeSort( temp2 );

            merge( temp1, temp2, value );
        }
    }

    private static void merge( int[] temp1, int[] temp2, int[] value )
    {
        int i = 0, j = 0, k = 0;

        while( i < temp1.length && j < temp2.length )
        {
            if( temp1[i] <= temp2[j] )
                value[k++] = temp1[i++];
            else
                value[k++] = temp2[j++];
        }

        while( i < temp1.length )
            value[k++] = temp1[i++];

        while( j < temp2.length )
            value[k++] = temp2[j++];
    }
}
