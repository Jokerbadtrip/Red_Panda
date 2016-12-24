package brainfuck.language;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.readers.LecteurTextuel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author jamatofu on 07/12/16.
 */
public class LecteurTextuelTest {
    LecteurTextuel lecteurTextuel;
    @Before
    public void setUp() throws Exception {
        lecteurTextuel = new LecteurTextuel();
    }

    @Test
    public void estInstruction() throws Exception {
        lecteurTextuel.setProgramme("INCRINDECRRIGHTLEFTOUTJUMPBACKMAMAN");
        assertEquals(Keywords.INCR, lecteurTextuel.estInstruction());
        assertEquals(Keywords.IN, lecteurTextuel.estInstruction());
        assertEquals(Keywords.DECR, lecteurTextuel.estInstruction());
        assertEquals(Keywords.RIGHT, lecteurTextuel.estInstruction());
        assertEquals(Keywords.LEFT, lecteurTextuel.estInstruction());
        assertEquals(Keywords.OUT, lecteurTextuel.estInstruction());
        assertEquals(Keywords.JUMP, lecteurTextuel.estInstruction());
        assertEquals(Keywords.BACK, lecteurTextuel.estInstruction());
        assertEquals(null, lecteurTextuel.estInstruction());
    }

    @Test
    public void creeTableauCommande() throws Exception {
        lecteurTextuel.setProgramme("INCR+DECR[]BACKIN.OUT,JUMP<>LEFTRIGHT-");
        ArrayList<Keywords> listeTest = new ArrayList<>();
        listeTest.add(Keywords.INCR);
        listeTest.add(Keywords.INCR);
        listeTest.add(Keywords.DECR);
        listeTest.add(Keywords.JUMP);
        listeTest.add(Keywords.BACK);
        listeTest.add(Keywords.BACK);
        listeTest.add(Keywords.IN);
        listeTest.add(Keywords.OUT);
        listeTest.add(Keywords.OUT);
        listeTest.add(Keywords.IN);
        listeTest.add(Keywords.JUMP);
        listeTest.add(Keywords.LEFT);
        listeTest.add(Keywords.RIGHT);
        listeTest.add(Keywords.LEFT);
        listeTest.add(Keywords.RIGHT);
        listeTest.add(Keywords.DECR);
        assertEquals(listeTest, lecteurTextuel.creeTableauCommande());
    }

    @Test
    public void removeCommentary() throws Exception {
        assertEquals("INCRINCRALALA", lecteurTextuel.removeCommentary("INCRINCR#bonjour tout le monde!\nALALA#####plusieurs dieses\n"));
        assertEquals("INCRINCR  ALALA", lecteurTextuel.removeCommentary("INCRINCR  #bonjour tout le monde!\nALALA#####plusieurs dieses\n"));

    }

    @Test
    public void setTexteAAnalyser() throws Exception {
        String texte = "@JEAN +++\nDECR++  #comment ça va?\n#bien et toi?\n<<JEANJEAN";

        lecteurTextuel.setTexteAAnalyser(texte);
        assertEquals("DECR++<<++++++", lecteurTextuel.getTexteAAnalyser());

    }

}