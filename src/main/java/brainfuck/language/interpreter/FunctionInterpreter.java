package brainfuck.language.interpreter;

import brainfuck.language.Memory;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongInputException;
import brainfuck.language.function.Function;

import java.util.List;
import java.util.Map;

/**
 * @author jamatofu on 30/12/16.
 */
public class FunctionInterpreter extends Interpreter{
    private Map<String, Function> functionMap;

    /**
     * @param infilepath
     * @param outfilepath
     */
    public FunctionInterpreter(String infilepath, String outfilepath, Map<String, Function> functionMap) {
        super(infilepath, outfilepath);
        this.functionMap = functionMap;
        this.memory = new Memory(true);
    }

    /**
     * Cherche parmi le catalogue de fonction, le code de la fonction correspondante et l'exécute
     * @throws WrongInputException
     */
    public void identifyAndExecuteInstruction(Function function) throws WrongInputException {
        List<Keywords> keywordsList = function.getCode();
        recenseCrochet(keywordsList);

        for (Keywords keywords : keywordsList) {
            switch (keywords) {
                case INCR:
                    incrMethod();
                    break;
                case DECR:
                    decrMethod();
                    break;
                case LEFT:
                    leftMethod();
                    break;
                case RIGHT:
                    rightMethod();
                    break;
                case OUT:
                    outMethod(outfilepath);
                    break;
                case IN:
                    inMethod(infilepath);
                    break;
                case JUMP:
                    jumpMethod(keywordsList);
                    break;
                case BACK:
                    backMethod(keywordsList);
                    break;

                default:
                    break;
            }
        }
    }


    protected void jumpMethod(List<Keywords> keywordsList) {
        if (memory.getCellValue() == 0)
            cursor += countInstru(keywordsList, cursor);
    }

    protected void backMethod(List<Keywords> keywordsList) {
        if (memory.getCellValue() != 0)
            cursor = placeCrochet.get(retournePlace(keywordsList, cursor));
    }

    /**
     * Renvoie le résultat final d'une fonction. On suppose que le résultat final est la case acutellement pointée
     * @return
     */
    public short getResult() {
        short value = memory.getCellValue();
        memory.resetMemory();
        return value;
    }

    /**
     * Permet de connaitre rapidement la position du crochet ouvrant associé
     * au crochet fermant actuel
     *
     * @param i La position du crochet fermant que l'on interprete
     * @param tableauCommande La liste de commande que l'on est en train d'interpreter
     * @return La position dans la liste de commande du crochet ouvrantassocié au crochet fermant actuel
     */

    protected int retournePlace(List<Keywords> tableauCommande, int i){
        int placeCrochetActu = 0;
        int j = 0;

        while (j < i) {
            if (tableauCommande.get(j) == Keywords.BACK)
                placeCrochetActu++;
            j++;
        }
        return placeCrochet.size() - placeCrochetActu - 1;
    }

    /**
     * Permet de connaitre le nombre d'instruction qui sont compris entre le crochet ouvrant
     * actuel et le crochet fermant associé
     *
     * @param i La position du crochet ouvrant que l'on interprete
     * @param tableauCommande La liste de commande que l'on est en train d'interpreter
     * @return Le nombre d'instructions entre le crochet ouvrant actuel et le crochet fermant associé
     */

    protected int countInstru(List<Keywords> tableauCommande, int i) {
        int nbOuvrante = 1;
        int it = i + 1;

        while (nbOuvrante != 0) {
            if (tableauCommande.get(it) == Keywords.JUMP) {
                nbOuvrante++;
            }
            if (tableauCommande.get(it) == Keywords.BACK) {
                nbOuvrante--;
            }
            it++;
        }
        return it - i;
    }
}
