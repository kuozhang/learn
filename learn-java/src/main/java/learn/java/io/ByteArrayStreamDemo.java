
package learn.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteArrayStreamDemo
{

    public static void main( String[] args )
    {
        String content = "Demo for ByteArrayStream";
        ByteArrayInputStream in = new ByteArrayInputStream( content.getBytes() );
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try
        {
            toUpper( in, out );
            System.out.println( out.toString() );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
                out.close();
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
        }

    }

    public static void toUpper( InputStream in, OutputStream out ) throws IOException
    {
        int ch = 0;

        while( ( ch = in.read() ) != -1 )
        {
            out.write( Character.toUpperCase( ch ) );
        }
    }

}
