package brainfuck.language.enumerations;


import brainfuck.language.exceptions.IsNotACommandException;
import brainfuck.language.exceptions.IsNotAValidColorException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Un énum permettant d'associer n'importe quel type d'écriture (texte/image) à une instruction commune
 *
 * @author  Red_Panda
 */
public enum Keywords {

    INCR("INCR", "+", "#ffffff"),
    DECR("DECR", "-", "#4b0082"),
    LEFT("LEFT", "<", "#9400d3"),
    RIGHT("RIGHT", ">", "#0000ff"),
    OUT("OUT", ".", "#00ff00"),
    IN("IN", ",", "#ffff00"),
    JUMP("JUMP", "[", "#ff7f00"),
    BACK("BACK", "]", "#ff0000");

    private String word;
    private String shortcut;
    private String color;

    Keywords(String word, String shortcut, String color) {
        this.word = word;
        this.shortcut = shortcut;
        this.color = color;
    }

    //-----------------------------------------------------------------------------------------------------------------

    static final HashMap<String, Keywords> WORD_TO_KEYWORD_MAP; // Map that links words to keywords
    static{//Adding the links
        WORD_TO_KEYWORD_MAP = new HashMap<>();
        WORD_TO_KEYWORD_MAP.put("INCR", INCR);
        WORD_TO_KEYWORD_MAP.put("DECR", DECR);
        WORD_TO_KEYWORD_MAP.put("LEFT", LEFT);
        WORD_TO_KEYWORD_MAP.put("RIGHT", RIGHT);
        WORD_TO_KEYWORD_MAP.put("OUT", OUT);
        WORD_TO_KEYWORD_MAP.put("IN", IN);
        WORD_TO_KEYWORD_MAP.put("JUMP", JUMP);
        WORD_TO_KEYWORD_MAP.put("BACK", BACK);
    }


    static final HashMap<String, Keywords> SHORTCUT_TO_KEYWORD_MAP; // Map that links shortcuts to keywords
    static {//Adding the links
        SHORTCUT_TO_KEYWORD_MAP = new HashMap<>();
        SHORTCUT_TO_KEYWORD_MAP.put("+", INCR);
        SHORTCUT_TO_KEYWORD_MAP.put("-", DECR);
        SHORTCUT_TO_KEYWORD_MAP.put("<", LEFT);
        SHORTCUT_TO_KEYWORD_MAP.put(">", RIGHT);
        SHORTCUT_TO_KEYWORD_MAP.put(".", OUT);
        SHORTCUT_TO_KEYWORD_MAP.put(",", IN);
        SHORTCUT_TO_KEYWORD_MAP.put("[", JUMP);
        SHORTCUT_TO_KEYWORD_MAP.put("]", BACK);
    }

    static final HashMap<String, Keywords> COLOR_TO_KEYWORD_MAP; //Map that links colors to keywords
    static{//Adding the links
        COLOR_TO_KEYWORD_MAP = new HashMap<>();
        COLOR_TO_KEYWORD_MAP.put("#ffffff", INCR);
        COLOR_TO_KEYWORD_MAP.put("#4b0082", DECR);
        COLOR_TO_KEYWORD_MAP.put("#9400d3", LEFT);
        COLOR_TO_KEYWORD_MAP.put("#0000ff", RIGHT);
        COLOR_TO_KEYWORD_MAP.put("#00ff00", OUT);
        COLOR_TO_KEYWORD_MAP.put("#ffff00", IN);
        COLOR_TO_KEYWORD_MAP.put("#ff7f00", JUMP);
        COLOR_TO_KEYWORD_MAP.put("#ff0000", BACK);
    }


//---------------------------------------------------------------------------------------------------------------------


    /**
     * Verifie si la chaine de caractère entrée est valide et interpretable
     * @param word la chaine de caractère à vérifier
     * @return true si la chaine de caractère est valide
     * @throws IsNotACommandException
     */
    public static boolean isWord(String word) throws IsNotACommandException{
        if (WORD_TO_KEYWORD_MAP.containsKey(word)) return true;
        else throw new IsNotACommandException();
    }

    /**
     * Verifie si le caractère entré est valide et interpretable
     * @param shortcut tLe caractère à vérifier
     * @return true si le caractère est valide
     * @throws IsNotACommandException
     */
    public static boolean isShortcut(String shortcut) throws IsNotACommandException{
        if (SHORTCUT_TO_KEYWORD_MAP.containsKey(shortcut)) return true;
        else throw new IsNotACommandException();
    }

    /**
     * Verifie si la couleur entrée est valide et interpretable
     * @param color La couleur à vérifier
     * @return true si la couleur est valide
     * @throws IsNotAValidColorException
     */
    public static boolean isColor(String color) throws IsNotAValidColorException{
        if (COLOR_TO_KEYWORD_MAP.containsKey(color)) return true;
        else throw new IsNotAValidColorException();
    }




    /**
     * Renvoie l'instruction associée a la chaine de caractère entrée
     * @param word Le caractère à interpreter
     * @return L'instruction associée a la chaine de caractère entrée
     */
    public static Keywords wordToKeyword(String word){
        if (isWord(word)) return WORD_TO_KEYWORD_MAP.get(word);
        return null;
    }

    /**
     * Transforme un caractère en une instruction
     * @param shortcut Le caractère à interpreter
     * @return L'instruction associée au caractère entré
     */
    public static Keywords shortcutToKeyword(String shortcut){
        if (isShortcut(shortcut)) return SHORTCUT_TO_KEYWORD_MAP.get(shortcut);
        return null;
    }

    /**
     * Transforme une couleur en une instruction
     * @param color La couleur à interpreter
     * @return L'instruction associée à la couleur entrée
     */
    public static Keywords colorToKeyword(String color){
        if (isColor(color)) return COLOR_TO_KEYWORD_MAP.get(color);
        return null;
    }

    /**
     * Methode liant une instruction à une couleur
     * @param shortcut l'instruction à transformer en couleur
     * @return le code couleur associé à la couleur entrée
     */
    public static int keywordToColor(Keywords shortcut){
        for (Map.Entry<String, Keywords> entry : COLOR_TO_KEYWORD_MAP.entrySet()){
            if (shortcut.equals(entry.getValue())) return Integer.parseInt(entry.getKey());
        }
        return -1;

    }


    /**
     * Renvoie l'instruction asssociée à la chaine de caractère en entrée,
     * quelque soit son type (couleur...)
     *
     * @param any_type La chaine à convertir
     * @return l'instruction associée a la chaine en entrée
     */
    public static Keywords getKeywords(String any_type){
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
        for (Map.Entry<String , Keywords> word: WORD_TO_KEYWORD_MAP.entrySet()) {
            words.add(word.getKey());
        }
        return words;
    }

    /**
     * Renvoie le caractère associé à l'instruction en entrée
     *
     * @param keyword L'instruction à convertir en caractère
     * @return Le caractère associé à l'isntruction en entrée
     */
    public static String shortcutToString(Keywords keyword){
        for (Map.Entry<String , Keywords> shortcut: SHORTCUT_TO_KEYWORD_MAP.entrySet()) {
            if (shortcut.getValue().equals(keyword)) return shortcut.getKey();
        }
        return null;
    }


}
