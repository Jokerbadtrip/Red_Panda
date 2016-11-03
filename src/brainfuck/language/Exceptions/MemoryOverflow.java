package brainfuck.language.Exceptions;

/**
 * Created by jamatofu on 01/11/16.
 */
public class MemoryOverflow extends RuntimeException {
    public MemoryOverflow() {
        super();
    }

    public MemoryOverflow(String message) {
        super(message);
    }
}
