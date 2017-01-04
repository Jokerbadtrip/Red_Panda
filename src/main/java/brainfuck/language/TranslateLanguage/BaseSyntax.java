package brainfuck.language.TranslateLanguage;

/**
 * Enum pour décrire la traduction
 *
 * Created by Red_Panda on 02/01/2017.
 */
public enum BaseSyntax {

    MainFile("public class Main{\n" +
            "\t public static void main(String[] args) {\n" +
            "\t\tBrainfuck brainfuck = new Brainfuck();\n" +
            "\t\tbrainfuck.launch();\n" +
            "\t}\n" +
            "}"),
    BrainfuckFile("public class Brainfuck{\n" +
            "\tshort[] memory;\n" +
            "\tint pointer;\n" +
            "\tpublic Brainfuck(){\n" +
            "\t\tmemory = new short[30000];\n" +
            "\t}\n" +
            "\n"),
    LaunchMethod("\tpublic void launch(){\n");


    String code;

    BaseSyntax(String code) {
        this.code=code;
    }
}
