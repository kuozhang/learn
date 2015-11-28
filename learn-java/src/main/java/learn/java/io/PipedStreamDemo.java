
package learn.java.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamDemo
{

    public static void main( String[] args )
    {
        PipedStreamDemo demo = new PipedStreamDemo();

        Send send = demo.new Send();
        Receive receive = demo.new Receive();

        PipedOutputStream out = send.getPipedOutputStream();
        PipedInputStream in = receive.getPipedInputStream();

        try
        {
            out.connect( in );
            send.start();
            receive.start();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

    }

    class Send extends Thread
    {

        PipedOutputStream outStream = new PipedOutputStream();

        public PipedOutputStream getPipedOutputStream()
        {
            return this.outStream;
        }

        @Override
        public void run()
        {
            try
            {
                outStream.write( "Demo for PipedStream".getBytes() );
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    outStream.close();
                }
                catch( IOException e )
                {
                    e.printStackTrace();
                }
            }
        }
    }

    class Receive extends Thread
    {

        private PipedInputStream inStream = new PipedInputStream();

        public PipedInputStream getPipedInputStream()
        {
            return this.inStream;
        }

        @Override
        public void run()
        {
            try
            {
                byte[] buf = new byte[1024];
                StringBuilder sb = new StringBuilder();

                int avail = inStream.read( buf );

                while( avail > 0 )
                {
                    sb.append( new String( buf, 0, avail ) );
                    avail = inStream.read( buf );
                }

                System.out.println( sb.toString() );
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    inStream.close();
                }
                catch( IOException e )
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
