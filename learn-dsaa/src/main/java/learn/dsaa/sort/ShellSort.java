
package learn.dsaa.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ShellSort
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
        shellSort( actual );
        assertEquals( true, SortUtil.arrayEquals( actual, excepted ) );
    }

    // *** Shell Sort *** //

    public static void shellSort( int[] value )
    {
        int length = value.length;

        int d = length / 2;
        while( d >= 1 )
        {
            for( int i = 0; i < d; i++ )
            {
                for( int j = i; j < length; j += d )
                {
                    int povit = value[j];

                    for( int k = j - d; k >= i; k -= d )
                    {
                        if( value[k] > povit )
                        {
                            value[k + d] = value[k];
                        }
                        else
                        {
                            value[k + d] = povit;
                            break;
                        }

                        if( k == i )
                        {
                            value[k] = povit;
                        }
                    }
                }
            }

            d = d / 2;
        }
    }
}
