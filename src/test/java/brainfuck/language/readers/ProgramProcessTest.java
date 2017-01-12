package brainfuck.language.readers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 01/01/17.
 */
public class ProgramProcessTest {
    ProgramProcess process;

    @Before
    public void setUp() {
        process = new ProgramProcess("@BON +++\n" +
                "void fac --\n" +
                "\n" +
                "BON #Bien\n" +
                "fac\n");
    }

    @Test
    public void transform() throws Exception {
        assertEquals("+++\n" +
                "fac\n", process.transform());
    }

    @Test
    public void removeCommentary() throws Exception {
        assertEquals("@BON +++\n" +
                "void fac --\n" +
                "\n" +
                "BON \n" +
                "fac\n", process.removeCommentary());
    }

}