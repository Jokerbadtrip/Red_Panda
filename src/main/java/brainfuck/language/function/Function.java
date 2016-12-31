package brainfuck.language.function;

import brainfuck.language.enumerations.Keywords;
import java.util.List;

/**
 * Permet de modéliser ce qui ressemble à une fonction (procédure, fonction etc...)
 * @author jamatofu on 30/12/16.
 */
public class Function {
    private List<Keywords> code;
    private boolean procedure;

    public Function(List<Keywords> code, boolean procedure) {
        this.code = code;
        this.procedure = procedure;
    }

    public List<Keywords> getCode() {
        return code;
    }

    public boolean isProcedure() {
        return procedure;
    }
}
