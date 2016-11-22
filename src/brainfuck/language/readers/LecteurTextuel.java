package brainfuck.language.readers;

import brainfuck.language.exceptions.IsNotACommandException;
import brainfuck.language.OperationTexte;

import java.util.ArrayList;

/**
 * @author BEAL Clément on 28/09/16.
 */

public class LecteurTextuel {

    protected String[] words = {"RIGHT", "LEFT", "INCR", "DECR", "JUMP", "BACK", "OUT", "IN"};
    protected char[] shortcuts = {'+', '-', '<', '>', '.', ',', '[', ']'};

    private String texteAAnalyser;
    private int index;
    /*private ArrayList chiffre = new ArrayList(){{
        add('0');
        add('1');
        add('2');
        add('3');
        add('4');
        add('5');
        add('6');
        add('7');
        add('8');
        add('9');
    }};*/

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


                // selectionne un morceau du texte
                /*if (texteAAnalyser.length() - index  > 7){
                    String morceau7 = texteAAnalyser.substring(index,index+8);
                    if (morceau7.equals("TO_DIGIT")){
                        for (int j=0; j<48;j++){
                            listeCommandeTrouvee.add("-");
                        }
                        index+=8;
                    }
                }
                if (texteAAnalyser.length() - index  > 10){
                    String morceau10 = texteAAnalyser.substring(index, index +10);
                    if (morceau10.equals("MULTI_DECR")){
                        int monInt = 1;
                        if (texteAAnalyser.length() - index  > 12 && chiffre.contains(texteAAnalyser.charAt(index+12))){
                            if (texteAAnalyser.length() - index  > 13 && chiffre.contains(texteAAnalyser.charAt(index+13))){
                                monInt = 3;
                            }
                            else{
                                monInt = 2;
                            }
                        }

                        if (monInt == 2){
                            int i = Integer.parseInt(texteAAnalyser.substring(index+11, index+13));
                            for (int j=0; j<i;j++){
                                listeCommandeTrouvee.add("-");
                            }
                            index+=13;
                        }
                        else if (monInt == 3){
                            int i = Integer.parseInt(texteAAnalyser.substring(index+11, index+14));
                            for (int j=0; j<i;j++){
                                listeCommandeTrouvee.add("-");
                            }
                            index+=14;
                        }
                        else if (monInt == 1){
                            int i = Integer.parseInt(texteAAnalyser.substring(index+11, index+12));
                            for (int j=0; j<i;j++){
                                listeCommandeTrouvee.add("-");
                            }
                            index+=12;
                        }

                    }
                }


                if (texteAAnalyser.length() - index  > 0 && !(estShortcut(texteAAnalyser.charAt(index)))){*/
                    int cbAjouter = (longueurProgramme - index >= 5) ? 5 : longueurProgramme - index;
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
                //}

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


