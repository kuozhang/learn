
package learn.dsaa.misc;

/**
 * @author Kuo Zhang 求最大子序列和
 */
public class MaxSubSummary
{

    // *** 1 ***//
    /**
     * Cubic maximum contiguous subsequence sum algorithm Time Complexity: O(N^3)
     */
    public static int maxSubSum1( int[] a )
    {
        int maxSum = 0;

        for( int i = 0; i < a.length; i++ )
        {
            for( int j = i; j < a.length; j++ )
            {
                int thisSum = 0;

                for( int k = i; k <= j; k++ )
                {
                    thisSum += a[k];
                }

                if( thisSum > maxSum )
                {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    // *** 2 ***//
    /**
     * Time Complexity: O(N^2)
     */
    public static int maxSubSum2( int[] a )
    {
        int maxSum = 0;

        for( int i = 0; i < a.length; i++ )
        {
            int thisSum = 0;
            for( int j = i; j < a.length; j++ )
            {
                thisSum += a[j];

                if( thisSum > maxSum )
                {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    // *** 3 ***//
    /**
     * Time Complexity O( NLogN )
     */
    public static int maxSubSum3( int[] a )
    {
        return maxSumRec( a, 0, a.length - 1 );
    }

    public static int maxSumRec( int[] a, int left, int right )
    {
        if( left == right )
        {
            return a[left] > 0 ? a[left] : 0;
        }

        int center = ( left + right ) / 2;
        int maxLeftSum = maxSumRec( a, left, center );
        int maxRightSum = maxSumRec( a, center + 1, right );

        int maxLeftBorderSum = 0;
        int thisSum = 0;
        for( int i = center; i >= left; i-- )
        {
            thisSum += a[i];
            if( thisSum > maxLeftBorderSum )
            {
                maxLeftBorderSum = thisSum;
            }
        }

        int maxRightBorderSum = 0;
        thisSum = 0;
        for( int i = center; i <= right; i++ )
        {
            thisSum += a[i];
            if( thisSum > maxRightBorderSum )
            {
                maxRightBorderSum = thisSum;
            }
        }

        return max3( maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum );
    }

    private static int max3( int a, int b, int c )
    {
        int max = ( a >= b ? a : b );
        max = ( max >= c ? max : c );
        return max;
    }

    // *** 4 ***//
    /**
     * Time Complexity: O(N)
     */

    public static int maxSubSum4( int[] a )
    {
        int maxSum = 0;
        int thisSum = 0;
        for( int i = 0; i < a.length; i++ )
        {
            thisSum += a[i];

            if( thisSum > maxSum )
            {
                maxSum = thisSum;
            }

            if( thisSum < 0 )
            {
                thisSum = 0;
            }
        }

        return maxSum;
    }
}
