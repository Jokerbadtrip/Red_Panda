package brainfuck.language.exceptions;

import brainfuck.language.enumerations.Flags;

/**
 * Created by Red_Panda on 30/11/2016.
 *
 * This exception is thrown when a flag is not a valid flag
 * Valid flags : -p -i -o --rewrite --check --translate --trace
 */
public class UnknownFlagsException extends RuntimeException {
    private String flags;

    public UnknownFlagsException(String flags) {
        this.flags=flags;
    }

    @Override
    public String toString(){
        return "The flag "+flags+" is not a valid flag.";
    }
}
