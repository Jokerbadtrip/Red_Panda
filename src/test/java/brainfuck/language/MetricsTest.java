package brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 08/01/17.
 */
public class MetricsTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        Metrics.PROC_SIZE = 4;
        Metrics.DATA_MOVE = 3;
        Metrics.DATA_WRITE = 2;
        Metrics.DATA_READ = 2;
        Metrics.EXEC_MOVE = 2;
        Metrics.EXEC_TIME = 0;
        Metrics.START_TIME = 200L;

        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void displayMetrics() throws Exception {
        String out = "Taille du programme : 4\n" +
                "Temps d'exécution : 0ms\n" +
                "Nombre de fois que le pointeur d'exécution a bougé : 2\n" +
                "Nombre de fois que le pointeur de mémoire a bougé : 3\n" +
                "Nombre de fois que la mémoire a été modifiée : 2\n" +
                "Nombre de fois que la mémoire a été lue : 2\n";
        Metrics.displayMetrics();
        assertEquals(out, outputStream.toString());
    }

    @Test
    public void execTime() throws Exception {
        Metrics.execTime(300L);
        assertEquals(100L, Metrics.EXEC_TIME);
    }


    @After
    public void cleanUp() {
            System.setOut(null);
    }
}