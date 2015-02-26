/******************************************************************************
 * Copyright (c) 2010 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Original file was copied from
 * org.eclipse.wst.common.project.facet.core.util.internal.FileUtil
 *
 ******************************************************************************/

package learn.java.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

/**
 * @author Kuo Zhang
 *
 */
public class FileUtil
{

    public static void clearContents( File versionFile )
    {
        if( versionFile != null && versionFile.exists() )
        {
            try
            {
                RandomAccessFile file = new RandomAccessFile( versionFile, "rw" );
                file.setLength( 0 );
                file.close();
            }
            catch( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    public static void moveFile( File file, String destPath )
    {
        if( file != null && file.exists() )
        {
            System.out.println( "The file doesn't exit !" );
        }

        file.renameTo( new File( destPath, file.getName() ) );
    }

    public static void copyFileToDir( File file, File dir )
    {
        if( file == null || ( !file.exists() ) || dir == null || ( !dir.exists() ) || ( !dir.isDirectory() ) )
        {
            return;
        }

        byte[] buf = new byte[4096];

        OutputStream out = null;
        FileInputStream in = null;

        try
        {
            out = new FileOutputStream( new File( dir, file.getName() ) );
            in = new FileInputStream( file );

            int avail = in.read( buf );
            while( avail > 0 )
            {
                out.write( buf, 0, avail );
                avail = in.read( buf );
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
                if( in != null )
                {
                    in.close();
                }
            }
            catch( IOException e )
            {
                // ignore
            }

            try
            {
                if( out != null )
                {
                    out.close();
                }
            }
            catch( IOException ex )
            {
                // ignore
            }
        }
    }

    public static void deleteDir( File directory, boolean removeAll )
    {
        if( directory == null || !directory.isDirectory() )
        {
            return;
        }

        for( File file : directory.listFiles() )
        {
            if( file.isDirectory() && removeAll )
            {
                deleteDir( file, removeAll );
            }
            else
            {
                file.delete();
            }
        }

        directory.delete();
    }

    public static void deleteDirContents( final File directory )
    {
        if( directory == null || !directory.isDirectory() )
        {
            return;
        }

        for( File file : directory.listFiles() )
        {
            if( file.isDirectory() )
            {
                deleteDir( file, true );
            }
            else
            {
                file.delete();
            }
        }

    }

    public static void mkdirs( final File f )
    {
        f.mkdirs();
    }

    public static String readContents( File file )
    {
        return readContents( file, false );
    }

    public static String readContents( File file, boolean includeNewlines )
    {
        if( file == null )
        {
            return null;
        }

        if( !file.exists() )
        {
            return null;
        }

        StringBuffer contents = new StringBuffer();
        BufferedReader bufferedReader = null;

        try
        {
            FileReader fileReader = new FileReader( file );

            bufferedReader = new BufferedReader( fileReader );

            String line;

            while( ( line = bufferedReader.readLine() ) != null )
            {
                contents.append( line );

                if( includeNewlines )
                {
                    contents.append( System.getProperty( "line.separator" ) ); //$NON-NLS-1$
                }
            }
        }
        catch( Exception e )
        {
            // ignore
        }
        finally
        {
            if( bufferedReader != null )
            {
                try
                {
                    bufferedReader.close();
                }
                catch( IOException e )
                {
                    // best effort no need to log
                }
            }
        }

        return contents.toString();
    }

    public static String[] readLinesFromFile( File file )
    {
        if( file == null )
        {
            return null;
        }

        if( !file.exists() )
        {
            return null;
        }

        List<String> lines = new ArrayList<String>();
        BufferedReader bufferedReader = null;

        try
        {
            FileReader fileReader = new FileReader( file );

            bufferedReader = new BufferedReader( fileReader );

            String line;

            while( ( line = bufferedReader.readLine() ) != null )
            {
                lines.add( line );
            }
        }
        catch( Exception e )
        {
            // ignore
        }
        finally
        {
            if( bufferedReader != null )
            {
                try
                {
                    bufferedReader.close();
                }
                catch( Exception e )
                {
                    // no need to log, best effort
                }
            }
        }

        return lines.toArray( new String[lines.size()] );
    }

    public static String readStreamToString( InputStream contents ) throws IOException
    {
        if( contents == null )
        {
            return null;
        }

        final char[] buffer = new char[0x10000];

        StringBuilder out = new StringBuilder();

        Reader in = new InputStreamReader( contents, "UTF-8" );

        int read;
        do
        {
            read = in.read( buffer, 0, buffer.length );

            if( read > 0 )
            {
                out.append( buffer, 0, read );
            }
        }
        while( read >= 0 );

        contents.close();

        return out.toString();
    }

    public static Document readXML( InputStream inputStream, EntityResolver resolver, ErrorHandler error )
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        try
        {
            db = dbf.newDocumentBuilder();

            if( resolver != null )
            {
                db.setEntityResolver( resolver );
            }

            if( error != null )
            {
                db.setErrorHandler( error );
            }

            return db.parse( inputStream );
        }
        catch( Throwable t )
        {
            return null;
        }
    }

    public static Document readXML( String content )
    {
        return readXML( new ByteArrayInputStream( content.getBytes() ), null, null );
    }

    public static Document readXMLFile( File file )
    {
        return readXMLFile( file, null );
    }

    public static Document readXMLFile( File file, EntityResolver resolver )
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        try
        {
            db = dbf.newDocumentBuilder();

            if( resolver != null )
            {
                db.setEntityResolver( resolver );
            }

            return db.parse( file );
        }
        catch( Throwable t )
        {
            return null;
        }
    }


    public static boolean searchAndReplace( File file, String search, String replace ) throws FileNotFoundException, IOException
    {
        boolean replaced = false;

        if( file.exists() )
        {
            final String searchContents = readStreamToString( new FileInputStream( file ) );

            final String replaceContents = searchContents.replaceAll( search, replace );

            replaced = ! searchContents.equals( replaceContents );

            if( replaced )
            {
                writeStreamFromString( replaceContents, new FileOutputStream( file ) );
            }

        }

        return replaced;
    }

    public static int writeFileFromStream( File tempFile, InputStream in ) throws IOException
    {
        byte[] buffer = new byte[1024];

        BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream( tempFile ) );
        BufferedInputStream bin = new BufferedInputStream( in );

        int bytesRead = 0;
        int bytesTotal = 0;

        // Keep reading from the file while there is any content
        // when the end of the stream has been reached, -1 is returned
        while( ( bytesRead = bin.read( buffer ) ) != -1 )
        {
            out.write( buffer, 0, bytesRead );
            bytesTotal += bytesRead;
        }

        if( bin != null )
        {
            bin.close();
        }

        if( out != null )
        {
            out.flush();
            out.close();
        }

        return bytesTotal;
    }

    public static void writeStreamFromString( String contents, OutputStream outputStream ) throws IOException
    {
        if( contents == null )
        {
            return;
        }

        final char[] buffer = new char[0x10000];

        Reader in = new InputStreamReader( new ByteArrayInputStream( contents.getBytes( "UTF-8" ) ) );  //$NON-NLS-1$

        Writer out = new OutputStreamWriter( outputStream, "UTF-8" ); //$NON-NLS-1$

        int read;
        do
        {
            read = in.read( buffer, 0, buffer.length );

            if( read > 0 )
            {
                out.write( buffer, 0, read );
            }
        }
        while( read >= 0 );

        in.close();
        out.flush();
        out.close();
    }

    public static boolean compareContent( File file1, File file2 )
    {
        if( file1 == null || ! file1.exists() || file2 == null || ! file2.exists() )
        {
            return false; // or throw certain exception
        }

        if( file1.isDirectory() )
        {
            System.out.println( "file1 is a directory, cannot be compared." );
        }

        if( file2.isDirectory() )
        {
            System.out.println( "file1 is a directory, cannot be compared." );
        }

        if( file1.length() != file2.length() )
        {
            return false;
        }

        try
        {
            if( file1.getCanonicalFile().equals( file2.getCanonicalFile() ) )
            {
                return true;
            }
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        // compare the file contents using Stream
        try
        {
            FileInputStream is1 = new FileInputStream( file1 );
            FileInputStream is2 = new FileInputStream( file2 );
        }
        catch( FileNotFoundException e )
        {
            e.printStackTrace();
        }

        // compare the file contents using Reader
        return false;
    }

    // compare contents byte by byte
    public static boolean compareContents( InputStream is1, InputStream is2 ) throws IOException
    {
        BufferedInputStream bis1 = new BufferedInputStream( is1 );
        BufferedInputStream bis2 = new BufferedInputStream( is2 );

        int byte1 = bis1.read();

        // -1 means the end
        while( byte1 != -1 )
        {
            int byte2 = bis2.read();

            if( byte1 != byte2 )
            {
                return false;
            }

            byte1 = bis1.read();
        }

        int ch2 = bis1.read();

        return ch2 == -1;
    }

    // compare contents char by char
    public static boolean compareContents( Reader reader1, Reader reader2 ) throws IOException
    {
        BufferedReader br1 = reader1 instanceof BufferedReader ? (BufferedReader)reader1 : new BufferedReader( reader1 );
        BufferedReader br2 = reader2 instanceof BufferedReader ? (BufferedReader)reader2 : new BufferedReader( reader2 );

        int char1 = br1.read();

        // -1 means the end
        while( char1 != -1 )
        {
            int char2 = br2.read();
            if( char1 != char2 )
            {
                return false;
            }

            char1 = br1.read();
        }

        int ch2 = br1.read();

        return ch2 == -1;
    }

    public static boolean compareContentsByLine( Reader reader1, Reader reader2 ) throws IOException
    {
        BufferedReader br1 = reader1 instanceof BufferedReader ? (BufferedReader)reader1 : new BufferedReader( reader1 );
        BufferedReader br2 = reader2 instanceof BufferedReader ? (BufferedReader)reader2 : new BufferedReader( reader2 );

        String line1 = br1.readLine();
        String line2 = br2.readLine();

        while( line1 != null )
        {
            if( line2 == null || !line1.equals( line2 ) )
            {
                return false;
            }

            line1 = br1.readLine();
            line2 = br2.readLine();
        }

        return line2 == null ? true : false;
    }

}
