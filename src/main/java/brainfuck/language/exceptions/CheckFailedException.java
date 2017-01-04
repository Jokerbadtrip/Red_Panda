package brainfuck.language.exceptions;

/**
 * Décrit le fait que le nombre de JUMP n'est pas le même que le nombre de BACK
 *
 * Created by Red_Panda on 14/12/16.
 */
public class CheckFailedException extends Exception {

    public CheckFailedException() {
        super();
    }

    @Override
    public String toString(){
        return "Code Error 4 : Check your brackets";
    }




}
