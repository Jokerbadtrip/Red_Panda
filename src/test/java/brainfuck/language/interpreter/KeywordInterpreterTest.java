package brainfuck.language.interpreter;

import brainfuck.language.Metrics;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.ValueOutOfBoundException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by Enzo on 06/01/2017.
 */
public class KeywordInterpreterTest {

    KeywordInterpreter keywordInterpreter = new KeywordInterpreter(null, "null");

    @Before
    public void setUp() {
        Metrics.PROC_SIZE = 0;
        Metrics.EXEC_TIME = 0;
        Metrics.START_TIME = 0;
        Metrics.EXEC_MOVE = 0;
        Metrics.DATA_MOVE = 0;
        Metrics.DATA_WRITE = 0;
        Metrics.DATA_READ = 0;
    }


    @Test (expected = ValueOutOfBoundException.class)
    public void identifyAndExecuteInstruction() throws Exception {

        // test INCR/DECR/LEFT/RIGHT

        keywordInterpreter.identifyAndExecuteInstruction(Keywords.INCR);
        assertEquals(1, keywordInterpreter.getCurrentValue());
        assertEquals(0, keywordInterpreter.getCurrentPointer());

        keywordInterpreter.identifyAndExecuteInstruction(Keywords.RIGHT);
        assertEquals(0, keywordInterpreter.getCurrentValue());
        assertEquals(1, keywordInterpreter.getCurrentPointer());

        keywordInterpreter.identifyAndExecuteInstruction(Keywords.LEFT);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.DECR);
        assertEquals(0, keywordInterpreter.getCurrentValue());
        assertEquals(0, keywordInterpreter.getCurrentPointer());

        // OUT
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream('a');
        System.setOut(new PrintStream(outputStream));
        keywordInterpreter.setCurrentValue((short) 97);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.OUT);

        assertEquals(97, keywordInterpreter.getCurrentValue());
        assertEquals(1, Metrics.DATA_READ);
        System.setOut(System.out);

        // IN
        ByteArrayInputStream inputStream = new ByteArrayInputStream("a".getBytes());
        System.setIn(inputStream);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.IN);
        assertEquals(97, keywordInterpreter.getCurrentValue());
        assertEquals(5, Metrics.DATA_WRITE);

        // JUMP BACK
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.JUMP);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.BACK);
        assertEquals(3, Metrics.DATA_READ);

        // exception
        keywordInterpreter.setCurrentValue((short) 255);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.INCR);
    }

    @Test
    public void iniATracer() throws Exception {
        assertTrue(keywordInterpreter.getTrace() == null);
        keywordInterpreter.iniATracer("fichier_test_trace");
        assertTrue(keywordInterpreter.getTrace() != null);
    }

    @Test
    public void endTrace() throws Exception {
        keywordInterpreter.endTrace();
        assertNull(keywordInterpreter.getTrace());
    }

    @Test
    public void writeStateOfMemory() throws Exception {
        keywordInterpreter.setCurrentValue((short) 200);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.RIGHT);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.INCR);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.RIGHT);
        keywordInterpreter.setCurrentValue((short) 100);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.RIGHT);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.INCR);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.INCR);
        keywordInterpreter.identifyAndExecuteInstruction(Keywords.INCR);

        assertEquals("C0: 200 C1: 1 C2: 100 C3: 3 ", keywordInterpreter.writeStateOfMemory());
    }
}