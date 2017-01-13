package brainfuck.language.flag;

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
    TRACE("--trace", false),
    TRANSLATE_IN_JAVA("--java", false);

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
     * Permet de retourner un objet Flag en fonction d'un String
     * @param arg le string à "convertir" en Flag
     * @return le flag correspondant SINON rien
     */
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
    public boolean NeedAFilePath() {
        return needAFilePath;
    }
}
