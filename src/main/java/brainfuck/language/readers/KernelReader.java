package brainfuck.language.readers;


import brainfuck.language.Interpreter;
import brainfuck.language.OperationTexte;
import brainfuck.language.Trace;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static brainfuck.language.enumerations.Flags.*;
import static brainfuck.language.enumerations.Keywords.BACK;
import static brainfuck.language.enumerations.Keywords.JUMP;



/**
 * Classe permettant de lire les instructions reçues dans la console, ainsi que de les interpreter dans notre programme
 *
 * @author  Red_Panda
 */
public class KernelReader {

    public static String filepathToWrite= "";
    public static String filepathToRead = "";
    private String fichierALire = "";





    /**
     * On interprete chaque commande qui ont été dans la console
     *
     * @param args toutes les commandes reçues par la console
     * @return le nom du fichier a lire à la classe Moteur
     * @throws FilePathNotFoundException
     * @throws MainFlagNotFoundException
     */
    public void interpreterConsole(String[] args) throws Exception {

        boolean containMainFlag = false; // Si "-p" est présent
        boolean containRewrite = false; // Si "--rewritee est présent
        boolean containTranslate = false;// Si "--translate" est présent
        boolean containTrace=false;// Si "--trace" est présent
        boolean containCheck=false;// Si "--check" est présent
        int numberOfFlags = 0; // Le nombre de flags présent

        for (int i = 0; i < args.length; i++) {
            if (isMainFlag(args[i])) {
                containMainFlag = true;
                try {
                    if (fileExists(args[i + 1]) && isValidExtensionOfFile(args[i+1])) {
                        fichierALire = args[i + 1];
                        i++; // on incrémente i pour qu'à la prochaine boucle on teste le prochain flag
                    }
                    else throw new FileNotFoundException();
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new FilePathNotFoundException(args[i]);
                }
            } else if (isInFlag(args[i])) {
                try {
                    if (fileExists(args[i + 1]) && isValidExtensionOfFile(args[i + 1])) {
                        getFilepath(args[i + 1], false);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new FilePathNotFoundException(args[i]);
                }
            } else if (isOutFlag(args[i])) {
                try {
                    getFilepath(args[i + 1], true);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new FilePathNotFoundException((args[i]));
                }
            } else if (isRewriteFlag(args[i])) {
                containRewrite = true;
                numberOfFlags++;
            } else if (isTranslateFlag(args[i])) {
                containTranslate = true;
                numberOfFlags++;
            } else if (isTraceFlag(args[i])) {
                containTrace=true;
                numberOfFlags++;
            }
            else if(isCheckFlag(args[i])){
                containCheck=true;
                numberOfFlags++;
            }
        }

        if (fichierALire.equals(""));//ne rien faire
        else if (containMainFlag) {

            ArrayList<Keywords> listeCommande = readFile(fichierALire); //Les commandes écrites par l'utilisateur sous forme de tableau

            switch (numberOfFlags) {

                case 0 :

                    executeProgramWithoutTrace(listeCommande);
                    break;

                case 1 :

                    if (containRewrite) {
                        commandRewrite(listeCommande);
                    }
                    else if (containTranslate) {
                        commandTranslate(listeCommande);
                    }
                    else if (containCheck) {
                        commandCheck(listeCommande);
                    }
                    else if (containTrace && !listeCommande.equals(null)) {
                        commandTrace(listeCommande);
                    }
                    break;

                case 2 :

                    if (containRewrite && containTranslate){
                        commandRewrite(listeCommande);
                        commandTranslate(listeCommande);
                    }
                    else if (containRewrite && containCheck){
                        commandCheck(listeCommande);
                        commandRewrite(listeCommande);
                    }
                    else if (containCheck && containTranslate){
                        commandCheck(listeCommande);
                        commandTranslate(listeCommande);
                    }
                    else throw new IncompatibleFlagsException();
                    break;

                case 3 :
                    if (containCheck && containRewrite && containTranslate){
                        commandTranslate(listeCommande);
                        commandCheck(listeCommande);
                        commandRewrite(listeCommande);
                    }
                    else throw new IncompatibleFlagsException();
                    break;
            }
        }
        else throw new MainFlagNotFoundException();


    }





    /**
     * Execute la commande --check
     * Regarde qu'il y ai bien autant de crochet ouvert que fermé
     *
     * @param listeCommande les commandes sous forme de shortcut dans une liste
     * @return true si tout est bien refermé SINON false
     */

    public boolean commandCheck(ArrayList<Keywords> listeCommande) throws CheckFailedException{
        int count = 0;
        int i =0;
        while (i< listeCommande.size() && count >=0){
            if (listeCommande.get(i).equals(JUMP)) count++;
            else if (listeCommande.get(i).equals(BACK)) count--;
            i++;
        }
        if (count == 0){
            System.out.println("Error Code 0");
            return true;
        }

        else throw new CheckFailedException();

    }

    /**
     * Exécute la lecture du programme sans le tracer
     * @param listeCommande la liste de commande sous forme de Keywords
     * @throws CheckFailedException
     * @throws WrongInputException
     * @throws OutOfMemoryException
     * @throws ValueOutOfBoundException
     */
    private void executeProgramWithoutTrace(ArrayList<Keywords> listeCommande) throws CheckFailedException, WrongInputException, OutOfMemoryException, ValueOutOfBoundException {
        if (commandCheck(listeCommande)) {
            Interpreter interpreter = new Interpreter();
            interpreter.keywordsExecution(listeCommande);
        } else throw new CheckFailedException();
    }

    /**
     * Exécute la lecture du programme en le traçant
     * @param listeCommande la liste de commande sous forme de Keywords
     * @param filename le nom du fichier
     * @throws WrongInputException
     * @throws OutOfMemoryException
     * @throws ValueOutOfBoundException
     * @throws CheckFailedException
     */
    private void executeProgramWithTrace(ArrayList<Keywords> listeCommande, String filename) throws WrongInputException, OutOfMemoryException, ValueOutOfBoundException, CheckFailedException {
        if (commandCheck(listeCommande)) {
            Interpreter interpreter = new Interpreter();
            interpreter.iniATracer(filename);
            interpreter.keywordsExecution(listeCommande);
        } else throw new CheckFailedException();
    }

    /**
     * Exécute la commande --rewrite
     * @param listeCommande les commandes sous forme de keywords dans une liste
     * @throws IsNotACommandException si la commande est invalide
     */
    private void commandRewrite(ArrayList<Keywords> listeCommande) throws IsNotACommandException {
        System.out.println("La traduction de votre programme en syntaxe courte est : ");
        OperationTexte.toString(listeCommande);
    }

    /**
     * Execute la commande --translate
     * @param listeCommande les commandes sous forme de keywords dans une liste
     * @throws IsNotACommandException si la commande est invalide
     */
    private void commandTranslate(ArrayList<Keywords> listeCommande) throws IsNotACommandException {
        LecteurImage lecteurImage = new LecteurImage();
        String name = fichierALire.substring(0, fichierALire.indexOf("."));
        lecteurImage.translateFromShortcutToImage(listeCommande, name);
    }

    /**
     * Exécute la commande --trace et lance le programme aussi
     * @param listeCommande la liste de commande sous forme de keywords
     * @throws CheckFailedException si il y a une boucle incomplète
     * @throws WrongInputException si l'entrée est incorrecte
     * @throws OutOfMemoryException si la mémoiré est dépassée
     * @throws ValueOutOfBoundException si les valeurs sont dépassés
     */
    private void commandTrace(ArrayList<Keywords> listeCommande) throws CheckFailedException, WrongInputException, OutOfMemoryException, ValueOutOfBoundException {
        String name = fichierALire.substring(fichierALire.indexOf("/"), fichierALire.indexOf("."));
        Trace trace = new Trace(name);
        executeProgramWithTrace(listeCommande, name);
    }

    /**
     * Verifie si l'argument "-p" a été tapé
     * @param arg l'argument à tester
     * @return true si c'est un flag valable et que c'est un -p
     * @throws UnknownFlagsException si le flag est non valide
     */
    private boolean isMainFlag(String arg) throws UnknownFlagsException {

        if (FileToRead.equals(toFlag(arg))) return true;
        return false;
    }

    /**
     * Vérifie si l'argument "-i" a été tapé
     * @param arg l'argument à tester
     * @return true si c'est un flag valable et que c'est un -i
     * @throws UnknownFlagsException si le flag est non valide
     */
    private boolean isInFlag(String arg) throws UnknownFlagsException {

        if (In.equals(toFlag(arg))) return true;
        return false;
    }

    /**
     * Vérifie si l'argument "-o" a été tapé
     * @param arg l'argument à tester
     * @return true si c'est un flag valable et que c'est un -o
     * @throws UnknownFlagsException si le flag est non valide
     */
    private boolean isOutFlag(String arg)throws UnknownFlagsException{

        if (Out.equals(toFlag(arg))) return true;
        return false;
    }

    /**
     * Vérifie si l'argument "--rewrite" a été tapé
     * @param arg l'argument a tester
     * @return true si c'est un flag valable et que c'est un --rewrite
     * @throws UnknownFlagsException si le flag est non valide
     */
    private boolean isRewriteFlag(String arg) throws UnknownFlagsException{

        if (Rewrite.equals(toFlag(arg))) return true;
        return false;
    }

    /**
     * Vérifie si l'argument "--translate" a été tapé
     * @param arg l'argument à tester
     * @return true si c'est un flag valable et que c'est un --translate
     * @throws UnknownFlagsException si le flag est non valide
     */
    private boolean isTranslateFlag(String arg) throws UnknownFlagsException{
        if (Translate.equals(toFlag(arg))) return true;
        return false;
    }

    /**
     * Vérifie si l'argument "--trace" a été tapé
     * @param arg l'argument à tester
     * @return true si c'est un flag valable et que c'est un --trace
     * @throws UnknownFlagsException si le flag est non valide
     */
    private boolean isTraceFlag(String arg) throws UnknownFlagsException{
        if (Trace.equals(toFlag(arg))) return true;
        return false;
    }

    /**
     * Vérifie si l'argument "--check" a été tapé
     * @param arg l'argument à tester
     * @return true si c'est un flag valable et que c'est un --check
     * @throws UnknownFlagsException si le flag est non valide
     */
    private boolean isCheckFlag(String arg) throws UnknownFlagsException{
        if (Check.equals(toFlag(arg)))return true;
        return false;
    }

    /**
     * Vérifie si l'extension du fichier est une extension lisible
     * @param arg L'adresse du fichier ou juste son nom
     * @return true si l'extension est valide
     */
    private boolean isValidExtensionOfFile(String arg){

        //if the argument contains a file with .bf or .bmp or .txt extension then there is a file path
        if (arg.toLowerCase().matches("(?i).*bf") || arg.toLowerCase().matches("(?i).*bmp") || arg.toLowerCase().matches("(?i).*txt")) return true;
        return false;
    }


    /**
     * Vérifie si le fichier est existant
     * @param arg l'adresse du fichier ou juste son nom
     * @return true s'il existe
     */
    private boolean fileExists(String arg) {

        File file = new File(arg);
        return file.exists();
    }

    /**
     * Définie les adresses des fichiers à lire ou a écrire
     * @param arg l'adresse du fichier ou son simple nom
     * @param writeOrRead un booléen qui définie l'état du l'argument précédent, si -i alors true, si -o alors false
     */
    private void getFilepath(String arg, boolean writeOrRead) {

        if (writeOrRead) filepathToWrite = arg;
        else filepathToRead = arg;
    }



    /**
     * lit le fichier donné en paramètre en fonction de son extension
     * @param file le fichier a lire
     * @return la liste de commande du fichier
     * @throws Exception
     */
    private ArrayList<Keywords> readFile(String file) throws Exception {

        String extension = file.substring(file.indexOf(".") + 1);
        ArrayList<Keywords> listeCommandes = new ArrayList<>();


        if (extension.equals("bf")){
            LecteurFichiers lecteurFichiers = new LecteurFichiers();
            String texteAAnalyser = lecteurFichiers.reader(file);
            if (!lecteurFichiers.isEmpty()) {
                LecteurTextuel lecteurTextuel = new LecteurTextuel();
                lecteurTextuel.setTexteAAnalyser(texteAAnalyser);
                listeCommandes = lecteurTextuel.creeTableauCommande();
            }
        }
        else if (extension.equals("bmp")){
            LecteurImage lecteurImage = new LecteurImage();
            listeCommandes = lecteurImage.read(file);
        }

        return listeCommandes;
    }







}