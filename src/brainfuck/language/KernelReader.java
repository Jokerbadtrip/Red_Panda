package brainfuck.language;

import brainfuck.language.Exceptions.FilePathNotFoundException;


import static brainfuck.language.Flags.isFlag;
import static brainfuck.language.Flags.showFlags;
import static brainfuck.language.Flags.toFlag;


/**
 * @author  BEAL Clément on 05/10/16.
 *
 * Dans cette classe, on va lire les instructions reçues dans la console. On les lira puis on lancera l'action liée à cette commande
 *
 * Transformer interpreterCOmmande en Bool. On renvoie le bool au Motor. Si vrai, le programme continue et on récupère le
 * @version 1.0
 */
public class KernelReader {

    static String filepathForWriting, filepathForReading;

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
    public String interpreterCommande(String[] args){
        String fichierALire = null;

        for(int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-p":
                        try {
                            fichierALire = args[i + 1].replace("./", "");
                            i++;
                        } catch (FilePathNotFoundException e) {
                            System.out.println(e.toString());
                        }
                        break;

                    case "-i": // on va lire un fichier
                        try {
                            filepathForReading = args[i + 1];
                            i++;
                        } catch (FilePathNotFoundException e) {
                            e.toString();
                        }
                        break;

                    case "-o": // on va écrire dans un fichier
                        try {
                            filepathForWriting = args[i + 1];
                            i++;
                        } catch (FilePathNotFoundException e) {
                            e.toString();
                        }

                        break;
                }
            }


/*
        for(int i = 0; i < args.length; i++) {
            if (isFlag(args[i])) {
                switch (toFlag(args[i])) {
                    case FileToRead:
                        try {
                            fichierALire = args[i + 1].replace("./", "");
                            i++;
                        } catch (FilePathNotFoundException e) {
                            System.out.println(e.toString());
                        }
                        break;

                    case Rewrite:
                        try {
                            fichierALire = args[i + 1].replace("./", "");
                            i++;
                        } catch (FilePathNotFoundException e) {
                            System.out.println(e.toString());
                        }
                        break;

                    case In: // on va lire un fichier
                        try {
                            filepathForReading = args[i + 1];
                            i++;
                        } catch (FilePathNotFoundException e) {
                            e.toString();
                        }
                        break;

                    case Out: // on va écrire dans un fichier
                        try {
                            filepathForWriting = args[i + 1];
                            i++;
                        } catch (FilePathNotFoundException e) {
                            e.toString();
                        }

                        break;

                    default:
                        System.out.println(args[i] + " is not a command.");
                        showFlags();
                }
            }
        }
*/

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

}
