package brainfuck.language.interpreter;

import brainfuck.language.Metrics;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongInputException;
import brainfuck.language.function.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Permet de gérer à la fois l'exécution des instructions et celles des fonctions
 * @author jamatofu on 31/12/16.
 */
public class InterpreterMaster {
    private FunctionInterpreter functionInterpreter;
    private KeywordInterpreter keywordInterpreter;
    private boolean needTrace;
    private int cursor = 0;
    protected List<Integer> placeCrochet = new ArrayList<>();

    private Map<Integer, Keywords> keywordsMap;
    private Map<Integer, Function> functionMap;
    private Map<Integer, Integer> linkedBracket;

    /**
     * Constructeur
     * @param needTrace paramètre pour activer la trace
     * @param keywordsMap le dictionnaire des keywords avec leur numéro d'exécution
     * @param functionMap le dictionnaire des fonctions/procédure avec leur numéro d'exécution
     */
    public InterpreterMaster(boolean needTrace, Map<Integer, Keywords> keywordsMap, Map<Integer, Function> functionMap) {
        this.needTrace = needTrace;
        this.keywordsMap = keywordsMap;
        this.functionMap = functionMap;
        this.linkedBracket = new HashMap<>();
    }

    /**
     * Initialise les deux interpréteurs
     * @param infilepath chemin d'accès -i
     * @param outfilepath chemin d'accès -o
     * @param tracePath chemin d'accès pour la trace
     */
    public void initializeInterpreters(String infilepath, String outfilepath, String tracePath) {
        this.keywordInterpreter = new KeywordInterpreter(infilepath, outfilepath);
        if(this.needTrace)
            keywordInterpreter.iniATracer(tracePath);

        this.functionInterpreter = new FunctionInterpreter(infilepath, outfilepath);
    }

    /**
     * Interpréteur général. "Exécute" le programme brainfuck
     */
    public void interpreterProgram() throws WrongInputException {
        List<Keywords> keywordsList = new ArrayList<>(keywordsMap.values());
        this.setLinkedBracket();

        while (cursorIsInInterval()) {
            if(keywordsMap.containsKey(cursor)) { // interprète un keyword
                Keywords keywords = keywordsMap.get(cursor);
                switch (keywords) {
                    case JUMP:
                        jump();
                        break;
                    case BACK:
                        back();
                        break;
                }

                keywordInterpreter.identifyAndExecuteInstruction(keywords);
            }
            else if (functionMap.get(cursor).isProcedure()) { // interprète une procédure
                for(Keywords keywords : functionMap.get(cursor).getCode()) {
                    keywordInterpreter.identifyAndExecuteInstruction(keywords);
                }
            }
            else { // interprète une fonction
                functionInterpreter.identifyAndExecuteInstruction(functionMap.get(cursor));
                keywordInterpreter.setCurrentValue(functionInterpreter.getResult());
            }

            cursor++;
        }

        Metrics.PROC_SIZE = cursor;
    }

    /**
     * Permet de faire les taches post-interprétation
     */
    public void finishInterpreter() {
        keywordInterpreter.endTrace();
        System.out.println("\n/*----------------------------*/");
        System.out.println(keywordInterpreter.writeStateOfMemory());
        System.out.println("\n/*----------------------------*/\n");
    }

    /**
     * Regarde si le curseur existe dans un des deux catalogues
     * @return vrai si dans intervalle
     */
    public boolean cursorIsInInterval() {
        return keywordsMap.containsKey(cursor) || functionMap.containsKey(cursor);
    }

    /**
     * Méthode jump
     */
    private void jump() {
        if(keywordInterpreter.getCurrentValue() == 0)
            cursor = linkedBracket.get(cursor);
    }

    /**
     * Méthode back
     */
    private void back() {
        if(keywordInterpreter.getCurrentValue() != 0) {
            cursor = linkedBracket.get(cursor);
        }
    }

    /**
     * Permet de lire un crochet ouvrant à un fermé et vise versa
     */
    private void setLinkedBracket() {
        List<Integer> posCrochetOuvrant = new ArrayList<>();
        int i = 0;

        for(Map.Entry<Integer, Keywords> keywordsEntry: keywordsMap.entrySet()) {
            if(Keywords.JUMP.equals(keywordsEntry.getValue())) {
                posCrochetOuvrant.add(keywordsEntry.getKey());
                i++;
            }
            else if(Keywords.BACK.equals(keywordsEntry.getValue())) {
                linkedBracket.put(posCrochetOuvrant.get(i - 1), keywordsEntry.getKey());
                linkedBracket.put(keywordsEntry.getKey(), posCrochetOuvrant.get(i - 1));
                i--;
            }
        }
    }

    /**
     * Permet de récupérer la valeur vers laquelle pointe le pointeur dans la mémoire
     * @return la valeur de la mémoire pointée
     */
    public short getCurrentValue() {
        return keywordInterpreter.getCurrentValue();
    }
}
