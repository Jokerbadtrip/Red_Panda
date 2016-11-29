package brainfuck.language.readers;

import brainfuck.language.Macro;
import brainfuck.language.OperationTexte;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import static brainfuck.language.enumerations.Keywords.*;

/**
 * @author BEAL Clément on 28/09/16.
 */

public class LecteurTextuel {

    private String texteAAnalyser;
    private int index;

    public LecteurTextuel() {
        this.index = 0;
    }

   /* /**
     *
     * On cherche à savoir si le caractère actuellement lu est un des shortcuts que l'on connait. Sinon cela peut être un mot
     *
     * @param premierCaractere on fournit uniquement le premier caractère en test (shortcut = 1 caractère)
     * @return true si c'est un shortcut SINON false


    public boolean estShortcut(String premierCaractere) {
        if (isKeyword(premierCaractere)) return true;
        return false;
    }*/


    /**
     *
     * On cherche à savoir si une chaine de caractère particulière fait parti des INSTRUCTIONS
     * Au préalable, on nous aura envoyé un tableau de STRING de 5 caractère MAXIMUM (nbr de lettre le plus grand pour une instruction)
     * On utilise une fonction pour nous couper la chaine afin de récupérer les 2 premiers caract, puis les 3 etc...
     * On regarde si cette chaine contient une des INSTRUCTIONS
     * SI la chaine est contenue, on rajoute à l'INDEX le nombre de lettre de l'instruction trouvé
     *
     * Checks if the text to be analyzed is an instruction word or not
     *
     *
     * @return the keyword associated
     */

    public Keywords estInstruction() {
        for (String word : displayWords())
            if (Pattern.matches("^"+word+"(.*)", texteAAnalyser)) {
                supprimerMorceauProgramme(word.length());
                return wordToKeyword(word);
            }
        return null;

       /* String regex;
        for(Keywords word : Keywords.values()) {
            regex = "^" + word.getWord() + "(.*)";
            if(Pattern.matches(regex, texteAAnalyser)) {
                supprimerMorceauProgramme(word.getWord().length());
                return word;
            }
        }
        return null;*/
    }

    /**
     * Erases the words in the current program from the beginning to the
     * parameter
     * @param nbCaracASupprimer the number of characters to be erased
     */
    private void supprimerMorceauProgramme(int nbCaracASupprimer) {
        texteAAnalyser = texteAAnalyser.substring(nbCaracASupprimer);
    }

    /**
     * Creates a list of the keywords inside the program
     * @return the list of keywords
     */
    public ArrayList<Keywords> creeTableauCommande() {

        String firstCharacter;
        Keywords commandFound;
        ArrayList<Keywords> commandFound_List = new ArrayList<>();

        while (texteAAnalyser.length()!=0){
            //Store the first character of the current untreated program
            firstCharacter = Character.toString(texteAAnalyser.charAt(index));

            if (isShortcut(firstCharacter)){ // if the first character is a shortcut, e.g +,-,>
                commandFound_List.add(shortcutToKeyword(firstCharacter)); // We had the command
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

        /*String premierCaractere;
        Keywords commandeTrouvee;
        int longueurProgramme = texteAAnalyser.length();
        ArrayList<Keywords> listeCommandeTrouvee = new ArrayList<>();

        while(texteAAnalyser.length() != 0) {
            premierCaractere = Character.toString(texteAAnalyser.charAt(index));

            if(estShortcut(premierCaractere)) {
                listeCommandeTrouvee.add(Keywords.toKeyword(premierCaractere));
                supprimerMorceauProgramme(1);
            } else {
                commandeTrouvee = estInstruction();
                if (commandeTrouvee == null) {
                    throw new IsNotACommandException();
                } else {
                    listeCommandeTrouvee.add(commandeTrouvee);
                }
            }
        }

        return listeCommandeTrouvee;*/
    }

    /**
     * Supprime tout les commentaires compris entre #
     */
    public String removeCommentary(String texte) {
        String regex = "#(.*?)\\n";
        return texte.replaceAll(regex, "");
    }

    /**
     * Recoit de la classe Moteur le nom d'un fichier à lire
     * @param texte nom du fichier à lire
     */
    public void setTexteAAnalyser(String texte) {
        Macro macro = new Macro(texte);
        texte = macro.readMacro();
        texte = removeCommentary(texte);
        texte = texte.replaceAll("\\s+", "");
        texte = OperationTexte.transformerInstructionEnSymbole(texte);

        texteAAnalyser = texte;
    }

    public String getTexteAAnalyser() { return texteAAnalyser;}
}


