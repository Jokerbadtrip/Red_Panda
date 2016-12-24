package brainfuck.language;

import brainfuck.language.exceptions.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        Errors error = new Errors();

        try {
            Motor motor = new Motor(args);
            motor.lancerProgramme();
        }
        catch (UnknownFlagsException e){error.handle(e);}
        catch (CheckFailedException e){error.handle(e);}
        catch (FilePathNotFoundException e){error.handle(e);}
       // catch (IsNotAValidColorException e){error.handle(e);}
        catch (IsNotACommandException e){error.handle(e);}
        catch (MainFlagNotFoundException e){error.handle(e);}
        catch (OutOfMemoryException e){error.handle(e);}
        catch (ValueOutOfBoundException e){error.handle(e);}
        catch (WrongInputException e){error.handle(e);}
        catch (WrongMacroNameException e){error.handle(e);}
        catch (FileNotFoundException e){error.handle(e);}



    }
}
