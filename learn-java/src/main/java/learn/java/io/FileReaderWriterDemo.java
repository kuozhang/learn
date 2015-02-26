package learn.java.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author Kuo Zhang
 *
 * Demo for FileInputStream and FileOutputStream
 */
public class FileReaderWriterDemo
{
    public static void main( String[] args )
    {
        FileReaderWriterDemo demo = new FileReaderWriterDemo();
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

    // implement coping file with FileReader and FileWriter
    public void copyFile( File srcFile, File destFile )
    {
        FileReader reader = null;
        FileWriter writer = null; 

        try
        {
            reader = new FileReader( srcFile );
            writer = new FileWriter( destFile );

            char[] buf = new char[1024];
            int avail = reader.read( buf );

            while( avail > -1 )
            {
                writer.write( buf, 0, avail );
                avail =reader.read( buf );
            }

        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if( reader != null )
                {
                    reader.close();
                }
            }
            catch( IOException e ) { e.printStackTrace(); }
            try
            {
                if( writer != null )
                {
                    writer.close();
                }
            }
            catch( IOException e ) { e.printStackTrace(); }
        }
    }
}
