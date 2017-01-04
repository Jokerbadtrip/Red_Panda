package brainfuck.language.function;


import brainfuck.language.enumerations.Keywords;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jamatofu on 04/01/17.
 */
public class FunctionTest {

    Function function;

    @Before
    public void setUp() throws Exception {
        List<Keywords> keywordList = Arrays.asList(new Keywords[]{Keywords.IN, Keywords.OUT});
        function = new Function(keywordList, true, "Fonction");
    }

    @Test
    public void setParametre() throws Exception {
        function.setParametre("++-");
        List<Keywords> keywordsList = Arrays.asList(new Keywords[] {Keywords.INCR, Keywords.INCR, Keywords.DECR});
        assertEquals(keywordsList, function.getParametre());
    }

    @Test
    public void hasParameter() throws Exception {
        assertFalse(function.hasParameter());
        function.setParametre("+");
        assertTrue(function.hasParameter());
    }

}