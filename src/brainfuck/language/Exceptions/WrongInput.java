package brainfuck.language.Exceptions;

/**
 * Created by jamatofu on 01/11/16.
 */
public class WrongInput extends RuntimeException {
    public WrongInput() {
        super();
    }

    public WrongInput(String message) {
        super(message);
    }
}
