package brainfuck.language.flag.commande;

import brainfuck.language.enumerations.Keywords;

import java.util.List;

/**
 * Permet de réécrire le programme en syntaxe courte
 * @author Red Panda
 */
public class Rewrite {
    private List<Keywords> program;

    public Rewrite(List<Keywords> program) {
        this.program = program;
    }

    /**
     * Permet de réécrire un programme en syntaxe courte uniquement
     * @return le programme en syntaxe courte
     */
    public String rewriteProgram() {
        StringBuilder rewritedProgram = new StringBuilder();

        for(Keywords line : program) {
            rewritedProgram.append(line.getShortcut());
        }

        return rewritedProgram.toString();
    }
}
