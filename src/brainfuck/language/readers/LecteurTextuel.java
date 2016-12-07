package brainfuck.language.readers;

import brainfuck.language.Macro;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Classe permettant de lire dans un fichier ".bf" les instructions qu'il contient, et de les envoyer à l'interpreteur
 *
 * @author  Red_Panda
 */

public class LecteurTextuel {

    private String texteAAnalyser;

    /**
     * Vérifie si le texte à analyser est une instruction ou non
     * @return the keyword associated
     */

    public Keywords estInstruction() {
        for (String word : Keywords.displayWords())
            if (Pattern.matches("^"+word+"(.*)", texteAAnalyser)) {
                supprimerMorceauProgramme(word.length());
                return Keywords.wordToKeyword(word);
            }
        return null;
    }

    /**
     * Effacer les mots du programme actuelle, depuis le début du programme
     * jusqu'au parametre
     * @param nbCaracASupprimer Le nombre de caractere a effacer
     */
    private void supprimerMorceauProgramme(int nbCaracASupprimer) {
        texteAAnalyser = texteAAnalyser.substring(nbCaracASupprimer);
    }

    /**
     * Crée une liste de commande dans le programme à interpreter
     * @return la liste de commande
     */
    public ArrayList<Keywords> creeTableauCommande() {

        String firstCharacter;
        Keywords commandFound;
        ArrayList<Keywords> commandFound_List = new ArrayList<>();



        while (texteAAnalyser.length()!=0){
            //Store the first character of the current untreated program
            firstCharacter = Character.toString(texteAAnalyser.charAt(0));

            if (Keywords.isShortcut(firstCharacter)){ // if the first character is a shortcut, e.g +,-,>
                commandFound_List.add(Keywords.shortcutToKeyword(firstCharacter)); // We had the command
                supprimerMorceauProgramme(1);//We delete that shortcut
            }
            else {
                commandFound = estInstruction();
                //If there is a bad command in the file, then we tell it to the user, it's might not be intended
                if (commandFound == null) throw new IsNotACommandException();
                //Else the command is added to the list
                else commandFound_List.add(commandFound);
            }
        }

        return commandFound_List;
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
    public void setTexteAAnalyser(String texte) {
        if (!texte.equals("")) {
            Macro macro = new Macro(texte);
            texte = macro.readMacro();
            texte = removeCommentary(texte);
            texte = texte.replaceAll("\\s+", "");

            texteAAnalyser = texte;
        }
    }

    public void setProgramme(String programme) { texteAAnalyser = programme;}

    public String getTexteAAnalyser() {
        return texteAAnalyser;
    }
}


