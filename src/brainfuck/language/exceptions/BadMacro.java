package brainfuck.language.exceptions;

/**
 * Signale si il reste un caractère d'une macro
 * @author jamatofu on 04/11/16.
 */
public class BadMacro extends RuntimeException{
    @Override
    public String toString(){return "Une de vos macros a un nom réservé.";}
}
