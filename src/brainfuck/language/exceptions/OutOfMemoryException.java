package brainfuck.language.exceptions;

/**
 * Cette exception est levée lorsque le pointeur a dépassé les limites imposées (limite maximale ou minimale)
 *
 * @author  Red_Panda
 */
public class OutOfMemoryException extends RuntimeException {

    /**
     * Constructeur pour OutOfMemoryException
     */
    public OutOfMemoryException() {}

    /**
     * Renvoie le texte correspondant à la description de l'erreur
     * @return "Error Code : 2"  est toujours renvoié
     */
    @Override
    public String toString(){return "Error Code : 2";}
}
