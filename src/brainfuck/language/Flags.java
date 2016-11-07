package brainfuck.language;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SERRANO Simon on 03/11/2016.
 */
public enum Flags {
    FileToRead("-p"),
    Rewrite("--rewrite"),
    In("-i"),
    Out("-o");

    static final Map<String, Flags> STRING_FLAGS_MAP;
    static {
        STRING_FLAGS_MAP = new HashMap<>();
        STRING_FLAGS_MAP.put("-p",FileToRead);
        STRING_FLAGS_MAP.put("--rewrite",Rewrite);
        STRING_FLAGS_MAP.put("-i",In);
        STRING_FLAGS_MAP.put("-o",Out);
    }


    Flags(String flag) { }

    public static boolean isFlag(String arg){
        if (STRING_FLAGS_MAP.containsKey(arg)) return true;
        return false;
    }

    public static void showFlags(){
        System.out.println("Available commands are :");
        for (Flags command: Flags.values()) {
            System.out.println(command);
        }
    }

    public static Flags toFlag(String arg){
        return STRING_FLAGS_MAP.get(arg);
    }



}
