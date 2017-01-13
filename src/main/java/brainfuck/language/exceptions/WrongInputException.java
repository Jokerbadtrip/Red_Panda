package brainfuck.language.exceptions;

/**
 * Catte exception est levée lorsqu'on appelle une commande IN et qu'on ne renvoie pas seulement UN caractere ascii
 *
 * @author  Red_Panda
 */
public class WrongInputException extends Exception {

    /**
     * Constructeur pour WrongInput
     */
    public WrongInputException() {
        super("Unavailable entered data");
    }
}