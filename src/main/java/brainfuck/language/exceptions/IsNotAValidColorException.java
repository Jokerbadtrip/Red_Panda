package brainfuck.language.exceptions;

/**
 * Cette exception est levée lorsque la couleur actuelle n'en est pas une,
 * et qu'elle n'est donc pas interpretable
 *
 * @author  Red_Panda
 */

public class IsNotAValidColorException extends RuntimeException {

    /**
     * Constructeur pour IsNotAValidColorException
     */
    public IsNotAValidColorException() {
        super("Error Code -1 : One of the colors is not a valid color");
    }
}
