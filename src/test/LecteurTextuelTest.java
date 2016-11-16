package test;

import brainfuck.language.LecteurTextuel;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

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
        assertTrue(lecteurTextuel.estShortcut('-'));
        assertTrue(lecteurTextuel.estShortcut('<'));
        assertTrue(lecteurTextuel.estShortcut('>'));
        assertTrue(lecteurTextuel.estShortcut('.'));
        assertTrue(lecteurTextuel.estShortcut(','));
        assertTrue(lecteurTextuel.estShortcut('['));
        assertTrue(lecteurTextuel.estShortcut(']'));
        assertFalse(lecteurTextuel.estShortcut('*'));

    }

    @org.junit.Test
    public void estInstruction() throws Exception {

    }

    @org.junit.Test
    public void couperChaineCaractere() throws Exception {

    }

    @org.junit.Test
    public void creeTableauCommande() throws Exception {
        String[] a = { "CLEME", "CLEM", "CLE", "CL"};
        assertArrayEquals(a ,lecteurTextuel.couperChaineCaractere("CLEME"));

        String[] b = { "CLEM", "CLE", "CL"};
        assertArrayEquals(b ,lecteurTextuel.couperChaineCaractere("CLEM"));

        String[] c = { "CLE", "CL"};
        assertArrayEquals(c ,lecteurTextuel.couperChaineCaractere("CLE"));

        String[] d = { "CL"};
        assertArrayEquals(d ,lecteurTextuel.couperChaineCaractere("CL"));

        String[] e = { };
        assertArrayEquals(e, lecteurTextuel.couperChaineCaractere("C"));
    }

    @org.junit.Test
    public void removeCommentary() throws Exception {
        lecteurTextuel.setTexteAAnalyser("+++#Bonjour#\n");
        lecteurTextuel.removeCommentary();
        assertEquals("+++", lecteurTextuel.getTexteAAnalyser());

        lecteurTextuel.setTexteAAnalyser("+++#hello\nazerza#poizua\n985");
        lecteurTextuel.removeCommentary();
        System.out.println(lecteurTextuel.getTexteAAnalyser());
    }

}