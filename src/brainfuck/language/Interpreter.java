package brainfuck.language;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.OutOfMemoryException;
import brainfuck.language.exceptions.ValueOutOfBoundException;
import brainfuck.language.exceptions.WrongInput;
import brainfuck.language.readers.KernelReader;
import brainfuck.language.readers.LecteurFichiers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static brainfuck.language.enumerations.Keywords.isWord;


/**
 * Cette classe interprète les mots clés d'un texte/image en fonction de
 * l'instruction, une action sur la classe Mémoire sera effectuée
 *
 * @author  Red_Panda
 */

public class Interpreter {

    private Memory memory = new Memory();
    private List<Integer> placeCrochet = new ArrayList<Integer>();
    private Trace trace = null;

    /**
     * Initialise la partie "Tracé" de l'interpreteur lorsque
     * l'argument "--Trace" a été entré dans la console
     *
     * @param nomFichier Le nom du fichier dans lequel sera enregistré les logs
     */

    public void iniATracer(String nomFichier) {
        trace = new Trace(nomFichier);
    }

    /**
     * Compare le mot avec la liste des mots exécutables et agit en conséquence
     *
     * @param tableauCommande la liste de commande extrait du programme
     * @throws ValueOutOfBoundException OutOfMemoryException
     */
    public void keywordsExecution(ArrayList<Keywords> tableauCommande)
            throws OutOfMemoryException, ValueOutOfBoundException {

        Metrics.PROC_SIZE = tableauCommande.size();
        int i = 0, itot = 0;
        recenseCrochet(tableauCommande);

        while (i < tableauCommande.size()) {
                switch (tableauCommande.get(i)) {
                    case INCR:
                        memory.incr();
                        Metrics.DATA_WRITE++;
                        break;
                    case DECR:
                        Metrics.DATA_WRITE++;
                        memory.decr();
                        break;

                    case LEFT:
                        Metrics.DATA_MOVE++;
                        memory.left();
                        break;

                    case RIGHT:
                        Metrics.DATA_MOVE++;
                        memory.right();
                        break;
                    case OUT:
                        Metrics.DATA_READ++;
                        outMethod();
                        break;

                    case IN:
                        try {
                            Metrics.DATA_WRITE++;
                            inMethod();
                        } catch (WrongInput e) {
                            e.printStackTrace();
                        }
                        break;
                    case JUMP:
                        Metrics.DATA_READ++;
                        if (memory.getCellValue() == 0)
                            i += countInstru(tableauCommande, i);
                        break;
                    case BACK:
                        Metrics.DATA_READ++;
                        if (memory.getCellValue() != 0)
                            i = placeCrochet.get(retournePlace(tableauCommande, i));
                        break;

                    default:
                        break;
                }

            if (trace != null)
                trace.tracerUpdate(itot, i, memory.getPointer(), memory.toString());

            i++;
            itot++;
            Metrics.EXEC_MOVE++;
        }

        memory.printMemory();

        if (trace != null)
            trace.end();
    }

    /**
     * Permet d'enregistrer la position de tous les crochets ouvrants présent dans le fichier
     * actuelle, dans une liste.
     *
     * @param tableauCommande La liste de commande que l'on veut analyser
     */

    public void recenseCrochet(ArrayList<Keywords> tableauCommande) {
        int i;

        for (i =0; i < tableauCommande.size();i++)
        {
            if (tableauCommande.get(i).equals(Keywords.JUMP))
                placeCrochet.add(i);
        }
    }

    /**
     * Permet de connaitre rapidement la position du crochet ouvrant associé
     * au crochet fermant actuel
     *
     * @param i La position du crochet fermant que l'on interprete
     * @param tableauCommande La liste de commande que l'on est en train d'interpreter
     * @return La position dans la liste de commande du crochet ouvrantassocié au crochet fermant actuel
     */

    public int retournePlace(ArrayList<Keywords> tableauCommande, int i){
        int placeCrochetActu = 0;
        int j = 0;

        while (j < i) {
            if (tableauCommande.get(j) == Keywords.BACK)
                placeCrochetActu++;
            j++;
        }
        return (placeCrochet.size() - placeCrochetActu - 1);
    }

    /**
     * Permet de connaitre le nombre d'instruction qui sont compris entre le crochet ouvrant
     * actuel et le crochet fermant associé
     *
     * @param i La position du crochet ouvrant que l'on interprete
     * @param tableauCommande La liste de commande que l'on est en train d'interpreter
     * @return Le nombre d'instructions entre le crochet ouvrant actuel et le crochet fermant associé
     */

    public int countInstru(ArrayList<Keywords> tableauCommande, int i) {
        int nbOuvrante = 1;
        int it = i + 1;

        while (nbOuvrante != 0) {
            if (tableauCommande.get(it) == Keywords.JUMP) {
                nbOuvrante++;
            }
            if (tableauCommande.get(it) == Keywords.BACK) {
                nbOuvrante--;
            }
            it++;
        }
        return (it - i);
    }

    /**
     *
     * Gère la commande IN. On gère le case ou nous rentrons "-i" et le cas
     * par défaut (console)
     */
    public void inMethod() {
        String entree;

        if (KernelReader.filepathForReading == null) { // dans le cas oÃ¹ on n'a
            // pas fait -i
            Scanner scanner = new Scanner(System.in);
            entree = scanner.nextLine();

            if (entree.length() != 1) {
                throw new WrongInput();
            } else {
                char character = entree.charAt(0);

                try {
                    memory.updateMemory((short) character);
                } catch (ValueOutOfBoundException e) {
                    System.out.println(e.toString());
                }
            }
        } else { // dans le cas oÃ¹ on a fait i
            LecteurFichiers lecteurFichiers = new LecteurFichiers();

            try {
                short modifyMemory = Short.parseShort(lecteurFichiers.reader(KernelReader.filepathForReading));
                memory.updateMemory(modifyMemory);
            } catch (FileNotFoundException e) {
                e.toString();
            }
        }
    }

    /**
     * Gère la commande OUT. On gère le case ou nous rentrons "-o" et le cas
     * par défaut (console)
     */

    public void outMethod() {
        if (KernelReader.filepathForWriting == null) {
            char numb = (char) memory.getCellValue();
            System.out.println(numb);
        } else {
            LecteurFichiers lecteurFichiers = new LecteurFichiers();

            try {
                lecteurFichiers.write(KernelReader.filepathForWriting, Integer.toString(memory.getCellValue()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void appellerMemoire() {
        memory.printMemory();
    }
}