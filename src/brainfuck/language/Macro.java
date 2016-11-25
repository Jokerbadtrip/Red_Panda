package brainfuck.language;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.BadMacro;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Cette classe gère les macros dans le langage BrainFuck
 * Une macro aura une syntaxe semblable au C
 * ex : #define NOM_MACRO code
 *      #define NOM_MACRO(param1, param2, ...) code (cas avec paramètre)
 * Dans notre cas, ce sera :
 *      \@NOM_MACRO(param1,param2, ...) code
 * @author jamatofu on 04/11/16.
 */
public class Macro {
    private TreeMap<String, String> macro = new TreeMap<String, String>();
    private String programme;

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
        try {
            decomposerMacro(toutesLesMacros);
        }
        catch (BadMacro e) {
            e.printStackTrace();
        }
    }

    /**
     * Découpe dans chaque ligne de macro son nom et son code puis l'insère dans le Map macro
     * @param macrosADecouper les lignes contenants les macros
     */
    public void decomposerMacro(ArrayList<String> macrosADecouper) throws BadMacro {
        for (String mac : macrosADecouper) {
            mac = mac.replaceFirst("@", "");

            String name = mac.substring(0, mac.indexOf(" "));
            String code = mac.substring(mac.indexOf(" ") + 1, mac.length());

            for(Keywords keywords : Keywords.values()) {
                if(keywords.getShortcut().equals(name) || keywords.getWord().equals(name)) {
                    throw new BadMacro();
                }

            }
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

    public String getProgramme() { return programme; }

}
