package brainfuck.language.interpreter;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.function.Function;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @author jamatofu on 09/01/17.
 */
public class InterpreterMasterTest {
    InterpreterMaster interpreter;

    @Before
    public void setUp() throws Exception {
        Map<Integer, Function> functionMap = new HashMap<>();
        List<Keywords> keywordsList = new ArrayList<>();
        keywordsList.add(Keywords.INCR);

        Function function = new Function(keywordsList, false, "a");
        Function procedure = new Function(keywordsList, true, "b");

        functionMap.put(4, function);
        functionMap.put(5, procedure);



    }

    @Test
    public void interpreterProgram() throws Exception {
        Map<Integer, Keywords> keywordsMap = new HashMap<>();
        Map<Integer, Function> functionMap = new HashMap<>();

        keywordsMap.put(0, Keywords.INCR);
        keywordsMap.put(1, Keywords.JUMP);
        keywordsMap.put(2, Keywords.DECR);
        keywordsMap.put(3, Keywords.BACK);

        interpreter = new InterpreterMaster(true, keywordsMap, functionMap);
        interpreter.initializeInterpreters(null, null, null);
        interpreter.interpreterProgram();
    }

    @Test
    public void finishInterpreter() throws Exception {

    }

    @Test
    public void cursorIsInInterval() throws Exception {
        assertTrue(interpreter.cursorIsInInterval());
//        assertFalse(interpreter.cursorIsInInterval());
    }

    @Test
    public void retournePlace() throws Exception {

    }

    @Test
    public void countInstru() throws Exception {

    }

}