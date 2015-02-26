package learn.dsaa.stack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * @author Kuo Zhang
 *
 */
public class TestMyStack
{
    @Test
    public void testStackByArray()
    {
        StackByArray<Integer> stack = new StackByArray<Integer>();

        stack.push( 1 );
        stack.push( 2 );
        stack.push( 3 );
        stack.push( 4 );
        stack.push( 5 );

        assertEquals( 5, stack.pop().intValue() );
        assertEquals( 4, stack.top().intValue() );
        assertEquals( 4, stack.pop().intValue() );
        assertEquals( 3, stack.pop().intValue() );

        stack.push( 10 );
        assertEquals( 10, stack.top().intValue() );
        assertEquals( 10, stack.pop().intValue() );
        assertEquals( 2, stack.top().intValue() );
    }

    @Test
    public void testStackByList()
    {
        StackByList<Integer> stack = new StackByList<Integer>();

        stack.push( 1 );
        stack.push( 2 );
        stack.push( 3 );
        stack.push( 4 );
        stack.push( 5 );

        assertEquals( 5, stack.pop().intValue() );
        assertEquals( 4, stack.top().intValue() );
        assertEquals( 4, stack.pop().intValue() );
        assertEquals( 3, stack.pop().intValue() );

        stack.push( 10 );
        assertEquals( 10, stack.top().intValue() );
        assertEquals( 10, stack.pop().intValue() );
        assertEquals( 2, stack.top().intValue() );
    }

}
