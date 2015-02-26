package learn.java.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author Kuo Zhang
 * 
 * Demo for process stream, not finished
 */
public class ProcessStreamDemo implements Runnable
{

    private Process p = null;

    private boolean received = false;

    public ProcessStreamDemo() throws IOException
    {
        p = Runtime.getRuntime().exec( "java ChildProcess" );
        new Thread( this ).start();
    }

    public static void main( String[] args ) throws IOException
    {
        ProcessStreamDemo demo = new ProcessStreamDemo();
        demo.send();
    }

    public void send() throws IOException
    {
        OutputStream os = p.getOutputStream();
        BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( os ) );

        while( true )
        {
            bw.write( "Foo" );
//            System.out.println("Sent: Foo");
        }
    }

    public void run()
    {
        InputStream in = p.getInputStream();
        BufferedReader br = new BufferedReader( new InputStreamReader( in ) );

        try
        {
            String receivedMsg = br.readLine();
            while( receivedMsg == null )
            {
                receivedMsg = br.readLine();
            }

            System.out.println( "Receive: " + receivedMsg );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
}
