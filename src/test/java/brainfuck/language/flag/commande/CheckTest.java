package brainfuck.language.flag.commande;

import brainfuck.language.enumerations.Keywords;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jamatofu on 01/01/17.
 */
public class CheckTest {
    Check check = new Check();

    @Test
    public void isWellChecked() throws Exception {
        List<Keywords> keywordsList = new ArrayList<>();
        keywordsList.add(Keywords.INCR);
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        keywordsList.add(Keywords.INCR);
        assertTrue(check.isWellChecked(keywordsList));

        keywordsList.clear();
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        assertTrue(check.isWellChecked(keywordsList));

        keywordsList.clear();
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        keywordsList.add(Keywords.BACK);
        assertTrue(check.isWellChecked(keywordsList));

        keywordsList.clear();
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        assertFalse(check.isWellChecked(keywordsList));

        keywordsList.clear();
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        keywordsList.add(Keywords.BACK);
        assertFalse(check.isWellChecked(keywordsList));

        keywordsList.clear();
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.IN);
        keywordsList.add(Keywords.BACK);
        keywordsList.add(Keywords.BACK);
        assertTrue(check.isWellChecked(keywordsList));
    }

}