package brainfuck.language.exceptions;

/**
 * Created by Enzo on 04/01/2017.
 */
public class KeywordsConversionException extends RuntimeException {

    /**
     * Constructeur pour KeywordsConversionException
     */
    public KeywordsConversionException() {
        super("Error Code -1 : Conversion impossible");
    }
}

