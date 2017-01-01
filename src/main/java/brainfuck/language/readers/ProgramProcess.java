package brainfuck.language.readers;

import brainfuck.language.Macro;
import brainfuck.language.exceptions.WrongFunctionNameException;
import brainfuck.language.exceptions.WrongMacroNameException;
import brainfuck.language.exceptions.function.BadFunctionDefinition;
import brainfuck.language.function.Function;
import brainfuck.language.function.ParseFunction;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Permet de faire certain traitement de texte sur le programme avant interprétation (macro, commentaire, fonction)
 * @author jamatofu on 31/12/16.
 */
public class ProgramProcess {
    private String program;
    private ParseFunction parseFunction;

    public ProgramProcess(String program) {
        this.program = program;
    }

    /**
     * Permet de remplacer les macros par leur code dans le programme
     * Enlève ensuite les commentaires
     * Identidie les fonctions déclarées
     * @return le programme final à examiner
     */
    public String transform() throws WrongMacroNameException, FileNotFoundException, WrongFunctionNameException, BadFunctionDefinition {
        if (!"".equals(program)) {
            Macro macro = new Macro(program);
            program = macro.readMacro();
            program = removeCommentary();

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
    public String removeCommentary() {
        String regex = "#(.*?)\\n";
        return program.replaceAll(regex, "");
    }

    public Map<String, Function> getFunctionMap() {
        return parseFunction.getFunctionMap();
    }
}
