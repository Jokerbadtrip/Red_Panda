import brainfuck.language.*;

import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
/*
        Macro macro = new Macro();
        macro.findMacro("@DIGIT +++\n@COOL ---\nDIGITDIGITCOOLCOOLINCR\n");
        macro.remplacerMacroParCode();
        System.out.print(macro.toutEnOrdre());
*/

        Motor motor = new Motor(args);
        motor.lancerProgramme();
    }
}
