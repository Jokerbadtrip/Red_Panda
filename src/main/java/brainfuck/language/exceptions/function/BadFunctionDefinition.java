package brainfuck.language.exceptions.function;

/**
 * @author jamatofu on 04/01/17.
 */
public class BadFunctionDefinition extends Exception{

    public BadFunctionDefinition(String name) {
        super("It is not a valid name for function/ macro : " + name);
    }
}
