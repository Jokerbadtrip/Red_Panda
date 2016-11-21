package brainfuck.language.Enumerations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SERRANO Simon on 03/11/2016.
 */
public enum Flags {
    FileToRead("-p"),
    Rewrite("--rewrite"),
    In("-i"),
    Out("-o"),
    Check("--check"),
    Translate("--translate");



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

    }


    /**
     * Constructor for flags
     * @param flag the flag the user can write in the console
     */
    Flags(String flag) { }

    /**
     * Checks if the given string is an available flag
     * @param arg argument given in the console by the user
     * @return true if the given string is a flag
     */
    public static boolean isFlag(String arg){
        if (STRING_FLAGS_MAP.containsKey(arg)) return true;
        return false;
    }

    /**
     * Prints all the flags in the console
     */
    public static void showFlags(){
        System.out.println("Available commands are :");
        for (Map.Entry<String, Flags> flag: STRING_FLAGS_MAP.entrySet()) {
            System.out.println(flag.getKey());
        }
    }

    /**
     * Transforms a string into a flag
     * @param arg the flag given by the user in to console
     * @return the flag linked with its string
     */
    public static Flags toFlag(String arg){
        return STRING_FLAGS_MAP.get(arg);
    }



}
