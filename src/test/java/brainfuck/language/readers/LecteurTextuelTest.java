package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 07/12/16.
 */
public class LecteurTextuelTest {
    LecteurTextuel lecteurTextuel;

    @Test
    public void creeTableauCommande() throws Exception {
        lecteurTextuel = new LecteurTextuel("INCR");
        ArrayList<Keywords> listeTest = new ArrayList<>();
        listeTest.add(Keywords.INCR);
        assertEquals(listeTest, lecteurTextuel.creeTableauCommande());

        lecteurTextuel = new LecteurTextuel("++--");
        listeTest.add(Keywords.INCR);
        listeTest.add(Keywords.DECR);
        listeTest.add(Keywords.DECR);
        assertEquals(listeTest, lecteurTextuel.creeTableauCommande());
    }
}