package brainfuck.language;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.flag.commande.Check;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jamatofu on 29/12/16.
 */
public class CheckTest {
    Check check;

    @Test
    public void isWellChecked() throws Exception {
        List<Keywords> keywordsList = new ArrayList<>();
        this.check = new Check();
        assertTrue(this.check.isWellChecked(keywordsList));

        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        this.check = new Check();
        assertTrue(this.check.isWellChecked(keywordsList));

        keywordsList.add(1, Keywords.JUMP);
        keywordsList.add(2, Keywords.BACK);
        this.check = new Check();
        assertTrue(this.check.isWellChecked(keywordsList));

        keywordsList.clear();
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        this.check = new Check();
        assertTrue(this.check.isWellChecked(keywordsList));

        keywordsList.clear();
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        this.check = new Check();
        assertFalse(this.check.isWellChecked(keywordsList));

        keywordsList.clear();
        keywordsList.add(Keywords.JUMP);
        keywordsList.add(Keywords.BACK);
        keywordsList.add(Keywords.BACK);
        this.check = new Check();
        assertFalse(this.check.isWellChecked(keywordsList));
    }

}