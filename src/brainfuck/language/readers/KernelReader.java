package brainfuck.language.readers;



import brainfuck.language.exceptions.FilePathNotFoundException;
import brainfuck.language.exceptions.MainFlagNotFoundException;

import static brainfuck.language.enumerations.Flags.*;


/**
 * @author  BEAL Clément on 05/10/16.
 *
 * Dans cette classe, on va lire les instructions reçues dans la console. On les lira puis on lancera l'action liée à cette commande
 *
 * Transformer interpreterCOmmande en Bool. On renvoie le bool au Motor. Si vrai, le programme continue et on récupère le
 * @version 1.0
 */
public class KernelReader {

    public static String filepathForWriting, filepathForReading;
    private String nomFichier = null;
    private String fichierALire = null;



    public String getNomFichier(){
        return nomFichier;
    }
    /**
     *
     * On lit chaque commande reçues par la console et on regarde si elle est contenue dans la variable commande
     * Si elle ne l'est pas, on le dit
     * Va exécuter chacun des paramètres rentrées dans la console sauf la lecture du fichier qui est directement traité
     * On parcourt le tableau de boolean qui indique quelle commande a été entrée
     *  /!\ /!\ /!\ /!\ /!\ /!\ /!\
     * /!\ /!\
     * /!\ /!\ Il faudrait peut être gérer l'exception au cas où une commmande n'existe pas. Ca ne bloquera pas le programme de rentrer une commande fausse mais il faut y faire attention
     * /!\ /!\
     * /!\ /!\ /!\ /!\ /!\ /!\
     * @param args toutes les commandes reçues par la console
     * @return le nom du fichier a lire à la classe Moteur
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
                        showFlags();
                    }

                }

            }
        }
        else throw new MainFlagNotFoundException();
        return fichierALire;

    }





    /**
     * Execute la commande --check
     *  Regarde qu'il y ai bien autant de crochet ouvert que fermé
     *
     *  /!\/!\/!\/!\/!\
     *  /!\/!\/!\/!\/!\
     *  Peut être amélioré avec d'autre méthode. Celle là est simple mais est la plus lente
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
     * Checks whether the -p has been given in the arguments
     * @param args arguments given when calling the program
     * @return true if the -p is present
     */
    public boolean containsMainFlag(String[] args){
        for (String arg : args){
            if (FileToRead.equals(toFlag(arg))) return true;
        }
        return false;
    }

    /**
     * Checks whether or not a file path is contained in the argument
     * @param arg the entry argument
     * @return true is there is a file path
     */
    public boolean containsFilePath(String arg){
        //if the argument contains a file with .bf or .bmp extension then there is a file path
        if (arg.indexOf(".bf") != -1 || arg.indexOf(".bmp") != -1) return true;
        return false;
    }


}
