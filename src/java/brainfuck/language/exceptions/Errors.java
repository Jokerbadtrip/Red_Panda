package brainfuck.language.exceptions;

import java.io.FileNotFoundException;

/**
 * Created by Red_Panda on 14/12/16.
 */
public class Errors extends RuntimeException{

    public Errors() {}

    public void handle(WrongMacroNameException e){ System.out.println(e.toString()); }

    public void handle(CheckFailedException e){ System.out.println(e.toString()); }

    public void handle(FilePathNotFoundException e){System.out.println(e.toString());}

    public void handle(FileNotFoundException e){ System.out.println("Error Code 3 : One or many files have not been found"); }

    public void handle(IsNotACommandException e){ System.out.println(e.toString()); }

    public void handle(IsNotAValidColorException e){ System.out.println(e.toString()); }

    public void handle(MainFlagNotFoundException e){ System.out.println(e.toString()); }

    public void handle(OutOfMemoryException e){ System.out.println(e.toString());}

    public void handle(UnknownFlagsException e){ System.out.println(e.toString()); }

    public void handle(ValueOutOfBoundException e){ System.out.println(e.toString());}

    public void handle(WrongInputException e){ System.out.println(e.toString()); }

}
