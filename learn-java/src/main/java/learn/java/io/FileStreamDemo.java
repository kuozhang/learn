package learn.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author Kuo Zhang
 *
 * Demo for FileInputStream and FileOutputStream
 */
public class FileStreamDemo
{
    public static void main( String[] args )
    {
        FileStreamDemo demo = new FileStreamDemo();
        demo.moveFile( "resources/foo.txt", "resources_" );
    }

    public void moveFile( String filePath, String destPath )
    {
        File srcFile = new File( filePath );
        File destFile = new File( destPath, srcFile.getName() );

        copyFile( srcFile, destFile );

        srcFile.delete();
    }

    // the destination file will be destpath/file name
    public void copyFile( String filePath, String destPath )
    {
        // skip the normal check

        File srcFile = new File( filePath );
        File destFile = new File( destPath, srcFile.getName() );

        copyFile( srcFile, destFile );
    }

    public void copyFile( File srcFile, File destFile )
    {
        InputStream is = null;
        OutputStream os = null;

        try
        {
            is = new FileInputStream( srcFile );
            os = new FileOutputStream( destFile );

            byte[] buf = new byte[1024];
            int avail = is.read( buf );

            while( avail > 0 )
            {
                os.write( buf, 0, avail );
                avail = is.read( buf );
            }

        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if( is != null )
                {
                    is.close();
                }
            }
            catch( IOException e ) { e.printStackTrace(); }
            try
            {
                if( os != null )
                {
                    os.close();
                }
            }
            catch( IOException e ) { e.printStackTrace(); }
        }
    }
}
