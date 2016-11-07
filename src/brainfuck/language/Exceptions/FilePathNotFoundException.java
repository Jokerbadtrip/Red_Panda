package brainfuck.language.Exceptions;

/**
 * Created by MSI on 07/11/2016.
 */
public class FilePathNotFoundException extends RuntimeException {
    public FilePathNotFoundException() { }

    @Override
    public String toString(){return "You need to specify a file path after the flag";}
}
