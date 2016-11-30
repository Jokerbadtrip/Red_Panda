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
            System.out.println("Not implemented yet");
        }
    }

    private void tracerUpdate(int i, int itot) {
        if (aTracer) {
            try {
                fw.write("Execution step number = " + (itot + 1) + ", execution pointer position : " + (i+1)
                        + ", data pointer position : " + memory.getPointer() + ", Snapshot : " + memory.toString());
                fw.write("\r\n");
            } catch (IOException e) {
                System.out.println("Not implemented yet");
            }
        }
    }

    /**
     * Compare le mot avec la liste des mots exécutables et agit en conséquence
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
                        tracerUpdate(i, itot);
                        Metrics.DATA_WRITE++;
                        break;
                    case DECR:
                        memory.decr();
                        tracerUpdate(i, itot);
                        Metrics.DATA_WRITE++;
                        break;

                    case LEFT:
                        Metrics.DATA_MOVE++;
                        memory.left();
                        tracerUpdate(i, itot);
                        break;

                    case RIGHT:
                        Metrics.DATA_MOVE++;
                        memory.right();
                        tracerUpdate(i, itot);
                        break;
                    case OUT:
                        Metrics.DATA_READ++;
                        outMethod();
                        tracerUpdate(i, itot);
                        break;

                    case IN:
                        try {
                            Metrics.DATA_WRITE++;
                            inMethod();
                            tracerUpdate(i, itot);
                        } catch (WrongInput e) {
                            e.printStackTrace();
                        }
                        break;
                    case JUMP:
                        Metrics.DATA_READ++;
                        if (memory.getCellValue() == 0)
                            i += countInstru(tableauCommande, i);

                        tracerUpdate(i, itot);
                        break;
                    case BACK:
                        Metrics.DATA_READ++;
                        if (memory.getCellValue() != 0)
                            i = placeCrochet.get(retournePlace(tableauCommande, i));

                        tracerUpdate(i, itot);
                        break;

                    default:

                }

            i++;
            itot++;
            Metrics.EXEC_MOVE++;
        }

        memory.printMemory();
        if(fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void recenseCrochet(ArrayList<Keywords> tableauCommande) {
        int i;

        for (i =0; i < tableauCommande.size();i++)
        {
            if (tableauCommande.get(i).equals(Keywords.JUMP))
                placeCrochet.add(i);
        }
    }

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

    public int countInstru(ArrayList<Keywords> commandes, int i) {
        int nbOuvrante = 1;
        int it = i + 1;

        while (nbOuvrante != 0) {
            if (commandes.get(it) == Keywords.JUMP) {
                nbOuvrante++;
            }
            if (commandes.get(it) == Keywords.BACK) {
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