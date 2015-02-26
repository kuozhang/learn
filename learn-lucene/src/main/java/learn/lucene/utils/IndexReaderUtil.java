package learn.lucene.utils;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;


/**
 * @author Kuo Zhang
 *
 */
public class IndexReaderUtil
{
    public static IndexReader getReader( Directory dic ) throws IOException
    {
        return DirectoryReader.open( dic );
    }

    public static IndexReader getReader( String indexPath ) throws IOException
    {
        return DirectoryReader.open( DirectoryUtil.getDirectory( indexPath ) );
    }
}
