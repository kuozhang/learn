package learn.java.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author Kuo Zhang
 *
 * System.in and System.out are instances of InputStream and OutputStream
 */
public class SystemStreamDemo
{
    public static void main( String[] args )
    {
        try
        {
            toUpper( System.in, System.out );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    public static void toUpper( InputStream in, OutputStream out ) throws IOException
    {
        int ch = 0;
        while( ( ch = in.read() )!= -1 )
        {
           out.write( Character.toUpperCase( ch ) ); 
        }
    }

}
