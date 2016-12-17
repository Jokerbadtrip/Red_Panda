package brainfuck.language.enumerations;


import brainfuck.language.exceptions.IsNotACommandException;
import brainfuck.language.exceptions.IsNotAValidColorException;




import java.util.ArrayList;


/**
 * Un énum permettant d'associer n'importe quel type d'écriture (texte/image) à une instruction commune
 *
 * @author  Red_Panda
 */
public enum Keywords {

    INCR("+", "#ffffff"),
    DECR("-", "#4b0082"),
    LEFT("<", "#9400d3"),
    RIGHT(">", "#0000ff"),
    OUT( ".", "#00ff00"),
    IN(",", "#ffff00"),
    JUMP( "[", "#ff7f00"),
    BACK( "]", "#ff0000");


    private String shortcut;
    private String color;

    Keywords(String shortcut, String color) {

        this.shortcut = shortcut;
        this.color = color;
    }




    /**
     * Verifie si la chaine de caractère entrée est valide et interpretable
     * @param word la chaine de caractère à vérifier
     * @return true si la chaine de caractère est valide
     * @throws IsNotACommandException
     */
    public static boolean isWord(String word) {
        for (Keywords keyword: Keywords.values()) {
            if (keyword.name().equals(word)) return true;
        }
        return false;
    }

    /**
     * Verifie si le caractère entré est valide et interpretable
     * @param shortcut tLe caractère à vérifier
     * @return true si le caractère est valide
     */
    public static boolean isShortcut(String shortcut) {
        for (Keywords keyword : Keywords.values()){
            if (keyword.getShortcut().equals(shortcut)) return true;
        }
        return false;
    }

    /**
     * Verifie si la couleur entrée est valide et interpretable
     * @param color La couleur à vérifier
     * @return true si la couleur est valide
     * @throws IsNotAValidColorException
     */
    public static boolean isColor(String color) throws IsNotAValidColorException{
        for (Keywords keyword : Keywords.values()){
            if (keyword.getColor().equals(color)) return true;
            if (color.equals("#000000")) return true;
        }
        throw new IsNotAValidColorException();
    }




    /**
     * Renvoie l'instruction associée a la chaine de caractère entrée
     * @param word Le caractère à interpreter
     * @return L'instruction associée a la chaine de caractère entrée
     */
    public static Keywords wordToKeyword(String word){
        if (isWord(word))
            for (Keywords keywords : Keywords.values())
                if (keywords.name().equals(word)) return keywords;
        return null;
    }

    /**
     * Transforme un caractère en une instruction
     * @param shortcut Le caractère à interpreter
     * @return L'instruction associée au caractère entré
     */
    public static Keywords shortcutToKeyword(String shortcut){
        if (isShortcut(shortcut))
            for (Keywords keywords : Keywords.values())
                if (keywords.getShortcut().equals(shortcut)) return keywords;
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
                if (keywords.getColor().equals(color)) return keywords;
        return null;
    }

    /**
     * Methode liant une instruction à une couleur
     * @param shortcut l'instruction à transformer en couleur
     * @return le code couleur associé à la couleur entrée
     */
    public static int keywordToColor(Keywords shortcut) throws IsNotACommandException {
        for (Keywords keywords : Keywords.values())
            if (keywords.equals(shortcut)) return Integer.decode(keywords.getColor());
        throw new IsNotACommandException();

    }


    /**
     * Renvoie l'instruction asssociée à la chaine de caractère en entrée,
     * quelque soit son type (couleur...)
     *
     * @param any_type La chaine à convertir
     * @return l'instruction associée a la chaine en entrée
     */
    public static Keywords getKeywords(String any_type) throws IsNotAValidColorException {
        if (isColor(any_type)) return colorToKeyword(any_type);
        else if(isShortcut(any_type)) return shortcutToKeyword(any_type);
        else if (isWord(any_type)) return wordToKeyword(any_type);
        else return null;
    }

    /**
     * Affiche toutes les instructions en tant que liste de chaine de caractère
     *
     * @return La liste des instructions
     */
    public static ArrayList<String> displayWords(){
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
    public static String shortcutToString(Keywords keyword) throws IsNotACommandException {
        if (isKeyword(keyword)){
            return keyword.getShortcut();
        }
        else throw new IsNotACommandException();
    }

    public static boolean isKeyword(Keywords any_keyword){
        for (Keywords keywords : Keywords.values()){
            if (keywords.equals(any_keyword)) return true;
        }
        return false;
    }


    public String getShortcut() { return  this.shortcut; }
    public String getColor() { return this.color; }


}
