package brainfuck.language.exceptions;

/**
 * Signale si il reste un caractère d'une macro
 * @author jamatofu on 04/11/16.
 */
public class WrongMacroNameException extends Exception{

    /**
     * Constructeur de BadMacro
     */
    public WrongMacroNameException() {super(); }

    @Override
    public String toString(){return "Error Code -1 : One of the macros has a reserved name.";}
}
