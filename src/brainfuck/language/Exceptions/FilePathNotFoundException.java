package brainfuck.language.Exceptions;

/**
 * Created by Serrano Simon on 07/11/2016.
 */
public class FilePathNotFoundException extends RuntimeException {

    /**
     * Constructor for FilePathNotFoundException
     */
    public FilePathNotFoundException() { }

    /**
     * Prints a string in the console
     * @return "You need to specify a file path after the flag" every time
     */
    @Override
    public String toString(){return "You need to specify a file path after the flag";}
}
