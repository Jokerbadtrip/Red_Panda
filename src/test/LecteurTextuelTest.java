package test;


import brainfuck.language.readers.LecteurTextuel;
import org.junit.Test;


/**
 * @author jamatofu on 13/11/16.
 */
public class LecteurTextuelTest {
    LecteurTextuel lecteurTextuel = new LecteurTextuel();


    String texteAAnalyser = "INCRRIGHT++++>>INCRINCRINCR-->INCRINCRDECRLEFT";

    @org.junit.Before
    public void setUp() throws Exception {
        LecteurTextuel lecteurTextuel = new LecteurTextuel();
    }

    @Test
    public void estShortcut() throws Exception {
      /* assertTrue(lecteurTextuel.estShortcut('+'));
        assertTrue(lecteurTextuel.estShortcut('-'));
        assertTrue(lecteurTextuel.estShortcut('<'));
        assertTrue(lecteurTextuel.estShortcut('>'));
        assertTrue(lecteurTextuel.estShortcut('.'));
        assertTrue(lecteurTextuel.estShortcut(','));
        assertTrue(lecteurTextuel.estShortcut('['));
        assertTrue(lecteurTextuel.estShortcut(']'));
        assertFalse(lecteurTextuel.estShortcut('*'));*/

    }

    @org.junit.Test
    public void estInstruction() throws Exception {

    }

    @org.junit.Test
    public void couperChaineCaractere() throws Exception {

    }

    @Test
    public void creeTableauCommandeTest() throws Exception{

    }

}