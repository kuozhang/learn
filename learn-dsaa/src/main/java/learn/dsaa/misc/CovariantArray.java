package learn.dsaa.misc;


/**
 * @author Kuo Zhang
 *
 * 协变数组
 *
 * // TODO, to be continued...
 */
public class CovariantArray
{
    private class Person
    {
        protected String name;

        public String getName()
        {
            return name;
        }

        public void setName( String name )
        {
            this.name = name;
        }
    }

    private class Employee extends Person
    {

    }

    private class Student extends Person
    {

    }
}
