package brainfuck.language.exceptions;

/**
 * @author jamatofu on 30/12/16.
 */
public class WrongFunctionNameException extends Exception{
    /**
     * Constructeur de BadMacro
     */
    public WrongFunctionNameException(String message) {
        super("Invalid name for function : " + message);
    }
}
