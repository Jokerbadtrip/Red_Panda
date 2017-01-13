package brainfuck.language.interpreter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

/**
 * @author jamatofu on 13/01/17.
 */
public class InterpreterTest {
    InterpreterForTest interpreter;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        String in = temporaryFolder.getRoot().getAbsolutePath() + "/in.bf";
        String out = temporaryFolder.getRoot().getAbsolutePath() + "/out.bf";

        File inFile = new File(in);
        File outFile = new File(out);
        inFile.createNewFile();
        outFile.createNewFile();
        interpreter = new InterpreterForTest(in, out, true);
    }

    @Test
    public void in() {

    }


    public class InterpreterForTest extends Interpreter{
        // sert à tester les fonctions de Interpreter
        public InterpreterForTest(String in, String out, boolean memory) {
            super(in, out, memory);
        }
    }
}