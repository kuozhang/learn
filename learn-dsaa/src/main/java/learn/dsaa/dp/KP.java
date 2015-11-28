
package learn.dsaa.dp;

/**
 * <p>
 * Dynamic Programming: Knapsack Problem
 * </p>
 *
 * @author Kuo Zhang
 */
public class KP
{

    public static int kp( int[] volumns, int[] values, int availableVolumn )
    {
        if( volumns.length != values.length )
        {
            System.out.println( "Length inconsistent !" ); // Should throw an exception here
            return -1;
        }

        return doKp( volumns, values, availableVolumn, volumns.length );
    }

    // sum of selected weight can be larger than totalWeights
    // try to make the sum of values largest
    public static int doKp( int[] volumns, int[] values, int availableVolumn, int count )
    {
        if( availableVolumn <= 0 )
        {
            return 0;
        }

        if( count <= 0 )
        {
            return 0;
        }

        if( count == 1 )
        {
            if( volumns[0] <= availableVolumn )
            {
                return values[0];
            }
            else
            {
                return 0;
            }
        }

        int valueSum1 = doKp( volumns, values, availableVolumn, count - 1 );
        int valueSum2 = doKp( volumns, values, availableVolumn - volumns[count - 1], count - 1 ) + values[count - 1];

        return valueSum1 > valueSum2 ? valueSum1 : valueSum2;
    }

    public static void main( String[] args )
    {
        // the length of volumns must equal to length of values
        int[] volumns = { 4, 3, 4, 2 };
        int[] values = { 20, 6, 20, 4 };
        int availableVolumn = 9;
        int maxSumOfValue = kp( volumns, values, availableVolumn );

        System.out.println( maxSumOfValue );
    }
}
