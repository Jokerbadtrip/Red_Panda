package brainfuck.language.exceptions;

/**
 * Created by Red_Panda on 26/12/16.
 */
public class IncompatibleFlagsException extends Exception {
    public IncompatibleFlagsException() {
        super();
    }

    @Override
    public String toString() {
        return "At least two flags are incompatible";
    }
}
