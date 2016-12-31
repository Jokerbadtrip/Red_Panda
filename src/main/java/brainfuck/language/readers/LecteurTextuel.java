package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;
import brainfuck.language.exceptions.WrongMacroNameException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de lire dans un fichier ".bf" les instructions qu'il contient, et de les envoyer à l'interpreteur
 *
 * @author  Red_Panda
 */

public class LecteurTextuel {
    protected String program;
    private List<Keywords> keywordsList;

    public LecteurTextuel(String program) throws WrongMacroNameException {
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
                throw new IsNotACommandException();
        }
        return keywordsList;
    }

    public void setProgram(String program) {
        this.program = program;
    }

}


