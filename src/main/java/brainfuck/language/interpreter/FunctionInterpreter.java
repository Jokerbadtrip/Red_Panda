package brainfuck.language.interpreter;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongInputException;
import brainfuck.language.function.Function;

import java.util.List;

/**
 * Interpréteur de fonction. A 255 cases mémoires allouée
 * Dès qu'une fonction est interprétée, la valeur qui sera récupérée sera la valeur sur laquelle est le pointeur
 * @author jamatofu on 30/12/16.
 */
public class FunctionInterpreter extends Interpreter{

    /**
     * Constructeur d'un interpréteur
     * @param infilepath chemin d'accès pour la commande -i
     * @param outfilepath chemin d'accès pour la commande -o
     */
    public FunctionInterpreter(String infilepath, String outfilepath) {
        super(infilepath, outfilepath, true);
    }

    /**
     * Cherche parmi le catalogue de fonction, le code de la fonction correspondante et l'exécute
     * @throws WrongInputException
     */
    public void identifyAndExecuteInstruction(Function function) throws WrongInputException {
        List<Keywords> keywordsList = function.getCode();

        if(function.hasParameter())
            keywordsList.addAll(0, function.getParametre());

        setLinkedBracket(keywordsList);

        for (; cursor < keywordsList.size(); cursor++) {
            switch (keywordsList.get(cursor)) {
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
                    outMethod();
                    break;
                case IN:
                    inMethod();
                    break;
                case JUMP:
                    jump();
                    break;
                case BACK:
                    back();
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Renvoie le résultat final d'une fonction. On suppose que le résultat final est la case acutellement pointée.
     * On remet la mémoire à zéro pour l'appel d'une autre fonction
     * @return valeur de la case pointée
     */
    public short getResult() {
        short value = memory.getCellValue();
        memory.resetMemory();
        cursor = 0;
        return value;
    }
}
