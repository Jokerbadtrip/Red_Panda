package brainfuck.language.exceptions;

/**
 * Cette exception est levée lorsque la valeur de la case mémoire actuelle a dépassé
 * les limites imposées (que ce soit la valeur maximale ou minimale)
 *
 * @author  Red_Panda
 */
public class ValueOutOfBoundException extends Exception {

    /**
     * Constructeur pour ValueOutOfBoundException
     */
    public ValueOutOfBoundException(){}

    /**
     * Renvoie le texte correspondant à la description de l'erreur
     * @return "Error Code : 1"  est toujours renvoyé
     */
    @Override
    public String toString(){
        return "Error Code 1 : One of the cell has been incremented while being set at the value 255 or" +
            " one of the cell has been decremented while being set at the value 0" ;
    }

}
