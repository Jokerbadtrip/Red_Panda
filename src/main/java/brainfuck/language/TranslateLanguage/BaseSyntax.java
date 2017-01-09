package brainfuck.language.TranslateLanguage;

/**
 * Enum pour décrire la traduction
 *
 * Created by Red_Panda on 02/01/2017.
 */
public enum BaseSyntax {

    MainFile("public class Main{\n" +
            "   public static void main(String[] args) {\n" +
            "       Brainfuck brainfuck = new Brainfuck();\n" +
            "       brainfuck.launch();\n" +
            "   }\n" +
            "}"),
    BrainfuckFile("public class Brainfuck{\n" +
            "   short[] memory;\n" +
            "   public Brainfuck(){\n" +
            "       memory = new short[30000];\n" +
            "   }\n" +
            "\n"),


    LaunchMethod("  public void launch(){\n"),
    CloseFile(" }\n" +
            "}");



    String code;

    BaseSyntax(String code) {
        this.code=code;
    }
}
