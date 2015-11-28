
package learn.java.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Kuo Zhang Demo for BufferedReader and BufferedWriter
 */
public class BufferedReaderWriterDemo
{

    public static void main( String[] args )
    {
        BufferedReaderWriterDemo demo = new BufferedReaderWriterDemo();
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
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try
        {
            reader = new BufferedReader( new FileReader( srcFile ) );
            writer = new BufferedWriter( new FileWriter( destFile ) );

            String line = reader.readLine();

            while( line != null )
            {
                writer.write( line );
                writer.newLine();
                line = reader.readLine();
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
            catch( IOException e )
            {
                e.printStackTrace();
            }
            try
            {
                if( writer != null )
                {
                    writer.close();
                }
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

}
