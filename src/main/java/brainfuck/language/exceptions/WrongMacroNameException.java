package brainfuck.language.exceptions;

/**
 * Signale si il reste un caractère d'une macro
 * @author jamatofu on 04/11/16.
 */
public class WrongMacroNameException extends Exception{
    /**
     * Constructeur de BadMacro
     */
    public WrongMacroNameException(String message) {
        super("Invalid name for macro : " + message);
    }
}
