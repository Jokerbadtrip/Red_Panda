package brainfuck.language.exceptions;

/**
 * Cette exception est levée lorsque l'instruction "-p" n'a pas été entré en argument dans la console
 *
 * @author  Red_Panda
 */

public class MainFlagNotFoundException extends RuntimeException {

    /**
     * Constructeur pour MainFlagNotFoundException
     */
    public MainFlagNotFoundException() {}

    /**
     * Renvoie le texte correspondant à la description de l'erreur
     * @return "You need to specify the main flag : -p \n exemple : ./bfck -p myfilepath" est toujours renvoié
     */
    @Override
    public String toString() {
        return "You need to specify the main flag : -p \n exemple : ./bfck -p myfilepath";
    }
}
