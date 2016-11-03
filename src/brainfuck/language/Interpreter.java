package brainfuck.language;


import brainfuck.language.Exceptions.OutOfMemoryException;
import brainfuck.language.Exceptions.ValueOutOfBoundException;
import brainfuck.language.Exceptions.WrongInput;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public void keywordsExecution(ArrayList<String> tableauCommande) throws OutOfMemoryException, ValueOutOfBoundException{

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
                    case "." :
                        outMethod();
                        break;

                    case "," :
                        try {
                            inMethod();
                        }
                        catch (WrongInput e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[":
                        break;
                    case "]" :
                        break;
                    default:
                }
        }

        memory.printMemory();

    }

    /**
     * Gère la commande IN
     * On gère le ca où on a rentré le -i et le cas par défaut (console)
     */
    public void inMethod() {
        String entree;

        if(KernelReader.filepathForReading == null) { // dans le cas où on n'a pas fait -i
            Scanner scanner = new Scanner(System.in);
            entree = scanner.nextLine();

            if (entree.length() != 1) throw new WrongInput();
            else {
                char character = entree.charAt(0);

                try {
                    memory.updateMemory((short) character);
                } catch (ValueOutOfBoundException e) {
                    e.printStackTrace();
                }
            }
        }
        else { // dans le cas où on a fait i
            LecteurFichiers lecteurFichiers = new LecteurFichiers();

            try {
                short modifyMemory = Short.parseShort(lecteurFichiers.reader(KernelReader.filepathForReading));
                memory.updateMemory(modifyMemory);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gère la méthode OUT
     * On gère le cas où on a la commande -o et le cas par défaut (console)
     */

    public void outMethod() {
        if(KernelReader.filepathForWriting == null) {
            char numb = (char) memory.getValueCell();
            System.out.println(numb);
        } else{
            LecteurFichiers lecteurFichiers = new LecteurFichiers();

            try {
                lecteurFichiers.write(KernelReader.filepathForWriting, Integer.toString(memory.getValueCell()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void appellerMemoire() { memory.printMemory();}
}