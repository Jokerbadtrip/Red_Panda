package brainfuck.language.interpreter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 05/01/17.
 */
public class TraceTest {
    Trace trace;

    @Rule
    public TemporaryFolder  temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        trace = new Trace("test");
    }

    @Test
    public void tracerUpdate() throws Exception {
        temporaryFolder.create();
        trace.tracerUpdate(0, 0 , "A");
        String textExpected = "Execution step number = 1, execution pointer position : 1"
                + ", data pointer position : 0, Snapshot : A";

        trace.end();

        File file = trace.getNomFichier();

        try(FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            assertEquals(textExpected, bufferedReader.readLine());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Test
    public void end() throws Exception {
        trace.end();
    }

}