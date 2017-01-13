package brainfuck.language.function;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.WrongFunctionNameException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author jamatofu on 01/01/17.
 */
public class ParseFunctionTest {
    ParseFunction parser;

    @Before
    public void setUp() throws Exception {
        this.parser = new ParseFunction("void salut ++\n" +
                "function oui --\n" +
                "++\n" +
                "\n" +
                "INCR\n" +
                "<<>>\n" +
                "function non >>\n" +
                "void bye ..");
    }

    @Test
    public void findPrototype() throws Exception {
        Map<String, Function> catalogue;
        assertEquals("++\nINCR\n<<>>\n", this.parser.findPrototype());
        catalogue = this.parser.getFunctionMap();

        assertEquals(4, catalogue.size());
        assertTrue(catalogue.containsKey("oui"));
        assertFalse(catalogue.get("oui").isProcedure());
        assertEquals(Arrays.asList(new Keywords[]{Keywords.DECR, Keywords.DECR}) ,catalogue.get("oui").getCode());

        assertTrue(catalogue.containsKey("salut"));
        assertTrue(catalogue.get("salut").isProcedure());
        assertEquals(Arrays.asList(new Keywords[]{Keywords.INCR, Keywords.INCR}) ,catalogue.get("salut").getCode());

        assertTrue(catalogue.containsKey("non"));
        assertFalse(catalogue.get("non").isProcedure());
        assertEquals(Arrays.asList(new Keywords[]{Keywords.RIGHT, Keywords.RIGHT}) ,catalogue.get("non").getCode());

        assertTrue(catalogue.containsKey("bye"));
        assertTrue(catalogue.get("bye").isProcedure());
        assertEquals(Arrays.asList(new Keywords[]{Keywords.OUT, Keywords.OUT}) ,catalogue.get("bye").getCode());
    }

    @Test(expected = WrongFunctionNameException.class)
    public void addProcedure() throws Exception {
        Map<String, Function> catalogue;
        this.parser.addProcedure("void salut ++", true);
        catalogue = this.parser.getFunctionMap();
        assertTrue(catalogue.containsKey("salut"));
        assertTrue(catalogue.get("salut").isProcedure());
        assertEquals(Arrays.asList(new Keywords[]{Keywords.INCR, Keywords.INCR}), catalogue.get("salut").getCode());

        this.parser.addProcedure("function bye ++", false);
        catalogue = this.parser.getFunctionMap();
        assertTrue(catalogue.containsKey("bye"));
        assertFalse(catalogue.get("bye").isProcedure());
        assertEquals(Arrays.asList(new Keywords[]{Keywords.INCR, Keywords.INCR}), catalogue.get("salut").getCode());

        //exception WrongFunctionName
        this.parser.addProcedure("void INCR ++", true);

    }

    @Test
    public void isValidName() {
        assertTrue(this.parser.isValidName("aaa"));
        assertTrue(this.parser.isValidName("incr"));
        assertFalse(this.parser.isValidName("INCR"));
        assertFalse(this.parser.isValidName("654"));
    }

}