package brainfuck.language;

import brainfuck.language.exceptions.WrongFunctionNameException;
import brainfuck.language.exceptions.WrongMacroNameException;
import brainfuck.language.function.Function;
import brainfuck.language.function.ParseFunction;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Permet de faire certain traitement de texte sur lr programme avant interprétation
 * @author jamatofu on 31/12/16.
 */
public class ProgramProcess {
    private String program;
    private ParseFunction parseFunction;

    public ProgramProcess(String program) {
        this.program = program;
    }

    /**
     *
     * @return
     */
    public String transform() throws WrongMacroNameException, FileNotFoundException, WrongFunctionNameException {
        if (!"".equals(program)) {
            Macro macro = new Macro(program);
            program = macro.readMacro();
            removeCommentary();

            parseFunction = new ParseFunction(program);
            program = parseFunction.findPrototype();

            return program;
        }

        return "";
    }

    /**
     * Supprime tous les caractères compris entre # et "\n"
     * @return le texte en entrée, sans les commentaires
     */
    public void removeCommentary() {
        String regex = "#(.*?)\\n";
        program.replaceAll(regex, "");
    }

    /**
     *
     * @return
     */
    public Map<String, Function> getFunctionMap() {
        return parseFunction.getFunctionMap();
    }
}
