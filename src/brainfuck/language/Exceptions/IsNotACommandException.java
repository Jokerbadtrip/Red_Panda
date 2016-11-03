package brainfuck.language.Exceptions;

/**
 * Created by MSI on 03/11/2016.
 */
public class IsNotACommandException extends RuntimeException {
    public IsNotACommandException() { }

    @Override
    public String toString(){return "One of the commands is not an available command";}


}
