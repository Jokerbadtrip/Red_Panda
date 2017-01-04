package brainfuck.language.function;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.readers.LecteurTextuel;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Permet de modéliser ce qui ressemble à une fonction (procédure, fonction etc...)
 * @author jamatofu on 30/12/16.
 */
public class Function {
    private List<Keywords> code;
    private boolean procedure;
    private List<Keywords> parametre = null;
    private String functionName;

    public Function(List<Keywords> code, boolean procedure, String functionName) {
        this.code = code;
        this.procedure = procedure;
        this.functionName = functionName;
    }

    public List<Keywords> getCode() {
        return code;
    }

    public boolean isProcedure() {
        return procedure;
    }

    public void setParametre(String parametre) throws FileNotFoundException {
        LecteurTextuel lecteurTextuel = new LecteurTextuel(parametre);
        this.parametre = lecteurTextuel.creeTableauCommande();
    }

    public boolean hasParameter() {
        return parametre != null;
    }

    public List<Keywords> getParametre() {
        return  parametre;
    }

    public String getFunctionName() { return functionName; }
}
