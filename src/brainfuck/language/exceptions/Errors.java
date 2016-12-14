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

    public void handle(CheckFailedException e){
        System.out.println("Code Error : 4");
    }

    public void handle(FilePathNotFoundException e){System.out.println(e.toString());}

    public void handle(FileNotFoundException e){
        System.out.println("Error Code : 3");
    }

    public void handle(IsNotACommandException e){
        errorCode();
        System.out.println(e.toString());
    }

    public void handle(IsNotAValidColorException e){
        errorCode();
        System.out.println(e.toString());
    }

    public void handle(MainFlagNotFoundException e){
        errorCode();
        System.out.println(e.toString());
    }

    public void handle(OutOfMemoryException e){ System.out.println(e.toString());}

    public void handle(UnknownFlagsException e){
        errorCode();
        System.out.println(e.toString());
    }

    public void handle(ValueOutOfBoundException e){ System.out.println(e.toString());}

    public void handle(WrongInput e){
        errorCode();
        System.out.println(e.toString());
    }

    private void errorCode(){
        System.out.println("Error Code : -1");
    }
}
