package brainfuck.language.exceptions;

/**
 * Cette exception est levée lorsque la couleur actuelle n'en est pas une,
 * et qu'elle n'est donc pas interpretable
 *
 * @author  Red_Panda
 */

public class IsNotAValidColorException extends Exception {

    /**
     * Constructeur pour IsNotAValidColorException
     */
    public IsNotAValidColorException() {}

    /**
     * Renvoie le texte correspondant à la description de l'erreur
     * @return "One of the colors is not a valid color" est toujours renvoié
     */
    @Override
    public String toString(){ return "Error Code -1 : One of the colors is not a valid color"; }
}
