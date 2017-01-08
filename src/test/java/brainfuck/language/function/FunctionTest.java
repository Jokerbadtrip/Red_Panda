package brainfuck.language.function;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Enzo on 06/01/2017.
 */
public class FunctionTest {

    Function func;

    List<Keywords> keywords;

    @Before
    public void initialize() {
        keywords = new ArrayList<Keywords>();
        keywords.add(Keywords.INCR);
        keywords.add(Keywords.INCR);
        keywords.add(Keywords.RIGHT);
        keywords.add(Keywords.INCR);

        func = new Function(keywords,true,"This is a test");
    }

    @Test (expected = IsNotACommandException.class)
    public void setParametre() throws Exception {
        func.setParametre("++>+");
        assertEquals(keywords, func.getParametre());

        keywords.remove(Keywords.INCR);
        assertNotEquals(keywords, func.getParametre());

        func.setParametre("Not a command");
    }

    @Test
    public void hasParameter() throws Exception {
        assertFalse(func.hasParameter());
        func.setParametre("+++");
        assertTrue(func.hasParameter());

    }

}