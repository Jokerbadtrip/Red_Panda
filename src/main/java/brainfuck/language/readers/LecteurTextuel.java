package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mini-identificateur d'instruction pour une seule ligne de programme
 *
 * @author  Red_Panda
 */

public class LecteurTextuel {
    protected String program;
    private List<Keywords> keywordsList;

    public LecteurTextuel(String program) {
        this.program = program;
    }

    /**
     * Crée une liste de commande dans le programme à interpreter
     * @return la liste de commande
     */
    public List<Keywords> creeTableauCommande() throws IsNotACommandException, FileNotFoundException {
        program.trim();
        this.keywordsList = new ArrayList<>();

        if(!program.isEmpty()) {

            if (Keywords.isWord(program))
                keywordsList.add(Keywords.valueOf(program));
            else {
                keywordsList.addAll(inTheCaseOfShortcut(program));
            }
        }
        return keywordsList;
    }

    /**
     * Permet de récupérer les keywords situé sur une ligne String
     * @param line un String
     * @return une liste contenant les keywords de la ligne
     * @throws IsNotACommandException
     */
    private List<Keywords> inTheCaseOfShortcut(String line) throws IsNotACommandException {
        List<Keywords> keywordsList = new ArrayList<>();

        for (char character : line.toCharArray()) {
            if(Keywords.isShortcut(character)) {
                keywordsList.add(Keywords.shortcutToKeyword(character));
            }
            else if(Character.isWhitespace(character));
            else
                throw new IsNotACommandException(character);
        }
        return keywordsList;
    }

    public void setProgram(String program) {
        this.program = program;
    }

}


