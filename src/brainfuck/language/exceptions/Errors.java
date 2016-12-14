package brainfuck.language.exceptions;

import java.io.FileNotFoundException;

/**
 * Created by Red_Panda on 14/12/16.
 */
public class Errors {

    public Errors() {
    }

    public void handle(BadMacro e){
        System.out.println("Error Code : -1");
        System.out.println("One of the macros has a reserved name");
    }

    public void handle(FilePathNotFoundException e){}

    public void handle(FileNotFoundException e){
        System.out.println("Error Code : 3");
    }
}
