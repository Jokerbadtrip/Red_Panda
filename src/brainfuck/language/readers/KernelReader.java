package brainfuck.language.readers;


import brainfuck.language.exceptions.FilePathNotFoundException;
import brainfuck.language.exceptions.MainFlagNotFoundException;

import static brainfuck.language.enumerations.Flags.*;


/**
 * Classe permettant de lire les instructions reçues dans la console, ainsi que de les interpreter dans notre programme
 *
 * @author  Red_Panda
 */
public class KernelReader {

    public static String filepathForWriting, filepathForReading;
    private String nomFichier = null;
    private String fichierALire = null;



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
    public String interpreterCommande(String[] args) throws FilePathNotFoundException, MainFlagNotFoundException {

        if (containsMainFlag(args)) {
            for (int i = 0; i < args.length; i++) {
                if (isFlag(args[i])) {
                    switch (toFlag(args[i])) {
                        case FileToRead:
                            try {
                                if (containsFilePath(args[i + 1])) {
                                    fichierALire = args[i + 1].replace("./", "");
                                    i++;// We increment i here to skip the file path and go to the next flag
                                } else throw new FilePathNotFoundException();
                            }catch (ArrayIndexOutOfBoundsException e){throw new FilePathNotFoundException();}
                            break;


                        case Rewrite:
                            //Do nothing
                            break;

                        case In: // on va lire un fichier

                            if (containsFilePath(args[i+1])) {
                                filepathForReading = args[i+1];
                                i++;
                            }
                            else throw new FilePathNotFoundException();

                            break;

                        case Out: // on va écrire dans un fichier

                            if (containsFilePath(args[i+1])){
                                filepathForWriting = args[i+1];
                                i++;
                            }
                            else throw new FilePathNotFoundException();

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

    public boolean commandeCheck(String texteALire) {
        int nbrJump, nbrBack;

        nbrJump = texteALire.length() - texteALire.replace("[", "").length();
        nbrBack = texteALire.length() - texteALire.replace("]", "").length();
        return nbrBack == nbrJump;
    }

    /**
     * Verifie si l'argument "-p" a bien été entré dans la liste d'arguments
     *
     * @param args La liste d'arguments qui ont été entré dans la console
     * @return True si le "-p" a bien été entré
     */
    public boolean containsMainFlag(String[] args){
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
        //if the argument contains a file with .bf or .bmp extension then there is a file path
        if (arg.indexOf(".bf") != -1 || arg.indexOf(".bmp") != -1) return true;
        return false;
    }


}
