package brainfuck.language.readers;

import brainfuck.language.Macro;
import brainfuck.language.OperationTexte;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author BEAL Clément on 28/09/16.
 */

public class LecteurTextuel {

    private String texteAAnalyser;
    private int index;

    public LecteurTextuel() {
        this.index = 0;
    }

    /**
     *
     * On cherche à savoir si le caractère actuellement lu est un des shortcuts que l'on connait. Sinon cela peut être un mot
     *
     * @param premierCaractere on fournit uniquement le premier caractère en test (shortcut = 1 caractère)
     * @return true si c'est un shortcut SINON false
     */

    public boolean estShortcut(String premierCaractere) {

        for(Keywords keywords : Keywords.values()) {
            if(Objects.equals(keywords.getShortcut(), premierCaractere)) {

                return true;
            }
        }
        return false;
    }

    /**
     *
     * On cherche à savoir si une chaine de caractère particulière fait parti des INSTRUCTIONS
     * Au préalable, on nous aura envoyé un tableau de STRING de 5 caractère MAXIMUM (nbr de lettre le plus grand pour une instruction)
     * On utilise une fonction pour nous couper la chaine afin de récupérer les 2 premiers caract, puis les 3 etc...
     * On regarde si cette chaine contient une des INSTRUCTIONS
     * SI la chaine est contenue, on rajoute à l'INDEX le nombre de lettre de l'instruction trouvé
     *
     *
     * @return true si INSTRUCTION trouvée sinon false
     */

    public Keywords estInstruction() {
        String regex;
        for(Keywords word : Keywords.values()) {
            regex = "^" + word.getWord() + "(.*)";
            if(Pattern.matches(regex, texteAAnalyser)) {
                supprimerMorceauProgramme(word.getWord().length());
                return word;
            }
        }
        return null;
    }

    private void supprimerMorceauProgramme(int nbCaracASupprimer) {
        texteAAnalyser = texteAAnalyser.substring(nbCaracASupprimer);
    }

    /**
     *  Grosse méthode. On lit le texte, trouve les commandes, les stocke dans une liste et on renvoie la liste
     * @return une liste contenant toutes les commandes trouvees dans le programme. Une case = une instruction
     */

    public ArrayList<Keywords> creeTableauCommande() {
        String premierCaractere;
        Keywords commandeTrouvee;
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

        return listeCommandeTrouvee;
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


