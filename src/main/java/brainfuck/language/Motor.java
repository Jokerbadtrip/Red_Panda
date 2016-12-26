package brainfuck.language;


import brainfuck.language.readers.KernelReader;


/**
 * Cette classe permet de communiquer avec toutes les autres classes. Elle relie le lecteur de console avec le lecteur de fichier
 * et ce dernier avec l'interpreteur.
 * C'est ici qu'on choisie le bon interpréteur et le bon lecteur pour le fichier
 *
 *@author  Red_Panda
 */
public class Motor {

    private String[] args;
    private KernelReader kernel;


    /**
     * Constructeur de Motor. On y met quoi dedans? On ne doit pas créer d'instance pour le lecteur image/textuel
     * On peut initialiser le KernelReader mais rien de plus.
     * @param args les paramètres écrits dans la console. On les renverra au KernelReader
     */
    public Motor(String[] args) {
        Metrics.START_TIME = System.currentTimeMillis();
        this.args = args;
        kernel = new KernelReader();
    }

    /**
     * Méthode qui exécute le programme étape par étape. D'abords on lit les instructions de la console puis on les exécute
     * Ensuite, on lit le fichier du programme et on récupère toutes les commandes.
     * A l'aide du lecteur textuel, on identifie chacune des commandes du programme
     * Avec l'interpréteur textue, on effectue l'action appropriée à l'instruction
     */

    public void lancerProgramme() throws Exception {

        callKernel(args);
        Metrics.EXEC_TIME(System.currentTimeMillis());
    }

    /**
     *  Appelle un objet KernelReadear afin de lire les commandes
     * @param args la liste des arguments rentrées dans la console
     */
    public void callKernel(String[] args) throws Exception {

        kernel.interpreterConsole(args);

    }

}
