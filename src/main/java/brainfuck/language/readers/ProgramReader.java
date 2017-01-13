package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;
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

    private Map<Integer, Keywords> keywordsToInterpreter = new HashMap<>();
    private Map<Integer, Function> functionToInterpreter = new HashMap<>();

    private int cursor = 0;

    public ProgramReader(String program, Map<String, Function> functionMap) {
        super(program);
        this.functionMap = functionMap;
    }

    /**
     * Crée une liste de commande dans le programme à interpreter
     * @return la liste de commande
     */
    public void createBothMap() throws IsNotACommandException, FileNotFoundException {
        String[] linesOfProgram = program.split(System.getProperty("line.separator"));
        String[] cutLine;
        Function function;

        for(String line: linesOfProgram) {
            line.trim();
            cutLine = line.split(" ");
            line = cutLine[0];

            if (line.isEmpty())
                continue;

            if (Keywords.isWord(line)) {
                keywordsToInterpreter.put(cursor, Keywords.valueOf(line));
                cursor++;
            }
            else if(functionMap.containsKey(line)) {
                function = functionMap.get(line);

                if(cutLine.length == 2) {
                    function.setParametre(cutLine[1]);
                }

                functionToInterpreter.put(cursor, function);
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
                throw new IsNotACommandException(character);
        }
        return keywordsList;
    }


    public Map<Integer, Keywords> getKeywordsToInterpreter() {
        return keywordsToInterpreter;
    }

    public Map<Integer, Function> getFunctionToInterpreter() {
        return functionToInterpreter;
    }

    public void setKeywordsToInterpreter(Map<Integer, Keywords> keywordsToInterpreter) {
        this.keywordsToInterpreter = keywordsToInterpreter;
    }

    public void setFunctionToInterpreter(Map<Integer, Function> functionToInterpreter) {
        this.functionToInterpreter = functionToInterpreter;
    }
}
