package brainfuck.language;

import brainfuck.language.exceptions.WrongMacroNameException;
import brainfuck.language.exceptions.function.BadFunctionDefinition;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 13/01/17.
 */
public class MacroTest {
    Macro macro;

    @Before
    public void setUp() throws Exception {
        String programme = "@a ++\n" +
                "@b a\n";
        macro = new Macro(programme);
    }

    @Test(expected = BadFunctionDefinition.class)
    public void findPrototype_BadFunctionDefinition() throws Exception {
        macro.setProgramme("@a ++ b\n +");
        macro.findPrototype();
    }

    @Test (expected = WrongMacroNameException.class)
    public void findPrototype_WrongMacroNameException() throws Exception {
        macro.setProgramme("@INCR ++\n +");
        macro.findPrototype();
    }

    @Test
    public void macroWithParam() throws Exception {
        Map<String, String > macroMap = new HashMap<>();
        macroMap.put("a", "+");
        String macroTest = "@a +\na 5\n";
        macro.setProgramme(macroTest);
        macro.setMacro(macroMap);

        macro.remplacerMacroParCode();
        assertEquals("@a +\n+++++\n", macro.getStringBuilder()); // n'est pas censé supprimer le @a + dans ce test là
    }

    @Test
    public void macroWithOutParam() throws Exception {
        Map<String, String > macroMap = new HashMap<>();
        macroMap.put("a", "+");
        String macroTest = "@a +\na\n";
        macro.setProgramme(macroTest);
        macro.setMacro(macroMap);

        macro.remplacerMacroParCode();
        assertEquals("@a +\n+\n", macro.getStringBuilder()); // n'est pas censé supprimer le @a + dans ce test là
    }

}