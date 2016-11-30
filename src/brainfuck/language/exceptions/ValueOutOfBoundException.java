package brainfuck.language.exceptions;

/**
 * Cette exception est levée lorsque la valeur de la case mémoire actuelle a dépassé
 * les limites imposées (que ce soit la valeur maximale ou minimale)
 *
 * @author  Red_Panda
 */
public class ValueOutOfBoundException extends RuntimeException {

    /**
     * Constructeur pour the ValueOutOfBoundException
     */
    public ValueOutOfBoundException(){}

    /**
     * Renvoie le texte correspondant à la description de l'erreur
     * @return "Error Code : 1"  est toujours renvoié
     */
    @Override
    public String toString(){return "Error Code : 1" ;}

}
