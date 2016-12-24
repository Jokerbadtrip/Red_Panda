package brainfuck.language;

import brainfuck.language.exceptions.ValueOutOfBoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 * Created by Red_Panda on 16/11/2016.
 */
public class MemoryTest {

    Memory memory = new Memory();

    @Test(expected = ValueOutOfBoundException.class)
    public void incr() throws ValueOutOfBoundException {
        memory.incr();
        assertEquals(1,memory.getCellValue());

        memory.incr();
        assertNotEquals(3, memory.getCellValue());

        memory.updateMemory((short) 255);
        memory.incr();
    }

    @Test(expected = ValueOutOfBoundException.class)
    public void decr() throws Exception {
        memory.updateMemory((short) 4);

        memory.decr();
        assertEquals(3,memory.getCellValue());

        memory.decr();
        memory.decr();
        assertNotEquals(0,memory.getCellValue());

        memory.decr();
        memory.decr();
    }

    @Test
    public void right() throws Exception {

    }

    @Test
    public void left() throws Exception {

    }

    @Test
    public void printMemory() throws Exception {

    }

    @Test
    public void updateMemory() throws Exception {

    }

    @Test
    public void getmArray() throws Exception {

    }

    @Test
    public void getCellValue() throws Exception {

    }

}