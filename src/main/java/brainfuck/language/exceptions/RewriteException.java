package brainfuck.language.exceptions;

/**
 * Si un programme ne peut pas être "rewrite", cette exception est retournée
 * @author jamatofu on 29/12/16.
 */
public class RewriteException extends Exception {

    @Override
    public String toString(){
        return "Program can't be rewrited. Check your program.";
    }
}
