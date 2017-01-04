package brainfuck.language;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.flag.commande.Rewrite;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 29/12/16.
 */
public class RewriteTest {
    Rewrite rewrite;

    @Before
    public void setUp() {
        List<Keywords> list = new ArrayList<>();
        list.add(Keywords.INCR);
        list.add(Keywords.DECR);
        list.add(Keywords.IN);
        list.add(Keywords.OUT);
        list.add(Keywords.LEFT);
        list.add(Keywords.RIGHT);
        list.add(Keywords.JUMP);
        list.add(Keywords.BACK);
        this.rewrite = new Rewrite(list);
    }

    @Test
    public void rewriteProgram() throws Exception {
        String result = "+-,.<>[]";

        assertEquals(result, this.rewrite.rewriteProgram());
    }

}