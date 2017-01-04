package brainfuck.language;

import brainfuck.language.exceptions.OutOfMemoryException;
import brainfuck.language.exceptions.ValueOutOfBoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
/**
 *
 * Created by Red_Panda on 16/11/2016.
 */
public class MemoryTest {

    Memory memory = new Memory(true);

    @Test(expected = ValueOutOfBoundException.class)
    public void incr() throws ValueOutOfBoundException {
        memory.incr();
        assertEquals(1,memory.getCellValue());

        memory.updateMemory((short) 255);
        memory.incr();
    }

    @Test(expected = ValueOutOfBoundException.class)
    public void decr() throws Exception {
        memory.updateMemory((short) 4);
        assertEquals(4,memory.getCellValue());

        memory.decr();
        assertEquals(3,memory.getCellValue());

        memory.decr();
        memory.decr();
        memory.decr();

        memory.decr();
    }

    @Test (expected = OutOfMemoryException.class)
    public void right() throws Exception {
        assertEquals(0,memory.getPointer());
        memory.right();
        assertEquals(1,memory.getPointer());

        memory.setPointer(29999);
        memory.right();
    }

    @Test (expected = OutOfMemoryException.class)
    public void left() throws Exception {
        memory.setPointer(2);
        assertEquals(2,memory.getPointer());
        memory.left();
        assertEquals(1,memory.getPointer());

        memory.left();
        memory.left();
    }

    @Test
    public void writeStateOfMemory(){
        memory.incr();
        memory.right();
        memory.right();
        memory.incr();

        assertEquals("C0: 1 C2: 1 ", memory.writeStateOfMemory());
    }

    @Test (expected = ValueOutOfBoundException.class)
    public void updateMemory(){
        memory.updateMemory((short) 255);
        assertEquals(255, memory.getCellValue());

        memory.right();
        memory.incr();
        memory.updateMemory((short) 0);
        assertEquals(0,memory.getCellValue());

        memory.updateMemory((short) 10000);
    }

    @Test
    public void resetMemory(){
        memory.incr();
        memory.right();
        memory.incr();

        assertEquals("C0: 1 C1: 1 ", memory.writeStateOfMemory());
        memory.resetMemory();
        assertEquals("", memory.writeStateOfMemory());

    }
}