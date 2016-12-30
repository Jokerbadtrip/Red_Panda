package brainfuck.language.interpreter;

import brainfuck.language.Memory;
import brainfuck.language.Metrics;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.OutOfMemoryException;
import brainfuck.language.exceptions.ValueOutOfBoundException;
import brainfuck.language.exceptions.WrongInputException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Cette classe interprète les mots clés d'un texte/image en fonction de
 * l'instruction, une action sur la classe Mémoire sera effectuée
 *
 * @author  Red_Panda
 */

public class Interpreter {

    private Memory memory = new Memory();
    private List<Integer> placeCrochet = new ArrayList<>();
    private List<Keywords> tableauCommande = new ArrayList<>();
    private Trace trace = null;
    private int cursor = 0;

    private String infilepath;
    private String outfilepath;

    /**
     *
     * @param infilepath
     * @param outfilepath
     */
    public Interpreter(String infilepath, String outfilepath, List<Keywords> tableauCommande) {
        this.infilepath = infilepath;
        this.outfilepath = outfilepath;
        this.tableauCommande = tableauCommande;
    }

    /**
     * Initialise la partie "Tracé" de l'interpreteur lorsque
     * l'argument "--TRACE" a été entré dans la console
     *
     * @param nomFichier Le nom du fichier dans lequel sera enregistré les logs
     */

    public void iniATracer(String nomFichier) {
        trace = new Trace(nomFichier);
    }

    /**
     * Compare le mot avec la liste des mots exécutables et agit en conséquence
     *
     * @throws ValueOutOfBoundException OutOfMemoryException
     */
    public void keywordsExecution() throws OutOfMemoryException, ValueOutOfBoundException, WrongInputException {
        Metrics.PROC_SIZE = tableauCommande.size();
        recenseCrochet(tableauCommande);

        while (cursor < tableauCommande.size()) {
                switch (tableauCommande.get(cursor)) {
                    case INCR:
                        incrMethod();
                        break;
                    case DECR:
                        decrMethod();
                        break;
                    case LEFT:
                        leftMethod();
                        break;
                    case RIGHT:
                        rightMethod();
                        break;
                    case OUT:
                        outMethod(outfilepath);
                        break;
                    case IN:
                        inMethod(infilepath);
                        break;
                    case JUMP:
                        jumpMethod();
                        break;
                    case BACK:
                        backMethod();
                        break;

                    default:
                        break;
                }

            if (trace != null) {
                trace.tracerUpdate(cursor, memory.getPointer(), memory.writeStateOfMemory());
            }
            Metrics.EXEC_MOVE++;

            cursor++;
        }

        System.out.println(memory.writeStateOfMemory());

        if (trace != null)
            trace.end();
    }

    /**
     * Permet d'enregistrer la position de tous les crochets ouvrants présent dans le fichier
     * actuelle, dans une liste.
     *
     * @param tableauCommande La liste de commande que l'on veut analyser
     */

    public void recenseCrochet(List<Keywords> tableauCommande) {
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

    public int retournePlace(List<Keywords> tableauCommande, int i){
        int placeCrochetActu = 0;
        int j = 0;

        while (j < i) {
            if (tableauCommande.get(j) == Keywords.BACK)
                placeCrochetActu++;
            j++;
        }
        return placeCrochet.size() - placeCrochetActu - 1;
    }

    /**
     * Permet de connaitre le nombre d'instruction qui sont compris entre le crochet ouvrant
     * actuel et le crochet fermant associé
     *
     * @param i La position du crochet ouvrant que l'on interprete
     * @param tableauCommande La liste de commande que l'on est en train d'interpreter
     * @return Le nombre d'instructions entre le crochet ouvrant actuel et le crochet fermant associé
     */

    public int countInstru(List<Keywords> tableauCommande, int i) {
        int nbOuvrante = 1;
        int it = i + 1;

        while (nbOuvrante != 0) {
            if (tableauCommande.get(it) == Keywords.JUMP) {
                nbOuvrante++;
            }
            if (tableauCommande.get(it) == Keywords.BACK) {
                        Metrics.DATA_WRITE++;
                nbOuvrante--;
            }
            it++;
        }
        return it - i;
    }

    /**
     *
     * Gère la commande IN. On gère le case ou nous rentrons "-i" et le cas
     * par défaut (console)
     */
    public void inMethod(String arg) throws WrongInputException, ValueOutOfBoundException {
        String entree;

        if ("".equals(arg)) { // dans le cas où on n'a pas fait -i
            Scanner scanner = new Scanner(System.in);
            entree = scanner.nextLine();

            if (entree.length() != 1) {
                throw new WrongInputException();
            } else {
                char character = entree.charAt(0);

                memory.updateMemory((short) character);
            }
        } else { // dans le cas où on a fait -i
            File file = new File(infilepath);
            int charactere;

            try(FileInputStream fis = new FileInputStream(file)) {
                while((charactere = fis.read()) != -1) {
                    memory.updateMemory((short) charactere);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        Metrics.DATA_WRITE++;
    }

    /**
     * Gère la commande OUT. On gère le case ou nous rentrons "-o" et le cas
     * par défaut (console)
     */

    public void outMethod(String arg) {
        Metrics.DATA_READ++;
        if (arg == null) {
            char numb = (char) memory.getCellValue();
            System.out.print(numb);
        } else {
            File file = new File(arg);
            try(FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(String.valueOf((char) memory.getCellValue()));
                fileWriter.close();
            } catch (IOException e) { }
        }
    }

    private void incrMethod() {
        memory.incr();
        Metrics.DATA_WRITE++;
    }

    private void decrMethod() {
        memory.decr();
        Metrics.DATA_WRITE++;
    }

    private void leftMethod() {
        Metrics.DATA_MOVE++;
        memory.left();
    }

    private void rightMethod() {
        Metrics.DATA_MOVE++;
        memory.right();
    }

    private void jumpMethod() {
        if (memory.getCellValue() == 0)
            cursor += countInstru(tableauCommande, cursor);

        Metrics.DATA_READ++;
    }

    private void backMethod() {
        if (memory.getCellValue() != 0)
            cursor = placeCrochet.get(retournePlace(tableauCommande, cursor));

        Metrics.DATA_READ++;
    }
}