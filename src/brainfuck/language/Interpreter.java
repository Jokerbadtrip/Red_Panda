package brainfuck.language;


import brainfuck.language.Exceptions.OutOfMemoryException;
import brainfuck.language.Exceptions.ValueOutOfBoundException;
import brainfuck.language.Exceptions.WrongInput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

import static brainfuck.language.Keywords.toKeyword;

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
    private File backLog;
    private boolean aTracer = false;
    FileWriter fw;

    public void iniATracer(String nomFichier){
        aTracer = true;
        backLog = new File(nomFichier + ".log");
        try {
            fw = new FileWriter(backLog);
        } catch (IOException e){

        }
    }

    private void tracerUpdate(int i){
        if (aTracer) {
            try {
                fw.write("Execution step number = " + (i+1) + ", execution pointer position : " + "" + ", data pointer position : " + memory.getmArray() + ", Snapshot : " + memory.toString());
                fw.write("\r\n");
            }
            catch (IOException e){

            }
        }
    }

    /**
     * Compare le mot avec la liste des mots exécutables et agit en conséquence
     * @
     * @throws ValueOutOfBoundException OutOfMemoryException
     */
    public void keywordsExecution(ArrayList<String> tableauCommande) throws OutOfMemoryException, ValueOutOfBoundException{
        String commande;
        int i = 0;

<<<<<<< Updated upstream
        while (i < tableauCommande.size()){
            commande = tableauCommande.get(i);
            switch (commande) {
                case "+":
                    memory.incr();
                    tracerUpdate(i);
                    break;
                case "-":
                    memory.decr();
                    tracerUpdate(i);
                    break;

                case "<":
                    memory.left();
                    tracerUpdate(i);
                    break;

                case ">":
                    memory.right();
                    tracerUpdate(i);
                    break;
                case ".":
                    outMethod();
                    tracerUpdate(i);
                    break;

                case ",":
                    try {
                        inMethod();
                        tracerUpdate(i);
                    }
                    catch (WrongInput e) {
                        e.printStackTrace();
                    }
                    break;
                case "[":
                    int nbInstruInLoop = countInstru(tableauCommande, i);
                    if (memory.getCellValue() == 0){
                        foncWhile(tableauCommande, i, nbInstruInLoop);
                    }
                    else {
                        i+= nbInstruInLoop;
                    }
                    tracerUpdate(i);
                    break;
                case "]":
                tracerUpdate(i);
                break;
                default:

            }
            i++;

/*
=======
        for(String commande : tableauCommande) {
            i++;

>>>>>>> Stashed changes
                switch (toKeyword(commande)) {
                    case INCR:
                        memory.incr();
                        break;
                    case DECR:
                        memory.decr();
                        break;

                    case LEFT:
                        memory.left();
                        break;

                    case RIGHT:
                        memory.right();
                        break;
                    case OUT:
                        outMethod();
                        break;

                    case IN:
                        try {
                            inMethod();
                        }
                        catch (WrongInput e) {
                            e.printStackTrace();
                        }
                        break;
                    case JUMP:
                        if (memory.getCellValue() == 0){
                            foncWhile(tableauCommande, i);
                            // ATTENTION NE PAS OUBLIER DE FAIRE LA FONC POUR SAUTER TOUT !!!
                            //test
                        }
                        else

                        break;
                    case BACK:

                        break;
                    default:
                }

        }

        memory.printMemory();

    }

    private int countInstru(ArrayList<String> commandes, int i){
        int nbOuvrante = 1;
        int it = i + 1;

        while (commandes.get(it) != "]" || nbOuvrante != 1) {
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

    private void foncWhile(ArrayList<String> commandes, int i, int it){
        int maxIT = i + it;

        while (i < maxIT){
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(commandes.get(it));
            keywordsExecution(temp);
            i++;
        }

        if (memory.getCellValue() != 0){
            foncWhile(commandes,i,it);
        }
    }

    /**
     * Gère la commande IN
     * On gère le cas où on a rentré le -i et le cas par défaut (console)
     */
    public void inMethod() {
        String entree;

        if(KernelReader.filepathForReading == null) { // dans le cas où on n'a pas fait -i
            Scanner scanner = new Scanner(System.in);
            entree = scanner.nextLine();

            if (entree.length() != 1) {throw  new WrongInput();}
            else {
                char character = entree.charAt(0);

                try {
                    memory.updateMemory((short) character);
                } catch (ValueOutOfBoundException e) {
                    System.out.println(e.toString());
                }
            }
        }
        else { // dans le cas où on a fait i
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
     * Gère la méthode OUT
     * On gère le cas où on a la commande -o et le cas par défaut (console)
     */

    public void outMethod() {
        if(KernelReader.filepathForWriting == null) {
            char numb = (char) memory.getCellValue();
            System.out.println(numb);
        } else{
            LecteurFichiers lecteurFichiers = new LecteurFichiers();

            try {
                lecteurFichiers.write(KernelReader.filepathForWriting, Integer.toString(memory.getCellValue()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void appellerMemoire() { memory.printMemory();}
}