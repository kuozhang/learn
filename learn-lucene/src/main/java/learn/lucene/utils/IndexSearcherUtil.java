package learn.lucene.utils;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;


/**
 * @author Kuo Zhang
 *
 */
public class IndexSearcherUtil
{
    public static IndexSearcher getSearcher( IndexReader reader )
    {
        return new IndexSearcher( reader );
    }

    public static IndexSearcher getSearcher( String indexPath ) throws IOException
    {
        return new IndexSearcher( IndexReaderUtil.getReader( indexPath ) );
    }
}
