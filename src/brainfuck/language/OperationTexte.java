package brainfuck.language;

import brainfuck.language.enumerations.Keywords;

import java.util.ArrayList;

import static brainfuck.language.enumerations.Keywords.shortcutToString;

/**
 * Contient les méthodes qui manipule du texte
 * @author  Red_Panda
 */
public class OperationTexte {

    /**
     * Permet de transformer les instructions mot en instruction raccourci (--rewrite)
     * @param programmeATraduire liste contenant des instructions en mot
     * @return la liste de commande en shortcut
     */
    public static String transformerInstructionEnSymbole(String programmeATraduire){
        programmeATraduire = programmeATraduire.replaceAll("RIGHT", ">");
        programmeATraduire = programmeATraduire.replaceAll("LEFT", "<");
        programmeATraduire = programmeATraduire.replaceAll("INCR", "+");
        programmeATraduire = programmeATraduire.replaceAll("DECR", "-");
        programmeATraduire = programmeATraduire.replaceAll("JUMP", "[");
        programmeATraduire = programmeATraduire.replaceAll("BACK", "]");
        programmeATraduire = programmeATraduire.replaceAll("OUT", ".");
        programmeATraduire = programmeATraduire.replaceAll("IN", ",");

        return programmeATraduire;
    }

    /**
     * Permet d'afficher une liste
     * @param liste une liste
     */
    public static void toString(ArrayList<Keywords> liste) { // permet d'afficher une liste
        for (Keywords comm : liste) {
            System.out.print(shortcutToString(comm));
        }
        System.out.println();
    }
}
