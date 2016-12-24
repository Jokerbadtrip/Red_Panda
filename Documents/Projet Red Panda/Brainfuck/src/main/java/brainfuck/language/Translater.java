package brainfuck.language;

import brainfuck.language.enumerations.Keywords;

/**
 * @author jamatofu on 14/12/16.
 */
public class Translater {
    private String program;
    private StringBuilder newProgram;

    public Translater(String program) {
        this.program = program;
        this.newProgram = new StringBuilder();
    }

    /**
     * Permet de mettre les premières lignes importantes du program (pré-porcesseur, main...)
     */
    public void initializeProgram() {
        this.newProgram.append("#include <stdio.h>");
        this.newProgram.append("\n#include <stdlib.h>\n");
        this.newProgram.append("\nint main() {");
    }

    public void finishProgram() {
        this.newProgram.append("\nreturn 0;");
        this.newProgram.append("\n}");
    }
    /**
     * Compte le nombre de caractère identique avant qu'il y en ai un différent
     * @param morceauProgramme
     * @return
     */
    public int countCharacterInARow(String morceauProgramme) {
        return 0;
    }

    /**
     * Traduit l'instruction Brainfuck en équivalent dans un autre langage
     * @param keywords
     */
    public void translateInstruction(Keywords keywords) {
        switch (keywords) {
            case IN:
                break;
            case OUT:
                break;
            case INCR:
                break;
            case DECR:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
            case JUMP:
                break;
            case BACK:
                break;
            default:
                break;
        }
    }

    /**
     * Ajoute une/des ligne(s) au fichier texte du program
     */
    public void writeLineIntoProgram(String line, int nvIndentation) {
        for(int i = 0; i < nvIndentation; i++)
            this.newProgram.append("\t");
        this.newProgram.append(line);
        this.newProgram.append("\n");
    }

    public String getProgram() {
        return program;
    }

    public StringBuilder getNewProgram() {
        return newProgram;
    }
}
