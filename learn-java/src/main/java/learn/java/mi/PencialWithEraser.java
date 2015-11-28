
package learn.java.mi;

import org.junit.Test;

/**
 * @author Kuo Zhang 使用嵌套类来模拟实现Java中的多重继承的功能
 */
public class PencialWithEraser
{

    private Pencial pencial = new MyPencial();
    private Eraser eraser = new MyEraser();

    private class MyPencial extends Pencial
    {

        @Override
        public void write()
        {
            System.out.println( "Writing" );
        }
    }

    private class MyEraser extends Eraser
    {

        @Override
        public void erase()
        {
            System.out.println( "Erasing" );
        }
    }

    @Test
    public void write()
    {
        pencial.write();

        // 匿名类, 能够重写方法, 能添加新方法, 但外部无法调用后加入的方法
        Pencial pencial2 = new MyPencial()
        {

            @Override
            public void write()
            {
                System.out.println( "This is an anonymous class" );
            }

            public void newMethod()
            {
            }
        };

        pencial2.write();
    }

    public void erase()
    {
        eraser.erase();
    }
}
