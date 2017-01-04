package brainfuck.language.flag;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author jamatofu on 01/01/17.
 */
public class FlagsTest {

    @Test
    public void fromFlagToEnum() throws Exception {
        assertEquals(Flags.FILE_TO_READ, Flags.fromFlagToEnum("-p"));
        assertEquals(Flags.IN, Flags.fromFlagToEnum("-i"));
        assertEquals(Flags.OUT, Flags.fromFlagToEnum("-o"));
        assertEquals(Flags.REWRITE, Flags.fromFlagToEnum("--rewrite"));
        assertNull(Flags.fromFlagToEnum("-yo"));
    }

}