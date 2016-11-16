package brainfuck.language;

import brainfuck.language.Exceptions.IsNotACommandException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BEAL Clément on 28/09/16.
 */

public class LecteurTextuel {

    protected String[] words = {"RIGHT", "LEFT", "INCR", "DECR", "JUMP", "BACK", "OUT", "IN"};
    protected char[] shortcuts = {'+', '-', '<', '>', '.', ',', '[', ']'};

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

    public boolean estShortcut(char premierCaractere) {

        for(char shortcut : shortcuts) {
            if(shortcut == premierCaractere) return true;
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
     * @param texteDecoupe un morceau du texte source.
     * @return true si INSTRUCTION trouvée sinon false
     */

    public String estInstruction(String[] texteDecoupe) {
        String aEnvoyer = null;
        boolean finBoucle = false;

            for (String motDecoupe : texteDecoupe) { // on parcourt chacun des éléments du morceau de texte découpé
                for (String sousTab : words) { // là, on parcourt chacun des éléments de la liste word
                         if(sousTab.equals(motDecoupe)) {
                             aEnvoyer = sousTab;
                             finBoucle = true;
                             break;
                         }
                }

                if(finBoucle) break;
            }
        return aEnvoyer;
    }
    /**
     *
     * LA méthode va retourner un tableau de string de 4 String. Le premier aura 5 carac, le deuxieme 4 ... le quatrième 2
     *
     * @param texteADecouper un String de 5 caractère au maximum. Dépend du nombre de caractère restant dans le fichier
     * @return un tableau de String contenu le texte découpé
     */

    public String[] couperChaineCaractere(String texteADecouper) {
        String texteDecoupe[] = new String[texteADecouper.length() - 1];

        int longueur = texteADecouper.length();

        for (int i = longueur; i > 1; i--) texteDecoupe[longueur - i] = texteADecouper.substring(0, i);

        return texteDecoupe;
    }

    /**
     *  Grosse méthode. On lit le texte, trouve les commandes, les stocke dans une liste et on renvoie la liste
     * @return une liste contenant toutes les commandes trouvees dans le programme. Une case = une instruction
     */

    public ArrayList<String> creeTableauCommande() {
        char premierCaractere;
        int longueurProgramme = texteAAnalyser.length();
        ArrayList<String> listeCommandeTrouvee = new ArrayList<String>();

        while(texteAAnalyser.length() - index  > 0) {
            premierCaractere = this.texteAAnalyser.charAt(index);

            if(estShortcut(premierCaractere)) {
                listeCommandeTrouvee.add(Character.toString(premierCaractere));
                index += 1;
            } else {
                    String[] texteDecoupe;

                    // il se peut que le programme fasse moins de 5 caractères. Il faut donc faire en sorte qu'on ne sélectionne pas tjs 5 carctère.
                    int cbAjouter = (longueurProgramme - index >= 5) ? 5 : longueurProgramme - index;

                    // selectionne un morceau du texte
                    String morceauTexteAAnalyser = texteAAnalyser.substring(index, index + cbAjouter);
                    // coupe ce morceau de texte en morceaux plus petit
                    texteDecoupe = couperChaineCaractere(morceauTexteAAnalyser);

                    String commandeTrouvee = estInstruction(texteDecoupe);

                if (commandeTrouvee == null) {
                    throw new IsNotACommandException();
                } else {
                    index += commandeTrouvee.length();
                    listeCommandeTrouvee.add(commandeTrouvee);
                }

/*                while(texteAAnalyser.length() > 0) {
                    switch (Keywords.valueOf())
                }*/




            }
        }

        return listeCommandeTrouvee;
    }

    /**
     * Supprime tout les commentaires compris entre #
     */
    public void removeCommentary() {
        if(OperationTexte.isCorrectlyComment(texteAAnalyser)) {
            String regex = "#(.*?)\\n";
                texteAAnalyser = texteAAnalyser.replaceAll(regex, "");
        }
    }

    /**
     * Recoit de la classe Moteur le nom d'un fichier à lire
     * @param texte nom du fichier à lire
     */
    public void setTexteAAnalyser(String texte) {
        texteAAnalyser = OperationTexte.transformerInstructionEnSymbole(texte);
    }

    public String getTexteAAnalyser() { return texteAAnalyser;}
}

