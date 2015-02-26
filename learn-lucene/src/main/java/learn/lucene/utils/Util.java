package learn.lucene.utils;

import java.io.IOException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;


/**
 * @author Kuo Zhang
 *
 */
public class Util
{

    public static void deleteAllDocs( IndexWriter writer )
    {
        try
        {
            writer.deleteAll();
        }
        catch( IOException e )
        {
            logException( e );
        }
    }

    public static void deleteDocsByTerms( IndexWriter writer, Term... terms )
    {
        try
        {
            writer.deleteDocuments( terms );
        }
        catch( IOException e )
        {
            logException( e );
        }
    }

    public static void deleteDocsByQueries( IndexWriter writer, Query... queries )
    {
        try 
        {
            writer.deleteDocuments( queries );
        }
        catch( IOException e )
        {
            logException( e );
        }
    }

    public static void logException( Exception e )
    {
        e.printStackTrace();
        // TODO log exception
    }

}
