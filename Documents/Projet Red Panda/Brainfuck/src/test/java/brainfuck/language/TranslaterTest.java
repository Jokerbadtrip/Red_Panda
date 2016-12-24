package brainfuck.language;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jamatofu on 14/12/16.
 */
public class TranslaterTest {
    Translater translater;

    @Before
    public void setUp() throws Exception {
        this.translater = new Translater("++++++----++<<>>>[+].,");
    }

    @Test
    public void initializeProgram() throws Exception {

    }

    @Test
    public void finishProgram() throws Exception {

    }

    @Test
    public void countCharacterInARow() throws Exception {

    }

    @Test
    public void translateInstruction() throws Exception {

    }

    @Test
    public void writeLineIntoProgram() throws Exception {
        this.translater.writeLineIntoProgram("", 0);
    }

}