package brainfuck.language;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.BadMacro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe gère les macros dans le langage BrainFuck
 *
 * Syntaxe d'une macro :
 *      \@NOM_MACRO (param)  code
 * @author jamatofu on 04/11/16.
 */
public class Macro {
    private HashMap<String, String> macro = new HashMap<String, String>();
    private HashMap<String, String> macroRecursive = new HashMap<>();
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

        return programme.replaceAll("\\s+", "");
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
        boolean isParameterized;
        String caractereLimitant1 = " ";
        String caractereLimitant2 = " ";

        for (String mac : macrosADecouper) {
            isParameterized = isParameterizedMacro(mac);
            if(isParameterized) {
                caractereLimitant1 = "(";
                caractereLimitant2 = ")";
            }

            mac = mac.replaceFirst("@", "");

            String name = mac.substring(0, mac.indexOf(caractereLimitant1));
            String code = mac.substring(mac.indexOf(caractereLimitant2) + 1, mac.length());

            // vérifie que le nom de la macro ne correspond pas à une macro déjà existante
            if(macro.containsKey(name) || macroRecursive.containsKey(name)) throw new BadMacro();
            for(Keywords keywords : Keywords.values()) {
                if(keywords.getShortcut().equals(name) || keywords.name().equals(name)) {
                    throw new BadMacro();
                }
            }

            if(isParameterized) macroRecursive.put(name, code);
            else macro.put(name, code);

            caractereLimitant1 = " ";
            caractereLimitant2 = " ";
        }
    }

    /**
     * Vérifie que la macro est une macro paramétrisée. C'est-à-dire que l'on a la structure nom(param)
     * @param macro la macro à analyser
     * @return vrai si la macro est paramétrée SINON faux
     */

    public boolean isParameterizedMacro(String macro) {
        String regex = "^@(.*)\\(\\) (.*)+";
        return Pattern.matches(regex, macro);
    }

    /**
     * Récupère la valeur du paramètre d'une macro (ex : A(5) => 5)
     * @param macro la macro appelée avec son paramètre
     * @return la valeur du paramètre
     */
    public int retournerParametre(String macro) {
        return Integer.parseInt(macro.substring(macro.indexOf("(") + 1, macro.indexOf(")")));
    }

    /**
     * Change les commandes macro dans le programme par les instructions correspondantes
     * @return le texte modifié
     */
    public void remplacerMacroParCode() {
        String codeAMettre = "";
        String regex;
        Pattern p;
        Matcher m;

        for(Entry<String, String> entry : macroRecursive.entrySet()) {
            regex = entry.getKey() + "\\(\\d\\)";
            p = Pattern.compile(regex);
            m  = p.matcher(programme);

            String truc = null;
            while(m.find()) {
                truc = m.group();
            }

            //TODO retourner une exception
//            if(truc == null) {}
            int valeurParam = retournerParametre(truc);

            for(int i = 0; i < valeurParam; i++) {
                codeAMettre += entry.getValue();
            }
            System.out.println(codeAMettre);

            programme = programme.replace(entry.getKey() + "(" + valeurParam + ")", codeAMettre);
        }


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
}
