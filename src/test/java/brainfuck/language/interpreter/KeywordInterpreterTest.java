package brainfuck.language.interpreter;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.ValueOutOfBoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Enzo on 06/01/2017.
 */
public class KeywordInterpreterTest {

    KeywordInterpreter keywordInterpreter = new KeywordInterpreter("IN_file_test.txt","OUT_file_test.txt");


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