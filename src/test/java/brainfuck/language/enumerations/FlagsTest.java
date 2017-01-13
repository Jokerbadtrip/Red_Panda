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
        String expectedResult = "Available commands are : -p -i -o --rewrite --check --translate --trace --java ";
        assertEquals(expectedResult, Flags.showFlags());
    }

    @Test
    public void toFlag() throws Exception {

    }

    @Test
    public void getFlag() throws Exception {

    }

    @Test
    public void NeedAFilePath() throws Exception {
        Flags in = Flags.FILE_TO_READ;
        assertEquals(in.NeedAFilePath(),true);
    }

    @Test
    public void fromFlagToEnum() throws Exception {
        Flags expectedFlag = Flags.IN;
        assertEquals(expectedFlag,Flags.fromFlagToEnum("-i"));
    }

}