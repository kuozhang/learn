
package learn.java.misc;

import java.io.File;
import java.io.IOException;

public class Test
{

    public static void main( String[] args )
    {
        diffCanonicialAndAbsolutePath();
    }

    public static void diffCanonicialAndAbsolutePath()
    {
        File file1 = new File( "foo" );
        File file2 = new File( "../bar" );

        try
        {

            System.out.println( "Two paths are same if there are no '.' or '..' in file path." );
            System.out.println( file1.getAbsolutePath() );
            System.out.println( file1.getCanonicalPath() );

            System.out.println( "Two paths are different." );
            System.out.println( file2.getAbsolutePath() );
            System.out.println( file2.getCanonicalPath() );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

}
