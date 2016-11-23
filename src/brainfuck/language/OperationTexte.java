package brainfuck.language;

import java.util.ArrayList;

/**
 * Contient les méthodes qui manipule du texte
 * @author jamatofu on 04/11/16.
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
    public static void toString(ArrayList<String> liste) { // permet d'afficher une liste
        for (String comm : liste) {
            System.out.print(comm);
        }
        System.out.println();
    }
}
