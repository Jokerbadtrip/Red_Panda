package brainfuck.language.interpreter;

import brainfuck.language.exceptions.WrongInputException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 13/01/17.
 */
public class InterpreterTest {
    InterpreterForTest interpreter;
    String in;
    String out;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        in = temporaryFolder.getRoot().getAbsolutePath() + "/in.bf";
        out = temporaryFolder.getRoot().getAbsolutePath() + "/out.bf";

        File inFile = new File(in);
        File outFile = new File(out);
        inFile.createNewFile();
        outFile.createNewFile();

        interpreter = new InterpreterForTest(in, out, true);
    }

    @Test
    public void inWithFilePath() throws WrongInputException {
        try(FileWriter fw = new FileWriter(in)) {
            fw.write("a");
        } catch (IOException e) {
            System.out.println("In file not found");
        }

        interpreter.inMethod();
        assertEquals(97, interpreter.getCurrentValue());
    }

    @Test
    public void inWithoutFilePath() throws WrongInputException {
        interpreter.setInfilepath(null);
        InputStream inputStream = new ByteArrayInputStream("a".getBytes());
        System.setIn(inputStream);
        interpreter.inMethod();

        assertEquals(97, interpreter.getCurrentValue());
    }

    @Test(expected = WrongInputException.class)
    public void inWithWrongInput() throws WrongInputException {
        try(FileWriter fw = new FileWriter(in)) {
            fw.write("ab");
        } catch (IOException e) {
            System.out.println("In file not found");
        }

        interpreter.inMethod();
    }

    @Test
    public void outWithFilePath() throws WrongInputException {
        interpreter.setCurrentValue((short) 97);
        interpreter.outMethod();
        int sortie = -1;

        try(FileReader fr = new FileReader(out)) {
            sortie = fr.read();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("Out file not found");
        }

        assertEquals(97, sortie);
    }

    @Test
    public void outWithoutFilePath() throws WrongInputException {
        interpreter.setOutfilepath(null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        interpreter.setCurrentValue((short) 97);
        interpreter.outMethod();

        assertEquals("a", outputStream.toString());
    }

    @Test
    public void jump() {
        Map<Integer, Integer> linkedBracket = new HashMap<>();
        linkedBracket.put(0, 1);
        linkedBracket.put(1, 0);

        interpreter.setLinkedBracket(linkedBracket);
        interpreter.jump();
        assertEquals(1, interpreter.getCursor());
    }

    @Test
    public void back() {
        Map<Integer, Integer> linkedBracket = new HashMap<>();
        linkedBracket.put(0, 1);
        linkedBracket.put(1, 0);

        interpreter.setLinkedBracket(linkedBracket);
        interpreter.setCursor(1);
        interpreter.setCurrentValue((short) 1);
        interpreter.back();
        assertEquals(0, interpreter.getCursor());
    }


    public class InterpreterForTest extends Interpreter{
        // sert à tester les fonctions de Interpreter
        public InterpreterForTest(String in, String out, boolean memory) {
            super(in, out, memory);
        }
    }
}