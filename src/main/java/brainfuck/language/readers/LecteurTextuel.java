package brainfuck.language.readers;

import brainfuck.language.Macro;
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
    private String program;

    public LecteurTextuel(String program) throws WrongMacroNameException {
        this.program = modifiedProgram(program);
    }

    /**
     * Crée une liste de commande dans le programme à interpreter
     * @return la liste de commande
     */
    public List<Keywords> creeTableauCommande() throws IsNotACommandException, FileNotFoundException {
        List<Keywords> commandFoundList = new ArrayList<>();
        String[] linesOfProgram = program.split(System.getProperty("line.separator"));

        for(String line : linesOfProgram) {
            if (Keywords.isWord(line))
                commandFoundList.add(Keywords.valueOf(line));
            else {
                commandFoundList.addAll(inTheCaseOfShortcut(line));
            }

        }

        return commandFoundList;
    }

    /**
     * Permet de récupérer les keywords situé sur une ligne String
     * @param line un String
     * @return une liste contenant les keywords de la ligne
     * @throws IsNotACommandException
     */
    public List<Keywords> inTheCaseOfShortcut(String line) throws IsNotACommandException {
        List<Keywords> keywordsList = new ArrayList<>();

        for (char character : line.toCharArray()) {
            if(Keywords.isShortcut(character))
                keywordsList.add(Keywords.shortcutToKeyword(character));
            else if(Character.isWhitespace(character));
            else
                throw new IsNotACommandException();
        }

        return keywordsList;
    }

    /**
     * Supprime tous les caractères compris entre # et "\n"
     * @param texte Le texte où les commentaires doivent être supprimés
     * @return le texte en entrée, sans les commentaires
     */
    public String removeCommentary(String texte) {
        String regex = "#(.*?)\\n";
        return texte.replaceAll(regex, "");
    }

    /**
     * Reçois de la classe Moteur le nom d'un fichier à lire
     * @param texte nom du fichier à lire
     */
    private String modifiedProgram(String texte) throws WrongMacroNameException {
        if (!"".equals(texte)) {
            Macro macro = new Macro(texte);
            texte = macro.readMacro();
            texte = removeCommentary(texte);

            return texte;
        }

        return "";
    }

    public void setProgram(String program) {
        this.program = program;
    }
}


