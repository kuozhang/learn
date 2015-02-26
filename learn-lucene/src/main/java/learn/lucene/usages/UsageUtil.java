package learn.lucene.usages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import learn.lucene.utils.IndexSearcherUtil;
import learn.lucene.utils.IndexWriterUtil;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;


/**
 * @author Kuo Zhang
 * 
 * wrap indexing and searching process, for convenient usage 
 */
public class UsageUtil
{ 
    public static final String DOCS_PATH = "src/test/resources/docs";
    public static final String LICENSES_PATH = "src/test/resources/licenses";

    public static final String DOCS_INDEX_PATH = "src/test/resources/index/docs";
    public static final String LICENSES_INDEX_PATH = "src/test/resources/index/licenses";

    public static void indexLicenses() throws IOException
    {
        indexFiles( LICENSES_INDEX_PATH, LICENSES_PATH );
    }

    public static void indexDocuments() throws IOException
    {
        indexFiles( DOCS_INDEX_PATH, DOCS_PATH );
    }

    public static void indexFiles( String indexPath, String docsPath ) throws IOException
    {
        final IndexWriter writer = IndexWriterUtil.getWriter( indexPath );

        index( writer, new File( docsPath ) );

        writer.commit();

        System.out.println( "Indexing done." );
    }

    // index the given file or directory with four fields: name, path, last modified time, contents
    private static void index( IndexWriter writer, File file ) throws IOException
    {
        if( file.canRead() )
        {
            if( file.isDirectory() )
            {
                String[] files = file.list();

                for( String f : files )
                {
                    index( writer, new File( file, f ) );
                }
            }
            else
            {
                Document doc = new Document();

                Field nameField = new StringField( "name", file.getName(), Field.Store.YES );
                Field pathField = new StringField( "path" , file.getPath(), Field.Store.YES );
                Field timeField = new LongField( "moditied_name", file.lastModified(), Field.Store.NO );
                Field contentsField =
                    new TextField( "contents", new BufferedReader( new InputStreamReader(
                        new FileInputStream( file ), StandardCharsets.UTF_8 ) ) );

                doc.add( nameField );
                doc.add( pathField );
                doc.add( timeField );
                doc.add( contentsField );

                writer.addDocument( doc );

                System.out.println( file + " has been added." );
            }
        }
    }

    public static Document[] searchLicenses( Query query ) throws IOException
    {
        return searchFiles( LICENSES_INDEX_PATH , 10, query );
    }

    public static Document[] searchLicenses( Query query, int hitsCount ) throws IOException
    {
        return searchFiles( LICENSES_INDEX_PATH, hitsCount, query );
    }

    public static Document[] searchDocuments( Query query ) throws IOException
    {
        return searchFiles( DOCS_INDEX_PATH, 10, query );
    }

    public static Document[] searchDocuments( Query query, int hitsCount ) throws IOException
    {
        return searchFiles( DOCS_INDEX_PATH, hitsCount, query );
    }

    public static Document[] searchFiles( String indexPath, int hitsCount, Query query ) throws IOException
    {
        return searchFiles( indexPath, hitsCount, query, null, null );
    }

    public static Document[] searchFiles( String indexPath, int hitsCount, Query query, Filter filter ) throws IOException
    {
        return searchFiles( indexPath, hitsCount, query, filter, null );
    }

    public static Document[] searchFiles( String indexPath, int hitsCount, Query query, Sort sort ) throws IOException
    {
        return searchFiles( indexPath, hitsCount, query, null, sort );
    }

    public static Document[] searchFiles( String indexPath, int hitsCount, Query query , Filter filter, Sort sort ) throws IOException
    {
        List<Document> retval = new ArrayList<Document>();

        IndexSearcher searcher = IndexSearcherUtil.getSearcher( indexPath );

        TopDocs topDocs = null;

        if( query == null )
        {
            return new Document[0];
        }

        if( filter != null && sort != null )
        {
            topDocs = searcher.search( query, filter, hitsCount, sort );
        }
        else if( filter != null && sort == null )
        {
            topDocs = searcher.search( query, filter, hitsCount );
        }
        else if( filter == null && sort != null )
        {
            topDocs = searcher.search( query, hitsCount, sort );
        }
        else if( filter == null && sort == null )
        {
            topDocs = searcher.search( query, hitsCount );
        }

        if( topDocs != null )
        {
            for( ScoreDoc s : topDocs.scoreDocs )
            {
                retval.add( searcher.doc( s.doc ) );
            }
        }

        return retval.toArray( new Document[0]);
    }
}
