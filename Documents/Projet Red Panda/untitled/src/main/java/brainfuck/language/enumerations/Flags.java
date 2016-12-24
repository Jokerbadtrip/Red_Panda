package brainfuck.language.enumerations;

import brainfuck.language.exceptions.UnknownFlagsException;


/**
 * Enum permettant d'associer les differents arguments consoles a des instructions compréhensible par notre programme
 *
 * @author  Red_Panda
 */
public enum Flags {
    FileToRead("-p"),
    In("-i"),
    Out("-o"),
    Rewrite("--rewrite"),
    Check("--check"),
    Translate("--translate"),
    Trace("--trace");



private String flag;

    /**
     * Constructeur de Flag
     * @param flag Le flag que l'utilisateur peut entrer dans la console
     */
    Flags(String flag) {
        this.flag = flag;
    }

    /**
     * Verifie si l'argument en entrée est bien un flag ou non
     *
     * @param arg L'argument que l'utilisateur a entré dans la console
     * @return true si l'argument en entrée est un flag
     */
    public static boolean isFlag(String arg) throws UnknownFlagsException{
        if (!isFilePath(arg)) {
            for (Flags flags : Flags.values())
                if (flags.getFlag().equals(arg)) return true;
            throw new UnknownFlagsException(arg);
        }
        return false;
    }

    /**
     * Affiche tous les flags disponibles dans la console
     */
    public static String showFlags(){
        String printer = "Available commands are : ";
        for (Flags flags : Flags.values()) {
            printer += flags.getFlag()+" ";
        }
        return printer;
    }

    /**
     * Transforme la chaine de caractère en entrée en flag
     * @param arg Le flag entré par l'utilisateur dans la console
     * @return La flag lié a la chaine de caractère
     */
    public static Flags toFlag(String arg) throws UnknownFlagsException{
        if (!isFilePath(arg)) {
            for (Flags flags : Flags.values())
                if (arg.equals(flags.getFlag())) return flags;
            throw new UnknownFlagsException(arg);
        }
        return null;
    }

    public String getFlag() { return flag; }

    private static boolean isFilePath(String arg){
        if (arg.toLowerCase().matches("(?i).*bf") || arg.toLowerCase().matches("(?i).*bmp") || arg.toLowerCase().matches("(?i).*txt")) return true;
        else return false;
    }
}
