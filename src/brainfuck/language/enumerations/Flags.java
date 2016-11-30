package brainfuck.language.enumerations;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum permettant d'associer les differents arguments consoles a des instructions compréhensible par notre programme
 *
 * @author  Red_Panda
 */
public enum Flags {
    FileToRead("-p"),
    Rewrite("--rewrite"),
    In("-i"),
    Out("-o"),
    Check("--check"),
    Translate("--translate"),
    Trace("--trace");



    //Map to link enums and what the user writes in the console
    static final Map<String, Flags> STRING_FLAGS_MAP;
    static {
        STRING_FLAGS_MAP = new HashMap<>();
        STRING_FLAGS_MAP.put("-p",FileToRead);
        STRING_FLAGS_MAP.put("--rewrite",Rewrite);
        STRING_FLAGS_MAP.put("-i",In);
        STRING_FLAGS_MAP.put("-o",Out);
        STRING_FLAGS_MAP.put("--check",Check);
        STRING_FLAGS_MAP.put("--translate",Translate);
        STRING_FLAGS_MAP.put("--trace",Trace);

    }


    /**
     * Constructeur de Flag
     * @param flag Le flag que l'utilisateur peut entrer dans la console
     */
    Flags(String flag) { }

    /**
     * Verifie si l'argument en entrée est bien un flag ou non
     *
     * @param arg L'argument que l'utilisateur a entré dans la console
     * @return true si l'argument en entrée est un flag
     */
    public static boolean isFlag(String arg){
        if (STRING_FLAGS_MAP.containsKey(arg)) return true;
        return false;
    }

    /**
     * Affiche tous les flags disponibles dans la console
     */
    public static void showFlags(){
        System.out.println("Available commands are :");
        for (Map.Entry<String, Flags> flag: STRING_FLAGS_MAP.entrySet()) {
            System.out.println(flag.getKey());
        }
    }

    /**
     * Transforme la chaine de caractère en entrée en flag
     * @param arg Le flag entré par l'utilisateur dans la console
     * @return La flag lié a la chaine de caractère
     */
    public static Flags toFlag(String arg){
        return STRING_FLAGS_MAP.get(arg);
    }
}
