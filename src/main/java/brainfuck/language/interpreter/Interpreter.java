package brainfuck.language.interpreter;

import brainfuck.language.Memory;
import brainfuck.language.enumerations.Keywords;
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

public abstract class Interpreter {
    protected Memory memory = new Memory(false);
    protected List<Integer> placeCrochet = new ArrayList<>();
    protected int cursor = 0;

    protected String infilepath;
    protected String outfilepath;


    public Interpreter() {
        this(null, null);
    }

    /**
     * Constructeur d'un interpréteur
     * @param infilepath chemin d'accès pour la commande -i
     * @param outfilepath chemin d'accès pour la commande -o
     */
    public Interpreter(String infilepath, String outfilepath) {
        this.infilepath = infilepath;
        this.outfilepath = outfilepath;
    }
    
    /**
     * Permet d'enregistrer la position de tous les crochets ouvrants présent dans le fichier
     * actuelle, dans une liste.
     *
     * @param tableauCommande La liste de commande que l'on veut analyser
     */

    protected void recenseCrochet(List<Keywords> tableauCommande) {
        for (int i =0; i < tableauCommande.size();i++)
        {
            if (tableauCommande.get(i).equals(Keywords.JUMP))
                placeCrochet.add(i);
        }
    }

    /**
     *
     * Gère la commande IN. On gère le case ou nous rentrons "-i" et le cas
     * par défaut (console)
     */
    public void inMethod(String arg) throws WrongInputException, ValueOutOfBoundException {
        String entree;

        if (arg == null) { // dans le cas où on n'a pas fait -i
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
    }

    /**
     * Gère la commande OUT. On gère le case ou nous rentrons "-o" et le cas
     * par défaut (console)
     */

    protected void outMethod(String arg) {
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

    protected void incrMethod() {
        memory.incr();
    }

    protected void decrMethod() {
        memory.decr();
    }

    protected void leftMethod() {
        memory.left();
    }

    protected void rightMethod() {
        memory.right();
    }
}