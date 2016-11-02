package brainfuck.language;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author BEAL Clément on 28/09/16.
 */

public class LecteurTextuel {

    protected String[] words = {"RIGHT", "LEFT", "INCR", "DECR", "JUMP", "BACK", "OUT", "IN"};
    protected String[] shortcuts = {"+", "-", "<", ">", ".", ",", "[", "]"};

    private String texteALire;
    private int indexCaractereActuellementLu;

    public LecteurTextuel() {
        this.indexCaractereActuellementLu = 0;
    }

    /**
     *
     * On cherche à savoir si le caractère actuellement lu est un des shortcuts que l'on connait. Sinon cela peut être un mot
     *
     * @param premierCaractere on fournit uniquement le premier caractère en test (shortcut = 1 caractère)
     * @return true si c'est un shortcut SINON false
     */

    public boolean estShortcut(String premierCaractere) {

        for(String shortcut : shortcuts) {
            if(shortcut.equals(premierCaractere)) return true;
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
                         if(motDecoupe.equals(sousTab)) {
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
        String texteDecoupe[] = new String[texteADecouper.length()];

        int longueur = texteADecouper.length();

        for (int i = longueur; i > 1; i--) texteDecoupe[longueur - i] = texteADecouper.substring(0, i);

        return texteDecoupe;
    }

    /**
     *  Grosse méthode. On lit le texte, trouve les commandes, les stocke dans une liste et on renvoie la liste
     * @return une liste contenant toutes les commandes trouvees dans le programme. Une case = une instruction
     */

    public ArrayList<String> creeTableauCommande() {
        String premierCaractere; // peut être utiliser un char?
        ArrayList<String> listeCommande = new ArrayList<String>();

        while(texteALire.length() - indexCaractereActuellementLu  > 0) {
            premierCaractere = Character.toString(this.texteALire.charAt(indexCaractereActuellementLu));

            if(estShortcut(premierCaractere)) {
                listeCommande.add(premierCaractere);
                indexCaractereActuellementLu += 1;
            }
            else {
                    String[] texteDecoupe;

                    // il se peut que le programme fasse moins de 5 caractères. Il faut donc faire en sorte qu'on ne sélectionne pas tjs 5 carctère.
                    int tailletexte = texteALire.length();
                    int cbAjouter;

                    if(tailletexte - indexCaractereActuellementLu >= 5) {
                        cbAjouter = 5;
                    }
                    else {
                        cbAjouter = tailletexte - indexCaractereActuellementLu;
                    }

                    // selectionne un morceau du texte
                    String texteALireMorceau = texteALire.substring(indexCaractereActuellementLu, indexCaractereActuellementLu + cbAjouter);
                    texteDecoupe = couperChaineCaractere(texteALireMorceau);

                    String commandeTrouvee = estInstruction(texteDecoupe);

                    if (commandeTrouvee != null) {
                        indexCaractereActuellementLu += commandeTrouvee.length();
                        listeCommande.add(commandeTrouvee);
                    }
            }
        }

        return listeCommande;
    }




    /**
     * Recoit de la classe Moteur le nom d'un fichier à lire
     * @param texte nom du fichier à lire
     */
    public void setTexteALire(String texte) { texteALire = texte; }

    public void toString(ArrayList<String> liste) { // permet d'afficher une liste
        for(String comm : liste) {
            System.out.print(comm);
        }
    }

    public ArrayList<String> transformerInstructionEnSymbole(ArrayList<String> listeATransformer){
        Collections.replaceAll(listeATransformer, "RIGHT", ">");
        Collections.replaceAll(listeATransformer, "LEFT", "<");
        Collections.replaceAll(listeATransformer, "INCR", "+");
        Collections.replaceAll(listeATransformer, "DECR", "-");
        Collections.replaceAll(listeATransformer, "JUMP", "[");
        Collections.replaceAll(listeATransformer, "BACK", "]");
        Collections.replaceAll(listeATransformer, "OUT", ".");
        Collections.replaceAll(listeATransformer, "IN", ",");

        return listeATransformer;
    }
}

