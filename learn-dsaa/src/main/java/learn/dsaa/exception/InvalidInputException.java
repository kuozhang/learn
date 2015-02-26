package learn.dsaa.exception;


/**
 * @author Kuo Zhang
 *
 */
@SuppressWarnings( "serial" )
public class InvalidInputException extends RuntimeException
{
    public InvalidInputException()
    {
        super();
    }

    public InvalidInputException( String message )
    {
        super( message );
    }

}
