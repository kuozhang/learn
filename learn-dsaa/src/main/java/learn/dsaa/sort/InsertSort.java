
package learn.dsaa.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kuo Zhang
 */
public class InsertSort
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
        int length = actual.length;
        int[] temp1 = new int[length];

        System.arraycopy( actual, 0, temp1, 0, length );
        insertSort( temp1 );
        assertEquals( true, SortUtil.arrayEquals( temp1, excepted ) );

        int[] temp2 = new int[length];
        System.arraycopy( actual, 0, temp2, 0, length );
        insertSort( temp2, 0, length - 1 );
        assertEquals( true, SortUtil.arrayEquals( temp2, excepted ) );
    }

    // *** Insert Sort ***

    public static void insertSort( int[] value )
    {
        for( int i = 1; i < value.length; i++ )
        {
            int pivot = value[i];

            for( int j = i - 1; j >= 0; j-- )
            {
                if( value[j] > pivot )
                {
                    value[j + 1] = value[j];
                }
                else if( value[j] <= pivot )
                {
                    value[j + 1] = pivot;
                    break;
                }

                if( j == 0 )
                    value[j] = pivot;
            }
        }
    }

    // *** Insert Sort ***

    public static void insertSort( int[] value, int start, int end )
    {
        for( int i = start + 1; i <= end; i++ )
        {
            int pivot = value[i];

            for( int j = i - 1; j >= start; j-- )
            {
                if( value[j] > pivot )
                {
                    value[j + 1] = value[j];
                }
                else if( value[j] <= pivot )
                {
                    value[j + 1] = pivot;
                    break;
                }

                if( j == start )
                    value[j] = pivot;
            }
        }
    }
}
