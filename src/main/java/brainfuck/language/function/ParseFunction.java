package brainfuck.language.function;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongFunctionNameException;
import brainfuck.language.exceptions.WrongMacroNameException;
import brainfuck.language.exceptions.function.BadFunctionDefinition;
import brainfuck.language.readers.LecteurTextuel;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Permet de chercher les prototypes des fonctions/procédure dans le programme
 * Les stocke dans un catalogue
 * Renvoie un programme sans ces prototypes
 * Un prototype peut se trouver n'importe où dans le programme
 * @author jamatofu on 30/12/16.
 */
public class ParseFunction  {
    private String program;
    private Map<String, Function> functionMap;

    public ParseFunction(String program) {
        this.program = program.trim();
        this.functionMap = new HashMap<>();
    }

    /**
     * Permet de lire les prototypes des fonctions dans un programme
     * @return le programme sans les prototypes
     * @throws WrongMacroNameException
     * @throws FileNotFoundException
     * @throws WrongFunctionNameException
     */
    public String findPrototype() throws WrongMacroNameException, FileNotFoundException, WrongFunctionNameException, BadFunctionDefinition {
        String[] lineOfProgram = program.split(System.lineSeparator());
        StringBuilder stringBuilder = new StringBuilder(); // va récolter le programme sans prototype

        for(String line : lineOfProgram) { // explore le programme ligne par ligne
            line = line.trim();
            if(line.isEmpty()) // si c'est une ligne vide, on passe à la suivante
                continue;

            if(line.startsWith(PrimitiveFunction.VOID.name().toLowerCase())) { // dans le cas d'une procédure
                addProcedure(line, true);
            }
            else if(line.startsWith(PrimitiveFunction.FUNCTION.name().toLowerCase())) { // dans le cas d'une fonction
                addProcedure(line, false);
            }
            else { // la ligne est composé de keyword/instruction
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Permet de rajouter une fonction dans le catalogue de fonction
     * @param line ligne contenant la function
     * @param isProcedure permet de dire si c'est une procédure ou une fonction
     * @throws FileNotFoundException
     * @throws WrongFunctionNameException
     * @throws WrongMacroNameException
     */
    public void addProcedure(String line, boolean isProcedure) throws FileNotFoundException, WrongFunctionNameException, BadFunctionDefinition {
        String[] piecesOfLine = line.split(" ");

        if(piecesOfLine.length != 3) // ne peut pas contenir plus ou moins de 3 morceaux
            throw new BadFunctionDefinition(line);

        if(!isValidName(piecesOfLine[1]))
            throw new WrongFunctionNameException(piecesOfLine[1]);

        LecteurTextuel lecteurTextuel = new LecteurTextuel(piecesOfLine[2]);

        Function function = new Function(lecteurTextuel.creeTableauCommande(), isProcedure, piecesOfLine[1]);
        functionMap.put(piecesOfLine[1], function);
    }

    /**
     * Vérifie que le nom de la fonction est valide. Pas un mot déjà existant(nom de keyword, shortcut) ni un mot composé exclusivment de chiffre
     * @param name nom de la fonction
     * @return vrai si valide
     */
    public boolean isValidName(String name) {
        for(Keywords keywords : Keywords.values()) {
            if(keywords.name().equals(name) || keywords.getShortcut() == name.charAt(0))
                return false;
        }

        return !name.matches("(\\d+)");
    }

    /**
     * Accesseur de functionMap
     * @return functionMap
     */
    public Map<String, Function> getFunctionMap() {
        return functionMap;
    }
}
