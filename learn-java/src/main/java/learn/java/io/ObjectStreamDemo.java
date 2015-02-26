package learn.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * @author Kuo Zhang
 * 
 * ObjectInputStream and ObjectOutputStream for 
 */
public class ObjectStreamDemo
{
    public static void main( String[] args ) throws Exception
    {
        Student s1 = new Student( "Foo", 20 );
        Student s2 = new Student( "Bar", 30 );

        File file = new File( "Students" );
        ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream( "Students") );

        os.writeObject( s1 );
        os.writeObject( s2 );
        os.close();

        file.renameTo( new File( "Students_" ) );

        ObjectInputStream is = new ObjectInputStream( new FileInputStream( "Students_" ) );

        Student s1_ =  (Student)is.readObject();
        Student s2_ =  (Student)is.readObject();

        s1_.info();
        s2_.info();

        is.close();
    }

}

class Student implements Serializable
{
    private static final long serialVersionUID = 1L;

    public Student( String name, int age )
    {
        this.setName( name );
        this.setAge( age );
    }
    private String name;
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge( int age )
    {
        this.age = age;
    }

    public void info()
    {
        System.out.println( "Name:" + this.getName() + "Age: " + this.getAge() );
    }
}
