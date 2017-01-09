package brainfuck.language.exceptions;

/**
 * Cette exception est levée lorsque le chemin d'accès n'a pas été entré en argument
 *
 * @author  Red_Panda
 */
public class FilePathNotFoundException extends RuntimeException {
    String s;

    public FilePathNotFoundException(String message) {
        super("Error Code 3 : You need to specify a file path after the flag " + message);
        this.s = message;
    }

    /**
     * Renvoie le texte correspondant à la description de l'erreur
     * @return "You need to specify a file path after the flag" est toujours renvoié
     */
//    @Override
//    public String toString() {
//        return "Error Code 3 : You need to specify a file path after the flag " + s;
//    }
}
