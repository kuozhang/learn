package learn.lucene.utils;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;


/**
 * @author Kuo Zhang
 *
 */
public class IndexWriterUtil
{

    public static IndexWriter getWriter( String indexPath ) 
    {
        try
        {
            return getWriter( DirectoryUtil.getDirectory( indexPath ) );
        }
        catch( IOException e )
        {
            Util.logException( e );
        }

        return null;
    }

    public static IndexWriter getWriter( Directory dir ) throws IOException
    {
        return getWriter( dir, Version.LATEST, new StandardAnalyzer() );
    }

    public static IndexWriter getWriter( Directory dir, Analyzer analyzer ) throws IOException
    {
        return getWriter( dir, Version.LATEST, analyzer );
    }

    public static IndexWriter getWriter( Directory dir, Version version ) throws IOException
    {
        return getWriter( dir, version, new StandardAnalyzer() );
    }

    public static IndexWriter getWriter( Directory dir, Version version, Analyzer analyzer ) throws IOException
    {
        IndexWriterConfig conf = new IndexWriterConfig( version, analyzer );
        return new IndexWriter( dir, conf );
    }
}
