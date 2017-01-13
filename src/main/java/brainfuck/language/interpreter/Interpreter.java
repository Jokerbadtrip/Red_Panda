package brainfuck.language.interpreter;

import brainfuck.language.Memory;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.ValueOutOfBoundException;
import brainfuck.language.exceptions.WrongInputException;

import java.io.*;
import java.util.*;


/**
 * Cette classe interprète les mots clés d'un texte/image en fonction de
 * l'instruction, une action sur la classe Mémoire sera effectuée
 *
 * @author  Red_Panda
 */

public abstract class Interpreter {
    protected Memory memory;
    protected Map<Integer, Integer> linkedBracket;

    protected int cursor = 0;

    protected String infilepath;
    protected String outfilepath;

    /**
     * Constructeur d'un interpréteur
     * @param infilepath chemin d'accès pour la commande -i
     * @param outfilepath chemin d'accès pour la commande -o
     * @param memory définit le type de mémoire
     */
    public Interpreter(String infilepath, String outfilepath, boolean memory) {
        this.infilepath = infilepath;
        this.outfilepath = outfilepath;
        this.memory = new Memory(memory);
        this.linkedBracket = new HashMap<>();
    }

    /**
     * Méthode jump
     */
    protected void jump() {
        if(memory.getCellValue() == 0)
            cursor = linkedBracket.get(cursor);
    }

    /**
     * Méthode back
     */
    protected void back() {
        if(memory.getCellValue() != 0) {
            cursor = linkedBracket.get(cursor);
        }
    }

    /**
     * Permet d'associer un crochet ouvrant à un crochet fermée et vise versa
     */
    protected void setLinkedBracket(List<Keywords> keywordsList) {
        List<Integer> posCrochetOuvrant = new ArrayList<>();
        int i = 0;

        for(int j = 0; j < keywordsList.size(); j++) {
            if(Keywords.JUMP.equals(keywordsList.get(j))) {
                posCrochetOuvrant.add(j);
                i++;
            }
            else if(Keywords.BACK.equals(keywordsList.get(j))) {
                linkedBracket.put(posCrochetOuvrant.get(i - 1), j);
                linkedBracket.put(j, posCrochetOuvrant.get(i - 1));
                i--;
            }
        }
    }

    /**
     * Gère la commande IN. On gère le case ou nous rentrons "-i" et le cas
     * par défaut (console)
     */
    public void inMethod() throws WrongInputException, ValueOutOfBoundException {
        String entree;

        if (infilepath == null) { // dans le cas où on n'a pas fait -i
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
            String charactere;
            try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
                charactere = bf.readLine();
                if(charactere.length() != 1)
                    throw new WrongInputException();

                memory.updateMemory((short) charactere.charAt(0));

            } catch (IOException ex) {
                System.out.println("Filepath of In file is wrong");
            }
        }
    }

    /**
     * Gère la commande OUT. On gère le case ou nous rentrons "-o" et le cas par défaut (console)
     */
    protected void outMethod() {
        if (outfilepath == null) {
            char numb = (char) memory.getCellValue();
            System.out.print(numb);
        } else {
            File file = new File(outfilepath);
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

    public void setInfilepath(String infilepath) {
        this.infilepath = infilepath;
    }

    public void setOutfilepath(String outfilepath) {
        this.outfilepath = outfilepath;
    }

    /**
     * Ecris le contenu de la mémoire principale
     */
    public String writeStateOfMemory() {
        return memory.writeStateOfMemory();
    }

    public int getCurrentPointer() { return memory.getPointer();}

    public short getCurrentValue() { return memory.getCellValue(); }

    public void setCurrentValue(short value) {
        memory.updateMemory(value);
    }

    public void setLinkedBracket(Map<Integer, Integer> linkedBracket) {
        this.linkedBracket = linkedBracket;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }
}