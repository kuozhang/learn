
package learn.dsaa.sort;

/**
 * @author Kuo Zhang
 * 
 * use int for instance, to be more generic, can change the int type to <AnyType extends Comparable<? super AnyType>>,
 * and any comparison like "<", ">" and "==", use the method AnyType.compareTo(AnyType)
 * "<" : anAnyTypeObject.compareTo( anotherAnyTypeObject ) < 0
 * ">" : anAnyTypeObject.compareTo( anotherAnyTypeObject ) > 0
 * "==" : anAnyTypeObject.compareTo( anotherAnyTypeObject ) == 0
 */
public class SortUtil
{

    public static boolean arrayEquals( int[] a, int[] b )
    {
        if( a.length != b.length )
        {
            return false;
        }

        for( int i = 0; i < a.length; i++ )
        {
            if( a[i] != b[i] )
            {
                return false;
            }
        }

        return true;
    }

    public static void print( int[] values )
    {
        for( int i : values )
        {
            System.out.print( i + " ");
        }

        System.out.println();
    }
}
