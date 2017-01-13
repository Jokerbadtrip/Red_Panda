package brainfuck.language.interpreter;

import brainfuck.language.Metrics;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongInputException;

/**
 * Interpréter de Keyword. A 29745 cases mémoires allouées
 * @author jamatofu on 31/12/16.
 */
public class KeywordInterpreter extends Interpreter {
    private Trace trace = null;

    /**
     * Constructeur d'un interpréteur
     * @param infilepath chemin d'accès pour la commande -i
     * @param outfilepath chemin d'accès pour la commande -o
     */
    public KeywordInterpreter(String infilepath, String outfilepath) {
        super(infilepath, outfilepath, false);
    }

    /**
     *
     * @throws WrongInputException
     */
    public void identifyAndExecuteInstruction(Keywords keywords) throws WrongInputException {
        switch (keywords) {
            case INCR:
                incrMethod();
                Metrics.DATA_WRITE++;
                break;
            case DECR:
                decrMethod();
                Metrics.DATA_WRITE++;
                break;
            case LEFT:
                leftMethod();
                Metrics.DATA_MOVE++;
                break;
            case RIGHT:
                rightMethod();
                Metrics.DATA_MOVE++;
                break;
            case OUT:
                outMethod();
                Metrics.DATA_READ++;
                break;
            case IN:
                inMethod();
                Metrics.DATA_WRITE++;
                break;
            case JUMP:
            case BACK:
                Metrics.DATA_READ++;
                break;

            default:
                break;
        }

        if (trace != null) {
            trace.tracerUpdate(cursor, memory.getPointer(), memory.writeStateOfMemory());
        }
        Metrics.EXEC_MOVE++;
    }

    /**
     * Initialise la partie "Tracé" de l'interpreteur lorsque
     * l'argument "--TRACE" a été entré dans la console
     *
     * @param nomFichier Le nom du fichier dans lequel sera enregistré les logs
     */

    public void iniATracer(String nomFichier) {
        trace = new Trace(nomFichier);
    }

    /**
     * Permet de fermer la trace
     */
    public void endTrace() {
        if (trace != null)
            trace.end();
    }

    public Trace getTrace(){ return trace;}
}
