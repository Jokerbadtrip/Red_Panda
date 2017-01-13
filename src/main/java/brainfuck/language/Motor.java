package brainfuck.language;


import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.*;
import brainfuck.language.exceptions.function.BadFunctionDefinition;
import brainfuck.language.flag.Flags;
import brainfuck.language.flag.KernelReader;
import brainfuck.language.flag.commande.Check;
import brainfuck.language.flag.commande.Rewrite;
import brainfuck.language.flag.commande.Translate;
import brainfuck.language.interpreter.InterpreterMaster;
import brainfuck.language.readers.LecteurImage;
import brainfuck.language.readers.ProgramProcess;
import brainfuck.language.readers.ProgramReader;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Cette classe permet de communiquer avec toutes les autres classes. Elle relie le lecteur de console avec le lecteur de fichier
 * et ce dernier avec l'interpreteur.
 *
 *@author  Red_Panda
 */
public class Motor {

    private String[] args;
    private KernelReader kernelReader;
    private ProgramProcess programProcess;
    private ProgramReader programReader;
    private boolean needInterpreter = true;
    private String filePathToProgram;

    /**
     * Constructeur de Motor. On y met quoi dedans? On ne doit pas créer d'instance pour le lecteur image/textuel
     * On peut initialiser le KernelReader mais rien de plus.
     * @param args les paramètres écrits dans la console. On les renverra au KernelReader
     */
    public Motor(String[] args) {
        Metrics.START_TIME = System.currentTimeMillis();
        this.args = args;
    }

    /**
     * Méthode qui exécute le programme étape par étape. D'abords on lit les instructions de la console puis on les exécute
     * Ensuite, on lit le fichier du programme et on récupère toutes les commandes.
     * A l'aide du lecteur textuel, on identifie chacune des commandes du programme
     * Avec l'interpréteur textue, on effectue l'action appropriée à l'instruction
     */

    public void lancerProgramme() throws InvalidValue, FileNotFoundException, WrongMacroNameException, WrongInputException, OutOfMemoryException, WrongFunctionNameException, BadFunctionDefinition, RewriteException {
        Metrics.execTime(System.currentTimeMillis());

        filePathToProgram = callKernel(args);
        String program = getProgram(filePathToProgram);
        List<Keywords> keywordsList;


        if(filePathToProgram.endsWith(".bf")) {
            callLecteurTextuel(program);
            keywordsList = new ArrayList<>(programReader.getKeywordsToInterpreter().values());
        }
        else if (filePathToProgram.endsWith(".bmp")) {
            keywordsList = callLecteurImage(filePathToProgram);
        }
        else
            throw new InvalidValue("Your file can only have two extension : .bf .bmp");

        executeFlag(keywordsList, kernelReader.getFlagMap()); // lance l'exécution des méthdes des drapeaux

        if(needInterpreter)
            callInterpreter(filePathToProgram);


        Metrics.displayMetrics();
    }

    /**
     *  Appelle un objet KernelReadear afin de lire les commandes
     * @param args la liste des arguments rentrées dans la console
     * @return le chemin d'accès vers le programme
     */
    public String callKernel(String[] args) throws FilePathNotFoundException, MainFlagNotFoundException, IncompatibleFlagsException {
        List<String> argList =  new ArrayList<>(Arrays.asList(args));
        kernelReader = new KernelReader(argList);

        kernelReader.identifyFlag();
        kernelReader.identifyFilePathForSpecificFlag();

        kernelReader.goodFlag();
        kernelReader.verifyIfAllFlagsAreValid();

        return kernelReader.getFilePath(Flags.FILE_TO_READ);
    }


    /**
     *
     * @param program
     * @return
     * @throws FileNotFoundException
     * @throws IsNotACommandException
     * @throws WrongMacroNameException
     * @throws WrongFunctionNameException
     */
    public void callLecteurTextuel(String program) throws FileNotFoundException, IsNotACommandException, WrongMacroNameException, WrongFunctionNameException, BadFunctionDefinition {
        programProcess = new ProgramProcess(program);
        program = programProcess.transform();

        programReader = new ProgramReader(program, programProcess.getFunctionMap());
        programReader.createBothMap();
    }

    /**
     * Appelle le lecteur image
     * @param filePath chemin d'accès vers le fichier image
     * @return la liste d'instructions dans l'image
     * @throws IsNotAValidColorException
     */
    public List<Keywords> callLecteurImage(String filePath) {
        LecteurImage lecteurImage = new LecteurImage();
        return lecteurImage.read(filePath);
    }

    /**
     * Appelle l'interpréteur
     * @param filePath chemin d'accès pour le fichier trace
     * @throws WrongInputException
     * @throws OutOfMemoryException
     * @throws ValueOutOfBoundException
     */
    public void callInterpreter(String filePath) throws WrongInputException, OutOfMemoryException, ValueOutOfBoundException {
        boolean needTrace = kernelReader.getFlag(Flags.TRACE);
        InterpreterMaster interpreterMaster = new InterpreterMaster(needTrace, programReader.getKeywordsToInterpreter(),
                programReader.getFunctionToInterpreter());

        interpreterMaster.initializeInterpreters(kernelReader.getFilePath(Flags.IN),
                kernelReader.getFilePath(Flags.OUT), filePath);

        interpreterMaster.interpreterProgram();
        interpreterMaster.finishInterpreter();
    }

    /**
     * Exécute les méthodes des flags
     * @param keywordsList liste des keywords à exécuter
     * @param flagsMap le catalogue des flags
     * @throws RewriteException
     */
    public void executeFlag(List<Keywords> keywordsList, Map<Flags, Boolean> flagsMap) throws RewriteException {

        if(flagsMap.get(Flags.REWRITE) != null) {
            if(programReader.getFunctionToInterpreter().size() > 0)
                throw new RewriteException();

            Rewrite rewrite = new Rewrite(keywordsList);
            System.out.println("La traduction de votre programme en syntaxe courte est : " + rewrite.rewriteProgram() + "\n");
            needInterpreter = false;
        }

        if(flagsMap.get(Flags.CHECK) != null) {
            Check check = new Check();
            if(check.isWellChecked(keywordsList))
                System.out.println("Program is well balanced.\n");
            else {
                System.out.println("Program is not well-balanced.\n");
            }
            needInterpreter = false;
        }

        if(flagsMap.get(Flags.TRANSLATE) != null) {
            Translate translate = new Translate(filePathToProgram);
            try {
                translate.translateFromShortcutToImage(keywordsList);
                System.out.println("Translation finished.\n");
            } catch (IOException ex) {
                System.out.println("Error during saving of the image.\n");
            }
            needInterpreter = false;
        }
    }

    /**
     * Permet de récupérer le fichier texte du programme
     */
    private String getProgram(String filePath) {
        StringBuilder program = new StringBuilder();
        File file = new File(filePath);
        int charactere;

        try(FileInputStream fis = new FileInputStream(file)) {
            while((charactere = fis.read()) != -1) {
                program.append((char) charactere);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return program.toString();
    }

}
