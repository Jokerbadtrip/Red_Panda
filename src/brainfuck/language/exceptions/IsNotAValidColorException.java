package brainfuck.language.exceptions;

/**
 * Created by SERRANO Simon on 25/11/2016.
 */

/**
 * This exception describes the fact that a color is a valid color or not, if not, then we throw a new exception
 */

public class IsNotAValidColorException extends RuntimeException {
    public IsNotAValidColorException() {
    }

    @Override
    public String toString(){ return "One of the colors is not a valid color"; }
}
