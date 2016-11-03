import brainfuck.language.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        interpreter.outMethod();
        interpreter.appellerMemoire();
    }
}
