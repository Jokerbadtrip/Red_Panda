package brainfuck.language;


import brainfuck.language.Exceptions.OutOfMemoryException;
import brainfuck.language.Exceptions.ValueOutOfBoundException;

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
     *
     * @return true si l'action a été réalisée avec succès
     */
    public void keywordsExecution(ArrayList<String> tableauCommande) throws OutOfMemoryException,
            ValueOutOfBoundException{

        for(String commande : tableauCommande) {
                switch (commande) {
                    case "+":
                        memory.incr();
                        break;
                    case "-":
                        memory.decr();
                        break;

                    case "<":
                        memory.left();
                        break;

                    case ">":
                        memory.right();
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
                }
        }
        memory.printMemory();

    }
}