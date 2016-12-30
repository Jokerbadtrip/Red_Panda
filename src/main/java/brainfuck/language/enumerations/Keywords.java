package brainfuck.language.enumerations;


import brainfuck.language.exceptions.IsNotACommandException;
import brainfuck.language.exceptions.IsNotAValidColorException;

import java.util.ArrayList;
import java.util.List;


/**
 * Un énum permettant d'associer n'importe quel type d'écriture (texte/image) à une instruction commune
 *
 * @author  Red_Panda
 */
public enum Keywords {

    INCR('+', "#ffffff"),
    DECR('-', "#4b0082"),
    LEFT('<', "#9400d3"),
    RIGHT('>', "#0000ff"),
    OUT('.', "#00ff00"),
    IN(',', "#ffff00"),
    JUMP('[', "#ff7f00"),
    BACK(']', "#ff0000");


    private char shortcut;
    private String color;

    Keywords(char shortcut, String color) {
        this.shortcut = shortcut;
        this.color = color;
    }

    /**
     * Verifie si la chaine de caractère entrée est valide et interpretable
     * @param word la chaine de caractère à vérifier
     * @return true si la chaine de caractère est valide
     */
    public static boolean isWord(String word) {
        for (Keywords keyword: Keywords.values()) {
            if (keyword.name().equals(word))
                return true;
        }
        return false;
    }

    /**
     * Verifie si le caractère entré est valide et interpretable
     * @param shortcut tLe caractère à vérifier
     * @return true si le caractère est valide
     */
    public static boolean isShortcut(Character shortcut) {
        for (Keywords keyword : Keywords.values()){
            if (keyword.getShortcut() == shortcut)
                return true;
        }
        return false;
    }

    /**
     * Verifie si la couleur entrée est valide et interpretable
     * @param color La couleur à vérifier
     * @return true si la couleur est valide
     */
    public static boolean isColor(String color) throws IsNotAValidColorException{
        for (Keywords keyword : Keywords.values()){
            if (keyword.getColor().equals(color))
                return true;
            if ("#000000".equals(color))
                return true;
        }
        return false;
    }




    /**
     * Renvoie l'instruction associée a la chaine de caractère entrée
     * @param word Le caractère à interpreter
     * @return L'instruction associée a la chaine de caractère entrée
     */
    public static Keywords wordToKeyword(String word){
        if (isWord(word))
            for (Keywords keywords : Keywords.values())
                if (keywords.name().equals(word))
                    return keywords;
        return null;
    }

    /**
     * Transforme un caractère en une instruction
     * @param shortcut Le caractère à interpreter
     * @return L'instruction associée au caractère entré
     */
    public static Keywords shortcutToKeyword(char shortcut){
        if (isShortcut(shortcut))
            for (Keywords keywords : Keywords.values())
                if (keywords.getShortcut() == shortcut)
                    return keywords;
        return null;
    }

    /**
     * Transforme une couleur en une instruction
     * @param color La couleur à interpreter
     * @return L'instruction associée à la couleur entrée
     */
    public static Keywords colorToKeyword(String color) throws IsNotAValidColorException {
        if (isColor(color))
            for (Keywords keywords : Keywords.values())
                if (keywords.getColor().equals(color))
                    return keywords;
        return null;
    }

    /**
     * Methode liant une instruction à une couleur
     * @param shortcut l'instruction à transformer en couleur
     * @return le code couleur associé à la couleur entrée
     */
    public static int keywordToColor(Keywords shortcut) throws IsNotACommandException {
        for (Keywords keywords : Keywords.values())
            if (keywords.equals(shortcut))
                return Integer.decode(keywords.getColor());
        throw new IsNotACommandException();

    }


    /**
     * Renvoie l'instruction asssociée à la chaine de caractère en entrée,
     * quelque soit son type (couleur...)
     *
     * @param anyType La chaine à convertir
     * @return l'instruction associée a la chaine en entrée
     */
    public static Keywords getKeywords(String anyType) throws IsNotAValidColorException {
        if (isColor(anyType))
            return colorToKeyword(anyType);
        else if(isShortcut(anyType.charAt(0)))
            return shortcutToKeyword(anyType.charAt(0));
        else if (isWord(anyType))
            return wordToKeyword(anyType);
        else return null;
    }

    /**
     * Affiche toutes les instructions en tant que liste de chaine de caractère
     *
     * @return La liste des instructions
     */
    public static List<String> displayWords(){
        ArrayList<String> words = new ArrayList<>();
        for (Keywords keywords: Keywords.values()){
            words.add(keywords.name());
        }
        return words;
    }

    /**
     * Renvoie le caractère associé à l'instruction en entrée
     *
     * @param keyword L'instruction à convertir en caractère
     * @return Le caractère associé à l'isntruction en entrée
     */
    public static char shortcutToString(Keywords keyword) throws IsNotACommandException {
        if (isKeyword(keyword)){
            return keyword.getShortcut();
        }
        else throw new IsNotACommandException();
    }

    public static boolean isKeyword(Keywords anyKeyword){
        for (Keywords keywords : Keywords.values()){
            if (keywords.equals(anyKeyword))
                return true;
        }
        return false;
    }


    public char getShortcut() { return  this.shortcut; }
    public String getColor() { return this.color; }


}
