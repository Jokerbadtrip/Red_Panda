package brainfuck.language.exceptions;

/**
 * This exception describes the fact that a cell in the memory cannot be incremented or decremented because the min/max
 * value is reached
 * Created by SERRANO Simon on 03/11/2016.
 */
public class ValueOutOfBoundException extends RuntimeException {

    /**
     * Constructor for the ValueOutOfBoundException
     */
    public ValueOutOfBoundException(){}

    /**
     * Prints the error in the console
     * @return "Error Code : 1" everytime
     */
    @Override
    public String toString(){return "Error Code : 1" ;}

}
