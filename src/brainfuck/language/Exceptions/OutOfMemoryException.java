package brainfuck.language.Exceptions;

/**
 * This exception describes the fact that the pointer of the memory is out of the memory
 * Created by SERRANO Simon on 03/11/2016.
 */
public class OutOfMemoryException extends RuntimeException {

    /**
     * Constructor for OutOfMemoryException
     */
    public OutOfMemoryException() { }

    /**
     * Prints the error in the console
     * @return "Error Code : 2" all the time
     */
    @Override
    public String toString(){return "Error Code : 2";}
}
