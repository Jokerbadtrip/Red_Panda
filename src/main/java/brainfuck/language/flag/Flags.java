package brainfuck.language.flag;

import brainfuck.language.exceptions.UnknownFlagsException;


/**
 * Enum permettant d'associer les differents arguments consoles a des instructions compréhensible par notre programme
 *
 * @author  Red_Panda
 */
public enum Flags {
    FILE_TO_READ("-p", true),
    IN("-i", true ),
    OUT("-o", true ),
    REWRITE("--rewrite", false),
    CHECK("--check", false),
    TRANSLATE("--translate", false),
    TRACE("--trace", false);



    private String flag;
    private boolean needAFilePath;

    /**
     * Constructeur de Flag
     * @param flag Le flag que l'utilisateur peut entrer dans la console
     */
    Flags(String flag, boolean needAFilePath) {
        this.flag = flag;
        this.needAFilePath = needAFilePath;
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
                if (flags.getFlag().equals(arg))
                    return true;
            throw new UnknownFlagsException(arg);
        }
        return false;
    }

    /**
     * Affiche tous les flags disponibles dans la console
     */
    public static String showFlags(){
        StringBuilder printer = new StringBuilder();
        printer.append("Available commands are : ");

        for (Flags flags : Flags.values()) {
            printer.append(flags.getFlag());
            printer.append(" ");
        }
        return printer.toString();
    }

    /**
     * Transforme la chaine de caractère en entrée en flag
     * @param arg Le flag entré par l'utilisateur dans la console
     * @return La flag lié a la chaine de caractère
     */
    public static Flags toFlag(String arg) throws UnknownFlagsException{
        if (!isFilePath(arg)) {
            for (Flags flags : Flags.values())
                if (arg.equals(flags.getFlag()))
                    return flags;
            throw new UnknownFlagsException(arg);
        }
        return null;
    }

    private static boolean isFilePath(String arg){
        return arg.toLowerCase().matches("(?i).*bf") || arg.toLowerCase().matches("(?i).*bmp") || arg.toLowerCase().matches("(?i).*txt");
    }

    public static Flags fromFlagToEnum(String arg) {
        for(Flags flags : Flags.values()) {
            if(flags.getFlag().equals(arg))
                return flags;
        }
        return null;
    }

    /**
     * Accesseur de l'attribut flag
     * @return flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Accesseur de l'attribut needAFilePath
     * @return needAFilePath
     */
    public boolean isNeedAFilePath() {
        return needAFilePath;
    }
}
