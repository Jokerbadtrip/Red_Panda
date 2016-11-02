package brainfuck.language;

import java.util.ArrayList;

/**
 * @author BEAL Clément, SERRANO Simon on 28/09/16.
 *
 * Cette classe interprète les mot clés d'un texte/image
 * En fonction de l'instruction, une action sur la classe Memoire sera effectuée
 *
 * @version 1.0
 */




public class Interpreter {
    
    private Memory memory = new Memory();

    /**
     * Compare le mot avec la liste des mots exécutables et agit en conséquence
     * @param keyword mot clé rentré par l'utilisateur
     * @return true si l'action a été réalisée avec succès
     */
    public boolean keywordsExecution(ArrayList<String> tableauCommande){
        boolean isOk = true;
        for(String commande : tableauCommande) {
            if (isOk) {
                switch (commande) {
                    case "+":
                        isOk = memory.incr();
                        break;
                    case "-":
                        isOk = memory.decr();
                        break;

                    case "<":
                        isOk = memory.left();
                        break;

                    case ">":
                        isOk = memory.right();
                        break;

                   /* case "." :
                        break;
                    case "," :
                        break;
                    case "[" :
                        break;
                    case "]" :
                        break;*/
                    default:
                        return false;
                }
            }
        }
        if (isOk) memory.printMemory();
        return isOk;
    }
}