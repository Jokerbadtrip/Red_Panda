package brainfuck.language;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe gère les macros dans le langage BrainFuck
 * Une macro aura une syntaxe semblable au C
 * ex : #define NOM_MACRO code
 *      #define NOM_MACRO(param1, param2, ...) code (cas avec paramètre)
 * Dans notre cas, ce sera :
 *      \@NOM_MACRO(param1,param2, ...) code
 * @author  Red_Panda
 */
public class Macro {
    private TreeMap<String, String> macro = new TreeMap<String, String>();
    private String programme;

    /**
     * Constructeur de la classe Macro
     */

    public Macro(String programme) {
        this.programme = programme;
    }

    /**
     * Permet de globalement lire une macro et de la remplacer par son code
     * @return le programme avec les macro remplacées par le code
     */
    public String readMacro() {
        findMacro();
        remplacerMacroParCode();

        return programme;
    }

    /**
     * Permet de détecter les lignes qui correspondent à une macro
     * Les renvoie ensuite au découpeur de macro
     */
    public void findMacro() {
        ArrayList<String> toutesLesMacros = new ArrayList<String>();

        while(programme.charAt(0) == '@') {
            toutesLesMacros.add(programme.substring(0, programme.indexOf("\n")));
            programme = supprimerLigneDeTexte(toutesLesMacros.get(toutesLesMacros.size() -1), programme);
        }


        //for(String a : toutesLesMacros) System.out.println(a);
        decomposerMacro(toutesLesMacros);
    }

    /**
     * Découpe dans chaque ligne de macro son nom et son code puis l'insère dans le Map macro
     * @param macrosADecouper les lignes contenants les macros
     */
    public void decomposerMacro(ArrayList<String> macrosADecouper) {
        for (String mac : macrosADecouper) {
            mac = mac.replaceFirst("@", "");

            String name = mac.substring(0, mac.indexOf(" "));
            String code = mac.substring(mac.indexOf(" ") + 1, mac.length());

            macro.put(name, code);
        }
    }

    /**
     * Change les commandes macro dans le programme par les instructions correspondantes
     * @return le texte modifié
     */
    public void remplacerMacroParCode() {
        for(Entry<String, String> entry : macro.entrySet()) {
            programme = programme.replaceAll(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Supprime une ligne d'un texte
     * @param ligneASupprimer la ligne à supprimer du texte
     */
    public String supprimerLigneDeTexte(String ligneASupprimer, String texte) {
        return texte.replace(ligneASupprimer + "\n", "");
    }

    public boolean toutEnOrdre() {
        System.out.print(programme);
        Pattern pattern = Pattern.compile("(.*)[^+-](.*)");
        Matcher matcher = pattern.matcher(programme);
        return matcher.find();
    }

    public String getProgramme() { return programme; }

}
