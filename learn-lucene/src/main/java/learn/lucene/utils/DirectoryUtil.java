package learn.lucene.utils;

import java.io.File;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 * @author Kuo Zhang
 *
 */
public class DirectoryUtil
{
    public static Directory getDirectory( String path )
    {
        try
        {
            return FSDirectory.open( new File( path ) );
        }
        catch( Exception e )
        {
            Util.logException( e );
        }

        return null;
    }

}
