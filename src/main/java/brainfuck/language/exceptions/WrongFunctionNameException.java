package brainfuck.language.exceptions;

/**
 * @author jamatofu on 30/12/16.
 */
public class WrongFunctionNameException extends Exception{
    String message;

    /**
     * Constructeur de BadMacro
     */
    public WrongFunctionNameException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Invalid name for function : " + message;
    }
}
