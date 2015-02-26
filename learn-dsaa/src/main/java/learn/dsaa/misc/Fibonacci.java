package learn.dsaa.misc;

import static org.junit.Assert.assertEquals;
import learn.dsaa.exception.InvalidInputException;

import org.junit.Test;


/**
 * @author Kuo Zhang
 *
 * 斐波那契数列
 * 
 * // TODO handle the case where input is 100, use BigInteger?
 * 
 *  **fib_1 means the return value is the value of the Nst element
 *  **fib_2 means the return value is the set of all elements
 *  
 *  improved_fib* means using loop but not recursion, without "improved" prefix means using recursion
 *  
 */
public class Fibonacci
{

    /*
     * this recursion has a really bad performance, because fib( n-1 ), fib( n-2 ) ... f(n-n),
     * all of them are called multiple times. 
     *
     */
    public static int fib_1( int n )
    {
        if( n < 0 )
        {
            throw new InvalidInputException( "The input cannot be less than 0." );
        }

        if( n == 0 )
        {
            return 0;
        }
        else if( n == 1 )
        {
            return 1;
        }
        else
        {
            return fib_1( n - 1 ) + fib_1( n - 2 );
        }
    }

    public static int[] fib_2( int n )
    {
        if( n < 0 )
        {
            throw new InvalidInputException( "The input cannot be less than 0." );
        }

        int[] retval = new int[ n + 1 ];

        fib_2_internal( n, retval );

        return retval;
    }

    public static int fib_2_internal( int n, int[] values ) 
    {
        if( n == 0 )
        {
            values[n] = 0;
            return 0;
        }
        else if( n == 1 )
        {
            values[n] = 1;
            return 1;
        }
        else
        {
            values[n] = fib_2_internal( n - 1, values ) + fib_2_internal( n - 2, values );
            return values[n];
        }
    }

    /*
     * an improved fibonacci, use loop to replace the recursion
     * 
     */
    public static int improved_fib_1( int n )
    {
        if( n < 0 )
        {
            throw new InvalidInputException( "The input cannot be less than 0" );
        }

        if( n == 0 )
        {
            return 0;
        }
        else if( n == 1 )
        {
            return 1;
        }

        int last = 1;
        int nextToLast = 0;
        int result = 0;
        for( int i = 2; i <= n; i++ )
        {
            result = last + nextToLast;
            nextToLast = last;
            last = result;
        }

        return result;
    }

    // return the whole fibonacci set from 0 to n 
    public static int[] improved_fib_2( int n )
    {
        if( n < 0 )
        {
            throw new InvalidInputException( "The input cannot be less than 0" );
        }

        int[] retval = new int[ n + 1];

        if( n == 0 )
        {
            retval[0] = 0;
        }
        else if( n == 1 )
        {
            retval[0] = 0;
            retval[1] = 1;
        }
        else if( n > 1 )
        {
            retval[0] = 0;
            retval[1] = 1;

            int last = 1;
            int nextToLast = 0;
            int result = 0;

            for( int i = 2; i <= n; i++ )
            {
                result = last + nextToLast;
                nextToLast = last;
                last = result;
                retval[i] = result;
            }
        }

        return retval;
    }

    @Test
    public void test_fib_1()
    {
        int num = 10;

        int[] excepted = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 };

        int[] actual = new int[11];

        for( int i = 0; i <= num; i++ )
        {
            actual[i] = fib_1( i );
        }

        assertEquals( excepted.length, actual.length );

        for( int i = 0; i < excepted.length ; i ++ )
        {
            assertEquals( excepted[i], actual[i] );
        }
    }

    @Test
    public void test_fib_2()
    {
        int num = 10;

        int[] excepted = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 };

        int[] actual = fib_2( num );

        assertEquals( excepted.length, actual.length );

        for( int i = 0; i < excepted.length ; i ++ )
        {
            assertEquals( excepted[i], actual[i] );
        }
    }

    @Test
    public void test_improved_fib_1()
    {
        int num = 10;

        int[] excepted = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 };

        int[] actual = new int[11];

        for( int i = 0; i <= num; i++ )
        {
            actual[i] = improved_fib_1( i );
        }

        assertEquals( excepted.length, actual.length );

        for( int i = 0; i < excepted.length ; i ++ )
        {
            assertEquals( excepted[i], actual[i] );
        }
    }

    @Test
    public void test_improved_fib_2()
    {
        int num = 10;

        int[] excepted = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 };

        int[] actual = improved_fib_2( num );

        assertEquals( excepted.length, actual.length );

        for( int i = 0; i < excepted.length ; i ++ )
        {
            assertEquals( excepted[i], actual[i] );
        }
    }
}
