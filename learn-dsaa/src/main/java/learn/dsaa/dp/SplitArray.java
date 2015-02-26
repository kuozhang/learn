package learn.dsaa.dp;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Kuo Zhang
 *
 * An array with 2n items, split the array to two, each of them has n items
 * try to make the sums of the two arrays equal or closest
 */
public class SplitArray
{
   public static void split( int[] array )
   {
       int length = array.length;

       if( length == 0 )
       {
           System.out.println( "No itemes in array." );
           return;
       }

       if( length % 2 != 0 )
       {
           System.out.println( "The length of input array should be a even number." );
           return;
       }

       int halfLength = length / 2;

       int sum = 0;

       for( int i : array )
       {
           sum += i;
       }

       int halfSum = sum / 2;

       ValueWithLocations result = doSplit( array, 0, halfLength, halfSum );

       System.out.println( "Sum: " + sum );
       System.out.println( "Half of sum: " + halfSum );
       System.out.println( "Actual half of sum: " + result.value );

       for( int loc : result.locations )
       {
           System.out.println( loc + ": " + array[loc] );
       }
   }

    public static ValueWithLocations doSplit( int[] array, int start, int amount, int sum )
    {
        ValueWithLocations retval = new ValueWithLocations();

        if( amount == 1 )
        {
            retval.value = array[start];
            retval.locations.add( start );

            return retval;
        }

        if( amount == array.length - start )
        {
            int temp = 0;

            for( int i = start; i < array.length; i++ )
            {
                temp += array[i];
                retval.locations.add( i );
            }

            retval.value = temp;

            return retval;
        }

        ValueWithLocations result1 = doSplit( array, start + 1, amount, sum );
        ValueWithLocations result2 = doSplit( array, start + 1, amount - 1, sum - array[start] );
        result2.value = result2.value + array[start];
        result2.locations.add( start );

        int temp1 = ( sum - result1.value ) >= 0 ? ( sum - result1.value ) : ( result1.value - sum ); 
        int temp2 = ( sum - result2.value ) >= 0 ? ( sum - result2.value ) : ( result2.value - sum ); 

        if( temp1 <= temp2 )
        {
            return result1;
        }
        else
        {
            return result2;
        }
    }

    public static void main( String[] args )
    {
        int[] array = { 1, 5, 7, 8, 9, 4, 6, 11, 20, 17 };
        split( array );
    }

    // store the result value and the location of items
    private static class ValueWithLocations
    {
        public int value;
        public List<Integer> locations;

        public ValueWithLocations()
        {
            value = 0;
            locations = new ArrayList<Integer>();
        }

        public ValueWithLocations( int result, Integer... locations )
        {
            this.value = result;
            this.locations = new ArrayList<Integer>();

            for( Integer i : locations )
            {
                this.locations.add( i );
            }
        }

    }

}
