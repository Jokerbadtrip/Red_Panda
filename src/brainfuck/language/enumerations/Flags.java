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
        for (Flags flags : Flags.values())
            if (flags.getFlag().equals(arg)) return true;
        throw new UnknownFlagsException(arg);
    }

    /**
     * Affiche tous les flags disponibles dans la console
     */
    public static void showFlags(){
        System.out.println("Available commands are :");
        for (Flags flags : Flags.values()) {
            System.out.println(flags.getFlag());
        }
    }

    /**
     * Transforme la chaine de caractère en entrée en flag
     * @param arg Le flag entré par l'utilisateur dans la console
     * @return La flag lié a la chaine de caractère
     */
    public static Flags toFlag(String arg) throws UnknownFlagsException{
        for (Flags flags : Flags.values())
            if (arg.equals(flags.getFlag())) return flags;
        throw new UnknownFlagsException(arg);
    }

    public String getFlag() { return flag; }
}
