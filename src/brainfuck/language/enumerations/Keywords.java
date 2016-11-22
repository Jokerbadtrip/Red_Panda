package brainfuck.language.enumerations;




import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serrano Simon on 03/11/2016.
 */
public enum Keywords {
    INCR("+"),
    DECR("-"),
    LEFT("<"),
    RIGHT(">"),
    OUT("."),
    IN(","),
    JUMP("["),
    BACK("]");


    Keywords(String shortcut) { }


    //Map to link the keywords and the code written in the .bf file
    static final Map<String, Keywords> STRING_KEYWORDS_MAP;
    static {
        STRING_KEYWORDS_MAP = new HashMap<>();
        STRING_KEYWORDS_MAP.put("+",INCR);
        STRING_KEYWORDS_MAP.put("-",DECR);
        STRING_KEYWORDS_MAP.put("<",LEFT);
        STRING_KEYWORDS_MAP.put(">",RIGHT);
        STRING_KEYWORDS_MAP.put(".",OUT);
        STRING_KEYWORDS_MAP.put(",",IN);
        STRING_KEYWORDS_MAP.put("[",JUMP);
        STRING_KEYWORDS_MAP.put("]",BACK);
    }

    /**
     * transforms a String into a keyword
     * @param keyword the given keyword
     * @return the associated keyword
     */
    public static Keywords toKeyword(String keyword){
        return STRING_KEYWORDS_MAP.get(keyword);
    }



}
