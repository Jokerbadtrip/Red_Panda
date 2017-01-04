package brainfuck.language.exceptions;

/**
 * Created by Enzo on 04/01/2017.
 */
public class KeywordsConversionException extends RuntimeException {

    /**
     * Constructeur pour KeywordsConversionException
     */
    public KeywordsConversionException() {}

    /**
     * Renvoie le texte correspondant à la description de l'erreur
     * @return "Conversion impossible" est toujours renvoié
     */
    @Override
    public String toString(){ return "Error Code -1 : Conversion impossible"; }
}

