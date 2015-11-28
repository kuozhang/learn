
package learn.dsaa.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kuo Zhang
 */
public class SelectSort
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
        selectSort( actual );
        assertEquals( true, SortUtil.arrayEquals( actual, excepted ) );
    }

    /*** Select Sort ***/

    public static void selectSort( int[] value )
    {
        for( int i = 0; i < value.length; i++ )
        {
            int minCount = i;
            int minValue = value[minCount];

            for( int j = i + 1; j < value.length; j++ )
            {
                if( value[j] < minValue )
                {
                    minCount = j;
                    minValue = value[minCount];
                }
            }

            // exchange the value of des[i] and des[minCount]
            if( i != minCount )
            {
                value[minCount] = value[i];
                value[i] = minValue;
            }
        }
    }
}
