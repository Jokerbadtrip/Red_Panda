package test;

import brainfuck.language.LecteurTextuel;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jamatofu on 13/11/16.
 */
public class LecteurTextuelTest {
    LecteurTextuel lecteurTextuel = new LecteurTextuel();

    @org.junit.Before
    public void setUp() throws Exception {
        LecteurTextuel lecteurTextuel = new LecteurTextuel();
    }

    @Test
    public void estShortcut() throws Exception {
            assertTrue(lecteurTextuel.estShortcut('+'));

    }

    @org.junit.Test
    public void estInstruction() throws Exception {

    }

    @org.junit.Test
    public void couperChaineCaractere() throws Exception {

    }

    @org.junit.Test
    public void creeTableauCommande() throws Exception {

    }

    @org.junit.Test
    public void removeCommentary() throws Exception {

    }

}