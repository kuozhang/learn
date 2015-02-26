package learn.lucene.usages;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.junit.Test;


/**
 * @author Kuo Zhang
 *
 */
public class IndexSearchUsage
{
    @Test
    public void testIndexFiles() throws IOException, ParseException 
    {

        // LuceneDemo.indexDocuments();
        // LuceneDemo.indexLicenses();

        QueryParser parser = new QueryParser( "contents", new StandardAnalyzer() );
        Query query = parser.parse( "Developer" );

        // it turns out that TermQuery doesn't work well !
        // Query query = new TermQuery( new Term( "contents", "Developer" ) );

        // Document[] documents = LuceneDemo.searchDocuments( query );

        Document[] documents = UsageUtil.searchLicenses( query, 1 );

        /*
        FSDirectory dic = FSDirectory.open( new File( LuceneDemo.LICENSES_INDEX_PATH ) );

        DirectoryReader reader = DirectoryReader.open( dic );

        IndexSearcher searcher = new IndexSearcher( reader );
        TopDocs results = searcher.search( query, 1000 );
        ScoreDoc[] documents = results.scoreDocs;

        for( ScoreDoc doc : documents )
        {
            System.out.println( searcher.doc( doc.doc ).get( "path" ) );
        }
         */

        for( Document doc: documents )
        {
            System.out.println( doc.get( "name") ) ;
        }
    }
}
