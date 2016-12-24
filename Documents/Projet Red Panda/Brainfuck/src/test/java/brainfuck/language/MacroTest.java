package brainfuck.language;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author jamatofu on 07/12/16.
 */
public class MacroTest {
    Macro macro;
    @Before
    public void setUp() throws Exception {
        macro = new Macro("@Test +++\n" +
                          "@CLEMENT Test\n" +
                          "@MULTI_DECR() -\n" +
                          "INCR++--<LEFTJUMP");
    }

    @Test
    public void readMacro() throws Exception {

    }

    @Test
    public void findMacro() throws Exception {

    }

    @Test
    public void decomposerMacro() throws Exception {

    }

    @Test
    public void isParameterizedMacro() throws Exception {
        String ligneMacro = "@Clement() +++";
        assertTrue(macro.isParameterizedMacro(ligneMacro));
        ligneMacro = "@Clement +++";
        assertFalse(macro.isParameterizedMacro(ligneMacro));
    }

    @Test
    public void retournerParametre() throws Exception {
        String macroTrouve = "CLEMENT(5)";
        assertEquals(5, macro.retournerParametre(macroTrouve));
        macroTrouve = "CLEMENT()";
        assertEquals("", macro.retournerParametre(macroTrouve));
    }

    @Test
    public void remplacerMacroParCode() throws Exception {

    }

    @Test
    public void supprimerLigneDeTexte() throws Exception {

    }

}