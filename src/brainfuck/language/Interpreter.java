package language;

import language.Exceptions.OutOfMemoryException;
import language.Exceptions.ValueOutOfBoundException;
import language.Exceptions.WrongInput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

import static language.Keywords.toKeyword;

/**
 * @author BEAL ClÃ©ment, SERRANO Simon on 28/09/16.
 *
 *         Cette classe interprÃ¨te les mot clÃ©s d'un texte/image En fonction
 *         de l'instruction, une action sur la classe Memoire sera effectuÃ©e
 *
 * @version 1.0
 */

public class Interpreter {

    private Memory memory = new Memory();
    private File backLog;
    private boolean aTracer = false;
    private List<Integer> placeCrochet = new ArrayList<Integer>();
    FileWriter fw;


    public void iniATracer(String nomFichier) {
        aTracer = true;
        backLog = new File(nomFichier + ".log");
        try {
            fw = new FileWriter(backLog);
        } catch (IOException e) {

        }
    }

    private void tracerUpdate(int i) {
        if (aTracer) {
            try {
                fw.write("Execution step number = " + (i + 1) + ", execution pointer position : " + ""
                        + ", data pointer position : " + memory.getmArray() + ", Snapshot : " + memory.toString());
                fw.write("\r\n");
            } catch (IOException e) {

            }
        }
    }

    /**
     * Compare le mot avec la liste des mots exécutables et agit en conséquence
     * @param tableauCommande la liste de commande extrait du programme
     * @throws ValueOutOfBoundException OutOfMemoryException
     */
    public void keywordsExecution(ArrayList<String> tableauCommande)
            throws OutOfMemoryException, ValueOutOfBoundException {
        String commande;
        int i = 0;
        recenseCrochet(tableauCommande);

        while (i < tableauCommande.size()) {
            commande = tableauCommande.get(i);
            switch (toKeyword(commande)) {
                case INCR:
                    memory.incr();
                    tracerUpdate(i);
                    Metrics.DATA_WRITE++;
                    break;
                case DECR:
                    memory.decr();
                    tracerUpdate(i);
                    Metrics.DATA_WRITE++;
                    break;

                case LEFT:
                    memory.left();
                    tracerUpdate(i);
                    break;

                case RIGHT:
                    memory.right();
                    tracerUpdate(i);
                    break;
                case ".":
                    outMethod();
                    tracerUpdate(i);
                    break;

                case IN:
                    try {
                        inMethod();
                        tracerUpdate(i);
                    } catch (WrongInput e) {
                        e.printStackTrace();
                    }
                    break;
                case JUMP:
                    Metrics.DATA_READ++;
                    if (memory.getCellValue() == 0)
                        i+=countInstru(tableauCommande, i);

                    tracerUpdate(i);
                    break;
                case BACK:
                    Metrics.DATA_READ++;
                    tracerUpdate(i);
                    break;

                default:

            }
            i++;
        }
        memory.printMemory();
    }

    public void recenseCrochet(ArrayList<String> tableauCommande) {
        int i = 0;
        int countCrochet = 0;

        while (i < tableauCommande.size()) {
            if (tableauCommande.get(i) == "[")
            {
                placeCrochet.add(i);
                countCrochet++;
            }
            i++;
        }
    }

    public int retournePlace(ArrayList<String> tableauCommande, int i){
        int placeCrochetActu = 0;
        int j = 0;

        while (j < i) {
            if (tableauCommande.get(j) == "]")
                placeCrochetActu++;

            j++;
        }
        return (placeCrochet.size() - placeCrochetActu - 1);
    }

    public int countInstru(ArrayList<String> commandes, int i) {
        int nbOuvrante = 1;
        int it = i + 1;

        while (nbOuvrante != 0) {
            if (commandes.get(it) == "[") {
                nbOuvrante++;
            }
            if (commandes.get(it) == "]") {
                nbOuvrante--;
            }

            it++;
        }
        return (it - i);
    }

    /**
     * GÃ¨re la commande IN On gÃ¨re le cas oÃ¹ on a rentrÃ© le -i et le cas par
     * dÃ©faut (console)
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
     * GÃ¨re la mÃ©thode OUT On gÃ¨re le cas oÃ¹ on a la commande -o et le cas
     * par dÃ©faut (console)
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