package brainfuck.language;


import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.RewriteException;
import brainfuck.language.flag.Flags;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Red_Panda on 14/12/16.
 */
public class MotorTest {
    Motor motor;

    @Before
    public void setUp() {
        motor = new Motor(null);
    }

    @Test
    public void executeFlag_CHECK() throws RewriteException {
        Map<Flags, Boolean> flagsMap = new HashMap<>();
        flagsMap.put(Flags.CHECK, true);
        flagsMap.put(Flags.REWRITE, false);
        flagsMap.put(Flags.TRANSLATE, false);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<Keywords> keywordsList = new ArrayList<>();
        keywordsList.add(Keywords.JUMP);

        motor.executeFlag(keywordsList, flagsMap);
        assertEquals("Program is not well-balanced.\n\n", outputStream.toString());

        outputStream.reset();
        keywordsList.add(Keywords.BACK);
        motor.executeFlag(keywordsList, flagsMap);
        assertEquals("Program is well balanced.\n\n", outputStream.toString());
    }
}
