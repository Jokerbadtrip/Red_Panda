package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;
import brainfuck.language.exceptions.WrongMacroNameException;
import brainfuck.language.function.Function;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Permet de sortir deux catalogues des instructions/fonctions à exécuter selon un ID.
 * @author jamatofu on 31/12/16.
 */
public class ProgramReader extends LecteurTextuel{
    private Map<String, Function> functionMap;

    private Map<Integer, Keywords> keywordsToInterpreter;
    private Map<Integer, Function> functionToInterpreter;

    private int cursor = 0;

    public ProgramReader(String program, Map<String, Function> functionMap) throws WrongMacroNameException {
        super(program);
        this.functionMap = functionMap;
        this.keywordsToInterpreter = new HashMap<>();
        this.functionToInterpreter = new HashMap<>();
    }

    /**
     * Crée une liste de commande dans le programme à interpreter
     * @return la liste de commande
     */
    public void createBothMap() throws IsNotACommandException, FileNotFoundException {
        String[] linesOfProgram = program.split(System.getProperty("line.separator"));

        for(String line: linesOfProgram) {
            line.trim();
            if (line.isEmpty())
                continue;

            if (Keywords.isWord(line)) {
                keywordsToInterpreter.put(cursor, Keywords.valueOf(line));
                cursor++;
            }
            else if(functionMap.containsKey(line)) {
                functionToInterpreter.put(cursor, functionMap.get(line));
                cursor++;
            }
            else {
                keywordsToInterpreter.putAll(inTheCaseOfShortcut(line));
            }
        }
    }

    /**
     * Permet de récupérer les keywords situé sur une ligne String
     * @param line un String
     * @return une liste contenant les keywords de la ligne
     * @throws IsNotACommandException
     */
    private Map<Integer, Keywords> inTheCaseOfShortcut(String line) throws IsNotACommandException {
        Map<Integer, Keywords> keywordsList = new HashMap<>();
        for (char character : line.toCharArray()) {
            if(Keywords.isShortcut(character)) {
                keywordsList.put(cursor, Keywords.shortcutToKeyword(character));
                cursor++;
            }
            else if(Character.isWhitespace(character));
            else
                throw new IsNotACommandException();
        }
        return keywordsList;
    }


    public Map<Integer, Keywords> getKeywordsToInterpreter() {
        return keywordsToInterpreter;
    }

    public Map<Integer, Function> getFunctionToInterpreter() {
        return functionToInterpreter;
    }
}
