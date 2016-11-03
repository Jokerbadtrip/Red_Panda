package brainfuck.language;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author BEAL Clément on 05/10/2016.
 *
 * Cette classe permet de communiquer avec toutes les autres classes. Elle relie le lecteur de console avec le lecteur de fichier et ce dernier avec l'interpreteur
 * C'est ici qu'on choisie le bon interpréteur et le bon lecteur pour le fichier
 *
 * /!\ /!\ /!\ /!\ /!\ /!\ /!\ v
 * /!\
 * /!\ Il faut faire une classe Lecteur et en faire hériter LecteurImage Et textuel. Comme ça, on peut crée un objet Lecteur et définir plus tard son type
 * /!\
 * /!\ /!\ /!\ /!\ /!\ /!\
 *
 * @version 1.0
 */
public class Motor {

    private String[] args;
    private KernelReader kernel;
    private LecteurTextuel lecteur;
    private Interpreter interpreter;
    private String texteALire;


    /**
     * Constructeur de Motor. On y met quoi dedans? On ne doit pas créer d'instance pour le lecteur image/textuel
     * On peut initialiser le KernelReader mais rien de plus.
     * @param args les paramètres écrits dans la console. On les renverra au KernelReader
     */
    public Motor(String[] args) {
        this.args = args;
        kernel = new KernelReader();
    }

    /**
     * Méthode qui exécute le programme étape par étape. D'abords on lit les instructions de la console puis on les exécute
     * Ensuite, on lit le fichier du programme et on récupère toutes les commandes.
     * A l'aide du lecteur textuel, on identifie chacune des commandes du programme
     * Avec l'interpréteur textue, on effectue l'action appropriée à l'instruction
     */

    public void lancerProgramme() {
        boolean aReWrite = false;
        boolean aCheck = false;

        for(String arg: args) {
            if(arg == "--rewrite") aReWrite = true;
            if(arg == "--check") aCheck = true;
        }

        if(callKernel(args)) {
            ArrayList<String> listeDeCommande = callLecteurTextuel(this.texteALire);
            //lecteur.transformerInstructionEnSymbole(listeDeCommande);

            if(aReWrite) {
                System.out.println("Rewrite");
                lecteur.toString(lecteur.transformerInstructionEnSymbole(listeDeCommande));
            }
            else if(aCheck) {
                System.out.println("Check");
                if(kernel.commandeCheck(texteALire)) System.out.println("Tout est ok");
                else System.out.println("Rien n'est ok");
            }
            else {
                System.out.println("Fin du programme");
            }
        }
    }

    /**
     *  Votre fonction doit retourner quoi? Un boolean ok, mais pourquoi? Si c'est faux, on arrete le programme?
     * @param args la liste des arguments rentrées dans la console
     * @return ???
     */
    public boolean callKernel(String[] args) {

        String fichierALire = kernel.interpreterCommande(args);




        if (fichierALire!=null) { //Si le fichier est existant alors ...

            String extensionFichier = this.extensionFichier(fichierALire);

            //Dans le cas où on gère un texte, on va récupérer toutes les données du texte

            if (extensionFichier.equals("bf")) {
                try {
                    LecteurFichiers reader = new LecteurFichiers();
                    texteALire = reader.decodeur(fichierALire);
                } catch (FileNotFoundException e) {
                    System.out.println(e.toString());
                    return false;
                }

                return (kernel.interpreterCommande(args) != null);
            }
        }





        return false;
    }


    /**
     *  Appelle l'interpréteur de texte. L'interpréteur de texte gérera ensuite l'éxution des instructions
     *
     * @param commandeAExecuter une liste de toutes les instructions contenues dans le fichier programme
     * @return true si tout à bien été exécuté SINON false si une instruction a posée problème
     */
    public void callInterpreter(ArrayList<String> commandeAExecuter) {
        interpreter = new Interpreter();
        interpreter.keywordsExecution(commandeAExecuter);
    }

    /**
     *  Appelle le lecteur textuel. Il gérera la lecture et la différencation entre toutes les instructions
     * @param texteALire
     * @return commande une liste contenye toutes les instructions
     */

    public ArrayList<String> callLecteurTextuel(String texteALire){
        lecteur = new LecteurTextuel();
        lecteur.setTexteALire(texteALire);
        //lecteur.setTexteALire(texteALire);
        ArrayList<String> instruction = lecteur.creeTableauCommande();

        return instruction;
    }

    /**
     * Récupère l'extenion du fichier qui va être lu. Permet de savoir quel lecteur et interpréteur on va utiliser
     * @param fichier le nom du fichier ex => "programme.bf" ou "programme.bmp"
     * @return l'extension en string. Uniquement ce qu'il y a après le point
     */

    public String extensionFichier(String fichier) {

        return (fichier.substring(fichier.indexOf(".") + 1));
    }


}

