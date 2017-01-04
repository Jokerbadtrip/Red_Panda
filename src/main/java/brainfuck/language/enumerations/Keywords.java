package brainfuck.language.enumerations;


import brainfuck.language.exceptions.IsNotAValidColorException;
import brainfuck.language.exceptions.KeywordsConversionException;


/**
 * Un énum permettant d'associer n'importe quel type d'écriture (texte/image) à une instruction commune
 *
 * @author  Red_Panda
 */
public enum Keywords {

    INCR('+', "#ffffff", "memory[pointer]+="),
    DECR('-', "#4b0082", "memory[pointer]-="),
    LEFT('<', "#9400d3", "pointer--;\n"),
    RIGHT('>', "#0000ff", "pointer++;\n"),
    OUT('.', "#00ff00", "out(String filename);\n"),
    IN(',', "#ffff00", "in(String filename);\n"),
    JUMP('[', "#ff7f00", ""),
    BACK(']', "#ff0000", "");


    private char shortcut;
    private String color;
    private String correspondingCode;

    Keywords(char shortcut, String color, String correspondingCode) {
        this.shortcut = shortcut;
        this.color = color;
        this.correspondingCode = correspondingCode;
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
            if (keyword.getColor().equals(color) || "#000000".equals(color))
                return true;
        }
        return false;
    }

    /**
     * Transforme un caractère en une instruction
     * @param shortcut Le caractère à interpreter
     * @return L'instruction associée au caractère entré
     */
    public static Keywords shortcutToKeyword(char shortcut) throws KeywordsConversionException{
        if (!isShortcut(shortcut)){
            throw new KeywordsConversionException();
        }
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
        if (!isColor(color)){
            throw new KeywordsConversionException();
        }
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
    public static int keywordToColor(Keywords shortcut) {
        for (Keywords keywords : Keywords.values())
            if (keywords.equals(shortcut))
                return Integer.decode(keywords.getColor());
        return 0;
    }

    public char getShortcut() { return  this.shortcut; }

    public String getColor() { return this.color; }

}
