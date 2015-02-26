package learn.java.mt;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author Kuo Zhang
 * 
 * Executors is a factory which can produce different types of ThreadPool
 */
public class ExecutorsDemo
{
    public static void main( String[] args )
    {
//        new ExecutorsTest().testCachedThreadPool();
//        new ExecutorsTest().testFixedThreadPool();
        new ExecutorsDemo().testSingleThreadExecutor();
    }

    public void testCachedThreadPool()
    {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Callable<String> callableTask = TaskFactory.getCallableTask();

        for( int i = 0; i < 100; i++ )
        {
            try
            {
                Future<String> future = cachedThreadPool.submit( callableTask );
                System.out.println( future.get() );
            }
            catch( InterruptedException | ExecutionException e )
            {
                e.printStackTrace();
            }
        }

        Runnable runnableTask = TaskFactory.getRunnableTask();

        for( int i = 0; i < 100; i++ )
        {
            cachedThreadPool.submit( runnableTask );
        }

        // shut down the thread pool after being used
        cachedThreadPool.shutdown();
    }

    public void testFixedThreadPool()
    {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool( 5 );

        Callable<String> callableTask = TaskFactory.getCallableTask();

        for( int i = 0; i < 100; i++ )
        {
            try
            {
                Future<String> future = fixedThreadPool.submit( callableTask );
                System.out.println( future.get() );
            }
            catch( InterruptedException | ExecutionException e )
            {
                e.printStackTrace();
            }
        }

        Runnable runnableTask = TaskFactory.getRunnableTask();

        for( int i = 0; i < 100; i++ )
        {
            fixedThreadPool.submit( runnableTask );
        }

        fixedThreadPool.shutdown();
    }

    public void testSingleThreadExecutor()
    {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        Callable<String> callableTask = TaskFactory.getCallableTask();

        for( int i = 0; i < 100; i++ )
        {
            try
            {
                Future<String> future = singleThreadPool.submit( callableTask );
                System.out.println( future.get() );
            }
            catch( InterruptedException | ExecutionException e )
            {
                e.printStackTrace();
            }
        }

        Runnable runnableTask = TaskFactory.getRunnableTask();

        for( int i = 0; i < 100; i++ )
        {
            singleThreadPool.submit( runnableTask );
        }

        singleThreadPool.shutdown();
    }

    private void testThreadPool( ExecutorService threadPool )
    {
        Callable<String> callableTask = TaskFactory.getCallableTask();

        for( int i = 0; i < 100; i++ )
        {
            try
            {
                Future<String> future = threadPool.submit( callableTask );
                System.out.println( future.get() );
            }
            catch( InterruptedException | ExecutionException e )
            {
                e.printStackTrace();
            }
        }

        Runnable runnableTask = TaskFactory.getRunnableTask();

        for( int i = 0; i < 100; i++ )
        {
            threadPool.execute( runnableTask );
        }

        threadPool.shutdown();
    }
}

class TaskFactory
{

    // a Callable object returns a value
    public static Callable<String> getCallableTask()
    {
        return new Callable<String>()
        {
            @Override
            public String call() throws Exception
            {
                String retval = "A callable rask is running.";
                return retval;
            }
        };
    }

    // a Runnable object doesn't return a value
    public static Runnable getRunnableTask()
    {
        return new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println( "A runnable rask is running." );
            }
        };
    }
}
