package learn.dsaa.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Kuo Zhang 
 *
 */
public class BubbleSort
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
	    bubbleSort( actual );
	    assertEquals( true, SortUtil.arrayEquals( actual, excepted ) );
	}

    // *** Bubble Sort ***

    public static void bubbleSort( int[] value )
    {
        for( int i = 0; i < value.length; i++ )
        {
            for( int j = value.length - 1; j > i; j-- )
            {
                if( value[j] < value[j - 1] )
                {
                    int temp = value[j];
                    value[j] = value[j - 1];
                    value[j - 1] = temp;
                }
            }
        }
    }
}
