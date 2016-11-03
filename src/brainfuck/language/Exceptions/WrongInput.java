package brainfuck.language.Exceptions;

/**
 * Exception pour lacommande IN. Si on rentre une entrée
 * @author jamatofu on 01/11/16.
 */
public class WrongInput extends RuntimeException {
    public WrongInput() {
        super();
    }

    public WrongInput(String message) {
        super(message);
    }
}
