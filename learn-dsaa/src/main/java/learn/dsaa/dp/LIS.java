
package learn.dsaa.dp;

/**
 * <p>
 * Dynamic Programming: Longest Increasing Subsequence Strict: items must be incremental Non-Strict: items can be
 * non-decreasing items of sequence can be discrete
 * </p>
 * <p>
 * TODO, output the sequence for each item
 * </p>
 *
 * @author Kuo Zhang
 */
public class LIS
{

    public static int[] lis( int[] src )
    {
        if( src.length == 0 )
        {
            return null;
        }

        int len = src.length;

        int[] retval = new int[len];

        retval[0] = 1;

        for( int i = 1; i < len; i++ )
        {
            int lenOfLIS = 1;

            for( int j = i - 1; j >= 0; j-- )
            {
                // if( src[j] < src[i] && lenOfLIS < retval[j] + 1 ) strict
                if( src[j] <= src[i] && lenOfLIS < retval[j] + 1 ) // non-strict
                {
                    lenOfLIS = retval[j] + 1;
                }
            }

            retval[i] = lenOfLIS;
        }

        return retval;
    }

    public static void main( String[] args )
    {
        int[] valuesForTest =
            { 11, 12, 13, 14, 15, 6, 8, 7, 9, 5, 1, 3, 20, 2, 4, 0, 16, 17, 19, 18, 10, 0, 5, 15, 10, 20 };

        int[] lis = lis( valuesForTest );

        // // System.out.println("The length of Longest Increasing Subsequence is: " + lis.length );

        printArray( lis );
    }

    private static void printArray( int[] lis2 )
    {
        for( int val : lis2 )
        {
            System.out.print( val + " " );
        }
    }
}
