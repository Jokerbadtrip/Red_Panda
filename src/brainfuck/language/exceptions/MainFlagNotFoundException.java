package brainfuck.language.exceptions;

/**
 * Created by MSI on 21/11/2016.
 */
public class MainFlagNotFoundException extends RuntimeException {
    public MainFlagNotFoundException() {
    }

    @Override
    public String toString() {
        return "You need to specify the main flag : -p \n exemple : ./bfck -p myfilepath";
    }
}
