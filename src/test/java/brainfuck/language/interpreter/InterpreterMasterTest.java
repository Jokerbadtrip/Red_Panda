package brainfuck.language.interpreter;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongInputException;
import brainfuck.language.function.Function;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jamatofu on 09/01/17.
 */
public class InterpreterMasterTest {
    InterpreterMaster interpreter;

    @Before
    public void setUp() throws Exception {
        interpreter = new InterpreterMaster(false, null, null);
    }

    @Test
    public void interpreterProgram() throws Exception {
        Map<Integer, Keywords> keywordsMap = new HashMap<>();
        Map<Integer, Function> functionMap = new HashMap<>();

        keywordsMap.put(0, Keywords.INCR);
        keywordsMap.put(0, Keywords.INCR);
        keywordsMap.put(1, Keywords.JUMP);
        keywordsMap.put(2, Keywords.DECR);
        keywordsMap.put(3, Keywords.BACK);

        interpreter.setFunctionMap(functionMap);
        interpreter.setKeywordsMap(keywordsMap);

        interpreter.initializeInterpreters(null, null, null);
        interpreter.interpreterProgram();
        assertEquals(0, interpreter.getCurrentValue());
    }

    @Test
    public void finishInterpreter() throws Exception {

    }

    @Test
    public void cursorIsInInterval() throws Exception {
        Map<Integer, Keywords> keywordsMap = new HashMap<>();
        Map<Integer, Function> functionMap = new HashMap<>();
        keywordsMap.put(0, Keywords.INCR);

        interpreter = new InterpreterMaster(true, keywordsMap, functionMap);
        interpreter.initializeInterpreters(null, null, null);

        assertTrue(interpreter.cursorIsInInterval());
        interpreter.interpreterProgram();
        assertFalse(interpreter.cursorIsInInterval());
    }

    @Test
    public void interpretationProcedure() throws WrongInputException {
        Map<Integer, Function> functionMap = new HashMap<>();
        Map<Integer, Keywords> keywordsMap = new HashMap<>();

        List<Keywords> keywordsList = new ArrayList<>();
        keywordsList.add(Keywords.INCR);

        Function procedure = new Function(keywordsList, true, "procedure");
        functionMap.put(0, procedure);

        interpreter.setKeywordsMap(keywordsMap);
        interpreter.setFunctionMap(functionMap);
        interpreter.initializeInterpreters(null, null, null);


        interpreter.interpreterProgram();
        assertEquals(1, interpreter.getCurrentValue());
    }

}