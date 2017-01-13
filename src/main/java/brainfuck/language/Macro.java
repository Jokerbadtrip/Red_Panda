package brainfuck.language;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongMacroNameException;
import brainfuck.language.exceptions.function.BadFunctionDefinition;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe gère les macros dans le langage BrainFuck
 *
 * Syntaxe d'une macro :
 *      \@NOM_MACRO  code
 * ex: @MACRO ++++
 * @author jamatofu on 04/11/16.
 */
public class Macro {
    private Map<String, String> macro;
    private String programme;
    private StringBuilder stringBuilder = new StringBuilder();

    public Macro(String programme) {
        this.programme = programme;
        this.macro = new HashMap();
    }

    /**
     * Permet de globalement lire une macro et de la remplacer par son code
     * @return le programme avec les macro remplacées par le code
     */
    public String readMacro() throws WrongMacroNameException, BadFunctionDefinition {
        findPrototype();
        changeCodeIntoMacro();
        remplacerMacroParCode();

        return programme;
    }

    /**
     * Permet de détecter les lignes qui correspondent à une macro
     * Les renvoie ensuite au découpeur de macro
     */
    public void findPrototype() throws WrongMacroNameException, BadFunctionDefinition {
        List<String> prototypes = new ArrayList<>();
        while(programme.charAt(0) == '@') {
            prototypes.add(programme.substring(0, programme.indexOf('\n')));
            programme = supprimerLigneDeTexte(prototypes.get(prototypes.size() -1), programme);
        }

        sliceMacro(prototypes);
    }

    /**
     * Découpe dans chaque prototype son nom et son code puis l'insère dans le Map macro
     * @param macrosADecouper les lignes contenants les macros
     */
    public void sliceMacro(List<String> macrosADecouper) throws WrongMacroNameException, BadFunctionDefinition {
        String[] cutLine;

        for (String mac : macrosADecouper) {
            mac = mac.replaceFirst("@", "");
            cutLine = mac.split(" ");

            if(cutLine.length != 2)
                throw new BadFunctionDefinition(mac);

            String name = cutLine[0];
            String code = cutLine[1];
            code = code.replaceAll("\\s+", "");



            if(!isValidName(name))
                throw new WrongMacroNameException(name);

            macro.put(name, code);
        }
    }

    /**
     * Vérifie que le nom de la macro est valide
     * @param name nom macro
     * @return vrai si valide
     */
    private boolean isValidName(String name) {
        for(Keywords keywords : Keywords.values()) {
            if(name.equals(keywords.name()) || keywords.getShortcut() == name.charAt(0)) {
                return false;
            }
        }

        return !name.matches("(\\d+)");
    }

    /**
     * Permet de changer le code à l'intérieur des macros récursives
     */
    private void changeCodeIntoMacro() {
        Set<String> nameMacro = macro.keySet();
        for(Entry<String, String> entry : macro.entrySet()) {
            if(nameMacro.contains(entry.getValue()))
                macro.put(entry.getKey(), macro.get(entry.getValue()));
        }
    }

    /**
     * Regarde si la macro a un paramètre
     * @param line ligne à regarder
     * @return vrai si paramètre présent
     */
    private boolean isParametrised(String line) {
        String regex = "^(.*) (\\d)+( #(.*))?";
        return Pattern.matches(regex, line);
    }

    /**
     * Lis chacunes des lignes du programme et en récupère le code
     * @return le texte modifié
     */
    public void remplacerMacroParCode() {
        programme = programme.trim();
        String[] linesProgram = programme.split(System.lineSeparator());

        for(String line : linesProgram) {
            if(line.isEmpty())
                continue;

            if(!getCodeFromOneLine(line))
                stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }
        programme = stringBuilder.toString();
    }

    /**
     * Lis une macro sur une seule ligne et en récupère le code
     * @param line ligne programme
     */
    private boolean getCodeFromOneLine(String line) {
        String regex;
        Pattern p;
        Matcher m;
        String[] cutLine;
        cutLine = line.split("\\s");

        for (Entry<String, String> macroEntry : macro.entrySet()) {
            if (cutLine[0].matches(macroEntry.getKey())) {
                if (isParametrised(line)) { // si la ligne contient un parametre
                    regex = macroEntry.getKey() + "\\d";
                    p = Pattern.compile(regex);
                    m = p.matcher(programme);

                    while (m.find()) {
                        m.group();
                    }

                    int valeurParam = Integer.parseInt(cutLine[1]);
                    for (int i = 0; i < valeurParam; i++) {
                        stringBuilder.append(macroEntry.getValue());
                    }

                    return true;

                } else { // sinon on ajoute simplement 1 fois le code de la macro
                    stringBuilder.append(macroEntry.getValue());
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * Supprime une ligne d'un texte
     * @param ligneASupprimer la ligne à supprimer du texte
     */
    private String supprimerLigneDeTexte(String ligneASupprimer, String texte) {
        return texte.replace(ligneASupprimer + System.lineSeparator(), "");
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    /**
     * Renvoie le texte de stringBuilder
     * @return texte de stringbuilder
     */
    public String getStringBuilder() {
        return stringBuilder.toString();
    }

    public void setMacro(Map<String, String> macro) {
        this.macro = macro;
    }
}
