package brainfuck.language.readers;


import brainfuck.language.exceptions.CheckFailedException;
import brainfuck.language.exceptions.FilePathNotFoundException;
import brainfuck.language.exceptions.MainFlagNotFoundException;
import brainfuck.language.exceptions.UnknownFlagsException;

import static brainfuck.language.enumerations.Flags.*;


/**
 * Classe permettant de lire les instructions reçues dans la console, ainsi que de les interpreter dans notre programme
 *
 * @author  Red_Panda
 */
public class KernelReader {

    public static String filepathToWrite, filepathToRead;
    private String nomFichier = null;
    private String fichierALire = null;
    private boolean writeOrRead = true; // true write, false read



    public String getNomFichier(){
        return nomFichier;
    }
    /**
     * On interprete chaque commande qui ont été dans la console
     *
     * @param args toutes les commandes reçues par la console
     * @return le nom du fichier a lire à la classe Moteur
     * @throws FilePathNotFoundException
     * @throws MainFlagNotFoundException
     */
    public String interpreterCommande(String[] args) throws FilePathNotFoundException, MainFlagNotFoundException, UnknownFlagsException {

        if (containsMainFlag(args)) {
            for (int i = 0; i < args.length; i++) {
                if (isFlag(args[i]) && !containsFilePath(args[i])) {
                    switch (toFlag(args[i])) {
                        case FileToRead:
                            try {
                                if (containsFilePath(args[i + 1])) {
                                    fichierALire = args[i + 1].replace("./", "");
                                    i++;// We increment i here to skip the file path and go to the next flag
                                }
                            }catch (ArrayIndexOutOfBoundsException e){throw new FilePathNotFoundException(args[i]);}
                            break;


                        case Rewrite:
                            //Do nothing
                            break;

                        case In: // on va lire un fichier

                            try {
                                writeOrRead = false;
                                getFilepath(args[i+1]);
                                i++;
                            }catch (ArrayIndexOutOfBoundsException e) {throw new FilePathNotFoundException(args[i]);}
                            break;

                        case Out: // on va écrire dans un fichier

                            try {
                                writeOrRead = true;
                                getFilepath(args[i+1]);
                                i++;
                            }catch (ArrayIndexOutOfBoundsException e) {throw new FilePathNotFoundException(args[i]);}
                            break;
                    }

                     if (!isFlag(args[i]) && !containsFilePath(args[i])) {
                        System.out.println(args[i] + " is not a command.");
                        System.out.println(showFlags());
                    }

                }

            }
        }
        else throw new MainFlagNotFoundException();
        return fichierALire;
    }





    /**
     * Execute la commande --check
     * Regarde qu'il y ai bien autant de crochet ouvert que fermé
     *
     * @param texteALire le texte entier du fichier
     * @return true si tout est bien refermé SINON false
     */

    public boolean commandeCheck(String texteALire) throws CheckFailedException {
        int count = 0;
        int i =0;
        while (i<texteALire.length() && count >=0){
            String c = Character.toString(texteALire.charAt(i));
            if (c.equals("[")) count++;
            else if (c.equals("]")) count--;
            i++;
        }
        if (count == 0) return true;
        else throw new CheckFailedException();

    }

    /**
     * Verifie si l'argument "-p" a bien été entré dans la liste d'arguments
     *
     * @param args La liste d'arguments qui ont été entré dans la console
     * @return True si le "-p" a bien été entré
     */
    public boolean containsMainFlag(String[] args) throws UnknownFlagsException {
        for (String arg : args){
            if (FileToRead.equals(toFlag(arg))) return true;
        }
        return false;
    }

    /**
     * Verifie si le chemin d'accès au fichier que l'on doit analysé
     * a bien été entré en argument

     * @param arg L'argument que l'on doit verifier
     * @return true si le chemin d'accès existe bien
     */
    public boolean containsFilePath(String arg){
        //if the argument contains a file with .bf or .bmp or .txt extension then there is a file path
        if (arg.toLowerCase().matches("(?i).*bf") || arg.toLowerCase().matches("(?i).*bmp") || arg.toLowerCase().matches("(?i).*txt")) return true;
        return false;
    }

    public void getFilepath(String arg){
        if (containsFilePath(arg)){
            if (writeOrRead){
                filepathToWrite = arg;
            }
            else filepathToRead = arg;
        }
    }


}
