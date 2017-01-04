package brainfuck.language.flag.commande;

import brainfuck.language.enumerations.Keywords;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 01/01/17.
 */
public class RewriteTest {
    Rewrite rewrite;

    @Test
    public void rewriteProgram() throws Exception {
        List<Keywords> keywordsList = new ArrayList<>();
        keywordsList.add(Keywords.INCR);
        keywordsList.add(Keywords.DECR);
        keywordsList.add(Keywords.OUT);
        keywordsList.add(Keywords.IN);
        keywordsList.add(Keywords.LEFT);
        keywordsList.add(Keywords.RIGHT);
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        rewrite = new Rewrite(keywordsList);

        assertEquals("+-.,<>[]", rewrite.rewriteProgram());
    }

}