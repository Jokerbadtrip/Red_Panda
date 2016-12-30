package brainfuck.language.enumerations;

import brainfuck.language.flag.Flags;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 29/12/16.
 */
public class FlagsTest {

    @Test
    public void isFlag() throws Exception {

    }

    @Test
    public void showFlags() throws Exception {
        String exceptedResult = "Available commands are : -p -i -o --rewrite --check --translate --trace ";
        assertEquals(exceptedResult, Flags.showFlags());
    }

    @Test
    public void toFlag() throws Exception {

    }

    @Test
    public void getFlag() throws Exception {

    }

    @Test
    public void isNeedAFilePath() throws Exception {

    }

    @Test
    public void fromFlagToEnum() throws Exception {

    }

}