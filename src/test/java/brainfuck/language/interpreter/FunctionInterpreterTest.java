package brainfuck.language.interpreter;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongInputException;
import brainfuck.language.function.Function;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 05/01/17.
 */
public class FunctionInterpreterTest {
    FunctionInterpreter interpreter;
    Function function;

    @Before
    public void setUp() throws Exception {
        interpreter = new FunctionInterpreter(null, null);
    }

    @Test
    public void executionParametre() {
        List<Keywords> keywordsList = new ArrayList<>(Arrays.asList(Keywords.INCR));
        function = new Function(keywordsList, false, "Clem");
        function.setParametre("+++");

        try {
            interpreter.identifyAndExecuteInstruction(function);
        } catch (WrongInputException e) {
            System.out.println("Erreur dans FunctionInterpreterTest");
        }
        assertEquals(4, interpreter.getResult());

    }

    @Test(expected = WrongInputException.class)
    public void identifyAndExecuteInstruction() throws Exception {
        List<Keywords> keywordsList = new ArrayList<>(Arrays.asList(Keywords.JUMP, Keywords.BACK, Keywords.INCR, Keywords.DECR,
                Keywords.RIGHT, Keywords.LEFT));
        function = new Function(keywordsList, false, "Clem");

        interpreter.identifyAndExecuteInstruction(function);
        assertEquals(0, interpreter.getResult());

//         Test de IN
        String charInFile = "a";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(charInFile.getBytes());
        System.setIn(inputStream);

        keywordsList = new ArrayList<>();
        keywordsList.add(Keywords.IN);
        Function inFunction = new Function(keywordsList, false, "al");
        interpreter.identifyAndExecuteInstruction(inFunction);
        assertEquals(97, interpreter.getResult());

        // Dans le cas où le fichier contient 2 caractères
        charInFile = "aa";
        inputStream = new ByteArrayInputStream(charInFile.getBytes());
        System.setIn(inputStream);
        interpreter.identifyAndExecuteInstruction(inFunction);
        System.setIn(System.in);

        // Test de OUT
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        keywordsList.clear();
        keywordsList.add(Keywords.OUT);

        inFunction = new Function(keywordsList, false, "out");
        interpreter.identifyAndExecuteInstruction(inFunction);
        assertEquals(0, output.toString());

    }

    @Test
    public void boucleTest() throws Exception {
        List<Keywords> keywordsList = new ArrayList<>();
        keywordsList.add(Keywords.INCR);
        keywordsList.add(Keywords.INCR);
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.DECR);
        keywordsList.add(Keywords.BACK);
        Function testFunction = new Function(keywordsList, true, "al");

        interpreter.identifyAndExecuteInstruction(testFunction);
        assertEquals(0, interpreter.getResult());
    }

}